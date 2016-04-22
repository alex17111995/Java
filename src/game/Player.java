package game;

import graphicInterface.UpdateGui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Player implements Runnable{
    int numberOfPoints=0;

    public int getPlayerNumber() {
        return playerNumber;
    }
    public int getPlayerPoints() {
        return numberOfPoints;
    }


    int playerNumber;
    Game gamePlayer;
    Player(int playerNumber,Game game){
        this.playerNumber=playerNumber;
        this.gamePlayer=game;
    }

    public List<Character> getTiles() {
        return tiles;
    }

    synchronized public void setGameOver() {
        this.gameOver = true;
    }

    boolean gameOver=false;
    public void setTiles(List<Character> tiles) {
        this.tiles = tiles;
    }
    private String postedWord="";
   public synchronized void postWord(String word){
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
        tiles=gamePlayer.getTiles(7); //initHand
        while(true){
            try {
                synchronized (this){
                    if(gameOver)
                        break;
                }
                String wordSubmitted= getWord(); //apel blocant
                //TODO update GUI using something like controller.update()..

               boolean isValid=checkValidWordAndUpdateDataStructures(wordSubmitted);
                //SHIT WORD
                //TODO update
                UpdateGui.update(this,isValid,wordSubmitted);
            } catch (InterruptedException e) {
                break;//s-a terminaj jocul
            }
        }

    }
    protected boolean checkValidWordAndUpdateDataStructures(String wordSubmitted){
        if(gamePlayer.postWord(wordSubmitted)==true && checkHaveRequiringTiles(wordSubmitted)==true){
            synchronized (this) {
                List<Character> newTiles= gamePlayer.getTiles(wordSubmitted.length());
                if(newTiles.size()==0){
                    gamePlayer.setGameOver(this);
                }
                char[] formingCharcters= wordSubmitted.toCharArray();
                for (char formingCharcter : formingCharcters) {
                    this.tiles.remove(new Character(formingCharcter));
                }
                for (Character newTile : newTiles) {
                    this.tiles.add(newTile);
                }
                numberOfPoints += gamePlayer.computeWordValue(wordSubmitted);
            }
            gamePlayer.switchPlayer();//turn ended, switchPlayer
            return  true;
        }
        return  false;

    }
    protected  void discardTilesIfStuck(){
        return;
    }
    private boolean checkHaveRequiringTiles(String word){

        char[] formingCharacters=word.toCharArray();
        Arrays.sort(formingCharacters);
        Character lastChar=null;
        int lastIndex=0;
        for (char formingCharacter : formingCharacters) {
            if(lastChar!=null&& formingCharacter==lastChar){
                if(lastIndex!=this.tiles.size()-1)return false;
               int index = this.tiles.subList(lastIndex+1,this.tiles.size()).indexOf(formingCharacter);
                if(index==-1)
                    return false;
                lastIndex=index;
            }
            else{
                int index = this.tiles.indexOf(formingCharacter);
                if(index==-1)
                    return false;
                lastIndex=index;
                lastChar=formingCharacter;
            }
        }
        return true;
    }

    public List<Character> getTiles(int numberOfTilesToGet){
        return this.tiles;
    }


    public void notifyTurnSwitch() {
        //nu fa nimic
    }

}
