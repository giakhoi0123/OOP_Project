package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Staff {
    private String staffId;
    private String name;
    private String birth;
    private String address;
    
    public Staff() {
    }
    
    public Staff(String staffId, String name, String birth, String address) { 
        this.staffId = staffId;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }
    
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã nhân viên (Staff ID): ");
        staffId = scanner.nextLine();
        System.out.print("Nhập tên nhân viên: ");
        name = scanner.nextLine();
        System.out.print("Nhập ngày sinh (yyyy-MM-dd): "); // Gợi ý định dạng ngày tháng chuẩn
        birth = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
    }
    
    // --- Getters & Setters ---
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String toCSV() {
        return staffId + "," + name + "," + birth + "," + address;
    }
    
    /**
     * Phương thức tĩnh để tạo đối tượng Staff từ chuỗi CSV.
     */
    public static Staff readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 4) {
            System.err.println("Lỗi định dạng CSV cho Nhân viên (Staff): Dòng không đủ 4 trường. Dòng: " + csvLine);
            return null;
        }
        return new Staff(
            parts[0].trim(), // staffId
            parts[1].trim(), // name
            parts[2].trim(), // birth
            parts[3].trim()  // address
        );
    }
    
    public void ghiFile() {
        String filename = "data/staff.csv"; // <-- Sửa đường dẫn file về data/

        try (FileWriter fw = new FileWriter(filename, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + filename + ": " + e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        // Tối ưu hiển thị đẹp, rõ ràng bằng Tiếng Việt
        return String.format(
            "| Mã NV: %s | Tên: %s\n" +
            "| Ngày sinh: %s | Địa chỉ: %s",
            staffId, name, birth, address
        );
    }
}