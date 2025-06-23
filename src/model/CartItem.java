package model;

public class CartItem {
    private Product Product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.Product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return Product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return Product.getPrice() * quantity;
    }
}
