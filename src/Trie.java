import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/22/2016.
 */
public class Trie {
    class TrieNode{
        public char getLetter() {
            return letter;
        }
        List<TrieNode>  children;
        public void setLetter(char letter) {
            this.letter = letter;
        }

        public boolean isValidWord() {
            return isValidWord;
        }
        TrieNode childWithLetter(char letter){
            for (TrieNode child : children) {
                if(child.getLetter()==letter)
                    return child;
            }
            return null;
        }
        boolean childContainsWord(String stringToSearch){
            if(stringToSearch=="")
                return true;
            for (TrieNode child : children) {
                if(stringToSearch.startsWith(""+child.getLetter()))
                    return child.childContainsWord(stringToSearch.substring(1));
            }
            return false;
        }
        public void setValidWord() {
            isValidWord = true;

        }
        void addChild(TrieNode child){
            children.add(child);
        }
        TrieNode(char letter){
            this.letter=letter;
            children= new ArrayList<>();
        }

        private    char letter;
        private boolean isValidWord;

    }
    TrieNode root=new TrieNode('C');//C de la ciubi:X nu conteaza oricum:X
    boolean isValidWord(String str){
        char[] word= str.toCharArray();
        TrieNode currentNode=root;
        for(int i=0;i<word.length;i++){
            TrieNode nextNode=currentNode.childWithLetter(word[i]);
            if(nextNode==null)
                return false;
            currentNode=nextNode;
        }
        return true;
    }
    void insertWord(String str){
        char[] word= str.toCharArray();
        TrieNode currentNode=root;
        int wordPosition=0;
        for(;wordPosition<word.length;){
            TrieNode nextNode=currentNode.childWithLetter(word[wordPosition]);
            if(nextNode==null)
                break;
            currentNode=nextNode;
            ++wordPosition;
        }
        for(;wordPosition<word.length;wordPosition++){
            TrieNode newLetterNode=new TrieNode(word[wordPosition]);
            currentNode.addChild(newLetterNode);
            currentNode=newLetterNode;
        }
        currentNode.setValidWord();
    }

}
