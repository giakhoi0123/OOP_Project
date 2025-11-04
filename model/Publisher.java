package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Publisher {
    private String publisherId;
    private String name;
    private String address;
    private String phone;

    public Publisher() {
    }
    public Publisher(String publisherId, String name, String address, String phone) {
        this.publisherId = publisherId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    
    public void nhap() {
        // LƯU Ý: Đã xóa scanner.close() để tránh đóng System.in
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã nhà xuất bản (Publisher ID): ");
        publisherId = scanner.nextLine();
        System.out.print("Nhập tên nhà xuất bản: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        phone = scanner.nextLine();
    }
    
    public String getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public static Publisher readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 4) {
            System.err.println("Lỗi định dạng CSV cho Nhà Xuất Bản: Dòng không đủ 4 trường. Dòng: " + csvLine);
            return null;
        }
        return new Publisher(
            parts[0].trim(), // publisherId
            parts[1].trim(), // name
            parts[2].trim(), // address
            parts[3].trim()  // phone
        );
    }
    
    public String toCSV() {
        return publisherId + "," + name + "," + address + "," + phone;
    }

    public void ghiFile() {
        // SỬA ĐƯỜNG DẪN FILE VỀ data/
        String fileName = "data/publishers.csv"; 
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
            "| ID: %s | Tên NXB: %s\n" +
            "| Địa chỉ: %s\n" +
            "| SĐT: %s",
            publisherId, name, address, phone
        );
    }
}