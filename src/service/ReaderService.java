package service;

import java.util.Scanner;
import model.*;

interface IReaderService {
    void addReader();
    void deleteReader();
    int getTotalReaders();
}

public class ReaderService implements IReaderService {
    private Reader[] readers = new Reader[100]; // mảng cố định 100 người đọc
    private int count = 0; // số người đọc hiện tại
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void addReader() {
        if (count >= readers.length) {
            System.out.println("Danh sách người đọc đã đầy!");
            return;
        }
        System.out.println("\n--- Thêm người đọc mới ---");
        Reader newReader = new Reader();
        newReader.nhap();
        readers[count++] = newReader;

        System.out.println("Đã thêm người đọc thành công: " + readers[count - 1].getName());
    }

    @Override
    public void deleteReader() {
        System.out.println("\n--- Xóa người đọc ---");
        System.out.print("Nhập ID người đọc cần xóa: ");
        String readerId = scanner.nextLine();

        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (readers[i].getReaderId().equals(readerId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            // Dời các phần tử sau về trước để lấp chỗ trống
            for (int i = indexToRemove; i < count - 1; i++) {
                readers[i] = readers[i + 1];
            }
            readers[count - 1] = null;
            count--;
            System.out.println("Đã xóa người dùng có ID: " + readerId);
        } else {
            System.out.println("Không tìm thấy người dùng với ID: " + readerId);
        }
    }

    @Override
    public int getTotalReaders() {
        return count;
    }
}
