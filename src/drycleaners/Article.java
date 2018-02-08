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
public class Article {
    private ArticleState state;
    private String howMany;
    private double price;
    
    public Article(String type, String howMany) {
        this.state = null;
        this.howMany = howMany;
        switch (type) {
            case "suit":
                this.price = 12.50;
                break;
            case "dress":
                this.price = 9.50;     
                break;
            case "tie":
                this.price = 4.00;
                break;
        }
    }
    
    public void setState(ArticleState state){
      this.state = state;		
   }

   public ArticleState getState(){
      return state;
   }

    public String getHowMany() {
        return howMany;
    }
   
   public double getCost(){
       double foo = Integer.parseInt(this.howMany);
       return price*foo;
   }
           
    
}
