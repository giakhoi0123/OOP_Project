package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Borrow implements Serializable {
    private String borrowId;
    private LocalDate brDate;
    private LocalDate returnDate;
    private LocalDate actuallyReturnDate; // Null nếu chưa trả
    private String readerID;
    private String staffID;
    private Scanner sc = new Scanner(System.in);

    public Borrow() {
        // Khởi tạo các giá trị mặc định khi tạo mới
        this.brDate = LocalDate.now();
        this.returnDate = brDate.plusDays(14); // Giả sử thời hạn mượn là 14 ngày
    }

    public Borrow(String borrowId, LocalDate brDate, LocalDate returnDate, LocalDate actuallyReturnDate, String readerID, String staffID) {
        this.borrowId = borrowId;
        this.brDate = brDate;
        this.returnDate = returnDate;
        this.actuallyReturnDate = actuallyReturnDate;
        this.readerID = readerID;
        this.staffID = staffID;
    }

    // --- Input method for Console ---
    public void nhap() {
        System.out.print("Nhập ID phiếu mượn: ");
        this.borrowId = sc.nextLine();
        System.out.print("Nhập ID độc giả: ");
        this.readerID = sc.nextLine();
        System.out.print("Nhập ID nhân viên: ");
        this.staffID = sc.nextLine();
        
        this.brDate = LocalDate.now(); // Ngày mượn là ngày hiện tại
        this.returnDate = brDate.plusDays(14); // Ngày hẹn trả
        System.out.println("-> Ngày mượn: " + this.brDate + ", Ngày hẹn trả: " + this.returnDate);
        this.actuallyReturnDate = null;
    }

    // --- Logic methods ---
    public boolean daTra() {
        return this.actuallyReturnDate != null;
    }

    public boolean quaHan(LocalDate homNay) {
        // Chỉ kiểm tra quá hạn nếu chưa trả (actuallyReturnDate == null)
        if (!daTra()) {
            return homNay.isAfter(this.returnDate);
        }
        // Nếu đã trả, kiểm tra xem ngày trả thực tế có sau ngày hẹn trả không
        return this.actuallyReturnDate != null && this.actuallyReturnDate.isAfter(this.returnDate);
    }
    
    // --- CSV I/O ---
    public String toCSV() {
        // Cần chuyển đổi LocalDate thành String (yyyy-MM-dd)
        String actualReturnStr = actuallyReturnDate != null ? actuallyReturnDate.toString() : "";
        return borrowId + "," + brDate.toString() + "," + returnDate.toString() + "," + actualReturnStr + "," + readerID + "," + staffID;
    }

    /**
     * Phương thức tĩnh để tạo đối tượng Borrow từ chuỗi CSV.
     * Thứ tự fields: borrowId, brDate, returnDate, actuallyReturnDate, readerID, staffID
     */
    public static Borrow readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        // Có thể có 5 hoặc 6 trường (tùy actuallyReturnDate có giá trị hay không)
        if (parts.length < 5 || parts.length > 6) return null;
        
        LocalDate brDate = LocalDate.parse(parts[1].trim());
        LocalDate returnDate = LocalDate.parse(parts[2].trim());
        LocalDate actualReturnDate = parts.length == 6 && !parts[3].trim().isEmpty() ? LocalDate.parse(parts[3].trim()) : null;
        
        return new Borrow(
            parts[0].trim(),
            brDate,
            returnDate,
            actualReturnDate,
            parts[parts.length - 2].trim(), // readerID (vị trí cuối - 2)
            parts[parts.length - 1].trim()  // staffID (vị trí cuối - 1)
        );
    }

    // --- Getters & Setters ---
    public String getBorrowId() { return borrowId; }
    public void setBorrowId(String borrowId) { this.borrowId = borrowId; }

    public LocalDate getBrDate() { return brDate; }

    public LocalDate getReturnDate() { return returnDate; }

    public LocalDate getActuallyReturnDate() { return actuallyReturnDate; }
    public void setActuallyReturnDate(LocalDate actuallyReturnDate) { this.actuallyReturnDate = actuallyReturnDate; }

    public String getReaderID() { return readerID; }

    public String getStaffID() { return staffID; }
    
    @Override
    public String toString() {
        String trangThai = daTra() ? "Đã trả" : "Đang mượn";
        String ngayTraThucTe = actuallyReturnDate != null ? actuallyReturnDate.toString() : "Chưa trả";
        return String.format("ID: %s | Độc giả: %s | NV: %s | Mượn: %s | Hẹn trả: %s | Trả TT: %s | Trạng thái: %s",
            borrowId, readerID, staffID, brDate, returnDate, ngayTraThucTe, trangThai);
    }
}