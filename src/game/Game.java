package game;

import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Game {
    private List<Player> playersOfGame;
    private int currentPlayer=0;
    private AcceptedWords wordsAccepted;
    private Bag bag;

    boolean postWord(String word){
        return wordsAccepted.isAcceptedWord(word);
    }
    void generatePlayer(){
        playersOfGame.add(new Player(playersOfGame.size(),this));
    }
    synchronized Player switchPlayer(){

        Player nextPlayer= playersOfGame.get(currentPlayer=(currentPlayer+1)%playersOfGame.size());
        nextPlayer.notifyTurnSwitch();
        return nextPlayer;
    }
   synchronized Player getPlayer(){
        return playersOfGame.get(currentPlayer);

    }
    int computeWordValue(String word){
        return word.length();
    }

}
