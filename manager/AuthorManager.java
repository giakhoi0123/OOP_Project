package manager;

import java.util.Scanner;
import service.AuthorService;

/**
 * Quản lý menu và các thao tác liên quan đến Tác giả
 */
public class AuthorManager {
    private final AuthorService authorService;
    private final Scanner scanner;

    public AuthorManager(AuthorService authorService) {
        this.authorService = authorService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Tác giả ---");
            System.out.println("1. Thêm tác giả");
            System.out.println("2. Xóa tác giả");
            System.out.println("3. Sửa thông tin tác giả");
            System.out.println("4. Tìm kiếm tác giả (theo ID)");
            System.out.println("5. Hiển thị tất cả tác giả");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: authorService.addAuthor(); break;
                    case 2: authorService.removeAuthor(); break;
                    case 3: authorService.updateAuthor(); break;
                    case 4: authorService.findAndShowAuthorById(); break;
                    case 5: authorService.showAllAuthors(); break;
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
