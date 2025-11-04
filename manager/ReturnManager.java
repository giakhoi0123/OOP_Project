package manager;

import java.util.Scanner;
import service.ReturnService;

/**
 * Quản lý menu và các thao tác liên quan đến Trả Sách
 */
public class ReturnManager {
    private final ReturnService returnService;
    private final Scanner scanner;

    public ReturnManager(ReturnService returnService) {
        this.returnService = returnService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Trả Sách ---");
            System.out.println("1. Thêm phiếu trả (Return)");
            System.out.println("2. Xóa phiếu trả");
            System.out.println("3. Sửa phiếu trả");
            System.out.println("4. Tìm kiếm phiếu trả (theo ID)");
            System.out.println("5. Hiển thị tất cả phiếu trả");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: returnService.input(); break; 
                    case 2: returnService.removeReturn(); break;
                    case 3: returnService.editReturn(); break;
                    case 4: returnService.searchReturnById(); break;
                    case 5: returnService.output(); break;
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
