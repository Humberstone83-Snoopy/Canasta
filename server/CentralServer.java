/*     */ package server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import sample.ActiveGame;
/*     */ import sample.NewGame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CentralServer
/*     */   implements ServerInterface
/*     */ {
/*  22 */   protected int port = 25565;
/*     */   
/*     */   protected Vector<ClientHandler> listOfClients;
/*     */   
/*     */   protected Vector<Socket> listOfSockets;
/*     */   
/*     */   private ServerSocket serverSocket;
/*     */   
/*     */   private static List<String> lobbyTranscript;
/*  31 */   private static NewGame[] gameLobby = new NewGame[100];
/*  32 */   private static ActiveGame[] activeGames = new ActiveGame[100];
/*     */ 
/*     */ 
/*     */   
/*     */   private static int newGameCounter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CentralServer() throws IOException {
/*  42 */     this.serverSocket = new ServerSocket(this.port);
/*  43 */     this.listOfClients = new Vector<>();
/*  44 */     this.listOfSockets = new Vector<>();
/*  45 */     lobbyTranscript = Collections.synchronizedList(new ArrayList<>());
/*  46 */     newGameCounter = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNewGameCounter() {
/*  53 */     return newGameCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setNewGameCounter(int newGameCounter) {
/*  60 */     CentralServer.newGameCounter = newGameCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void acceptClients() throws IOException {
/*  69 */     int clientNumber = 0;
/*     */ 
/*     */     
/*  72 */     ExecutorService threadPool = Executors.newCachedThreadPool();
/*     */     try {
/*  74 */       while (!Thread.currentThread().isInterrupted()) {
/*     */ 
/*     */         
/*  77 */         log("Up and listening on port " + this.serverSocket.getLocalPort() + "...");
/*  78 */         Socket clientSocket = this.serverSocket.accept();
/*  79 */         log("Just connected to " + clientSocket.getRemoteSocketAddress());
/*  80 */         this.listOfSockets.add(clientSocket);
/*     */ 
/*     */         
/*  83 */         ClientHandler client = new ClientHandler(clientSocket, clientNumber++);
/*  84 */         this.listOfClients.add(client);
/*  85 */         threadPool.submit(client);
/*     */       } 
/*     */     } finally {
/*     */       
/*  89 */       threadPool.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(String message) {
/*  98 */     System.out.println(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws IOException {
/* 106 */     this.serverSocket.close();
/*     */   }
/*     */   
/*     */   public static void addComment(String comment) {
/* 110 */     lobbyTranscript.add(comment);
/*     */   }
/*     */   
/*     */   public static int getSize() {
/* 114 */     return lobbyTranscript.size();
/*     */   }
/*     */   
/*     */   public static String getComment(int n) {
/* 118 */     return lobbyTranscript.get(n);
/*     */   }
/*     */   
/*     */   public static NewGame[] getGameLobby() {
/* 122 */     return gameLobby;
/*     */   }
/*     */   
/*     */   public static void setGameLobby(NewGame[] newGameLobby) {
/* 126 */     gameLobby = newGameLobby;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void startGame(int gameID) {
/* 131 */     activeGames[gameID] = new ActiveGame(gameID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ActiveGame[] getActiveGames() {
/* 139 */     return activeGames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setActiveGames(ActiveGame[] activeGames) {
/* 146 */     CentralServer.activeGames = activeGames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws SQLException, IOException {
/* 156 */     (new CentralServer()).acceptClients();
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\server\CentralServer.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */