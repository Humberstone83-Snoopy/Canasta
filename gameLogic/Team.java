/*     */ package gameLogic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Team
/*     */   implements TeamInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ArrayList<Player> players;
/*     */   private Stock stock;
/*     */   private boolean down;
/*     */   private boolean deckWild;
/*     */   private boolean gotCanasta;
/*     */   private boolean firstOut;
/*     */   private int downPoints;
/*     */   private int teamScore;
/*     */   private int teamNumber;
/*     */   
/*     */   public Team(int teamNumber, ArrayList<Player> players) {
/*  39 */     this.teamNumber = teamNumber;
/*  40 */     this.players = players;
/*  41 */     this.stock = new Stock();
/*  42 */     this.teamScore = 0;
/*  43 */     this.downPoints = 50;
/*  44 */     this.down = false;
/*  45 */     this.deckWild = true;
/*  46 */     this.gotCanasta = false;
/*  47 */     this.firstOut = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Player> getPlayers() {
/*  56 */     return this.players;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayers(ArrayList<Player> players) {
/*  64 */     this.players = players;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDown(boolean down) {
/*  72 */     this.down = down;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDown() {
/*  80 */     return this.down;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDownPoints() {
/*  88 */     return this.downPoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDownPoints(int downPoints) {
/*  96 */     this.downPoints = downPoints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDeckWild() {
/* 104 */     return this.deckWild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeckWild(boolean deckWild) {
/* 112 */     this.deckWild = deckWild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stock getStock() {
/* 120 */     return this.stock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStock(Stock stock) {
/* 128 */     this.stock = stock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGotCanasta() {
/* 136 */     return this.gotCanasta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGotCanasta(Boolean gotCanasta) {
/* 144 */     this.gotCanasta = gotCanasta.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFirstOut() {
/* 152 */     return this.firstOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstOut(boolean firstOut) {
/* 160 */     this.firstOut = firstOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeamScore() {
/* 167 */     return this.teamScore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeamScore(int teamScore) {
/* 174 */     this.teamScore = teamScore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeamNumber() {
/* 181 */     return this.teamNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeamNumber(int teamNumber) {
/* 188 */     this.teamNumber = teamNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGotCanasta(boolean gotCanasta) {
/* 195 */     this.gotCanasta = gotCanasta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 203 */     return "Team [players=" + this.players + ", stock=" + this.stock + ", down=" + this.down + ", deckWild=" + this.deckWild + 
/* 204 */       ", gotCanasta=" + this.gotCanasta + ", firstOut=" + this.firstOut + ", downPoints=" + this.downPoints + 
/* 205 */       ", teamScore=" + this.teamScore + ", teamNumber=" + this.teamNumber + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Team.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */