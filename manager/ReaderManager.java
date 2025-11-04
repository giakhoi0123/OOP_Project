package manager;

import java.util.Scanner;
import service.ReaderService;

/**
 * Quản lý menu và các thao tác liên quan đến Độc giả
 */
public class ReaderManager {
    private final ReaderService readerService;
    private final Scanner scanner;

    public ReaderManager(ReaderService readerService) {
        this.readerService = readerService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Độc giả ---");
            System.out.println("1. Thêm độc giả");
            System.out.println("2. Xóa độc giả");
            System.out.println("3. Sửa thông tin độc giả");
            System.out.println("4. Tìm kiếm độc giả (theo ID)");
            System.out.println("5. Hiển thị tất cả độc giả");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: readerService.addReader(); break; 
                    case 2: readerService.deleteReader(); break;
                    case 3: readerService.updateReader(); break;
                    case 4: readerService.findAndShowReaderById(); break;
                    case 5: readerService.showAllReaders(); break;
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
