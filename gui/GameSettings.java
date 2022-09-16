/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
/*     */ import javafx.application.Application;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ChoiceBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ 
/*     */ public class GameSettings
/*     */   extends Application
/*     */   implements GameSettingsInterface
/*     */ {
/*  28 */   private Font titleFont = new Font("Ariel", 50.0D);
/*  29 */   private Font textFont = new Font("Ariel", 16.0D);
/*     */   private BorderPane root;
/*     */   
/*     */   public GameSettings() {
/*  33 */     this.root = createGameSettings();
/*  34 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  35 */     String css = getClass().getResource("style.css").toExternalForm();
/*  36 */     scene.getStylesheets().add(css);
/*  37 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */   
/*     */   public BorderPane createGameSettings() {
/*  42 */     BorderPane bp = new BorderPane();
/*     */     
/*  44 */     Label title = new Label("Game Settings");
/*  45 */     title.setFont(this.titleFont);
/*  46 */     title.setAlignment(Pos.CENTER);
/*     */     
/*  48 */     Label gameType = new Label("GameType: 1V1 | 2V2");
/*  49 */     gameType.setFont(this.textFont);
/*     */     
/*  51 */     ChoiceBox<String> gameTypeBox = new ChoiceBox<>();
/*  52 */     gameTypeBox.getItems().add("1V1 - 2 players");
/*  53 */     gameTypeBox.getItems().add("2V2 - 4 players");
/*     */     
/*  55 */     VBox gameTypeVBox = new VBox(new Node[] { gameType, gameTypeBox });
/*  56 */     gameTypeVBox.setSpacing(10.0D);
/*  57 */     gameTypeVBox.setAlignment(Pos.CENTER);
/*     */     
/*  59 */     Label goalScore = new Label("Goal Score: 1500pts | 3000pts | 5000pts");
/*  60 */     goalScore.setFont(this.textFont);
/*     */     
/*  62 */     ChoiceBox<String> goalScoreBox = new ChoiceBox<>();
/*  63 */     goalScoreBox.getItems().add("5000pts");
/*  64 */     goalScoreBox.getItems().add("3000pts");
/*  65 */     goalScoreBox.getItems().add("1500pts");
/*     */     
/*  67 */     VBox goalScoreVBox = new VBox(new Node[] { goalScore, goalScoreBox });
/*  68 */     goalScoreVBox.setSpacing(10.0D);
/*  69 */     goalScoreVBox.setAlignment(Pos.CENTER);
/*     */     
/*  71 */     Label teamChoice = new Label("Choose teams?");
/*  72 */     goalScore.setFont(this.textFont);
/*     */     
/*  74 */     ChoiceBox<String> teamChoiceBox = new ChoiceBox<>();
/*  75 */     teamChoiceBox.getItems().add("default");
/*  76 */     teamChoiceBox.getItems().add("random");
/*  77 */     teamChoiceBox.getItems().add("choose");
/*     */     
/*  79 */     VBox teamChoiceVBox = new VBox(new Node[] { teamChoice, teamChoiceBox });
/*  80 */     goalScoreVBox.setSpacing(10.0D);
/*  81 */     goalScoreVBox.setAlignment(Pos.CENTER);
/*     */     
/*  83 */     VBox vb1 = new VBox(new Node[] { gameTypeVBox, goalScoreVBox, teamChoiceVBox });
/*  84 */     vb1.setAlignment(Pos.CENTER);
/*  85 */     vb1.setSpacing(50.0D);
/*     */     
/*  87 */     Button back = new Button("Cancel");
/*  88 */     back.setAlignment(Pos.CENTER);
/*  89 */     back.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/*  93 */     HBox left = new HBox(new Node[] { back });
/*  94 */     left.setAlignment(Pos.CENTER_RIGHT);
/*     */     
/*  96 */     Button confirm = new Button("Create");
/*  97 */     confirm.setAlignment(Pos.CENTER);
/*  98 */     confirm.setOnAction(action -> {
/*     */           String gameTyp = null;
/*     */           
/*     */           String points = null;
/*     */           
/*     */           String teamAllocation = null;
/*     */           
/*     */           gameTyp = paramChoiceBox1.getValue();
/*     */           
/*     */           points = paramChoiceBox2.getValue();
/*     */           teamAllocation = paramChoiceBox3.getValue();
/*     */           if (gameTyp != null && points != null && teamAllocation != null) {
/*     */             int teamAl;
/*     */             if (teamAllocation.equals("default")) {
/*     */               teamAl = 0;
/*     */             } else if (teamAllocation.equals("random")) {
/*     */               teamAl = 1;
/*     */             } else {
/*     */               teamAl = 2;
/*     */             } 
/*     */             try {
/*     */             
/* 120 */             } catch (IOException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 127 */     HBox right = new HBox(new Node[] { confirm });
/* 128 */     right.setAlignment(Pos.CENTER_LEFT);
/*     */ 
/*     */     
/* 131 */     bp.setTop(title);
/* 132 */     bp.setCenter(vb1);
/* 133 */     bp.setRight(right);
/* 134 */     bp.setLeft(left);
/*     */ 
/*     */     
/* 137 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 144 */     Stage window = stage;
/* 145 */     BorderPane bp = createGameSettings();
/* 146 */     bp.setBottom(null);
/* 147 */     window.setTitle("Canasta App");
/* 148 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 149 */     window.setScene(scene);
/*     */     
/* 151 */     String css = getClass().getResource("style.css").toExternalForm();
/* 152 */     scene.getStylesheets().add(css);
/* 153 */     window.show();
/* 154 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 156 */             System.out.println("Stage is closing");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException, SQLException {
/* 176 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\GameSettings.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */