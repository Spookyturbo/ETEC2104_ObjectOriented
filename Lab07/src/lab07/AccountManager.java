/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 *
 * @author techn
 */
public class AccountManager {
    
    private final ArrayList<Account> m_accounts = new ArrayList<>();
    private int m_idCount = 0;
    
    //Returns true if user is found
    boolean verifyUser(String username, String password) {
        return m_accounts.stream().anyMatch((acc) -> acc.getUsername().equals(username) 
                                                     && acc.getPassword().equals(password));
    }
    
    //Returns true if could add user, false if not (Duplicate or invalid username)
    //Considered invalid if just doesn't have an @ sign
    boolean addUser(String emailAddress, String password) {
        //Check for valid email
        if(!emailAddress.contains("@"))
            return false;
        
        //Check for duplicate username
        if(m_accounts.stream().anyMatch((acc) -> acc.getUsername().equals(emailAddress)))
            return false;
        
        Account acc = new Account(emailAddress, emailAddress, password, m_idCount++);
        m_accounts.add(acc);
        
        return true;
    }
    
    //Returns the user id number, or -1 if none
    int getUID(String username) {
        Account account = getAccount((acc)->acc.getUsername().equals(username));
        
        if(account == null)
            return -1;
        
        return account.getId();
    }
    
    //Returns email address for user or null if error
    String getEmail(int userID) {
        Account account = getAccount((acc)-> acc.getId() == userID);
        
        if(account == null)
            return null;
        
        return account.getEmail();
    }
    
    //Returns true if user is an admin, false if user is not or does not exist
    boolean isAdmin(int userID) {
        Account account = getAccount((acc)->acc.getId() == userID);
        
        if(account == null)
            return false;
        
        return account.isAdmin();
    }
    
    //Set users admin flag, returns true if OK, false if error
    //Can only error if userID does not exist
    boolean setAdmin(int userID, boolean isAdmin) {
        Account account = getAccount((acc)-> acc.getId() == userID);
        
        if(account == null)
            return false;
        
        account.setAdmin(isAdmin);
        
        return true;
    }
    
    //Returns the users avatar or null if error or no avatar
    byte[] getAvatar(int userID) {
        Account account = getAccount((acc)->acc.getId() == userID);
        
        if(account == null)
            return null;
        
        return account.getAvatar();
    }
    
    //Sets a user's avatar. Returns true for OK, false if not
    //Returns false for no user, or for img being null, or of length 0
    boolean setAvatar(int userID, byte[] img) {
        if(img == null)
            return false;
        
        if(img.length == 0)
            return false;
        
        Account account = getAccount((acc)->acc.getId() == userID);
        
        if(account == null)
            return false;
        
        account.setAvatar(img);
        
        return true;
    }
    
    //Gets an account using a predicate check from the list of accounts
    Account getAccount(Predicate<Account> check) {
        return m_accounts.stream().filter(check).findFirst().orElse(null);
    }
    
}
