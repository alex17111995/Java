package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Alex on 4/22/2016.
 */
public class Bag {
    List<Character> availableTiles = new ArrayList<>();
    Map<Character,Integer> valueOfTile;
    Random random = new Random();
    public Bag(){
        for(char letter='A';letter<='Z';letter++){
            for(int i=0;i<5;i++)
            availableTiles.add(letter);
        }
    }

    int findValueOfLetter(Character character) throws IllegalArgumentException{
        Integer value=valueOfTile.get(character);
        if(value==null)
            throw new IllegalArgumentException("Invalid letter");
        return value;
    }

    synchronized List<Character> getTiles(int numberOfTiles){
        List<Character> randomTiles= new ArrayList<>();
        for(int i=0;i<numberOfTiles;i++){
            if(availableTiles.size()==0)
                break;
            int randomPosition=random.nextInt(availableTiles.size());
            char charFromBag= availableTiles.get(randomPosition);
            availableTiles.remove(randomPosition);
            randomTiles.add(charFromBag);
        }
        return  randomTiles;

    }
}
