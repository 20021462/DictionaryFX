package com.example.DictionaryFx;

import control.DictionaryCommandline;
import control.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Word;

import java.util.Locale;

import static com.example.DictionaryFx.DictionaryApplication.mainController;
import static com.example.DictionaryFx.DictionaryController.dict;
import static com.example.DictionaryFx.DictionaryController.wordNumber;

public class TranslateController {
    public Stage stage;
    private boolean enToVi=true;

    @FXML
    private TextField wordTranslateInput;

    @FXML
    private TextArea wordTranslateOutput;

    @FXML
    private Label langFrom;

    @FXML
    private Label langTo;

    @FXML
    private void translateButtonClicked(MouseEvent mouseEvent) {
        if (enToVi){
            String input = wordTranslateInput.getText();
            String output = Translator.translate("en", "vi", input);
            wordTranslateOutput.setText(output);
            return;
        }
        String input = wordTranslateInput.getText();
        String output = Translator.translate("vi", "en", input);
        wordTranslateOutput.setText(output);
    }

    @FXML
    private void changeButtonClicked(MouseEvent mouseEvent) {
        enToVi=!enToVi;
        String a= langFrom.getText();
        langFrom.setText(langTo.getText());
        langTo.setText(a);
        System.out.println();
    }
}
