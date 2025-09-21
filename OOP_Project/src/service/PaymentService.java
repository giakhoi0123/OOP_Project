// src/service/PaymentService.java
package service;

import model.Fine;

public class PaymentService {
    // Xử lý thanh toán tiền phạt
    public void processFinePayment() {
        Fine fine = new Fine();
        fine.calculateFine(); // Giả sử tính toán tiền phạt
        System.out.println("Đã xử lý thanh toán tiền phạt.");
    }
}