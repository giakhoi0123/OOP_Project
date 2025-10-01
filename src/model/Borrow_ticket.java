package model;

public class Borrow_ticket {
    private String borrowTicketId;
    private String borrowId;
    private String bookId;
    private int quantity;

    public Borrow_ticket() {
    }

    public Borrow_ticket(String borrowTicketId, String borrowId, String bookId) {
        this.borrowTicketId = borrowTicketId;
        this.borrowId = borrowId;
        this.bookId = bookId;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã chi tiết phiếu mượn: ");
        borrowTicketId = scanner.nextLine();
        System.out.print("Nhập mã phiếu mượn: ");
        borrowId = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        bookId = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        quantity = Integer.parseInt(scanner.nextLine());
    }
    public String getBorrowTicketId() {
        return borrowTicketId;
    }

    public void setBorrowTicketId(String borrowTicketId) {
        this.borrowTicketId = borrowTicketId;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Borrow_ticket{borrowTicketId='" + borrowTicketId + "', borrowId='" + borrowId + "', bookId='" + bookId + "', quantity='" + quantity + "'}";
    }
}
