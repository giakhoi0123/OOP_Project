// src/model/Author.java
package model; 

public class Author {
    private String authorId;
    private String name;
    private String address;
    private String phone;
    
    public Author() {
    }
    public Author(String authorId, String name, String address, String phone) {
        this.authorId = authorId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã tác giả: ");
        authorId = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ tác giả: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại tác giả: ");
        phone = scanner.nextLine();
        scanner.close();
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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
    @Override
    public String toString() {
        return "Author{authorId='" + authorId + "', name='" + name + "', address='" + address + "', phone='" + phone + "'}";
    }
}