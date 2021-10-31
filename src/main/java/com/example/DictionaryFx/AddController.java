package com.example.DictionaryFx;

import control.DictionaryCommandline;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Word;

import static com.example.DictionaryFx.DictionaryApplication.mainController;
import static com.example.DictionaryFx.DictionaryController.dict;
import static com.example.DictionaryFx.DictionaryController.wordNumber;

public class AddController {
    public Stage stage;

    @FXML
    private TextArea wordExplain;

    @FXML
    private TextField wordSound;

    @FXML
    private TextField wordTarget;

    @FXML
    private void cancelButtonClicked() {
        stage.close();
    }

    @FXML
    private void addButtonClicked() {
        boolean add = true;
        int tmp = DictionaryCommandline.dictionarySearcher(dict, wordTarget.getText().toLowerCase())[0];
        if (wordTarget.getText().equals("")
                || tmp >= 0 && wordTarget.getText().toLowerCase().equals(dict.getWords().get(tmp))) {
            wordTarget.setStyle("-fx-border-color: #FF2B30");
            add = false;
        }
        if (wordSound.getText().equals("")) {
            wordSound.setStyle("-fx-border-color: #FF2B30");
            add = false;
        }
        if (wordExplain.getText().equals("")) {
            wordExplain.setStyle("-fx-border-color: #FF2B30");
            add = false;
        }
        if (!add) return;
        Word newWord = new Word(wordTarget.getText().toLowerCase(), wordSound.getText(), wordExplain.getText());
        DictionaryController.word = newWord;
        dict.add(wordTarget.getText().toLowerCase(), wordSound.getText(), wordExplain.getText());
        wordNumber = dict.sort(dict.getWords().size() - 1);
        System.out.println(wordNumber);
        mainController.setChosenWord(newWord);
        mainController.searchButtonClicked();
        stage.close();
    }

}
