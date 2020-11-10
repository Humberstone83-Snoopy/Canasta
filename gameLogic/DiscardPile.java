/*    */ package gameLogic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DiscardPile
/*    */   extends Deck
/*    */   implements DiscardPileInterface, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public DiscardPile() {
/* 26 */     setCards(new ArrayList<>());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Deck clear(Deck deck) {
/* 35 */     for (int i = 0; i < cardsLeft(); i++) {
/* 36 */       deck.addCard(dealCard());
/*    */     }
/* 38 */     return deck;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Card checkTopCard() {
/* 46 */     return getCards().get(cardsLeft() - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\DiscardPile.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */