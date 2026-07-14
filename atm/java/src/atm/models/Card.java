package atm.models;

public class Card {
    private int number;
    private Account account;
    private int pin;
    public Card(int number, Account account, int pin) {
        this.number = number;
        this.account = account;
        this.pin = pin;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
}
