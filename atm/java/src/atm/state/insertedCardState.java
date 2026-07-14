package atm.state;

import atm.enums.OperationType;


import atm.Atm;

import atm.models.Card;

public class insertedCardState implements AtmState {

    @Override
    public void insertCard(Atm atmInstance, Card card) {
        throw new IllegalStateException("Card Already inserted");
    }

    @Override
    public void enterPin(Atm atmInstance, int pin) {
        if(atmInstance.authenticate(pin))
            atmInstance.setState(new AuthenticatedState());
        else{
            ejectCard(atmInstance);
            throw new RuntimeException("Wrong Pin, Insert the card again");
        }
            
    }

    @Override
    public void selectOp(Atm atmInstance, OperationType op, int... args) {
        throw new IllegalStateException("Authenticate first");
    }

    @Override
    public void ejectCard(Atm atmInstance) {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atmInstance.setCard(null);
        atmInstance.setState(new IdleState());
    }
    
}
