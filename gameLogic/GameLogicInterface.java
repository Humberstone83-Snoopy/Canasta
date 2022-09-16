package gameLogic;

import java.util.ArrayList;

public interface GameLogicInterface {
  String toString();
  
  void deal();
  
  void toggleTurn();
  
  void pickUpFromDeck();
  
  void pickUpFromDiscard();
  
  void pickUpFromDiscardWild();
  
  void pickUpFromDiscardMeld();
  
  void discardCard();
  
  void createMeld();
  
  void addToMeld();
  
  void goDown();
  
  void checkForRoundEnd();
  
  ArrayList<Card> handleRed3();
  
  boolean checkWildStatus();
  
  boolean checkDownStatus();
  
  Player findPlayer();
  
  Hand findPlayerHand();
  
  Stock findPlayerStock();
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\GameLogicInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */