package model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Book {
    private String id;
    private String title;
    private String authorID;
    private String publisherID;
    private String categoryID;
    private String supplierID; // Thêm Supplier ID
    private boolean isAvailable;
    private int price;
    private int quantity; // Số lượng sách trong kho

    public Book() {
    }

    public Book(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, boolean isAvailable, int price, int quantity) {
        this.id = id;
        this.title = title;
        this.authorID = authorID;
        this.publisherID = publisherID;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
        this.isAvailable = isAvailable; // Đã sửa lỗi gán cố định true
        this.price = price;
        this.quantity = quantity;
    }

    public Book(Book b) {
    this.id = b.id;
    this.title = b.title;
    this.authorID = b.authorID;
    this.publisherID = b.publisherID;
    this.categoryID = b.categoryID;
    this.supplierID = b.supplierID;
    this.isAvailable = b.isAvailable;
    this.price = b.price;
    this.quantity = b.quantity;
}

    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã sách (Book ID): ");
        id = scanner.nextLine();
        System.out.print("Nhập tiêu đề sách: ");
        title = scanner.nextLine();
        System.out.print("Nhập mã tác giả (Author ID): ");
        authorID = scanner.nextLine();
        System.out.print("Nhập mã nhà xuất bản (Publisher ID): ");
        publisherID = scanner.nextLine();
        System.out.print("Nhập mã thể loại (Category ID): ");
        categoryID = scanner.nextLine();
        System.out.print("Nhập mã nhà cung cấp (Supplier ID): ");
        supplierID = scanner.nextLine();
        System.out.print("Sách có sẵn (true/false): ");
        isAvailable = scanner.nextBoolean();
        System.out.print("Nhập giá nhập kho (INT): ");
        price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập số lượng sách (Quantity): ");
        quantity = scanner.nextInt();
    }

    public abstract void xuatLoaiSach();
    
    // --- Getters & Setters (Giữ nguyên) ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorID;
    }

    public void setAuthorId(String authorID) {
        this.authorID = authorID;
    }

    public String getPublisherId() {
        return publisherID;
    }

    public void setPublisherId(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getCategoryId() {
        return categoryID;
    }

    public void setCategoryId(String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplierId() {
        return supplierID;
    }

    public void setSupplierId(String supplierID) {
        this.supplierID = supplierID;
    }

    public void ghiFile(String filename) {
        // Sửa đường dẫn file để bắt đầu từ thư mục 'data/'
        String fullPath = "data/" + filename; 
        try (FileWriter fw = new FileWriter(fullPath, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(this.toCSV()); 
            bw.newLine();
            
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fullPath + ": " + e.getMessage());
        }
    }

    public String toCSV() {
        return id + "," + title + "," + authorID + "," + publisherID + "," + categoryID + "," + supplierID + "," + isAvailable + "," + price + "," + quantity;
    }
    
    @Override
    public String toString() {
        // Định dạng hiển thị đẹp cho các trường chung của Book (sẽ được lớp con sử dụng)
        String available = this.isAvailable() ? "Có sẵn" : "Đã hết";
        
        return String.format(
            "| ID Sách: %s\n" +
            "| Tiêu đề: %s\n" +
            "| Giá nhập kho: %d VNĐ\n" +
            "| Trạng thái: %s\n" +
            "| Mã Tác giả: %s, Mã NXB: %s, Mã TL: %s, Mã NCC: %s, Số lượng: %d\n",
            this.getId(), this.getTitle(), this.getPrice(), available,
            this.getAuthorId(), this.getPublisherId(), this.getCategoryId(), this.getSupplierId(), this.getQuantity()
        );
    }
}