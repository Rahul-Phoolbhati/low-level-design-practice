package atm.respchain;

public class NoteDispensor100 extends CardDispensor {
    

    public NoteDispensor100(int avlCount) {
        super(avlCount);
    }

    public NoteDispensor100(int avlCount, CardDispensor next) {
        super(avlCount, next);
    }

    @Override
    public void dispence(int amount) {
        int notes = Math.min(amount / 100, this.avlCount);
        int remainder = amount - notes * 100;

        if(notes > 0) System.out.println("Dispensed " + notes + " x ₹100 notes");
        
        if (remainder > 0 && next != null) {
            next.dispence(remainder);
        }
        this.avlCount -= notes;
    }

    @Override
    public boolean canDispence(int amount) {
        int notes = Math.min(this.avlCount, amount/100);
        int rem = amount - notes*100;

        return rem==0 || (next!= null && next.canDispence(rem));
    }

}
