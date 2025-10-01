package model;

public class Return {
    private String returnId;
    private String borrowId;
    private String bookId;
    private String fine;
    private String return_date;
    private String StaffID;
    private int quantity;
    public Return() {
    }
    public Return(String returnId, String borrowId, String bookId, String fine, String return_date, String StaffID) {
        this.returnId = returnId;
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.fine = fine;
        this.return_date = return_date;
        this.StaffID = StaffID;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã phiếu trả: ");
        returnId = scanner.nextLine();
        System.out.print("Nhập mã phiếu mượn: ");
        borrowId = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        bookId = scanner.nextLine();
        System.out.print("Nhập tiền phạt (nếu có, nếu không thì nhập 0): ");
        fine = scanner.nextLine();
        System.out.print("Nhập ngày trả (yyyy-MM-dd): ");
        return_date = scanner.nextLine();
        System.out.print("Nhập mã nhân viên: ");
        StaffID = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        quantity = Integer.parseInt(scanner.nextLine());
    }
    public String getReturnId() {
        return returnId;
    }
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }
    public String getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getFine() {
        return fine;
    }
    public void setFine(String fine) {
        this.fine = fine;
    }
    public String getReturn_date() {
        return return_date;
    }
    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
    public String getStaffID() {
        return StaffID;
    }
    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Return{returnId='" + returnId + "', borrowId='" + borrowId + "', bookId='" + bookId + "', fine='" + fine + "', return_date='" + return_date + "', StaffID='" + StaffID + "', quantity='" + quantity + "'}";
    }
}
