package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return items.entrySet().stream()
            .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
            .sum();
    }

    public void clear() {
        items.clear();
    }
} 