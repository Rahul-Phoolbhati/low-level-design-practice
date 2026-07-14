package atm.state;

import atm.enums.OperationType;
import atm.models.Card;
import atm.Atm;

public class IdleState implements AtmState{

    @Override
    public void insertCard(Atm atmInstance,  Card card) {
        atmInstance.setCard(card);
        System.out.println("Card inserted");
        atmInstance.setState(new insertedCardState());
    }

    @Override
    public void enterPin(Atm atmInstance, int pin) {
        throw new IllegalStateException("Insert card first");
    }

    @Override
    public void selectOp(Atm atmInstance, OperationType op, int... args) {
        throw new IllegalStateException("Insert card first");
    }

    @Override
    public void ejectCard(Atm atmInstance) {
        throw new IllegalStateException("Insert card first");
    }

}
