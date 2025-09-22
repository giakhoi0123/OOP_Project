package main;

import java.util.Scanner;
import service.BookService;
import service.LoanService;
import service.PaymentService;
import service.UserService;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Khởi tạo các service
        BookService bookService = new BookService();
        UserService userService = new UserService();
        LoanService loanService = new LoanService();
        PaymentService paymentService = new PaymentService();

        try (Scanner scanner = new Scanner(System.in)) {
            int luaChon;
            do {
                System.out.println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====");
                System.out.println("1. Quản lý sách");
                System.out.println("2. Quản lý người dùng");
                System.out.println("3. Quản lý mượn/trả");
                System.out.println("4. Xử lý thanh toán");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                luaChon = scanner.nextInt();

                switch (luaChon) {
                    case 1 -> quanLySach(bookService, scanner);
                    case 2 -> quanLyNguoiDung(userService, scanner);
                    case 3 -> quanLyMuonTra(loanService, scanner);
                    case 4 -> xuLyThanhToan(paymentService, scanner);
                    case 0 -> System.out.println("Thoát chương trình. Tạm biệt!");
                    default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } while (luaChon != 0);
        }
    }

    // Quản lý sách
    private static void quanLySach(BookService bookService, Scanner scanner) {
        System.out.println("\n--- Quản lý sách ---");
        System.out.println("1. Thêm sách");
        System.out.println("2. Xóa sách");
        System.out.println("3. Tìm kiếm sách");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();

        switch (luaChon) {
            case 1 -> bookService.addBook();
            case 2 -> bookService.deleteBook();
            case 3 -> bookService.searchBook();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Quản lý người dùng
    private static void quanLyNguoiDung(UserService userService, Scanner scanner) {
        System.out.println("\n--- Quản lý người dùng ---");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();

        switch (luaChon) {
            case 1 -> userService.addUser();
            case 2 -> userService.deleteUser();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Quản lý mượn trả
    private static void quanLyMuonTra(LoanService loanService, Scanner scanner) {
        System.out.println("\n--- Quản lý mượn/trả ---");
        System.out.println("1. Mượn sách");
        System.out.println("2. Trả sách");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();

        switch (luaChon) {
            case 1 -> loanService.issueLoan();
            case 2 -> loanService.processReturn();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Xử lý thanh toán
    private static void xuLyThanhToan(PaymentService paymentService, Scanner scanner) {
        System.out.println("\n--- Xử lý thanh toán ---");
        System.out.println("1. Thanh toán tiền phạt");
        System.out.println("2. Xem lịch sử thanh toán");
        System.out.println("3. Tính tổng tiền phạt");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        switch (luaChon) {
            case 1 -> paymentService.processFinePayment();
            case 2 -> paymentService.viewPaymentHistory();
            case 3 -> paymentService.calculateTotalFine();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }
}
