package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    private String readerId;
    private String name;
    private String birth;
    private String address;
    private String phone;
    private String email;

    public Reader() {
    }
    public Reader(String readerId, String name, String birth, String address, String phone, String email) {
        this.readerId = readerId;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã độc giả (Reader ID): ");
        readerId = scanner.nextLine();
        System.out.print("Nhập tên độc giả: ");
        name = scanner.nextLine();
        System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
        birth = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        phone = scanner.nextLine();
        System.out.print("Nhập email: ");
        email = scanner.nextLine();
    }

    // --- Getters & Setters ---
    public String getReaderId() { return readerId; }
    public void setReaderId(String readerId) { this.readerId = readerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }
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
        return readerId + "," + name + "," + birth + "," + address + "," + phone + "," + email;
    }
    
    /**
     * Phương thức tĩnh để tạo đối tượng Reader từ chuỗi CSV.
     */
    public static Reader readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 6) {
            System.err.println("Lỗi định dạng CSV cho Độc giả (Reader): Dòng không đủ 6 trường. Dòng: " + csvLine);
            return null;
        }
        return new Reader(
            parts[0].trim(), // readerId
            parts[1].trim(), // name
            parts[2].trim(), // birth
            parts[3].trim(), // address
            parts[4].trim(), // phone
            parts[5].trim()  // email
        );
    }
    
    /**
     * Ghi file đơn lẻ. (Đã sửa đường dẫn file)
     */
    public void ghiFile() {
        String fileName = "data/readers.csv"; 
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
            "| ID: %s | Tên: %s\n" +
            "| Ngày sinh: %s | Địa chỉ: %s\n" +
            "| SĐT: %s | Email: %s",
            readerId, name, birth, address, phone, email
        );
    }
}