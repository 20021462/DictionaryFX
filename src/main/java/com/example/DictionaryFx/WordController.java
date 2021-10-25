package com.example.DictionaryFx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Word;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class WordController {
    private Word word;
    private MyListener myListener;

    @FXML
    private TextField wordTarget;

    public void setData(Word word, MyListener myListener) {
        this.word = word;
        this.myListener = myListener;
        wordTarget.setText(word.getWordTarget());
    }

    @FXML
    public void onClicked() {
        DictionaryController.word = this.word;
        myListener.onClickListener(word);
    }
}
