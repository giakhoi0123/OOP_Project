package model;

public class Fine {
    private double amount;          // Số tiền phạt
    private String paymentMethod;   // Phương thức thanh toán
    private String paymentTime;     // Thời gian thanh toán (dd/MM/yyyy HH:mm:ss)

    public Fine() {
        this.amount = 0.0;
        this.paymentMethod = "Chưa thanh toán";
        this.paymentTime = "Chưa có";
    }

    public Fine(double amount, String paymentMethod, String paymentTime) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentTime = paymentTime;
    }

    // Getter & Setter
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return "Thanh toán: " +
                "Số tiền = " + amount +
                ", Phương thức = '" + paymentMethod + '\'' +
                ", Thời gian = " + paymentTime;
    }
}
