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
public class Customer implements java.io.Serializable {
    private String name;
    private String id;
    private String role;
    private Article suit;
    private Article dress;
    private Article tie;
    private Receipt receipt;
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.id = phoneNumber;
        this.role = "Customer";
    }
    public Customer(String name, String phoneNumber, Article suit, Article dress, Article tie) {
        this.name = name;
        this.id = phoneNumber;
        this.suit = suit;
        this.dress = dress;
        this.tie = tie;
        this.role = "Customer";
    }

    public String getName() {
        return name;
    }

    public Article getSuit() {
        return suit;
    }

    public Article getDress() {
        return dress;
    }

    public Article getTie() {
        return tie;
    }
    public double getCost(Article article) {
        return article.getCost();
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void String(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
