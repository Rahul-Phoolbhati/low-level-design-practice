package java.src.splitwise.models;

import java.util.*;

public class Expense {

    public static int nextExpenseId=0;
    private String id;
    private String desc;
    private List<Split> splits;
    public String paidByUserId;
    
    private double amt;
    private String groupId;
    

    public Expense(String desc, List<Split> splits, String paidByUserId, double amt, String groupId) {
        this.id = "expense" + (++nextExpenseId);
        this.desc = desc;
        this.splits = splits;
        this.paidByUserId = paidByUserId;
        this.amt = amt;
        this.groupId = groupId;
    }
    public Expense(String desc, List<Split> splits, String paidByUserId, double amt) {
        this(desc, splits, paidByUserId, amt, "");
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public List<Split> getSplits() {
        return splits;
    }
    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
    public double getAmt() {
        return amt;
    }
    public void setAmt(double amt) {
        this.amt = amt;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getPaidByUserId() {
        return paidByUserId;
    }
    public void setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
    }
}
