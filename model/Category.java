package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Category {
    private String id;
    private String name;
    
    public Category() {
    }
    
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã thể loại (Category ID): ");
        id = scanner.nextLine();
        System.out.print("Nhập tên thể loại: ");
        name = scanner.nextLine();
    }
    
    // --- Getters & Setters ---
    public String getCategoryId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Ghi file đơn lẻ. (Đã sửa đường dẫn file)
     */
    public void ghiFile() {
        String filename = "data/categories.csv"; // <-- Sửa đường dẫn file về data/

        try(FileWriter fw = new FileWriter(filename, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + filename + ": " + e.getMessage());
        }
    }
    
    /**
     * Chuyển đổi đối tượng thành chuỗi CSV.
     */
    public String toCSV (){
        return id + "," + name;
    }

    /**
     * Phương thức tĩnh để tạo một Category object từ một dòng CSV.
     */
    public static Category readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 2) {
            System.err.println("❌ Lỗi định dạng CSV cho Thể loại (Category): Dòng không đủ 2 trường. Dòng: " + csvLine);
            return null;
        }
        return new Category(
            parts[0].trim(), // id
            parts[1].trim()  // name
        );
    }

    @Override
    public String toString() {
        // Tối ưu hiển thị đẹp, rõ ràng bằng Tiếng Việt
        return String.format("| Mã Thể loại: %s | Tên: %s", id, name);
    }
}