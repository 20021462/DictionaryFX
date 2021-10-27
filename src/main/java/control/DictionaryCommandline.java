package control;

import model.*;

public class DictionaryCommandline {
    /**
     * This method prints all words in a dictionary.
     *
     * @param dict a Dictionary class object
     */
    public static void showAllWords(Dictionary dict) {
        for (int i=0;i<dict.getWords().size();i++){
            showOneWords(dict,i);
        }
    }

    /**
     * This method prints word number n in a dictionary
     * @param dict a Dictionary class object
     * @param i index of word
     */
    public static void showOneWords(Dictionary dict, int i) {
        System.out.println(dict.getWords().get(i).getWordTarget()+" "+dict.getWords().get(i).getWordSound());
        System.out.println(dict.getWords().get(i).getWordExplain());
    }

    /**
     * This method searches words in dictionary.
     *
     * @param dict     dictionary object
     * @param wordFind searched string
     */
    public static int[] dictionarySearcher(Dictionary dict, String wordFind) {
        int left = 0, right = dict.getWords().size() - 1;
        boolean find = false;
        while (left <= right) {
            int size = wordFind.length();
            int middle = left + (right - left) / 2;
            String compareWord = dict.getWords().get(middle).getWordTarget();
            int n = compareSubString(wordFind, compareWord);
            if (n == 0) {
                left = middle;
                right = middle;
                find = true;
                break;
            } else if (n > 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        int[] list = new int[2];
        if (!find) {
            list[0] = -1;
            list[1] = -1;
            return list;
        }
        while (left > 0) {
            String compareWord = dict.getWords().get(--left).getWordTarget();
            if (compareSubString(wordFind, compareWord) != 0) {
                left++;
                break;
            }
        }
        if (right - left + 1 > 20) {
            right = left + 20 - 1;
        } else {
            while (right - left < 20 && right < dict.getWords().size() - 1) {
                String compareWord = dict.getWords().get(++right).getWordTarget();
                if (compareSubString(wordFind, compareWord) != 0) {
                    right--;
                    break;
                }
            }
        }
        list[0] = left;
        list[1] = right;
        return list;
    }

    private static int compareSubString(String substring, String text) {
        int size = substring.length();
        if (text.length() > size) {
            text = text.substring(0, size);
        }
        int n = substring.compareTo(text);
        return n;
    }

    public static void dictionaryBasic() {
    }

    public static void dictionaryAdvanced(){

    }
}
