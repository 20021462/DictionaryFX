package com.example.DictionaryFx;

import control.DictionaryCommandline;
import control.DictionaryManagement;
import control.Translator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Dictionary;
import model.Word;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DictionaryController implements Initializable {
    private static Dictionary dict = new Dictionary();

    public static Word word;

    private Stage stage;
    private Scene scene;

    private MyListener myListener = new MyListener() {
        @Override
        public void onClickListener(Word word) {
            setChosenWord(word);
        }
    };

    @FXML
    private Label chosenWordPronoun;

    @FXML
    private Label chosenWordTarget;

    @FXML
    private TextArea chosenWordExplain;

    @FXML
    private GridPane searchGrid;

    @FXML
    private TextField searchBar;

    @FXML
    private ImageView speakerButton;

    @FXML
    private TextArea wordExplainInput;

    @FXML
    private TextField wordSoundInput;

    @FXML
    private TextField wordTargetInput;

    @FXML
    private TextArea wordTranslateInput;

    @FXML
    private TextArea wordTranslateOutput;


    private void setChosenWord(Word word) {
        chosenWordTarget.setText(word.getWordTarget());
        chosenWordPronoun.setText(word.getWordSound());
        chosenWordExplain.setText(word.getWordExplain());
        speakerButton.setVisible(true);
    }

    @FXML
    private void addButtonClicked() {
        String wordTarget = wordTargetInput.getText();
        String wordSound = wordSoundInput.getText();
        String wordExplain = wordExplainInput.getText();
        if (wordTarget.equals("")) return;
        dict.add(wordTarget, wordSound, wordExplain);
        searchButtonClicked();
    }

    @FXML
    private void removeButtonClicked() {
        int[] arr = DictionaryCommandline.dictionarySearcher(dict, word.getWordTarget());
        dict.remove(arr[0]);
        searchButtonClicked();
        System.out.println(dict.getWords().size());
    }

    @FXML
    private void saveButtonClicked() {
        DictionaryManagement.dictionaryExportToFile("dictionary.txt", dict);
    }

    @FXML
    private void searchButtonClicked() {
        searchGrid.getChildren().clear();
        String find = searchBar.getText();
        if (find == "") {
            return;
        }
        find = find.toLowerCase(Locale.ROOT);
        int[] arr = DictionaryCommandline.dictionarySearcher(dict, find);
        if (arr[0] == -1) {
            return;
        }
        try {
            for (int i = arr[0]; i <= arr[1]; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Word.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                WordController wordController = fxmlLoader.getController();
                wordController.setData(dict.getWords().get(i), myListener);

                searchGrid.add(anchorPane, 0, i);
                searchGrid.setMinWidth(220);
                searchGrid.setPrefWidth(220);
                searchGrid.setMaxWidth(220);

                searchGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                searchGrid.setPrefHeight(30);
                searchGrid.setMaxHeight(30);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void speakWord() {
        word.speak();
    }


    @FXML
    void translate() {
        String input = wordTranslateInput.getText();
        String output = Translator.translate("en", "vi", input);
        wordTranslateOutput.setText(output);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile("dictionary.txt", dict);
        speakerButton.setVisible(false);
    }

}
