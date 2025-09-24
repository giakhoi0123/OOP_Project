package service;

import java.util.Scanner;

public class MenuService {
    private BookService bookService;
    private UserService userService;
    private LoanService loanService;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public MenuService(BookService bookService, UserService userService,LoanService loanService, PaymentService paymentService,NotificationService notificationService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    // 1. Menu Quản lý sách
    public void menuSach(Scanner scanner) {
        System.out.println("\n--- Quản lý sách ---");
        System.out.println("1. Thêm sách");
        System.out.println("2. Xóa sách");
        System.out.println("3. Tìm kiếm sách");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        switch (luaChon) {
            case 1 -> bookService.addBook();
            case 2 -> bookService.deleteBook();
            case 3 -> bookService.searchBook();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // 2. Menu Quản lý người dùng
    public void menuNguoiDung(Scanner scanner) {
        System.out.println("\n--- Quản lý người dùng ---");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        switch (luaChon) {
            case 1 -> userService.addUser();
            case 2 -> userService.deleteUser();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // 3. Menu Quản lý mượn/trả
    public void menuMuonTra(Scanner scanner) {
        System.out.println("\n--- Quản lý mượn/trả ---");
        System.out.println("1. Mượn sách");
        System.out.println("2. Trả sách");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        switch (luaChon) {
            case 1 -> loanService.issueLoan();
            case 2 -> loanService.processReturn();
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // 4. Menu Xử lý thanh toán
    public void menuThanhToan(Scanner scanner) {
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

    // 5. Menu Thống kê
    public void menuThongKe() {
        System.out.println("\n--- Thống kê ---");
        System.out.println("Số lượng sách: " + bookService.getTotalBooks());
        System.out.println("Số lượng người dùng: " + userService.getTotalUsers());
        System.out.println("Số lượng sách đang được mượn: " + loanService.getTotalLoans());
    }

    // 6. Menu Gửi thông báo
    public void menuThongBao(Scanner scanner) {
        System.out.println("\n--- Gửi thông báo ---");
        System.out.println("1. Gửi thông báo mượn sách");
        System.out.println("2. Gửi thông báo trả sách");
        System.out.println("3. Xem lịch sử thông báo");
        System.out.print("Chọn chức năng: ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

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
