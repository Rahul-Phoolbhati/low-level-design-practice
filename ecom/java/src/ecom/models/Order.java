package ecom.models;

import java.util.ArrayList;
import java.util.List;

import ecom.enums.OrderStatus;

public class Order {
    private String id;
    private String customerId;
    private final List<OrderItem> orderItems;
    private OrderStatus status;

    public Order(String customerId, Cart c){
        this.customerId = customerId;
        this.orderItems = new ArrayList<>(c.orderItems);
        this.status = OrderStatus.PENDING;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getTotal(){
        int total =0;
        for(OrderItem item : orderItems){
            total += item.getProduct().getPrice()*item.getQuantity();
        }
        return total;
    }

    public boolean placeOrder(){
        this.status = OrderStatus.PLACED;
        System.out.println("Order placed with payment");
        System.out.print("-------------------");
        return true;
    }

}
