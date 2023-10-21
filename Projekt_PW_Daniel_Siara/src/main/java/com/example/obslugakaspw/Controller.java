package com.example.obslugakaspw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Controller {

    @FXML
    public TextField numberBefBreak;

    @FXML
    public TextField numberCR;

    @FXML
    public TextField numberCust;

    @FXML
    public Button startButton;

    @FXML
    public List<Circle> circleList;

    @FXML
    public List<Rectangle> crList;


    App panel;

    {
        try {
            panel = new App(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onStartButton(ActionEvent event) {
        System.out.println("Start symulacji");
        panel.onStartButton();
    }
}

