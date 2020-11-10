/*    */ package sample;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserHighscore
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int experience;
/*    */   private String username;
/*    */   
/*    */   public UserHighscore(int experience, String username) {
/* 16 */     this.experience = experience;
/* 17 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 21 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 25 */     this.username = username;
/*    */   }
/*    */   
/*    */   public int getExperience() {
/* 29 */     return this.experience;
/*    */   }
/*    */   
/*    */   public void setExperience(int experience) {
/* 33 */     this.experience = experience;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\sample\UserHighscore.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */