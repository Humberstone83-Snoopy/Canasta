/*     */ package sample;
/*     */ 
/*     */ import gameLogic.TwoPlayerLogic;
/*     */ import java.io.Serializable;
/*     */ import server.CentralServer;
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
/*     */ public class ActiveGame
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private boolean roundOver;
/*     */   private int gameID;
/*     */   private int team1Total;
/*     */   private int team2Total;
/*     */   private String thisUserOut;
/*     */   private boolean p1Connected;
/*     */   private boolean p2Connected;
/*     */   private boolean p3Connected;
/*     */   private boolean p4Connected;
/*     */   private boolean p1Continue;
/*     */   private boolean p2Continue;
/*     */   private boolean p3Continue;
/*     */   private boolean p4Continue;
/*     */   private TwoPlayerLogic game0;
/*     */   
/*     */   public ActiveGame(int gameID) {
/*  37 */     this.gameID = gameID;
/*  38 */     this.game0 = new TwoPlayerLogic();
/*  39 */     this.game0.deal();
/*  40 */     this.roundOver = false;
/*  41 */     this.team1Total = 0;
/*  42 */     this.team2Total = 0;
/*  43 */     this.p1Connected = true;
/*  44 */     this.p2Connected = true;
/*  45 */     this.thisUserOut = "";
/*     */ 
/*     */     
/*  48 */     if (CentralServer.getGameLobby()[gameID].getGameType() == 1) {
/*  49 */       this.p3Connected = true;
/*  50 */       this.p4Connected = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP1Continue() {
/*  60 */     return this.p1Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP1Continue(boolean p1Continue) {
/*  68 */     this.p1Continue = p1Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP2Continue() {
/*  76 */     return this.p2Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP2Continue(boolean p2Continue) {
/*  84 */     this.p2Continue = p2Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP3Continue() {
/*  92 */     return this.p3Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP3Continue(boolean p3Continue) {
/* 100 */     this.p3Continue = p3Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP4Continue() {
/* 108 */     return this.p4Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP4Continue(boolean p4Continue) {
/* 116 */     this.p4Continue = p4Continue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP1Connected() {
/* 125 */     return this.p1Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP1Connected(boolean p1Connected) {
/* 132 */     this.p1Connected = p1Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP2Connected() {
/* 139 */     return this.p2Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP2Connected(boolean p2Connected) {
/* 146 */     this.p2Connected = p2Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP3Connected() {
/* 153 */     return this.p3Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP3Connected(boolean p3Connected) {
/* 160 */     this.p3Connected = p3Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP4Connected() {
/* 167 */     return this.p4Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP4Connected(boolean p4Connected) {
/* 174 */     this.p4Connected = p4Connected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeam1Total() {
/* 181 */     return this.team1Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeam1Total(int team1Total) {
/* 190 */     this.team1Total = team1Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeam2Total() {
/* 199 */     return this.team2Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeam2Total(int team2Total) {
/* 208 */     this.team2Total = team2Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwoPlayerLogic getGame0() {
/* 217 */     return this.game0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGame0(TwoPlayerLogic game0) {
/* 224 */     this.game0 = game0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGameID() {
/* 231 */     return this.gameID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameID(int gameID) {
/* 238 */     this.gameID = gameID;
/*     */   }
/*     */   
/*     */   public boolean isRoundOver() {
/* 242 */     return this.roundOver;
/*     */   }
/*     */   
/*     */   public void setRoundOver(boolean roundOver) {
/* 246 */     this.roundOver = roundOver;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getThisUserOut() {
/* 252 */     return this.thisUserOut;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThisUserOut(String thisUserOut) {
/* 258 */     this.thisUserOut = thisUserOut;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\sample\ActiveGame.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */