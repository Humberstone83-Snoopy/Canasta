/*     */ package database;
/*     */ 
/*     */ import gameLogic.TwoPlayerLogic;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.List;
/*     */ import sample.NewGame;
/*     */ import sample.UserGameStats;
/*     */ import sample.UserHighscore;
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
/*     */ public class Driver
/*     */   implements DriverInterface
/*     */ {
/*     */   private String URL;
/*     */   private String databaseUsername;
/*     */   private String databasePassword;
/*     */   private Statement statement;
/*     */   private Connection connection;
/*     */   
/*     */   public Driver() {
/*  36 */     System.out.println("Connecting to the database...");
/*     */     try {
/*  38 */       Exception exception2, exception1 = null;
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
/*     */     
/*     */     }
/*  52 */     catch (SQLException e) {
/*  53 */       e.printStackTrace();
/*  54 */     } catch (FileNotFoundException e1) {
/*  55 */       e1.printStackTrace();
/*  56 */     } catch (IOException e1) {
/*  57 */       e1.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean authenticate(String user, String passwd) throws IOException {
/*     */     try {
/*  68 */       ResultSet resultSet = this.statement.executeQuery("select * from users");
/*  69 */       while (resultSet.next()) {
/*  70 */         String username = resultSet.getString("username");
/*  71 */         String password = resultSet.getString("pass_word");
/*     */         
/*  73 */         if (user.equals(username) && passwd.equals(password)) {
/*  74 */           log("Authentication succesfull");
/*  75 */           return true;
/*     */         }
/*     */       
/*     */       } 
/*  79 */     } catch (SQLException e) {
/*  80 */       e.printStackTrace();
/*     */     } 
/*  82 */     log("Authentication failed");
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserID(String username) throws IOException, SQLException {
/*  94 */     int key = -1;
/*     */     try {
/*  96 */       ResultSet rs = this.statement.executeQuery("SELECT * FROM users WHERE username =  '" + username + "'");
/*  97 */       while (rs.next()) {
/*  98 */         key = rs.getInt(1);
/*     */       }
/* 100 */     } catch (SQLException e) {
/* 101 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 104 */     return key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void signIn(int userID) throws SQLException {
/* 115 */     this.statement.executeUpdate("UPDATE users SET online = true, avaliable = true WHERE id= '" + userID + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logout(int userID) throws SQLException {
/* 127 */     this.statement.executeUpdate("UPDATE users SET online = false, avaliable = false WHERE id= '" + userID + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean signUp(String username, String password, String fName, String lName, int age) {
/*     */     try {
/* 137 */       ResultSet resultSet = this.statement.executeQuery("select username from users");
/* 138 */       while (resultSet.next()) {
/* 139 */         String rsUsername = resultSet.getString("username");
/*     */         
/* 141 */         if (rsUsername.equals(username)) {
/* 142 */           log("Username is taken");
/* 143 */           return false;
/*     */         } 
/*     */       } 
/*     */       
/* 147 */       PreparedStatement insertStatement = this.connection.prepareStatement(
/* 148 */           "INSERT INTO users(username, pass_word, online, avaliable) VALUES (?,?,false,false) ");
/*     */       
/* 150 */       insertStatement.setString(1, username);
/* 151 */       insertStatement.setString(2, password);
/*     */       
/* 153 */       insertStatement.executeUpdate();
/*     */       
/* 155 */       int user_id = -1;
/*     */       
/* 157 */       ResultSet resultSet2 = this.statement.executeQuery("select id from users where username = '" + username + "'");
/* 158 */       while (resultSet2.next()) {
/* 159 */         user_id = resultSet2.getInt("id");
/*     */       }
/*     */       
/* 162 */       PreparedStatement insertStatement2 = this.connection
/* 163 */         .prepareStatement("INSERT INTO user_profiles(user_id, first_name, last_name, age, avatar, country) VALUES (?,?,?,?,?,?) ");
/*     */ 
/*     */       
/* 166 */       insertStatement2.setInt(1, user_id);
/* 167 */       insertStatement2.setString(2, fName);
/* 168 */       insertStatement2.setString(3, lName);
/* 169 */       insertStatement2.setInt(4, age);
/* 170 */       insertStatement2.setString(5, "default");
/* 171 */       insertStatement2.setString(6, "Unspecified");
/*     */       
/* 173 */       insertStatement2.executeUpdate();
/*     */       
/* 175 */       PreparedStatement insertStatement3 = this.connection.prepareStatement(
/* 176 */           "INSERT INTO user_games_stats(user_id, no_games_played, wins, losses, experience, rank) VALUES (?,?,?,?,?,?) ");
/*     */ 
/*     */       
/* 179 */       insertStatement3.setInt(1, user_id);
/* 180 */       insertStatement3.setInt(2, 0);
/* 181 */       insertStatement3.setInt(3, 0);
/* 182 */       insertStatement3.setInt(4, 0);
/* 183 */       insertStatement3.setInt(5, 0);
/* 184 */       insertStatement3.setInt(6, 0);
/*     */       
/* 186 */       insertStatement3.executeUpdate();
/*     */       
/* 188 */       return true;
/*     */     }
/* 190 */     catch (SQLException e) {
/* 191 */       System.out.println("Driver: signUp SQL Exception");
/* 192 */       e.printStackTrace();
/*     */ 
/*     */       
/* 195 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserGameStats getUserGameData(String id) throws SQLException, IOException {
/* 208 */     UserGameStats MyGameStats = null;
/* 209 */     int noGamesPlayed = -1;
/* 210 */     int wins = -1;
/* 211 */     int losses = -1;
/* 212 */     int experience = -1;
/*     */     
/*     */     try {
/* 215 */       ResultSet gameStatsSet = this.statement.executeQuery(
/* 216 */           "SELECT no_games_played, wins, losses, experience FROM user_games_stats WHERE USER_ID ='" + id + 
/* 217 */           "'");
/*     */       
/* 219 */       while (gameStatsSet.next()) {
/* 220 */         noGamesPlayed = gameStatsSet.getInt("no_games_played");
/* 221 */         wins = gameStatsSet.getInt("wins");
/* 222 */         losses = gameStatsSet.getInt("losses");
/* 223 */         experience = gameStatsSet.getInt("experience");
/*     */         
/* 225 */         MyGameStats = new UserGameStats(noGamesPlayed, wins, losses, experience);
/*     */       }
/*     */     
/* 228 */     } catch (SQLException e) {
/* 229 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 232 */     return MyGameStats;
/*     */   }
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
/*     */   public UserHighscore[] getTopTenPlayers() throws SQLException, IOException {
/* 245 */     UserHighscore[] Array = new UserHighscore[10];
/*     */     
/*     */     try {
/* 248 */       ResultSet highScoresResultSet = this.statement.executeQuery(
/* 249 */           "SELECT  a.user_id, b.username, a.experience FROM user_games_stats AS a INNER JOIN users AS b ON a.user_id = b.id WHERE a.USER_ID IS NOT NULL ORDER BY experience DESC LIMIT 10");
/*     */       
/* 251 */       int counter = 0;
/* 252 */       while (highScoresResultSet.next()) {
/* 253 */         int score = highScoresResultSet.getInt("experience");
/* 254 */         String username = highScoresResultSet.getString("username");
/*     */         
/* 256 */         Array[counter] = new UserHighscore(score, username);
/* 257 */         counter++;
/*     */       }
/*     */     
/* 260 */     } catch (SQLException e) {
/* 261 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 264 */     return Array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserGameStats[] getPlayersInLobby() throws IOException, SQLException {
/* 276 */     UserGameStats[] array = new UserGameStats[100];
/*     */     
/*     */     try {
/* 279 */       ResultSet playerInfo = this.statement
/* 280 */         .executeQuery("SELECT username FROM users WHERE online = true AND avaliable = true");
/* 281 */       int counter = 0;
/*     */       
/* 283 */       while (playerInfo.next()) {
/* 284 */         String username = playerInfo.getString("username");
/* 285 */         array[counter] = new UserGameStats(username);
/* 286 */         counter++;
/*     */       } 
/* 288 */     } catch (SQLException e) {
/* 289 */       e.printStackTrace();
/*     */     } 
/* 291 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   public int createSavedGame(int userID, int team) throws SQLException {
/* 296 */     PreparedStatement insertStatement = this.connection.prepareStatement(
/* 297 */         "INSERT INTO saved_games(user_id, user_team, team1_score, team2_score) VALUES (?,?,?,?) ");
/*     */     
/* 299 */     insertStatement.setInt(1, userID);
/* 300 */     insertStatement.setInt(2, team);
/* 301 */     insertStatement.setInt(3, 0);
/* 302 */     insertStatement.setInt(4, 0);
/*     */     
/* 304 */     insertStatement.executeUpdate();
/*     */     
/* 306 */     int id = -1;
/*     */     
/* 308 */     ResultSet savedGameInfo = this.statement.executeQuery("SELECT id FROM saved_games WHERE user_id = " + userID);
/*     */     
/* 310 */     while (savedGameInfo.next()) {
/* 311 */       id = savedGameInfo.getInt("id");
/*     */     }
/*     */     
/* 314 */     return id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSavedGame(int sGID, int t1Sc, int t2Sc) throws SQLException {
/* 319 */     this.statement.executeUpdate("UPDATE saved_games SET team1_score = " + t1Sc + ", team2_score = " + t2Sc + " WHERE id= '" + sGID + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getTeamScores(int gameID) throws SQLException {
/* 325 */     ResultSet savedGameInfo = this.statement.executeQuery("SELECT team1_score, team2_score FROM saved_games WHERE id = " + gameID);
/*     */     
/* 327 */     int team1Score = 0;
/* 328 */     int team2Score = 0;
/*     */     
/* 330 */     while (savedGameInfo.next()) {
/* 331 */       team1Score = savedGameInfo.getInt("team1_score");
/* 332 */       team2Score = savedGameInfo.getInt("team2_score");
/*     */     } 
/*     */     
/* 335 */     int[] teamScores = { team1Score, team2Score };
/*     */     
/* 337 */     return teamScores;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateGameStats(int clientID, boolean win, int newExperience) throws SQLException {
/* 342 */     ResultSet gameStatsInfo = this.statement.executeQuery("SELECT * FROM user_games_stats WHERE user_id = '" + clientID + "'");
/*     */     
/* 344 */     int noOfGamesPlayed = -1;
/* 345 */     int wins = -1;
/* 346 */     int losses = -1;
/* 347 */     int experience = -1;
/* 348 */     int rank = -1;
/* 349 */     int id = -1;
/*     */     
/* 351 */     while (gameStatsInfo.next()) {
/* 352 */       id = gameStatsInfo.getInt("id");
/* 353 */       noOfGamesPlayed = gameStatsInfo.getInt("no_games_played");
/* 354 */       wins = gameStatsInfo.getInt("wins");
/* 355 */       losses = gameStatsInfo.getInt("losses");
/* 356 */       experience = gameStatsInfo.getInt("experience");
/* 357 */       rank = gameStatsInfo.getInt("rank");
/*     */     } 
/*     */     
/* 360 */     noOfGamesPlayed++;
/* 361 */     if (win) {
/* 362 */       wins++;
/*     */     } else {
/* 364 */       losses++;
/*     */     } 
/*     */ 
/*     */     
/* 368 */     experience += newExperience;
/* 369 */     rank = Math.floorDiv(experience, 10000);
/*     */ 
/*     */     
/* 372 */     this.statement.executeUpdate("UPDATE user_games_stats SET no_games_played = " + noOfGamesPlayed + ", wins = " + wins + ", losses = " + losses + ", experience = " + experience + ", rank = " + rank + " WHERE id = '" + id + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean changeUsername(String oldUsername, String newUsername) throws IOException, SQLException {
/* 381 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean changePassword(String oldPassword, String newPassword) throws IOException, SQLException {
/* 387 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSavedGamesData(String username, int p1Score, int p2Score, int noOfRounds, int playerNumber) throws IOException, SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NewGame[] getSavedGameLobbyPlayers() throws IOException, SQLException {
/* 400 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromLiveSavedGamesTable(String userName) throws IOException, SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToLivePlayerTable(String userName) throws IOException, SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int assignGameID(int game_id, List<?> rs1) {
/* 418 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int addToNewGameTable(String username, int clientID) throws IOException {
/* 424 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromNewGamesTable(String username) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteNewGames(String username) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromLivePlayerTable(String userName) throws IOException, SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NewGame[] getNewGames() throws IOException {
/* 448 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readProperties() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeDatabase() throws SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnline(String username, boolean b) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean joinGame(String targetClient, int clientID) throws IOException {
/* 472 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGuestClientID(String username) throws IOException {
/* 478 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getOpReady(String username) throws IOException {
/* 484 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getGameStart(String opUsername) throws IOException {
/* 490 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpReady(String opUsername) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromJoinedGame(String opUsername) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateGame(TwoPlayerLogic game, int gameID) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwoPlayerLogic selectFromGame(int gameID) throws IOException {
/* 514 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createGame(int gameID) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStarted(int gameID) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGameID(String opUsername) throws IOException {
/* 532 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerTurn(int playerID, int gameID) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlayerTurn(int gameID) throws IOException {
/* 544 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsoleMessage(int gameID, int protocol) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(String message) {
/* 557 */     System.out.println(message);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\database\Driver.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */