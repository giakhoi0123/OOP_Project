package model;

public class Fine {
    private double amount;

    public Fine() {
        this.amount = 0.0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void calculateFine() {
        // Giả sử tính tiền phạt (ví dụ: 10.0)
        this.amount = 10.0;
        System.out.println("Tiền phạt được tính: " + amount);
    }

    @Override
    public String toString() {
        return "Fine{" +
                "amount=" + amount +
                '}';
    }
}