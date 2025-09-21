// src/service/BookService.java
package service;

import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books = new ArrayList<>();

    // Thêm sách vào danh sách
    public void addBook() {
        // Tạo sách mẫu (có thể thay bằng nhập từ người dùng)
        Book book = new Book("1", "Java Programming", null, null, null);
        books.add(book);
        System.out.println("Đã thêm sách: " + book.getTitle());
    }

    // Xóa sách khỏi danh sách
    public void deleteBook() {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào để xóa.");
            return;
        }
        books.remove(0); // Xóa sách đầu tiên (chỉ là ví dụ)
        System.out.println("Đã xóa sách đầu tiên trong danh sách.");
    }

    // Tìm kiếm sách
    public void searchBook() {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong thư viện.");
            return;
        }
        System.out.println("Danh sách sách hiện có:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle());
        }
    }
}