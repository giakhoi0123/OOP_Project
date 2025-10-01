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
        System.out.print("Nhập mã đơn hàng: ");
        orderId = scanner.nextLine();
        System.out.print("Nhập mã nhân viên: ");
        staffId = scanner.nextLine();
        System.out.print("Nhập ngày đặt hàng: ");
        orderDate = scanner.nextLine();
        System.out.print("Nhập tổng tiền: ");
        totalAmount = scanner.nextLine();
        System.out.print("Nhập mã nhà cung cấp: ");
        supplierId = scanner.nextLine();
    }
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
    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', staffId='" + staffId + "', orderDate='" + orderDate + "', totalAmount='" + totalAmount + "', supplierId='" + supplierId + "'}";
    }
    
}
