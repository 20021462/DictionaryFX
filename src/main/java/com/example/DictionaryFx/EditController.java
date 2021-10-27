package com.example.DictionaryFx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Word;

import static com.example.DictionaryFx.DictionaryApplication.mainController;
import static com.example.DictionaryFx.DictionaryController.dict;

public class EditController {
    private Word word;
    private int wordNumber;

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
    private void editButtonClicked() {
        Word newWord = new Word(wordTarget.getText(), wordSound.getText(), wordExplain.getText());
        DictionaryController.word = newWord;
        dict.getWords().set(wordNumber, newWord);
        mainController.setChosenWord(newWord);
        mainController.searchButtonClicked();
        stage.close();
    }

    public void setWordEdit(Word word, int wordNumber) {
        this.wordNumber = wordNumber;
        this.word = word;
        wordExplain.setText(word.getWordExplain());
        wordSound.setText(word.getWordSound());
        wordTarget.setText(word.getWordTarget());
    }
}
