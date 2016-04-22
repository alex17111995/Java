import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Player implements Runnable{
    int numberOfPoints=0;
    int playerNumber;
    Game gamePlayer;
    Player(int playerNumber,Game game){
        this.playerNumber=playerNumber;
        this.gamePlayer=game;
    }
    List<Character> tiles= new ArrayList<>();

    @Override
    public void run() {

    }

    List<Character> getTiles(int numberOfTilesToGet){
        return null;
    }
    boolean postWord(String str){
        //TODO
        return false;
    }

}
