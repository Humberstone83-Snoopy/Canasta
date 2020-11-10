/*     */ package client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.Socket;
/*     */ import java.sql.SQLException;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import packet.GamesInLobbyData;
/*     */ import packet.IntegerMsg;
/*     */ import packet.PlayersInLobbyData;
/*     */ import packet.StringMsg;
/*     */ import packet.TopTenData;
/*     */ import sample.ActiveGame;
/*     */ import sample.NewGame;
/*     */ import sample.UserGameStats;
/*     */ import sample.UserHighscore;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClientConnection
/*     */ {
/*     */   private Socket clientSocket;
/*     */   private ObjectInputStream in;
/*     */   private ObjectOutputStream out;
/*     */   private int clientID;
/*     */   private String username;
/*     */   private int gameID;
/*     */   private int savedGameID;
/*     */   
/*     */   public ClientConnection(String hostname, int port) throws IOException {
/*  36 */     log("Connecting to " + hostname + " on port " + port);
/*  37 */     this.clientSocket = new Socket(hostname, port);
/*  38 */     log("Connection with " + this.clientSocket.getRemoteSocketAddress() + " has been established.");
/*     */ 
/*     */     
/*  41 */     this.in = new ObjectInputStream(this.clientSocket.getInputStream());
/*  42 */     this.out = new ObjectOutputStream(this.clientSocket.getOutputStream());
/*     */     
/*  44 */     String serverResponse = receiveMsg();
/*  45 */     String[] params = serverResponse.split("#");
/*  46 */     log(serverResponse);
/*     */     
/*  48 */     this.clientID = Integer.parseInt(params[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(String message) {
/*  55 */     System.out.println(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws IOException {
/*  64 */     this.clientSocket.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public String receiveMsg() {
/*  69 */     StringMsg serverResponse = null;
/*  70 */     String message = null;
/*     */     
/*     */     try {
/*  73 */       serverResponse = (StringMsg)this.in.readObject();
/*  74 */       message = serverResponse.getMessage();
/*     */ 
/*     */ 
/*     */       
/*  78 */       log("Recieving:" + message);
/*  79 */     } catch (IOException|ClassNotFoundException e) {
/*  80 */       e.printStackTrace();
/*  81 */       log("Error receiving message");
/*     */     } 
/*  83 */     return message;
/*     */   }
/*     */   
/*     */   private int receiveIntMsg() {
/*  87 */     IntegerMsg serverResponse = null;
/*  88 */     int message = 0;
/*     */     
/*     */     try {
/*  91 */       serverResponse = (IntegerMsg)this.in.readObject();
/*  92 */       message = serverResponse.getMessage();
/*     */ 
/*     */ 
/*     */       
/*  96 */       log("Recieving:" + message);
/*  97 */     } catch (IOException|ClassNotFoundException e) {
/*  98 */       e.printStackTrace();
/*  99 */       log("Error receiving message");
/*     */     } 
/* 101 */     return message;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMsg(String clientResponse) throws IOException {
/* 106 */     this.out.writeObject(clientResponse);
/* 107 */     this.out.flush();
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
/*     */   public void signIn(String username, String password) throws IOException, SQLException {
/* 119 */     log("sending 0 to server");
/* 120 */     sendMsg("0," + username + "," + password);
/*     */     
/* 122 */     if (receiveMsg().equals("true")) {
/* 123 */       this.username = username;
/* 124 */       log(receiveMsg());
/* 125 */       this.clientID = receiveIntMsg();
/* 126 */       log("ID = " + this.clientID);
/*     */     }
/*     */     else {
/*     */       
/* 130 */       receiveMsg();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 139 */     return this.username;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean signUp(String userUsername, String userPassword, String userFirstName, String userLastName, String userAge) throws IOException {
/* 155 */     log("sending 1 to server");
/* 156 */     sendMsg("1," + userUsername + "," + userPassword + "," + userFirstName + "," + userLastName + "," + userAge);
/*     */     
/* 158 */     if (receiveMsg().equals("true")) {
/* 159 */       log(receiveMsg());
/* 160 */       return true;
/*     */     } 
/*     */     
/* 163 */     receiveMsg();
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logout() throws IOException {
/* 173 */     log("sending 4 to server");
/* 174 */     sendMsg("4");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObservableList<UserGameStats> getUserStats() throws IOException {
/* 184 */     log("sending 2 to server");
/* 185 */     sendMsg("2," + this.clientID);
/*     */     
/* 187 */     UserGameStats data = null;
/*     */ 
/*     */     
/*     */     try {
/* 191 */       data = (UserGameStats)this.in.readObject();
/* 192 */     } catch (ClassNotFoundException e) {
/* 193 */       e.printStackTrace();
/* 194 */     } catch (IOException e) {
/* 195 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 199 */     ObservableList<UserGameStats> userGameStatsData = FXCollections.observableArrayList();
/* 200 */     userGameStatsData.add(data);
/* 201 */     return userGameStatsData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObservableList<UserHighscore> getLeaderBoard() throws IOException {
/* 210 */     log("sending 3 to server");
/* 211 */     sendMsg("3");
/*     */ 
/*     */     
/* 214 */     TopTenData data = null;
/*     */     
/*     */     try {
/* 217 */       data = (TopTenData)this.in.readObject();
/* 218 */     } catch (ClassNotFoundException e) {
/* 219 */       e.printStackTrace();
/* 220 */     } catch (IOException e) {
/* 221 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 224 */     ObservableList<UserHighscore> userHighScores = FXCollections.observableArrayList();
/*     */     
/* 226 */     for (int i = 0; i < (data.getTopTen()).length; i++) {
/* 227 */       userHighScores.add(data.getTopTen()[i]);
/*     */     }
/*     */     
/* 230 */     return userHighScores;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObservableList<UserGameStats> getPlayersInLobby() throws IOException {
/* 240 */     log("sending 5 to server");
/* 241 */     sendMsg("5");
/*     */ 
/*     */ 
/*     */     
/* 245 */     PlayersInLobbyData data = null;
/*     */     try {
/* 247 */       data = (PlayersInLobbyData)this.in.readObject();
/* 248 */     } catch (ClassNotFoundException|IOException e) {
/* 249 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 252 */     ObservableList<UserGameStats> playersInLobby = FXCollections.observableArrayList();
/* 253 */     for (int i = 0; i < (data.getPlayersInLobby()).length; i++) {
/* 254 */       playersInLobby.add(data.getPlayersInLobby()[i]);
/*     */     }
/*     */     
/* 257 */     return playersInLobby;
/*     */   }
/*     */   
/*     */   public void sendLobbyMessage(String message) throws IOException {
/* 261 */     log("sending 6 to server");
/* 262 */     sendMsg("6");
/* 263 */     sendMsg(String.valueOf(this.username) + ": " + message);
/*     */   }
/*     */ 
/*     */   
/*     */   public String retrieveLobbyMessages(int size) throws IOException {
/* 268 */     log("sending 7 to server");
/* 269 */     sendMsg("7");
/*     */     
/* 271 */     int sizeDifference = receiveIntMsg() - size;
/*     */     
/* 273 */     String messages = null;
/*     */     
/* 275 */     if (sizeDifference > 0) {
/* 276 */       log("sending 8 to server");
/* 277 */       sendMsg("8," + sizeDifference);
/* 278 */       messages = receiveMsg();
/*     */     } 
/*     */     
/* 281 */     return messages;
/*     */   }
/*     */   
/*     */   public void addNewGame(int gameType, int goalScore, int teamAllocation) throws IOException {
/* 285 */     log("sending 9 to server");
/* 286 */     sendMsg("9," + gameType + "," + goalScore + "," + this.username + "," + this.clientID + "," + teamAllocation);
/* 287 */     this.gameID = receiveIntMsg();
/*     */   }
/*     */   
/*     */   public ObservableList<NewGame> getGamesInLobby() throws IOException {
/* 291 */     log("sending 10 to server");
/* 292 */     sendMsg("10");
/*     */ 
/*     */ 
/*     */     
/* 296 */     GamesInLobbyData data = null;
/*     */     try {
/* 298 */       data = (GamesInLobbyData)this.in.readObject();
/* 299 */     } catch (ClassNotFoundException|IOException e) {
/* 300 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 303 */     ObservableList<NewGame> gamesInLobby = FXCollections.observableArrayList();
/* 304 */     for (int i = 0; i < (data.getGamesInLobby()).length; i++) {
/* 305 */       if (data.getGamesInLobby()[i] != null) {
/* 306 */         gamesInLobby.add(data.getGamesInLobby()[i]);
/*     */       }
/*     */     } 
/*     */     
/* 310 */     return gamesInLobby;
/*     */   }
/*     */   
/*     */   public boolean joinGame(String hostName) throws IOException {
/* 314 */     if (hostName != null) {
/* 315 */       log("sending 11 to server");
/* 316 */       sendMsg("11," + hostName + "," + this.username + "," + this.clientID);
/*     */       
/* 318 */       String joined = receiveMsg();
/*     */       
/* 320 */       if (joined.equals("true")) {
/* 321 */         this.gameID = receiveIntMsg();
/* 322 */         return true;
/*     */       } 
/* 324 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 328 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NewGame getCurrentGLobby() throws IOException {
/* 335 */     log("sending 12 to server");
/* 336 */     sendMsg("12," + this.gameID);
/* 337 */     NewGame data = null;
/*     */     try {
/* 339 */       data = (NewGame)this.in.readObject();
/* 340 */     } catch (ClassNotFoundException e) {
/* 341 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 344 */     return data;
/*     */   }
/*     */   
/*     */   public void readyUp() throws IOException {
/* 348 */     log("sending 13 to server");
/* 349 */     sendMsg("13," + this.gameID + "," + this.clientID);
/*     */   }
/*     */   
/*     */   public void startGame() throws IOException {
/* 353 */     log("sending 14 to server");
/* 354 */     sendMsg("14," + this.gameID);
/*     */   }
/*     */   
/*     */   public void doSend(String messageInput) throws IOException {
/* 358 */     log("sending 15 to server");
/* 359 */     sendMsg("15," + this.gameID);
/* 360 */     sendMsg(messageInput);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActiveGame getActiveGame() throws IOException {
/* 365 */     log("sending 16 to server");
/* 366 */     sendMsg("16," + this.gameID);
/*     */     
/* 368 */     ActiveGame data = null;
/*     */     try {
/* 370 */       data = (ActiveGame)this.in.readObject();
/* 371 */     } catch (ClassNotFoundException e) {
/* 372 */       e.printStackTrace();
/*     */     } 
/* 374 */     return data;
/*     */   }
/*     */   
/*     */   public void sendActiveGame(ActiveGame aGame) throws IOException {
/* 378 */     log("sending 17 to server");
/* 379 */     sendMsg("17," + this.gameID);
/* 380 */     this.out.reset();
/* 381 */     this.out.writeObject(aGame);
/* 382 */     this.out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSavedGameEntry() throws IOException {
/* 388 */     log("sending 18 to server");
/* 389 */     sendMsg("18," + this.clientID + "," + this.gameID);
/*     */ 
/*     */     
/* 392 */     this.savedGameID = receiveIntMsg();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSavedGame(int team1Score, int team2Score) throws IOException {
/* 400 */     log("sending 19 to server");
/* 401 */     sendMsg("19," + this.savedGameID + "," + team1Score + "," + team2Score);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAndQuit() throws IOException {
/* 407 */     log("sending 20 to server");
/* 408 */     sendMsg("20," + this.clientID + "," + this.savedGameID + "," + this.username + "," + this.gameID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSettings(NewGame settings) throws IOException {
/* 414 */     log("sending 21 to server");
/* 415 */     sendMsg("21," + this.gameID);
/* 416 */     this.out.writeObject(settings);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void continueGame() throws IOException {
/* 422 */     log("sending 22 to server");
/* 423 */     sendMsg("22," + this.gameID + "," + this.clientID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkPlayersConnected(int gameType) throws IOException {
/* 429 */     log("sending 23 to server");
/* 430 */     sendMsg("23," + gameType + "," + this.gameID);
/* 431 */     String answer = receiveMsg();
/*     */     
/* 433 */     if (answer.equals("true")) {
/* 434 */       return true;
/*     */     }
/* 436 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkContinue(int gameType) throws IOException {
/* 443 */     log("sending 24 to server");
/* 444 */     sendMsg("24," + gameType + "," + this.gameID);
/* 445 */     String answer = receiveMsg();
/*     */     
/* 447 */     if (answer.equals("true")) {
/* 448 */       return true;
/*     */     }
/* 450 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\client\ClientConnection.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */