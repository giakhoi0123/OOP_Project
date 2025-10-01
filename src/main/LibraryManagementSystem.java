package main;

import java.util.Scanner;
import service.BookService;
import service.LoanService;
import service.NotificationService;
import service.PaymentService;
import service.ReaderService;
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Khởi tạo các service
        BookService bookService = new BookService();
        ReaderService userService = new ReaderService();
        LoanService loanService = new LoanService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();
        try (Scanner scanner = new Scanner(System.in)) {
            int luaChon;
            do {
                System.out.println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====");
                System.out.println("1. Quản lý sách");
                System.out.println("2. Quản lý người dùng");
                System.out.println("3. Quản lý mượn/trả");
                System.out.println("4. Xử lý thanh toán");
                System.out.println("5. Thống kê");
                System.out.println("6. Gửi thông báo");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                luaChon = scanner.nextInt();

                switch (luaChon) {
                    case 1 -> quanLySach(bookService, scanner);
                    case 2 -> quanLyNguoiDung(userService, scanner);
                    case 3 -> quanLyMuonTra(loanService, scanner);
                    case 4 -> xuLyThanhToan(paymentService, scanner);
                    case 5 -> thongKe(bookService, userService, loanService);
                    case 6 -> guiThongBao(notificationService, scanner);
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
    private static void quanLyNguoiDung(ReaderService userService, Scanner scanner) {
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
    // Thống kê
private static void thongKe(BookService bookService, ReaderService userService, LoanService loanService) {
    System.out.println("\n--- Thống kê ---");
    int soLuongSach = bookService.getTotalBooks();
    int soLuongNguoiDung = userService.getTotalUsers();
    int soLuongSachDangMuon = loanService.getTotalLoans();

    System.out.println("Số lượng sách: " + soLuongSach);
    System.out.println("Số lượng người dùng: " + soLuongNguoiDung);
    System.out.println("Số lượng sách đang được mượn: " + soLuongSachDangMuon);
}

// Gửi thông báo
private static void guiThongBao(NotificationService notificationService, Scanner scanner) {
    System.out.println("\n--- Gửi thông báo ---");
    System.out.println("1. Gửi thông báo mượn sách");
    System.out.println("2. Gửi thông báo trả sách");
    System.out.println("3. Xem lịch sử thông báo");
    System.out.print("Chọn chức năng: ");
    int luaChon = scanner.nextInt();
    scanner.nextLine(); // bỏ dòng thừa

    if (luaChon == 3) {
        notificationService.readNotifications();
        return;
    }

    System.out.print("Nhập tên người dùng: ");
    String tenNguoiDung = scanner.nextLine();
    System.out.print("Nhập tên sách: ");
    String tenSach = scanner.nextLine();

    if (luaChon == 1) {
        notificationService.sendNotification("Mượn", tenNguoiDung, tenSach);
    } else if (luaChon == 2) {
        notificationService.sendNotification("Trả", tenNguoiDung, tenSach);
    } else {
        System.out.println("Lựa chọn không hợp lệ.");
    }
}
}