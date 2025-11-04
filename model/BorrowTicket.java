package model;

import java.io.Serializable;
import java.util.Scanner;

public class BorrowTicket implements Serializable {
    private String borrowTicketId;
    private String borrowId;
    private String bookId;
    private int quantity;

    public BorrowTicket() {}
    
    public BorrowTicket(String borrowTicketId, String borrowId, String bookId, int quantity) {
        this.borrowTicketId = borrowTicketId;
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public void nhap(Scanner scanner) {
        // Hàm này thường được gọi từ BorrowService/BorrowTicketService
        System.out.print("  Nhập mã chi tiết mượn (Ticket ID): ");
        this.borrowTicketId = scanner.nextLine();
        // borrowId được set tự động từ phiếu mượn cha
        System.out.print("  Nhập mã sách (Book ID) mượn: ");
        this.bookId = scanner.nextLine();
        System.out.print("  Nhập số lượng: ");
        try {
            this.quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("  ❌ Số lượng không hợp lệ. Đặt mặc định 1.");
            this.quantity = 1;
        }
    }

    // --- Getters & Setters ---
    public String getBorrowTicketId() { return borrowTicketId; }
    public void setBorrowTicketId(String borrowTicketId) { this.borrowTicketId = borrowTicketId; }

    public String getBorrowId() { return borrowId; }
    public void setBorrowId(String borrowId) { this.borrowId = borrowId; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // --- CSV I/O ---
    public String toCSV() {
        return borrowTicketId + "," + borrowId + "," + bookId + "," + quantity;
    }

    /**
     * Phương thức tĩnh để tạo đối tượng BorrowTicket từ chuỗi CSV.
     */
    public static BorrowTicket readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 4) {
             System.err.println("❌ Lỗi CSV Chi tiết mượn: Không đủ 4 trường. Dòng: " + csvLine);
             return null;
        }
        try {
            return new BorrowTicket(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                Integer.parseInt(parts[3].trim())
            );
        } catch (NumberFormatException e) {
            System.err.println("❌ Lỗi CSV Chi tiết mượn: Lỗi chuyển đổi số. Dòng: " + csvLine);
            return null;
        }
    }

    @Override
    public String toString() {
        // Định dạng đẹp, Tiếng Việt
        return String.format(
            "| Mã Ticket: %s | Sách ID: %s | Số lượng: %d",
            borrowTicketId, bookId, quantity
        );
    }
}