// src/model/Staff.java
package model;

public class Staff {
    private String staffId;
    private String name;
    private String birth;
    private String address;
    
    public Staff() {
    }
    public Staff(String name, String birth, String address, String staffId) {
        this.name = name;
        this.staffId = staffId;
        this.birth = birth;
        this.address = address;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã nhân viên: ");
        staffId = scanner.nextLine();
        System.out.print("Nhập tên nhân viên: ");
        name = scanner.nextLine();
        System.out.print("Nhập ngày sinh nhân viên: ");
        birth = scanner.nextLine();
        System.out.print("Nhập địa chỉ nhân viên: ");
        address = scanner.nextLine();
    }
    // Getters, setters, toString()
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    @Override
    public String toString() {
        return "Staff{staffId='" + staffId + "', name='" + name + "', birth='" + birth + "', address='" + address + "'}";
    }
}