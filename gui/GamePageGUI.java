/*      */ package gui;
/*      */ 
/*      */ import client.ClientMain;
/*      */ import gameLogic.Card;
/*      */ import gameLogic.Hand;
/*      */ import gameLogic.Meld;
/*      */ import gameLogic.Player;
/*      */ import gameLogic.Stock;
/*      */ import gameLogic.TwoPlayerLogic;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import javafx.application.Application;
/*      */ import javafx.application.Platform;
/*      */ import javafx.event.Event;
/*      */ import javafx.event.EventHandler;
/*      */ import javafx.scene.Group;
/*      */ import javafx.scene.Scene;
/*      */ import javafx.scene.control.Button;
/*      */ import javafx.scene.effect.DropShadow;
/*      */ import javafx.scene.effect.Light;
/*      */ import javafx.scene.effect.Lighting;
/*      */ import javafx.scene.image.Image;
/*      */ import javafx.scene.input.MouseEvent;
/*      */ import javafx.scene.paint.Color;
/*      */ import javafx.scene.paint.ImagePattern;
/*      */ import javafx.scene.paint.Paint;
/*      */ import javafx.scene.shape.Circle;
/*      */ import javafx.scene.shape.Rectangle;
/*      */ import javafx.scene.text.Font;
/*      */ import javafx.scene.text.FontWeight;
/*      */ import javafx.scene.text.Text;
/*      */ import javafx.stage.Stage;
/*      */ import sample.ActiveGame;
/*      */ import sample.NewGame;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GamePageGUI
/*      */   extends Application
/*      */   implements GamePageInterface
/*      */ {
/*      */   public static TwoPlayerLogic game;
/*   47 */   private static int playerID = 0;
/*      */   int teamID;
/*   49 */   int opID = 1;
/*      */   
/*      */   String username;
/*      */   Text opHandSize;
/*      */   private NewGame settings;
/*      */   private ActiveGame aGame;
/*      */   private Group root;
/*      */   public static Text consoleMessage;
/*      */   private boolean run;
/*      */   private Color downStroke;
/*      */   private int downTracker;
/*      */   private Group tips;
/*      */   private Group rulesGroup;
/*      */   private Group discard;
/*      */   private Group hand;
/*      */   private Group myMelds;
/*      */   private Group opMelds;
/*      */   private Text turnMarker;
/*      */   private Group sp;
/*      */   private Button createMeld;
/*      */   
/*      */   public GamePageGUI(ActiveGame aGame, NewGame settings) {
/*   71 */     this.settings = settings;
/*   72 */     game = aGame.getGame0();
/*   73 */     this.aGame = aGame;
/*   74 */     this.username = ClientMain.client.getUsername();
/*      */     
/*   76 */     if (settings.getHost().equals(this.username)) {
/*   77 */       playerID = 0;
/*   78 */       this.teamID = settings.getP1Team();
/*   79 */     } else if (settings.getPlayer2().equals(this.username)) {
/*   80 */       playerID = 1;
/*   81 */       this.teamID = settings.getP2Team();
/*   82 */     } else if (settings.getPlayer3().equals(this.username)) {
/*   83 */       playerID = 2;
/*   84 */       this.teamID = settings.getP3Team();
/*      */     } else {
/*   86 */       playerID = 3;
/*   87 */       this.teamID = settings.getP4Team();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  109 */     if (this.teamID == 0) {
/*  110 */       this.opID = 1;
/*      */     } else {
/*  112 */       this.opID = 0;
/*      */     } 
/*      */     
/*  115 */     this.root = createGame();
/*  116 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  117 */     ClientMain.window.setScene(scene);
/*      */     
/*  119 */     this.run = true;
/*  120 */     run();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void run() {
/*  126 */     Thread thread = new Thread(new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/*  130 */             Runnable updater = new Runnable()
/*      */               {
/*      */                 
/*      */                 public void run()
/*      */                 {
/*  135 */                   if (!(GamePageGUI.null.access$0(GamePageGUI.null.this)).aGame.isRoundOver() && 
/*  136 */                     (GamePageGUI.null.access$0(GamePageGUI.null.this)).aGame.getGame0().getPlayerTurn() != GamePageGUI.playerID) {
/*      */                     
/*      */                     try {
/*  139 */                       (GamePageGUI.null.access$0(GamePageGUI.null.this)).aGame = ClientMain.client.getActiveGame();
/*  140 */                       GamePageGUI.game = (GamePageGUI.null.access$0(GamePageGUI.null.this)).aGame.getGame0();
/*  141 */                     } catch (IOException e) {
/*  142 */                       e.printStackTrace();
/*      */                     } 
/*      */                     
/*  145 */                     GamePageGUI.null.access$0(GamePageGUI.null.this).updateHand();
/*  146 */                     GamePageGUI.null.access$0(GamePageGUI.null.this).updateDiscard();
/*  147 */                     GamePageGUI.null.access$0(GamePageGUI.null.this).updateMelds();
/*  148 */                     GamePageGUI.null.access$0(GamePageGUI.null.this).updateTurnMarker();
/*      */                   } 
/*      */ 
/*      */ 
/*      */                   
/*  153 */                   if ((GamePageGUI.null.access$0(GamePageGUI.null.this)).aGame.isRoundOver()) {
/*  154 */                     (GamePageGUI.null.access$0(GamePageGUI.null.this)).run = false;
/*      */                   }
/*      */                 }
/*      */               };
/*      */ 
/*      */ 
/*      */             
/*      */             while (true) {
/*      */               try {
/*  163 */                 Thread.sleep(5000L);
/*  164 */               } catch (InterruptedException interruptedException) {}
/*      */ 
/*      */ 
/*      */               
/*  168 */               if (!GamePageGUI.this.run) {
/*      */                 break;
/*      */               }
/*  171 */               Platform.runLater(updater);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  178 */     thread.setDaemon(true);
/*  179 */     thread.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setConsoleMessage(String message) {
/*  187 */     consoleMessage.setText(message);
/*      */   }
/*      */ 
/*      */   
/*      */   public Group gameBG() {
/*  192 */     Group bG = new Group();
/*      */     
/*  194 */     Rectangle bG1 = new Rectangle(0.0D, 0.0D, 1000.0D, 1000.0D);
/*  195 */     bG1.setFill(Color.DARKSLATEGREY);
/*  196 */     Rectangle bG2 = new Rectangle(12.5D, 12.5D, 975.0D, 975.0D);
/*  197 */     bG2.setFill(Color.DARKOLIVEGREEN);
/*  198 */     Rectangle stockBox = new Rectangle(135.0D, 400.0D, 725.0D, 105.0D);
/*  199 */     stockBox.setFill((Paint)null);
/*  200 */     stockBox.setStroke(Color.BLACK);
/*  201 */     stockBox.setOpacity(0.5D);
/*  202 */     Text melds = new Text("Melds");
/*  203 */     melds.setFill(Color.BLACK);
/*  204 */     melds.setOpacity(0.5D);
/*  205 */     melds.setX(450.0D);
/*  206 */     melds.setY(465.0D);
/*  207 */     melds.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/*  208 */     Rectangle opStockBox = new Rectangle(135.0D, 100.0D, 725.0D, 105.0D);
/*  209 */     opStockBox.setFill((Paint)null);
/*  210 */     opStockBox.setStroke(Color.BLACK);
/*  211 */     opStockBox.setOpacity(0.5D);
/*  212 */     Text opMelds = new Text("Opponent Melds");
/*  213 */     opMelds.setFill(Color.BLACK);
/*  214 */     opMelds.setOpacity(0.5D);
/*  215 */     opMelds.setX(380.0D);
/*  216 */     opMelds.setY(165.0D);
/*  217 */     opMelds.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/*  218 */     Rectangle userBack1 = new Rectangle(775.0D, 0.0D, 200.0D, 80.0D);
/*  219 */     userBack1.setFill(Color.BLACK);
/*  220 */     userBack1.setArcHeight(5.0D);
/*  221 */     userBack1.setArcWidth(5.0D);
/*  222 */     userBack1.setEffect(ds());
/*  223 */     Rectangle userBack2 = new Rectangle(776.0D, 0.0D, 24.0D, 79.0D);
/*      */     
/*  225 */     userBack2.setFill(Color.RED);
/*  226 */     userBack2.setArcHeight(5.0D);
/*  227 */     userBack2.setArcWidth(5.0D);
/*  228 */     Rectangle userBack3 = new Rectangle(801.0D, 0.0D, 173.0D, 79.0D);
/*  229 */     userBack3.setFill(Color.WHITE);
/*  230 */     userBack3.setArcHeight(5.0D);
/*  231 */     userBack3.setArcWidth(5.0D);
/*      */     
/*  233 */     Rectangle opHandBox = new Rectangle(920.0D, 25.0D, 50.0D, 50.0D);
/*  234 */     opHandBox.setFill(Color.BLACK);
/*  235 */     opHandBox.setArcHeight(10.0D);
/*  236 */     opHandBox.setArcWidth(10.0D);
/*      */     
/*  238 */     Rectangle opHandBox2 = new Rectangle(921.0D, 26.0D, 48.0D, 48.0D);
/*  239 */     opHandBox2.setFill(Color.LIGHTGOLDENRODYELLOW);
/*  240 */     opHandBox2.setArcHeight(10.0D);
/*  241 */     opHandBox2.setArcWidth(10.0D);
/*      */ 
/*      */     
/*  244 */     Text opUsername = new Text("Example");
/*  245 */     opUsername.setX(815.0D);
/*  246 */     opUsername.setY(25.0D);
/*  247 */     opUsername.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/*      */ 
/*      */     
/*  250 */     Text team = new Text("Team 2");
/*  251 */     team.setX(815.0D);
/*  252 */     team.setY(45.0D);
/*  253 */     team.setFont(Font.font((String)null, FontWeight.NORMAL, 14.0D));
/*      */ 
/*      */     
/*  256 */     this.opHandSize = new Text();
/*  257 */     this.opHandSize.setText(game.getOpHandSize(playerID));
/*  258 */     this.opHandSize.setX(927.0D);
/*  259 */     this.opHandSize.setY(60.0D);
/*  260 */     this.opHandSize.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/*      */     
/*  262 */     Text cards = new Text("cards left");
/*  263 */     cards.setX(927.0D);
/*  264 */     cards.setY(70.0D);
/*  265 */     cards.setFont(Font.font((String)null, FontWeight.NORMAL, 8.0D));
/*      */     
/*  267 */     bG.getChildren().add(bG1);
/*  268 */     bG.getChildren().add(bG2);
/*  269 */     bG.getChildren().add(userBack1);
/*  270 */     bG.getChildren().add(userBack2);
/*  271 */     bG.getChildren().add(userBack3);
/*  272 */     bG.getChildren().add(opUsername);
/*  273 */     bG.getChildren().add(team);
/*  274 */     bG.getChildren().add(opHandBox);
/*  275 */     bG.getChildren().add(opHandBox2);
/*  276 */     bG.getChildren().add(this.opHandSize);
/*  277 */     bG.getChildren().add(cards);
/*  278 */     bG.getChildren().add(stockBox);
/*  279 */     bG.getChildren().add(melds);
/*  280 */     bG.getChildren().add(opStockBox);
/*  281 */     bG.getChildren().add(opMelds);
/*      */     
/*  283 */     return bG;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DropShadow ds() {
/*  290 */     DropShadow ds = new DropShadow();
/*  291 */     ds.setOffsetY(3.0D);
/*  292 */     ds.setColor(Color.color(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D));
/*  293 */     return ds;
/*      */   }
/*      */   
/*      */   public Lighting lighting() {
/*  297 */     Light.Distant light = new Light.Distant();
/*  298 */     light.setAzimuth(-135.0D);
/*      */     
/*  300 */     Lighting lighting = new Lighting();
/*  301 */     lighting.setLight(light);
/*  302 */     lighting.setSurfaceScale(5.0D);
/*      */     
/*  304 */     return lighting;
/*      */   }
/*      */ 
/*      */   
/*      */   public Color randomColor() {
/*  309 */     return Color.color(Math.random(), Math.random(), Math.random());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ImagePattern cardImage(int rank, int suit) {
/*  318 */     String URL = "/Users/jackhumberstone/eclipse-workspace/Canasta 2.0/src/res/";
/*  319 */     String stringRank = "";
/*  320 */     String stringSuit = "";
/*      */     
/*  322 */     if (rank == 1) {
/*  323 */       stringRank = "A";
/*  324 */     } else if (rank == 11) {
/*  325 */       stringRank = "J";
/*  326 */     } else if (rank == 12) {
/*  327 */       stringRank = "Q";
/*  328 */     } else if (rank == 13) {
/*  329 */       stringRank = "K";
/*  330 */     } else if (rank == 14) {
/*  331 */       stringRank = "joker";
/*      */     } else {
/*  333 */       stringRank = Integer.toString(rank);
/*      */     } 
/*      */     
/*  336 */     if (suit == 0) {
/*  337 */       stringSuit = "S";
/*  338 */     } else if (suit == 1) {
/*  339 */       stringSuit = "C";
/*  340 */     } else if (suit == 2) {
/*  341 */       stringSuit = "D";
/*  342 */     } else if (suit == 3) {
/*  343 */       stringSuit = "H";
/*      */     } else {
/*  345 */       stringSuit = "";
/*      */     } 
/*      */     
/*  348 */     URL = String.valueOf(URL) + stringRank;
/*      */     
/*  350 */     URL = String.valueOf(URL) + stringSuit;
/*      */     
/*  352 */     URL = String.valueOf(URL) + ".jpg";
/*      */     
/*      */     try {
/*  355 */       FileInputStream inputstream = new FileInputStream(URL);
/*      */       
/*  357 */       Image img = new Image(inputstream);
/*      */       
/*  359 */       return new ImagePattern(img, 0.0D, 0.0D, 1.0D, 1.0D, true);
/*      */     }
/*  361 */     catch (FileNotFoundException e) {
/*  362 */       e.printStackTrace();
/*  363 */       System.out.println("Error: file not found - GamePage, cardImage");
/*      */ 
/*      */       
/*  366 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addHand(int i, int handSize) {
/*  372 */     Group cards = new Group();
/*      */     
/*  374 */     double spacing = 35.0D;
/*      */     
/*  376 */     if (handSize > 33) {
/*  377 */       spacing = 15.0D;
/*  378 */     } else if (handSize > 24) {
/*  379 */       spacing = 25.0D;
/*      */     } 
/*      */     
/*  382 */     final Rectangle card = new Rectangle(50.0D + i * spacing, 550.0D, 95.0D, 140.0D);
/*  383 */     card.setFill(Color.WHITE);
/*  384 */     card.setArcWidth(30.0D);
/*  385 */     card.setArcHeight(20.0D);
/*  386 */     card.setStroke(Color.BLACK);
/*  387 */     card.setStrokeWidth(2.0D);
/*  388 */     card.setEffect(ds());
/*      */     
/*  390 */     int suit = ((Card)getThisPlayerHand().getCards().get(i)).getSuit();
/*  391 */     final int rank = ((Card)getThisPlayerHand().getCards().get(i)).getRank();
/*      */     
/*  393 */     card.setFill(cardImage(rank, suit));
/*  394 */     card.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0)
/*      */           {
/*  398 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID) {
/*  399 */               if (GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDown()) {
/*  400 */                 if (GameGuiMethods2P.hand(rank)) {
/*  401 */                   card.setY(530.0D);
/*  402 */                   card.setDisable(true);
/*      */                 }
/*      */               
/*  405 */               } else if (GameGuiMethods2P.downPickUp(rank)) {
/*  406 */                 card.setY(530.0D);
/*  407 */                 card.setDisable(true);
/*  408 */                 if (GamePageGUI.this.downTracker != rank) {
/*  409 */                   GamePageGUI.this.downStroke = GamePageGUI.this.randomColor();
/*      */                 }
/*  411 */                 card.setStroke(GamePageGUI.this.downStroke);
/*  412 */                 GamePageGUI.this.downTracker = rank;
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*      */     
/*  418 */     cards.getChildren().add(card);
/*  419 */     return cards;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addOpMelds(int i, int rank, int suit, int size, boolean isCanasta, boolean isNatural) {
/*  424 */     Group cardGroup = new Group();
/*      */     
/*  426 */     Rectangle card1 = new Rectangle(135.0D + i * 55.7692308D, 100.0D, 75.0D, 105.0D);
/*  427 */     card1.setFill(Color.WHITE);
/*  428 */     card1.setArcWidth(30.0D);
/*  429 */     card1.setArcHeight(20.0D);
/*  430 */     card1.setStroke(Color.BLACK);
/*  431 */     card1.setStrokeWidth(2.0D);
/*  432 */     card1.setFill(cardImage(rank, suit));
/*      */     
/*  434 */     if (isCanasta) {
/*  435 */       card1.setRotate(90.0D);
/*  436 */       if (isNatural) {
/*  437 */         DropShadow borderGlow = new DropShadow();
/*  438 */         borderGlow.setColor(Color.GOLD);
/*  439 */         borderGlow.setOffsetX(0.0D);
/*  440 */         borderGlow.setOffsetY(0.0D);
/*  441 */         card1.setEffect(borderGlow);
/*      */       } 
/*      */     } 
/*      */     
/*  445 */     Text meldSize = new Text("(" + Integer.toString(size) + ")");
/*  446 */     meldSize.setX(165.0D + i * 55.7692308D);
/*  447 */     meldSize.setY(220.0D);
/*      */     
/*  449 */     if (isNatural) {
/*  450 */       DropShadow borderGlow = new DropShadow();
/*  451 */       borderGlow.setColor(Color.GOLD);
/*  452 */       borderGlow.setOffsetX(0.0D);
/*  453 */       borderGlow.setOffsetY(0.0D);
/*  454 */       meldSize.setEffect(borderGlow);
/*      */     } 
/*      */     
/*  457 */     cardGroup.getChildren().add(card1);
/*  458 */     cardGroup.getChildren().add(meldSize);
/*      */     
/*  460 */     return cardGroup;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addMyMelds(int i, final int rank, int suit, int size, boolean isCanasta, boolean isNatural) {
/*  465 */     Group cardGroup = new Group();
/*      */     
/*  467 */     Rectangle card1 = new Rectangle(135.0D + i * 55.7692308D, 400.0D, 75.0D, 105.0D);
/*  468 */     card1.setFill(Color.WHITE);
/*  469 */     card1.setArcWidth(30.0D);
/*  470 */     card1.setArcHeight(20.0D);
/*  471 */     card1.setStroke(Color.BLACK);
/*  472 */     card1.setStrokeWidth(2.0D);
/*  473 */     card1.setFill(cardImage(rank, suit));
/*      */     
/*  475 */     if (isCanasta) {
/*  476 */       card1.setRotate(90.0D);
/*  477 */       if (isNatural) {
/*  478 */         DropShadow borderGlow = new DropShadow();
/*  479 */         borderGlow.setColor(Color.GOLD);
/*  480 */         borderGlow.setOffsetX(0.0D);
/*  481 */         borderGlow.setOffsetY(0.0D);
/*  482 */         card1.setEffect(borderGlow);
/*      */       } 
/*      */     } 
/*      */     
/*  486 */     Text meldSize = new Text("(" + Integer.toString(size) + ")");
/*  487 */     meldSize.setX(165.0D + i * 55.7692308D);
/*  488 */     meldSize.setY(520.0D);
/*      */     
/*  490 */     if (isNatural) {
/*  491 */       DropShadow borderGlow = new DropShadow();
/*  492 */       borderGlow.setColor(Color.GOLD);
/*  493 */       borderGlow.setOffsetX(0.0D);
/*  494 */       borderGlow.setOffsetY(0.0D);
/*  495 */       meldSize.setEffect(borderGlow);
/*      */     } 
/*      */     
/*  498 */     cardGroup.getChildren().add(card1);
/*  499 */     cardGroup.getChildren().add(meldSize);
/*  500 */     cardGroup.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0) {
/*  503 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID)
/*      */             {
/*  505 */               if (GamePageGUI.game.findPlayerHand().greaterThanOne() || GamePageGUI.game.findPlayerStock().checkForCanasta()) {
/*      */                 
/*      */                 try {
/*  508 */                   GameGuiMethods2P.meld(rank);
/*  509 */                 } catch (IllegalArgumentException e) {
/*  510 */                   if (GamePageGUI.game.getA() != null) {
/*  511 */                     GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getA());
/*  512 */                     GamePageGUI.game.setA(null);
/*      */                   } 
/*  514 */                   if (GamePageGUI.game.getB() != null) {
/*  515 */                     GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getB());
/*  516 */                     GamePageGUI.game.setB(null);
/*      */                   } 
/*  518 */                   if (GamePageGUI.game.getC() != null) {
/*  519 */                     GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getC());
/*  520 */                     GamePageGUI.game.setC(null);
/*      */                   } 
/*      */                 } 
/*  523 */                 GamePageGUI.this.updateHand();
/*  524 */                 GamePageGUI.this.updateMelds();
/*      */                 
/*  526 */                 if (GamePageGUI.game.isRoundOver()) {
/*  527 */                   GamePageGUI.this.aGame.setThisUserOut(ClientMain.client.getUsername());
/*  528 */                   GamePageGUI.this.aGame.setRoundOver(true);
/*  529 */                   GamePageGUI.this.run = false;
/*      */                 } 
/*      */ 
/*      */                 
/*  533 */                 GamePageGUI.this.aGame.setGame0(GamePageGUI.game);
/*      */                 try {
/*  535 */                   ClientMain.client.sendActiveGame(GamePageGUI.this.aGame);
/*  536 */                 } catch (IOException e) {
/*  537 */                   e.printStackTrace();
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*      */     
/*  544 */     return cardGroup;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addDiscard() {
/*  549 */     Group disc = new Group();
/*      */     
/*  551 */     Rectangle disc1 = new Rectangle(525.0D, 250.0D, 85.0D, 120.0D);
/*  552 */     disc1.setFill((Paint)null);
/*  553 */     disc1.setArcWidth(30.0D);
/*  554 */     disc1.setArcHeight(20.0D);
/*  555 */     disc1.setStroke(Color.BLACK);
/*  556 */     disc1.setStrokeWidth(2.0D);
/*      */     
/*  558 */     disc1.setEffect(ds());
/*      */     
/*  560 */     disc.getChildren().add(disc1);
/*      */     
/*  562 */     Text discard = new Text("Discard");
/*  563 */     discard.setFill(Color.BLACK);
/*  564 */     discard.setStrokeWidth(0.5D);
/*  565 */     discard.setX(542.0D);
/*  566 */     discard.setY(315.0D);
/*  567 */     discard.setFont(Font.font((String)null, FontWeight.BOLD, 14.0D));
/*      */     
/*  569 */     disc.getChildren().add(discard);
/*      */     
/*  571 */     if (game.getDiscard().cardsLeft() > 0) {
/*  572 */       Rectangle disc2 = new Rectangle(525.0D, 250.0D, 85.0D, 120.0D);
/*  573 */       disc2.setFill(Color.WHITE);
/*  574 */       disc2.setArcWidth(30.0D);
/*  575 */       disc2.setArcHeight(20.0D);
/*  576 */       disc2.setStroke(Color.BLACK);
/*  577 */       disc2.setStrokeWidth(2.0D);
/*      */       
/*  579 */       disc2.setEffect(ds());
/*      */       
/*  581 */       int suit = game.getDiscard().checkTopCard().getSuit();
/*  582 */       int rank = game.getDiscard().checkTopCard().getRank();
/*      */       
/*  584 */       disc2.setFill(cardImage(rank, suit));
/*      */       
/*  586 */       disc.getChildren().add(disc2);
/*      */     } 
/*      */     
/*  589 */     disc.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0) {
/*  592 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID) {
/*      */               
/*  594 */               GameGuiMethods2P.swapRed3();
/*      */               
/*  596 */               if (GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDown()) {
/*      */                 
/*  598 */                 GameGuiMethods2P.discard();
/*      */                 
/*  600 */                 if (GamePageGUI.game.isRoundOver()) {
/*  601 */                   GamePageGUI.this.aGame.setThisUserOut(ClientMain.client.getUsername());
/*  602 */                   GamePageGUI.this.run = false;
/*  603 */                   GamePageGUI.this.aGame.setRoundOver(true);
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*  608 */               else if (GameGuiMethods2P.downDiscard()) {
/*  609 */                 GamePageGUI.this.createMeld.setText("Create Meld ");
/*      */               } 
/*      */               
/*  612 */               GamePageGUI.this.updateHand();
/*  613 */               GamePageGUI.this.updateMelds();
/*  614 */               GamePageGUI.this.updateDiscard();
/*      */               
/*  616 */               ActiveGame aGame2 = GamePageGUI.this.aGame;
/*  617 */               aGame2.setGame0(GamePageGUI.game);
/*      */               
/*      */               try {
/*  620 */                 ClientMain.client.sendActiveGame(aGame2);
/*  621 */               } catch (IOException e) {
/*  622 */                 e.printStackTrace();
/*      */               } 
/*      */               
/*  625 */               GamePageGUI.this.aGame = aGame2;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  630 */     return disc;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Group addDeck() {
/*  636 */     Group deck = new Group();
/*      */     
/*  638 */     Rectangle deck1 = new Rectangle(375.0D, 250.0D, 85.0D, 120.0D);
/*  639 */     deck1.setArcWidth(30.0D);
/*  640 */     deck1.setArcHeight(20.0D);
/*  641 */     deck1.setStroke(Color.BLACK);
/*  642 */     deck1.setStrokeWidth(2.0D);
/*  643 */     deck1.setEffect(ds());
/*      */     
/*  645 */     Text deckTitle = new Text("Deck");
/*  646 */     deckTitle.setFill(Color.RED);
/*  647 */     deckTitle.setStroke(Color.BLACK);
/*  648 */     deckTitle.setStrokeWidth(0.5D);
/*  649 */     deckTitle.setX(400.0D);
/*  650 */     deckTitle.setY(315.0D);
/*  651 */     deckTitle.setFont(Font.font((String)null, FontWeight.BOLD, 14.0D));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  656 */     String URL = "/Users/jackhumberstone/eclipse-workspace/Canasta 2.0/src/res/Yellow_back.jpg";
/*      */     
/*      */     try {
/*  659 */       FileInputStream inputstream = new FileInputStream(URL);
/*      */       
/*  661 */       Image img = new Image(inputstream);
/*      */       
/*  663 */       deck1.setFill(new ImagePattern(img, 0.0D, 0.0D, 1.0D, 1.0D, true));
/*  664 */     } catch (FileNotFoundException e) {
/*  665 */       e.printStackTrace();
/*  666 */       System.out.println("Error: file not found - GamePage, cardImage");
/*      */     } 
/*      */     
/*  669 */     deck.getChildren().add(deck1);
/*  670 */     deck.getChildren().add(deckTitle);
/*      */     
/*  672 */     deck.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0) {
/*  675 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID) {
/*  676 */               GameGuiMethods2P.deck();
/*  677 */               GamePageGUI.this.updateHand();
/*  678 */               GamePageGUI.this.updateMelds();
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  683 */     return deck;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Group addButtons() {
/*  689 */     Group buttons = new Group();
/*  690 */     Rectangle back = new Rectangle(875.0D, 435.0D, 156.0D, 95.0D);
/*  691 */     back.setFill(Color.DIMGRAY);
/*  692 */     back.setEffect(lighting());
/*  693 */     buttons.getChildren().add(back);
/*  694 */     Button cancel = new Button("     Cancel     ");
/*  695 */     cancel.setLayoutX(895.0D);
/*  696 */     cancel.setLayoutY(450.0D);
/*  697 */     cancel.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0)
/*      */           {
/*  701 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID) {
/*  702 */               if (GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDown()) {
/*  703 */                 GameGuiMethods2P.cancel();
/*      */               } else {
/*  705 */                 GameGuiMethods2P.buttonDownCancel();
/*      */               } 
/*  707 */               GamePageGUI.game.findPlayerHand().sort();
/*  708 */               GamePageGUI.this.updateHand();
/*      */             } 
/*      */           }
/*      */         });
/*  712 */     buttons.getChildren().add(cancel);
/*  713 */     this.createMeld = new Button("    Go down   ");
/*  714 */     this.createMeld.setLayoutX(895.0D);
/*  715 */     this.createMeld.setLayoutY(485.0D);
/*  716 */     this.createMeld.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public void handle(MouseEvent arg0)
/*      */           {
/*  723 */             if (GamePageGUI.game.getPlayerTurn() == GamePageGUI.playerID)
/*      */             {
/*      */ 
/*      */               
/*  727 */               if (GamePageGUI.game.findPlayerHand().greaterThanOne() || GamePageGUI.game.findPlayerStock().checkForCanasta()) {
/*      */                 
/*  729 */                 if (GamePageGUI.game.isDrawnCard()) {
/*  730 */                   if (GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDown()) {
/*  731 */                     GameGuiMethods2P.createMeld();
/*      */                   }
/*  733 */                   else if (GameGuiMethods2P.goDown()) {
/*  734 */                     GamePageGUI.this.createMeld.setText("Create Meld ");
/*      */                   } 
/*      */                   
/*  737 */                   GamePageGUI.this.updateHand();
/*  738 */                   GamePageGUI.this.updateMelds();
/*      */                   
/*  740 */                   if (GamePageGUI.game.isRoundOver()) {
/*  741 */                     GamePageGUI.this.aGame.setThisUserOut(ClientMain.client.getUsername());
/*  742 */                     GamePageGUI.this.aGame.setRoundOver(true);
/*  743 */                     GamePageGUI.this.run = false;
/*      */                   } 
/*      */ 
/*      */                   
/*  747 */                   GamePageGUI.this.aGame.setGame0(GamePageGUI.game);
/*      */                   try {
/*  749 */                     ClientMain.client.sendActiveGame(GamePageGUI.this.aGame);
/*  750 */                   } catch (IOException e) {
/*  751 */                     e.printStackTrace();
/*      */                   }
/*      */                 
/*      */                 } else {
/*      */                   
/*  756 */                   GamePageGUI.consoleMessage.setText("Pick up first, Either click the deck or use hand and discard pile.");
/*      */                 } 
/*      */               } else {
/*      */                 
/*  760 */                 GamePageGUI.consoleMessage.setText("Not enough cards left to make this action - get a Canasta before going out.");
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*  765 */     buttons.getChildren().add(this.createMeld);
/*  766 */     return buttons;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Group addHotBar() {
/*  772 */     Group bar = new Group();
/*      */     
/*  774 */     Rectangle greyBar = new Rectangle(0.0D, 690.0D, 1000.0D, 40.0D);
/*  775 */     greyBar.setFill(Color.DIMGRAY);
/*  776 */     greyBar.setEffect(lighting());
/*  777 */     bar.getChildren().add(greyBar);
/*      */     
/*  779 */     Rectangle textBox = new Rectangle(400.0D, 700.0D, 500.0D, 25.0D);
/*  780 */     textBox.setFill(Color.BLACK);
/*  781 */     textBox.setOpacity(0.3D);
/*  782 */     bar.getChildren().add(textBox);
/*      */     
/*  784 */     String hotBarText = this.username;
/*  785 */     hotBarText = String.valueOf(hotBarText) + "    |    ";
/*      */     
/*  787 */     if (playerID == 0) {
/*  788 */       hotBarText = String.valueOf(hotBarText) + "Team 1    |    ";
/*      */       
/*  790 */       hotBarText = String.valueOf(hotBarText) + game.getTeam1Score();
/*  791 */       hotBarText = String.valueOf(hotBarText) + "pts";
/*      */     } else {
/*  793 */       hotBarText = String.valueOf(hotBarText) + "Team 2    |    ";
/*      */       
/*  795 */       hotBarText = String.valueOf(hotBarText) + game.getTeam2Score();
/*  796 */       hotBarText = String.valueOf(hotBarText) + "pts";
/*      */     } 
/*      */     
/*  799 */     Text usernameText = new Text(hotBarText);
/*  800 */     usernameText.setFill(Color.BLACK);
/*  801 */     usernameText.setX(50.0D);
/*  802 */     usernameText.setY(715.0D);
/*  803 */     bar.getChildren().add(usernameText);
/*      */     
/*  805 */     consoleMessage = new Text();
/*  806 */     consoleMessage.setText("Example Message");
/*  807 */     consoleMessage.setFill(Color.INDIANRED);
/*  808 */     consoleMessage.setStroke(Color.WHITESMOKE);
/*  809 */     consoleMessage.setStrokeWidth(0.2D);
/*  810 */     consoleMessage.setX(420.0D);
/*  811 */     consoleMessage.setY(716.0D);
/*  812 */     consoleMessage.setFont(Font.font((String)null, FontWeight.BOLD, 14.0D));
/*  813 */     bar.getChildren().add(consoleMessage);
/*      */     
/*  815 */     return bar;
/*      */   }
/*      */   
/*      */   public Group getTips() {
/*  819 */     Group tips = new Group();
/*      */     
/*  821 */     Text DeckTip = new Text("Click here to draw a card ->");
/*  822 */     DeckTip.setX(180.0D);
/*  823 */     DeckTip.setY(300.0D);
/*  824 */     tips.getChildren().add(DeckTip);
/*      */     
/*  826 */     Text DiscTip = new Text(
/*  827 */         "<- Click here to discard a card... \n\n                   ...or to pick up from the discard pile");
/*  828 */     DiscTip.setX(650.0D);
/*  829 */     DiscTip.setY(300.0D);
/*  830 */     tips.getChildren().add(DiscTip);
/*      */     
/*  832 */     Text ClickMelds = new Text("Click on melds ->\nto add cards");
/*  833 */     ClickMelds.setX(20.0D);
/*  834 */     ClickMelds.setY(450.0D);
/*  835 */     tips.getChildren().add(ClickMelds);
/*      */     
/*  837 */     Text ClickHand = new Text("Click on a card to select");
/*  838 */     ClickHand.setX(415.0D);
/*  839 */     ClickHand.setY(540.0D);
/*  840 */     tips.getChildren().add(ClickHand);
/*  841 */     tips.setVisible(false);
/*  842 */     return tips;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addHelpButton() {
/*  847 */     Group helpButton = new Group();
/*      */     
/*  849 */     Circle hpBack = new Circle(970.0D, 710.0D, 12.0D);
/*  850 */     hpBack.setFill(Color.WHITESMOKE);
/*  851 */     hpBack.setStroke(Color.BLACK);
/*  852 */     hpBack.setStrokeWidth(0.5D);
/*  853 */     hpBack.setOpacity(0.6D);
/*      */     
/*  855 */     Circle hpBack2 = new Circle(970.0D, 710.0D, 10.0D);
/*  856 */     hpBack2.setFill(Color.DARKORANGE);
/*  857 */     hpBack2.setStroke(Color.BLACK);
/*  858 */     hpBack2.setStrokeWidth(0.5D);
/*  859 */     hpBack2.setOpacity(0.6D);
/*      */     
/*  861 */     Text questionMark = new Text("?");
/*  862 */     questionMark.setFill(Color.BLACK);
/*  863 */     questionMark.setX(965.0D);
/*  864 */     questionMark.setY(717.0D);
/*  865 */     questionMark.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/*  866 */     questionMark.setOpacity(0.6D);
/*      */     
/*  868 */     helpButton.getChildren().add(hpBack);
/*  869 */     helpButton.getChildren().add(hpBack2);
/*  870 */     helpButton.getChildren().add(questionMark);
/*      */     
/*  872 */     helpButton.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0) {
/*  875 */             if (GamePageGUI.this.tips.isVisible()) {
/*  876 */               GamePageGUI.this.tips.setVisible(false);
/*      */             } else {
/*  878 */               GamePageGUI.this.tips.setVisible(true);
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  883 */     return helpButton;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group getRules() {
/*  888 */     Group rules = new Group();
/*      */     
/*  890 */     Text title = new Text("RULES");
/*  891 */     title.setFill(Color.GOLD);
/*  892 */     title.setStroke(Color.BLACK);
/*  893 */     title.setX(450.0D);
/*  894 */     title.setY(65.0D);
/*  895 */     title.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/*      */     
/*  897 */     Rectangle edge = new Rectangle(0.0D, 0.0D, 1000.0D, 1000.0D);
/*  898 */     edge.setFill(Color.SLATEGREY);
/*  899 */     rules.getChildren().add(edge);
/*      */     
/*  901 */     Rectangle bg = new Rectangle(12.5D, 12.5D, 975.0D, 975.0D);
/*  902 */     bg.setFill(Color.ANTIQUEWHITE);
/*  903 */     rules.getChildren().add(bg);
/*  904 */     rules.getChildren().add(title);
/*      */     
/*  906 */     Text overview = new Text(
/*  907 */         "> Your goal is to beat your opponent by scoring more points. \n> You score points by melding cards, and making as many canastas as possible.\n> A canasta is a meld of at least seven cards of the same rank.\n\n> Each player starts with 15 cards in hand. Yours are visible at the bottom of the window.\n\n> Both players take turns in drawing one card from the deck, and discarding one card on the discard pile (in that order).\n> Both players take turns in drawing the first card.\n\n> After drawing a card, a player may meld cards if (s)he wants to.\n> Cards are melded in columns of at least three cards; e.g. you can meld three Kings, or four Fives.\n> You cannot meld sequences like Four-Five-Six. Once a card has been melded, it cannot be taken back into the hand.\n\n> When a player has melded his cards, he ends his turn by discarding a card.\n> At that point, his melded cards are checked to see if they conform to the canasta rules.\n> Discarding a card is not necessary if the player can go out by melding all of his cards.\n\n> Instead of drawing a card from the stock, a player may take the entire discard pile.\nHowever, this is only allowed if he can directly meld the top card.\n\n> A hand is over when one of the players has no cards left in his hand, or when there are no cards left on the deck.\n> The scores of both players are then computed, and a new hand is dealt.\n> A player can only finish a hand when he has at least one canasta.\n\n> A canasta match is over when one of the players reaches 5,000 points.");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  915 */     overview.setX(100.0D);
/*  916 */     overview.setY(150.0D);
/*  917 */     rules.getChildren().add(overview);
/*  918 */     rules.setVisible(false);
/*  919 */     return rules;
/*      */   }
/*      */ 
/*      */   
/*      */   public Group addRulesButton() {
/*  924 */     Group ruleButton = new Group();
/*  925 */     Text rules = new Text("Rules");
/*  926 */     rules.setFill(Color.BLUE);
/*  927 */     rules.setX(912.0D);
/*  928 */     rules.setY(715.0D);
/*  929 */     rules.setFont(Font.font((String)null, FontWeight.BOLD, 14.0D));
/*  930 */     rules.setOpacity(0.6D);
/*  931 */     ruleButton.getChildren().add(rules);
/*      */     
/*  933 */     Rectangle line = new Rectangle(912.0D, 718.0D, 37.0D, 1.0D);
/*  934 */     line.setFill(Color.BLUE);
/*  935 */     line.setOpacity(0.6D);
/*  936 */     ruleButton.getChildren().add(line);
/*      */     
/*  938 */     ruleButton.setOnMouseClicked(new EventHandler<MouseEvent>()
/*      */         {
/*      */           public void handle(MouseEvent arg0) {
/*  941 */             if (GamePageGUI.this.rulesGroup.isVisible()) {
/*  942 */               GamePageGUI.this.rulesGroup.setVisible(false);
/*      */             } else {
/*  944 */               GamePageGUI.this.rulesGroup.setVisible(true);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  950 */     return ruleButton;
/*      */   }
/*      */   
/*      */   public Text addTurnMarker() {
/*  954 */     this.turnMarker = new Text();
/*      */ 
/*      */     
/*  957 */     this.turnMarker.setEffect(ds());
/*  958 */     this.turnMarker.setCache(true);
/*  959 */     this.turnMarker.setX(410.0D);
/*  960 */     this.turnMarker.setY(50.0D);
/*  961 */     this.turnMarker.setFill(Color.DARKRED);
/*  962 */     this.turnMarker.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/*  963 */     this.turnMarker.setStroke(Color.BLACK);
/*      */ 
/*      */     
/*  966 */     if (game.getPlayerTurn() == playerID) {
/*  967 */       this.turnMarker.setText("Your Turn...");
/*      */     } else {
/*  969 */       this.turnMarker.setText("Opponents Go...");
/*      */     } 
/*      */     
/*  972 */     return this.turnMarker;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Hand getThisPlayerHand() {
/*  977 */     return ((Player)game.getTeams()[playerID].getPlayers().get(0)).getHand();
/*      */   }
/*      */ 
/*      */   
/*      */   public Stock getThisPlayerStock() {
/*  982 */     return game.getTeams()[playerID].getStock();
/*      */   }
/*      */ 
/*      */   
/*      */   public Stock getOpStock() {
/*  987 */     return game.getTeams()[this.opID].getStock();
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateHand() {
/*  992 */     this.sp.getChildren().remove(this.hand);
/*  993 */     addHandLoop();
/*  994 */     this.sp.getChildren().add(this.hand);
/*      */   }
/*      */   
/*      */   public void addHandLoop() {
/*  998 */     int handSize = getThisPlayerHand().getCardCount();
/*  999 */     this.hand = new Group();
/* 1000 */     for (int i = 0; i < handSize; i++) {
/* 1001 */       this.hand.getChildren().add(addHand(i, handSize));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateDiscard() {
/* 1007 */     this.sp.getChildren().remove(this.discard);
/* 1008 */     this.discard = addDiscard();
/* 1009 */     this.sp.getChildren().add(this.discard);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateMelds() {
/* 1014 */     this.sp.getChildren().remove(this.myMelds);
/* 1015 */     this.sp.getChildren().remove(this.opMelds);
/* 1016 */     meldsLoop();
/* 1017 */     this.sp.getChildren().add(this.myMelds);
/* 1018 */     this.sp.getChildren().add(this.opMelds);
/*      */   }
/*      */   
/*      */   public void meldsLoop() {
/* 1022 */     this.myMelds = new Group(); int i;
/* 1023 */     for (i = 0; i < getThisPlayerStock().getMeldList().size(); i++) {
/* 1024 */       int rank = ((Meld)getThisPlayerStock().getMeldList().get(i)).getRank();
/* 1025 */       int suit = ((Card)((Meld)getThisPlayerStock().getMeldList().get(i)).getCards().get(0)).getSuit();
/* 1026 */       if (suit == 4) {
/* 1027 */         suit = ((Card)((Meld)getThisPlayerStock().getMeldList().get(i)).getCards().get(1)).getSuit();
/*      */       }
/* 1029 */       boolean isCanasta = ((Meld)getThisPlayerStock().getMeldList().get(i)).isCanasta();
/* 1030 */       boolean isNatural = ((Meld)getThisPlayerStock().getMeldList().get(i)).isNatural();
/*      */       
/* 1032 */       int size = ((Meld)getThisPlayerStock().getMeldList().get(i)).getCards().size();
/* 1033 */       this.myMelds.getChildren().add(addMyMelds(i, rank, suit, size, isCanasta, isNatural));
/*      */     } 
/* 1035 */     this.opMelds = new Group();
/* 1036 */     for (i = 0; i < getOpStock().getMeldList().size(); i++) {
/* 1037 */       int rank = ((Meld)getOpStock().getMeldList().get(i)).getRank();
/* 1038 */       int suit = ((Card)((Meld)getOpStock().getMeldList().get(i)).getCards().get(0)).getSuit();
/* 1039 */       if (suit == 4) {
/* 1040 */         suit = ((Card)((Meld)getOpStock().getMeldList().get(i)).getCards().get(1)).getSuit();
/*      */       }
/* 1042 */       boolean isCanasta = ((Meld)getOpStock().getMeldList().get(i)).isCanasta();
/* 1043 */       boolean isNatural = ((Meld)getOpStock().getMeldList().get(i)).isNatural();
/*      */       
/* 1045 */       int size = ((Meld)getOpStock().getMeldList().get(i)).getCards().size();
/* 1046 */       this.opMelds.getChildren().add(addOpMelds(i, rank, suit, size, isCanasta, isNatural));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTurnMarker() {
/* 1053 */     if (game.getPlayerTurn() == playerID) {
/* 1054 */       this.turnMarker.setText("Your Turn...");
/*      */     } else {
/* 1056 */       this.turnMarker.setText("Opponents Go...");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Group createGame() {
/* 1062 */     this.sp = new Group();
/*      */     
/* 1064 */     this.sp.getChildren().add(gameBG());
/* 1065 */     this.sp.getChildren().add(addDeck());
/* 1066 */     this.sp.getChildren().add(addTurnMarker());
/* 1067 */     this.discard = addDiscard();
/* 1068 */     this.sp.getChildren().add(this.discard);
/* 1069 */     this.sp.getChildren().add(addButtons());
/* 1070 */     addHandLoop();
/* 1071 */     this.sp.getChildren().add(this.hand);
/* 1072 */     meldsLoop();
/* 1073 */     this.sp.getChildren().add(this.myMelds);
/* 1074 */     this.sp.getChildren().add(this.opMelds);
/* 1075 */     this.tips = getTips();
/* 1076 */     this.sp.getChildren().add(this.tips);
/* 1077 */     this.rulesGroup = getRules();
/* 1078 */     this.sp.getChildren().add(this.rulesGroup);
/* 1079 */     this.sp.getChildren().add(addHotBar());
/* 1080 */     this.sp.getChildren().add(addHelpButton());
/* 1081 */     this.sp.getChildren().add(addRulesButton());
/*      */     
/* 1083 */     return this.sp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start(Stage stage) {
/* 1091 */     stage.setTitle("game");
/* 1092 */     Group root = createGame();
/* 1093 */     Scene scene = new Scene(root, 1000.0D, 1000.0D);
/* 1094 */     stage.setScene(scene);
/* 1095 */     stage.show();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1102 */     game = new TwoPlayerLogic();
/* 1103 */     game.deal();
/*      */     
/* 1105 */     Card a = new Card(1, 0, 20, false);
/* 1106 */     game.setA(a);
/* 1107 */     game.discardCard();
/* 1108 */     Card b = new Card(1, 1, 20, false);
/* 1109 */     game.setA(b);
/* 1110 */     game.discardCard();
/* 1111 */     Card c = new Card(1, 2, 20, false);
/* 1112 */     game.setA(c);
/* 1113 */     game.discardCard();
/* 1114 */     Card d = new Card(1, 3, 20, false);
/*      */     
/* 1116 */     Card e = new Card(1, 0, 1, false);
/*      */     
/* 1118 */     Card f = new Card(1, 0, 1, false);
/*      */     
/* 1120 */     Card g = new Card(2, 0, 1, true);
/* 1121 */     game.setA(d);
/* 1122 */     game.discardCard();
/* 1123 */     game.setA(null);
/* 1124 */     game.findPlayerHand().drawCard(a);
/* 1125 */     game.findPlayerHand().drawCard(b);
/* 1126 */     System.out.println(game.getDiscard());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1153 */     launch(args);
/*      */   }
/*      */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\GamePageGUI.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */