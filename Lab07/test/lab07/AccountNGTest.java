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
public class AccountNGTest {

    /**
     * Test of getUsername method, of class Account.
     */
    @Test
    public void testGetUsername() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertEquals(account.getUsername(), "Username");
    }

    /**
     * Test of getEmail method, of class Account.
     */
    @Test
    public void testGetEmail() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertEquals(account.getEmail(), "Email");
    }

    /**
     * Test of getPassword method, of class Account.
     */
    @Test
    public void testGetPassword() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertEquals(account.getPassword(), "Password");
    }

    /**
     * Test of getId method, of class Account.
     */
    @Test
    public void testGetId() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertEquals(account.getId(), 0);
    }

     /**
     * Test of setAdmin method, of class Account.
     */
    @Test
    public void testSetAdmin() {
        Account account = new Account("Username", "Email", "Password", 0);
        account.setAdmin(true);
        assertTrue(account.isAdmin());
        account.setAdmin(false);
        assertFalse(account.isAdmin());
    }
    
    /**
     * Test of isAdmin method, of class Account.
     */
    @Test
    public void testIsAdmin() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertFalse(account.isAdmin());
        account.setAdmin(true);
        assertTrue(account.isAdmin());
    }

    /**
     * Test of getAvatar method, of class Account.
     */
    @Test
    public void testGetAvatar() {
        Account account = new Account("Username", "Email", "Password", 0);
        assertNull(account.getAvatar());
        account.setAvatar(new byte[] {0, 1, 2, 3});
        assertNotNull(account.getAvatar());
    }

    /**
     * Test of setAvatar method, of class Account.
     */
    @Test
    public void testSetAvatar() {
        Account account = new Account("Username", "Email", "Password", 0);
        account.setAvatar(null);
        assertNull(account.getAvatar());
        account.setAvatar(new byte[] {});
        assertNotNull(account.getAvatar());
        account.setAvatar(new byte[] {0, 1, 2, 3});
        assertNotNull(account.getAvatar());
    }
    
}
