/*    */ package packet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import sample.UserHighscore;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TopTenData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   UserHighscore[] topTen = new UserHighscore[10];
/*    */ 
/*    */   
/*    */   public TopTenData(UserHighscore[] topTen) {
/* 17 */     this.topTen = topTen;
/*    */   }
/*    */   
/*    */   public UserHighscore[] getTopTen() {
/* 21 */     return this.topTen;
/*    */   }
/*    */   
/*    */   public void setTopTen(UserHighscore[] topTen) {
/* 25 */     this.topTen = topTen;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\packet\TopTenData.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */