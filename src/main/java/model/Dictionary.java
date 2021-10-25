package model;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>(50000);

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void add(String wordTarget, String wordSound, String wordExplain) {
        wordExplain=wordExplain.concat("\n");
        Word value=new Word(wordTarget,wordSound,wordExplain);
        words.add(value);
        for (int i=words.size()-1;i>0;i--){
            if (value.getWordTarget().compareTo(words.get(i-1).getWordTarget())<0){
                words.set(i,words.get(i-1));
            } else {
                words.set(i,value);
                break;
            }
        }
    }

    public void remove(int number) {
        words.remove(number);
    }

    public static String SHOW = "-show";
    public static String ADD = "-add";
    public static String REMOVE = "-remove";
    public static String SEARCH = "-search";
    public static String EXIT = "-exit";
}
