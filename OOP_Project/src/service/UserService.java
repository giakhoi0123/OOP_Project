package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Reader;

public class UserService {
    private List<Reader> readers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Thêm người dùng
    public void addUser() {
        System.out.println("\n--- Thêm người dùng mới ---");
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Nhập ID người dùng: ");
        String readerId = scanner.nextLine();

        Reader reader = new Reader(username, password, readerId);
        readers.add(reader);

        System.out.println("Đã thêm người dùng thành công: " + reader.getUsername());
    }

    // Xóa người dùng
    public void deleteUser() {
        System.out.println("\n--- Xóa người dùng ---");
        System.out.print("Nhập ID người dùng cần xóa: ");
        String readerId = scanner.nextLine();

        Reader readerToRemove = null;
        for (Reader reader : readers) {
            if (reader.getReaderId().equals(readerId)) {
                readerToRemove = reader;
                break;
            }
        }

        if (readerToRemove != null) {
            readers.remove(readerToRemove);
            System.out.println("Đã xóa người dùng: " + readerToRemove.getUsername());
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + readerId);
        }
    }

    // Lấy tổng số người dùng
    public int getTotalUsers() {
        return readers.size();
    }
}