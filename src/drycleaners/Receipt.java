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
public class Receipt {
    Customer customer;

    public Receipt(Customer customer) {
        this.customer = customer;
    }
    private double getTotalPrice() {
        return customer.getCost(customer.getSuit()) +customer.getCost(customer.getDress()) + customer.getCost(customer.getTie());
    }
    public void getReceipt() {
        System.out.println("\n      BELOW IS YOUR RECEIPT");
        System.out.println("---------------------------------");
        System.out.format("%-10s \n","         Dry Cleaners");       
        System.out.format("%s","We Put The Clean in YOUR Routine\n");
        System.out.format("%s %15s %8s \n", "Article", "# of Articles", "Price");
        
        if (!customer.getSuit().getHowMany().equals("0"))
            System.out.format("%s %18s %8s \n","Suit", customer.getSuit().getHowMany(), customer.getCost(customer.getSuit()));
        if (!customer.getDress().getHowMany().equals("0"))
            System.out.format("%s %17s %8s \n","Dress", customer.getDress().getHowMany(), customer.getCost(customer.getDress()));
        if (!customer.getTie().getHowMany().equals("0"))
            System.out.format("%s %19s %8s \n","Tie", customer.getTie().getHowMany(), customer.getCost(customer.getTie()));
        System.out.format("%s.................%s\n", "Total price", getTotalPrice());
        System.out.format(" Thank you for shopping with us! \n        Have a great day!\n\n");
    }
}
