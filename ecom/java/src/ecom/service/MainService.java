package ecom.service;

import ecom.strategy.payment.PaymentStrategy;
import ecom.strategy.payment.UpiPayment;


import ecom.enums.ProductCategory;
import ecom.models.Cart;
import ecom.models.Customer;
import ecom.models.Order;
import ecom.models.Product;;



public class MainService {

    public void checkout(double amount, PaymentStrategy paymentStrategy) {

        boolean success = paymentStrategy.makePayment(amount);

        if (success) {
            System.out.println("Order placed");
        }
    }

    public static void main(String[] args) {

        Customer cust = new Customer("rahul");
        // List<String> products = {"Laptop", "mobile", "apple"};
        // List<Integer> stock = {2,5,8};
        // List<Integer> prices = {40000, 20000, 50};
        // List<ProductCategory> pc = {1,0,2,3};

        Product laptop = new Product("Laptop", 2, ProductCategory.ELECTRONICS, 40000);
        Product mobile = new Product("mobile", 5, ProductCategory.ELECTRONICS, 20000);
        Product apple = new Product("apple", 8, ProductCategory.GROCERY, 50);

        cust.addToCart(apple, 2);
        cust.addToCart(mobile, 1);
        
        Cart cart = cust.getCart();
        cart.displayCart();

        cart.decreaseItemQ(apple.getId(), 1);
        
        cart.displayCart();
        System.out.println("again pulling and printing");
        cart = cust.getCart();
        cart.displayCart();

        Order order = cust.createOrder();
        
        PaymentStrategy paymentMethod = new UpiPayment();
        boolean paymentStatus = paymentMethod.makePayment(order.getTotal());

        if (paymentStatus) {
            order.placeOrder();
        }

        MainService service = new MainService();

        service.checkout(1000, new UpiPayment());
    }
}