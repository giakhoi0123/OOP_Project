package manager;

import java.util.Scanner;
import service.PenaltyRuleService;

/**
 * Quản lý menu và các thao tác liên quan đến Quy tắc Phạt
 */
public class PenaltyRuleManager {
    private final PenaltyRuleService penaltyRuleService;
    private final Scanner scanner;

    public PenaltyRuleManager(PenaltyRuleService penaltyRuleService) {
        this.penaltyRuleService = penaltyRuleService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Quy tắc Phạt ---");
            System.out.println("1. Thêm quy tắc");
            System.out.println("2. Xóa quy tắc");
            System.out.println("3. Sửa quy tắc");
            System.out.println("4. Tìm kiếm quy tắc");
            System.out.println("5. Hiển thị tất cả quy tắc");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: penaltyRuleService.addRule(); break;
                    case 2: penaltyRuleService.removeRule(); break;
                    case 3: penaltyRuleService.editRule(); break;
                    case 4: penaltyRuleService.searchPenaltyRule(); break;
                    case 5: penaltyRuleService.output(); break;
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
