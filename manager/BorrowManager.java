package manager;

import java.util.Scanner;
import service.BorrowService;

/**
 * Quản lý menu và các thao tác liên quan đến Mượn Sách
 */
public class BorrowManager {
    private final BorrowService borrowService;
    private final Scanner scanner;

    public BorrowManager(BorrowService borrowService) {
        this.borrowService = borrowService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Mượn Sách ---");
            System.out.println("1. Lập phiếu mượn mới (Thêm Borrow & Details)");
            System.out.println("2. Xóa phiếu mượn (và chi tiết)");
            System.out.println("3. Cập nhật ngày trả thực tế");
            System.out.println("4. Tìm kiếm phiếu mượn (theo ID)");
            System.out.println("5. Hiển thị các sách đang mượn");
            System.out.println("6. Hiển thị các sách quá hạn (Hôm nay)");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: borrowService.addBorrow(); break;
                    case 2: borrowService.deleteBorrow(); break;
                    case 3: borrowService.updateBorrow(); break; 
                    case 4: borrowService.findBorrowById(); break;
                    case 5: System.out.println("Chức năng đang được phát triển."); break; 
                    case 6: System.out.println("Chức năng đang được phát triển."); break; 
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
