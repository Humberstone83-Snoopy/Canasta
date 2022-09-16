/*    */ package packet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringMsg
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String message;
/*    */   
/*    */   public StringMsg(String message) {
/* 16 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 20 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message) {
/* 24 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\packet\StringMsg.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */