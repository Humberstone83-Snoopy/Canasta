package gameLogic;

import java.util.ArrayList;

public interface MeldInterface {
  int getRank();
  
  void setRank(int paramInt);
  
  ArrayList<Card> getCards();
  
  void setCards(ArrayList<Card> paramArrayList);
  
  boolean isCanasta();
  
  boolean getCanasta();
  
  boolean isNatural();
  
  boolean getNatural();
  
  int countMeld();
  
  Deck clear(Deck paramDeck);
  
  void addCard(Card paramCard);
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\MeldInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */