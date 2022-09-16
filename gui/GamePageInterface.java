package gui;

import javafx.scene.Group;

public interface GamePageInterface {
  Group gameBG();
  
  Group addHand(int paramInt1, int paramInt2);
  
  Group addOpMelds(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2);
  
  Group addMyMelds(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2);
  
  Group addDiscard();
  
  Group addDeck();
  
  Group addButtons();
  
  Group addHotBar();
  
  Group addHelpButton();
  
  Group getTips();
  
  Group getRules();
  
  Group addRulesButton();
  
  void updateHand();
  
  void updateDiscard();
  
  void updateMelds();
  
  void updateTurnMarker();
  
  Group createGame();
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\GamePageInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */