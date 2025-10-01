package model;

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
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp: ");
        supplierId = scanner.nextLine();
        System.out.print("Nhập tên nhà cung cấp: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ nhà cung cấp: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại nhà cung cấp: ");
        phone = scanner.nextLine();
        System.out.print("Nhập email nhà cung cấp: ");
        email = scanner.nextLine();
        scanner.close();
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Supplier{supplierId='" + supplierId + "', name='" + name + "', address='" + address + "', phone='" + phone + "', email='" + email + "'}";
    }
}
