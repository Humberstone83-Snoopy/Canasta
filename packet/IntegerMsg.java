/*    */ package packet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegerMsg
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int message;
/*    */   
/*    */   public IntegerMsg(int message) {
/* 17 */     this.message = message;
/*    */   }
/*    */   
/*    */   public int getMessage() {
/* 21 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(int message) {
/* 25 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\packet\IntegerMsg.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */