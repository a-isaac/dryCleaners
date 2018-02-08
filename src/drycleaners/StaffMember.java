/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaners;

import java.util.ArrayList;

/**
 *
 * @author Ambro
 */
public abstract class StaffMember {
    private String pass;
    private String username;
    protected String role;
    
    public void setPass(String pass) {
    //MODIFIES: creates the account password
    //EFFECTS: returns the new account password
        this.pass = pass;
    }

    public String getPass() {
    //EFFECTS: returns the account password    
        return pass;
    }

    public String getRole() {
    //EFFECTS: returns the type of account    
        return role;
    }

    public String getUsername() {
   //EFFECTS: returns the account username   
        return username;
    }

    public void setUsername(String username) {
    //MODIFIES: creates the account username
    //EFFECTS: returns the new account username
        this.username = username;
    }
    
    

    public abstract void setRole();

    
}