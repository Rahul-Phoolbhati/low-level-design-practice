package ecom.models;
import java.util.UUID;

public class Customer{
    private String id;
    private String name;
    private Cart cart;

    public Customer(String name){
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.cart = new Cart(id);
    }

    public String getName(){
        return this.name;
    }

    public Cart getCart(){
        return this.cart;
    }

    public void addToCart(Product p, int quantity){
        this.cart.putItem(p, quantity);;
        System.out.println("Product" + p.getName() + "added in the cart");
    }

    public void removeFromCart(Product p){
        this.cart.removeItem(p.getId());
        System.out.println("Product " + p.getName() + " removed from the cart");
    }

    public Order createOrder(){
        return new Order(this.id, this.cart);
    }

}