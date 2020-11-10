package database;

import gameLogic.TwoPlayerLogic;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import sample.NewGame;
import sample.UserGameStats;
import sample.UserHighscore;

public interface DriverInterface {
  boolean authenticate(String paramString1, String paramString2) throws IOException;
  
  boolean signUp(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt);
  
  boolean changeUsername(String paramString1, String paramString2) throws IOException, SQLException;
  
  boolean changePassword(String paramString1, String paramString2) throws IOException, SQLException;
  
  UserHighscore[] getTopTenPlayers() throws SQLException, IOException;
  
  UserGameStats getUserGameData(String paramString) throws SQLException, IOException;
  
  void setSavedGamesData(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IOException, SQLException;
  
  NewGame[] getSavedGameLobbyPlayers() throws IOException, SQLException;
  
  void removeFromLiveSavedGamesTable(String paramString) throws IOException, SQLException;
  
  void addToLivePlayerTable(String paramString) throws IOException, SQLException;
  
  int assignGameID(int paramInt, List<?> paramList);
  
  int addToNewGameTable(String paramString, int paramInt) throws IOException;
  
  void removeFromNewGamesTable(String paramString) throws IOException;
  
  void deleteNewGames(String paramString) throws IOException;
  
  void removeFromLivePlayerTable(String paramString) throws IOException, SQLException;
  
  UserGameStats[] getPlayersInLobby() throws IOException, SQLException;
  
  NewGame[] getNewGames() throws IOException;
  
  int getUserID(String paramString) throws IOException, SQLException;
  
  void readProperties() throws IOException;
  
  void closeDatabase() throws SQLException;
  
  void setOnline(String paramString, boolean paramBoolean);
  
  boolean joinGame(String paramString, int paramInt) throws IOException;
  
  int getGuestClientID(String paramString) throws IOException;
  
  Boolean getOpReady(String paramString) throws IOException;
  
  Boolean getGameStart(String paramString) throws IOException;
  
  void setOpReady(String paramString) throws IOException;
  
  void removeFromJoinedGame(String paramString) throws IOException;
  
  void updateGame(TwoPlayerLogic paramTwoPlayerLogic, int paramInt) throws IOException;
  
  TwoPlayerLogic selectFromGame(int paramInt) throws IOException;
  
  void createGame(int paramInt) throws IOException;
  
  void setStarted(int paramInt) throws IOException;
  
  int getGameID(String paramString) throws IOException;
  
  void setPlayerTurn(int paramInt1, int paramInt2) throws IOException;
  
  int getPlayerTurn(int paramInt) throws IOException;
  
  void setConsoleMessage(int paramInt1, int paramInt2) throws IOException;
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\database\DriverInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */