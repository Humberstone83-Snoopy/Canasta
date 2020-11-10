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
/*     */ public class Stock
/*     */   implements StockInterface, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  30 */   private ArrayList<Meld> meldList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Meld> getMeldList() {
/*  38 */     return this.meldList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMeldList(ArrayList<Meld> stock) {
/*  46 */     this.meldList = stock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMeld(Meld meld) {
/*  54 */     if (alreadyMeld(meld.getRank())) {
/*  55 */       throw new IllegalArgumentException("Error: Meld already exists - Stock, addMeld");
/*     */     }
/*  57 */     this.meldList.add(meld);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToMeld(Card card) {
/*  65 */     int cardRank = card.getRank();
/*     */     
/*  67 */     for (int i = 0; i < this.meldList.size(); i++) {
/*  68 */       if (cardRank == ((Meld)this.meldList.get(i)).getRank()) {
/*  69 */         ((Meld)this.meldList.get(i)).addCard(card);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkForCanasta() {
/*  81 */     boolean containsCanasta = false;
/*  82 */     for (int i = 0; i < this.meldList.size(); i++) {
/*  83 */       if (((Meld)this.meldList.get(i)).isCanasta()) {
/*  84 */         containsCanasta = true;
/*     */       }
/*     */     } 
/*  87 */     return containsCanasta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countStock(boolean down) {
/*  95 */     int counter = 0;
/*  96 */     for (int i = 0; i < this.meldList.size(); i++) {
/*     */       
/*  98 */       Meld currentMeld = this.meldList.get(i);
/*     */       
/* 100 */       if (currentMeld.getRank() == 3 && (
/* 101 */         (Card)currentMeld.getCards().get(0)).getWild()) {
/*     */         
/* 103 */         if (down) {
/* 104 */           if (currentMeld.getCards().size() == 4) {
/* 105 */             counter += 800;
/*     */           } else {
/* 107 */             counter += currentMeld.countMeld();
/*     */           } 
/*     */         } else {
/* 110 */           counter -= currentMeld.countMeld();
/*     */         } 
/*     */       } else {
/*     */         
/* 114 */         counter += currentMeld.countMeld();
/*     */       } 
/*     */     } 
/* 117 */     return counter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countCanastas() {
/* 125 */     int counter = 0;
/* 126 */     for (int i = 0; i < this.meldList.size(); i++) {
/* 127 */       if (((Meld)this.meldList.get(i)).isCanasta()) {
/* 128 */         if (((Meld)this.meldList.get(i)).isNatural()) {
/* 129 */           counter += 500;
/*     */         } else {
/* 131 */           counter += 300;
/*     */         } 
/*     */       }
/*     */     } 
/* 135 */     return counter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToMeldByRank(int cardNumbMeld, Card a) {
/* 144 */     for (int i = 0; i < this.meldList.size(); i++) {
/* 145 */       if (cardNumbMeld == ((Meld)this.meldList.get(i)).getRank()) {
/* 146 */         ((Meld)this.meldList.get(i)).addCard(a);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean alreadyMeld(int numb) {
/* 157 */     for (int i = 0; i < this.meldList.size(); i++) {
/* 158 */       if (((Meld)this.meldList.get(i)).getRank() == numb) {
/* 159 */         return true;
/*     */       }
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 170 */     return "Stock [meldList=" + this.meldList + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Stock.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */