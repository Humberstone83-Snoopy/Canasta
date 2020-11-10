package gameLogic;

import java.util.ArrayList;

public interface HandInterface {
  Deck clear(Deck paramDeck);
  
  void drawCard(Card paramCard);
  
  void discardCard(Card paramCard);
  
  Card getCardSameRank(int paramInt);
  
  int getCardCount();
  
  Card getCard(int paramInt);
  
  ArrayList<Card> getCards();
  
  void setCards(ArrayList<Card> paramArrayList);
  
  void sort();
  
  boolean handIsEmpty();
  
  boolean greaterThanOne();
  
  boolean greaterThanTwo();
  
  boolean greaterThanThree();
  
  boolean greaterThanFour();
  
  int countHand();
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\HandInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */