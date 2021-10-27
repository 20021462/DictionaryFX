package com.example.DictionaryFx;

import control.DictionaryCommandline;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import static com.example.DictionaryFx.DictionaryController.dict;
import static com.example.DictionaryFx.DictionaryController.word;

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

    }
}
