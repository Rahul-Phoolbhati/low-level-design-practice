package java.src.splitwise;

import java.src.splitwise.eums.SplitType;
import java.src.splitwise.factory.SplitFactory;
import java.src.splitwise.models.Expense;
import java.src.splitwise.models.Group;
import java.src.splitwise.models.Split;
import java.src.splitwise.models.User;
import java.src.splitwise.str.SplitStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseManager {
    private Map<String, User> users;
    private Map<String, Group> groups;
    private Map<String, Expense> expenses;

    private static SplitwiseManager instance;
    
    private SplitwiseManager() {
        users = new HashMap<>();
        groups = new HashMap<>();
        expenses = new HashMap<>();
    }
    
    public static SplitwiseManager getInstance() {
        if(instance == null) {
            instance = new SplitwiseManager();
        }
        return instance;
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        System.out.println("User created: " + name + " (ID: " + user.getId() + ")");
        return user;
    }
    
    public User getUser(String userId) {
        return users.get(userId);
    }
    
    // Group management
    public Group createGroup(String name) {
        Group group = new Group(name);
        groups.put(group.getId(), group);
        System.out.println("Group created: " + name + " (ID: " + group.getId() + ")");
        return group;
    }
    
    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }
    
    public void addUserToGroup(String userId, String groupId) {
        User user = getUser(userId);
        Group group = getGroup(groupId);
        
        if (user != null && group != null) {
            group.addMember(user);
        }
    }
    
    // Try to remove user from group - just delegates to group
    public boolean removeUserFromGroup(String userId, String groupId) {
        Group group = getGroup(groupId);
        
        if (group == null) {
            System.out.println("Group not found!");
            return false;
        }
        
        User user = getUser(userId);
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        boolean userRemoved = group.removeMember(userId);
        
        if(userRemoved) {
            System.out.println(user.getName() + " successfully left " + group.getName());
        }
        return userRemoved;
    }
    
    // Expense management - delegate to group
    public void addExpenseToGroup(String groupId, String description, double amount, 
                          String paidByUserId, List<String> involvedUsers, 
                          SplitType splitType, List<Double> splitValues) {
        
        Group group = getGroup(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }
        
        group.addExpense(description, amount, paidByUserId, involvedUsers, splitType, splitValues);
    }
    
    public void addExpenseToGroup(String groupId, String description, double amount, 
                          String paidByUserId, List<String> involvedUsers, 
                          SplitType splitType) {
        addExpenseToGroup(groupId, description, amount, paidByUserId, involvedUsers, splitType, new ArrayList<>());
    }
    
    // Settlement - delegate to group
    public void settlePaymentInGroup(String groupId, String fromUserId, 
                              String toUserId, double amount) {
        
        Group group = getGroup(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }
        
        group.settlePayment(fromUserId, toUserId, amount);
    }
    
    // Settlement
    public void settleIndividualPayment(String fromUserId, String toUserId, double amount) {
        User fromUser = getUser(fromUserId);
        User toUser = getUser(toUserId);
        
        if (fromUser != null && toUser != null) {
            fromUser.updateBalance(toUserId, amount);
            toUser.updateBalance(fromUserId, -amount);
            
            System.out.println(fromUser.getName() + " settled Rs" + amount + " with " + toUser.getName());
        }
    }
    
    public void addIndividualExpense(String description, double amount, String paidByUserId,
                             String toUserId, SplitType splitType,
                            List<Double> splitValues) {

        SplitStrategy strategy = SplitFactory.getSplitStrategy(splitType);
        List<Split> splits = strategy.calcSplit(amount, Arrays.asList(paidByUserId, toUserId), splitValues);

        Expense expense = new Expense(description, splits, paidByUserId, amount);
        expenses.put(expense.getId(), expense);
        
        User paidByUser = getUser(paidByUserId);
        User toUser = getUser(toUserId);

        paidByUser.updateBalance(toUserId, amount);
        toUser.updateBalance(paidByUserId, -amount);
        
        System.out.println("Individual expense added: " + description + " (Rs " + amount 
                + ") paid by " + paidByUser.getName() +" for " + toUser.getName());
    }
    
    public void addIndividualExpense(String description, double amount, String paidByUserId,
                             String toUserId, SplitType splitType) {
        addIndividualExpense(description, amount, paidByUserId, toUserId, splitType, new ArrayList<>());
    }
    
   
    
    public void showGroupBalances(String groupId) {
        Group group = getGroup(groupId);
        if (group == null) return;
        
        group.showGroupBalances();
    }
    
    public void simplifyGroupDebts(String groupId) {
        Group group = getGroup(groupId);
        if (group == null) return;
                
        // Use group's balance data for debt simplification
        group.simplifyGroupDebts();
    }
}
