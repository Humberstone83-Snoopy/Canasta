/*     */ package gameLogic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
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
/*     */ public class TwoPlayerLogic
/*     */   implements GameLogicInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Team[] teams;
/*     */   private Deck deck;
/*     */   private DiscardPile discard;
/*     */   private int playerTurn;
/*     */   private int team1Score;
/*     */   private int team2Score;
/*     */   private boolean drawnCard;
/*     */   private boolean roundOver;
/*     */   private Card a;
/*     */   private Card b;
/*     */   private Card c;
/*     */   private Card down1;
/*     */   private Card down2;
/*     */   private Card down3;
/*     */   private Card down4;
/*     */   private Card down5;
/*     */   private Card down6;
/*     */   private Card down7;
/*     */   private Card down8;
/*     */   private Card down9;
/*     */   private Card down10;
/*     */   private Card down11;
/*     */   private Card down12;
/*     */   private Card down13;
/*     */   private Card down14;
/*     */   private Card down15;
/*     */   
/*     */   public TwoPlayerLogic() {
/*  53 */     Player player1 = new Player(0, 0);
/*  54 */     Player player2 = new Player(1, 1);
/*  55 */     ArrayList<Player> t1 = new ArrayList<>();
/*  56 */     t1.add(player1);
/*  57 */     ArrayList<Player> t2 = new ArrayList<>();
/*  58 */     t2.add(player2);
/*  59 */     Team team1 = new Team(0, t1);
/*  60 */     Team team2 = new Team(1, t2);
/*  61 */     Team[] teamArray = { team1, team2 };
/*     */     
/*  63 */     this.teams = teamArray;
/*  64 */     this.deck = new Deck();
/*  65 */     this.discard = new DiscardPile();
/*     */     
/*  67 */     this.playerTurn = 0;
/*  68 */     this.team1Score = 0;
/*  69 */     this.team2Score = 0;
/*  70 */     this.drawnCard = false;
/*  71 */     this.roundOver = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiscardPile getDiscard() {
/*  80 */     return this.discard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Team[] getTeams() {
/*  87 */     return this.teams;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlayerTurn() {
/*  95 */     return this.playerTurn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeam1Score() {
/* 103 */     return this.team1Score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeam1Score(int team1Score) {
/* 111 */     this.team1Score = team1Score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTeam2Score() {
/* 119 */     return this.team2Score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTeam2Score(int team2Score) {
/* 127 */     this.team2Score = team2Score;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDrawnCard() {
/* 135 */     return this.drawnCard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDrawnCard(boolean drawnCard) {
/* 143 */     this.drawnCard = drawnCard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRoundOver() {
/* 151 */     if (this.roundOver) {
/* 152 */       return this.roundOver;
/*     */     }
/* 154 */     checkForRoundEnd();
/* 155 */     return this.roundOver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Deck getDeck() {
/* 164 */     return this.deck;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoundOver(boolean roundOver) {
/* 172 */     this.roundOver = roundOver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deal() {
/* 183 */     this.deck.shuffle();
/*     */ 
/*     */     
/* 186 */     if (this.deck.cardsLeft() == 108) {
/*     */       
/* 188 */       int cardsToDeal = 15;
/*     */       
/* 190 */       for (int i = 0; i < cardsToDeal; i++) {
/* 191 */         ((Player)this.teams[0].getPlayers().get(0)).getHand().drawCard(this.deck.dealCard());
/* 192 */         ((Player)this.teams[1].getPlayers().get(0)).getHand().drawCard(this.deck.dealCard());
/*     */       } 
/*     */ 
/*     */       
/* 196 */       this.discard.addCard(this.deck.dealCard());
/*     */     } else {
/*     */       
/* 199 */       throw new IllegalArgumentException("Error: Cards missing from the deck - 2PL, deal");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleTurn() {
/* 208 */     setDrawnCard(false);
/* 209 */     if (this.playerTurn == 0) {
/* 210 */       this.playerTurn++;
/*     */     } else {
/* 212 */       this.playerTurn--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Player findPlayer() {
/* 221 */     return this.teams[this.playerTurn].getPlayers().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hand findPlayerHand() {
/* 229 */     return ((Player)this.teams[this.playerTurn].getPlayers().get(0)).getHand();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stock findPlayerStock() {
/* 237 */     return this.teams[this.playerTurn].getStock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickUpFromDeck() {
/* 246 */     findPlayer().getHand().drawCard(this.deck.dealCard());
/* 247 */     findPlayer().getHand().sort();
/* 248 */     this.drawnCard = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickUpFromDiscard() {
/* 257 */     if (this.a.getRank() == this.b.getRank() && this.a.getRank() == this.discard.checkTopCard().getRank()) {
/* 258 */       this.c = this.discard.dealCard();
/* 259 */       createMeld();
/* 260 */       int loopCounter = this.discard.cardsLeft();
/* 261 */       for (int i = 0; i < loopCounter; i++) {
/* 262 */         findPlayerHand().drawCard(this.discard.dealCard());
/*     */       }
/* 264 */       this.drawnCard = true;
/* 265 */       findPlayer().getHand().sort();
/*     */     } else {
/* 267 */       throw new IllegalArgumentException("Error: Cards not of the same rank - 2PL, pickUpFromDiscard");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickUpFromDiscardWild() {
/* 278 */     if ((this.a.getWild() && !this.b.getWild()) || (
/* 279 */       this.b.getWild() && !this.a.getWild())) {
/* 280 */       if (this.a.getRank() == this.discard.checkTopCard().getRank() || 
/* 281 */         this.b.getRank() == this.discard.checkTopCard().getRank()) {
/*     */         
/* 283 */         this.c = this.discard.dealCard();
/* 284 */         createMeld();
/* 285 */         int loopCounter = this.discard.cardsLeft();
/* 286 */         for (int i = 0; i < loopCounter; i++) {
/* 287 */           findPlayerHand().drawCard(this.discard.dealCard());
/*     */         }
/* 289 */         this.drawnCard = true;
/* 290 */         findPlayer().getHand().sort();
/*     */       } else {
/*     */         
/* 293 */         throw new IllegalArgumentException("Error: No matching rank - 2PL, pickUpFromDiscardWild");
/*     */       } 
/*     */     } else {
/* 296 */       throw new IllegalArgumentException("Error: WildCard condition not met - 2PL, pickUpFromDiscardWild");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickUpFromDiscardMeld() {
/* 306 */     if (findPlayerStock().alreadyMeld(this.discard.checkTopCard().getRank())) {
/* 307 */       findPlayerStock().addToMeld(this.discard.dealCard());
/* 308 */       int loopCounter = this.discard.cardsLeft();
/* 309 */       for (int i = 0; i < loopCounter; i++) {
/* 310 */         findPlayerHand().drawCard(this.discard.dealCard());
/*     */       }
/* 312 */       this.drawnCard = true;
/* 313 */       findPlayer().getHand().sort();
/*     */     } else {
/* 315 */       throw new IllegalArgumentException("Error: no matching meld - 2PL, pickUpFromDiscardMeld");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCard() {
/* 324 */     this.discard.addCard(this.a);
/* 325 */     this.drawnCard = false;
/* 326 */     toggleTurn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createMeld() {
/* 334 */     ArrayList<Card> newMeld = new ArrayList<>();
/* 335 */     newMeld.add(this.a);
/* 336 */     newMeld.add(this.b);
/* 337 */     newMeld.add(this.c);
/* 338 */     Meld meld = new Meld(newMeld);
/*     */     try {
/* 340 */       findPlayerStock().addMeld(meld);
/* 341 */     } catch (IllegalArgumentException e) {
/* 342 */       throw new IllegalArgumentException("Meld already exists");
/*     */     } 
/* 344 */     checkDownStatus();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToMeld() {
/* 354 */     if (this.a != null && 
/* 355 */       findPlayerStock().alreadyMeld(this.a.getRank())) {
/* 356 */       findPlayerStock().addToMeld(this.a);
/* 357 */       findPlayerHand().discardCard(this.a);
/*     */     } 
/*     */     
/* 360 */     if (this.b != null && 
/* 361 */       findPlayerStock().alreadyMeld(this.b.getRank())) {
/* 362 */       findPlayerStock().addToMeld(this.b);
/* 363 */       findPlayerHand().discardCard(this.b);
/*     */     } 
/*     */     
/* 366 */     if (this.c != null && 
/* 367 */       findPlayerStock().alreadyMeld(this.c.getRank())) {
/* 368 */       findPlayerStock().addToMeld(this.c);
/* 369 */       findPlayerHand().discardCard(this.c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWildToMeld(int rank) {
/* 379 */     Meld targetMeld = null;
/* 380 */     int wildCardCounter = 0;
/*     */ 
/*     */ 
/*     */     
/* 384 */     if (findPlayerStock().alreadyMeld(rank)) {
/* 385 */       for (int i = 0; i < findPlayerStock().getMeldList().size(); i++) {
/* 386 */         if (((Meld)findPlayerStock().getMeldList().get(i)).getRank() == rank) {
/* 387 */           targetMeld = findPlayerStock().getMeldList().get(i);
/*     */           break;
/*     */         } 
/*     */       } 
/* 391 */       for (int k = 0; k < targetMeld.getCards().size(); k++) {
/* 392 */         if (((Card)targetMeld.getCards().get(k)).getWild()) {
/* 393 */           wildCardCounter++;
/*     */         }
/*     */       } 
/*     */       
/* 397 */       if (wildCardCounter >= 3) {
/* 398 */         throw new IllegalArgumentException("Error: To many wildcards in this meld");
/*     */       }
/*     */ 
/*     */       
/* 402 */       if (this.a != null && 
/* 403 */         this.a.getWild() && this.a.getRank() != 3) {
/* 404 */         findPlayerStock().addToMeldByRank(rank, this.a);
/* 405 */         findPlayerHand().discardCard(this.a);
/* 406 */         wildCardCounter++;
/*     */       } 
/*     */ 
/*     */       
/* 410 */       if (this.b != null && 
/* 411 */         this.b.getWild() && this.b.getRank() != 3) {
/* 412 */         if (wildCardCounter >= 3) {
/* 413 */           throw new IllegalArgumentException("Error: To many wildcards in this meld");
/*     */         }
/* 415 */         findPlayerStock().addToMeldByRank(rank, this.b);
/* 416 */         findPlayerHand().discardCard(this.b);
/* 417 */         wildCardCounter++;
/*     */       } 
/*     */ 
/*     */       
/* 421 */       if (this.c != null && 
/* 422 */         this.c.getWild() && this.c.getRank() != 3) {
/* 423 */         if (wildCardCounter >= 3) {
/* 424 */           throw new IllegalArgumentException("Error: To many wildcards in this meld");
/*     */         }
/* 426 */         findPlayerStock().addToMeldByRank(rank, this.c);
/* 427 */         findPlayerHand().discardCard(this.c);
/* 428 */         wildCardCounter++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void goDown() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkForRoundEnd() {
/* 448 */     if (this.deck.cardsLeft() == 0 || (
/* 449 */       (Player)this.teams[0].getPlayers().get(0)).getHand().handIsEmpty() || (
/* 450 */       (Player)this.teams[1].getPlayers().get(0)).getHand().handIsEmpty()) {
/* 451 */       this.roundOver = true;
/*     */     }
/*     */     
/* 454 */     if (this.roundOver) {
/* 455 */       this.team1Score += this.teams[0].getStock().countCanastas();
/* 456 */       this.team1Score += this.teams[0].getStock().countStock(this.teams[0].getDown());
/* 457 */       this.team1Score -= ((Player)this.teams[0].getPlayers().get(0)).getHand().countHand();
/*     */       
/* 459 */       this.team2Score += this.teams[1].getStock().countCanastas();
/* 460 */       this.team2Score += this.teams[1].getStock().countStock(this.teams[1].getDown());
/* 461 */       this.team2Score -= ((Player)this.teams[1].getPlayers().get(0)).getHand().countHand();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Card> handleRed3() {
/* 471 */     int counter = 0;
/* 472 */     ArrayList<Card> swappedCards = new ArrayList<>();
/* 473 */     for (int i = 0; i < findPlayerHand().getCardCount(); i++) {
/* 474 */       if (((Card)findPlayerHand().getCards().get(i)).getRank() == 3 && (
/* 475 */         (Card)findPlayerHand().getCards().get(i)).getWild()) {
/* 476 */         counter++;
/*     */       }
/*     */     } 
/*     */     
/* 480 */     if (!findPlayerStock().alreadyMeld(3) && counter > 0) {
/* 481 */       Card redThree = findPlayerHand().getWildCardSameRank(3);
/* 482 */       findPlayerStock().addMeld(new Meld(redThree));
/* 483 */       findPlayerHand().discardCard(redThree);
/* 484 */       swappedCards.add(this.deck.getCards().get(this.deck.cardsLeft() - 1));
/* 485 */       pickUpFromDeck();
/* 486 */       counter--;
/*     */     } 
/*     */     
/* 489 */     for (int l = 0; l < counter; l++) {
/* 490 */       Card redThree = findPlayerHand().getWildCardSameRank(3);
/* 491 */       findPlayerStock().addToMeld(redThree);
/* 492 */       findPlayerHand().discardCard(redThree);
/* 493 */       swappedCards.add(this.deck.getCards().get(this.deck.cardsLeft() - 1));
/* 494 */       pickUpFromDeck();
/*     */     } 
/* 496 */     return swappedCards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkWildStatus() {
/* 505 */     boolean down = checkDownStatus();
/* 506 */     for (int i = 0; i < this.discard.cardsLeft(); i++) {
/* 507 */       if (((Card)this.discard.getCards().get(i)).getWild() || !down) {
/* 508 */         this.teams[this.playerTurn].setDeckWild(true);
/*     */         break;
/*     */       } 
/* 511 */       this.teams[this.playerTurn].setDeckWild(false);
/*     */     } 
/*     */     
/* 514 */     return this.teams[this.playerTurn].getDeckWild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkDownStatus() {
/* 522 */     if (this.teams[this.playerTurn].getDown()) {
/* 523 */       return true;
/*     */     }
/*     */     
/* 526 */     int counter = 0;
/*     */     
/* 528 */     for (int i = 0; i < findPlayerStock().getMeldList().size(); i++) {
/* 529 */       if (((Meld)findPlayerStock().getMeldList().get(i)).getRank() == 2) {
/* 530 */         i++;
/*     */       }
/* 532 */       for (int k = 0; k < ((Meld)findPlayerStock().getMeldList().get(i)).getCards().size(); k++) {
/* 533 */         counter += ((Card)((Meld)findPlayerStock().getMeldList().get(i)).getCards().get(k)).getPoints();
/*     */       }
/*     */     } 
/*     */     
/* 537 */     if (counter >= this.teams[this.playerTurn].getDownPoints()) {
/* 538 */       this.teams[this.playerTurn].setDown(true);
/* 539 */       return true;
/*     */     } 
/* 541 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getA() {
/* 550 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setA(Card a) {
/* 557 */     this.a = a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getB() {
/* 564 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setB(Card b) {
/* 571 */     this.b = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getC() {
/* 578 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setC(Card c) {
/* 585 */     this.c = c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 593 */     return "TwoPlayerLogic [teams=" + Arrays.toString((Object[])this.teams) + ", deck=" + this.deck + ", discard=" + this.discard + 
/* 594 */       ", playerTurn=" + this.playerTurn + ", team1Score=" + this.team1Score + ", team2Score=" + this.team2Score + 
/* 595 */       ", drawnCard=" + this.drawnCard + ", roundOver=" + this.roundOver + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 603 */     TwoPlayerLogic game = new TwoPlayerLogic();
/* 604 */     game.deal();
/*     */     
/* 606 */     System.out.println(game.deck.cardsLeft());
/* 607 */     System.out.println(((Player)game.teams[game.playerTurn].getPlayers().get(0)).getHand().getCardCount());
/* 608 */     game.pickUpFromDeck();
/* 609 */     System.out.println(game.deck.cardsLeft());
/* 610 */     System.out.println(((Player)game.teams[game.playerTurn].getPlayers().get(0)).getHand().getCardCount());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOpHandSize(int myID) {
/* 615 */     if (myID == 0) {
/* 616 */       return Integer.toString(((Player)this.teams[1].getPlayers().get(0)).getHand().getCardCount());
/*     */     }
/* 618 */     return Integer.toString(((Player)this.teams[0].getPlayers().get(0)).getHand().getCardCount());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDownCards() {
/* 623 */     this.down1 = null;
/* 624 */     this.down2 = null;
/* 625 */     this.down3 = null;
/* 626 */     this.down4 = null;
/* 627 */     this.down5 = null;
/* 628 */     this.down6 = null;
/* 629 */     this.down7 = null;
/* 630 */     this.down8 = null;
/* 631 */     this.down9 = null;
/* 632 */     this.down10 = null;
/* 633 */     this.down11 = null;
/* 634 */     this.down12 = null;
/* 635 */     this.down13 = null;
/* 636 */     this.down14 = null;
/* 637 */     this.down15 = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDownCards(Card newCard) {
/* 642 */     if (this.down1 == null) {
/* 643 */       this.down1 = newCard;
/* 644 */     } else if (this.down2 == null) {
/* 645 */       this.down2 = newCard;
/* 646 */     } else if (this.down3 == null) {
/* 647 */       this.down3 = newCard;
/* 648 */     } else if (this.down4 == null) {
/* 649 */       this.down4 = newCard;
/* 650 */     } else if (this.down5 == null) {
/* 651 */       this.down5 = newCard;
/* 652 */     } else if (this.down6 == null) {
/* 653 */       this.down6 = newCard;
/* 654 */     } else if (this.down7 == null) {
/* 655 */       this.down7 = newCard;
/* 656 */     } else if (this.down8 == null) {
/* 657 */       this.down8 = newCard;
/* 658 */     } else if (this.down9 == null) {
/* 659 */       this.down9 = newCard;
/* 660 */     } else if (this.down10 == null) {
/* 661 */       this.down10 = newCard;
/* 662 */     } else if (this.down11 == null) {
/* 663 */       this.down11 = newCard;
/* 664 */     } else if (this.down12 == null) {
/* 665 */       this.down12 = newCard;
/* 666 */     } else if (this.down13 == null) {
/* 667 */       this.down13 = newCard;
/* 668 */     } else if (this.down14 == null) {
/* 669 */       this.down14 = newCard;
/* 670 */     } else if (this.down15 == null) {
/* 671 */       this.down15 = newCard;
/*     */     } else {
/* 673 */       return false;
/*     */     } 
/* 675 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDown1(Card down1) {
/* 682 */     this.down1 = down1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown1() {
/* 689 */     return this.down1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown2() {
/* 696 */     return this.down2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown3() {
/* 703 */     return this.down3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown4() {
/* 710 */     return this.down4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown5() {
/* 717 */     return this.down5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown6() {
/* 724 */     return this.down6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown7() {
/* 731 */     return this.down7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown8() {
/* 738 */     return this.down8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown9() {
/* 745 */     return this.down9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown10() {
/* 752 */     return this.down10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown11() {
/* 759 */     return this.down11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown12() {
/* 766 */     return this.down12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown13() {
/* 773 */     return this.down13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown14() {
/* 780 */     return this.down14;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getDown15() {
/* 787 */     return this.down15;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\TwoPlayerLogic.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */