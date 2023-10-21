package com.example.obslugakaspw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public AnchorPane parent;

    @Override
    public void start(Stage stage) {

        try {
            parent = FXMLLoader.load(getClass().getResource("Scena.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Symulacja obsługi kas w supermarkecie - Daniel Siara");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}