package com.example.DictionaryFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("Dictionary.fxml"));
        Scene scene = new Scene(scrollPane);
        stage.setTitle("Unfriendly Dictionary");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.getIcons().add(new Image(DictionaryApplication.class.getResourceAsStream("dictionary.png")));
        //stage.setResizable(false);
        //stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        launch();
    }
}
