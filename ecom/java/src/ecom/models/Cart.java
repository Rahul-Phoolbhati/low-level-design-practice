package ecom.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    String id;
    List<OrderItem> orderItems;
    String customerId;
    
    public Cart(String customerId){
        this.customerId = customerId;
        this.orderItems = new ArrayList<>();
    }

    public void displayCart() {
        if (orderItems.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        System.out.println("\n--- SHOPPING CART CONTENTS ---");
        double total = 0.0;
        for (OrderItem item : orderItems) {
            double itemTotal = item.getQuantity() * item.getProduct().getPrice();
            System.out.printf("Product: %s | Quantity: %d | Price: $%d | Subtotal: $%.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice(), itemTotal);
            total += itemTotal;
        }
        System.out.printf("--------------------------------- Total: $%.2f%n", total);
    }

    public void putItem(Product p, int quantity){
        if(p == null || quantity == 0)

        for (OrderItem item : this.orderItems) {
            if (item.getProduct().getId().equals(p.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        OrderItem newItem = new OrderItem(p, quantity);
        this.orderItems.add(newItem);
    }

    public boolean decreaseItemQ(String productId, int q){
        if (q <= 0) {
            System.out.println("Error: Decrease quantity must be positive.");
            return false;
        }
        for (OrderItem item : this.orderItems) {
            if (item.getProduct().getId().equals(productId)) {
                if (item.getQuantity() < q) {
                    System.out.printf("Error: Cannot decrease quantity by %d. Only %d remaining.%n", q, item.getQuantity());
                    return false;
                }

                item.setQuantity(item.getQuantity() - q);

                if (item.getQuantity() == 0) {
                    this.orderItems.remove(item);
                }
                return true;
            }
        }
        System.out.println("Error: Product ID " + productId + " not found in cart.");
        return false;
    }

    public boolean removeItem(String productId) {
        boolean removed = false;
        for (OrderItem item : this.orderItems) {
            if (item.getProduct().getId().equals(productId)) {
                this.orderItems.remove(item);
                removed = true;
                break; 
            }
        }
        return removed;
    }

}
