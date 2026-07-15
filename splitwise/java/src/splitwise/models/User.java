package java.src.splitwise.models;

import java.src.splitwise.interfaces.CustomObserver;
import java.util.*;

public class User implements CustomObserver{
    public static int nextID = 0;
    private String id;
    private String name;
    private String email;
    private Map<String, Double> balances;

    public User(String name, String email) {
        this.id = "user_"+(++nextID);
        this.name = name;
        this.email = email;
        this.balances = new HashMap<>();
    }
    public static int getNextID() {
        return nextID;
    }
    public static void setNextID(int nextID) {
        User.nextID = nextID;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Map<String, Double> getBalances() {
        return balances;
    }
    public void setBalances(Map<String, Double> balances) {
        this.balances = balances;
    } 

    public void updateByNotification(String message){
        System.out.println("NOTIFICATION to : "+ this.name +" :: "+ message );
    }
    
    public void updateBalance(String userId, double amt){
        balances.put(userId, balances.getOrDefault(userId, 0.0)+amt);

        if(Math.abs(balances.get(userId))<0.01){
            balances.remove(userId);
        }
    }


}
