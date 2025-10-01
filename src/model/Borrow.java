package model;

public class Borrow {
    private String borrowId;
    private String br_date;
    private String return_date;
    private String actually_return_date;
    private String ReaderID;
    private String StaffID;
    private String BookID;
    public Borrow() {
    }
    public Borrow(String borrowId, String br_date, String return_date, String actually_return_date, String ReaderID, String StaffID) {
        this.borrowId = borrowId;
        this.br_date = br_date;
        this.return_date = return_date;
        this.actually_return_date = actually_return_date;
        this.ReaderID = ReaderID;
        this.StaffID = StaffID;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã phiếu mượn: ");
        borrowId = scanner.nextLine();
        System.out.print("Nhập ngày mượn (yyyy-MM-dd): ");
        br_date = scanner.nextLine();
        System.out.print("Nhập ngày hẹn trả (yyyy-MM-dd): ");
        return_date = scanner.nextLine();
        System.out.print("Nhập ngày trả thực tế (yyyy-MM-dd) hoặc để trống nếu chưa trả: ");
        actually_return_date = scanner.nextLine();
        System.out.print("Nhập mã bạn đọc: ");
        ReaderID = scanner.nextLine();
        System.out.print("Nhập mã nhân viên: ");
        StaffID = scanner.nextLine();
    }
    public String getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
    public String getBr_date() {
        return br_date;
    }
    public void setBr_date(String br_date) {
        this.br_date = br_date;
    }
    public String getReturn_date() {
        return return_date;
    }
    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
    public String getActually_return_date() {
        return actually_return_date;
    }
    public void setActually_return_date(String actually_return_date) {
        this.actually_return_date = actually_return_date;
    }
    public String getReaderID() {
        return ReaderID;
    }
    public void setReaderID(String ReaderID) {
        this.ReaderID = ReaderID;
    }
    public String getStaffID() {
        return StaffID;
    }
    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }
    public String getBookID() {
        return BookID;
    }
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }
    @Override
    public String toString() {
        return "Borrow{borrowId='" + borrowId + "', br_date='" + br_date + "', return_date='" + return_date + "', actually_return_date='" + actually_return_date + "', ReaderID='" + ReaderID + "', StaffID='" + StaffID + "'}";
    }
}
