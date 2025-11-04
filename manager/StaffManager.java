package manager;

import java.util.Scanner;
import service.StaffService;

/**
 * Quản lý menu và các thao tác liên quan đến Nhân Viên
 */
public class StaffManager {
    private final StaffService staffService;
    private final Scanner scanner;

    public StaffManager(StaffService staffService) {
        this.staffService = staffService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Nhân Viên ---");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Xóa nhân viên");
            System.out.println("3. Sửa thông tin nhân viên");
            System.out.println("4. Tìm kiếm nhân viên (theo ID)");
            System.out.println("5. Hiển thị tất cả nhân viên");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: staffService.addStaff(new model.Staff()); break; 
                    case 2: staffService.deleteStaff(); break;
                    case 3: staffService.updateStaff(); break;
                    case 4: staffService.findAndShowStaffById(); break;
                    case 5: staffService.showAllStaff(); break;
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
