package dao;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product(1, "Whey Protein", 5500.0, 10));
        products.add(new Product(2, "Creatine", 3200.0, 0)); // Out of Stock
        products.add(new Product(3, "Pre-Workout", 2800.0, 5));
        products.add(new Product(4, "Mass Gainer", 4500.0, 7));
        products.add(new Product(5, "BCAA", 2500.0, 3));

        return products;
    }
}
