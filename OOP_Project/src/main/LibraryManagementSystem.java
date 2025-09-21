// src/main/LibraryManagementSystem.java
package main;

import model.*;
import service.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Khởi tạo các service
        BookService bookService = new BookService();
        UserService userService = new UserService();
        LoanService loanService = new LoanService();
        PaymentService paymentService = new PaymentService();

        // Menu chính của hệ thống
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Quản lý sách");
            System.out.println("2. Quản lý người dùng");
            System.out.println("3. Quản lý mượn trả sách");
            System.out.println("4. Xử lý thanh toán");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageBooks(bookService, scanner);
                    break;
                case 2:
                    manageUsers(userService, scanner);
                    break;
                case 3:
                    manageLoans(loanService, scanner);
                    break;
                case 4:
                    processPayments(paymentService, scanner);
                    break;
                case 0:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // Quản lý sách
    private static void manageBooks(BookService bookService, Scanner scanner) {
        System.out.println("\n--- Quản lý sách ---");
        System.out.println("1. Thêm sách");
        System.out.println("2. Xóa sách");
        System.out.println("3. Tìm kiếm sách");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bookService.addBook();
                break;
            case 2:
                bookService.deleteBook();
                break;
            case 3:
                bookService.searchBook();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Quản lý người dùng
    private static void manageUsers(UserService userService, Scanner scanner) {
        System.out.println("\n--- Quản lý người dùng ---");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                userService.addUser();
                break;
            case 2:
                userService.deleteUser();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Quản lý mượn trả sách
    private static void manageLoans(LoanService loanService, Scanner scanner) {
        System.out.println("\n--- Quản lý mượn trả sách ---");
        System.out.println("1. Cấp phiếu mượn");
        System.out.println("2. Xử lý trả sách");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                loanService.issueLoan();
                break;
            case 2:
                loanService.processReturn();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Xử lý thanh toán
private static void processPayments(PaymentService paymentService, Scanner scanner) {
    System.out.println("\n--- Xử lý thanh toán ---");
    System.out.println("1. Thanh toán tiền phạt");
    System.out.println("2. Xem lịch sử thanh toán");
    System.out.println("3. Tính tổng tiền phạt");
    System.out.print("Chọn chức năng: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Đọc bỏ dòng thừa

    switch (choice) {
        case 1:
            paymentService.processFinePayment();
            break;
        case 2:
            paymentService.viewPaymentHistory();
            break;
        case 3:
            paymentService.calculateTotalFine();
            break;
        default:
            System.out.println("Lựa chọn không hợp lệ.");
    }
}
}