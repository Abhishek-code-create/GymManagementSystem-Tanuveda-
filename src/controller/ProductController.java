package controller;

import dao.ProductDAO;
import model.Product;
import view.MainView;

import java.util.List;

public class ProductController {
    private ProductDAO dao;
    private MainView view;

    public ProductController() {
        dao = new ProductDAO();
        view = new MainView(this);
        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = dao.getAllProducts();
        view.displayProducts(products);
    }

    public void showView() {
        view.setVisible(true);
    }
}
