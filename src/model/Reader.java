package model;

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
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã người đọc: ");
        readerId = scanner.nextLine();
        System.out.print("Nhập tên người đọc: ");
        name = scanner.nextLine();
        System.out.print("Nhập ngày sinh người đọc: ");
        birth = scanner.nextLine();
        System.out.print("Nhập địa chỉ người đọc: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại người đọc: ");
        phone = scanner.nextLine();
        System.out.print("Nhập email người đọc: ");
        email = scanner.nextLine();
        scanner.close();
    }
    public String getReaderId() {
        return readerId;
    }
    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
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
        return "Reader{readerId='" + readerId + "', name='" + name + "', birth='" + birth + "', address='" + address + "', phone='" + phone + "', email='" + email + "'}";
    }
}