package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Borrow;
import model.PenaltyRule;
import model.TicketFine;

public class TicketFineService implements ITicketFineService {
    private TicketFine[] tickets = new TicketFine[0];
    private int count = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final String DATA_FILE = "data/TicketFine.csv";
    private BorrowService borrowService;
    private ReturnService returnService;
    private PenaltyRuleService penaltyRuleService;
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TicketFineService() {
        loadTicketsFromFile();
    }

    public void setServices(BorrowService bs, ReturnService rs, PenaltyRuleService prs) {
        this.borrowService = bs;
        this.returnService = rs;
        this.penaltyRuleService = prs;
    }

    private double calculateFine(String borrowId, String reasonId) {
        if (borrowService == null || penaltyRuleService == null) {
            System.err.println("Lỗi: Các Service Borrow/PenaltyRule chưa được thiết lập!");
            return 0.0;
        }
        PenaltyRule rule = penaltyRuleService.searchRuleById(reasonId);
        Borrow borrow = borrowService.findBorrowById(borrowId);
        if (rule == null || borrow == null) {
            System.err.println("Lỗi: Không tìm thấy luật phạt hay phiếu phạt liên quan."); 
            return 0.0;
        }
        if (reasonId.equalsIgnoreCase("OVERDUE") && borrow.getActuallyReturnDate() != null) {
            long daysOverdue = ChronoUnit.DAYS.between(borrow.getReturnDate(), borrow.getActuallyReturnDate());
            if (daysOverdue > 0) {
                return daysOverdue * rule.getPenalty();
            }
        }
        return rule.getPenalty();
    }

    public void addTicket(TicketFine ticket) {
        tickets = Arrays.copyOf(tickets, count + 1);
        tickets[count++] = ticket;
        System.out.println("✅ Thêm phiếu phạt thành công, đang lưu...");
        saveTicketsToFile();
    }

    @Override
    public void addTicket() {
        System.out.println("\n--- Thêm Phiếu Phạt Mới ---");
        TicketFine newTicket = new TicketFine();
        newTicket.input();
        double calculatedFine = calculateFine(newTicket.getBorrowId(), newTicket.getReasonId());
        newTicket.setTotalAmount(calculatedFine);
        System.out.println("💰 Số tiền phạt được tính: " + calculatedFine + " VNĐ");
        addTicket(newTicket);
    }

    // --- Các hàm CRUD (Giữ nguyên logic) ---
    @Override
    public TicketFine getTicketById(String ticketId) {
        for (int i = 0; i < count; i++) {
            if (tickets[i].getTicketId().equalsIgnoreCase(ticketId)) {
                return tickets[i];
            }
        }
        return null;
    }
    
    // ... (findAndShowTicketById, updateTicketById, updateTicket, deleteTicketById, deleteTicket giữ nguyên) ...

    @Override
    public void findAndShowTicketById() {
        System.out.print("Nhập ID phiếu phạt cần tìm: ");
        String ticketId = scanner.nextLine().trim();
        TicketFine ticket = getTicketById(ticketId);
        if (ticket != null) {
            System.out.println("✅ Thông tin phiếu phạt tìm thấy:");
            ticket.output();
        } else {
            System.out.println("❌ Không tìm thấy phiếu phạt với ID: " + ticketId);
        }
    }

    public boolean updateTicketById(String ticketId) {
        TicketFine ticketToUpdate = getTicketById(ticketId);
        if (ticketToUpdate != null) {
            System.out.println(" Tìm thấy phiếu phạt. Vui lòng nhập thông tin mới:");
            ticketToUpdate.input();
            ticketToUpdate.setTicketId(ticketId);
            double calculatedFine = calculateFine(ticketToUpdate.getBorrowId(), ticketToUpdate.getReasonId());
            ticketToUpdate.setTotalAmount(calculatedFine);
            System.out.println("✅ Cập nhật thông tin thành công! Số tiền phạt mới: " + calculatedFine + ". Đang lưu...");
            saveTicketsToFile();
            return true;
        }
        return false;
    }

