package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Return {
    private String returnId;
    private LocalDate returndate;
    private String borrowId;
    private String staffId;
    private double fineAmount;
    private ReturnDetail[] details;
    private int count = 0;
    
    // ĐỊNH DẠNG ĐỒNG BỘ: yyyy-MM-dd cho CSV, dd/MM/yyyy cho Console
    private static final DateTimeFormatter CSV_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Return(){
        returnId = "";
        returndate = LocalDate.now();
        borrowId = "";
        staffId = "";
        fineAmount = 0;
        details = new ReturnDetail[0];
    }

    public Return(String returnId, LocalDate returndate, String borrowId, String staffId, double fineAmount) {
        this.returnId = returnId;
        this.returndate = returndate;
        this.borrowId = borrowId;
        this.staffId = staffId;
        this.fineAmount = fineAmount;
        this.details = new ReturnDetail[0]; 
    }
    
    // Constructor đầy đủ (có chi tiết)
    public Return(String returnId, LocalDate returndate, String borrowId, String staffId, double fineAmount, ReturnDetail[] details, int count) {
        this(returnId, returndate, borrowId, staffId, fineAmount);
        this.details = details;
        this.count = count;
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhập mã phiếu trả (Return ID): ");
        returnId = sc.nextLine();
        System.out.print("Nhập ngày trả (dd/MM/yyyy): ");
        try {
            this.returndate = LocalDate.parse(sc.nextLine(), DISPLAY_FORMATTER); 
        } catch (Exception e) {
            System.out.println("⚠️ Định dạng ngày không hợp lệ. Sử dụng ngày hiện tại.");
            this.returndate = LocalDate.now();
        }
        
        System.out.print("Nhập mã phiếu mượn (Borrow ID): ");
        borrowId = sc.nextLine();
        System.out.print("Nhập mã nhân viên (Staff ID): ");
        staffId = sc.nextLine();
        
        System.out.print("Nhập số lượng chi tiết sách trả (Return Details): ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            n = 0;
        }

        details = new ReturnDetail[n]; 
        count = 0;
        fineAmount = 0;
        details = Arrays.copyOf(details, count);
    }

    public void output(){
        System.out.println("Mã Phiếu Trả (ID): " + returnId);
        System.out.println("Ngày Trả: " + returndate.format(DISPLAY_FORMATTER)); 
        System.out.println("Mã Phiếu Mượn: " + borrowId);
        System.out.println("Mã Nhân Viên: " + staffId);
        System.out.println("Tiền Phạt: " + fineAmount);
        System.out.println("--- Chi tiết sách trả ---");
        for(int i = 0; i < count; i++){
            System.out.println("Chi tiết " + (i + 1) + ":");
            System.out.println(details[i]);
        }
    }
    
    // --- Getters & Setters --- (Giữ nguyên)
    public String getReturnId() { return returnId; }
    public void setReturnId(String returnId) { this.returnId = returnId; }
    public LocalDate getReturndate() { return returndate; }
    public void setReturndate(LocalDate returndate) { this.returndate = returndate; }
    public String getBorrowId() { return borrowId; }
    public void setBorrowId(String borrowId) { this.borrowId = borrowId; }
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
    public ReturnDetail[] getDetails() { return Arrays.copyOf(details, count); }
    public void setDetails(ReturnDetail[] details) { this.details = details; this.count = details.length; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String toCSV() {
        // Ghi file theo format yyyy-MM-dd (CSV_FORMATTER)
        return returnId + "," + returndate.format(CSV_FORMATTER) + "," + borrowId + "," + staffId + "," + fineAmount;
    }

    public void ghiFile() {
        // Sửa đường dẫn file về data/
        String fileName = "data/return.csv"; 
        try (FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCSV());
            bw.newLine();           
        } 
        catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fileName + ": " + e.getMessage());
        }
    }  
    
    public static Return readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        // Cần 5 trường
        if (parts.length != 5) {
            System.err.println("Lỗi CSV Return: Không đủ 5 trường. Dòng: " + csvLine);
            return null;
        }
        
        try {
            // SỬA LỖI ĐỊNH DẠNG: Đọc ngày tháng theo CSV_FORMATTER (yyyy-MM-dd)
            LocalDate returnDate = LocalDate.parse(parts[1].trim(), CSV_FORMATTER);
            double fine = Double.parseDouble(parts[4].trim());
            
            return new Return(
                parts[0].trim(), // returnId
                returnDate,
                parts[2].trim(), // borrowId
                parts[3].trim(), // staffId
                fine
            );
        } catch (Exception e) {
            // Sửa thông báo lỗi sang tiếng Việt và báo cáo lỗi chi tiết
            System.err.println("❌ Lỗi đọc CSV Return: " + csvLine + " - Lỗi chuyển đổi dữ liệu.");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Phiếu Trả ID: %s | Ngày Trả: %s | Mượn ID: %s | NV ID: %s | Phạt: %.0f VNĐ",
            returnId, returndate.format(DISPLAY_FORMATTER), borrowId, staffId, fineAmount);
    }
}