/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author Ambro
 */
public class FileHandling {
    
    // writes to file according to either StaffMember or Customer
    // writes down all features, only effects / modifies the file
    public static void write(StaffMember a, Customer b) throws FileNotFoundException, IOException {
        BufferedWriter out;
        //if (!find(a.getUsername()) || !find(b.getName()) ) {
            try {
                if (! (a == null)) {
                    //replace helloworld.txt with the name of the file
                    out = new BufferedWriter(new FileWriter("accounts.txt", true));
                    //Write out the specified string to the file
                    out.write(a.getUsername() + " ");
                    out.write(a.getPass() + " ");
                    out.write(a.getRole() + " ");

                    out.newLine();
                    out.close();//flushes and closes the stream
                    System.out.println(a.getUsername() + " has been added");
                }
                
                else if (! (b == null)) {
                    //replace helloworld.txt with the name of the file
                    out = new BufferedWriter(new FileWriter("accounts.txt", true));
                    //Write out the specified string to the file
                    out.write(b.getName() + " ");
                    out.write(b.getId() + " ");
                    
                    if (null != b.getSuit())
                        out.write(b.getSuit().getHowMany() + " ");
                    if (null != b.getDress())
                        out.write(b.getDress().getHowMany() + " ");
                    if (null != b.getDress())
                        out.write(b.getTie().getHowMany() + " ");
                    
                    out.write(b.getRole() + " ");

                    out.newLine();
                    out.close();//flushes and closes the stream
                    System.out.println(b.getName() + " has been added");
                }

            } catch (IOException e) {
                System.out.println("There was a problem writing to file:" + e);
            }
        
        //else  System.out.println("Username already in use.\n");
    }
    
    // reads the file accoutns and deletes item given a name or id
    public static void delete(String name) throws FileNotFoundException, IOException {
        File inputFile = new File("accounts.txt");   // Your file  
        File tempFile = new File("Temp.txt");// temp file

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {

            if (currentLine.contains(name)) {
                continue;
            }
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();
       
        reader=new BufferedReader(new FileReader(tempFile));
        writer = new BufferedWriter(new FileWriter(inputFile));
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine);
            writer.newLine();
        }
        writer.close();
        reader.close();
    }
     
    // Looks through the accounts file and returns boolean if it finds the users information. could be name or anything of type string
    public static boolean find(String name) {
        BufferedReader in;
        String read = null;
        try {     //open a bufferedReader to file helloworld.txt
            in = new BufferedReader(new FileReader("accounts.txt"));
            //read a line from accounts.txt and save into a string
            while ((read = in.readLine()) != null) {
                //Split the line and save the variables
                String[] arr = read.split(" ");
                //Extracting all the user information

                if (arr[0].equals(name) || arr[1].equals(name)) {
                    return true;
                }
            }
            in.close();
            return false;
        } catch (IOException e) {
            System.out.println("There was a problem while reading:" + e);
            return false;
        }
    }
    
    // Looks through the accounts file and creates a Customer object from a line according to the customer 
    // phone number (ID). It returns object of type Customer
    public static Customer getCustomer(String phoneNumber) {
    Customer c1 = null;
    BufferedReader in;
    String read = null;
    try {     //open a bufferedReader to file helloworld.txt
        in = new BufferedReader(new FileReader("accounts.txt"));
        //read a line from accounts.txt and save into a string
        while ((read = in.readLine()) != null) {
            //Split the line and save the variables
            String[] arr = read.split(" ");
            //Extracting all the user information
            if (arr[1].equals(phoneNumber)) {
                Article suit = new Article("suit",arr[2]);
                Article dress = new Article("dress",arr[3]);
                Article tie = new Article("tie", arr[4]);

                c1 = new Customer(arr[0],arr[1],suit, dress, tie);
            }

        }

        //safely close the BufferedReader after use
        in.close();
    } catch (IOException e) {
        System.out.println("There was a problem:" + e);
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Please place an order before you attempt to complete state:\n"+e);
    }
    
    return c1; 
}
   private static String userInput() {
    String commands;
    Scanner user_input = new Scanner(System.in);
    commands = user_input.next();
//      System.out.print("This is what Test1 is :" + commands);
    return commands;
} 
    
   // this function reads through the file and creates an object of Customer
   // according to given Name and phone Number
   // 
   public static Customer read(String name, String phoneNumber) {
    Customer c1 = null;
    String numSuit;
    String numDress;
    String numTie;

    BufferedReader in;
    String read = null;
    try {     //open a bufferedReader to file helloworld.txt
        in = new BufferedReader(new FileReader("accounts.txt"));
        //read a line from accounts.txt and save into a string
        while ((read = in.readLine()) != null) {
            //Split the line and save the variables
            String[] arr = read.split(" ");
            //Extracting all the user information
            if (arr[1].equals(phoneNumber)) {
                delete(name);
                System.out.println("How many suits are you bringing today?");
                numSuit = userInput();
                System.out.println("How many dresses are you bringing today?");
                numDress = userInput();
                System.out.println("How many ties are you bringing today?");
                numTie = userInput();
                
                Article suit = new Article("suit", numSuit);
                Article dress = new Article("dress", numDress);
                Article tie = new Article("tie", numTie);
                c1 = new Customer(name, phoneNumber, suit, dress, tie);
            }

        }

        //safely close the BufferedReader after use
        in.close();
    } catch (IOException e) {
        System.out.println("There was a problem:" + e);
    }
    return c1;
  }
}

