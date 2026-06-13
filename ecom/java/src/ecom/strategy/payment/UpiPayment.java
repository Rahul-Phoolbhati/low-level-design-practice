package ecom.strategy.payment;

public class UpiPayment implements PaymentStrategy{
    @Override
    public boolean makePayment(double amount){
        System.out.println("Paying via Upi : " + amount);
        return true;
    }
}