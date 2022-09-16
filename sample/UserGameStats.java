/*    */ package sample;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserGameStats
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int numberGamesPlayed;
/*    */   private int wins;
/*    */   private int losses;
/*    */   private int experience;
/*    */   private String username;
/*    */   
/*    */   public String toString() {
/* 15 */     return "UserGameStats [numberGamesPlayed=" + this.numberGamesPlayed + ", wins=" + this.wins + ", losses=" + this.losses + 
/* 16 */       ", experience=" + this.experience + ", username=" + this.username + "]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUsername() {
/* 27 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 31 */     this.username = username;
/*    */   }
/*    */   
/*    */   public UserGameStats(String username) {
/* 35 */     this.username = username;
/*    */   }
/*    */   
/*    */   public UserGameStats(int numberGamesPlayed, int wins, int losses) {
/* 39 */     this.numberGamesPlayed = numberGamesPlayed;
/* 40 */     this.wins = wins;
/* 41 */     this.losses = losses;
/*    */   }
/*    */   
/*    */   public UserGameStats(int numberGamesPlayed, int wins, int losses, int experience) {
/* 45 */     this.numberGamesPlayed = numberGamesPlayed;
/* 46 */     this.wins = wins;
/* 47 */     this.losses = losses;
/* 48 */     this.experience = experience;
/*    */   }
/*    */   
/*    */   public int getNumberGamesPlayed() {
/* 52 */     return this.numberGamesPlayed;
/*    */   }
/*    */   
/*    */   public void setNumberGamesPlayed(int numberGamesPlayed) {
/* 56 */     this.numberGamesPlayed = numberGamesPlayed;
/*    */   }
/*    */   
/*    */   public int getWins() {
/* 60 */     return this.wins;
/*    */   }
/*    */   
/*    */   public void setWins(int wins) {
/* 64 */     this.wins = wins;
/*    */   }
/*    */   
/*    */   public int getLosses() {
/* 68 */     return this.losses;
/*    */   }
/*    */   
/*    */   public void setLosses(int losses) {
/* 72 */     this.losses = losses;
/*    */   }
/*    */   
/*    */   public int getExperience() {
/* 76 */     return this.experience;
/*    */   }
/*    */   
/*    */   public void setExperience(int experience) {
/* 80 */     this.experience = experience;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\sample\UserGameStats.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */