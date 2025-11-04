package manager;

import java.util.Scanner;
import service.PublisherService;

/**
 * Quản lý menu và các thao tác liên quan đến Nhà Xuất Bản
 */
public class PublisherManager {
    private final PublisherService publisherService;
    private final Scanner scanner;

    public PublisherManager(PublisherService publisherService) {
        this.publisherService = publisherService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Nhà Xuất Bản ---");
            System.out.println("1. Thêm NXB");
            System.out.println("2. Xóa NXB");
            System.out.println("3. Sửa thông tin NXB");
            System.out.println("4. Tìm kiếm NXB (theo ID)");
            System.out.println("5. Hiển thị tất cả NXB");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: publisherService.addPublisher(new model.Publisher()); break; 
                    case 2: publisherService.removePublisher(); break;
                    case 3: publisherService.updatePublisher(); break;
                    case 4: publisherService.findAndShowPublisherById(); break;
                    case 5: publisherService.showAllPublishers(); break;
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
