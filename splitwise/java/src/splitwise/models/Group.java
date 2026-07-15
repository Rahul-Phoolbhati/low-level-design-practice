package java.src.splitwise.models;

import java.src.splitwise.DebtSimplifier;
import java.src.splitwise.eums.SplitType;
import java.src.splitwise.factory.SplitFactory;
import java.src.splitwise.interfaces.CustomObserver;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    public static int nextGroupId = 0;
    private  String id;
    

    private  String name;
    private  List<User> members;
    Map<String, Expense> expences;
    Map<String, Map<String, Double>> groupBalances;
    public Group(String name) {
        this.name = name;
        this.expences = new HashMap<>();
        this.groupBalances = new HashMap<>();
        this.id = "group" + (++nextGroupId);
        this.members = new ArrayList<>();
    }
    private User getUserByuserId(String userId) {
        User user = null;

        for(User member : members) {
            if(member.getId().equals(userId)) {
                user = member;
            }
        }
        return user;
    }

    public void addMember(User user){
        members.add(user);

        groupBalances.put(user.getId(), new HashMap<>());
    }

    public boolean removeMember(String userId){
        if(!canUserLeave(userId)){
            throw new RuntimeException("User cant leave without settlement");
        }

        members.removeIf(user -> user.getId().equals(userId));

        groupBalances.remove(userId);

        for(Map.Entry<String, Map<String, Double>> memberBal : groupBalances.entrySet()){
            memberBal.getValue().remove(userId);
        }
        return true;

    }

    public boolean isMember(String userId) {
        return groupBalances.containsKey(userId);
    }
    
    public void updateGroupBalance(String fromUserId, String toUserId, double amount) {
        groupBalances.get(fromUserId).put(toUserId, 
            groupBalances.get(fromUserId).getOrDefault(toUserId, 0.0) + amount);
        groupBalances.get(toUserId).put(fromUserId, 
            groupBalances.get(toUserId).getOrDefault(fromUserId, 0.0) - amount);
        
        // Remove if balance becomes zero
        if (Math.abs(groupBalances.get(fromUserId).get(toUserId)) < 0.01) {
            groupBalances.get(fromUserId).remove(toUserId);
        }
        if (Math.abs(groupBalances.get(toUserId).get(fromUserId)) < 0.01) {
            groupBalances.get(toUserId).remove(fromUserId);
        }
    }

    public boolean canUserLeave(String userId){
        if (!isMember(userId)) {
            throw new RuntimeException("user is not a part of this group");
        }
        
        Map<String, Double> userBalanceSheet = groupBalances.get(userId);
        for (Map.Entry<String, Double> balance : userBalanceSheet.entrySet()) {
            if (Math.abs(balance.getValue()) > 0.01) {
                return false; 
            }
        }
        return true;
    }


    public boolean addExpense(String description, double amount, String paidByUserId,
                   List<String> involvedUsers, SplitType splitType, 
                   List<Double> splitValues) {
        
        if (!isMember(paidByUserId)) {
            throw new RuntimeException("user is not a part of this group");
        }
        
        for (String userId : involvedUsers) {
            if (!isMember(userId)) {
                throw new RuntimeException("involvedUsers are not a part of this group");
            }
        }
        
        List<Split> splits = SplitFactory.getSplitStrategy(splitType)
                                .calcSplit(amount, involvedUsers, splitValues);
        
        Expense expense = new Expense(description, splits, paidByUserId, amount, id);
        expences.put(expense.getId(), expense);
        
        for (Split split : splits) {
            if (!split.getUserId().equals(paidByUserId)) {
                // Person who paid gets positive balance, person who owes gets negative
                updateGroupBalance(paidByUserId, split.getUserId(), split.getAmt());
            }
        }
        
        System.out.println("\n=========== Sending Notifications ====================");
        String paidByName = getUserByuserId(paidByUserId).getName();
        notifyMembers("New expense added: " + description + " (Rs " + amount + ")");
        
        System.out.println("\n=========== Expense Message ====================");
        System.out.println("Expense added to " + name + ": " + description + " (Rs " + amount 
             + ") paid by " + paidByName +" and involved people are : ");
        if(!splitValues.isEmpty()) {
            for(int i=0; i<splitValues.size(); i++) {
                System.out.println(getUserByuserId(involvedUsers.get(i)).getName() + " : " + splitValues.get(i));
            }
        } 
        else {
            for(String user : involvedUsers) {
                System.out.print(getUserByuserId(user).getName() + ", ");
            }
            System.out.println("\nWill be Paid Equally");
        }    
        
        return true;
    }
    public boolean addExpense(String description, double amount, String paidByUserId,
                   List<String> involvedUsers, SplitType splitType) {
        return addExpense(description, amount, paidByUserId, involvedUsers, splitType, new ArrayList<>());
    }
        public boolean settlePayment(String fromUserId, String toUserId, double amount) {
        if (!isMember(fromUserId) || !isMember(toUserId)) {
            System.out.println("user is not a part of this group");
            return false;
        }
        
        updateGroupBalance(fromUserId, toUserId, amount);
        
        String fromName = getUserByuserId(fromUserId).getName();
        String toName = getUserByuserId(toUserId).getName();
        
        notifyMembers("Settlement: " + fromName + " paid " + toName + " Rs " + amount);
        
        System.out.println("Settlement in " + name + ": " + fromName + " settled Rs " 
             + amount + " with " + toName);
        
        return true;
    }

    public void showGroupBalances() {
        System.out.println("\n=== Group Balances for " + name + " ===");
        DecimalFormat df = new DecimalFormat("#.##");
        
        for (Map.Entry<String, Map<String, Double>> pair : groupBalances.entrySet()) {
            String memberId = pair.getKey();
            String memberName = getUserByuserId(memberId).getName();

            System.out.println(memberName + "'s balances in group:");
            
            Map<String, Double> userBalances = pair.getValue();
            if (userBalances.isEmpty()) {
                System.out.println("  No outstanding balances");
            } 
            else {
                for (Map.Entry<String, Double> userBalance : userBalances.entrySet()) {
                    String otherMemberUserId = userBalance.getKey();
                    String otherName = getUserByuserId(otherMemberUserId).getName();
                    
                    double balance = userBalance.getValue();
                    if (balance > 0) {
                        System.out.println("  " + otherName + " owes: Rs " + df.format(balance));
                    } else {
                        System.out.println("  Owes " + otherName + ": Rs " + df.format(Math.abs(balance)));
                    }
                }
            }
        }
    }
    


    public void notifyMembers(String message) {
        for (CustomObserver observer : members) {
            observer.updateByNotification(message);
        }
    }

    public Map<String, Double> getUserGroupBalances(String userId) {
        if (!isMember(userId)) {
            throw new RuntimeException("user is not a part of this group");
        }
        return groupBalances.get(userId);
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void simplifyGroupDebts() {
        Map<String, Map<String, Double>> simplifiedBalances = DebtSimplifier.simplifyDebts(groupBalances);
        groupBalances = simplifiedBalances;
    
        System.out.println("\nDebts have been simplified for group: " + name);
    }

    

}
