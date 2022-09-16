/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ChoiceBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.layout.Background;
/*     */ import javafx.scene.layout.BackgroundFill;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.Priority;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ import sample.NewGame;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewGamePage
/*     */   extends Application
/*     */   implements NewGameInterface
/*     */ {
/*  38 */   private Font titleFont = new Font("Ariel", 50.0D);
/*  39 */   private Font headerFont = new Font("Ariel", 30.0D);
/*  40 */   private Font textFont = new Font("Ariel", 16.0D);
/*  41 */   private Insets margin = new Insets(15.0D, 15.0D, 15.0D, 15.0D);
/*     */   
/*     */   private String gameTypeLabel;
/*     */   private int gameType;
/*     */   private int goalScore;
/*     */   private BorderPane root;
/*     */   private NewGame currentGLobby;
/*     */   private Scene scene;
/*     */   private TextArea transcript;
/*     */   private boolean run = true;
/*     */   
/*     */   public NewGamePage(String gameTyp, String points, int teamAllocation) throws IOException {
/*  53 */     this.gameTypeLabel = gameTyp;
/*     */     
/*  55 */     if (gameTyp.equals("1V1 - 2 players")) {
/*  56 */       this.gameType = 0;
/*     */     } else {
/*  58 */       this.gameType = 1;
/*     */     } 
/*     */     
/*  61 */     if (points.equals("5000pts")) {
/*  62 */       this.goalScore = 5000;
/*  63 */     } else if (points.equals("3000pts")) {
/*  64 */       this.goalScore = 3000;
/*     */     } else {
/*  66 */       this.goalScore = 1500;
/*     */     } 
/*     */     
/*  69 */     ClientMain.client.addNewGame(this.gameType, this.goalScore, teamAllocation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NewGamePage() throws IOException {
/*  76 */     this.currentGLobby = ClientMain.client.getCurrentGLobby();
/*  77 */     this.gameType = this.currentGLobby.getGameType();
/*     */     
/*  79 */     if (this.gameType == 0) {
/*  80 */       this.gameTypeLabel = "1V1 - 2 players";
/*     */     } else {
/*  82 */       this.gameTypeLabel = "2V2 - 4 players";
/*     */     } 
/*     */     
/*  85 */     this.goalScore = this.currentGLobby.getGoalScore();
/*     */     
/*  87 */     this.root = createNewGame();
/*  88 */     this.scene = new Scene(this.root, 1000.0D, 725.0D);
/*  89 */     String css = getClass().getResource("style.css").toExternalForm();
/*  90 */     this.scene.getStylesheets().add(css);
/*  91 */     ClientMain.window.setScene(this.scene);
/*     */ 
/*     */     
/*  94 */     Thread thread = new Thread(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  98 */             Runnable updater = new Runnable()
/*     */               {
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void run()
/*     */                 {
/* 105 */                   if (!(NewGamePage.null.access$0(NewGamePage.null.this)).currentGLobby.isGameStarted()) {
/*     */                     
/*     */                     try {
/* 108 */                       (NewGamePage.null.access$0(NewGamePage.null.this)).currentGLobby = ClientMain.client.getCurrentGLobby();
/* 109 */                     } catch (IOException e) {
/* 110 */                       e.printStackTrace();
/*     */                     } 
/*     */                     
/* 113 */                     (NewGamePage.null.access$0(NewGamePage.null.this)).root = NewGamePage.null.access$0(NewGamePage.null.this).createNewGame();
/* 114 */                     (NewGamePage.null.access$0(NewGamePage.null.this)).scene = new Scene((NewGamePage.null.access$0(NewGamePage.null.this)).root, 1000.0D, 725.0D);
/* 115 */                     String css = getClass().getResource("style.css").toExternalForm();
/* 116 */                     (NewGamePage.null.access$0(NewGamePage.null.this)).scene.getStylesheets().add(css);
/* 117 */                     ClientMain.window.setScene((NewGamePage.null.access$0(NewGamePage.null.this)).scene);
/*     */                   } 
/*     */ 
/*     */                   
/* 121 */                   if ((NewGamePage.null.access$0(NewGamePage.null.this)).currentGLobby.isGameStarted()) {
/*     */                     try {
/* 123 */                       ClientMain.client.addSavedGameEntry();
/* 124 */                     } catch (IOException e) {
/* 125 */                       e.printStackTrace();
/*     */                     } 
/* 127 */                     (NewGamePage.null.access$0(NewGamePage.null.this)).run = false;
/*     */                   } 
/*     */                 }
/*     */               };
/*     */ 
/*     */ 
/*     */             
/*     */             while (true) {
/*     */               try {
/* 136 */                 Thread.sleep(8000L);
/* 137 */               } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */               
/* 141 */               if (!NewGamePage.this.run) {
/*     */                 break;
/*     */               }
/* 144 */               Platform.runLater(updater);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 151 */     thread.setDaemon(true);
/* 152 */     thread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BorderPane createNewGame() {
/* 158 */     BorderPane bp = new BorderPane();
/*     */     
/* 160 */     bp.setBottom(null);
/*     */     
/* 162 */     Label lobby = new Label("New Game");
/* 163 */     lobby.setFont(this.titleFont);
/* 164 */     lobby.setAlignment(Pos.CENTER);
/*     */ 
/*     */     
/* 167 */     Label GamesLabel = new Label("Wait for players to join and click\n  ready up when you are ready!");
/* 168 */     GamesLabel.setFont(this.headerFont);
/* 169 */     GamesLabel.setAlignment(Pos.CENTER);
/*     */ 
/*     */     
/* 172 */     Button updateLobbyplayers = new Button("Refresh Lobby");
/* 173 */     updateLobbyplayers.setOnAction(action -> {
/*     */         
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     HBox gameButtons = new HBox();
/* 183 */     gameButtons.setSpacing(20.0D);
/* 184 */     gameButtons.setAlignment(Pos.CENTER);
/*     */     
/* 186 */     if (!this.currentGLobby.getHost().equals(ClientMain.client.getUsername())) {
/* 187 */       Button readyUp = new Button("Ready up!");
/*     */       
/* 189 */       if (this.currentGLobby.isP2Ready() && 
/* 190 */         this.currentGLobby.getPlayer2().equals(ClientMain.client.getUsername())) {
/* 191 */         readyUp.setDisable(true);
/* 192 */       } else if (this.currentGLobby.isP3Ready() && 
/* 193 */         this.currentGLobby.getPlayer3().equals(ClientMain.client.getUsername())) {
/* 194 */         readyUp.setDisable(true);
/* 195 */       } else if (this.currentGLobby.isP4Ready() && 
/* 196 */         this.currentGLobby.getPlayer4().equals(ClientMain.client.getUsername())) {
/* 197 */         readyUp.setDisable(true);
/*     */       } 
/*     */       
/* 200 */       readyUp.setAlignment(Pos.CENTER);
/* 201 */       readyUp.setOnAction(action -> {
/*     */             
/*     */             try {
/*     */               ClientMain.client.readyUp();
/* 205 */             } catch (IOException e1) {
/*     */               e1.printStackTrace();
/*     */             } 
/*     */             
/*     */             paramButton.setDisable(true);
/*     */           });
/*     */       
/* 212 */       gameButtons.getChildren().add(readyUp);
/*     */     }
/*     */     else {
/*     */       
/* 216 */       Button startGame = new Button("Start Game");
/* 217 */       startGame.setDisable(true);
/* 218 */       startGame.setAlignment(Pos.CENTER);
/* 219 */       startGame.setOnAction(action -> {
/*     */             try {
/*     */               this.run = false;
/*     */               
/*     */               ClientMain.client.startGame();
/*     */               
/*     */               ClientMain.client.addSavedGameEntry();
/* 226 */             } catch (IOException e1) {
/*     */               e1.printStackTrace();
/*     */             } 
/*     */           });
/*     */       
/* 231 */       gameButtons.getChildren().add(startGame);
/*     */       
/* 233 */       if (this.currentGLobby.getGameType() == 0) {
/* 234 */         if (this.currentGLobby.isP2Ready()) {
/* 235 */           startGame.setDisable(false);
/*     */         }
/*     */       }
/* 238 */       else if (this.currentGLobby.isP2Ready() && this.currentGLobby.isP3Ready() && this.currentGLobby.isP4Ready()) {
/* 239 */         startGame.setDisable(false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 245 */     TextField messageInput = new TextField();
/*     */     
/* 247 */     Button sendButton = new Button();
/* 248 */     Button quitButton = new Button();
/*     */     
/* 250 */     this.transcript = new TextArea();
/*     */     
/* 252 */     this.transcript = new TextArea();
/* 253 */     this.transcript.setPrefRowCount(30);
/* 254 */     this.transcript.setPrefColumnCount(60);
/* 255 */     this.transcript.setWrapText(true);
/* 256 */     this.transcript.setEditable(false);
/* 257 */     this.transcript.setMaxHeight(300.0D);
/*     */     
/* 259 */     for (int i = 0; i < this.currentGLobby.getSize(); i++) {
/* 260 */       this.transcript.appendText(this.currentGLobby.getComment(i));
/*     */     }
/*     */     
/* 263 */     sendButton.setText("send");
/* 264 */     quitButton.setText("quit");
/*     */     
/* 266 */     messageInput.setPrefColumnCount(40);
/*     */     
/* 268 */     sendButton.setOnAction(e -> {
/*     */           try {
/*     */             this.transcript.appendText(String.valueOf(ClientMain.client.getUsername()) + ": " + paramTextField.getText() + "\n");
/*     */             ClientMain.client.doSend(String.valueOf(ClientMain.client.getUsername()) + ": " + paramTextField.getText() + "\n");
/* 272 */           } catch (IOException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */           paramTextField.setText((String)null);
/*     */         });
/* 277 */     quitButton.setOnAction(e -> {
/*     */         
/*     */         });
/*     */     
/* 281 */     sendButton.setDefaultButton(true);
/* 282 */     messageInput.setMaxWidth(400.0D);
/*     */ 
/*     */     
/* 285 */     Label username1 = new Label("Host: " + this.currentGLobby.getHost());
/* 286 */     username1.setFont(this.textFont);
/* 287 */     VBox usernames = new VBox(new Node[] { username1 });
/*     */     
/* 289 */     if (this.currentGLobby.getPlayer2() != null) {
/*     */       Label username2;
/* 291 */       if (this.currentGLobby.isP2Ready()) {
/* 292 */         username2 = new Label("(Ready) " + this.currentGLobby.getPlayer2());
/*     */       } else {
/* 294 */         username2 = new Label("(Not ready) " + this.currentGLobby.getPlayer2());
/*     */       } 
/* 296 */       username2.setFont(this.textFont);
/* 297 */       usernames.getChildren().add(username2);
/*     */     } 
/*     */     
/* 300 */     if (this.currentGLobby.getPlayer3() != null) {
/*     */       Label username3;
/* 302 */       if (this.currentGLobby.isP3Ready()) {
/* 303 */         username3 = new Label("(Ready) " + this.currentGLobby.getPlayer3());
/*     */       } else {
/* 305 */         username3 = new Label("(Not ready) " + this.currentGLobby.getPlayer3());
/*     */       } 
/* 307 */       username3.setFont(this.textFont);
/* 308 */       usernames.getChildren().add(username3);
/*     */     } 
/*     */     
/* 311 */     if (this.currentGLobby.getPlayer4() != null) {
/*     */       Label username4;
/* 313 */       if (this.currentGLobby.isP4Ready()) {
/* 314 */         username4 = new Label("(Ready) " + this.currentGLobby.getPlayer4());
/*     */       } else {
/* 316 */         username4 = new Label("(Not ready) " + this.currentGLobby.getPlayer4());
/*     */       } 
/*     */       
/* 319 */       username4.setFont(this.textFont);
/* 320 */       usernames.getChildren().add(username4);
/*     */     } 
/*     */     
/* 323 */     usernames.setAlignment(Pos.CENTER);
/* 324 */     usernames.setSpacing(10.0D);
/* 325 */     usernames.setStyle("-fx-border-color: black; -fx-border-width:2px");
/* 326 */     usernames.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(Color.DIMGRAY, null, null) }));
/*     */     
/* 328 */     Label setting1 = new Label("Game Type: " + this.gameTypeLabel);
/* 329 */     setting1.setFont(this.textFont);
/* 330 */     Label setting2 = new Label("Points Goal: " + this.goalScore + "pts");
/* 331 */     setting2.setFont(this.textFont);
/* 332 */     VBox settings = new VBox(new Node[] { setting1, setting2 });
/* 333 */     settings.setAlignment(Pos.CENTER);
/* 334 */     settings.setSpacing(10.0D);
/* 335 */     settings.setStyle("-fx-border-color: black; -fx-border-width:2px");
/* 336 */     settings.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(Color.DIMGRAY, null, null) }));
/*     */     
/* 338 */     if (this.currentGLobby.getTeamAllocation() == 0) {
/* 339 */       Label setting3 = new Label("Teams: default");
/* 340 */       setting3.setFont(this.textFont);
/* 341 */       settings.getChildren().add(setting3);
/* 342 */     } else if (this.currentGLobby.getTeamAllocation() == 1) {
/* 343 */       Label setting4 = new Label("Teams: random");
/* 344 */       setting4.setFont(this.textFont);
/* 345 */       settings.getChildren().add(setting4);
/*     */     } else {
/*     */       
/* 348 */       String teams = this.currentGLobby.getHost();
/* 349 */       if (this.currentGLobby.getP1Team() == -1) {
/* 350 */         teams = String.valueOf(teams) + " (pick a team)";
/* 351 */       } else if (this.currentGLobby.getP1Team() == 0) {
/* 352 */         teams = String.valueOf(teams) + " (team 1)";
/*     */       } else {
/* 354 */         teams = String.valueOf(teams) + " (team 2)";
/*     */       } 
/*     */       
/* 357 */       if (this.currentGLobby.getPlayer2() != null) {
/* 358 */         teams = String.valueOf(teams) + "\n" + this.currentGLobby.getPlayer2();
/* 359 */         if (this.currentGLobby.getP2Team() == -1) {
/* 360 */           teams = String.valueOf(teams) + " (pick a team)";
/* 361 */         } else if (this.currentGLobby.getP2Team() == 0) {
/* 362 */           teams = String.valueOf(teams) + " (team 1)";
/*     */         } else {
/* 364 */           teams = String.valueOf(teams) + " (team 2)";
/*     */         } 
/*     */       } 
/*     */       
/* 368 */       if (this.currentGLobby.getPlayer3() != null) {
/* 369 */         teams = String.valueOf(teams) + "\n" + this.currentGLobby.getPlayer3();
/* 370 */         if (this.currentGLobby.getP3Team() == -1) {
/* 371 */           teams = String.valueOf(teams) + " (pick a team)";
/* 372 */         } else if (this.currentGLobby.getP3Team() == 0) {
/* 373 */           teams = String.valueOf(teams) + " (team 1)";
/*     */         } else {
/* 375 */           teams = String.valueOf(teams) + " (team 2)";
/*     */         } 
/*     */       } 
/*     */       
/* 379 */       if (this.currentGLobby.getPlayer4() != null) {
/* 380 */         teams = String.valueOf(teams) + "\n" + this.currentGLobby.getPlayer4();
/* 381 */         if (this.currentGLobby.getP4Team() == -1) {
/* 382 */           teams = String.valueOf(teams) + " (pick a team)";
/* 383 */         } else if (this.currentGLobby.getP4Team() == 0) {
/* 384 */           teams = String.valueOf(teams) + " (team 1)";
/*     */         } else {
/* 386 */           teams = String.valueOf(teams) + " (team 2)";
/*     */         } 
/*     */       } 
/*     */       
/* 390 */       Label setting5 = new Label("Teams: choose\n\n" + 
/* 391 */           teams);
/* 392 */       setting5.setFont(this.textFont);
/* 393 */       settings.getChildren().add(setting5);
/*     */       
/* 395 */       Label teamChoice = new Label("Choose team");
/* 396 */       teamChoice.setFont(this.textFont);
/*     */       
/* 398 */       ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
/* 399 */       teamChoiceBox.getItems().add("team 1");
/* 400 */       teamChoiceBox.getItems().add("team 2");
/* 401 */       settings.getChildren().add(teamChoice);
/* 402 */       settings.getChildren().add(teamChoiceBox);
/*     */     } 
/*     */ 
/*     */     
/* 406 */     HBox top = new HBox(new Node[] { lobby });
/* 407 */     top.setAlignment(Pos.TOP_LEFT);
/*     */     
/* 409 */     HBox bottom = new HBox(8.0D, new Node[] { messageInput, sendButton, quitButton });
/* 410 */     HBox.setHgrow(messageInput, Priority.ALWAYS);
/* 411 */     HBox.setMargin(quitButton, new Insets(0.0D, 0.0D, 0.0D, 50.0D));
/* 412 */     bottom.setPadding(new Insets(8.0D));
/* 413 */     bottom.setStyle("-fx-border-color: black; -fx-border-width:2px");
/*     */     
/* 415 */     VBox games = new VBox(new Node[] { gameButtons });
/* 416 */     games.setSpacing(10.0D);
/*     */     
/* 418 */     HBox onlinePlayers = new HBox(new Node[] { updateLobbyplayers });
/* 419 */     onlinePlayers.setAlignment(Pos.CENTER);
/* 420 */     onlinePlayers.setSpacing(20.0D);
/*     */     
/* 422 */     VBox rightVB = new VBox();
/* 423 */     rightVB.getChildren().addAll(new Node[] { GamesLabel, onlinePlayers, this.transcript, bottom });
/*     */     
/* 425 */     rightVB.setPadding(this.margin);
/* 426 */     rightVB.setSpacing(20.0D);
/* 427 */     rightVB.setAlignment(Pos.CENTER);
/* 428 */     rightVB.setMaxWidth(640.0D);
/*     */     
/* 430 */     VBox centerVB = new VBox();
/* 431 */     centerVB.getChildren().addAll(new Node[] { usernames, settings, games });
/*     */     
/* 433 */     centerVB.setPadding(this.margin);
/* 434 */     centerVB.setSpacing(50.0D);
/* 435 */     centerVB.setAlignment(Pos.CENTER);
/*     */     
/* 437 */     bp.setTop(top);
/* 438 */     bp.setCenter(centerVB);
/* 439 */     bp.setRight(rightVB);
/*     */ 
/*     */     
/* 442 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 450 */     Stage window = stage;
/* 451 */     BorderPane bp = createNewGame();
/* 452 */     bp.setBottom(null);
/* 453 */     window.setTitle("Canasta App");
/* 454 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 455 */     window.setScene(scene);
/*     */     
/* 457 */     String css = getClass().getResource("style.css").toExternalForm();
/* 458 */     scene.getStylesheets().add(css);
/* 459 */     window.show();
/* 460 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 462 */             System.out.println("Stage is closing");
/*     */           }
/*     */         });
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
/*     */   public static void main(String[] args) throws IOException, SQLException {
/* 476 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\NewGamePage.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */