package service;

import java.io.*;

interface INotificationService {
    void sendNotification(String loaiThongBao, String tenNguoiDung, String tenSach);
    void readNotifications();
}
public class NotificationService implements INotificationService{
    private static final String FILE_NAME = "thongbao.txt";

    // Gửi thông báo và lưu vào file
    public void sendNotification(String loaiThongBao, String tenNguoiDung, String tenSach) {
        String noiDung = "[Thông báo " + loaiThongBao + " sách]\n"
                + "Người dùng: " + tenNguoiDung + "\n"
                + "Tên sách: " + tenSach + "\n"
                + "Trạng thái: " + (loaiThongBao.equals("Mượn") ? "Đã mượn thành công!" : "Đã trả thành công!") + "\n";

        // In ra màn hình
        System.out.println("\n" + noiDung);

        // Ghi vào file
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(noiDung + "----------------------\n");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Đọc file và in ra nội dung
    public void readNotifications() {
        System.out.println("\n--- Lịch sử thông báo ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chưa có thông báo nào!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}
