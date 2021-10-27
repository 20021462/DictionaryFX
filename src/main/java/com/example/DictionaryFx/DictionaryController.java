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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Dictionary;
import model.Word;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DictionaryController implements Initializable {
    public static Dictionary dict = new Dictionary();

    public static Word word;

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


    private void setChosenWord(Word word) {
        chosenWordTarget.setText(word.getWordTarget());
        chosenWordPronoun.setText(word.getWordSound());
        chosenWordExplain.setText(word.getWordExplain());
        speakerButton.setVisible(true);
    }

    @FXML
    private void addButtonClicked() {
//        String wordTarget = wordTargetInput.getText();
//        String wordSound = wordSoundInput.getText();
//        String wordExplain = wordExplainInput.getText();
//        if (wordTarget.equals("")) return;
//        dict.add(wordTarget, wordSound, wordExplain);
//        searchButtonClicked();
    }

    @FXML
    private void editButtonClicked() throws IOException {
        if (word == null) return;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
        Parent root = fxmlLoader.load();
        EditController editController = fxmlLoader.getController();
        editController.setWordEdit(word);
        Scene secondScene = new Scene(root);
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit");
        newWindow.getIcons().add(new Image(Objects.requireNonNull(DictionaryApplication.class.getResourceAsStream("dictionary.png"))));
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(DictionaryApplication.primaryStage);
        newWindow.setX(DictionaryApplication.primaryStage.getX() + 200);
        newWindow.setY(DictionaryApplication.primaryStage.getY() + 100);
        newWindow.show();
    }

    @FXML
    private void removeButtonClicked() throws IOException {
        if (word == null) return;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Remove.fxml"));
        Parent root = fxmlLoader.load();
        RemoveController removeController = fxmlLoader.getController();
        Scene secondScene = new Scene(root);
        Stage newWindow = new Stage();
        removeController.stage = newWindow;
        newWindow.setTitle("Remove");
        newWindow.getIcons().add(new Image(Objects.requireNonNull(DictionaryApplication.class.getResourceAsStream("remove.png"))));
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(DictionaryApplication.primaryStage);
        newWindow.setX(DictionaryApplication.primaryStage.getX() + 200);
        newWindow.setY(DictionaryApplication.primaryStage.getY() + 100);
        newWindow.show();
        newWindow.setResizable(false);

//        while (true) {
//            if (removeController.cancel) {
//                newWindow.close();
//                return;
//            } else if (removeController.remove) {
//                int[] arr = DictionaryCommandline.dictionarySearcher(dict, word.getWordTarget());
//                dict.remove(arr[0]);
//                searchButtonClicked();
//            }
//        }

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
//        String input = wordTranslateInput.getText();
//        String output = Translator.translate("en", "vi", input);
//        wordTranslateOutput.setText(output);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile("dictionary.txt", dict);
        speakerButton.setVisible(false);
    }

}
