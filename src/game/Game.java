package game;

import java.io.File;
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
    void setGameOver(Player notifier){
        for (Player player : playersOfGame) {
            if(notifier==player)
                continue;
            player.setGameOver();
            player.notify();
        }
    }
   public Game(){
      ParseFile file= new ParseFile();
      List<String>cuvinte= file.getList(new File("words.txt"));
       bag=new Bag();
       wordsAccepted= new AcceptedWords(new Trie(),cuvinte);
    }
    public void generatePlayer(){
        Player player= new Player(playersOfGame.size(),this);
        playersOfGame.add(player);
        new Thread(player).start();
    }
    public  void generateAutomatedPlayer(){
        Player player= new AutomatedPlayer(playersOfGame.size(),this);
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
