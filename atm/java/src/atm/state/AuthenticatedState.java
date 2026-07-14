package atm.state;

import atm.enums.OperationType;

import atm.Atm;
import atm.models.Card;

public class AuthenticatedState implements AtmState {

    @Override
    public void insertCard(Atm atmInstance, Card card) {
        throw new IllegalStateException("Card Already inserted");
    }

    @Override
    public void enterPin(Atm atmInstance, int pin) {
        throw new IllegalStateException(" Already Authenticated");
    }

    @Override
    public void selectOp(Atm atmInstance, OperationType op, int... args) {
        switch (op) {
            case CHECK_BALANCE:
                System.out.println(atmInstance.checkBalance());
                break;
            
            case WITHDRAW:
                if (args.length == 0 || args[0] <= 0) {
                    System.out.println("Error: Invalid withdrawal amount specified.");
                    break;
                }
                int amountToWithdraw = args[0];
                atmInstance.withDrawCash(amountToWithdraw);
                break;
        
            default:
                break;
        }
    }

    @Override
    public void ejectCard(Atm atmInstance) {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atmInstance.setCard(null);
        atmInstance.setState(new IdleState());
    }
    
}
