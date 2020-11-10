/*     */ package gameLogic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
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
/*     */ public class Meld
/*     */   implements MeldInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int rank;
/*     */   private boolean natural;
/*     */   private boolean canasta;
/*     */   private ArrayList<Card> cards;
/*     */   
/*     */   public Meld(ArrayList<Card> cards) {
/*  26 */     int index = 0;
/*  27 */     this.cards = cards;
/*     */     
/*     */     while (true) {
/*  30 */       if (!((Card)this.cards.get(index)).getWild()) {
/*  31 */         this.rank = ((Card)this.cards.get(index)).getRank();
/*     */         break;
/*     */       } 
/*  34 */       index++;
/*     */     } 
/*     */     
/*  37 */     this.natural = true;
/*  38 */     this.natural = isNatural();
/*  39 */     this.canasta = isCanasta();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Meld(Card redThree) {
/*  46 */     this.rank = redThree.getRank();
/*  47 */     ArrayList<Card> meld = new ArrayList<>();
/*  48 */     meld.add(redThree);
/*  49 */     this.cards = meld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRank() {
/*  57 */     return this.rank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRank(int rank) {
/*  65 */     this.rank = rank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Card> getCards() {
/*  73 */     return this.cards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCards(ArrayList<Card> meld) {
/*  82 */     this.cards = meld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCanasta() {
/*  90 */     if (this.cards.size() >= 7) {
/*  91 */       if (!this.canasta) {
/*  92 */         this.canasta = true;
/*     */       }
/*  94 */       return this.canasta;
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanasta() {
/* 105 */     return this.canasta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNatural() {
/* 113 */     if (this.natural) {
/* 114 */       for (int i = 0; i < this.cards.size(); i++) {
/* 115 */         if (((Card)this.cards.get(i)).getWild()) {
/* 116 */           this.natural = false;
/* 117 */           return this.natural;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 122 */     return this.natural;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNatural() {
/* 130 */     return this.canasta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countMeld() {
/* 138 */     int counter = 0;
/* 139 */     for (int i = 0; i < this.cards.size(); i++) {
/* 140 */       counter += ((Card)this.cards.get(i)).getPoints();
/*     */     }
/* 142 */     return counter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Deck clear(Deck deck) {
/* 150 */     for (int i = 0; i < getCards().size(); i++) {
/* 151 */       deck.addCard(getCards().get(i));
/*     */     }
/* 153 */     setCards(new ArrayList<>());
/* 154 */     return deck;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 162 */     return "Meld [rank=" + this.rank + ", natural=" + this.natural + ", canasta=" + this.canasta + ", cards=" + this.cards + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCard(Card card) {
/* 170 */     this.cards.add(card);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Meld.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */