/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import gameLogic.Meld;
/*     */ import gameLogic.TwoPlayerLogic;
/*     */ import java.io.IOException;
/*     */ import javafx.application.Application;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Group;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.effect.DropShadow;
/*     */ import javafx.scene.effect.Light;
/*     */ import javafx.scene.effect.Lighting;
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
/*     */ public class PostGameGUI
/*     */   extends Application implements PostGameInterface {
/*     */   private TwoPlayerLogic game;
/*     */   private ActiveGame aGame;
/*     */   private NewGame settings;
/*     */   private Group root;
/*     */   
/*     */   public PostGameGUI(ActiveGame aGame, NewGame settings) {
/*  35 */     this.game = aGame.getGame0();
/*  36 */     this.settings = settings;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     if (aGame.getThisUserOut().equals(ClientMain.client.getUsername())) {
/*     */       
/*  43 */       String username = ClientMain.client.getUsername();
/*     */       
/*  45 */       if (username.equals(settings.getHost())) {
/*  46 */         if (settings.getP1Team() == 0) {
/*  47 */           this.game.setTeam1Score(this.game.getTeam1Score() + 100);
/*     */         } else {
/*  49 */           this.game.setTeam2Score(this.game.getTeam2Score() + 100);
/*     */         } 
/*  51 */       } else if (username.equals(settings.getPlayer2())) {
/*  52 */         if (settings.getP2Team() == 0) {
/*  53 */           this.game.setTeam1Score(this.game.getTeam1Score() + 100);
/*     */         } else {
/*  55 */           this.game.setTeam2Score(this.game.getTeam2Score() + 100);
/*     */         } 
/*  57 */       } else if (username.equals(settings.getPlayer3())) {
/*  58 */         if (settings.getP3Team() == 0) {
/*  59 */           this.game.setTeam1Score(this.game.getTeam1Score() + 100);
/*     */         } else {
/*  61 */           this.game.setTeam2Score(this.game.getTeam2Score() + 100);
/*     */         }
/*     */       
/*  64 */       } else if (settings.getP4Team() == 0) {
/*  65 */         this.game.setTeam1Score(this.game.getTeam1Score() + 100);
/*     */       } else {
/*  67 */         this.game.setTeam2Score(this.game.getTeam2Score() + 100);
/*     */       } 
/*     */ 
/*     */       
/*  71 */       aGame.setTeam1Total(aGame.getTeam1Total() + this.game.getTeam1Score());
/*  72 */       aGame.setTeam2Total(aGame.getTeam2Total() + this.game.getTeam2Score());
/*     */       try {
/*  74 */         ClientMain.client.sendActiveGame(aGame);
/*  75 */       } catch (IOException e) {
/*  76 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/*  81 */       this.aGame = ClientMain.client.getActiveGame();
/*  82 */     } catch (IOException e1) {
/*  83 */       e1.printStackTrace();
/*     */     } 
/*     */     
/*     */     try {
/*  87 */       ClientMain.client.updateSavedGame(aGame.getTeam1Total(), aGame.getTeam2Total());
/*  88 */     } catch (IOException e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  92 */     this.root = createScene();
/*  93 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  94 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Group createScene() {
/* 100 */     Group sp = new Group();
/*     */     
/* 102 */     sp.getChildren().add(addBG());
/* 103 */     sp.getChildren().add(addText());
/* 104 */     sp.getChildren().add(addButtons());
/*     */     
/* 106 */     return sp;
/*     */   }
/*     */   
/*     */   public LinearGradient lg1() {
/* 110 */     Stop[] stops2 = { new Stop(0.0D, Color.RED), new Stop(1.0D, Color.PURPLE) };
/* 111 */     LinearGradient lg2 = new LinearGradient(0.0D, 0.0D, 1.0D, 0.0D, true, CycleMethod.NO_CYCLE, stops2);
/* 112 */     return lg2;
/*     */   }
/*     */   
/*     */   public LinearGradient lg2() {
/* 116 */     Stop[] stops3 = { new Stop(0.0D, Color.PURPLE), new Stop(1.0D, Color.BLUE) };
/* 117 */     LinearGradient lg3 = new LinearGradient(0.0D, 0.0D, 1.0D, 0.0D, true, CycleMethod.NO_CYCLE, stops3);
/* 118 */     return lg3;
/*     */   }
/*     */   
/*     */   public Lighting lighting() {
/* 122 */     Light.Distant light = new Light.Distant();
/* 123 */     light.setAzimuth(-135.0D);
/*     */     
/* 125 */     Lighting lighting = new Lighting();
/* 126 */     lighting.setLight(light);
/* 127 */     lighting.setSurfaceScale(5.0D);
/*     */     
/* 129 */     return lighting;
/*     */   }
/*     */   
/*     */   public DropShadow ds() {
/* 133 */     DropShadow ds = new DropShadow();
/* 134 */     ds.setOffsetY(3.0D);
/* 135 */     ds.setColor(Color.color(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D));
/* 136 */     return ds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Group addBG() {
/* 141 */     Group group = new Group();
/*     */     
/* 143 */     Rectangle board = new Rectangle(50.0D, 80.0D, 900.0D, 600.0D);
/* 144 */     board.setFill(Color.DARKOLIVEGREEN);
/* 145 */     board.setStroke(Color.BLACK);
/* 146 */     board.setEffect(ds());
/* 147 */     board.setArcHeight(10.0D);
/* 148 */     board.setArcWidth(10.0D);
/* 149 */     group.getChildren().add(board);
/*     */     
/* 151 */     Rectangle outerBack = new Rectangle(140.0D, 140.0D, 720.0D, 500.0D);
/* 152 */     outerBack.setFill(Color.DARKSLATEGREY);
/* 153 */     outerBack.setStroke(Color.BLACK);
/* 154 */     outerBack.setEffect(ds());
/* 155 */     outerBack.setArcHeight(10.0D);
/* 156 */     outerBack.setArcWidth(10.0D);
/* 157 */     group.getChildren().add(outerBack);
/*     */     
/* 159 */     Rectangle whiteSquare = new Rectangle(150.0D, 150.0D, 350.0D, 480.0D);
/* 160 */     whiteSquare.setFill(Color.FLORALWHITE);
/* 161 */     whiteSquare.setStroke(Color.BLACK);
/* 162 */     whiteSquare.setArcHeight(10.0D);
/* 163 */     whiteSquare.setArcWidth(10.0D);
/* 164 */     group.getChildren().add(whiteSquare);
/*     */     
/* 166 */     Rectangle whiteSquare2 = new Rectangle(500.0D, 150.0D, 350.0D, 480.0D);
/* 167 */     whiteSquare2.setFill(Color.FLORALWHITE);
/* 168 */     whiteSquare2.setStroke(Color.BLACK);
/* 169 */     whiteSquare2.setArcHeight(10.0D);
/* 170 */     whiteSquare2.setArcWidth(10.0D);
/* 171 */     group.getChildren().add(whiteSquare2);
/*     */     
/* 173 */     Rectangle strip1 = new Rectangle(200.0D, 150.0D, 250.0D, 480.0D);
/* 174 */     strip1.setStroke(Color.BLACK);
/* 175 */     strip1.setFill(lg1());
/* 176 */     group.getChildren().add(strip1);
/*     */     
/* 178 */     Rectangle strip2 = new Rectangle(550.0D, 150.0D, 250.0D, 480.0D);
/* 179 */     strip2.setStroke(Color.BLACK);
/* 180 */     strip2.setFill(lg2());
/* 181 */     group.getChildren().add(strip2);
/*     */     
/* 183 */     Rectangle strip3 = new Rectangle(204.0D, 154.0D, 242.0D, 472.0D);
/* 184 */     strip3.setFill(Color.FLORALWHITE);
/* 185 */     group.getChildren().add(strip3);
/*     */     
/* 187 */     Rectangle strip4 = new Rectangle(554.0D, 154.0D, 242.0D, 472.0D);
/* 188 */     strip4.setFill(Color.FLORALWHITE);
/* 189 */     group.getChildren().add(strip4);
/*     */     
/* 191 */     Rectangle winnerBack = new Rectangle(350.0D, 145.0D, 300.0D, 50.0D);
/* 192 */     winnerBack.setFill(Color.LIGHTSLATEGREY);
/* 193 */     winnerBack.setStroke(Color.BLACK);
/* 194 */     winnerBack.setEffect(ds());
/* 195 */     winnerBack.setArcHeight(10.0D);
/* 196 */     winnerBack.setArcWidth(10.0D);
/* 197 */     group.getChildren().add(winnerBack);
/*     */     
/* 199 */     Rectangle lineCircle1 = new Rectangle(253.0D, 291.0D, 130.0D, 2.0D);
/* 200 */     lineCircle1.setArcHeight(50.0D);
/* 201 */     lineCircle1.setArcWidth(100.0D);
/* 202 */     group.getChildren().add(lineCircle1);
/*     */     
/* 204 */     Rectangle lineCircle2 = new Rectangle(603.0D, 291.0D, 130.0D, 2.0D);
/* 205 */     lineCircle2.setArcHeight(50.0D);
/* 206 */     lineCircle2.setArcWidth(100.0D);
/* 207 */     group.getChildren().add(lineCircle2);
/*     */     
/* 209 */     Rectangle nameBack1 = new Rectangle(255.0D, 610.0D, 140.0D, 30.0D);
/* 210 */     nameBack1.setFill(Color.LIGHTSLATEGREY);
/* 211 */     nameBack1.setStroke(Color.BLACK);
/* 212 */     nameBack1.setEffect(ds());
/* 213 */     nameBack1.setArcHeight(10.0D);
/* 214 */     nameBack1.setArcWidth(10.0D);
/* 215 */     group.getChildren().add(nameBack1);
/*     */     
/* 217 */     Rectangle nameBack2 = new Rectangle(605.0D, 610.0D, 140.0D, 30.0D);
/* 218 */     nameBack2.setFill(Color.LIGHTSLATEGREY);
/* 219 */     nameBack2.setStroke(Color.BLACK);
/* 220 */     nameBack2.setEffect(ds());
/* 221 */     nameBack2.setArcHeight(10.0D);
/* 222 */     nameBack2.setArcWidth(10.0D);
/* 223 */     group.getChildren().add(nameBack2);
/*     */     
/* 225 */     return group;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Group addText() {
/* 231 */     Group group = new Group();
/*     */     
/* 233 */     Text gameover = new Text("GAME OVER");
/*     */ 
/*     */     
/* 236 */     gameover.setEffect(ds());
/* 237 */     gameover.setCache(true);
/* 238 */     gameover.setX(400.0D);
/* 239 */     gameover.setY(50.0D);
/* 240 */     gameover.setFill(Color.DARKRED);
/* 241 */     gameover.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/* 242 */     group.getChildren().add(gameover);
/*     */     
/* 244 */     if (this.aGame.getTeam1Total() > this.settings.getGoalScore() || this.aGame.getTeam2Total() > this.settings.getGoalScore()) {
/*     */       
/* 246 */       if (this.settings.getGameType() == 0)
/*     */       {
/* 248 */         if (this.aGame.getTeam1Total() > this.aGame.getTeam2Total()) {
/* 249 */           Text p1Win = new Text("Team 1 Wins!");
/* 250 */           p1Win.setX(400.0D);
/* 251 */           p1Win.setY(180.0D);
/* 252 */           p1Win.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/* 253 */           p1Win.setFill(Color.RED);
/* 254 */           p1Win.setEffect(lighting());
/* 255 */           p1Win.setStroke(Color.BLACK);
/* 256 */           group.getChildren().add(p1Win);
/*     */         } else {
/* 258 */           Text p2Win = new Text("Team 2 Wins");
/* 259 */           p2Win.setX(400.0D);
/* 260 */           p2Win.setY(180.0D);
/* 261 */           p2Win.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/* 262 */           p2Win.setFill(Color.RED);
/* 263 */           p2Win.setStroke(Color.BLACK);
/* 264 */           p2Win.setEffect(lighting());
/* 265 */           group.getChildren().add(p2Win);
/*     */         }
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 272 */       Text thisUserOut = new Text(String.valueOf(this.aGame.getThisUserOut()) + " went out!");
/* 273 */       thisUserOut.setX(400.0D);
/* 274 */       thisUserOut.setY(180.0D);
/* 275 */       thisUserOut.setFont(Font.font((String)null, FontWeight.BOLD, 32.0D));
/* 276 */       thisUserOut.setFill(Color.RED);
/* 277 */       thisUserOut.setEffect(lighting());
/* 278 */       thisUserOut.setStroke(Color.BLACK);
/* 279 */       group.getChildren().add(thisUserOut);
/*     */     } 
/*     */     
/* 282 */     Text team1Plus = new Text("+" + Integer.toString(this.game.getTeam2Score()));
/* 283 */     team1Plus.setX(601.0D);
/* 284 */     team1Plus.setY(290.0D);
/* 285 */     team1Plus.setFont(Font.font((String)null, FontWeight.BOLD, 40.0D));
/* 286 */     group.getChildren().add(team1Plus);
/*     */     
/* 288 */     Text team1Score = new Text(Integer.toString(this.aGame.getTeam2Total()));
/* 289 */     team1Score.setX(626.0D);
/* 290 */     team1Score.setY(325.0D);
/* 291 */     team1Score.setFont(Font.font((String)null, FontWeight.BOLD, 40.0D));
/* 292 */     group.getChildren().add(team1Score);
/*     */     
/* 294 */     Text team2Plus = new Text("+" + Integer.toString(this.game.getTeam1Score()));
/* 295 */     team2Plus.setX(251.0D);
/* 296 */     team2Plus.setY(290.0D);
/* 297 */     team2Plus.setFont(Font.font((String)null, FontWeight.BOLD, 40.0D));
/* 298 */     group.getChildren().add(team2Plus);
/*     */ 
/*     */     
/* 301 */     Text team2Score = new Text(Integer.toString(this.aGame.getTeam1Total()));
/* 302 */     team2Score.setX(276.0D);
/* 303 */     team2Score.setY(325.0D);
/* 304 */     team2Score.setFont(Font.font((String)null, FontWeight.BOLD, 40.0D));
/* 305 */     group.getChildren().add(team2Score);
/*     */     
/* 307 */     Text canastas = new Text("Canastas:");
/* 308 */     canastas.setX(205.0D);
/* 309 */     canastas.setY(375.0D);
/* 310 */     canastas.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 311 */     group.getChildren().add(canastas);
/*     */     
/* 313 */     Text players1 = new Text("Players:");
/* 314 */     players1.setX(205.0D);
/* 315 */     players1.setY(525.0D);
/* 316 */     players1.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 317 */     group.getChildren().add(players1);
/*     */     
/* 319 */     Text canastas2 = new Text("Canastas:");
/* 320 */     canastas2.setX(556.0D);
/* 321 */     canastas2.setY(375.0D);
/* 322 */     canastas2.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 323 */     group.getChildren().add(canastas2);
/*     */     
/* 325 */     Text players2 = new Text("Players:");
/* 326 */     players2.setX(556.0D);
/* 327 */     players2.setY(525.0D);
/* 328 */     players2.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 329 */     group.getChildren().add(players2);
/*     */     
/* 331 */     int numberOfCanastas = 0;
/*     */     int i;
/* 333 */     for (i = 0; i < this.game.getTeams()[0].getStock().getMeldList().size(); i++) {
/* 334 */       if (((Meld)this.game.getTeams()[0].getStock().getMeldList().get(i)).isCanasta()) {
/* 335 */         numberOfCanastas++;
/* 336 */         Text achievedCanastas = new Text();
/*     */         
/* 338 */         int meldRank = ((Meld)this.game.getTeams()[0].getStock().getMeldList().get(i)).getRank();
/* 339 */         String rank = Integer.toString(meldRank);
/*     */         
/* 341 */         if (meldRank == 1) {
/* 342 */           rank = "ACE";
/* 343 */         } else if (meldRank == 11) {
/* 344 */           rank = "JACK";
/* 345 */         } else if (meldRank == 12) {
/* 346 */           rank = "QUEEN";
/* 347 */         } else if (meldRank == 13) {
/* 348 */           rank = "KING";
/*     */         } 
/*     */         
/* 351 */         rank = String.valueOf(rank) + "'s";
/*     */         
/* 353 */         achievedCanastas.setX(266.0D);
/* 354 */         achievedCanastas.setY(390.0D + (numberOfCanastas * 20));
/* 355 */         achievedCanastas.setFont(Font.font((String)null, FontWeight.BOLD, 16.0D));
/*     */ 
/*     */         
/* 358 */         if (((Meld)this.game.getTeams()[0].getStock().getMeldList().get(i)).isNatural()) {
/* 359 */           achievedCanastas.setFill(Color.INDIANRED);
/* 360 */           rank = String.valueOf(rank) + " - (Natural)";
/*     */         } else {
/*     */           
/* 363 */           rank = String.valueOf(rank) + " - (Wild)";
/*     */         } 
/*     */         
/* 366 */         achievedCanastas.setText(rank);
/*     */         
/* 368 */         group.getChildren().add(achievedCanastas);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 373 */     if (numberOfCanastas == 0) {
/* 374 */       Text na = new Text("N/A");
/* 375 */       na.setX(266.0D);
/* 376 */       na.setY(390.0D);
/* 377 */       na.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 378 */       group.getChildren().add(na);
/*     */     } 
/*     */     
/* 381 */     numberOfCanastas = 0;
/*     */     
/* 383 */     for (i = 0; i < this.game.getTeams()[1].getStock().getMeldList().size(); i++) {
/* 384 */       if (((Meld)this.game.getTeams()[1].getStock().getMeldList().get(i)).isCanasta()) {
/* 385 */         numberOfCanastas++;
/* 386 */         Text achievedCanastas = new Text();
/*     */         
/* 388 */         int meldRank = ((Meld)this.game.getTeams()[1].getStock().getMeldList().get(i)).getRank();
/* 389 */         String rank = Integer.toString(meldRank);
/*     */         
/* 391 */         if (meldRank == 1) {
/* 392 */           rank = "ACE";
/* 393 */         } else if (meldRank == 11) {
/* 394 */           rank = "JACK";
/* 395 */         } else if (meldRank == 12) {
/* 396 */           rank = "QUEEN";
/* 397 */         } else if (meldRank == 13) {
/* 398 */           rank = "KING";
/*     */         } 
/*     */         
/* 401 */         rank = String.valueOf(rank) + "'s";
/*     */         
/* 403 */         achievedCanastas.setText(rank);
/* 404 */         achievedCanastas.setX(616.0D);
/* 405 */         achievedCanastas.setY(390.0D + (numberOfCanastas * 20));
/* 406 */         achievedCanastas.setFont(Font.font((String)null, FontWeight.BOLD, 16.0D));
/*     */ 
/*     */         
/* 409 */         if (((Meld)this.game.getTeams()[1].getStock().getMeldList().get(i)).isNatural()) {
/* 410 */           achievedCanastas.setFill(Color.INDIANRED);
/* 411 */           rank = String.valueOf(rank) + " - (Natural)";
/*     */         } else {
/*     */           
/* 414 */           rank = String.valueOf(rank) + " - (Wild)";
/*     */         } 
/*     */         
/* 417 */         achievedCanastas.setText(rank);
/*     */         
/* 419 */         group.getChildren().add(achievedCanastas);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 424 */     if (numberOfCanastas == 0) {
/* 425 */       Text na = new Text("N/A");
/* 426 */       na.setX(616.0D);
/* 427 */       na.setY(390.0D);
/* 428 */       na.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 429 */       group.getChildren().add(na);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 434 */     Text name1 = new Text(this.settings.getHost());
/* 435 */     name1.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 436 */     if (this.settings.getP1Team() == 0) {
/* 437 */       name1.setX(266.0D);
/* 438 */       name1.setY(550.0D);
/*     */     } else {
/* 440 */       name1.setX(616.0D);
/* 441 */       name1.setY(550.0D);
/*     */     } 
/*     */     
/* 444 */     Text name2 = new Text(this.settings.getPlayer2());
/* 445 */     name2.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 446 */     if (this.settings.getP2Team() == 0 && this.settings.getP1Team() == 0) {
/* 447 */       name2.setX(266.0D);
/* 448 */       name2.setY(570.0D);
/* 449 */     } else if (this.settings.getP2Team() == 0) {
/* 450 */       name2.setX(266.0D);
/* 451 */       name2.setY(550.0D);
/* 452 */     } else if (this.settings.getP2Team() == 1 && this.settings.getP1Team() == 0) {
/* 453 */       name2.setX(616.0D);
/* 454 */       name2.setY(550.0D);
/*     */     } else {
/* 456 */       name2.setX(616.0D);
/* 457 */       name2.setY(570.0D);
/*     */     } 
/* 459 */     group.getChildren().add(name1);
/* 460 */     group.getChildren().add(name2);
/*     */     
/* 462 */     if (this.settings.getGameType() == 1) {
/*     */       
/* 464 */       Text name3 = new Text(this.settings.getPlayer3());
/* 465 */       name3.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 466 */       if (this.settings.getP1Team() == 0 && this.settings.getP2Team() == 1) {
/* 467 */         if (this.settings.getP3Team() == 0) {
/* 468 */           name3.setX(266.0D);
/* 469 */           name3.setY(570.0D);
/*     */         } else {
/* 471 */           name3.setX(616.0D);
/* 472 */           name3.setY(570.0D);
/*     */         } 
/* 474 */       } else if (this.settings.getP3Team() == 0) {
/* 475 */         name3.setX(266.0D);
/* 476 */         name3.setY(550.0D);
/*     */       } else {
/* 478 */         name3.setX(616.0D);
/* 479 */         name3.setY(550.0D);
/*     */       } 
/*     */       
/* 482 */       Text name4 = new Text(this.settings.getPlayer4());
/* 483 */       name4.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/* 484 */       if (this.settings.getP4Team() == 0) {
/* 485 */         name4.setX(266.0D);
/* 486 */         name4.setY(570.0D);
/*     */       } else {
/* 488 */         name4.setX(616.0D);
/* 489 */         name4.setY(570.0D);
/*     */       } 
/*     */       
/* 492 */       group.getChildren().add(name3);
/* 493 */       group.getChildren().add(name4);
/*     */     } 
/*     */ 
/*     */     
/* 497 */     Text team1 = new Text("Team 1");
/* 498 */     team1.setX(295.0D);
/* 499 */     team1.setY(630.0D);
/* 500 */     team1.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/*     */     
/* 502 */     group.getChildren().add(team1);
/*     */     
/* 504 */     Text team2 = new Text("Team 2");
/* 505 */     team2.setX(641.0D);
/* 506 */     team2.setY(630.0D);
/* 507 */     team2.setFont(Font.font((String)null, FontWeight.BOLD, 18.0D));
/*     */     
/* 509 */     group.getChildren().add(team2);
/*     */     
/* 511 */     return group;
/*     */   }
/*     */ 
/*     */   
/*     */   public Group addButtons() {
/* 516 */     Group group = new Group();
/*     */     
/* 518 */     Button playAgain = new Button();
/* 519 */     playAgain.setText("Continue...");
/* 520 */     playAgain.setLayoutX(100.0D);
/* 521 */     playAgain.setLayoutY(100.0D);
/* 522 */     playAgain.setOnAction(e -> {
/*     */           this.settings.setGameStarted(false);
/*     */ 
/*     */           
/*     */           try {
/*     */             ClientMain.client.updateSettings(this.settings);
/*     */ 
/*     */             
/*     */             ClientMain.client.continueGame();
/* 531 */           } catch (IOException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */ 
/*     */           
/*     */           try {
/*     */             playAgainMethod();
/* 538 */           } catch (IOException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 544 */     if (this.aGame.getTeam1Total() < this.settings.getGoalScore() && this.aGame.getTeam2Total() < this.settings.getGoalScore()) {
/*     */       
/* 546 */       playAgain.setVisible(true);
/*     */     } else {
/* 548 */       playAgain.setVisible(false);
/*     */     } 
/* 550 */     group.getChildren().add(playAgain);
/*     */     
/* 552 */     Button quit = new Button();
/* 553 */     quit.setText("Save & Quit");
/* 554 */     quit.setLayoutX(810.0D);
/* 555 */     quit.setLayoutY(100.0D);
/* 556 */     quit.setOnAction(e -> {
/*     */           try {
/*     */             ClientMain.client.saveAndQuit();
/* 559 */           } catch (IOException e1) {
/*     */             e1.printStackTrace();
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 565 */     group.getChildren().add(quit);
/*     */     
/* 567 */     return group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playAgainMethod() throws IOException {
/*     */     while (true) {
/* 575 */       this.settings = ClientMain.client.getCurrentGLobby();
/*     */ 
/*     */       
/* 578 */       if (ClientMain.client.checkPlayersConnected(this.settings.getGameType())) {
/*     */ 
/*     */ 
/*     */         
/* 582 */         if (ClientMain.client.checkContinue(this.settings.getGameType())) {
/*     */ 
/*     */ 
/*     */           
/* 586 */           if (ClientMain.client.getUsername().equals(this.settings.getHost())) {
/*     */ 
/*     */             
/* 589 */             TwoPlayerLogic newGame = new TwoPlayerLogic();
/* 590 */             newGame.deal();
/*     */             
/* 592 */             this.aGame = ClientMain.client.getActiveGame();
/* 593 */             this.aGame.setRoundOver(false);
/*     */             
/* 595 */             this.aGame.setGame0(newGame);
/* 596 */             ClientMain.client.sendActiveGame(this.aGame);
/*     */ 
/*     */             
/* 599 */             this.settings.setGameStarted(true);
/* 600 */             ClientMain.client.updateSettings(this.settings);
/*     */           } 
/*     */           
/* 603 */           if (this.settings.isGameStarted()) {
/* 604 */             this.aGame = ClientMain.client.getActiveGame();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 617 */         ClientMain.client.saveAndQuit();
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/*     */       try {
/* 623 */         Thread.sleep(5000L);
/* 624 */       } catch (InterruptedException e) {
/* 625 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void start(Stage stage) {}
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\PostGameGUI.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */