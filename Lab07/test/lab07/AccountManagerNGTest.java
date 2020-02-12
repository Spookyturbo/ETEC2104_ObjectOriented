/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author techn
 */
public class AccountManagerNGTest {
    
    @Test
    public void testValidUserAdded() {
        AccountManager manager = new AccountManager();
        boolean result = manager.addUser("test", "aheiufhewa");
        assertFalse(result);
        result = manager.addUser("test@thing.com", "12345");
        assertTrue(result);
    }
    
    @Test
    public void testDuplicateAddUser() {
        AccountManager manager = new AccountManager();
        boolean result = manager.addUser("test2@thing.com", "23456");
        assertTrue(result);
        result = manager.addUser("test2@thing.com", "23456");
        assertFalse(result);
    }
    
    @Test
    public void testVerifyUser() {
        AccountManager manager = new AccountManager();
        //Check for non existing
        assertFalse(manager.verifyUser("test@thing.com", "12345"));
        manager.addUser("test@thing.com", "12345");
        //check for existing
        assertTrue(manager.verifyUser("test@thing.com", "12345"));
        //check for messed up password only
        assertFalse(manager.verifyUser("test@thing.com", "1234567"));
        //check for messed up username only
        assertFalse(manager.verifyUser("t@thing.com", "12345"));
        //Check both wrong
        assertFalse(manager.verifyUser("awdaw", "awdaw"));
    }
    
    @Test
    public void testGetUserId() {
        AccountManager manager = new AccountManager();
        //Make sure if doesn't exist, return -1
        assertEquals(manager.getUID("test@test.com"), -1);
        manager.addUser("test@test.com", "12345");
        
        assertEquals(manager.getUID("test@test.com"), 0);
        manager.addUser("test2@test.com", "123456");
        //Make sure both are still correct
        assertEquals(manager.getUID("test@test.com"), 0);
        assertEquals(manager.getUID("test2@test.com"), 1);
        
    }
    
    @Test
    public void testGetEmail() {
        AccountManager manager = new AccountManager();
        assertNull(manager.getEmail(0));
        manager.addUser("Test@test.com", "123456");
        assertNotNull(manager.getEmail(manager.getUID("Test@test.com")));
        
    }
    
    //Tests to see if setAdmin returns proper results based on validity
    @Test
    public void testSetAdminValid() {
        AccountManager manager = new AccountManager();
        assertFalse(manager.setAdmin(0, true));
        assertFalse(manager.setAdmin(0, false));
        
        manager.addUser("test@test.com", "12345");
        
        //If true, setting to true again should not throw an error
        assertTrue(manager.setAdmin(manager.getUID("test@test.com"), true));
        assertTrue(manager.setAdmin(manager.getUID("test@test.com"), true));
        assertTrue(manager.setAdmin(manager.getUID("test@test.com"), false));
        assertTrue(manager.setAdmin(manager.getUID("test@test.com"), false));
        
        
    }
    
    //Test to see if isAdmin returns proper results
    //Requires using setAdmin
    @Test 
    public void testIsAdmin() {
        AccountManager manager = new AccountManager();
        
        assertFalse(manager.isAdmin(0));
        manager.addUser("test@test.com", "12345");
        assertFalse(manager.isAdmin(0));
        manager.setAdmin(0, true);
        assertTrue(manager.isAdmin(0));
        manager.setAdmin(0, false);
        assertFalse(manager.isAdmin(0));
    }
    
    @Test
    public void overwriteAvatar() {
        AccountManager manager = new AccountManager();
        manager.addUser("test@test.com", "12345");
        //Try setting, then overwriting with same thing and dif
        assertTrue(manager.setAvatar(0, new byte[] {0, 1, 2, 3}));
        assertTrue(manager.setAvatar(0, new byte[] {0, 1, 2, 3}));
        assertTrue(manager.setAvatar(0, new byte[] {0, 1, 2, 3, 4}));
    }
    
    @Test
    public void setInvalidAvatar() {
        AccountManager manager = new AccountManager();
        assertFalse(manager.setAvatar(0, null));
        assertFalse(manager.setAvatar(0, new byte[]{}));
    }
    
    @Test
    public void setAvatar() {
        AccountManager manager = new AccountManager();
        
        assertFalse(manager.setAvatar(0, new byte[] {0, 1, 2, 3}));
        manager.addUser("test@test.com", "12345");
        assertTrue(manager.setAvatar(0, new byte[] {0, 1, 2, 3}));
        
    }
    
    @Test
    public void getAvatar() {
        AccountManager manager = new AccountManager();
        assertNull(manager.getAvatar(0));
        manager.addUser("test@test.com", "12345");
        assertNull(manager.getAvatar(0));
        manager.setAvatar(0, new byte[] {0, 1, 2, 3});
        assertNotNull(manager.getAvatar(0));
    }
    
    @Test
    public void getAccount() {
        AccountManager manager = new AccountManager();
        
        //Get account based off id and username when it doesn't exist
        Account account = manager.getAccount((acc)->acc.getUsername().equals("test@test.com"));
        assertNull(account);
        account = manager.getAccount((acc)->acc.getId() == 0);
        assertNull(account);
        
        manager.addUser("test@test.com", "12345");
        //Do again when does exist
        account = manager.getAccount((acc)->acc.getUsername().equals("test@test.com"));
        assertNotNull(account);
        account = manager.getAccount((acc)->acc.getId() == 0);
        assertNotNull(account);
    }
    
}
