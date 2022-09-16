package gameLogic;

import java.util.ArrayList;

public interface DeckInterface {
  ArrayList<Card> getCards();
  
  void setCards(ArrayList<Card> paramArrayList);
  
  void addCard(Card paramCard);
  
  void removeCard();
  
  void shuffle();
  
  int cardsLeft();
  
  Card dealCard();
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\DeckInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */