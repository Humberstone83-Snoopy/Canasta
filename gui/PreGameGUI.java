/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import javafx.application.Application;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Group;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.effect.DropShadow;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.paint.CycleMethod;
/*     */ import javafx.scene.paint.LinearGradient;
/*     */ import javafx.scene.paint.Stop;
/*     */ import javafx.scene.shape.Rectangle;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.scene.text.FontWeight;
/*     */ import javafx.scene.text.Text;
/*     */ import javafx.stage.Stage;
/*     */ import sample.ActiveGame;
/*     */ import sample.NewGame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreGameGUI
/*     */   extends Application
/*     */   implements PreGameInterface
/*     */ {
/*     */   private Group root;
/*     */   private NewGame settings;
/*     */   private ActiveGame game;
/*     */   
/*     */   public PreGameGUI(NewGame currentGLobby) {
/*  42 */     this.settings = currentGLobby;
/*     */     try {
/*  44 */       this.game = ClientMain.client.getActiveGame();
/*  45 */     } catch (IOException e) {
/*  46 */       e.printStackTrace();
/*     */     } 
/*  48 */     this.root = createPreScene();
/*  49 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  50 */     String css = getClass().getResource("style.css").toExternalForm();
/*  51 */     scene.getStylesheets().add(css);
/*  52 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */   
/*     */   public PreGameGUI(NewGame settings2, ActiveGame aGame) {
/*  57 */     this.settings = settings2;
/*  58 */     this.game = aGame;
/*  59 */     this.root = createPreScene();
/*  60 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  61 */     String css = getClass().getResource("style.css").toExternalForm();
/*  62 */     scene.getStylesheets().add(css);
/*  63 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Group createPreScene() {
/*  72 */     Group sp = new Group();
/*     */     
/*  74 */     sp.getChildren().add(preSceneBG());
/*  75 */     sp.getChildren().add(createButton());
/*  76 */     sp.getChildren().add(sceneText());
/*     */     
/*  78 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DropShadow ds() {
/*  85 */     DropShadow ds = new DropShadow();
/*  86 */     ds.setOffsetY(3.0D);
/*  87 */     ds.setColor(Color.color(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D));
/*  88 */     return ds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Group sceneText() {
/*  96 */     Group text = new Group();
/*     */     
/*  98 */     Text preGameText1 = new Text("Welcome to...");
/*  99 */     preGameText1.setEffect(ds());
/* 100 */     preGameText1.setCache(true);
/* 101 */     preGameText1.setX(10.0D);
/* 102 */     preGameText1.setY(70.0D);
/* 103 */     preGameText1.setFill(Color.DARKRED);
/* 104 */     preGameText1.setFont(Font.font((String)null, FontWeight.BOLD, 64.0D));
/*     */     
/* 106 */     Text team1 = new Text("Team 1");
/* 107 */     team1.setX(170.0D);
/* 108 */     team1.setY(550.0D);
/* 109 */     team1.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 110 */     Text team2 = new Text("Team 2");
/* 111 */     team2.setX(745.0D);
/* 112 */     team2.setY(550.0D);
/* 113 */     team2.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/*     */ 
/*     */ 
/*     */     
/* 117 */     Text name1 = new Text(this.settings.getHost());
/* 118 */     if (this.settings.getP1Team() == 0) {
/* 119 */       name1.setX(175.0D);
/* 120 */       name1.setY(600.0D);
/*     */     } else {
/* 122 */       name1.setX(740.0D);
/* 123 */       name1.setY(600.0D);
/*     */     } 
/*     */     
/* 126 */     Text name2 = new Text(this.settings.getPlayer2());
/* 127 */     if (this.settings.getP2Team() == 0 && this.settings.getP1Team() == 0) {
/* 128 */       name2.setX(175.0D);
/* 129 */       name2.setY(650.0D);
/* 130 */     } else if (this.settings.getP2Team() == 0) {
/* 131 */       name2.setX(175.0D);
/* 132 */       name2.setY(600.0D);
/* 133 */     } else if (this.settings.getP2Team() == 1 && this.settings.getP1Team() == 0) {
/* 134 */       name2.setX(740.0D);
/* 135 */       name2.setY(600.0D);
/*     */     } else {
/* 137 */       name2.setX(740.0D);
/* 138 */       name2.setY(650.0D);
/*     */     } 
/*     */     
/* 141 */     text.getChildren().add(preGameText1);
/* 142 */     text.getChildren().add(team1);
/* 143 */     text.getChildren().add(team2);
/* 144 */     text.getChildren().add(name1);
/* 145 */     text.getChildren().add(name2);
/*     */     
/* 147 */     if (this.settings.getGameType() == 1) {
/*     */       
/* 149 */       Text name3 = new Text(this.settings.getPlayer3());
/* 150 */       if (this.settings.getP1Team() == 0 && this.settings.getP2Team() == 1) {
/* 151 */         if (this.settings.getP3Team() == 0) {
/* 152 */           name3.setX(175.0D);
/* 153 */           name3.setY(650.0D);
/*     */         } else {
/* 155 */           name3.setX(740.0D);
/* 156 */           name3.setY(650.0D);
/*     */         } 
/* 158 */       } else if (this.settings.getP3Team() == 0) {
/* 159 */         name3.setX(175.0D);
/* 160 */         name3.setY(600.0D);
/*     */       } else {
/* 162 */         name3.setX(740.0D);
/* 163 */         name3.setY(600.0D);
/*     */       } 
/*     */       
/* 166 */       Text name4 = new Text(this.settings.getPlayer4());
/* 167 */       if (this.settings.getP4Team() == 0) {
/* 168 */         name4.setX(175.0D);
/* 169 */         name4.setY(650.0D);
/*     */       } else {
/* 171 */         name4.setX(740.0D);
/* 172 */         name4.setY(650.0D);
/*     */       } 
/*     */       
/* 175 */       text.getChildren().add(name3);
/* 176 */       text.getChildren().add(name4);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Group preSceneBG() {
/* 190 */     Group BG = new Group();
/*     */     
/* 192 */     Stop[] stops = { new Stop(0.0D, Color.RED), new Stop(1.0D, Color.BLUE) };
/* 193 */     LinearGradient lg1 = new LinearGradient(0.0D, 0.0D, 1.0D, 0.0D, true, CycleMethod.NO_CYCLE, stops);
/*     */     
/* 195 */     Stop[] stops2 = { new Stop(0.0D, Color.GOLD), new Stop(1.0D, Color.DARKGOLDENROD) };
/* 196 */     LinearGradient lg2 = new LinearGradient(0.0D, 0.0D, 1.0D, 0.0D, true, CycleMethod.NO_CYCLE, stops2);
/*     */     
/* 198 */     Rectangle bG1 = new Rectangle(10.0D, 500.0D, 980.0D, 200.0D);
/* 199 */     bG1.setFill(lg1);
/* 200 */     bG1.setEffect(ds());
/* 201 */     bG1.setArcHeight(5.0D);
/* 202 */     bG1.setArcWidth(5.0D);
/* 203 */     Rectangle bG2 = new Rectangle(20.0D, 510.0D, 960.0D, 180.0D);
/* 204 */     bG2.setFill(Color.DARKOLIVEGREEN);
/*     */     
/* 206 */     Rectangle buttonTab = new Rectangle(400.0D, 600.0D, 200.0D, 200.0D);
/* 207 */     buttonTab.setFill(Color.BLACK);
/* 208 */     buttonTab.setArcHeight(20.0D);
/* 209 */     buttonTab.setArcWidth(20.0D);
/* 210 */     buttonTab.setEffect(ds());
/*     */     
/* 212 */     Rectangle buttonTabLayer = new Rectangle(402.0D, 602.0D, 196.0D, 196.0D);
/* 213 */     buttonTabLayer.setFill(lg2);
/* 214 */     buttonTabLayer.setArcHeight(20.0D);
/* 215 */     buttonTabLayer.setArcWidth(20.0D);
/*     */     
/* 217 */     Rectangle teamTab1 = new Rectangle(675.0D, 525.0D, 200.0D, 150.0D);
/* 218 */     teamTab1.setFill(Color.FLORALWHITE);
/* 219 */     teamTab1.setArcHeight(20.0D);
/* 220 */     teamTab1.setArcWidth(20.0D);
/* 221 */     teamTab1.setEffect(ds());
/*     */     
/* 223 */     Rectangle teamTabLayer1 = new Rectangle(676.0D, 526.0D, 198.0D, 43.0D);
/* 224 */     teamTabLayer1.setFill(Color.CORNFLOWERBLUE);
/* 225 */     teamTabLayer1.setArcHeight(20.0D);
/* 226 */     teamTabLayer1.setArcWidth(20.0D);
/*     */     
/* 228 */     Rectangle teamTabLayer2 = new Rectangle(725.0D, 620.0D, 100.0D, 2.0D);
/* 229 */     teamTabLayer2.setFill(Color.BLACK);
/* 230 */     teamTabLayer2.setArcHeight(20.0D);
/* 231 */     teamTabLayer2.setArcWidth(20.0D);
/*     */     
/* 233 */     Rectangle teamTab2 = new Rectangle(100.0D, 525.0D, 200.0D, 150.0D);
/* 234 */     teamTab2.setFill(Color.FLORALWHITE);
/* 235 */     teamTab2.setArcHeight(20.0D);
/* 236 */     teamTab2.setArcWidth(20.0D);
/* 237 */     teamTab2.setEffect(ds());
/*     */     
/* 239 */     Rectangle teamTabLayer3 = new Rectangle(101.0D, 526.0D, 198.0D, 43.0D);
/* 240 */     teamTabLayer3.setFill(Color.INDIANRED);
/* 241 */     teamTabLayer3.setArcHeight(20.0D);
/* 242 */     teamTabLayer3.setArcWidth(20.0D);
/*     */     
/* 244 */     Rectangle teamTabLayer4 = new Rectangle(150.0D, 620.0D, 100.0D, 2.0D);
/* 245 */     teamTabLayer4.setFill(Color.BLACK);
/* 246 */     teamTabLayer4.setArcHeight(20.0D);
/* 247 */     teamTabLayer4.setArcWidth(20.0D);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 252 */       FileInputStream inputstream = new FileInputStream("/Users/jackhumberstone/eclipse-workspace/Canasta 2.0/src/res/Aces.jpg");
/*     */       
/* 254 */       Image image = new Image(inputstream);
/* 255 */       ImageView imgView = new ImageView(image);
/*     */       
/* 257 */       inputstream = new FileInputStream(
/* 258 */           "/Users/jackhumberstone/eclipse-workspace/Canasta 2.0/src/res/canastaLogo.png");
/* 259 */       Image logo = new Image(inputstream);
/* 260 */       ImageView imgView2 = new ImageView(logo);
/*     */       
/* 262 */       imgView.setX(350.0D);
/* 263 */       imgView.setY(95.0D);
/* 264 */       imgView.setFitHeight(455.0D);
/* 265 */       imgView.setFitWidth(500.0D);
/* 266 */       imgView.setPreserveRatio(true);
/*     */       
/* 268 */       imgView2.setX(150.0D);
/* 269 */       imgView2.setY(300.0D);
/* 270 */       imgView2.setFitHeight(650.0D);
/* 271 */       imgView2.setFitWidth(650.0D);
/* 272 */       imgView2.setPreserveRatio(true);
/*     */       
/* 274 */       BG.getChildren().add(bG1);
/* 275 */       BG.getChildren().add(bG2);
/* 276 */       BG.getChildren().add(imgView);
/* 277 */       BG.getChildren().add(imgView2);
/* 278 */       BG.getChildren().add(buttonTab);
/* 279 */       BG.getChildren().add(buttonTabLayer);
/* 280 */       BG.getChildren().add(teamTab1);
/* 281 */       BG.getChildren().add(teamTabLayer1);
/* 282 */       BG.getChildren().add(teamTabLayer2);
/* 283 */       BG.getChildren().add(teamTab2);
/* 284 */       BG.getChildren().add(teamTabLayer3);
/* 285 */       BG.getChildren().add(teamTabLayer4);
/*     */     }
/* 287 */     catch (FileNotFoundException e) {
/* 288 */       e.printStackTrace();
/* 289 */       System.out.println("Error: File not found - PreGame, PreSceneBG");
/*     */     } 
/* 291 */     return BG;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button createButton() {
/* 299 */     Button clickToPlay = new Button();
/* 300 */     clickToPlay.setText("click to play!");
/* 301 */     clickToPlay.setLayoutX(445.0D);
/* 302 */     clickToPlay.setLayoutY(650.0D);
/* 303 */     clickToPlay.setOnAction(e -> {
/*     */         
/*     */         });
/* 306 */     return clickToPlay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) {
/* 314 */     stage.setTitle("Test");
/* 315 */     Group group = createPreScene();
/* 316 */     Scene scene = new Scene(group, 1000.0D, 1000.0D);
/* 317 */     stage.setScene(scene);
/* 318 */     stage.show();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 325 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\PreGameGUI.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */