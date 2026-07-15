package java.src.splitwise;

import java.src.splitwise.eums.SplitType;
import java.src.splitwise.models.Group;
import java.src.splitwise.models.User;
import java.util.*;

public class SplitwiseDemo {
    public static void main(String[] args) {
        
        SplitwiseManager manager = SplitwiseManager.getInstance();
        
        System.out.println("\n=========== Creating Users ====================");
        User user1 = manager.createUser("Aditya", "aditya@gmail.com");
        User user2 = manager.createUser("Rohit", "rohit@gmail.com");
        User user3 = manager.createUser("Manish", "manish@gmail.com");
        User user4 = manager.createUser("Saurav", "saurav@gmail.com");
        
        System.out.println("\n=========== Creating Group and Adding Members ====================");
        Group hostelGroup = manager.createGroup("Hostel Expenses");
        manager.addUserToGroup(user1.getId(), hostelGroup.getId());
        manager.addUserToGroup(user2.getId(), hostelGroup.getId());
        manager.addUserToGroup(user3.getId(), hostelGroup.getId());
        manager.addUserToGroup(user4.getId(), hostelGroup.getId());

        System.out.println("\n=========== Adding Expenses in group ====================");    
        List<String> groupMembers = Arrays.asList(user1.getId(), user2.getId(), user3.getId(), user4.getId());
        manager.addExpenseToGroup(hostelGroup.getId(), "Lunch", 800.0, user1.getId(), groupMembers, SplitType.EQUAL);
        
        List<String> dinnerMembers = Arrays.asList(user1.getId(), user3.getId(), user4.getId());
        List<Double> dinnerAmounts = Arrays.asList(200.0, 300.0, 200.0);
        manager.addExpenseToGroup(hostelGroup.getId(), "Dinner", 700.0, user3.getId(), dinnerMembers, 
                                 SplitType.EXACT, dinnerAmounts);

        System.out.println("\n=========== printing Group-Specific Balances ===================="); 
        manager.showGroupBalances(hostelGroup.getId());

        System.out.println("\n=========== Debt Simplification ===================="); 
        manager.simplifyGroupDebts(hostelGroup.getId());

        System.out.println("\n=========== printing Group-Specific Balances ===================="); 
        manager.showGroupBalances(hostelGroup.getId());

        System.out.println("\n=========== Adding Individual Expense ===================="); 
        manager.addIndividualExpense("Coffee", 40.0, user2.getId(), user4.getId(), SplitType.EQUAL);
        
        // System.out.println("\n=========== printing User Balances ===================="); 
        // manager.showUserBalance(user1.getId());
        // manager.showUserBalance(user2.getId());
        // manager.showUserBalance(user3.getId());
        // manager.showUserBalance(user4.getId());

        System.out.println("\n==========Attempting to remove Rohit from group==========");
        manager.removeUserFromGroup(user2.getId(), hostelGroup.getId());

        System.out.println("\n======== Making Settlement to Clear Rohit's Debt =========="); 
        manager.settlePaymentInGroup(hostelGroup.getId(), user2.getId(), user3.getId(), 200.0);
        
        System.out.println("\n======== Attempting to Remove Rohit Again ==========");
        manager.removeUserFromGroup(user2.getId(), hostelGroup.getId());
        
        System.out.println("\n=========== Updated Group Balances ===================="); 
        manager.showGroupBalances(hostelGroup.getId());
    }
}