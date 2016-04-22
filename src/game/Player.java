package game;

import graphicInterface.UpdateGui;

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

    public List<Character> getTiles() {
        return tiles;
    }


    public void setTiles(List<Character> tiles) {
        this.tiles = tiles;
    }
    private String postedWord="";
    synchronized void postWord(String word){
        postedWord=word;
        this.notify();
    }

    synchronized String getWord() throws InterruptedException {
        wait();
        return  postedWord;
    }
    List<Character> tiles= new ArrayList<>();
    synchronized int getNumberOfPoints(){
        return numberOfPoints;
    }

    @Override
    public void run() {
        while(true){
            try {
                String wordSubmitted= getWord(); //apel blocant
                //TODO update GUI using something like controller.update()..
                if(gamePlayer.postWord(wordSubmitted)==true){
                    synchronized (this) {
                        numberOfPoints += gamePlayer.computeWordValue(wordSubmitted);
                    }
                    gamePlayer.switchPlayer();//turn ended, switchPlayer
                }
                //SHIT WORD
                UpdateGui.update();
            } catch (InterruptedException e) {
                break;//s-a terminaj jocul
            }
        }

    }

    List<Character> getTiles(int numberOfTilesToGet){
        return null;
    }


    public void notifyTurnSwitch() {
        //nu fa nimic
    }
}
