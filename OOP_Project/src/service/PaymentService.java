package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Fine;

public class PaymentService {
    private Scanner scanner = new Scanner(System.in);
    private List<Fine> paymentHistory = new ArrayList<>(); // Lưu lịch sử thanh toán

    // Xử lý thanh toán tiền phạt
    public void processFinePayment() {
        System.out.println("\n--- Xử lý thanh toán tiền phạt ---");
        System.out.print("Nhập số tiền phạt: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        if (amount <= 0) {
            System.out.println("Số tiền không hợp lệ. Vui lòng thử lại.");
            return;
        }

        System.out.println("Chọn phương thức thanh toán:");
        System.out.println("1. Tiền mặt");
        System.out.println("2. Thẻ tín dụng");
        System.out.println("3. Ví điện tử");
        System.out.print("Lựa chọn: ");
        int method = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        String paymentMethod;
        switch (method) {
            case 1:
                paymentMethod = "Tiền mặt";
                break;
            case 2:
                paymentMethod = "Thẻ tín dụng";
                break;
            case 3:
                paymentMethod = "Ví điện tử";
                break;
            default:
                System.out.println("Phương thức thanh toán không hợp lệ.");
                return;
        }

        Fine fine = new Fine();
        fine.setAmount(amount);
        paymentHistory.add(fine); // Lưu vào lịch sử thanh toán

        System.out.println("Thanh toán thành công!");
        System.out.println("Số tiền: " + fine.getAmount() + " | Phương thức: " + paymentMethod);
    }

    // Xem lịch sử thanh toán
    public void viewPaymentHistory() {
        System.out.println("\n--- Lịch sử thanh toán ---");
        if (paymentHistory.isEmpty()) {
            System.out.println("Chưa có giao dịch nào.");
            return;
        }

        for (int i = 0; i < paymentHistory.size(); i++) {
            Fine fine = paymentHistory.get(i);
            System.out.println((i + 1) + ". Số tiền: " + fine.getAmount());
        }
    }

    // Tính tổng tiền phạt
    public void calculateTotalFine() {
        System.out.println("\n--- Tính tổng tiền phạt ---");
        double total = 0;
        for (Fine fine : paymentHistory) {
            total += fine.getAmount();
        }
        System.out.println("Tổng số tiền phạt đã thanh toán: " + total);
    }

    // Lấy số lượng giao dịch thanh toán
    public int getPaymentCount() {
        return paymentHistory.size();
    }
}