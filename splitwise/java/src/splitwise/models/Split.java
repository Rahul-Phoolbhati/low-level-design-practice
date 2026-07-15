package java.src.splitwise.models;

public class Split {
    private String userId;
    private double amt;
    
    public Split(String userId, double amt) {
        this.userId = userId;
        this.amt = amt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

}
