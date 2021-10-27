package com.example.DictionaryFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DictionaryApplication extends Application {
    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("Dictionary.fxml"));
        primaryStage.setTitle("Unfriendly Dictionary");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.getIcons().add(new Image(DictionaryApplication.class.getResourceAsStream("dictionary.png")));
        //stage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        launch();
    }
}
