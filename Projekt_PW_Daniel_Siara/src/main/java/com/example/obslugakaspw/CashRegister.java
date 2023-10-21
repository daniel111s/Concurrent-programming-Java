package com.example.obslugakaspw;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashRegister {

    public volatile int[] crQueue;
    public volatile int[] byCR;
    public volatile int[] befBreak;
    public volatile int toBreak;

    public int numOfCR;
    String numberOnly;
    Controller controller;
    int cusID;
    TranslateTransition transition = new TranslateTransition();

    final Lock dostep = new ReentrantLock();

    final Condition pay = dostep.newCondition();

    public CashRegister(int nocr, int bb, Controller controller) {
        numOfCR = nocr;
        crQueue = new int[numOfCR];
        byCR = new int[numOfCR];
        befBreak = new int[numOfCR];
        toBreak = bb;
        this.controller = controller;
    }

    public void paymentStart(String name, int crID) throws InterruptedException {

        while (befBreak[crID] == toBreak) {
            Thread.sleep(50);
        }

        dostep.lock();
        try {

            System.out.println("<><>  \t" + name + " staje w kolejce do kasy " + crID + " jest " + (crQueue[crID] + 1) + " w kolejce.");

            numberOnly = name.replaceAll("[^0-9]", "");
            cusID = Integer.parseInt(numberOnly);
            transition.setToX(-714 + (crID * 150));
            transition.setToY(342);
            transition.setDuration(Duration.millis(100));
            transition.setNode(controller.circleList.get(cusID));
            transition.play();

            crQueue[crID]++;

            Thread.sleep(250);
            while (byCR[crID] > 0) {
                pay.await();
            }
            befBreak[crID]++;


            System.out.println(">>>> \t" + name + " podchodzi do kasy nr " + crID);

            transition.setToX(-714 + (crID * 150));
            transition.setToY(488);
            transition.setDuration(Duration.millis(100));
            transition.setNode(controller.circleList.get(cusID));
            transition.play();

            crQueue[crID]--;

            byCR[crID]++;

        } finally {
            dostep.unlock();
        }
        Thread.sleep(1000);
    }

    public void paymentStop(String name, int crID) throws InterruptedException {

        dostep.lock();
        try {
            numberOnly = name.replaceAll("[^0-9]", "");
            cusID = Integer.parseInt(numberOnly);

            byCR[crID]--;

            System.out.println("<<<< \t" + name + " odchodzi od kasy nr " + crID + " pozostalo w kolejce: " + crQueue[crID]);
            controller.circleList.get(cusID).setVisible(false);
            pay.signal();


        } finally {
            dostep.unlock();
        }

        if (befBreak[crID] == toBreak && crQueue[crID] == 0) {

            System.out.println("Kasa nr " + crID + " robi przerwe");
            controller.crList.get(crID).setFill(Color.ORANGE);
            Thread.sleep(500 * numOfCR);

            System.out.println("Kasa nr " + crID + " konczy przerwe");
            controller.crList.get(crID).setFill(Color.GRAY);
            befBreak[crID] = 0;
        }
    }
}
