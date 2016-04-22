import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class AcceptedWords {
    private Trie trie;
    AcceptedWords(Trie trie,List<String> words ){
        //TODO TRIE
        this.trie=trie;
        for (String word : words) {
            this.insertWord(word);
        }
    }
   public boolean isAcceptedWord(String string){
        return  trie.isValidWord(string);
    }
    public void insertWord(String string){
        trie.insertWord(string);
    }


}
