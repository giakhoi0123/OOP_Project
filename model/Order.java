package model;

public class Order {
    private String orderId;
    private String staffId;
    private String orderDate;
    private String totalAmount;
    private String supplierId;

    public Order() {
    }
    public Order(String orderId, String staffId, String orderDate, String totalAmount, String supplierId) {
        this.orderId = orderId;
        this.staffId = staffId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.supplierId = supplierId;
    }
    
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã đơn hàng (Order ID): ");
        orderId = scanner.nextLine();
        System.out.print("Nhập mã nhân viên (Staff ID): ");
        staffId = scanner.nextLine();
        System.out.print("Nhập ngày đặt hàng (yyyy-MM-dd): ");
        orderDate = scanner.nextLine();
        // Giả định tổng tiền được tính toán sau, nhưng vẫn cho phép nhập
        System.out.print("Nhập tổng tiền: "); 
        totalAmount = scanner.nextLine();
        System.out.print("Nhập mã nhà cung cấp (Supplier ID): ");
        supplierId = scanner.nextLine();
    }
    
    // --- Getters & Setters ---
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public void ghiFile () {
        String fileName = "data/orders.csv"; // <-- Sửa đường dẫn file về data/
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName, true))) {
            writer.write(toCSV());
            writer.newLine();
            System.out.println("✅ Đã ghi đơn hàng vào file " + fileName + ".");
        } catch (java.io.IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fileName + ": " + e.getMessage());
        }
    }
    
    public static Order readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 5) {
            System.err.println("Lỗi định dạng CSV cho Đơn hàng (Order): Dòng không đủ 5 trường. Dòng: " + csvLine);
            return null;
        }
        
        // Sử dụng trim() để loại bỏ khoảng trắng thừa
        return new Order(
            parts[0].trim(), // orderId
            parts[1].trim(), // staffId
            parts[2].trim(), // orderDate
            parts[3].trim(), // totalAmount
            parts[4].trim()  // supplierId
        );
    }
    
    public String toCSV() {
        return orderId + "," + staffId + "," + orderDate + "," + totalAmount + "," + supplierId;
    }
    
    @Override
    public String toString() {
        // Tối ưu hiển thị đẹp, rõ ràng bằng Tiếng Việt
        return String.format(
            "| ID Đơn hàng: %s | Ngày đặt: %s | Tổng tiền: %s VNĐ\n" +
            "| Mã NV: %s | Mã NCC: %s",
            orderId, orderDate, totalAmount, staffId, supplierId
        );
    }
}