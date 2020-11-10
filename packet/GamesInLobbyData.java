/*    */ package packet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import sample.NewGame;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GamesInLobbyData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 12 */   private NewGame[] gamesInLobby = new NewGame[100];
/*    */ 
/*    */   
/*    */   public GamesInLobbyData(NewGame[] gamesInLobby) {
/* 16 */     this.gamesInLobby = gamesInLobby;
/*    */   }
/*    */   
/*    */   public NewGame[] getGamesInLobby() {
/* 20 */     return this.gamesInLobby;
/*    */   }
/*    */   
/*    */   public void setGamessInLobby(NewGame[] gamesInLobby) {
/* 24 */     this.gamesInLobby = gamesInLobby;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\packet\GamesInLobbyData.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */