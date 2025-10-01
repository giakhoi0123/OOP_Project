package service;

import java.util.Scanner;
import model.Book;
import model.Borrow;
import model.Borrow_ticket;

interface IBorrowService {
    void createBorrow();
    void returnBook();
    void showAllBorrows();
}

public class BorrowService implements IBorrowService {
    private Borrow[] borrowList = new Borrow[100];
    private Borrow_ticket[] ticketList = new Borrow_ticket[100];
    private Book[] bookList;  
    private int borrowCount = 0;
    private int ticketCount = 0;
    private int bookCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public BorrowService(Book[] bookList, int bookCount) {
        this.bookList = bookList;
        this.bookCount = bookCount;
    }

    @Override
    public void createBorrow() {
        System.out.println("\n--- Tạo phiếu mượn ---");
        Borrow newBorrow = new Borrow();
        newBorrow.nhap();

        Book book = findBookById(newBorrow.getBookID());
        if (book == null || !book.isAvailable()) {
            System.out.println("❌ Sách không tồn tại hoặc đã được mượn!");
            return;
        }

        // Thêm Borrow
        if (borrowCount < borrowList.length) {
            Borrow borrow = newBorrow;
            borrowList[borrowCount++] = borrow;
        } else {
            System.out.println("❌ Danh sách borrow đã đầy!");
            return;
        }

        Borrow_ticket newBorrow_ticket = new Borrow_ticket(newBorrow.getBorrowId(), newBorrow.getBookID(), newBorrow.getReaderID());
        // Thêm Borrow_ticket
        if (ticketCount < ticketList.length) {
            Borrow_ticket ticket = newBorrow_ticket;
            ticketList[ticketCount++] = ticket;
        } else {
            System.out.println("❌ Danh sách ticket đã đầy!");
            return;
        }

        // Cập nhật sách
        book.setAvailable(false);
        System.out.println("✅ Tạo phiếu mượn thành công!");
    }

    @Override
    public void returnBook() {
        System.out.println("\n--- Trả sách ---");
        System.out.print("Nhập mã phiếu mượn: ");
        String borrowId = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        String bookId = scanner.nextLine();
        System.out.print("Nhập ngày trả thực tế: ");
        String actualReturnDate = scanner.nextLine();

        Borrow borrow = findBorrowById(borrowId);
        if (borrow == null) {
            System.out.println("❌ Phiếu mượn không tồn tại!");
            return;
        }

        borrow.setActually_return_date(actualReturnDate);
        Book book = findBookById(bookId);
        if (book != null) {
            book.setAvailable(true);
        }
        System.out.println("✅ Trả sách thành công!");
    }

    private Borrow findBorrowById(String borrowId) {
        for (int i = 0; i < borrowCount; i++) {
            if (borrowList[i].getBorrowId().equals(borrowId)) {
                return borrowList[i];
            }
        }
        return null;
    }

    private Book findBookById(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].getId().equals(bookId)) {
                return bookList[i];
            }
        }
        return null;
    }

    @Override
    public void showAllBorrows() {
        System.out.println("\n--- Danh sách phiếu mượn ---");
        for (int i = 0; i < borrowCount; i++) {
            System.out.println(borrowList[i]);
        }
    }
}
