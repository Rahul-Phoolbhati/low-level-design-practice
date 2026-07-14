package atm;

import java.util.Map;

import atm.models.Account;
import atm.models.Card;
import atm.enums.OperationType;


public class AtmDemo {

    public static void main(String[] args) {
        Map<Integer, Integer> noteInventory = Map.of(
            500, 2,
            200, 20,
            100, 30
        );

        Atm atmInstance = new Atm(noteInventory);
        Account acc = new Account(0, 50000);
        Card c = new Card(84884, acc, 8888);

        atmInstance.insertCard(c);
        // atmInstance.selectOp(OperationType.CHECK_BALANCE);

        atmInstance.enterPin(8888);
        atmInstance.selectOp(OperationType.WITHDRAW, 1000);
        atmInstance.ejectCard();

        // atmInstance.enterPin(8898);

        atmInstance.insertCard(c);
        atmInstance.enterPin(8888);
        atmInstance.selectOp(OperationType.CHECK_BALANCE);
        atmInstance.ejectCard();

        
        atmInstance.insertCard(c);
        atmInstance.enterPin(8888);
        atmInstance.selectOp(OperationType.WITHDRAW, 1200);

    }


}



// State change in states can be via an enum and TransitionTo fucniton 
// or specific funtions for one to another state change like moveIdelToInsertState