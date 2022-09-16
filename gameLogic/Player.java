/*    */ package gameLogic;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Player
/*    */   implements PlayerInterface, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Hand hand;
/*    */   private int team;
/*    */   private int playerNo;
/*    */   
/*    */   public Player(int team, int playerNo) {
/* 33 */     this.hand = new Hand();
/* 34 */     this.team = team;
/* 35 */     this.playerNo = playerNo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Hand getHand() {
/* 44 */     return this.hand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHand(Hand hand) {
/* 52 */     this.hand = hand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTeam() {
/* 60 */     return this.team;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getplayerNo() {
/* 68 */     return this.playerNo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     return "Player [playerNo=" + this.playerNo + ", team=" + this.team + ", hand=" + this.hand + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Player.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */