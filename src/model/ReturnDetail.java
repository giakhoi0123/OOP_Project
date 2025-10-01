package model;

public class ReturnDetail {
    private String returnDetailId;
    private String returnId;
    private String bookId;
    private String quantity;

    public ReturnDetail() {
    }
    public ReturnDetail(String returnDetailId, String returnId, String bookId, String quantity) {
        this.returnDetailId = returnDetailId;
        this.returnId = returnId;
        this.bookId = bookId;
        this.quantity = quantity;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã chi tiết trả: ");
        returnDetailId = scanner.nextLine();
        System.out.print("Nhập mã phiếu trả: ");
        returnId = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        bookId = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        quantity = scanner.nextLine();
    }
    public String getReturnDetailId() {
        return returnDetailId;
    }
    public void setReturnDetailId(String returnDetailId) {
        this.returnDetailId = returnDetailId;
    }
    public String getReturnId() {
        return returnId;
    }
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "ReturnDetail{returnDetailId='" + returnDetailId + "', returnId='" + returnId + "', bookId='" + bookId + "', quantity='" + quantity + "'}";
    }
}