    @Override
    public void updateTicket() {
        System.out.print("Nhập ID phiếu phạt cần sửa: ");
        String ticketId = scanner.nextLine().trim();
        if (!updateTicketById(ticketId)) {
            System.out.println("❌ Không tìm thấy phiếu phạt với ID: " + ticketId + " để cập nhật.");
        }
    }

    public boolean deleteTicketById(String ticketId) {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (tickets[i].getTicketId().equalsIgnoreCase(ticketId)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            for (int j = indexToRemove; j < count - 1; j++) {
                tickets[j] = tickets[j + 1];
            }
            count--;
            tickets = Arrays.copyOf(tickets, count);
            return true;
        }
        return false;
    }

    @Override
    public void deleteTicket() {
        if (count == 0) {
            System.out.println("❌ Danh sách trống, không có phiếu phạt để xóa!");
            return;
        }
        System.out.print("\nNhập ID phiếu phạt cần xóa: ");
        String ticketId = scanner.nextLine().trim();
        if (deleteTicketById(ticketId)) {
            System.out.println("✅ Đã xóa phiếu phạt có ID: " + ticketId + ". Đang lưu...");
            saveTicketsToFile();
        } else {
            System.out.println("❌ Không tìm thấy phiếu phạt với ID: " + ticketId);
        }
    }

    @Override
    public void showAllTickets() {
        if (count == 0) {
            System.out.println("❌ Chưa có phiếu phạt nào trong danh sách!");
            return;
        }
        System.out.println("\n===== DANH SÁCH PHIẾU PHẠT (" + count + " phiếu) =====");
        for (int i = 0; i < count; i++) {
            tickets[i].output();
            System.out.println("-------------------------------------------------");
        }
    }

