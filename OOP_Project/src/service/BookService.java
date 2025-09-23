package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Author;
import model.Book;
import model.Category;
import model.Publisher;

public class BookService {
    private List<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Thêm sách vào danh sách
    public void addBook() {
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
        books.add(book);

        System.out.println("Đã thêm sách thành công: " + book.getTitle());
    }

    // Xóa sách khỏi danh sách
    public void deleteBook() {
        System.out.println("\n--- Xóa sách ---");
        System.out.print("Nhập ID sách cần xóa: ");
        String id = scanner.nextLine();

        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Đã xóa sách: " + bookToRemove.getTitle());
        } else {
            System.out.println("Không tìm thấy sách với ID: " + id);
        }
    }

    // Tìm kiếm sách theo tên
    public void searchBook() {
        System.out.println("\n--- Tìm kiếm sách ---");
        System.out.print("Nhập tên sách cần tìm: ");
        String title = scanner.nextLine();

        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }

        if (!foundBooks.isEmpty()) {
            System.out.println("Kết quả tìm kiếm:");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        } else {
            System.out.println("Không tìm thấy sách nào với tên: " + title);
        }
    }
    public int getTotalBooks() {
        return books.size();
    }
}