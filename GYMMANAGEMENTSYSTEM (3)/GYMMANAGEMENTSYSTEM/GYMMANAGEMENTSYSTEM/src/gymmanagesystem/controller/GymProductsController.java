/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagesystem.controller;
import gymmanagesystem.Dao.ProductDAO;
import gymmanagesystem.model.ProductModel;
import gymmanagesystem.view.gymproducts;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class GymProductsController {
    private gymproducts view;
    private ProductDAO productDAO;
    private List<ProductModel> products;

    public GymProductsController(gymproducts view) {
        this.view = view;
        this.productDAO = new ProductDAO();
        initialize();
    }

    private void initialize() {
        loadProducts();
        setupButtonListeners();
    }
    private void loadProducts() {
        try {
            products = productDAO.getAllProducts();
            if (products == null || products.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                    "No products available", 
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

    

        // Update UI with product data
        if (products.size() > 0) {
            ProductModel product1 = products.get(0);
            view.getProduct1NameLabel().setText(product1.getName());
            
            view.getProduct1PriceLabel().setText("NRS " + product1.getPrice());
            view.getProduct1Button().setText(product1.getStock() > 0 ? "BUY NOW !!!" : "OUT OF STOCK");
            view.getProduct1Button().setEnabled(product1.getStock() > 0);
        }

        if (products.size() > 1) {
            ProductModel product2 = products.get(1);
            view.getProduct2NameLabel().setText(product2.getName());
            
            view.getProduct2PriceLabel().setText("NRS " + product2.getPrice());
            view.getProduct2Button().setText(product2.getStock() > 0 ? "BUY NOW !!!" : "OUT OF STOCK");
            view.getProduct2Button().setEnabled(product2.getStock() > 0);
        }

        if (products.size() > 2) {
            ProductModel product3 = products.get(2);
            view.getProduct3NameLabel().setText(product3.getName());
            
            view.getProduct3Button().setText(product3.getStock() > 0 ? "BUY NOW !!!" : "OUT OF STOCK");
            view.getProduct3Button().setEnabled(product3.getStock() > 0);
        }
    }

    private void setupButtonListeners() {
        view.getBackButton();
        view.getProduct1Button();
        view.getProduct2Button();
        view.getProduct3Button();
    }

    private void purchaseProduct(int productIndex) {
        if (productIndex >= products.size()) {
            JOptionPane.showMessageDialog(view, 
                "Product not available", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductModel product = products.get(productIndex);
        if (product.getStock() > 0) {
            int newStock = product.getStock() - 1;
            if (productDAO.updateProductStock(product.getId(), newStock)) {
                product.setStock(newStock);
                JOptionPane.showMessageDialog(view, 
                    "Purchase Successful! Remaining Stock: " + newStock, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Update button if stock is now 0
                if (newStock == 0) {
                    switch(productIndex) {
                        case 0: 
                            view.getProduct1Button().setEnabled(false); 
                            view.getProduct1Button().setText("OUT OF STOCK"); 
                            break;
                        case 1: 
                            view.getProduct2Button().setEnabled(false); 
                            view.getProduct2Button().setText("OUT OF STOCK"); 
                            break;
                        case 2: 
                            view.getProduct3Button().setEnabled(false); 
                            view.getProduct3Button().setText("OUT OF STOCK"); 
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Failed to update stock in database", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, 
                "This item is currently out of stock.", 
                "Out of Stock", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void backToDashboard() {
        view.dispose();
        new gymmanagesystem.view.DashBoardView().setVisible(true);
    }
}