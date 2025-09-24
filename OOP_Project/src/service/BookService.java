package service;

import java.util.Scanner;
import model.Author;
import model.Book;
import model.Category;
import model.Publisher;

interface IBookService {
    void addBook();
    void deleteBook();
    void searchBook();
    int getTotalBooks();
}

public class BookService implements IBookService {
    private static final int MAX_BOOKS = 100; // Số sách tối đa
    private Book[] books = new Book[MAX_BOOKS];
    private int count = 0; // Số sách hiện có
    private Scanner scanner = new Scanner(System.in);

    // Thêm sách vào mảng
    @Override
    public void addBook() {
        if (count >= MAX_BOOKS) {
            System.out.println("Không thể thêm sách. Bộ nhớ đã đầy!");
            return;
        }

        System.out.println("\n--- Thêm sách mới ---");
        System.out.print("Nhập ID sách: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        String authorName = scanner.nextLine();
        System.out.print("Nhập tên nhà xuất bản: ");
        String publisherName = scanner.nextLine();
        System.out.print("Nhập thể loại sách: ");
        String categoryName = scanner.nextLine();

        Author author = new Author("A" + id, authorName);
        Publisher publisher = new Publisher("P" + id, publisherName);
        Category category = new Category("C" + id, categoryName);

        Book book = new Book(id, title, author, publisher, category);
        books[count] = book;
        count++;

        System.out.println("Đã thêm sách thành công: " + book.getTitle());
    }

    // Xóa sách khỏi mảng
    @Override
    public void deleteBook() {
        System.out.println("\n--- Xóa sách ---");
        System.out.print("Nhập ID sách cần xóa: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < count; i++) {
            if (books[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Dịch các phần tử phía sau lên
            for (int i = index; i < count - 1; i++) {
                books[i] = books[i + 1];
            }
            books[count - 1] = null; // Xóa phần tử cuối
            count--;
            System.out.println("Đã xóa sách với ID: " + id);
        } else {
            System.out.println("Không tìm thấy sách với ID: " + id);
        }
    }

    // Tìm kiếm sách theo tên
    @Override
    public void searchBook() {
        System.out.println("\n--- Tìm kiếm sách ---");
        System.out.print("Nhập tên sách cần tìm: ");
        String title = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sách nào với tên: " + title);
        }
    }

    // Lấy tổng số lượng sách
    @Override
    public int getTotalBooks() {
        return count;
    }
}
