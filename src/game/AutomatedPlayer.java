package game;

import graphicInterface.UpdateGui;

/**
 * Created by Alex on 4/22/2016.
 */
public class AutomatedPlayer extends  Player {
    AutomatedPlayer( int playerNumber,Game game){
        super(playerNumber,game);
    }

    @Override
    public synchronized void postWord(String word) {

    }

    @Override
    public void notifyTurnSwitch() {
        this.notify();
    }

    @Override
    public void run() {
        while(true){
            try {
                this.wait();
                Character[] tilesArray=new Character[7];
                this.tiles.toArray(tilesArray);
                String foundString=searchForWord("",tilesArray,0,this.tiles.size());
                if(foundString==null) {
                    discardTilesIfStuck();
                    //TODO UPDATE GUI
                }
                else {
                    this.checkValidWordAndUpdateDataStructures(foundString);
                    UpdateGui.update(this, true, foundString);
                }

            } catch (InterruptedException e) {
                break;
            }

        }
    }
    private String searchForWord(String current,Character[] tiles,int index,int maxIndex){
        if(index>maxIndex){
            return null;
        }
        for (int i=0;i<tiles.length;i++) {
            if(tiles[i]!='\0') {
                String appended = current + tiles[i];
                boolean isValid=gamePlayer.postWord(appended);
                if(isValid)
                    return appended;
                char aux=tiles[i];
                tiles[i]='\0';

                String returnedString=searchForWord(appended,tiles,index+1,maxIndex);
                tiles[i]=aux;
                if(returnedString!=null)
                    return returnedString;
            }
        }
        return null;
    }
}
