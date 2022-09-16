/*    */ package packet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import sample.UserGameStats;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayersInLobbyData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   private UserGameStats[] playersInLobby = new UserGameStats[100];
/*    */ 
/*    */   
/*    */   public PlayersInLobbyData(UserGameStats[] playersInLobby) {
/* 17 */     this.playersInLobby = playersInLobby;
/*    */   }
/*    */   
/*    */   public UserGameStats[] getPlayersInLobby() {
/* 21 */     return this.playersInLobby;
/*    */   }
/*    */   
/*    */   public void setPlayersInLobby(UserGameStats[] playersInLobby) {
/* 25 */     this.playersInLobby = playersInLobby;
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\packet\PlayersInLobbyData.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */