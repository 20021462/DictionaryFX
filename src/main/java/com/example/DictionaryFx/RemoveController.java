package com.example.DictionaryFx;

import control.DictionaryCommandline;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Word;

import static com.example.DictionaryFx.DictionaryApplication.mainController;
import static com.example.DictionaryFx.DictionaryController.*;

public class RemoveController {
    public Stage stage;

    @FXML
    private void cancelButtonClicked() {
        stage.close();
    }

    @FXML
    private void removeButtonClicked() {
        int[] arr = DictionaryCommandline.dictionarySearcher(dict, word.getWordTarget());
        dict.remove(arr[0]);
        stage.close();
        mainController.searchButtonClicked();
        mainController.setChosenWord(new Word("", "", ""));
        mainController.speakerButton.setVisible(false);
        wordNumber = -1;
    }
}
