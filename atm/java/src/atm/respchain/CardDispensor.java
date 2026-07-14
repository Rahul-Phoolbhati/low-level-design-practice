package atm.respchain;

public abstract class CardDispensor {

    protected int avlCount;
    protected CardDispensor next;


    public CardDispensor(int avlCount) {
        this.avlCount = avlCount;
    }
    public CardDispensor(int avlCount, CardDispensor next) {
        this.avlCount = avlCount;
        this.next = next;
    }
    public int getAvlCount() {
        return avlCount;
    }
    public void setAvlCount(int avlCount) {
        this.avlCount = avlCount;
    }
    public CardDispensor getNext() {
        return next;
    }
    public void setNext(CardDispensor next) {
        this.next = next;
    }

    public abstract void dispence(int amount);
    public abstract boolean canDispence(int amount);

}
