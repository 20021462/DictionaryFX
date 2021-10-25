import control.DictionaryCommandline;
import control.DictionaryManagement;
import model.Dictionary;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromFile("dictionary.txt", dict);
        int[] arr = DictionaryCommandline.dictionarySearcher(dict, "ab");
        System.out.println(arr[0] + " " + arr[1]);

    }
}