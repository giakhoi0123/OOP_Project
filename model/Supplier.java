package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Supplier {
    private String supplierId;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Supplier() {
    }
    public Supplier(String supplierId, String name, String address, String phone, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    
    public void nhap() { // Sử dụng @Override cho tính nhất quán
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp (Supplier ID): ");
        supplierId = scanner.nextLine();
        System.out.print("Nhập tên nhà cung cấp: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        phone = scanner.nextLine();
        System.out.print("Nhập email: ");
        email = scanner.nextLine();
    }

    // --- Getters & Setters ---
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    /**
     * Chuyển đổi đối tượng sang định dạng CSV.
     */
    public String toCSV() {
        return supplierId + "," + name + "," + address + "," + phone + "," + email;
    }
    
    /**
     * Phương thức tĩnh để tạo đối tượng Supplier từ chuỗi CSV.
     */
    public static Supplier readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 5) {
            System.err.println("Lỗi định dạng CSV cho Nhà Cung Cấp: Dòng không đủ 5 trường. Dòng: " + csvLine);
            return null;
        }
        return new Supplier(
            parts[0].trim(), // supplierId
            parts[1].trim(), // name
            parts[2].trim(), // address
            parts[3].trim(), // phone
            parts[4].trim()  // email
        );
    }
    
    /**
     * Ghi file đơn lẻ. (Đã sửa đường dẫn file)
     */
    public void ghiFile() {
        String fileName = "data/suppliers.csv"; 
        try (FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(this.toCSV());
            bw.newLine();           
            
        } 
        catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fileName + ": " + e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        // Tối ưu hiển thị đẹp, rõ ràng bằng Tiếng Việt
        return String.format(
            "| ID: %s | Tên NCC: %s\n" +
            "| Địa chỉ: %s\n" +
            "| SĐT: %s | Email: %s",
            supplierId, name, address, phone, email
        );
    }
}