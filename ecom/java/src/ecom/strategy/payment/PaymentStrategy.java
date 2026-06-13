package ecom.strategy.payment;

public interface PaymentStrategy{
    boolean makePayment(double amount);
}