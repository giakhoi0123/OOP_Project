package manager;

import java.util.Scanner;
import service.TicketFineService;

/**
 * Quản lý menu và các thao tác liên quan đến Phiếu Phạt
 */
public class TicketFineManager {
    private final TicketFineService ticketFineService;
    private final Scanner scanner;

    public TicketFineManager(TicketFineService ticketFineService) {
        this.ticketFineService = ticketFineService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Phiếu Phạt ---");
            System.out.println("1. Thêm phiếu phạt");
            System.out.println("2. Xóa phiếu phạt");
            System.out.println("3. Sửa phiếu phạt");
            System.out.println("4. Tìm kiếm phiếu phạt (theo ID)");
            System.out.println("5. Hiển thị tất cả phiếu phạt");
            System.out.println("------------------------------------");
            System.out.println("6. Thống kê Tổng tiền phạt theo Khoảng ngày");
            System.out.println("7. Thống kê Tổng tiền phạt theo Quý");
            System.out.println("8. Thống kê Tổng tiền phạt theo Nhóm (Mượn/Lý do)");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: ticketFineService.addTicket(); break; 
                    case 2: ticketFineService.deleteTicket(); break;
                    case 3: ticketFineService.updateTicket(); break;
                    case 4: ticketFineService.findAndShowTicketById(); break;
                    case 5: ticketFineService.showAllTickets(); break;
                    case 6: ticketFineService.statisticTotalFineByDateRange(); break;
                    case 7: 
                        System.out.print("Nhập năm cần thống kê (yyyy): ");
                        int year = Integer.parseInt(scanner.nextLine());
                        ticketFineService.statisticFineByQuarter(year); break;
                    case 8: 
                        System.out.print("Nhóm theo (Borrow/Reason): ");
                        String groupType = scanner.nextLine();
                        System.out.print("Năm: ");
                        int groupYear = Integer.parseInt(scanner.nextLine());
                        ticketFineService.statisticGroupedFine(groupType, groupYear); break;
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
