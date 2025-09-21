// src/service/UserService.java
package service;

import model.Reader;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Reader> readers = new ArrayList<>();

    // Thêm người dùng
    public void addUser() {
        // Tạo người dùng mẫu (có thể thay bằng nhập từ người dùng)
        Reader reader = new Reader("user1", "password1", "R001");
        readers.add(reader);
        System.out.println("Đã thêm người dùng: " + reader.getUsername());
    }

    // Xóa người dùng
    public void deleteUser() {
        if (readers.isEmpty()) {
            System.out.println("Không có người dùng nào để xóa.");
            return;
        }
        readers.remove(0); // Xóa người dùng đầu tiên (chỉ là ví dụ)
        System.out.println("Đã xóa người dùng đầu tiên trong danh sách.");
    }
}