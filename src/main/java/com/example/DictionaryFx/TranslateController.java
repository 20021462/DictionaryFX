package com.example.DictionaryFx;

import control.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
        wordTranslateInput.setText("");
        wordTranslateOutput.setText("");
        String a= langFrom.getText();
        langFrom.setText(langTo.getText());
        langTo.setText(a);
        System.out.println();
    }
}
