package ecom.strategy.payment;

public class CardPayment implements PaymentStrategy{
    @Override
    public boolean makePayment(double amount){
        System.out.println("Paying via Card : " + amount);
        return true;
    }
}