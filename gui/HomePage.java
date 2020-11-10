/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
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
/*     */ import javafx.scene.control.cell.PropertyValueFactory;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.GridPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.scene.text.Text;
/*     */ import javafx.scene.text.TextAlignment;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
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
/*     */ public class HomePage
/*     */   extends Application
/*     */   implements HomePageInterface
/*     */ {
/*  43 */   private Font titleFont = new Font("Ariel", 50.0D);
/*  44 */   private Font headerFont = new Font("Ariel", 30.0D);
/*  45 */   private Font textFont = new Font("Ariel", 16.0D);
/*     */ 
/*     */   
/*     */   private BorderPane root;
/*     */ 
/*     */ 
/*     */   
/*     */   public HomePage() throws IOException, SQLException {
/*  53 */     this.root = createHomePage();
/*  54 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  55 */     String css = getClass().getResource("style.css").toExternalForm();
/*  56 */     scene.getStylesheets().add(css);
/*  57 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BorderPane createHomePage() throws IOException, SQLException {
/*  68 */     BorderPane bp = new BorderPane();
/*     */     
/*  70 */     Label loginSuccess = new Label("Welcome back \n" + ClientMain.client.getUsername() + "!");
/*  71 */     loginSuccess.setFont(this.titleFont);
/*  72 */     Label leadsershipBoard = new Label("Leaderboard");
/*  73 */     leadsershipBoard.setFont(this.headerFont);
/*  74 */     Label userStats = new Label(String.valueOf(ClientMain.client.getUsername()) + " Gaming Stats");
/*  75 */     userStats.setFont(this.headerFont);
/*     */     
/*  77 */     TableView<UserHighscore> tableLeaderboard = new TableView<>();
/*  78 */     tableLeaderboard.getItems().clear();
/*  79 */     tableLeaderboard.getColumns().clear();
/*  80 */     tableLeaderboard.setEditable(false);
/*  81 */     tableLeaderboard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
/*  82 */     tableLeaderboard.setMaxSize(Double.MAX_VALUE, 400.0D);
/*  83 */     tableLeaderboard.setMinSize(Double.MIN_VALUE, 400.0D);
/*     */     
/*  85 */     TableColumn<UserHighscore, Integer> score = new TableColumn<>("Experience");
/*  86 */     TableColumn<UserHighscore, String> username = new TableColumn<>("Username");
/*  87 */     score.setCellValueFactory(new PropertyValueFactory<>("Experience"));
/*  88 */     username.setCellValueFactory(new PropertyValueFactory<>("Username"));
/*  89 */     tableLeaderboard.getColumns().add(score);
/*  90 */     tableLeaderboard.getColumns().add(username);
/*  91 */     tableLeaderboard.setItems(ClientMain.client.getLeaderBoard());
/*     */     
/*  93 */     TableView<UserGameStats> userStatsTable = new TableView<>();
/*  94 */     userStatsTable.getItems().clear();
/*  95 */     userStatsTable.getColumns().clear();
/*  96 */     userStatsTable.setEditable(false);
/*  97 */     userStatsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
/*  98 */     userStatsTable.setMaxSize(Double.MAX_VALUE, 100.0D);
/*  99 */     userStatsTable.setMinSize(Double.MIN_VALUE, 80.0D);
/*     */     
/* 101 */     TableColumn<UserGameStats, Integer> noGamesPlayed = new TableColumn<>("Games Played");
/* 102 */     TableColumn<UserGameStats, Integer> wins = new TableColumn<>("Wins");
/* 103 */     TableColumn<UserGameStats, Integer> losses = new TableColumn<>("Losses");
/* 104 */     TableColumn<UserGameStats, Integer> totalScore = new TableColumn<>("Experience");
/* 105 */     noGamesPlayed.setCellValueFactory(new PropertyValueFactory<>("numberGamesPlayed"));
/* 106 */     wins.setCellValueFactory(new PropertyValueFactory<>("Wins"));
/* 107 */     losses.setCellValueFactory(new PropertyValueFactory<>("Losses"));
/* 108 */     totalScore.setCellValueFactory(new PropertyValueFactory<>("Experience"));
/* 109 */     userStatsTable.getColumns().add(noGamesPlayed);
/* 110 */     userStatsTable.getColumns().add(wins);
/* 111 */     userStatsTable.getColumns().add(losses);
/* 112 */     userStatsTable.getColumns().add(totalScore);
/* 113 */     userStatsTable.setItems(ClientMain.client.getUserStats());
/*     */     
/* 115 */     Button playerSettings = new Button("Settings");
/* 116 */     playerSettings.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 120 */     Button playCanasta = new Button("Play Canasta");
/* 121 */     playCanasta.setFont(this.titleFont);
/* 122 */     playCanasta.setMinSize(180.0D, 100.0D);
/* 123 */     playCanasta.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 127 */     Button rules = new Button("Rules");
/* 128 */     rules.setMinSize(50.0D, 100.0D);
/* 129 */     rules.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 133 */     Button logout = new Button("Logout");
/* 134 */     logout.setAlignment(Pos.CENTER);
/* 135 */     logout.setOnAction(action -> {
/*     */           
/*     */           try {
/*     */             ClientMain.client.logout();
/* 139 */           } catch (IOException e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         });
/*     */     
/* 144 */     Text welcomeMessage = new Text();
/* 145 */     welcomeMessage.setText("Are you ready to tackle the Canasta challenge? \nPlay more games to increase your experience and climb further up the leaderboard!");
/*     */     
/* 147 */     welcomeMessage.setWrappingWidth(300.0D);
/* 148 */     welcomeMessage.setFill(Color.WHITE);
/* 149 */     welcomeMessage.setTextAlignment(TextAlignment.LEFT);
/* 150 */     welcomeMessage.setFont(this.textFont);
/*     */ 
/*     */     
/* 153 */     GridPane gp = new GridPane();
/* 154 */     gp.add(welcomeMessage, 0, 1, 2, 1);
/* 155 */     gp.add(rules, 0, 2, 1, 1);
/* 156 */     gp.add(playCanasta, 1, 2, 1, 1);
/* 157 */     gp.add(userStats, 0, 3, 2, 1);
/* 158 */     gp.add(userStatsTable, 0, 4, 2, 1);
/* 159 */     gp.setAlignment(Pos.CENTER);
/* 160 */     gp.setVgap(40.0D);
/* 161 */     gp.setHgap(10.0D);
/* 162 */     gp.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
/*     */     
/* 164 */     VBox leftVB = new VBox();
/* 165 */     leftVB.setSpacing(20.0D);
/* 166 */     leftVB.setAlignment(Pos.CENTER_LEFT);
/* 167 */     leftVB.getChildren().add(gp);
/*     */     
/* 169 */     VBox rightVB = new VBox();
/* 170 */     rightVB.setSpacing(20.0D);
/* 171 */     rightVB.setAlignment(Pos.CENTER_RIGHT);
/* 172 */     rightVB.getChildren().addAll(new Node[] { leadsershipBoard, tableLeaderboard });
/*     */     
/* 174 */     HBox dashboardHB = new HBox(new Node[] { leftVB, rightVB });
/* 175 */     dashboardHB.setAlignment(Pos.CENTER);
/* 176 */     dashboardHB.setSpacing(20.0D);
/*     */     
/* 178 */     HBox topHB = new HBox(new Node[] { loginSuccess, playerSettings, logout });
/* 179 */     topHB.setSpacing(30.0D);
/* 180 */     topHB.setAlignment(Pos.CENTER);
/*     */     
/* 182 */     VBox dashBoardVB = new VBox(new Node[] { topHB, dashboardHB });
/* 183 */     dashBoardVB.setAlignment(Pos.TOP_CENTER);
/*     */     
/* 185 */     bp.setCenter(dashBoardVB);
/*     */     
/* 187 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 195 */     Stage window = stage;
/* 196 */     BorderPane bp = createHomePage();
/* 197 */     bp.setBottom(null);
/* 198 */     window.setTitle("Canasta App");
/* 199 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 200 */     window.setScene(scene);
/* 201 */     String css = getClass().getResource("style.css").toExternalForm();
/* 202 */     scene.getStylesheets().add(css);
/* 203 */     window.show();
/* 204 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 206 */             System.out.println("Stage is closing");
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
/*     */   
/*     */   public static void main(String[] args) throws IOException, SQLException {
/* 221 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\HomePage.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */