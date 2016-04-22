import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Game {
    List<Player> playersOfGame;
    int currentPlayer=0;
    AcceptedWords wordsAccepted;
    Bag bag;

    void generatePlayer(){
        playersOfGame.add(new Player(playersOfGame.size(),this));
    }
    Player switchPlayer(){
        return playersOfGame.get(currentPlayer=(currentPlayer+1)%playersOfGame.size());
    }

}
