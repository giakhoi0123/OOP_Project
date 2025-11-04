package manager;

import java.util.Scanner;
import service.*;

/**
 * Lớp điều khiển trung tâm (Controller) quản lý toàn bộ hệ thống thư viện.
 * Chỉ chứa menu chính và điều hướng đến các Manager con.
 */
public class LibraryManager {
    // Khai báo tất cả các Manager
    private final AuthorManager authorManager;
    private final BookManager bookManager;
    private final ReaderManager readerManager;
    private final BorrowManager borrowManager;
    private final ReturnManager returnManager;
    private final PublisherManager publisherManager;
    private final PenaltyRuleManager penaltyRuleManager;
    private final OrderManager orderManager;
    private final CategoryManager categoryManager;
    private final StaffManager staffManager;
    private final SupplierManager supplierManager;
    private final TicketFineManager ticketFineManager;
    
    private final Scanner scanner;

    public LibraryManager() {
        this.scanner = new Scanner(System.in);
        
        // Khởi tạo tất cả Service (Sẽ tự động tải dữ liệu từ file)
        System.out.println("\n--- Đang tải dữ liệu hệ thống... ---");
        AuthorService authorService = new AuthorService();
        BookService bookService = new BookService();
        CategoryService categoryService = new CategoryService();
        PublisherService publisherService = new PublisherService();
        StaffService staffService = new StaffService();
        ReaderService readerService = new ReaderService();
        PenaltyRuleService penaltyRuleService = new PenaltyRuleService();
        OrderService orderService = new OrderService();
        SupplierService supplierService = new SupplierService();
        ReturnService returnService = new ReturnService(); 
        BorrowService borrowService = new BorrowService(); 
        TicketFineService ticketFineService = new TicketFineService();
        
        System.out.println("--- Hoàn tất tải dữ liệu. ---");
        
        // THIẾT LẬP CÁC DEPENDENCY ĐỂ CẬP NHẬT SỐ LƯỢNG SÁCH
        System.out.println("--- Đang thiết lập liên kết giữa các Service... ---");
        
        // 1. OrderService cần BookService để tăng số lượng khi nhập hàng
        orderService.setBookService(bookService);
        
        // 2. BorrowService cần BookService để giảm số lượng khi mượn
        borrowService.setBookService(bookService);
        
        // 3. ReturnService cần BookService và BorrowTicketService để tăng số lượng khi trả
        returnService.setBookService(bookService);
        returnService.setBorrowTicketService(borrowService.getBorrowTicketService());
        
        System.out.println("--- Hoàn tất thiết lập liên kết. ---");
        
        // Khởi tạo tất cả các Manager với Service tương ứng
        System.out.println("--- Đang khởi tạo các Manager... ---");
        this.authorManager = new AuthorManager(authorService);
        this.bookManager = new BookManager(bookService);
        this.readerManager = new ReaderManager(readerService);
        this.borrowManager = new BorrowManager(borrowService);
        this.returnManager = new ReturnManager(returnService);
        this.publisherManager = new PublisherManager(publisherService);
        this.penaltyRuleManager = new PenaltyRuleManager(penaltyRuleService);
        this.orderManager = new OrderManager(orderService);
        this.categoryManager = new CategoryManager(categoryService);
        this.staffManager = new StaffManager(staffService);
        this.supplierManager = new SupplierManager(supplierService);
        this.ticketFineManager = new TicketFineManager(ticketFineService);
        System.out.println("--- Hoàn tất khởi tạo. ---");
    }

    /**
     * Hiển thị menu chính của hệ thống.
     */
    public void showMainMenu() {
        int choice = -1;
        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Quản lý Tác giả (Authors)");
            System.out.println("2. Quản lý Sách (Books)");
            System.out.println("3. Quản lý Độc giả (Readers)");
            System.out.println("4. Quản lý Mượn Sách (Borrowing)");
            System.out.println("5. Quản lý Trả Sách (Returning)");
            System.out.println("6. Quản lý Nhà Xuất Bản (Publishers)");
            System.out.println("7. Quản lý Quy tắc Phạt (Penalty Rules)");
            System.out.println("8. Quản lý Đơn Hàng (Orders)");
            System.out.println("9. Quản lý Thể loại (Categories)");
            System.out.println("10. Quản lý Nhân Viên (Staff)");
            System.out.println("11. Quản lý Nhà Cung Cấp (Suppliers) ");
            System.out.println("12. Quản lý Phiếu Phạt (Ticket Fines)");
            System.out.println("0. Thoát");
            System.out.print("👉 Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleMainMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng nhập một số.");
            }
        } while (choice != 0);
        System.out.println("Đã thoát chương trình. Tạm biệt!");
    }

    /**
     * Xử lý lựa chọn từ menu chính.
     */
    private void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                authorManager.showMenu();
                break;
            case 2:
                bookManager.showMenu();
                break;
            case 3:
                readerManager.showMenu();
                break;
            case 4:
                borrowManager.showMenu();
                break;
            case 5:
                returnManager.showMenu();
                break;
            case 6:
                publisherManager.showMenu();
                break;
            case 7:
                penaltyRuleManager.showMenu();
                break;
            case 8:
                orderManager.showMenu();
                break;
            case 9:
                categoryManager.showMenu();
                break;
            case 10:
                staffManager.showMenu();
                break;
            case 11:
                supplierManager.showMenu();
                break;
            case 12:
                ticketFineManager.showMenu();
                break;
            case 0:
                // Thoát đã được xử lý trong vòng lặp do-while
                break;
            default:
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn lại.");
        }
    }
}