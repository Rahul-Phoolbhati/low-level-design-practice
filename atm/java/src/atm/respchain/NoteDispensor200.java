package atm.respchain;

public class NoteDispensor200 extends CardDispensor {

    public NoteDispensor200(int avlCount) {
        super(avlCount);
    }

    public NoteDispensor200(int avlCount, CardDispensor next) {
        super(avlCount, next);
    }

    @Override
    public void dispence(int amount) {
        int notes = Math.min(amount / 200, this.avlCount);
        int remainder = amount - notes * 200;

        if(notes > 0) System.out.println("Dispensed " + notes + " x ₹200 notes");
        
        if (remainder > 0 && next != null) {
            next.dispence(remainder);
        }
        this.avlCount -= notes;
    }

    @Override
    public boolean canDispence(int amount) {
        int notes = Math.min(this.avlCount, amount/200);
        int rem = amount - notes*200;

        return rem==0 || (next!= null && next.canDispence(rem));
    }

    
}
