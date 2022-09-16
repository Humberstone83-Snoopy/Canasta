package gameLogic;

import java.util.ArrayList;

public interface StockInterface {
  ArrayList<Meld> getMeldList();
  
  void setMeldList(ArrayList<Meld> paramArrayList);
  
  void addMeld(Meld paramMeld);
  
  void addToMeld(Card paramCard);
  
  boolean checkForCanasta();
  
  int countStock(boolean paramBoolean);
  
  int countCanastas();
  
  void addToMeldByRank(int paramInt, Card paramCard);
  
  boolean alreadyMeld(int paramInt);
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\StockInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */