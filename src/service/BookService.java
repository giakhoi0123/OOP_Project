package service;
import java.util.Scanner;
import model.*;

interface IBookService {
    void addBook();
    void updateBook();
    void deleteBook();
    Book getBookById(String bookId);
    Book[] getBooksByAuthorId(String authorId);
    Book[] getBooksByCategoryId(String categoryId);
    Book[] getBooksByPublisherId(String publisherId);
    Book[] getBooksBySupplierId(String supplierId);
}

public class BookService implements IBookService {
    private Book[] books = new Book[100];
    private int bookCount = 0;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addBook() {
    if (bookCount >= books.length) {
        System.out.println("❌ Danh sách sách đã đầy!");
        return;
    }

    System.out.println("Chọn loại sách để thêm:");
    System.out.println("1. Sách thường");
    System.out.println("2. Tiểu thuyết (Novel)");
    System.out.println("3. Sách giáo khoa (Text_Book)");
    System.out.print("👉 Nhập lựa chọn: ");
    int choice = Integer.parseInt(scanner.nextLine());

    Book newBook;
    switch (choice) {
        case 2:
            newBook = new Novel();
            break;
        case 3:
            newBook = new Text_Book();
            break;
        default:
            newBook = new Book();
            break;
    }

    newBook.nhap(); // gọi nhap() phù hợp (đa hình)
    books[bookCount++] = newBook;

    System.out.println("✅ Thêm sách thành công!");
}

    public void findBookById(String bookId) {
        Book book = getBookById(bookId);
        if (book != null) {
            System.out.println("Thông tin sách:");
            System.out.println(book);
        } else {
            System.out.println("❌ Không tìm thấy sách!");
        }
    }

    @Override
    public void updateBook() {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String bookId = scanner.nextLine();
        Book book = getBookById(bookId);
        if (book == null) {
            System.out.println("❌ Không tìm thấy sách!");
            return;
        }
        System.out.print("Nhập tên mới: ");
        String newTitle = scanner.nextLine();
        book.setTitle(newTitle);

        System.out.println("✅ Cập nhật sách thành công!");
    }

    @Override
    public void deleteBook() {
        System.out.print("Nhập mã sách cần xóa: ");
        String bookId = scanner.nextLine();
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(bookId)) {
                // dồn mảng
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                bookCount--;
                System.out.println("✅ Xóa sách thành công!");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy sách để xóa!");
    }

    @Override
    public Book getBookById(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(bookId)) {
                return books[i];
            }
        }
        return null;
    }

    @Override
    public Book[] getBooksByAuthorId(String authorId) {
        Book[] result = new Book[bookCount];
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getAuthorId().equals(authorId)) {
                result[count++] = books[i];
            }
        }
        return trimResult(result, count);
    }

    @Override
    public Book[] getBooksByCategoryId(String categoryId) {
        Book[] result = new Book[bookCount];
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getCategoryId().equals(categoryId)) {
                result[count++] = books[i];
            }
        }
        return trimResult(result, count);
    }

    @Override
    public Book[] getBooksByPublisherId(String publisherId) {
        Book[] result = new Book[bookCount];
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getPublisherId().equals(publisherId)) {
                result[count++] = books[i];
            }
        }
        return trimResult(result, count);
    }

    @Override
    public Book[] getBooksBySupplierId(String supplierId) {
        Book[] result = new Book[bookCount];
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getSupplierId().equals(supplierId)) {
                result[count++] = books[i];
            }
        }
        return trimResult(result, count);
    }

    // cắt mảng thừa null
    private Book[] trimResult(Book[] arr, int size) {
        Book[] trimmed = new Book[size];
        for (int i = 0; i < size; i++) {
            trimmed[i] = arr[i];
        }
        return trimmed;
    }
}
