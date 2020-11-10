package gameLogic;

public interface CardInterface {
  int getSuit();
  
  int getPoints();
  
  int getRank();
  
  boolean getWild();
  
  String stringRank();
  
  String stringRankSingleChar();
  
  String stringSuit();
  
  String toString();
  
  int compareTo(Card paramCard);
}


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gameLogic\CardInterface.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */