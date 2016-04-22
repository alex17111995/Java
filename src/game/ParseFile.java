package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alex on 4/23/2016.
 */
public class ParseFile {
    List<String> getList(File file){
        try {
            List<String> list= new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String word = scanner.nextLine();
                list.add(word);
            }
            scanner.close();
            return  list;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
