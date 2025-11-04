package model;

import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Scanner;

public class PenaltyRule {
    private String reasonId;
    private String reasonName;
    private double penalty;

    public PenaltyRule() {
    }

    public PenaltyRule(String reasonId, String reasonName, double penalty) {
        this.reasonId = reasonId;
        this.reasonName = reasonName;
        this.penalty = penalty;
    }
    
    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã lý do phạt (Reason ID): ");
        reasonId = sc.nextLine();
        System.out.print("Nhập tên lý do phạt: ");
        reasonName = sc.nextLine();
        System.out.print("Nhập số tiền phạt (VNĐ): ");
        
        try {
            penalty = Double.parseDouble(sc.nextLine()); 
        } catch (NumberFormatException e) {
            System.err.println("❌ Lỗi nhập liệu cho tiền phạt. Đặt mặc định là 0.0.");
            penalty = 0.0;
        }
    }

    public void output(){
        // Định dạng output đẹp hơn, dùng Tiếng Việt
        System.out.println(this.toString());
    }

    public PenaltyRule(PenaltyRule pr) {
        this.reasonId = pr.reasonId;
        this.reasonName = pr.reasonName;
        this.penalty = pr.penalty;
    }

    // --- Getters & Setters ---
    public String getReasonId() { return reasonId; }
    public void setReasonId(String reasonId) { this.reasonId = reasonId; }
    public String getReasonName() { return reasonName; }
    public void setReasonName(String reasonName) { this.reasonName = reasonName; }
    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }

    /**
     * Phương thức ghi file đơn lẻ. (Đã sửa đường dẫn file)
     */
    public void ghiFile() {
        String filename = "data/PenaltyRule.csv"; // <-- Sửa đường dẫn file về data/
        
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(this.toCSV());
            bw.newLine();
            
        } catch (IOException e) {
            System.err.println("❌ Lỗi ghi file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Chuyển đổi đối tượng sang định dạng CSV.
     */
    public String toCSV() {
        return reasonId + "," + reasonName + "," + penalty;
    }
    
    /**
     * Phương thức tĩnh để đọc đối tượng từ chuỗi CSV.
     */
    public static PenaltyRule readFromCSV(String csvLine) {
        String[] parts = csvLine.split(","); 
        
        if (parts.length != 3) {
            System.err.println("❌ Lỗi định dạng CSV cho Quy tắc phạt: Dòng không đủ 3 trường. Dòng: " + csvLine);
            return null;
        }
        
        try {
            return new PenaltyRule(
                parts[0].trim(),
                parts[1].trim(),
                Double.parseDouble(parts[2].trim())
            );
        } catch (NumberFormatException e) {
            System.err.println("❌ Lỗi định dạng số trong CSV Quy tắc phạt: " + csvLine);
            return null;
        }
    }
    
    @Override
    public String toString() {
        // Định dạng hiển thị đẹp và rõ ràng
        return String.format(
            "| Mã: %s | Tên lý do: %s\n" +
            "| Tiền phạt: %.0f VNĐ",
            reasonId, reasonName, penalty
        );
    }
}