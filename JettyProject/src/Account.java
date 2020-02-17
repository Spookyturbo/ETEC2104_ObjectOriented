/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author techn
 */
public class Account {
    
    public String name;
    public String userName;
    public String password;
    
    public Account(String user, String pass, String realName) {
        name = realName;
        userName = user;
        password = pass;
    }
    
    public boolean equals(Account acc) {
        return acc.userName.equals(userName) && acc.password.equals(password);
    }
    
    public boolean equals(String user, String pass) {
        return userName.equals(user) && password.equals(pass);
    }
    
}
