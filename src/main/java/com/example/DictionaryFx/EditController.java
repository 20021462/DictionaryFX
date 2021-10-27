package com.example.DictionaryFx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Word;

public class EditController {
    private Word word;

    @FXML
    private TextArea wordExplain;

    @FXML
    private TextField wordSound;

    @FXML
    private TextField wordTarget;



    public void setWordEdit(Word word) {
        this.word = word;
        wordExplain.setText(word.getWordExplain());
        wordSound.setText(word.getWordSound());
        wordTarget.setText(word.getWordTarget());
        System.out.println("YES");
    }
}
