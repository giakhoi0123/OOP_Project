package model;

public class Book {
    private String id;
    private String title;
    private String authorID;
    private String publisherID;
    private String categoryID;
    private String supplierID;
    private boolean isAvailable;
    public Book() {
    }
    public Book(String id, String title, String authorID, String publisherID, String categoryID, String supplierID,boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.authorID = authorID;
        this.publisherID = publisherID;
        this.categoryID = categoryID;
        this.isAvailable = true;
    }

    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        id = scanner.nextLine();
        System.out.print("Nhập tiêu đề sách: ");
        title = scanner.nextLine();
        System.out.print("Nhập mã tác giả: ");
        authorID = scanner.nextLine();
        System.out.print("Nhập mã nhà xuất bản: ");
        publisherID = scanner.nextLine();
        System.out.print("Nhập mã thể loại: ");
        categoryID = scanner.nextLine();
        System.out.print("Nhập mã nhà cung cấp: ");
        supplierID = scanner.nextLine();
        System.out.print("Sách có sẵn (true/false): ");
        isAvailable = scanner.nextBoolean();
        scanner.close();
    }
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

    public String getSupplierId() {
        return supplierID;
    }
    
    public void setSupplierId(String supplierID) {
        this.supplierID = supplierID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorID='" + authorID + '\'' +
                ", publisherID='" + publisherID + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}