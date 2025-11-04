package manager;

import java.util.Scanner;
import service.CategoryService;

/**
 * Quản lý menu và các thao tác liên quan đến Thể loại
 */
public class CategoryManager {
    private final CategoryService categoryService;
    private final Scanner scanner;

    public CategoryManager(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Thể loại ---");
            System.out.println("1. Thêm thể loại");
            System.out.println("2. Xóa thể loại");
            System.out.println("3. Sửa thông tin thể loại");
            System.out.println("4. Tìm kiếm thể loại (theo ID)");
            System.out.println("5. Hiển thị tất cả thể loại");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: categoryService.addCategory(new model.Category()); break; 
                    case 2: categoryService.removeCategory(); break;
                    case 3: categoryService.updateCategory(); break;
                    case 4: categoryService.findAndShowCategoryById(); break;
                    case 5: categoryService.showAllCategories(); break;
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
