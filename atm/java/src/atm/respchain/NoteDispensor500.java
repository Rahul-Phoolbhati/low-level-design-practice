package atm.respchain;

public class NoteDispensor500 extends CardDispensor {

    public NoteDispensor500(int avlCount) {
        super(avlCount);
    }

    public NoteDispensor500(int avlCount, CardDispensor next) {
        super(avlCount, next);
    }

    @Override
    public void dispence(int amount) {
        int notes = Math.min(amount / 500, this.avlCount);
        int remainder = amount - notes * 500;

        if(notes > 0) System.out.println("Dispensed " + notes + " x ₹500 notes");
        
        if (remainder > 0 && next != null) {
            next.dispence(remainder);
        }
        this.avlCount -= notes;
    }

    @Override
    public boolean canDispence(int amount) {
        int notes = Math.min(this.avlCount, amount/500);
        int rem = amount - notes*500;

        return rem==0 || (next!= null && next.canDispence(rem));
    }
    
}
