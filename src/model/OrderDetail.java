package model;

public class OrderDetail {
    private String orderDetailId;
    private String orderId;
    private String bookId;
    private int quantity;
    private String price;
    public OrderDetail() {
    }
    public OrderDetail(String orderDetailId, String orderId, String bookId, int quantity, String price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã chi tiết đơn hàng: ");
        orderDetailId = scanner.nextLine();
        System.out.print("Nhập mã đơn hàng: ");
        orderId = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        bookId = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập giá: ");
        price = scanner.nextLine();
    }
    public String getOrderDetailId() {
        return orderDetailId;
    }
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "OrderDetial{orderDetailId='" + orderDetailId + "', orderId='" + orderId + "', bookId='" + bookId + "', quantity='" + quantity + "', price='" + price + "'}";
    }
}
