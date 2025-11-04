package manager;

import java.util.Scanner;
import service.BookService;

/**
 * Quản lý menu và các thao tác liên quan đến Sách
 */
public class BookManager {
    private final BookService bookService;
    private final Scanner scanner;

    public BookManager(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Sách ---");
            System.out.println("1. Thêm sách");
            System.out.println("2. Xóa sách");
            System.out.println("3. Sửa thông tin sách (theo ID)");
            System.out.println("4. Tìm kiếm sách (theo ID)");
            System.out.println("5. Hiển thị thống kê nhanh");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: bookService.addBook(); break;
                    case 2: bookService.deleteBook(); break;
                    case 3: bookService.updateBook(); break;
                    case 4: bookService.findBookById(); break;
                    case 5: bookService.thongke(); break;
                    case 0: return;
                    default: System.out.println("❌ Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng nhập số.");
                choice = -1;
            }
        } while (choice != 0);
    }
}
