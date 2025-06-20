package controller;

import dao.ProductDAO;
import model.Product;
import view.ProductView;
import java.util.List;
import model.Cart;
import model.Order;

public class ProductController {
    private ProductDAO dao;
    private ProductView view;
    private Cart cart = new Cart();

    public ProductController(ProductDAO dao, ProductView view) {
        this.dao = dao;
        this.view = view;
    }

    public void showProducts() {
        List<Product> products = dao.getAllProducts();
        view.displayProducts(products);
    }

    public void addToCart(Product product) {
        cart.addProduct(product, 1);
    }

    public Order checkout() {
        // Update stock in DB
        for (var entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            dao.updateProductQuantity(p.getId(), p.getQuantity() - qty);
        }
        Order order = new Order(new java.util.HashMap<>(cart.getItems()), cart.getTotal());
        cart.clear();
        return order;
    }

    public Cart getCart() {
        return cart;
    }
}
