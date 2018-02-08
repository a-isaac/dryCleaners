/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaners;

/**
 *
 * @author Ambro
 */
public class Employee extends StaffMember implements java.io.Serializable{
    public Employee(String name, String passw) {
    //EFFECTS: returns new cusomer with only chequing account
        super.setPass(passw);
        setRole();
        super.setUsername(name);
    }

    @Override
    public void setRole() {
        super.role = "Employee";
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
    
    
    
}
