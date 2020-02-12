/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab07;

/**
 *
 * @author techn
 */
public class Account {
    
    private String m_username;
    private String m_email;
    private String m_password;
    private int m_id;
    private boolean m_isAdmin;
    private byte[] m_avatar;
    
    
    
    public Account(String username, String email, String password, int userID) {
        m_username = username;
        m_email = email;
        m_password = password;
        m_id = userID;
        m_isAdmin = false;
        m_avatar = null;
    }
    
    public String getUsername() {
        return m_username;
    }
    
    public String getEmail() {
        return m_email;
    }
    
    public String getPassword() {
        return m_password;
    }
    
    public int getId() {
        return m_id;
    }
    
    public boolean isAdmin() {
        return m_isAdmin;
    }
    
    public void setAdmin(boolean isAdmin) {
        m_isAdmin = isAdmin;
    }
    
    public byte[] getAvatar() {
        return m_avatar;
    }
    
    public void setAvatar(byte[] avatar) {
        m_avatar = avatar;
    }
}
