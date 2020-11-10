/*     */ package gameLogic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Deck
/*     */   implements DeckInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   private ArrayList<Card> cards = new ArrayList<>();
/*     */   public Deck() {
/*  34 */     while (this.cards.size() < 108) {
/*     */ 
/*     */       
/*  37 */       for (int i = 0; i < 4; i++) {
/*     */         
/*  39 */         for (int j = 0; j <= 12; j++) {
/*     */           
/*  41 */           int points = 0;
/*  42 */           boolean wild = false;
/*     */ 
/*     */           
/*  45 */           if (j == 0) {
/*  46 */             points = 20;
/*  47 */           } else if (j == 1) {
/*  48 */             wild = true;
/*  49 */             points = 20;
/*  50 */           } else if (j == 2 && i < 2) {
/*  51 */             points = 5;
/*  52 */           } else if (j == 2) {
/*  53 */             wild = true;
/*  54 */             points = 100;
/*  55 */           } else if (j < 7) {
/*  56 */             points = 5;
/*     */           } else {
/*  58 */             points = 10;
/*     */           } 
/*     */           
/*  61 */           this.cards.add(new Card(j + 1, i, points, wild));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  67 */       this.cards.add(new Card(14, 4, 50, true));
/*  68 */       this.cards.add(new Card(14, 4, 50, true));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Card> getCards() {
/*  77 */     return this.cards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCards(ArrayList<Card> deck) {
/*  85 */     this.cards = deck;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCard(Card card) {
/*  93 */     this.cards.add(card);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCard() {
/* 100 */     this.cards.remove(cardsLeft() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shuffle() {
/* 108 */     Collections.shuffle(this.cards);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cardsLeft() {
/* 116 */     return this.cards.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card dealCard() {
/* 124 */     Card dealtCard = this.cards.get(cardsLeft() - 1);
/* 125 */     removeCard();
/* 126 */     return dealtCard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return "Deck [cards=" + this.cards + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Deck.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */