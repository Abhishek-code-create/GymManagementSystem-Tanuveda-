package controller;

import model.CartItem;
import model.Product;
import view.CartView;

import java.util.ArrayList;
import java.util.List;

public class CartController {
    private List<CartItem> cart;
    private CartView cartView;

    public CartController() {
        cart = new ArrayList<>();
        cartView = new CartView(this);
    }

    public void addToCart(Product product, int quantity) {
        for (CartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new CartItem(product, quantity));
    }

    public void removeFromCart(Product product) {
        cart.removeIf(item -> item.getProduct().getId() == product.getId());
        cartView.displayCart(cart);
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void showCartView() {
        cartView.displayCart(cart);
        cartView.setVisible(true);
    }

    public void checkout() {
        new view.ConfirmationView(cart, getTotalPrice()).setVisible(true);
        cart.clear(); // clear cart after checkout
        cartView.dispose();
    }
}
