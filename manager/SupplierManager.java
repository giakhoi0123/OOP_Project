package manager;

import java.util.Scanner;
import service.SupplierService;

/**
 * Quản lý menu và các thao tác liên quan đến Nhà Cung Cấp
 */
public class SupplierManager {
    private final SupplierService supplierService;
    private final Scanner scanner;

    public SupplierManager(SupplierService supplierService) {
        this.supplierService = supplierService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Nhà Cung Cấp ---");
            System.out.println("1. Thêm NCC");
            System.out.println("2. Xóa NCC");
            System.out.println("3. Sửa thông tin NCC");
            System.out.println("4. Tìm kiếm NCC (theo ID)");
            System.out.println("5. Hiển thị tất cả NCC");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: supplierService.addSupplier(new model.Supplier()); break;
                    case 2: supplierService.deleteSupplier(); break;
                    case 3: supplierService.updateSupplier(); break;
                    case 4: supplierService.findAndShowSupplierById(); break;
                    case 5: supplierService.showAllSuppliers(); break;
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
