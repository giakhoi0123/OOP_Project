package service;

import model.Fine;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

interface IPaymentService {
    void processFinePayment();
    void viewPaymentHistory();
    void calculateTotalFine();
    int getPaymentCount();
}

public class PaymentService implements IPaymentService {
    private static final int MAX_PAYMENTS = 100; // tối đa 100 giao dịch
    private Fine[] paymentHistory = new Fine[MAX_PAYMENTS];
    private int count = 0; // số lượng giao dịch thực tế
    private Scanner scanner = new Scanner(System.in);

    // Xử lý thanh toán
    public void processFinePayment() {
        if (count >= MAX_PAYMENTS) {
            System.out.println("Danh sách thanh toán đã đầy!");
            return;
        }

        System.out.print("Nhập số tiền phạt: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Số tiền không hợp lệ.");
            return;
        }

        System.out.println("Chọn phương thức thanh toán:");
        System.out.println("1. Tiền mặt");
        System.out.println("2. Thẻ tín dụng");
        System.out.println("3. Ví điện tử");
        System.out.print("Lựa chọn: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        String paymentMethod;
        switch (method) {
            case 1: paymentMethod = "Tiền mặt"; break;
            case 2: paymentMethod = "Thẻ tín dụng"; break;
            case 3: paymentMethod = "Ví điện tử"; break;
            default:
                System.out.println("Phương thức không hợp lệ.");
                return;
        }

        // Lưu vào mảng
        Fine fine = new Fine();
        fine.setAmount(amount);
        fine.setPaymentMethod(paymentMethod);
        fine.setPaymentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        paymentHistory[count] = fine;
        count++;

        System.out.println("Thanh toán thành công: " + amount + " bằng " + paymentMethod);
    }

    // Xem lịch sử
    public void viewPaymentHistory() {
        if (count == 0) {
            System.out.println("Chưa có giao dịch nào.");
            return;
        }
        for (int i = 0; i < count; i++) {
            Fine f = paymentHistory[i];
            System.out.println((i + 1) + ". " + f.getAmount() + " | " + f.getPaymentMethod() + " | " + f.getPaymentTime());
        }
    }

    // Tính tổng tiền phạt
    public void calculateTotalFine() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += paymentHistory[i].getAmount();
        }
        System.out.println("Tổng tiền phạt: " + total);
    }

    // Lấy số lượng giao dịch
    public int getPaymentCount() {
        return count;
    }
}
