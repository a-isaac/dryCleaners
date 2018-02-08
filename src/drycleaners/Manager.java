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
public class Manager extends StaffMember implements java.io.Serializable{
    private static ArrayList employeeList = new ArrayList<Employee>();
    public Manager() {
    //EFFECTS: returns new manager account
        super.setPass("admin");
        super.setUsername("admin");
        setRole();
    }

    @Override
    public void setRole() {
    super.role="Manager";
    }
    
    
}