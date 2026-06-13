package ecom.models;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product p, int quantity){
        this.product = p;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int q){
        this.quantity = q;
    }
    
}
