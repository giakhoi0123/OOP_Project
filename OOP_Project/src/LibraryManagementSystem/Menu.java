package LibraryManagementSystem;

import java.util.Scanner;
import service.*;

public class Menu {
    public static void menu() {
        BookService bookService = new BookService();
        UserService userService = new UserService();
        LoanService loanService = new LoanService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();

        MenuService menu = new MenuService(bookService, userService, loanService, paymentService, notificationService);

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
                    case 1 -> menu.menuSach(scanner);
                    case 2 -> menu.menuNguoiDung(scanner);
                    case 3 -> menu.menuMuonTra(scanner);
                    case 4 -> menu.menuThanhToan(scanner);
                    case 5 -> menu.menuThongKe();
                    case 6 -> menu.menuThongBao(scanner);
                    case 0 -> System.out.println("Thoát chương trình. Tạm biệt!");
                    default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } while (luaChon != 0);
        }
    }
}