    @Override
    public void saveTicketsToFile() {
        try (FileWriter fw = new FileWriter(DATA_FILE, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i = 0; i < count; i++) {
                bw.write(tickets[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + count + " phiếu phạt vào file " + DATA_FILE + " thành công.");
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void loadTicketsFromFile() {
        BufferedReader br = null;
        TicketFine[] tempTickets = new TicketFine[0];
        int tempCount = 0;
        try {
            br = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                TicketFine ticket = TicketFine.readFromCSV(line);
                if (ticket != null) {
                    tempTickets = Arrays.copyOf(tempTickets, tempCount + 1);
                    tempTickets[tempCount++] = ticket;
                }
            }
            this.tickets = tempTickets;
            this.count = tempCount;
            if (tempCount > 0) {
                System.out.println("✅ Tải thành công " + tempCount + " phiếu phạt từ file " + DATA_FILE + ".");
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File dữ liệu " + DATA_FILE + " không tồn tại. Bắt đầu với danh sách trống.");
            this.tickets = new TicketFine[0];
            this.count = 0;
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ignored) {}
        }
    }

    // =================================================================
    // CÁC HÀM THỐNG KÊ PHIẾU PHẠT MỚI
    // =================================================================

    /**
     * 1. Thống kê tổng tiền phạt từ ngày A đến B.
     * @param startDate Ngày bắt đầu
     * @param endDate Ngày kết thúc
     * @return Map chứa "totalFine" và "count"
     */
    public Map<String, Double> statisticTotalFineByDateRange(LocalDate startDate, LocalDate endDate) {
        Map<String, Double> result = new HashMap<>();
        double totalFine = 0.0;
        int foundCount = 0;
        
        for (int i = 0; i < count; i++) {
            LocalDate ticketDate = tickets[i].getTicketDate();
            if (ticketDate != null && !ticketDate.isBefore(startDate) && !ticketDate.isAfter(endDate)) {
                totalFine += tickets[i].getTotalAmount();
                foundCount++;
            }
        }
        
        result.put("totalFine", totalFine);
        result.put("count", (double) foundCount);
        return result;
    }

    /**
     * 1. Thống kê tổng tiền phạt từ ngày A đến B (không tham số - menu).
     */
    @Override
    public void statisticTotalFineByDateRange() {
        if (count == 0) {
            System.out.println("❌ Chưa có phiếu phạt nào trong danh sách để thống kê!");
            return;
        }
        System.out.println("\n--- THỐNG KÊ TỔNG TIỀN PHẠT THEO KHOẢNG NGÀY ---");
        System.out.print("Nhập ngày bắt đầu (yyyy-MM-dd): ");
        String startStr = scanner.nextLine().trim();
        System.out.print("Nhập ngày kết thúc (yyyy-MM-dd): ");
        String endStr = scanner.nextLine().trim();
        
        LocalDate startDate, endDate;
        try {
            startDate = LocalDate.parse(startStr);
            endDate = LocalDate.parse(endStr);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Định dạng ngày không hợp lệ. Vui lòng nhập theo yyyy-MM-dd.");
            return;
        }

        Map<String, Double> result = statisticTotalFineByDateRange(startDate, endDate);
        double totalFine = result.get("totalFine");
        int foundCount = result.get("count").intValue();

        System.out.println("\n===== DANH SÁCH PHIẾU PHẠT TỪ " + startDate.format(DISPLAY_FORMATTER) + " ĐẾN " + endDate.format(DISPLAY_FORMATTER) + " =====");
        printTicketTableHeader();

        for (int i = 0; i < count; i++) {
            LocalDate ticketDate = tickets[i].getTicketDate();
            if (ticketDate != null && !ticketDate.isBefore(startDate) && !ticketDate.isAfter(endDate)) {
                printTicketRow(tickets[i]);
            }
        }
        
        if (foundCount == 0) {
            System.out.println("| Khong co phieu phat nao trong khoang thoi gian nay! |");
        }
        printTicketTableFooter(totalFine);
        
        if (foundCount > 0) {
            System.out.printf("💰 TỔNG TIỀN PHẠT: %,.0f VNĐ\n", totalFine);
        }
        System.out.println("====================================================");
    }
    
    /**
     * 2. Thống kê tổng tiền phạt theo quý (có tham số - trả về giá trị).
     * @param year Năm cần thống kê
     * @return Mảng double[4] chứa tổng tiền phạt theo quý
     */
    public double[] getFineByQuarter(int year) {
        double[] quarterTotals = new double[4];
        
        for (int i = 0; i < count; i++) {
            LocalDate date = tickets[i].getTicketDate();
            if (date != null && date.getYear() == year) {
                int month = date.getMonthValue();
                int quarter = (month - 1) / 3; // Q1=0, Q2=1, Q3=2, Q4=3
                quarterTotals[quarter] += tickets[i].getTotalAmount();
            }
        }
        
        return quarterTotals;
    }
    
    /**
     * 2. Thống kê tổng tiền phạt theo quý (không tham số - menu).
     */
    @Override
    public void statisticFineByQuarter(int year) {
        if (count == 0) {
            System.out.println("❌ Chưa có phiếu phạt nào trong danh sách để thống kê!");
            return;
        }
        
        double[] quarterTotals = getFineByQuarter(year);
        boolean foundData = false;
        
        for (double total : quarterTotals) {
            if (total > 0) {
                foundData = true;
                break;
            }
        }

        if (!foundData) {
            System.out.println("❌ Không tìm thấy dữ liệu phạt trong năm " + year + ".");
            return;
        }
        
        System.out.println("\n===== TỔNG TIỀN PHẠT THEO QUÝ NĂM " + year + " =====");
        System.out.println("+-----------+-----------+-----------+-----------+");
        System.out.printf("| %-9s | %-9s | %-9s | %-9s |\n", "Quý 1", "Quý 2", "Quý 3", "Quý 4");
        System.out.println("+-----------+-----------+-----------+-----------+");
        
        // Hiển thị tiền phạt
        double grandTotal = 0;
        System.out.printf("| %,.0f VNĐ | %,.0f VNĐ | %,.0f VNĐ | %,.0f VNĐ |\n",
                          quarterTotals[0], quarterTotals[1], quarterTotals[2], quarterTotals[3]);
        
        for (double total : quarterTotals) {
            grandTotal += total;
        }
        
        System.out.println("+-----------+-----------+-----------+-----------+");
        System.out.printf("💰 TỔNG THU CẢ NĂM %d: %,.0f VNĐ\n", year, grandTotal);
        System.out.println("==================================================");
    }

    /**
     * 3. Thống kê tổng tiền phạt theo nhóm (có tham số - trả về giá trị).
     * @param groupType "BORROW" hoặc "REASON"
     * @param year Năm cần thống kê
     * @return Map chứa key (ID) và value (tổng tiền)
     */
    public Map<String, Double> getGroupedFine(String groupType, int year) {
        java.util.function.Function<TicketFine, String> keyExtractor;

        if (groupType.equalsIgnoreCase("BORROW")) {
            keyExtractor = TicketFine::getBorrowId;
        } else if (groupType.equalsIgnoreCase("REASON")) {
            keyExtractor = TicketFine::getReasonId;
        } else {
            return new HashMap<>(); // Trả về Map rỗng nếu không hợp lệ
        }
        
        Map<String, Double> totalFineByGroup = new HashMap<>();
        
        for (TicketFine ticket : tickets) {
            if (ticket.getTicketDate() != null && ticket.getTicketDate().getYear() == year) {
                String key = keyExtractor.apply(ticket);
                double amount = ticket.getTotalAmount();
                totalFineByGroup.put(key, totalFineByGroup.getOrDefault(key, 0.0) + amount);
            }
        }
        
        return totalFineByGroup;
    }
    
    /**
     * 3. Thống kê tổng tiền phạt theo nhóm (không tham số - menu).
     */
    @Override
    public void statisticGroupedFine(String groupType, int year) {
        if (count == 0) {
            System.out.println("❌ Chưa có phiếu phạt nào trong danh sách để thống kê!");
            return;
        }
        
        String fieldName;
        if (groupType.equalsIgnoreCase("BORROW")) {
            fieldName = "Mã Phiếu Mượn";
        } else if (groupType.equalsIgnoreCase("REASON")) {
            fieldName = "Mã Lý Do Phạt";
        } else {
            System.out.println("❌ Loại nhóm không hợp lệ. Chỉ chấp nhận 'BORROW' hoặc 'REASON'.");
            return;
        }
        
        Map<String, Double> totalFineByGroup = getGroupedFine(groupType, year);
        
        if (totalFineByGroup.isEmpty()) {
            System.out.println("❌ Không tìm thấy dữ liệu phạt theo " + fieldName + " trong năm " + year + ".");
            return;
        }

        System.out.println("\n===== BÁO CÁO TIỀN PHẠT THEO " + fieldName.toUpperCase() + " NĂM " + year + " =====");
        System.out.printf("| %-15s | %-20s |\n", fieldName, "Tổng Tiền Phạt (VNĐ)");
        System.out.println("+-----------------+----------------------+");
        
        double grandTotal = 0;
        for (Map.Entry<String, Double> entry : totalFineByGroup.entrySet()) {
            System.out.printf("| %-15s | %,.0f VNĐ\n", entry.getKey(), entry.getValue());
            grandTotal += entry.getValue();
        }
        System.out.println("+-----------------+----------------------+");
        System.out.printf("💰 TỔNG CỘNG: %,.0f VNĐ\n", grandTotal);
        System.out.println("=========================================");
    }
    
    // --- HAM IN BANG TEXT (Đã đồng bộ) ---
    private void printTicketTableHeader() {
        System.out.println("+------------+------------+------------+-------------+");
        System.out.printf("| %-10s | %-10s | %-10s | %-11s |\n", "Mã Phiếu", "Mã Mượn", "Ngày Lập", "Tiền Phạt");
        System.out.println("+------------+------------+------------+-------------+");
    }

    private void printTicketRow(TicketFine t) {
        String dateStr = t.getTicketDate() != null ? t.getTicketDate().format(DISPLAY_FORMATTER) : "N/A";
        System.out.printf("| %-10s | %-10s | %-10s | %-11.0f |\n",
                t.getTicketId(),
                t.getBorrowId(),
                dateStr,
                t.getTotalAmount());
    }

    private void printTicketTableFooter(double total) {
        System.out.println("+------------+------------+------------+-------------+");
        System.out.printf("| %-35s | %-11.0f |\n", "Tổng cộng:", total);
        System.out.println("+-------------------------------------+-------------+");
    }
}