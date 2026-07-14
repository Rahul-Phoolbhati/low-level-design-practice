package atm;

import java.util.Map;

import atm.enums.OperationType;
import atm.models.Card;
import atm.respchain.CardDispensor;
import atm.respchain.DispensorChainBuilder;
import atm.state.AtmState;
import atm.state.IdleState;

public class Atm {

    private Map<Integer, Integer> noteInventory;
    private Card card;
    private CardDispensor CardDispensor;    
    private AtmState state;

    public Atm(Map<Integer, Integer> noteInventory) {
        this.noteInventory = noteInventory;
        this.CardDispensor = DispensorChainBuilder.build(noteInventory);
        this.state = new IdleState();
    }

    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public CardDispensor getCardDispensor() {
        return CardDispensor;
    }
    public void setCardDispensor(CardDispensor cardDispensor) {
        CardDispensor = cardDispensor;
    }
    public AtmState getState() {
        return state;
    }
    public void setState(AtmState state) {
        this.state = state;
    }
    

    public void insertCard(Card card){
        state.insertCard(this, card);
    }
    public void ejectCard(){
        state.ejectCard(this);
    }
    public void enterPin(int pin){
        state.enterPin(this, pin);
    }
    
    public boolean authenticate(int pin){
        return this.getCard().getPin() == pin;
    }

    public void selectOp(OperationType op, int... args){
        state.selectOp(this, op, args);
    }

    public void withDrawCash(int amount){
        if(card.getAccount().getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        if(!CardDispensor.canDispence(amount)){
            state.ejectCard(this);
            throw new RuntimeException("Wrong amount");
        }
        card.getAccount().setBalance(card.getAccount().getBalance() - amount);
        CardDispensor.dispence(amount);
    }
    public int checkBalance(){
        return card.getAccount().getBalance();
    }

}
