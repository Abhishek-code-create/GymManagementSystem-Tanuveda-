package model;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private Map<Product, Integer> items;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private double total;

    public Order(Map<Product, Integer> items, double total) {
        this.items = items;
        this.orderDate = LocalDate.now();
        this.deliveryDate = orderDate.plusDays(3);
        this.total = total;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public double getTotal() {
        return total;
    }
} 