/*     */ package gui;
/*     */ 
/*     */ import gameLogic.Card;
/*     */ import gameLogic.Meld;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameGuiMethods2P
/*     */ {
/*     */   private static ArrayList<Card> downCards;
/*     */   
/*     */   public static void deck() {
/*  17 */     cancel();
/*  18 */     if (!GamePageGUI.game.isDrawnCard()) {
/*  19 */       Card drawnCard = GamePageGUI.game.getDeck().getCards()
/*  20 */         .get(GamePageGUI.game.getDeck().getCards().size() - 1);
/*  21 */       GamePageGUI.game.pickUpFromDeck();
/*  22 */       log("Picked " + drawnCard.stringRank() + " up from the deck");
/*     */     } else {
/*  24 */       log("Already picked up");
/*     */     } 
/*  26 */     swapRed3();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void swapRed3() {
/*  31 */     ArrayList<Card> newCards = GamePageGUI.game.handleRed3();
/*     */     
/*  33 */     for (int i = 0; i < newCards.size(); i++) {
/*  34 */       log("Swapped red 3 for " + ((Card)newCards.get(i)).stringRank());
/*     */       
/*  36 */       if (((Card)newCards.get(i)).getRank() == 3 && ((Card)newCards.get(i)).getWild()) {
/*  37 */         ArrayList<Card> newCards2 = GamePageGUI.game.handleRed3();
/*  38 */         for (int l = 0; l < newCards2.size(); l++) {
/*  39 */           log("Swapped red 3 for " + ((Card)newCards2.get(l)).stringRank());
/*     */           
/*  41 */           if (((Card)newCards2.get(l)).getRank() == 3 && ((Card)newCards2.get(l)).getWild()) {
/*  42 */             ArrayList<Card> newCards3 = GamePageGUI.game.handleRed3();
/*  43 */             for (int j = 0; j < newCards3.size(); j++) {
/*  44 */               log("Swapped red 3 for " + ((Card)newCards3.get(j)).stringRank());
/*     */               
/*  46 */               if (((Card)newCards3.get(j)).getRank() == 3 && ((Card)newCards3.get(j)).getWild()) {
/*  47 */                 ArrayList<Card> newCards4 = GamePageGUI.game.handleRed3();
/*  48 */                 for (int k = 0; k < newCards4.size(); k++) {
/*  49 */                   log("Swapped red 3 for " + ((Card)newCards4.get(k)).stringRank());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discard() {
/*  65 */     if (GamePageGUI.game.isDrawnCard()) {
/*     */       
/*  67 */       if (GamePageGUI.game.getA() != null) {
/*     */         
/*  69 */         GamePageGUI.game.discardCard();
/*  70 */         log("Discarded " + GamePageGUI.game.getA().stringRank());
/*  71 */         GamePageGUI.game.setA(null);
/*  72 */         if (GamePageGUI.game.getB() != null) {
/*  73 */           GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getB());
/*     */         }
/*  75 */         GamePageGUI.game.setB(null);
/*  76 */         if (GamePageGUI.game.getC() != null) {
/*  77 */           GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getC());
/*     */         }
/*  79 */         GamePageGUI.game.setC(null);
/*     */       } else {
/*     */         
/*  82 */         log("Select a card first");
/*     */       } 
/*  84 */     } else if (GamePageGUI.game.findPlayerStock()
/*  85 */       .alreadyMeld(GamePageGUI.game.getDiscard().checkTopCard().getRank())) {
/*     */       
/*  87 */       if (GamePageGUI.game.findPlayerHand().greaterThanOne() || GamePageGUI.game.getDiscard().cardsLeft() > 1 || 
/*  88 */         GamePageGUI.game.findPlayerStock().checkForCanasta()) {
/*     */ 
/*     */         
/*  91 */         GamePageGUI.game.pickUpFromDiscardMeld();
/*  92 */         log("Picked up from discard");
/*  93 */         swapRed3();
/*     */       } else {
/*  95 */         log("1. not enough cards in hand or discard, make a canasta before going out");
/*     */       }
/*     */     
/*  98 */     } else if (GamePageGUI.game.getA() != null && GamePageGUI.game.getB() != null) {
/*     */       
/* 100 */       if (GamePageGUI.game.findPlayerHand().greaterThanThree() || GamePageGUI.game.getDiscard().cardsLeft() > 2 || 
/* 101 */         GamePageGUI.game.findPlayerStock().checkForCanasta()) {
/*     */         
/* 103 */         if (GamePageGUI.game.getA().getWild() && GamePageGUI.game.checkWildStatus()) {
/*     */           
/* 105 */           if (GamePageGUI.game.getA() != null) {
/* 106 */             GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getA());
/*     */           }
/* 108 */           GamePageGUI.game.setA(null);
/* 109 */           if (GamePageGUI.game.getB() != null) {
/* 110 */             GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getB());
/*     */           }
/* 112 */           GamePageGUI.game.setB(null);
/* 113 */           if (GamePageGUI.game.getC() != null) {
/* 114 */             GamePageGUI.game.getDiscard().addCard(GamePageGUI.game.getC());
/*     */           }
/* 116 */           GamePageGUI.game.setC(null);
/* 117 */           log("Deck is wild - use two natural cards");
/*     */         }
/* 119 */         else if (GamePageGUI.game.getB().getWild() && GamePageGUI.game.checkWildStatus()) {
/*     */           
/* 121 */           if (GamePageGUI.game.getA() != null) {
/* 122 */             GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getA());
/*     */           }
/* 124 */           GamePageGUI.game.setA(null);
/* 125 */           if (GamePageGUI.game.getB() != null) {
/* 126 */             GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getB());
/*     */           }
/* 128 */           GamePageGUI.game.setB(null);
/* 129 */           if (GamePageGUI.game.getC() != null) {
/* 130 */             GamePageGUI.game.getDiscard().addCard(GamePageGUI.game.getC());
/*     */           }
/* 132 */           GamePageGUI.game.setC(null);
/* 133 */           log("Deck is wild - use two natural cards");
/*     */         }
/* 135 */         else if (GamePageGUI.game.getA().getRank() == GamePageGUI.game.getDiscard().checkTopCard().getRank() && 
/* 136 */           GamePageGUI.game.getB().getRank() == GamePageGUI.game.getDiscard().checkTopCard()
/* 137 */           .getRank()) {
/*     */ 
/*     */           
/* 140 */           GamePageGUI.game.pickUpFromDiscard();
/* 141 */           log("Meld of " + GamePageGUI.game.getA().getRank() + " Created");
/* 142 */           GamePageGUI.game.setA(null);
/* 143 */           GamePageGUI.game.setB(null);
/* 144 */           GamePageGUI.game.setC(null);
/* 145 */           swapRed3();
/*     */         }
/* 147 */         else if ((GamePageGUI.game.getA().getWild() && 
/* 148 */           GamePageGUI.game.getB().getRank() == GamePageGUI.game.getDiscard().checkTopCard().getRank()) || (
/* 149 */           GamePageGUI.game.getB().getWild() && GamePageGUI.game.getA()
/* 150 */           .getRank() == GamePageGUI.game.getDiscard().checkTopCard().getRank())) {
/*     */ 
/*     */           
/* 153 */           GamePageGUI.game.pickUpFromDiscardWild();
/* 154 */           log("Meld of " + GamePageGUI.game.getC().getRank() + " Created");
/* 155 */           GamePageGUI.game.setA(null);
/* 156 */           GamePageGUI.game.setB(null);
/* 157 */           GamePageGUI.game.setC(null);
/* 158 */           swapRed3();
/*     */         } else {
/*     */           
/* 161 */           cancel();
/* 162 */           log("Cards dont match");
/*     */         } 
/*     */       } else {
/*     */         
/* 166 */         cancel();
/* 167 */         log("Select two cards first");
/*     */       } 
/*     */     } else {
/* 170 */       log("2. not enough cards in hand or discard, make a canasta before going out");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean downDiscard() {
/* 176 */     if (GamePageGUI.game.isDrawnCard()) {
/*     */       
/* 178 */       if (GamePageGUI.game.getDown1() != null) {
/*     */         
/* 180 */         GamePageGUI.game.getDiscard().addCard(GamePageGUI.game.getDown1());
/* 181 */         log("Discarded " + GamePageGUI.game.getDown1().stringRank());
/* 182 */         GamePageGUI.game.setDown1(null);
/* 183 */         buttonDownCancel();
/* 184 */         GamePageGUI.game.toggleTurn();
/* 185 */         return false;
/*     */       } 
/* 187 */       log("Select a card first");
/* 188 */       return false;
/*     */     } 
/*     */     
/* 191 */     if (!GamePageGUI.game.getDiscard().checkTopCard().getWild() || 
/* 192 */       GamePageGUI.game.getDiscard().checkTopCard().getRank() != 3) {
/* 193 */       initializeDownCards();
/* 194 */       downCards.add(GamePageGUI.game.getDiscard().checkTopCard());
/* 195 */       if (goDownDiscard()) {
/* 196 */         GamePageGUI.game.getDiscard().dealCard();
/* 197 */         int remainingCards = GamePageGUI.game.getDiscard().cardsLeft();
/* 198 */         for (int i = 0; i < remainingCards; i++) {
/* 199 */           GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getDiscard().dealCard());
/*     */         }
/* 201 */         GamePageGUI.game.setDrawnCard(true);
/* 202 */         return true;
/*     */       } 
/* 204 */       log("Failed to go down using the discard pile top card");
/* 205 */       buttonDownCancel();
/* 206 */       return false;
/*     */     } 
/*     */     
/* 209 */     log("Cant go down using top card");
/* 210 */     buttonDownCancel();
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hand(int rank) {
/* 220 */     String rankToString = Integer.toString(rank);
/*     */     
/* 222 */     if (rank == 1) {
/* 223 */       rankToString = "Ace";
/* 224 */     } else if (rank == 11) {
/* 225 */       rankToString = "Jack";
/* 226 */     } else if (rank == 12) {
/* 227 */       rankToString = "Queen";
/* 228 */     } else if (rank == 13) {
/* 229 */       rankToString = "King";
/* 230 */     } else if (rank == 14) {
/* 231 */       rankToString = "Joker";
/*     */     } 
/*     */     
/* 234 */     if (GamePageGUI.game.getA() == null || GamePageGUI.game.getB() == null || GamePageGUI.game.getC() == null) {
/* 235 */       if (GamePageGUI.game.getA() == null) {
/* 236 */         GamePageGUI.game.setA(GamePageGUI.getThisPlayerHand().getCardSameRank(rank));
/* 237 */         GamePageGUI.game.findPlayerHand().discardCard(GamePageGUI.game.getA());
/* 238 */         log("Selected " + rankToString);
/* 239 */         return true;
/* 240 */       }  if (GamePageGUI.game.getB() == null) {
/* 241 */         GamePageGUI.game.setB(GamePageGUI.getThisPlayerHand().getCardSameRank(rank));
/* 242 */         GamePageGUI.game.findPlayerHand().discardCard(GamePageGUI.game.getB());
/* 243 */         log("Selected " + rankToString);
/* 244 */         return true;
/* 245 */       }  if (GamePageGUI.game.getC() == null) {
/* 246 */         GamePageGUI.game.setC(GamePageGUI.getThisPlayerHand().getCardSameRank(rank));
/* 247 */         GamePageGUI.game.findPlayerHand().discardCard(GamePageGUI.game.getC());
/* 248 */         log("Selected " + rankToString);
/* 249 */         return true;
/*     */       } 
/* 251 */       log("Selected to many cards");
/* 252 */       return false;
/*     */     } 
/*     */     
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean downPickUp(int rank) {
/* 261 */     String rankToString = Integer.toString(rank);
/*     */     
/* 263 */     if (rank == 1) {
/* 264 */       rankToString = "Ace";
/* 265 */     } else if (rank == 11) {
/* 266 */       rankToString = "Jack";
/* 267 */     } else if (rank == 12) {
/* 268 */       rankToString = "Queen";
/* 269 */     } else if (rank == 13) {
/* 270 */       rankToString = "King";
/* 271 */     } else if (rank == 14) {
/* 272 */       rankToString = "Joker";
/*     */     } 
/*     */     
/* 275 */     Card selectedCard = GamePageGUI.getThisPlayerHand().getCardSameRank(rank);
/* 276 */     if (GamePageGUI.game.setDownCards(selectedCard)) {
/* 277 */       GamePageGUI.game.findPlayerHand().discardCard(selectedCard);
/* 278 */       log("Selected " + rankToString);
/*     */     } else {
/* 280 */       log("Selected to many cards");
/* 281 */       return false;
/*     */     } 
/*     */     
/* 284 */     return true;
/*     */   }
/*     */   
/*     */   public static void initializeDownCards() {
/* 288 */     downCards = new ArrayList<>();
/*     */     
/* 290 */     if (GamePageGUI.game.getDown1() != null) {
/* 291 */       downCards.add(GamePageGUI.game.getDown1());
/*     */     }
/* 293 */     if (GamePageGUI.game.getDown2() != null) {
/* 294 */       downCards.add(GamePageGUI.game.getDown2());
/*     */     }
/* 296 */     if (GamePageGUI.game.getDown3() != null) {
/* 297 */       downCards.add(GamePageGUI.game.getDown3());
/*     */     }
/* 299 */     if (GamePageGUI.game.getDown4() != null) {
/* 300 */       downCards.add(GamePageGUI.game.getDown4());
/*     */     }
/* 302 */     if (GamePageGUI.game.getDown5() != null) {
/* 303 */       downCards.add(GamePageGUI.game.getDown5());
/*     */     }
/*     */     
/* 306 */     if (GamePageGUI.game.getDown6() != null) {
/* 307 */       downCards.add(GamePageGUI.game.getDown6());
/*     */     }
/* 309 */     if (GamePageGUI.game.getDown7() != null) {
/* 310 */       downCards.add(GamePageGUI.game.getDown7());
/*     */     }
/* 312 */     if (GamePageGUI.game.getDown8() != null) {
/* 313 */       downCards.add(GamePageGUI.game.getDown8());
/*     */     }
/* 315 */     if (GamePageGUI.game.getDown9() != null) {
/* 316 */       downCards.add(GamePageGUI.game.getDown9());
/*     */     }
/* 318 */     if (GamePageGUI.game.getDown10() != null) {
/* 319 */       downCards.add(GamePageGUI.game.getDown10());
/*     */     }
/*     */     
/* 322 */     if (GamePageGUI.game.getDown11() != null) {
/* 323 */       downCards.add(GamePageGUI.game.getDown11());
/*     */     }
/* 325 */     if (GamePageGUI.game.getDown12() != null) {
/* 326 */       downCards.add(GamePageGUI.game.getDown12());
/*     */     }
/* 328 */     if (GamePageGUI.game.getDown13() != null) {
/* 329 */       downCards.add(GamePageGUI.game.getDown13());
/*     */     }
/* 331 */     if (GamePageGUI.game.getDown14() != null) {
/* 332 */       downCards.add(GamePageGUI.game.getDown14());
/*     */     }
/* 334 */     if (GamePageGUI.game.getDown15() != null) {
/* 335 */       downCards.add(GamePageGUI.game.getDown15());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean goDown() {
/* 342 */     initializeDownCards();
/*     */     
/* 344 */     int pointsTotal = 0;
/*     */     
/* 346 */     for (int i = 0; i < downCards.size(); i++) {
/* 347 */       if (downCards.get(i) != null) {
/* 348 */         pointsTotal += ((Card)downCards.get(i)).getPoints();
/*     */       }
/*     */     } 
/*     */     
/* 352 */     System.out.println("pointsTotal:" + pointsTotal);
/*     */     
/* 354 */     boolean sizeReached = false;
/* 355 */     boolean rankChange = false;
/*     */     
/* 357 */     int lastRank = ((Card)downCards.get(0)).getRank();
/* 358 */     int counter = 0;
/*     */     
/* 360 */     if (pointsTotal >= GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDownPoints()) {
/* 361 */       System.out.println("downCards.size() = " + downCards.size());
/*     */       
/* 363 */       for (int k = 0; k <= downCards.size(); k++) {
/* 364 */         System.out.println("kLoop = " + k);
/*     */         
/*     */         try {
/* 367 */           if (((Card)downCards.get(k)).getRank() != lastRank) {
/* 368 */             System.out.println("rankCheck");
/*     */             
/* 370 */             if (((Card)downCards.get(k)).getWild()) {
/* 371 */               counter++;
/* 372 */               System.out.println("counter:" + counter);
/*     */             } else {
/* 374 */               rankChange = true;
/* 375 */               System.out.println("rankChange2");
/*     */             } 
/*     */           } else {
/*     */             
/* 379 */             counter++;
/* 380 */             System.out.println("counter:" + counter);
/*     */           } 
/* 382 */         } catch (IndexOutOfBoundsException e) {
/* 383 */           System.out.println("caught");
/*     */         } 
/*     */         
/* 386 */         if (k >= downCards.size() - 1 && !rankChange) {
/* 387 */           rankChange = true;
/* 388 */           sizeReached = true;
/* 389 */           System.out.println("rankChange1");
/*     */         } 
/*     */         
/* 392 */         if (rankChange) {
/* 393 */           System.out.println("Initiate process");
/* 394 */           if (sizeReached) {
/* 395 */             System.out.println("Size reached, increase k");
/* 396 */             k++;
/*     */           } 
/*     */           
/* 399 */           if (counter >= 3) {
/* 400 */             System.out.println("process 1");
/* 401 */             GamePageGUI.game.setA(downCards.get(k - counter));
/* 402 */             System.out.println(GamePageGUI.game.getA());
/* 403 */             counter--;
/* 404 */             GamePageGUI.game.setB(downCards.get(k - counter));
/* 405 */             System.out.println(GamePageGUI.game.getB());
/* 406 */             counter--;
/* 407 */             GamePageGUI.game.setC(downCards.get(k - counter));
/* 408 */             System.out.println(GamePageGUI.game.getC());
/* 409 */             counter--;
/*     */             
/* 411 */             createMeld();
/*     */             
/* 413 */             while (counter > 0) {
/* 414 */               GamePageGUI.game.setA(downCards.get(k - counter));
/* 415 */               if (GamePageGUI.game.getA().getWild() || GamePageGUI.game.getA().getRank() == lastRank) {
/*     */                 try {
/* 417 */                   meld(lastRank);
/* 418 */                 } catch (IllegalArgumentException e) {
/* 419 */                   downCancel();
/* 420 */                   log("Tried inserting to many wildCards into a meld");
/* 421 */                   return false;
/*     */                 } 
/*     */               } else {
/*     */                 
/* 425 */                 downCancel();
/* 426 */                 log("Invalid selection 2");
/* 427 */                 return false;
/*     */               } 
/* 429 */               counter--;
/*     */             } 
/*     */             
/* 432 */             GamePageGUI.game.setA(null);
/*     */           } else {
/*     */             
/* 435 */             downCancel();
/* 436 */             log("Invalid selection 3");
/* 437 */             return false;
/*     */           } 
/*     */         } 
/*     */         
/* 441 */         if (rankChange) {
/* 442 */           System.out.println("Process 2");
/* 443 */           counter = 1;
/* 444 */           System.out.println("Counter:" + counter);
/* 445 */           System.out.println("k = " + k);
/* 446 */           rankChange = false;
/* 447 */           if (!sizeReached) {
/* 448 */             lastRank = ((Card)downCards.get(k)).getRank();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/* 453 */       downCancel();
/* 454 */       log("Not enough points, " + GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDownPoints() + 
/* 455 */           " needed.");
/* 456 */       return false;
/*     */     } 
/* 458 */     GamePageGUI.game.setDownCards();
/* 459 */     GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].setDown(true);
/* 460 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean goDownDiscard() {
/* 466 */     int pointsTotal = 0;
/*     */     
/* 468 */     for (int i = 0; i < downCards.size(); i++) {
/* 469 */       if (downCards.get(i) != null) {
/* 470 */         pointsTotal += ((Card)downCards.get(i)).getPoints();
/*     */       }
/*     */     } 
/*     */     
/* 474 */     System.out.println("pointsTotal:" + pointsTotal);
/*     */     
/* 476 */     boolean sizeReached = false;
/* 477 */     boolean rankChange = false;
/* 478 */     int lastRank = ((Card)downCards.get(0)).getRank();
/* 479 */     int counter = 0;
/*     */     
/* 481 */     if (pointsTotal >= GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDownPoints()) {
/* 482 */       System.out.println("downCards.size() = " + downCards.size());
/*     */       
/* 484 */       for (int k = 0; k <= downCards.size(); k++) {
/* 485 */         System.out.println("kLoop = " + k);
/*     */         
/*     */         try {
/* 488 */           if (((Card)downCards.get(k)).getRank() != lastRank) {
/* 489 */             System.out.println("rankCheck");
/*     */             
/* 491 */             if (((Card)downCards.get(k)).getWild()) {
/* 492 */               counter++;
/* 493 */               System.out.println("counter:" + counter);
/*     */             } else {
/* 495 */               rankChange = true;
/* 496 */               System.out.println("rankChange2");
/*     */             } 
/*     */           } else {
/*     */             
/* 500 */             counter++;
/* 501 */             System.out.println("counter:" + counter);
/*     */           } 
/* 503 */         } catch (IndexOutOfBoundsException e) {
/* 504 */           System.out.println("caught");
/*     */         } 
/*     */         
/* 507 */         if (k >= downCards.size() - 1 && !rankChange) {
/* 508 */           rankChange = true;
/* 509 */           sizeReached = true;
/* 510 */           System.out.println("rankChange1");
/*     */         } 
/*     */         
/* 513 */         if (rankChange) {
/* 514 */           System.out.println("Initiate process");
/* 515 */           if (sizeReached) {
/* 516 */             System.out.println("Size reached, increase k");
/* 517 */             k++;
/*     */           } 
/*     */           
/* 520 */           if (counter >= 3) {
/* 521 */             System.out.println("process 1");
/* 522 */             GamePageGUI.game.setA(downCards.get(k - counter));
/* 523 */             System.out.println(GamePageGUI.game.getA());
/* 524 */             counter--;
/* 525 */             GamePageGUI.game.setB(downCards.get(k - counter));
/* 526 */             System.out.println(GamePageGUI.game.getB());
/* 527 */             counter--;
/* 528 */             GamePageGUI.game.setC(downCards.get(k - counter));
/* 529 */             System.out.println(GamePageGUI.game.getC());
/* 530 */             counter--;
/*     */             
/* 532 */             createMeld();
/*     */             
/* 534 */             while (counter > 0) {
/* 535 */               GamePageGUI.game.setA(downCards.get(k - counter));
/* 536 */               if (GamePageGUI.game.getA().getWild() || GamePageGUI.game.getA().getRank() == lastRank) {
/*     */                 try {
/* 538 */                   meld(lastRank);
/* 539 */                 } catch (IllegalArgumentException e) {
/* 540 */                   buttonDownCancel();
/* 541 */                   log("Tried inserting to many wildCards into a meld");
/* 542 */                   return false;
/*     */                 } 
/*     */               } else {
/*     */                 
/* 546 */                 buttonDownCancel();
/* 547 */                 log("Invalid selection 2");
/* 548 */                 return false;
/*     */               } 
/* 550 */               counter--;
/*     */             } 
/*     */             
/* 553 */             GamePageGUI.game.setA(null);
/*     */           } else {
/*     */             
/* 556 */             buttonDownCancel();
/* 557 */             log("Invalid selection 3");
/* 558 */             return false;
/*     */           } 
/*     */         } 
/*     */         
/* 562 */         if (rankChange) {
/* 563 */           System.out.println("Process 2");
/* 564 */           counter = 1;
/* 565 */           System.out.println("Counter:" + counter);
/* 566 */           System.out.println("k = " + k);
/* 567 */           rankChange = false;
/* 568 */           if (!sizeReached) {
/* 569 */             lastRank = ((Card)downCards.get(k)).getRank();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/* 574 */       buttonDownCancel();
/* 575 */       log("Not enough points, " + GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].getDownPoints() + 
/* 576 */           " needed.");
/* 577 */       return false;
/*     */     } 
/* 579 */     GamePageGUI.game.setDownCards();
/* 580 */     GamePageGUI.game.getTeams()[GamePageGUI.game.getPlayerTurn()].setDown(true);
/* 581 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void buttonDownCancel() {
/* 586 */     initializeDownCards();
/* 587 */     downCancel();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void downCancel() {
/* 592 */     for (int c = 0; c < downCards.size(); c++) {
/* 593 */       GamePageGUI.game.findPlayerHand().drawCard(downCards.get(c));
/*     */     }
/*     */     
/* 596 */     int stockSize = GamePageGUI.game.findPlayerStock().getMeldList().size();
/* 597 */     for (int m = 0; m < stockSize; m++) {
/* 598 */       if (((Meld)GamePageGUI.game.findPlayerStock().getMeldList().get(stockSize - m - 1)).getRank() != 3) {
/* 599 */         System.out.println("Removing a meld");
/* 600 */         GamePageGUI.game.findPlayerStock().getMeldList().remove(stockSize - m - 1);
/*     */       } 
/*     */     } 
/*     */     
/* 604 */     GamePageGUI.game.setDownCards();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createMeld() {
/* 611 */     if (GamePageGUI.game.getA() != null && GamePageGUI.game.getB() != null && GamePageGUI.game.getC() != null) {
/* 612 */       if (GamePageGUI.game.getA().getRank() == 3 || GamePageGUI.game.getB().getRank() == 3 || 
/* 613 */         GamePageGUI.game.getC().getRank() == 3 || (GamePageGUI.game.getA().getWild() && 
/* 614 */         GamePageGUI.game.getB().getWild() && GamePageGUI.game.getC().getWild())) {
/* 615 */         cancel();
/* 616 */         log("Illegal meld attempt");
/*     */       }
/* 618 */       else if (GamePageGUI.game.getA().getRank() == GamePageGUI.game.getB().getRank() && 
/* 619 */         GamePageGUI.game.getB().getRank() == GamePageGUI.game.getC().getRank()) {
/*     */         try {
/* 621 */           GamePageGUI.game.createMeld();
/* 622 */           log("new meld has been put down");
/* 623 */         } catch (IllegalArgumentException e) {
/* 624 */           log("Meld already exists");
/* 625 */           cancel();
/*     */         } 
/* 627 */         GamePageGUI.game.setA(null);
/* 628 */         GamePageGUI.game.setB(null);
/* 629 */         GamePageGUI.game.setC(null);
/* 630 */       } else if ((GamePageGUI.game.getA().getRank() == GamePageGUI.game.getB().getRank() && 
/* 631 */         GamePageGUI.game.getC().getWild()) || (
/* 632 */         GamePageGUI.game.getB().getRank() == GamePageGUI.game.getC().getRank() && 
/* 633 */         GamePageGUI.game.getA().getWild()) || (
/* 634 */         GamePageGUI.game.getA().getRank() == GamePageGUI.game.getC().getRank() && 
/* 635 */         GamePageGUI.game.getB().getWild())) {
/*     */         try {
/* 637 */           GamePageGUI.game.createMeld();
/* 638 */           log("new meld has been put down");
/* 639 */         } catch (IllegalArgumentException e) {
/* 640 */           log("Meld already exists");
/* 641 */           cancel();
/*     */         } 
/* 643 */         GamePageGUI.game.setA(null);
/* 644 */         GamePageGUI.game.setB(null);
/* 645 */         GamePageGUI.game.setC(null);
/*     */       } else {
/* 647 */         cancel();
/* 648 */         log("Cards dont match");
/*     */       } 
/*     */     } else {
/*     */       
/* 652 */       cancel();
/* 653 */       log("Select more cards");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cancel() {
/* 659 */     if (GamePageGUI.game.getA() != null) {
/* 660 */       GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getA());
/*     */     }
/* 662 */     GamePageGUI.game.setA(null);
/* 663 */     if (GamePageGUI.game.getB() != null) {
/* 664 */       GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getB());
/*     */     }
/* 666 */     GamePageGUI.game.setB(null);
/* 667 */     if (GamePageGUI.game.getC() != null) {
/* 668 */       GamePageGUI.game.findPlayerHand().drawCard(GamePageGUI.game.getC());
/*     */     }
/* 670 */     GamePageGUI.game.setC(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void meld(int rank) {
/* 675 */     if (GamePageGUI.game.getA() != null || GamePageGUI.game.getB() != null || GamePageGUI.game.getC() != null) {
/*     */       
/* 677 */       if (rank != 3) {
/* 678 */         GamePageGUI.game.addToMeld();
/*     */         try {
/* 680 */           GamePageGUI.game.addWildToMeld(rank);
/* 681 */         } catch (IllegalArgumentException e) {
/* 682 */           throw new IllegalArgumentException("Error: To many wildcards in this meld");
/*     */         } 
/*     */       } else {
/* 685 */         System.out.println("game");
/* 686 */         throw new IllegalArgumentException(
/* 687 */             "Illegal argument, Black 3's cant be placed down, Red'3s are handled automatically.");
/*     */       } 
/*     */       
/* 690 */       if (GamePageGUI.game.getA() != null) {
/* 691 */         log(String.valueOf(GamePageGUI.game.getA().getRank()) + " inserted into meld");
/*     */       }
/* 693 */       if (GamePageGUI.game.getB() != null) {
/* 694 */         log(String.valueOf(GamePageGUI.game.getB().getRank()) + " inserted into meld");
/*     */       }
/* 696 */       if (GamePageGUI.game.getC() != null) {
/* 697 */         log(String.valueOf(GamePageGUI.game.getC().getRank()) + " inserted into meld");
/*     */       }
/* 699 */       GamePageGUI.game.setA(null);
/* 700 */       GamePageGUI.game.setB(null);
/* 701 */       GamePageGUI.game.setC(null);
/*     */     } else {
/* 703 */       log("Select a card");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void log(String message) {
/* 709 */     GamePageGUI.setConsoleMessage(message);
/* 710 */     System.out.println(message);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\GameGuiMethods2P.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */