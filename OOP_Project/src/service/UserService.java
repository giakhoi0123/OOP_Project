package service;

import java.util.Scanner;
import model.Reader;

interface IUserService {
    void addUser();
    void deleteUser();
    int getTotalUsers();
}

public class UserService implements IUserService {
    private Reader[] readers = new Reader[100];  // Mảng chứa tối đa 100 người dùng
    private int userCount = 0;                   // Số người dùng hiện tại
    private Scanner scanner = new Scanner(System.in);

    // Thêm người dùng
    @Override
    public void addUser() {
        if (userCount >= readers.length) {
            System.out.println("Danh sách người dùng đã đầy, không thể thêm mới!");
            return;
        }

        System.out.println("\n--- Thêm người dùng mới ---");
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Nhập ID người dùng: ");
        String readerId = scanner.nextLine();

        readers[userCount] = new Reader(username, password, readerId);
        userCount++;

        System.out.println("Đã thêm người dùng thành công: " + username);
    }

    // Xóa người dùng
    @Override
    public void deleteUser() {
        if (userCount == 0) {
            System.out.println("Danh sách rỗng, không có người dùng để xóa!");
            return;
        }

        System.out.println("\n--- Xóa người dùng ---");
        System.out.print("Nhập ID người dùng cần xóa: ");
        String readerId = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < userCount; i++) {
            if (readers[i].getReaderId().equals(readerId)) {
                // Dời các phần tử phía sau lên để lấp chỗ trống
                for (int j = i; j < userCount - 1; j++) {
                    readers[j] = readers[j + 1];
                }
                readers[userCount - 1] = null;  // Xóa phần tử cuối
                userCount--;
                found = true;
                System.out.println("Đã xóa người dùng với ID: " + readerId);
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy người dùng với ID: " + readerId);
        }
    }

    // Lấy tổng số người dùng
    @Override
    public int getTotalUsers() {
        return userCount;
    }
}
