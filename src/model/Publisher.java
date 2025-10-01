// src/model/Publisher.java
package model;

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
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã nhà xuất bản: ");
        publisherId = scanner.nextLine();
        System.out.print("Nhập tên nhà xuất bản: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ nhà xuất bản: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại nhà xuất bản: ");
        phone = scanner.nextLine();
        scanner.close();
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
    @Override
    public String toString() {
        return "Publisher{publisherId='" + publisherId + "', name='" + name + "', address='" + address + "', phone='" + phone + "'}";
    }
}