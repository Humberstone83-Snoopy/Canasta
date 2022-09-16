package gameLogic;

import java.util.ArrayList;

public interface TeamInterface {
  ArrayList<Player> getPlayers();
  
  void setPlayers(ArrayList<Player> paramArrayList);
  
  void setDown(boolean paramBoolean);
  
  boolean getDown();
  
  int getDownPoints();
  
  void setDownPoints(int paramInt);
  
  boolean getDeckWild();
  
  void setDeckWild(boolean paramBoolean);
  
  Stock getStock();
  
  void setStock(Stock paramStock);
  
  boolean getGotCanasta();
  
  void setGotCanasta(Boolean paramBoolean);
  
  boolean isFirstOut();
  
  void setFirstOut(boolean paramBoolean);
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\TeamInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */