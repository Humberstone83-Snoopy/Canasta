/*    */ package sample;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class TopTips
/*    */ {
/*  8 */   private static ArrayList<String> tipsList = new ArrayList<>();
/*  9 */   private static Random random = new Random();
/*    */ 
/*    */   
/*    */   public static String randomTip() {
/* 13 */     addTips();
/*    */     
/* 15 */     int index = random.nextInt(tipsList.size());
/*    */     
/* 17 */     return tipsList.get(index);
/*    */   }
/*    */   
/*    */   public static void addTips() {
/* 21 */     tipsList.add("Always keep a record of what's in the discard pile. When playing online, I have a small Wordpad window open to record the discards. The game can be too quick (and the pile too big) to remember without recording it somewhere, and if you don't keep tabs on the discard pile you will be at a huge disadvantage.");
/* 22 */     tipsList.add("Remember that your opponent needs a certain number of points for the initial meld (either 50, 90 or 120). I use the rule that in general they are unlikely to have 50 before turn 2, unlikely to have 90 before turn 5, and unlikely to have 120 before turn 7 (sooner if wild cards are allowed in the opening meld). Therefore you can take less care over what you discard in the early stages of the game. It can be useful to discard high singletons early on. Hold your 3s and 2s for later in the hand when there is a risk of your opponent picking up.");
/* 23 */     tipsList.add("Don't lock the pack if neither of you have melded yet. There is no point - it simply stalls for one turn. Next time round your opponent might pick up, and you've lost your valuable wild card!");
/* 24 */     tipsList.add("Watch for the draw pile running out - the game will end after the turn in which the last card is drawn, so meld before that happens! In a 4 player game, the draw pile runs out quite quickly and often before anyone has melded out.");
/* 25 */     tipsList.add("There is little point in picking up a pile of just one card unless it's a card you really need, e.g. to complete a Canasta and meld out!");
/* 26 */     tipsList.add("Don't forget you can meld before drawing - you may not wish to draw if you can meld out on your existing cards.");
/* 27 */     tipsList.add("Throwing a 2 says \"I want this discard pile\". Beware of the messages you are giving your opponent - they may guess what you are holding, and will probably try to block you.");
/* 28 */     tipsList.add("Throwing a 3 says \"I don't want you to take this discard pile\". If there's a good meld in there (whether on its own or it fits with your opponent's existing melds) that's ok - if not, you've probably used your 3 too early.");
/* 29 */     tipsList.add("Making a Canasta or two shows you are thinking of going out and may lure your opponent into melding (whether you intend him to or not).\n");
/* 30 */     tipsList.add("As soon as you can make the opening meld, do so in order that your partner can play");
/* 31 */     tipsList.add("Don't add wild cards to melds (apart from on the opening meld if appropriate) until your partner has had a chance to meld their cards. It may be that they can complete a Canasta for you.");
/* 32 */     tipsList.add("Never make a canasta early in the game with 4 naturals and 3 wilds. What are you trying to save?!? When an opponent does that to me, in 90% of the cases I take the game till the end and almost win in one hand.");
/* 33 */     tipsList.add(" Never defend playing singles with less than 3500 points of advantage. In most cases, you’ll lose. Defend with equilibrium, trying to assemble the most sets possible, in order to meld them.");
/* 34 */     tipsList.add("Although this is just a reference, usually a player can’t go out with more than 40 cards in the deck. This gives me some certains that only fail 5 to 10% of the times. Beyond 40 cards, well, you must read the game and take your odds.");
/* 35 */     tipsList.add("As all in life, persistence and faith are needed to play canasta. No game is lost till it’s lost. It’s your will and it must be strong. If it doesn’t happen, try again. The great players win impossible games!");
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\sample\TopTips.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */