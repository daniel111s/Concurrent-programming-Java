package com.example.obslugakaspw;

public class Customer extends Thread {

    int crID;
    public CashRegister CR;
    int val;
    int numCustomers;

    public Customer(String name, CashRegister cr, int crid, int v, int nc) {
        setName(name);
        CR = cr;
        val = v;
        crID = crid;
        numCustomers = nc;
    }

    public void shopping() {
        try {
            Thread.sleep((int) (Math.random() * 500 * numCustomers) + 5 * numCustomers);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void payment() {
        try {
            Thread.sleep((int) (Math.random() * 5 * numCustomers) + 5 * numCustomers);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (int i = 0; i < val; i++) {
            try {

                shopping();

                int pom = 0;

                while (CR.befBreak[crID] >= CR.toBreak && pom < CR.numOfCR + 1) {
                    crID = (crID + 1) % CR.numOfCR;
                    //Thread.sleep(100);
                    pom++;
                }

                CR.paymentStart(getName(), crID);
                payment();
                CR.paymentStop(getName(), crID);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}