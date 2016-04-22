package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Game {
    private List<Player> playersOfGame = new ArrayList<>();
    private int currentPlayer=0;

    private AcceptedWords wordsAccepted;
    private Bag bag;

   public boolean postWord(String word){
        return wordsAccepted.isAcceptedWord(word);
    }
   public Game(){
       List<String> testare = new ArrayList<>();
       testare.add("Ciubi");
       testare.add("Ciuc");
       bag=new Bag();
       wordsAccepted= new AcceptedWords(new Trie(),testare);
    }
    public void generatePlayer(){
        Player player= new Player(playersOfGame.size(),this);
        playersOfGame.add(player);
        new Thread(player).start();
    }
   public synchronized Player switchPlayer(){

        Player nextPlayer= playersOfGame.get(currentPlayer=(currentPlayer+1)%playersOfGame.size());
        nextPlayer.notifyTurnSwitch();
        return nextPlayer;
    }
public  synchronized Player getPlayer(){
        return playersOfGame.get(currentPlayer);

    }
    public int computeWordValue(String word){
        return word.length();
    }
    public List<Character> getTiles(int numberOfTiles){
        return bag.getTiles(numberOfTiles);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
