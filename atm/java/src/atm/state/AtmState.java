package atm.state;
import atm.Atm;
import atm.enums.OperationType;
import atm.models.Card;

public interface AtmState {

    public void insertCard(Atm atmInstance, Card card);
    public void enterPin(atm.Atm atmInstance, int pin);
    public void selectOp(Atm atmInstance, OperationType op, int... args);
    public void ejectCard(Atm atmInstance);
}
