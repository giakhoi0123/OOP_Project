// src/model/Payment.java
package model;

abstract public class Payment extends Fine{
    public Payment() {
        super();
    }
    public abstract  void processFinePayment();
    public abstract void viewPaymentHistory();
    public abstract void calculateTotalFine();
    public abstract int getPaymentCount();
}