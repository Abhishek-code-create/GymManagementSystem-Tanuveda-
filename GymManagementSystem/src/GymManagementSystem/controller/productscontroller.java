/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagementSystem.controller;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class productscontroller {
    public static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    private List<Product> productList;

    public productscontroller() {
        productList = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        // You can replace these with dynamic data from a database later
        productList.add(new Product("Protein Powder", 2000));
        productList.add(new Product("Shaker Bottle", 500));
        productList.add(new Product("Yoga Mat", 800));
        productList.add(new Product("Resistance Bands", 1200));
        productList.add(new Product("Dumbbells Set", 3500));
        productList.add(new Product("Gym Bag", 1500));
    }

    public List<Product> getProducts() {
        return productList;
    }
}

    

