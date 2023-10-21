package com.example.obslugakaspw;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {

    public static volatile int[] queue;
    static Thread[] customers;
    int initNumCustomers = 0;
    int initNumCR = 0;
    int initNumBefBreak = 0;
    static int MAXNUMBEROFCUSTOMERS = 200;
    static int MAXNUMBEROFCASHREGISTERS = 12;

    Controller controller;
    TranslateTransition transitionCus[] = new TranslateTransition[MAXNUMBEROFCUSTOMERS];
    TranslateTransition transitionCR[] = new TranslateTransition[MAXNUMBEROFCASHREGISTERS];


    App(Controller controller) throws InterruptedException {
        this.controller = controller;
    }

    public boolean isItNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isTextCorrect(String string) {
        if (string.length() != 0 && isItNumber(string)) {
            return Integer.parseInt(string) > 0;
        }
        return false;
    }

    private void initProcesses() {

        for (int i = 0; i < MAXNUMBEROFCUSTOMERS; i++) {
            TranslateTransition tranCus = new TranslateTransition();
            transitionCus[i] = tranCus;
        }
        for (int i = 0; i < MAXNUMBEROFCASHREGISTERS; i++) {
            TranslateTransition tranCR = new TranslateTransition();
            transitionCR[i] = tranCR;
        }


        for (int i = 0; i < initNumCR; i++) {
            transitionCR[i].setToX(-830 + (i * 150));
            transitionCR[i].setToY(421);
            controller.crList.get(i).setRotate(0);
            transitionCR[i].setDuration(Duration.seconds(1));
            transitionCR[i].setNode(controller.crList.get(i));
            transitionCR[i].play();
        }

        for (int i = 0; i < initNumCustomers; i++) {
            transitionCus[i].setDuration(Duration.seconds(1));
            transitionCus[i].setToX(-694 + (i * 10));
            transitionCus[i].setToY(102);
            transitionCus[i].setNode(controller.circleList.get(i));
            transitionCus[i].play();
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customers = new Thread[initNumCustomers];
        queue = new int[initNumCR];

        CashRegister cashRegister = new CashRegister(initNumCR, initNumBefBreak, controller);
        String name;
        int val = 1;
        int temp = 0;

        for (int i = 0; i < initNumCustomers; i++) {

            name = "Klient nr " + i;

            customers[i] = new Customer(name, cashRegister, temp, val, initNumCustomers);
            temp = (temp + 1) % initNumCR;
            customers[i].start();
        }


        for (int i = 0; i < initNumCustomers; i++) {
            try {
                customers[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Koniec symulacji ");
    }

    public void initProcWithTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                initProcesses();
                return null;
            }
        };
        new Thread(task).start();
    }

    public void onStartButton() {
        int numCustomers = 0;
        int numCR = 0;
        int numBefBreak = 0;

        try (InputStream input = App.class.getClassLoader().getResourceAsStream("data.properties")) {

            Properties properties = new Properties();

            if (input == null) {
                System.out.println("File not found");
                return;
            }

            properties.load(input);
            numCustomers = Integer.parseInt(properties.getProperty("numCustomers"));
            numCR = Integer.parseInt(properties.getProperty("numCR"));
            numBefBreak = Integer.parseInt(properties.getProperty("numBefBreak"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String textNumberCust = controller.numberCust.getText();
        String textNumberCR = controller.numberCR.getText();
        String textNumberBefBreak = controller.numberBefBreak.getText();

        if (isTextCorrect(textNumberCust)) {
            numCustomers = Integer.parseInt(textNumberCust);
        }
        if (isTextCorrect(textNumberCR)) {
            numCR = Integer.parseInt(textNumberCR);
        }
        if (isTextCorrect(textNumberBefBreak)) {
            numBefBreak = Integer.parseInt(textNumberBefBreak);
        }

        controller.numberCust.setText(numCustomers + "");
        controller.numberCR.setText(numCR + "");
        controller.numberBefBreak.setText(numBefBreak + "");
        this.initNumCustomers = numCustomers;
        this.initNumCR = numCR;
        this.initNumBefBreak = numBefBreak;

        initProcWithTask();

    }
}