/*     */ package server;
/*     */ 
/*     */ import database.Driver;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.Socket;
/*     */ import java.util.Arrays;
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
/*     */ public class ClientHandler
/*     */   implements Runnable, ClientHandlerInterface
/*     */ {
/*     */   private Socket clientSocket;
/*     */   private int clientNumber;
/*     */   private ObjectOutputStream out;
/*     */   private ObjectInputStream in;
/*     */   private Driver db;
/*     */   private int userID;
/*     */   
/*     */   public ClientHandler(Socket clientSocket, int clientNumber) {
/*  31 */     this.userID = -1;
/*  32 */     this.clientSocket = clientSocket;
/*  33 */     this.clientNumber = clientNumber;
/*  34 */     this.db = new Driver();
/*  35 */     log("New connection with client #" + clientNumber + " at " + clientSocket);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(String message) {
/*  42 */     System.out.println(message);
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
/*     */   public void run() {
/*     */     try {
/*  55 */       this.out = new ObjectOutputStream(this.clientSocket.getOutputStream());
/*  56 */       this.in = new ObjectInputStream(this.clientSocket.getInputStream());
/*     */ 
/*     */       
/*  59 */       log("sending greeting");
/*  60 */       this.out.writeObject(new StringMsg("Welcome, you are client #" + this.clientNumber));
/*     */ 
/*     */       
/*     */       while (true) {
/*  64 */         String clientResponse = (String)this.in.readObject();
/*  65 */         parseOpcode(clientResponse);
/*     */       } 
/*  67 */     } catch (IOException e) {
/*  68 */       log("Error handling client #" + this.clientNumber + ": " + e);
/*  69 */     } catch (Exception e) {
/*  70 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  73 */         this.clientSocket.close();
/*  74 */       } catch (IOException e) {
/*  75 */         log("Error closing client connection");
/*     */       } 
/*     */     } 
/*  78 */     log("Connection with client #" + this.clientNumber + " has been terminated."); } public void parseOpcode(String clientResponse) throws Exception { NewGame[] gameLobby; int counter, size, messageFinder; String messages; int i, gameType, goalScore; String username; int userID, teamAllocation; NewGame game, newGameArray[]; String hostName, myUsername; int myID, joinedGameID, clientID; ActiveGame activeGame, aGame, activeGames[]; int myTeam, savedGameID; NewGame settings; int sGID, t1Sc, t2Sc, SGID, teamScores[], team; boolean win; int experience;
/*     */     NewGame gameSettings, nGameArray[];
/*     */     boolean allConnected;
/*     */     int gType;
/*     */     ActiveGame aGame23;
/*     */     boolean allContinue;
/*     */     int gType24;
/*     */     ActiveGame aGame24;
/*  86 */     this.out.reset();
/*  87 */     Boolean authenticationFlag = Boolean.valueOf(false);
/*  88 */     int gameID = -1;
/*     */     
/*  90 */     String[] params = clientResponse.split(",");
/*  91 */     int opcode = Integer.parseInt(params[0]);
/*     */     
/*  93 */     switch (opcode) {
/*     */       
/*     */       case 0:
/*  96 */         log("0");
/*     */         
/*     */         try {
/*  99 */           String str1 = params[1];
/* 100 */           String password = params[2];
/*     */           
/* 102 */           if (this.db.authenticate(str1, password)) {
/* 103 */             log("Authentication successful");
/* 104 */             authenticationFlag = Boolean.valueOf(true);
/* 105 */             this.out.writeObject(new StringMsg(authenticationFlag.toString()));
/* 106 */             this.out.flush();
/* 107 */             this.out.writeObject(new StringMsg("Authentication succeeded, welcome " + str1 + "!"));
/* 108 */             this.out.flush();
/*     */             
/* 110 */             this.userID = this.db.getUserID(str1);
/* 111 */             if (this.userID == -1) {
/* 112 */               log("Failed to fetchUserID");
/*     */             }
/*     */             
/* 115 */             this.db.signIn(this.userID);
/* 116 */             this.out.writeObject(new IntegerMsg(this.userID));
/* 117 */             this.out.flush();
/*     */           } else {
/* 119 */             log("Incorrect username or password.");
/* 120 */             this.out.writeObject(new StringMsg(authenticationFlag.toString()));
/* 121 */             this.out.flush();
/* 122 */             this.out.writeObject(new StringMsg("Incorrect username or password."));
/* 123 */             this.out.flush();
/*     */           } 
/* 125 */           Arrays.fill((Object[])params, (Object)null);
/*     */           break;
/* 127 */         } catch (Exception e) {
/* 128 */           e.printStackTrace();
/* 129 */           log("Client Handler: Exception case 0");
/*     */         } 
/*     */       
/*     */       case 1:
/* 133 */         log("1");
/*     */         
/*     */         try {
/* 136 */           String str1 = params[1];
/* 137 */           String password = params[2];
/* 138 */           String fName = params[3];
/* 139 */           String lName = params[4];
/* 140 */           int age = Integer.parseInt(params[5]);
/*     */           
/* 142 */           Boolean addFlag = Boolean.valueOf(false);
/*     */           
/* 144 */           if (this.db.signUp(str1, password, fName, lName, age)) {
/* 145 */             log("Added new user");
/* 146 */             addFlag = Boolean.valueOf(true);
/* 147 */             this.out.writeObject(new StringMsg(addFlag.toString()));
/* 148 */             this.out.flush();
/* 149 */             this.out.writeObject(new StringMsg("User " + str1 + " added successfully!"));
/* 150 */             this.out.flush();
/*     */           } else {
/* 152 */             addFlag = Boolean.valueOf(false);
/* 153 */             this.out.writeObject(new StringMsg(addFlag.toString()));
/* 154 */             this.out.flush();
/* 155 */             this.out.writeObject(new StringMsg("Signup failed!"));
/* 156 */             this.out.flush();
/*     */           }
/*     */         
/* 159 */         } catch (Exception e) {
/* 160 */           e.printStackTrace();
/* 161 */           log("Client Handler: Exception case 1");
/*     */         } 
/* 163 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 168 */         log("2");
/*     */         
/*     */         try {
/* 171 */           String id = params[1];
/*     */           
/* 173 */           UserGameStats data = this.db.getUserGameData(id);
/* 174 */           this.out.writeObject(data);
/*     */         }
/* 176 */         catch (Exception e) {
/* 177 */           e.printStackTrace();
/* 178 */           log("Client Handler: Exception case 2");
/*     */         } 
/* 180 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 185 */         log("3");
/*     */ 
/*     */         
/*     */         try {
/* 189 */           UserHighscore[] data = this.db.getTopTenPlayers();
/* 190 */           TopTenData serverResponse = new TopTenData(data);
/* 191 */           this.out.writeObject(serverResponse);
/*     */         }
/* 193 */         catch (Exception e) {
/* 194 */           e.printStackTrace();
/* 195 */           log("Client Handler: Exception case 3");
/*     */         } 
/* 197 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 202 */         log("4");
/*     */         try {
/* 204 */           if (this.userID != -1) {
/* 205 */             this.db.logout(this.userID);
/* 206 */             this.userID = -1;
/*     */           } 
/* 208 */         } catch (Exception e) {
/* 209 */           e.printStackTrace();
/* 210 */           log("Client Handler: Exception case 4");
/*     */         } 
/* 212 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 217 */         log("5");
/*     */         try {
/* 219 */           UserGameStats[] data = this.db.getPlayersInLobby();
/* 220 */           PlayersInLobbyData serverResponse = new PlayersInLobbyData(data);
/* 221 */           this.out.writeObject(serverResponse);
/* 222 */         } catch (Exception e) {
/* 223 */           e.printStackTrace();
/* 224 */           log("Client Handler: Exception case 5");
/*     */         } 
/* 226 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 231 */         log("6");
/*     */         try {
/* 233 */           String message = (String)this.in.readObject();
/* 234 */           CentralServer.addComment(message);
/* 235 */         } catch (Exception e) {
/* 236 */           e.printStackTrace();
/* 237 */           log("Client Handler: Exception case 6");
/*     */         } 
/* 239 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 7:
/* 244 */         log("7");
/*     */         try {
/* 246 */           int j = CentralServer.getSize();
/* 247 */           this.out.writeObject(new IntegerMsg(j));
/* 248 */         } catch (Exception e) {
/* 249 */           e.printStackTrace();
/* 250 */           log("Client Handler: Exception case 7");
/*     */         } 
/* 252 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 8:
/* 257 */         log("8");
/* 258 */         counter = Integer.parseInt(params[1]);
/*     */         
/* 260 */         size = CentralServer.getSize();
/* 261 */         messageFinder = counter;
/* 262 */         messages = "";
/*     */         
/* 264 */         for (i = 0; i < counter; i++) {
/* 265 */           messages = String.valueOf(messages) + CentralServer.getComment(size - messageFinder);
/* 266 */           messages = String.valueOf(messages) + "~";
/* 267 */           messageFinder--;
/*     */         } 
/*     */         try {
/* 270 */           this.out.writeObject(new StringMsg(messages));
/*     */         }
/* 272 */         catch (Exception e) {
/* 273 */           e.printStackTrace();
/* 274 */           log("Client Handler: Exception case 8");
/*     */         } 
/* 276 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 9:
/* 281 */         log("9");
/*     */         
/* 283 */         gameID = CentralServer.getNewGameCounter();
/* 284 */         gameType = Integer.parseInt(params[1]);
/* 285 */         goalScore = Integer.parseInt(params[2]);
/* 286 */         username = params[3];
/* 287 */         userID = Integer.parseInt(params[4]);
/* 288 */         teamAllocation = Integer.parseInt(params[5]);
/*     */         
/* 290 */         game = new NewGame(gameID, gameType, goalScore, username, userID, teamAllocation);
/* 291 */         newGameArray = CentralServer.getGameLobby();
/* 292 */         newGameArray[gameID] = game;
/* 293 */         CentralServer.setGameLobby(newGameArray);
/*     */         
/* 295 */         this.out.writeObject(new IntegerMsg(gameID));
/* 296 */         gameID++;
/* 297 */         CentralServer.setNewGameCounter(gameID);
/*     */         
/* 299 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 10:
/* 304 */         log("10");
/*     */         
/*     */         try {
/* 307 */           NewGame[] data = CentralServer.getGameLobby();
/* 308 */           GamesInLobbyData serverResponse = new GamesInLobbyData(data);
/* 309 */           this.out.writeObject(serverResponse);
/* 310 */         } catch (Exception e) {
/* 311 */           e.printStackTrace();
/* 312 */           log("Client Handler: Exception case 10");
/*     */         } 
/* 314 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 11:
/* 319 */         log("11");
/*     */         
/* 321 */         hostName = params[1];
/* 322 */         myUsername = params[2];
/* 323 */         myID = Integer.parseInt(params[3]);
/* 324 */         authenticationFlag = Boolean.valueOf(false);
/* 325 */         joinedGameID = -1;
/*     */         
/*     */         try {
/* 328 */           NewGame[] lobby = CentralServer.getGameLobby();
/* 329 */           for (int j = 0; j < lobby.length; j++) {
/*     */             
/* 331 */             if (lobby[j].getHost().equals(hostName)) {
/*     */ 
/*     */               
/* 334 */               if (lobby[j].getGameType() == 0) {
/*     */                 
/* 336 */                 if (lobby[j].getPlayer2() == null) {
/*     */                   
/* 338 */                   lobby[j].setP2ID(myID);
/* 339 */                   lobby[j].setPlayer2(myUsername);
/* 340 */                   lobby[j].setPlayers("2/2");
/* 341 */                   authenticationFlag = Boolean.valueOf(true);
/* 342 */                   CentralServer.setGameLobby(lobby);
/* 343 */                   joinedGameID = lobby[j].getGameID();
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */                 
/* 348 */                 authenticationFlag = Boolean.valueOf(false);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 353 */               if (lobby[j].getPlayer2() == null) {
/*     */                 
/* 355 */                 lobby[j].setP2ID(myID);
/* 356 */                 lobby[j].setPlayer2(myUsername);
/* 357 */                 lobby[j].setPlayers("2/4");
/* 358 */                 authenticationFlag = Boolean.valueOf(true);
/* 359 */                 CentralServer.setGameLobby(lobby);
/* 360 */                 joinedGameID = lobby[j].getGameID();
/*     */                 break;
/*     */               } 
/* 363 */               if (lobby[j].getPlayer3() == null) {
/*     */                 
/* 365 */                 lobby[j].setP3ID(myID);
/* 366 */                 lobby[j].setPlayer3(myUsername);
/* 367 */                 lobby[j].setPlayers("3/4");
/* 368 */                 authenticationFlag = Boolean.valueOf(true);
/* 369 */                 CentralServer.setGameLobby(lobby);
/* 370 */                 joinedGameID = lobby[j].getGameID();
/*     */                 break;
/*     */               } 
/* 373 */               if (lobby[j].getPlayer4() == null) {
/*     */                 
/* 375 */                 lobby[j].setP4ID(myID);
/* 376 */                 lobby[j].setPlayer4(myUsername);
/* 377 */                 lobby[j].setPlayers("4/4");
/* 378 */                 authenticationFlag = Boolean.valueOf(true);
/* 379 */                 CentralServer.setGameLobby(lobby);
/* 380 */                 joinedGameID = lobby[j].getGameID();
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 385 */               authenticationFlag = Boolean.valueOf(false);
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 392 */           this.out.writeObject(new StringMsg(authenticationFlag.toString()));
/* 393 */           this.out.flush();
/*     */           
/* 395 */           if (authenticationFlag.booleanValue()) {
/* 396 */             this.out.writeObject(new IntegerMsg(joinedGameID));
/* 397 */             this.out.flush();
/*     */           }
/*     */         
/* 400 */         } catch (Exception e) {
/* 401 */           e.printStackTrace();
/* 402 */           log("ClientHandler: Exception case 11");
/* 403 */           this.out.writeObject(new StringMsg("Error"));
/*     */         } 
/* 405 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 12:
/* 410 */         log("12");
/*     */         
/* 412 */         gameID = Integer.parseInt(params[1]);
/*     */         
/* 414 */         this.out.writeObject(CentralServer.getGameLobby()[gameID]);
/* 415 */         this.out.flush();
/*     */         
/* 417 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 13:
/* 422 */         log("13");
/*     */         
/* 424 */         gameID = Integer.parseInt(params[1]);
/* 425 */         clientID = Integer.parseInt(params[2]);
/*     */         
/* 427 */         gameLobby = CentralServer.getGameLobby();
/*     */         
/* 429 */         if (gameLobby[gameID].getP2ID() == clientID) {
/* 430 */           gameLobby[gameID].setP2Ready(true);
/* 431 */         } else if (gameLobby[gameID].getP3ID() == clientID) {
/* 432 */           gameLobby[gameID].setP3Ready(true);
/*     */         } else {
/* 434 */           gameLobby[gameID].setP4Ready(true);
/*     */         } 
/*     */         
/* 437 */         CentralServer.setGameLobby(gameLobby);
/* 438 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 14:
/* 443 */         log("14");
/*     */         
/* 445 */         gameID = Integer.parseInt(params[1]);
/* 446 */         gameLobby = CentralServer.getGameLobby();
/*     */         
/* 448 */         gameLobby[gameID].setGameStarted(true);
/* 449 */         CentralServer.setGameLobby(gameLobby);
/*     */         
/* 451 */         CentralServer.startGame(gameID);
/*     */         
/* 453 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 15:
/* 458 */         log("15");
/*     */         
/* 460 */         gameID = Integer.parseInt(params[1]);
/*     */         
/*     */         try {
/* 463 */           String message = (String)this.in.readObject();
/* 464 */           CentralServer.getGameLobby()[gameID].addComment(message);
/* 465 */         } catch (Exception e) {
/* 466 */           e.printStackTrace();
/* 467 */           log("Client Handler: Exception case 15");
/*     */         } 
/* 469 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 16:
/* 474 */         log("16");
/*     */         
/* 476 */         gameID = Integer.parseInt(params[1]);
/*     */         
/* 478 */         activeGame = CentralServer.getActiveGames()[gameID];
/* 479 */         this.out.reset();
/* 480 */         this.out.writeObject(activeGame);
/* 481 */         this.out.flush();
/*     */         
/* 483 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 17:
/* 488 */         log("17");
/*     */         
/* 490 */         gameID = Integer.parseInt(params[1]);
/*     */         
/* 492 */         aGame = (ActiveGame)this.in.readObject();
/* 493 */         activeGames = CentralServer.getActiveGames();
/* 494 */         activeGames[gameID] = aGame;
/*     */         
/* 496 */         CentralServer.setActiveGames(activeGames);
/*     */         
/* 498 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 18:
/* 503 */         log("18");
/*     */         
/* 505 */         userID = Integer.parseInt(params[1]);
/* 506 */         gameID = Integer.parseInt(params[2]);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 511 */         settings = CentralServer.getGameLobby()[gameID];
/*     */         
/* 513 */         if (settings.getHostID() == userID) {
/* 514 */           myTeam = settings.getP1Team();
/* 515 */         } else if (settings.getP2ID() == userID) {
/* 516 */           myTeam = settings.getP2Team();
/* 517 */         } else if (settings.getP3ID() == userID) {
/* 518 */           myTeam = settings.getP3Team();
/*     */         } else {
/* 520 */           myTeam = settings.getP4Team();
/*     */         } 
/*     */         
/* 523 */         savedGameID = this.db.createSavedGame(userID, myTeam);
/*     */         
/* 525 */         this.out.writeObject(new IntegerMsg(savedGameID));
/* 526 */         this.out.flush();
/*     */         
/* 528 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 19:
/* 533 */         log("19");
/*     */         
/* 535 */         sGID = Integer.parseInt(params[1]);
/* 536 */         t1Sc = Integer.parseInt(params[2]);
/* 537 */         t2Sc = Integer.parseInt(params[3]);
/*     */         
/* 539 */         this.db.updateSavedGame(sGID, t1Sc, t2Sc);
/*     */         
/* 541 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 20:
/* 546 */         log("20");
/*     */         
/* 548 */         clientID = Integer.parseInt(params[1]);
/* 549 */         SGID = Integer.parseInt(params[2]);
/* 550 */         username = params[3];
/* 551 */         gameID = Integer.parseInt(params[4]);
/*     */ 
/*     */         
/* 554 */         teamScores = this.db.getTeamScores(SGID);
/* 555 */         team = 0;
/*     */ 
/*     */         
/* 558 */         if (CentralServer.getGameLobby()[gameID].getHostID() == clientID) {
/* 559 */           team = CentralServer.getGameLobby()[gameID].getP1Team();
/* 560 */           CentralServer.getActiveGames()[gameID].setP1Connected(false);
/* 561 */         } else if (CentralServer.getGameLobby()[gameID].getP2ID() == clientID) {
/* 562 */           team = CentralServer.getGameLobby()[gameID].getP2Team();
/* 563 */           CentralServer.getActiveGames()[gameID].setP2Connected(false);
/* 564 */         } else if (CentralServer.getGameLobby()[gameID].getP3ID() == clientID) {
/* 565 */           team = CentralServer.getGameLobby()[gameID].getP3Team();
/* 566 */           CentralServer.getActiveGames()[gameID].setP3Connected(false);
/*     */         } else {
/* 568 */           team = CentralServer.getGameLobby()[gameID].getP4Team();
/* 569 */           CentralServer.getActiveGames()[gameID].setP4Connected(false);
/*     */         } 
/*     */         
/* 572 */         win = false;
/* 573 */         experience = 0;
/*     */         
/* 575 */         if (teamScores[0] > teamScores[1]) {
/* 576 */           if (team == 0) {
/* 577 */             win = true;
/* 578 */             experience = teamScores[0];
/*     */           } else {
/* 580 */             win = false;
/* 581 */             experience = teamScores[1];
/*     */           }
/*     */         
/* 584 */         } else if (team == 0) {
/* 585 */           win = false;
/* 586 */           experience = teamScores[0];
/*     */         } else {
/* 588 */           win = true;
/* 589 */           experience = teamScores[1];
/*     */         } 
/*     */ 
/*     */         
/* 593 */         this.db.updateGameStats(clientID, win, experience);
/*     */         
/* 595 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 21:
/* 600 */         log("21");
/*     */         
/* 602 */         gameID = Integer.parseInt(params[1]);
/*     */         
/* 604 */         gameSettings = (NewGame)this.in.readObject();
/*     */         
/* 606 */         nGameArray = CentralServer.getGameLobby();
/* 607 */         nGameArray[gameID] = gameSettings;
/* 608 */         CentralServer.setGameLobby(nGameArray);
/*     */         
/* 610 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 22:
/* 615 */         log("22");
/*     */         
/* 617 */         gameID = Integer.parseInt(params[1]);
/* 618 */         clientID = Integer.parseInt(params[2]);
/*     */         
/* 620 */         if (CentralServer.getGameLobby()[gameID].getHostID() == clientID) {
/* 621 */           CentralServer.getActiveGames()[gameID].setP1Continue(true);
/* 622 */         } else if (CentralServer.getGameLobby()[gameID].getP2ID() == clientID) {
/* 623 */           CentralServer.getActiveGames()[gameID].setP2Continue(true);
/* 624 */         } else if (CentralServer.getGameLobby()[gameID].getP3ID() == clientID) {
/* 625 */           CentralServer.getActiveGames()[gameID].setP3Continue(true);
/*     */         } else {
/* 627 */           team = CentralServer.getGameLobby()[gameID].getP4Team();
/* 628 */           CentralServer.getActiveGames()[gameID].setP4Continue(true);
/*     */         } 
/*     */         
/* 631 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 23:
/* 636 */         log("23");
/*     */ 
/*     */ 
/*     */         
/* 640 */         gType = Integer.parseInt(params[1]);
/* 641 */         gameID = Integer.parseInt(params[2]);
/*     */         
/* 643 */         aGame23 = CentralServer.getActiveGames()[gameID];
/*     */         
/* 645 */         if (aGame23.isP1Connected()) {
/* 646 */           if (aGame23.isP2Connected()) {
/*     */             
/* 648 */             if (gType == 0) {
/* 649 */               allConnected = true;
/*     */             }
/* 651 */             else if (aGame23.isP3Connected()) {
/* 652 */               if (aGame23.isP4Connected()) {
/* 653 */                 allConnected = true;
/*     */               } else {
/* 655 */                 allConnected = false;
/*     */               } 
/*     */             } else {
/* 658 */               allConnected = false;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 663 */             allConnected = false;
/*     */           } 
/*     */         } else {
/* 666 */           allConnected = false;
/*     */         } 
/*     */         
/* 669 */         this.out.writeObject(new StringMsg(Boolean.toString(allConnected)));
/*     */         
/* 671 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 24:
/* 676 */         log("24");
/* 677 */         allContinue = false;
/*     */         
/* 679 */         gType24 = Integer.parseInt(params[1]);
/* 680 */         System.out.println("GameType is " + gType24);
/* 681 */         gameID = Integer.parseInt(params[2]);
/* 682 */         System.out.println("GameID is " + gameID);
/*     */         
/* 684 */         aGame24 = CentralServer.getActiveGames()[gameID];
/* 685 */         System.out.println("Player1 = " + aGame24.isP1Continue());
/* 686 */         System.out.println("Player2 = " + aGame24.isP2Continue());
/*     */         
/* 688 */         if (aGame24.isP1Continue()) {
/* 689 */           if (aGame24.isP2Continue()) {
/*     */             
/* 691 */             if (gType24 == 0) {
/* 692 */               allContinue = true;
/*     */             }
/* 694 */             else if (aGame24.isP3Continue()) {
/* 695 */               if (aGame24.isP4Continue()) {
/* 696 */                 allContinue = true;
/*     */               } else {
/* 698 */                 allContinue = false;
/*     */               } 
/*     */             } else {
/* 701 */               allContinue = false;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 706 */             allContinue = false;
/*     */           } 
/*     */         } else {
/* 709 */           allContinue = false;
/*     */         } 
/*     */         
/* 712 */         System.out.println("result = " + allContinue);
/*     */         
/* 714 */         this.out.writeObject(new StringMsg(Boolean.toString(allContinue)));
/*     */         
/* 716 */         Arrays.fill((Object[])params, (Object)null);
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\server\ClientHandler.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */