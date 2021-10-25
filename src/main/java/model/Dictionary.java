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
        words.add(new Word(wordTarget,wordSound,wordExplain));
        Word value=words.get(words.size()-1);
        for (int i=words.size()-1;i>0;i--){
            if (words.get(i).getWordTarget().compareTo(words.get(i-1).getWordTarget())<0){
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
