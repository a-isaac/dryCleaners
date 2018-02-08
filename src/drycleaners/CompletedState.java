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
public class CompletedState implements ArticleState {

   public void doAction(Article article) {
      //System.out.println("Article is in completed state");
      article.setState(this);	
   }

   @Override
   public String toString(){
      return "Completed State";
   }
}
