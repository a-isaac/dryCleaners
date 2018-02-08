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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

public final class UserInterface {

    StaffMember staffMember;
    private static FileHandling fileHandling = new FileHandling();
    private static Receipt receipt;
    private static int commandInt;
    private static String commands;
    
    public UserInterface() throws IOException {
        this.staffMember = new Manager();//Initializing the bank by creating Manager account
        File f = new File("accounts.txt");
        if (!f.exists()) {
            fileHandling.write(staffMember,null);
        }
    }  

    
    private static String userInput() {
        Scanner user_input = new Scanner(System.in);
        commands = user_input.next();
//      System.out.print("This is what Test1 is :" + commands);
        return commands;
    }

    private static int userInputInt() {
        Scanner user_input = new Scanner(System.in);
        commandInt = user_input.nextInt();
    //    System.out.print("This is what Test1 is :" + commandInt);
        return commandInt;
    }
    
    public boolean authenticate(String name, String pass, String type) {
        BufferedReader in;
        String read = null;
        try {     //open a bufferedReader to file accounts.txt
            in = new BufferedReader(new FileReader("accounts.txt"));
            //read a line from accounts.txt and save into a string
            while ((read = in.readLine()) != null) {
                //Split the line and save the variables
                String[] arr = read.split(" ");
                //Extracting all the user information
                if (arr[0].equals(name)) {
                    if (arr[1].equals(pass) && arr[2].equals(type)) {
                        if (type.equals("Employee")) {
                            System.out.println("To add a customer, type 'add'\n"
                                    + "To place a customer order, type 'order'\n"
                                    + "To complete an order, type complete // DEMONSTRATION PURPOSES //");
                        } else {
                            System.out.println("To manage employees input 'manageE'\n"
                                    + "To manage customers input 'manageC'");
                        }
                        return true;
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("There was a problem while reading:" + e);
        }

        System.out.println("User not found\n");
        return false;
    }
    
    public static void manageEmployee(String task) throws IOException {
        String inputTemp1;
        String inputTemp2;
        
        if (task.equals("add")) {
            System.out.println("Please enter employee name");
            inputTemp1 = userInput();
            System.out.println("Please enter employee password");
            inputTemp2 = userInput();
            Employee newEmployee = new Employee(inputTemp1, inputTemp2);
            fileHandling.write(newEmployee, null);
        }
        
        if (task.equals("del")){
            System.out.println("Enter Employee Name that will be deleted:");
            inputTemp1 = userInput();
            if(fileHandling.find(inputTemp1)) {
                System.out.println("Employee " + inputTemp1 + " has been fired.");            
                fileHandling.delete(inputTemp1);
            }
            else 
                System.out.println("Employee not found.");
        }
    }
    
    public static void manageCustomer(String task) throws IOException {
        String customerName;
        String customerId;
        Customer customer = null;
        if (task.equals("add")) {
            System.out.println("What is the customer name?");
            customerName = userInput();
            System.out.println("What is the customer phone number? This will be the customer ID.");
            customerId = userInput();
            Customer newCustomer = new Customer(customerName, customerId);
            fileHandling.write(null, newCustomer);
        }
        
        if (task.equals("order")) {
            System.out.println("Customer order ------- Please enter customer phone number.");
            customerId = userInput();
            System.out.println("Customer order ------- Please enter custome name.");
            customerName = userInput();
            System.out.println("");
            if (fileHandling.find(customerId)) {
                customer = fileHandling.read(customerName, customerId);
                fileHandling.write(null,customer);
                if (!(customer.getSuit().getHowMany().equals("0"))) {
                    ProcessingState processingSuits = new ProcessingState();
                    processingSuits.doAction(customer.getSuit());
                    System.out.println("Your suit is now in " + customer.getSuit().getState().toString());
                }
                
                if (!(customer.getDress().getHowMany().equals("0"))) {
                    ProcessingState processingDress = new ProcessingState();
                    processingDress.doAction(customer.getDress());
                    System.out.println("Your dress is now in " + customer.getDress().getState().toString());
                }
                
                if (!(customer.getTie().getHowMany().equals("0"))) {
                    ProcessingState processingTie = new ProcessingState();
                    processingTie.doAction(customer.getTie());
                    System.out.println("Your tie is now in " + customer.getTie().getState().toString());
                }
                receipt = new Receipt(customer);
                receipt.getReceipt();
            }
        }
        
        if (task.equals("complete")) {
            System.out.println("Enter customer ID");
            customerId = userInput();
            if (fileHandling.find(customerId)){
                
                customer = fileHandling.getCustomer(customerId);
                if (!(customer.getSuit().getHowMany().equals("0"))) {
                    CompletedState completedSuit = new CompletedState();
                    completedSuit.doAction(customer.getSuit());
                    System.out.println("Your suit is now in " + customer.getSuit().getState().toString());
                }
                
                if (!(customer.getDress().getHowMany().equals("0"))) {
                    CompletedState completedDress = new CompletedState();
                    completedDress.doAction(customer.getDress());
                    System.out.println("Your dress is now in " + customer.getDress().getState().toString());
                }
                
                if (!(customer.getTie().getHowMany().equals("0"))) {
                    CompletedState completedTie = new CompletedState();
                    completedTie.doAction(customer.getTie());
                    System.out.println("Your tie is now in " + customer.getTie().getState().toString());
                }
                System.out.println("Send email to Customer: Please pick up your order!");
            }
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String name, password;
        String input = null;
        UserInterface dryCleaners = new UserInterface();

        String temp1, temp2 = null;
        System.out.println("Please Enter 'm' for Manager or 'e' for Employee. Type 'end' to quit.");        
        while (true) {
            input = userInput();
            
            if (!input.equals("m") && !input.equals("e") && !input.equals("end"))
                System.out.println("Please input account type Manager 'm' or Employee 'e'. Type 'end' to quit.");
            
            else if (input.equals("m")) {
                System.out.println("Please enter username");
                name = userInput();
                System.out.println("Please enter password");
                password = userInput();
                
                //Manager Component----------------------------------
                if (dryCleaners.authenticate(name,password,"Manager")) {
                    input = userInput();
                    while (!input.equals("logout")) {
                        switch (input) {
                            case "manageE":
                                System.out.println("To add an employee input 'add'\n"
                                        + "To delete an employee input 'del'");
                                temp1 = userInput();
                                manageEmployee(temp1);
                                break;
                            case "manageC":
                                System.out.println("To add a customer input 'add'\n"
                                        + "To place an order input 'order'\n"
                                        + "To complete an order input 'complete' // DEMONSTRATION PURPOSES //");
                                temp1 = userInput();  
                                manageCustomer(temp1);
                                break;
                        }
                        System.out.println("Input next command....");
                        input = userInput();
                    }
                    System.out.println("Admin has logged out as Manager");
                    System.out.println("Please Enter 'm' for Manager or 'e' for Employee. Type 'end' to quit.");  
                }
            } else if (input.equals("e")) {
                
                //Employee Component--------------------------------
                System.out.println("Please enter username");
                name = userInput();
                System.out.println("Please enter password...");
                password = userInput();
                if (dryCleaners.authenticate(name, password, "Employee")){
                    input = userInput();
                    while (!input.equals("logout")) {
                        manageCustomer(input);
                        System.out.println("Input next command....");
                        input = userInput();
                    }
                    System.out.println(name + " has logged out as Employee");
                    System.out.println("Please Enter 'm' for Manager or 'e' for Employee. Type 'end' to quit.");  
                }
            }
            if (input.equals("end")) 
                break;
                         
        }
        
        
    }
}