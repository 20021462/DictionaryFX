package control;

import model.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
    private static Scanner scanner=new Scanner(System.in);
    /**
     * This method inserts word using command line.
     *
     * @param dict a Dictionary class object
     */
    public static void insertFromCommandline(Dictionary dict) {
        System.out.println("Enter number of added words: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter words:");
            String wordTarget = scanner.next();
            scanner.nextLine();
            System.out.println("Enter sound:");
            String wordSound = scanner.nextLine();
            System.out.println("Enter meaning:");
            String wordExplain = scanner.nextLine();
            dict.add(wordTarget, wordSound,wordExplain);
        }
        System.out.println("Added "+n+" words successful");
    }

    /**
     * This method removes word using command line by index.
     * @param dict a Dictionary class object
     * @param num index of that word
     */
    public static void removeFromCommandline(Dictionary dict, int num) {
        if (num>=0&&num<dict.getWords().size()){
            DictionaryCommandline.showOneWords(dict, num);
            System.out.println("Removed word \""+dict.getWords().get(num).getWordTarget() +"\" successfully");
            dict.remove(num);
            return;
        }
        System.out.println("Invalid Index!");
    }

    /**
     * This method removes word using command line by word target.
     *
     * @param dict a Dictionary class object
     * @param s    the word
     */
    public static void removeFromCommandline(Dictionary dict, String s){
        int num=DictionaryManagement.dictionaryLookup(s,dict);
        if(num!=-1){
            System.out.println("Removed word \""+dict.getWords().get(num).getWordTarget() +"\" successfully");
            dict.remove(num);

        }

    }


    /**
     *This method is used to add, remove, search or show words in dictionary.
     *
     * @param dict a Dictionary class object
     */
    public static void commandFromCommandline(Dictionary dict) {
        boolean exit = false;
        while (!exit) {
            String command = scanner.next();
            if (command.equals(Dictionary.ADD)) {
                insertFromCommandline(dict);
            } else if (command.equals(Dictionary.REMOVE)) {
                System.out.println("Enter index of word you want to remove: ");
                int n = scanner.nextInt();
                removeFromCommandline(dict, n);
            } else if (command.equals(Dictionary.SEARCH)) {
                System.out.println("Enter word:");
                String subWord = scanner.next();
                DictionaryCommandline.dictionarySearcher(dict, subWord);
            } else if (command.equals(Dictionary.SHOW)) {
                DictionaryCommandline.showAllWords(dict);
            } else if (command.equals(Dictionary.EXIT)) {
                exit = true;
            }
        }
    }

    /**
     * This method insert word by a file.
     *
     * @param path directory to the file
     * @param dict a Dictionary class object
     */
    public static void insertFromFile(String path,Dictionary dict) {
        try{
            boolean permit=false;
            File myFile = new File(path);
            Scanner fileScanner = new Scanner(myFile);
            String wordTarget="";
            String wordSound="";
            String wordExplain="";
            while (fileScanner.hasNextLine()) {
                String b = fileScanner.nextLine();
                if (b.charAt(0) == '@') {
                    if (permit){
                        dict.add(wordTarget,wordSound,wordExplain);
                        wordExplain="";
                    }
                    if (!permit) permit=true;
                    for (int i = 0; i < b.length(); i++) {
                        if (b.charAt(i) == '/') {
                            for (int j=i+1;j<b.length();j++){
                                if (b.charAt(j)=='/'){
                                    wordTarget=b.substring(1,i-1);
                                    wordSound=b.substring(i,j+1);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }else {
                    wordExplain = wordExplain.concat(b+'\n');
                }
            }
            dict.add(wordTarget,wordSound,wordExplain);
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An import error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * This method fine if there is your word in the dictionary, and show its meaning.
     *
     * @param word the word you want to remove
     * @param dict a Dictionary class object
     */
    public static int dictionaryLookup(String word,Dictionary dict){
        for (int i=0;i< dict.getWords().size();i++){
            if (dict.getWords().get(i).getWordTarget().equals(word)){
                DictionaryCommandline.showOneWords(dict,i);
                return i;
            }
        }
        System.out.println("Word not found!");
        return -1;
    }

    /**
     * This method export all words in a dictionary to a .txt file.
     *
     * @param path local path or file name
     * @param dict a Dictionary class object
     * @throws IOException
     */
    public static void dictionaryExportToFile(String path, Dictionary dict) {
        try{
            File dictFile = new File(path);
            dictFile.createNewFile();
            FileWriter myWriter = new FileWriter(dictFile);
            for (int i = 0; i < dict.getWords().size(); i++) {
                myWriter.write('@'+dict.getWords().get(i).getWordTarget()
                        +' '+dict.getWords().get(i).getWordSound()
                        +'\n'+dict.getWords().get(i).getWordExplain());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An export error occurred.");
            e.printStackTrace();
        }

    }
}
