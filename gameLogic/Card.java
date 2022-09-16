/*     */ package gameLogic;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ public class Card
/*     */   implements CardInterface, Comparable<Card>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final int RANK;
/*     */   private final int POINTS;
/*     */   private final int SUIT;
/*     */   private final boolean WILD;
/*     */   
/*     */   public Card(int rank, int suit, int points, boolean wild) {
/*  38 */     this.RANK = rank;
/*  39 */     this.POINTS = points;
/*  40 */     this.SUIT = suit;
/*  41 */     this.WILD = wild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuit() {
/*  51 */     return this.SUIT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPoints() {
/*  60 */     return this.POINTS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRank() {
/*  69 */     return this.RANK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getWild() {
/*  78 */     return this.WILD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String stringRank() {
/*  89 */     int rank = getRank();
/*     */     
/*  91 */     if (rank > 0 && rank < 15) {
/*  92 */       if (rank == 1)
/*  93 */         return "Ace"; 
/*  94 */       if (rank > 1 && getRank() < 11)
/*  95 */         return String.valueOf(getRank()); 
/*  96 */       if (rank == 11)
/*  97 */         return "Jack"; 
/*  98 */       if (rank == 12)
/*  99 */         return "Queen"; 
/* 100 */       if (rank == 13) {
/* 101 */         return "King";
/*     */       }
/* 103 */       return "Joker";
/*     */     } 
/*     */     
/* 106 */     throw new IllegalArgumentException("Invalid Rank - Class Card, stringRank(), Rank == " + rank);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String stringRankSingleChar() {
/* 117 */     int rank = getRank();
/*     */     
/* 119 */     if (rank > 0 && rank < 15) {
/* 120 */       if (rank == 1)
/* 121 */         return "A"; 
/* 122 */       if (rank > 1 && getRank() < 11)
/* 123 */         return String.valueOf(getRank()); 
/* 124 */       if (rank == 11)
/* 125 */         return "J"; 
/* 126 */       if (rank == 12)
/* 127 */         return "Q"; 
/* 128 */       if (rank == 13) {
/* 129 */         return "K";
/*     */       }
/* 131 */       return "J*";
/*     */     } 
/*     */     
/* 134 */     throw new IllegalArgumentException("Invalid Rank - Class Card, stringRank(), Rank == " + rank);
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
/*     */   public String stringSuit() {
/* 147 */     if (getSuit() == 0)
/* 148 */       return "Spades"; 
/* 149 */     if (getSuit() == 1)
/* 150 */       return "Clubs"; 
/* 151 */     if (getSuit() == 2)
/* 152 */       return "Diamonds"; 
/* 153 */     if (getSuit() == 3) {
/* 154 */       return "Hearts";
/*     */     }
/* 156 */     return "Joker";
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
/*     */   public String toString() {
/* 168 */     return "Card [" + stringRank() + " of " + stringSuit() + ", points = " + getPoints() + "]\n";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Card c) {
/* 176 */     return this.RANK - c.RANK;
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\Card.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */