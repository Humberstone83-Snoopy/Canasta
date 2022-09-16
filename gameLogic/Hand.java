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
/*     */ public class Hand
/*     */   implements HandInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private ArrayList<Card> cards = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Deck clear(Deck deck) {
/*  39 */     for (int i = 0; i < getCards().size(); i++) {
/*  40 */       deck.addCard(getCards().get(i));
/*     */     }
/*  42 */     setCards(new ArrayList<>());
/*  43 */     return deck;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCard(Card card) {
/*  52 */     this.cards.add(card);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCard(Card card) {
/*  62 */     this.cards.remove(card);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getCardSameRank(int rank) {
/*  70 */     int index = 0;
/*     */     while (true) {
/*  72 */       if (index >= this.cards.size()) {
/*  73 */         System.out.println("Card not found");
/*     */         break;
/*     */       } 
/*  76 */       if (((Card)this.cards.get(index)).getRank() == rank) {
/*  77 */         return this.cards.get(index);
/*     */       }
/*  79 */       index++;
/*     */     } 
/*     */     
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCardCount() {
/*  90 */     return this.cards.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Card getCard(int position) {
/*  98 */     return this.cards.get(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Card> getCards() {
/* 106 */     return this.cards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCards(ArrayList<Card> cards) {
/* 114 */     this.cards = cards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sort() {
/* 122 */     Collections.sort(this.cards);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handIsEmpty() {
/* 130 */     return this.cards.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean greaterThanOne() {
/* 140 */     if (this.cards.size() > 1) {
/* 141 */       return true;
/*     */     }
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean greaterThanTwo() {
/* 154 */     if (this.cards.size() > 2) {
/* 155 */       return true;
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean greaterThanThree() {
/* 168 */     if (this.cards.size() > 3) {
/* 169 */       return true;
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean greaterThanFour() {
/* 182 */     if (this.cards.size() > 4) {
/* 183 */       return true;
/*     */     }
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countHand() {
/* 194 */     int counter = 0;
/* 195 */     for (int i = 0; i < this.cards.size(); i++) {
/* 196 */       counter += ((Card)this.cards.get(i)).getPoints();
/*     */     }
/* 198 */     return counter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 206 */     return "Hand [cards=" + this.cards + "]";
/*     */   }
/*     */   
/*     */   public Card getWildCardSameRank(int rank) {
/* 210 */     int index = 0;
/*     */     while (true) {
/* 212 */       if (index >= this.cards.size()) {
/* 213 */         System.out.println("Card not found");
/*     */         break;
/*     */       } 
/* 216 */       if (((Card)this.cards.get(index)).getRank() == rank && ((Card)this.cards.get(index)).getWild()) {
/* 217 */         return this.cards.get(index);
/*     */       }
/* 219 */       index++;
/*     */     } 
/*     */     
/* 222 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Hand.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */