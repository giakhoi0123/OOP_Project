package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TicketFine {
    private String ticketId;
    private String borrowId;
    private String reasonId;
    private double totalAmount;
    private LocalDate ticketDate; // ngày lập phiếu phạt
    
    // Định dạng ngày tháng chuẩn cho CSV và hiển thị
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TicketFine() {
        totalAmount = 0;
        ticketDate = null;
    }

    public TicketFine(String ticketId, String borrowId, String reasonId, double totalAmount, LocalDate ticketDate) {
        this.ticketId = ticketId;
        this.borrowId = borrowId;
        this.reasonId = reasonId;
        this.totalAmount = totalAmount;
        this.ticketDate = ticketDate;
    }

    public TicketFine(TicketFine tf) {
        this.ticketId = tf.ticketId;
        this.borrowId = tf.borrowId;
        this.reasonId = tf.reasonId;
        this.totalAmount = tf.totalAmount;
        this.ticketDate = tf.ticketDate;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu phạt (Ticket ID): ");
        ticketId = sc.nextLine();
        System.out.print("Nhập mã phiếu mượn (Borrow ID): ");
        borrowId = sc.nextLine();
        System.out.print("Nhập mã lý do phạt (Reason ID): ");
        reasonId = sc.nextLine();
        
        // totalAmount thường được tính toán, không nhập.

        // Nhập ngày lập phiếu
        System.out.print("Nhập ngày lập phiếu (yyyy-MM-dd) (hoặc để trống): ");
        String dateStr = sc.nextLine().trim();
        if (!dateStr.isEmpty()) {
            try {
                // Sử dụng LocalDate.parse() mặc định là yyyy-MM-dd
                ticketDate = LocalDate.parse(dateStr); 
            } catch (DateTimeParseException e) {
                System.out.println("❌ Định dạng ngày không hợp lệ. Phiếu phạt không có ngày lập.");
                ticketDate = null;
            }
        } else {
            ticketDate = null;
        }
    }

    public void output(){
        System.out.println(this.toString());
    }

    // Getters
    public String getTicketId() { return ticketId; }
    public String getBorrowId() { return borrowId; }
    public String getReasonId() { return reasonId; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDate getTicketDate() { return ticketDate; }
    

    // Setters
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public void setBorrowId(String borrowId) { this.borrowId = borrowId; }
    public void setReasonId(String reasonId) { this.reasonId = reasonId; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setTicketDate(LocalDate ticketDate) { this.ticketDate = ticketDate; }

    @Override
    public String toString() {
        // Định dạng đẹp (Tiếng Việt)
        String dateStr = (ticketDate != null) ? ticketDate.format(DISPLAY_FORMATTER) : "N/A";
        
        return String.format(
            "| Mã Phiếu Phạt: %s | Mượn ID: %s | Lý do: %s\n" +
            "| Tổng tiền phạt: %.0f VNĐ | Ngày lập: %s",
            ticketId, borrowId, reasonId, totalAmount, dateStr
        );
    }
    
    /**
     * Chuyển đổi đối tượng sang định dạng CSV (yyyy-MM-dd).
     */
    public String toCSV() {
        // Ghi date theo định dạng yyyy-MM-dd (mặc định của toString)
        return ticketId + "," + borrowId + "," + reasonId + "," + totalAmount + "," + (ticketDate != null ? ticketDate.toString() : "");
    }

    /**
     * Phương thức tĩnh để tạo đối tượng TicketFine từ chuỗi CSV.
     */
    public static TicketFine readFromCSV(String csvLine) {
        // Cần ít nhất 4 trường (ticketId, borrowId, reasonId, totalAmount)
        // Trường thứ 5 (ticketDate) có thể rỗng
        String[] parts = csvLine.split(",", -1); // Dùng -1 để đảm bảo các trường rỗng ở cuối được tính
        
        if (parts.length < 4) {
            System.err.println("❌ Lỗi định dạng CSV cho TicketFine: Dòng không đủ 4 trường cơ bản. Dòng: " + csvLine);
            return null;
        }
        
        try {
            String tid = parts[0].trim();
            String bid = parts[1].trim();
            String rid = parts[2].trim();
            double amt = 0.0;
            // Kiểm tra và parse totalAmount
            if (!parts[3].trim().isEmpty()) { 
                amt = Double.parseDouble(parts[3].trim());
            }
            
            LocalDate tdate = null;
            // Kiểm tra và parse ticketDate (Chỉ khi có ít nhất 5 trường)
            if (parts.length >= 5 && !parts[4].trim().isEmpty()) {
                try {
                    tdate = LocalDate.parse(parts[4].trim());
                } catch (DateTimeParseException e) {
                    System.err.println("⚠️ Cảnh báo: Ngày lập phiếu không hợp lệ trong CSV: " + parts[4].trim());
                    tdate = null;
                }
            }
            
            return new TicketFine(tid, bid, rid, amt, tdate);
        } catch (NumberFormatException e) {
            System.err.println("❌ Lỗi phân tích số tiền phạt trong CSV: " + csvLine);
            return null;
        }
    }

    public void ghiFile() {
        // SỬA ĐƯỜNG DẪN FILE VỀ data/
        String fileName = "data/TicketFine.csv"; 
        try (FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCSV());
            bw.newLine();           
        } 
        catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fileName + ": " + e.getMessage());
        }
    }
}