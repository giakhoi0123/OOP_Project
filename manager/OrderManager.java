package manager;

import java.util.Scanner;
import service.OrderService;

/**
 * Quản lý menu và các thao tác liên quan đến Đơn Hàng
 */
public class OrderManager {
    private final OrderService orderService;
    private final Scanner scanner;

    public OrderManager(OrderService orderService) {
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Đơn Hàng ---");
            System.out.println("1. Thêm đơn hàng (và chi tiết)");
            System.out.println("2. Xóa đơn hàng (và chi tiết)");
            System.out.println("3. Cập nhật thông tin chung đơn hàng");
            System.out.println("4. Sửa chi tiết đơn hàng");
            System.out.println("5. Xóa chi tiết đơn hàng");
            System.out.println("------------------------------------");
            System.out.println("6. Thống kê Chi phí theo Khoảng ngày");
            System.out.println("7. Thống kê Chi phí theo Quý");
            System.out.println("8. Thống kê Chi phí theo Nhóm (NCC/NV)");
            System.out.println("9. Hiển thị chi tiết của một đơn hàng");
            System.out.println("10. Hiển thị tất cả đơn hàng");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: orderService.addOrderAndDetails(); break;
                    case 2: orderService.deleteOrder(); break;
                    case 3: orderService.updateOrder(); break;
                    case 4: orderService.updateOrderDetail(); break;
                    case 5: orderService.deleteOrderDetail(); break;
                    case 6: orderService.statisticTotalRevenueByDateRange(); break;
                    case 7: 
                        System.out.print("Nhập quý (1-4): ");
                        int quarter = Integer.parseInt(scanner.nextLine());
                        orderService.statisticQuarterlyFinancials(quarter); 
                        break;
                    case 8: 
                        System.out.print("Nhập loại nhóm (NCC/NV): ");
                        String groupType = scanner.nextLine();
                        orderService.statisticGroupedFinancials(groupType); 
                        break;
                    case 9: orderService.displayDetailsForSpecificOrder(); break;
                    case 10: orderService.displayAllOrders(); break;
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
