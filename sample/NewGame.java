/*     */ package sample;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewGame
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int gameID;
/*     */   private int gameType;
/*     */   private int goalScore;
/*     */   private String players;
/*     */   private String host;
/*     */   private int hostID;
/*     */   private String player2;
/*     */   private int p2ID;
/*     */   private String player3;
/*     */   private int p3ID;
/*     */   private String player4;
/*     */   private int p4ID;
/*     */   private int p1Team;
/*     */   private int p2Team;
/*     */   private int p3Team;
/*     */   private int p4Team;
/*     */   private List<String> lobbyTranscript;
/*     */   private boolean p2Ready;
/*     */   private boolean p3Ready;
/*     */   private boolean p4Ready;
/*     */   private boolean gameStarted;
/*     */   private int teamAllocation;
/*     */   
/*     */   public NewGame(int gameID, int gameType, int goalScore, String host, int hostID, int teamAllocation) {
/*  45 */     this.gameID = gameID;
/*  46 */     this.gameType = gameType;
/*  47 */     this.goalScore = goalScore;
/*  48 */     this.host = host;
/*  49 */     this.hostID = hostID;
/*  50 */     this.teamAllocation = teamAllocation;
/*     */     
/*  52 */     if (teamAllocation == 0) {
/*  53 */       this.p1Team = 0;
/*  54 */       this.p2Team = 1;
/*  55 */       if (gameType == 1) {
/*  56 */         this.p3Team = 0;
/*  57 */         this.p4Team = 1;
/*     */       } 
/*  59 */     } else if (teamAllocation == 1) {
/*  60 */       this.p1Team = 0;
/*  61 */       if (gameType == 0) {
/*  62 */         this.p2Team = 1;
/*     */       } else {
/*  64 */         this.p2Team = ThreadLocalRandom.current().nextInt(0, 2);
/*  65 */         if (this.p2Team == 0) {
/*  66 */           this.p3Team = 1;
/*  67 */           this.p4Team = 1;
/*     */         } else {
/*  69 */           this.p3Team = ThreadLocalRandom.current().nextInt(0, 2);
/*  70 */           if (this.p3Team == 0) {
/*  71 */             this.p4Team = 1;
/*     */           } else {
/*  73 */             this.p4Team = 0;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*  78 */       this.p1Team = -1;
/*  79 */       this.p2Team = -1;
/*  80 */       this.p3Team = -1;
/*  81 */       this.p4Team = -1;
/*     */     } 
/*     */     
/*  84 */     if (gameType == 0) {
/*  85 */       this.players = "1/2";
/*     */     } else {
/*  87 */       this.players = "1/4";
/*     */     } 
/*     */     
/*  90 */     this.player2 = null;
/*  91 */     this.p2ID = -1;
/*  92 */     this.player3 = null;
/*  93 */     this.p3ID = -1;
/*  94 */     this.player4 = null;
/*  95 */     this.p4ID = -1;
/*     */     
/*  97 */     this.p2Ready = false;
/*  98 */     this.p3Ready = false;
/*  99 */     this.p4Ready = false;
/* 100 */     this.gameStarted = false;
/*     */     
/* 102 */     this.lobbyTranscript = Collections.synchronizedList(new ArrayList<>());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeamAllocation() {
/* 110 */     return this.teamAllocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeamAllocation(int teamAllocation) {
/* 117 */     this.teamAllocation = teamAllocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP2Ready() {
/* 124 */     return this.p2Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP2Ready(boolean p2Ready) {
/* 131 */     this.p2Ready = p2Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP3Ready() {
/* 138 */     return this.p3Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP3Ready(boolean p3Ready) {
/* 145 */     this.p3Ready = p3Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isP4Ready() {
/* 152 */     return this.p4Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP4Ready(boolean p4Ready) {
/* 159 */     this.p4Ready = p4Ready;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGameStarted() {
/* 166 */     return this.gameStarted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameStarted(boolean gameStarted) {
/* 173 */     this.gameStarted = gameStarted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGameID() {
/* 180 */     return this.gameID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameID(int gameID) {
/* 187 */     this.gameID = gameID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGameType() {
/* 194 */     return this.gameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(int gameType) {
/* 201 */     this.gameType = gameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGoalScore() {
/* 208 */     return this.goalScore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGoalScore(int goalScore) {
/* 215 */     this.goalScore = goalScore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHost() {
/* 222 */     return this.host;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHost(String host) {
/* 229 */     this.host = host;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHostID() {
/* 236 */     return this.hostID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHostID(int hostID) {
/* 243 */     this.hostID = hostID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlayer2() {
/* 250 */     return this.player2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayer2(String player2) {
/* 257 */     this.player2 = player2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP2ID() {
/* 264 */     return this.p2ID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP2ID(int p2id) {
/* 271 */     this.p2ID = p2id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlayer3() {
/* 278 */     return this.player3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayer3(String player3) {
/* 285 */     this.player3 = player3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP3ID() {
/* 292 */     return this.p3ID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP3ID(int p3id) {
/* 299 */     this.p3ID = p3id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlayer4() {
/* 306 */     return this.player4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayer4(String player4) {
/* 313 */     this.player4 = player4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP4ID() {
/* 320 */     return this.p4ID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP4ID(int p4id) {
/* 327 */     this.p4ID = p4id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlayers() {
/* 334 */     return this.players;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayers(String players) {
/* 341 */     this.players = players;
/*     */   }
/*     */   
/*     */   public void addComment(String comment) {
/* 345 */     this.lobbyTranscript.add(comment);
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 349 */     return this.lobbyTranscript.size();
/*     */   }
/*     */   
/*     */   public String getComment(int n) {
/* 353 */     return this.lobbyTranscript.get(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 361 */     if (this.gameType == 0) {
/* 362 */       return "NewGame [gameID=" + this.gameID + ", gameType= 1V1 game, goalScore=" + this.goalScore + ", host=" + this.host + 
/* 363 */         ", player2=" + this.player2 + "]";
/*     */     }
/* 365 */     return "NewGame [gameID=" + this.gameID + ", gameType= 2V2 game, goalScore=" + this.goalScore + ", host=" + this.host + 
/* 366 */       ", player2=" + this.player2 + ", player3=" + this.player3 + ", player4=" + this.player4 + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP1Team() {
/* 375 */     return this.p1Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP1Team(int p1Team) {
/* 382 */     this.p1Team = p1Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP2Team() {
/* 389 */     return this.p2Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP2Team(int p2Team) {
/* 396 */     this.p2Team = p2Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP3Team() {
/* 403 */     return this.p3Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP3Team(int p3Team) {
/* 410 */     this.p3Team = p3Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getP4Team() {
/* 417 */     return this.p4Team;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setP4Team(int p4Team) {
/* 424 */     this.p4Team = p4Team;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\sample\NewGame.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */