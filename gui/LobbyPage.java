/*     */ package gui;
/*     */ import client.ClientMain;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import javafx.application.Application;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TableColumn;
/*     */ import javafx.scene.control.TableView;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.control.cell.PropertyValueFactory;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.Priority;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ import sample.NewGame;
/*     */ import sample.UserGameStats;
/*     */ 
/*     */ public class LobbyPage extends Application implements LobbyPageInterface {
/*  35 */   private Font titleFont = new Font("Ariel", 50.0D);
/*  36 */   private Font headerFont = new Font("Ariel", 30.0D);
/*  37 */   private Insets margin = new Insets(15.0D, 15.0D, 15.0D, 15.0D);
/*     */   private BorderPane root;
/*     */   private Timer timer;
/*     */   private TextArea transcript;
/*     */   
/*     */   public LobbyPage() {
/*  43 */     this.root = createLobby();
/*  44 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  45 */     String css = getClass().getResource("style.css").toExternalForm();
/*  46 */     scene.getStylesheets().add(css);
/*  47 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BorderPane createLobby() {
/*  53 */     BorderPane bp = new BorderPane();
/*     */     
/*  55 */     bp.setBottom(null);
/*     */     
/*  57 */     Label lobby = new Label("Game Lobby");
/*  58 */     lobby.setFont(this.titleFont);
/*  59 */     lobby.setAlignment(Pos.CENTER);
/*     */     
/*  61 */     TableView<UserGameStats> currentPlayersInLobbyTable = new TableView<>();
/*  62 */     currentPlayersInLobbyTable.getItems().clear();
/*  63 */     currentPlayersInLobbyTable.getColumns().clear();
/*  64 */     currentPlayersInLobbyTable.setEditable(false);
/*  65 */     currentPlayersInLobbyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
/*  66 */     currentPlayersInLobbyTable.setMaxSize(300.0D, 600.0D);
/*     */     
/*  68 */     TableColumn<UserGameStats, String> username = new TableColumn<>("Online Players");
/*  69 */     username.setCellValueFactory(new PropertyValueFactory<>("username"));
/*  70 */     currentPlayersInLobbyTable.getColumns().add(username);
/*     */     
/*     */     try {
/*  73 */       currentPlayersInLobbyTable.setItems(ClientMain.client.getPlayersInLobby());
/*  74 */     } catch (IOException e1) {
/*  75 */       e1.printStackTrace();
/*     */     } 
/*     */     
/*  78 */     TableView<NewGame> GameLobbyTable = new TableView<>();
/*  79 */     GameLobbyTable.getItems().clear();
/*  80 */     GameLobbyTable.getColumns().clear();
/*  81 */     GameLobbyTable.setEditable(false);
/*  82 */     GameLobbyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
/*  83 */     GameLobbyTable.setMaxSize(300.0D, 600.0D);
/*     */     
/*  85 */     TableColumn<NewGame, String> usernames = new TableColumn<>("Host");
/*  86 */     TableColumn<NewGame, String> players2 = new TableColumn<>("Players");
/*  87 */     TableColumn<NewGame, Integer> maxScore2 = new TableColumn<>("Max_Score");
/*  88 */     usernames.setCellValueFactory(new PropertyValueFactory<>("host"));
/*  89 */     players2.setCellValueFactory(new PropertyValueFactory<>("players"));
/*  90 */     maxScore2.setCellValueFactory(new PropertyValueFactory<>("goalScore"));
/*  91 */     GameLobbyTable.getColumns().add(usernames);
/*  92 */     GameLobbyTable.getColumns().add(players2);
/*  93 */     GameLobbyTable.getColumns().add(maxScore2);
/*     */     try {
/*  95 */       GameLobbyTable.setItems(ClientMain.client.getGamesInLobby());
/*  96 */     } catch (IOException e2) {
/*  97 */       e2.printStackTrace();
/*     */     } 
/*     */     
/* 100 */     AtomicReference<Boolean> selected = new AtomicReference<>(Boolean.valueOf(false));
/* 101 */     String[] userName = new String[1];
/* 102 */     GameLobbyTable.setOnMouseClicked(event -> {
/*     */           paramAtomicReference.set(Boolean.valueOf(true));
/*     */           
/*     */           NewGame ugs = paramTableView.getSelectionModel().getSelectedItem();
/*     */           paramArrayOfString[0] = ugs.getHost();
/*     */         });
/* 108 */     Label GamesLabel = new Label("Choose a game\n    to join!");
/* 109 */     GamesLabel.setFont(this.headerFont);
/* 110 */     GamesLabel.setAlignment(Pos.CENTER);
/*     */     
/* 112 */     Button updateLobbyplayers = new Button("Refresh Lobby");
/* 113 */     updateLobbyplayers.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 117 */     Button createGame = new Button("CreateGame");
/* 118 */     createGame.setAlignment(Pos.CENTER);
/* 119 */     createGame.setOnAction(action -> this.timer.cancel());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     Button joinGame = new Button("Join Game!");
/* 126 */     joinGame.setAlignment(Pos.CENTER);
/* 127 */     joinGame.setOnAction(action -> {
/*     */           this.timer.cancel();
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/*     */             boolean joined = ClientMain.client.joinGame(paramArrayOfString[0]);
/*     */ 
/*     */             
/*     */             if (joined);
/* 137 */           } catch (IOException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */         });
/* 141 */     TextField messageInput = new TextField();
/* 142 */     messageInput.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
/*     */     
/* 144 */     this.transcript = new TextArea();
/* 145 */     Button sendButton = new Button();
/* 146 */     Button quitButton = new Button();
/*     */     
/* 148 */     this.transcript = new TextArea();
/* 149 */     this.transcript.setPrefRowCount(30);
/* 150 */     this.transcript.setPrefColumnCount(60);
/* 151 */     this.transcript.setWrapText(true);
/* 152 */     this.transcript.setEditable(false);
/* 153 */     this.transcript.setMaxHeight(300.0D);
/*     */     
/* 155 */     this.timer = new Timer();
/* 156 */     this.timer.scheduleAtFixedRate(new TimerTask()
/*     */         {
/*     */           public void run()
/*     */           {
/* 160 */             String toCount = LobbyPage.this.transcript.getText();
/* 161 */             String[] lineArray = toCount.split("\n");
/* 162 */             int transcriptSize = lineArray.length - 1;
/*     */             try {
/* 164 */               String newMessages = ClientMain.client.retrieveLobbyMessages(transcriptSize);
/* 165 */               if (newMessages != null) {
/* 166 */                 String[] messages = newMessages.split("~");
/* 167 */                 for (int i = 0; i < messages.length; i++) {
/* 168 */                   LobbyPage.this.transcript.appendText(String.valueOf(messages[i]) + "\n");
/*     */                 }
/*     */               } 
/* 171 */             } catch (IOException e) {
/*     */               
/* 173 */               e.printStackTrace();
/*     */ 
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 179 */         }0L, 2500L);
/*     */ 
/*     */     
/* 182 */     quitButton.setText("quit");
/*     */     
/* 184 */     sendButton.setText("send");
/* 185 */     sendButton.setDefaultButton(true);
/*     */     
/* 187 */     messageInput.setMaxWidth(400.0D);
/*     */     
/* 189 */     sendButton.setOnAction(e -> {
/*     */           doSend(paramTextField.getText());
/*     */           
/*     */           paramTextField.setText((String)null);
/*     */         });
/* 194 */     quitButton.setOnAction(e -> {
/*     */           this.timer.cancel();
/*     */           try {
/*     */           
/* 198 */           } catch (IOException|SQLException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */         });
/*     */     
/* 203 */     HBox top = new HBox(new Node[] { lobby });
/* 204 */     top.setAlignment(Pos.TOP_LEFT);
/*     */     
/* 206 */     HBox bottom = new HBox(8.0D, new Node[] { messageInput, sendButton, quitButton });
/* 207 */     HBox.setHgrow(messageInput, Priority.ALWAYS);
/* 208 */     HBox.setMargin(quitButton, new Insets(0.0D, 0.0D, 0.0D, 50.0D));
/* 209 */     bottom.setPadding(new Insets(8.0D));
/* 210 */     bottom.setStyle("-fx-border-color: black; -fx-border-width:2px");
/*     */     
/* 212 */     HBox gameButtons = new HBox(new Node[] { joinGame, createGame });
/* 213 */     gameButtons.setSpacing(20.0D);
/* 214 */     gameButtons.setAlignment(Pos.CENTER);
/*     */     
/* 216 */     VBox games = new VBox(new Node[] { GameLobbyTable, gameButtons });
/* 217 */     games.setSpacing(10.0D);
/*     */     
/* 219 */     HBox onlinePlayers = new HBox(new Node[] { updateLobbyplayers, currentPlayersInLobbyTable });
/* 220 */     onlinePlayers.setAlignment(Pos.CENTER_RIGHT);
/* 221 */     onlinePlayers.setSpacing(20.0D);
/*     */     
/* 223 */     VBox rightVB = new VBox();
/* 224 */     rightVB.getChildren().addAll(new Node[] { top, onlinePlayers, this.transcript, bottom });
/* 225 */     rightVB.setPadding(this.margin);
/* 226 */     rightVB.setSpacing(5.0D);
/* 227 */     rightVB.setAlignment(Pos.CENTER);
/* 228 */     rightVB.setMaxWidth(640.0D);
/*     */     
/* 230 */     VBox centerVB = new VBox();
/* 231 */     centerVB.getChildren().addAll(new Node[] { GamesLabel, games });
/* 232 */     centerVB.setPadding(this.margin);
/* 233 */     centerVB.setSpacing(20.0D);
/* 234 */     centerVB.setAlignment(Pos.CENTER);
/*     */     
/* 236 */     bp.setCenter(centerVB);
/* 237 */     bp.setRight(rightVB);
/*     */     
/* 239 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void doSend(String message) {
/*     */     try {
/* 246 */       ClientMain.client.sendLobbyMessage(message);
/* 247 */     } catch (IOException e) {
/*     */       
/* 249 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 258 */     Stage window = stage;
/* 259 */     BorderPane bp = createLobby();
/* 260 */     bp.setBottom(null);
/* 261 */     window.setTitle("Canasta App");
/* 262 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 263 */     window.setScene(scene);
/*     */     
/* 265 */     String css = getClass().getResource("style.css").toExternalForm();
/* 266 */     scene.getStylesheets().add(css);
/* 267 */     window.show();
/* 268 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 270 */             System.out.println("Stage is closing");
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
/* 284 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\LobbyPage.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */