package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import model.Publisher; // Cần import model.Publisher

public class PublisherService {
    // 1. Khai báo mảng động ban đầu là mảng rỗng
    private Publisher[] publishers = new Publisher[0];
    private int publisherCount = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final String DATA_FILE = "data/publishers.csv";

    public PublisherService() {
        loadPublishersFromFile(); // TẢI DỮ LIỆU KHI KHỞI TẠO
    }
    
    // ======================================================================
    // PHƯƠNG THỨC QUẢN LÝ (SỬ DỤNG MẢNG ĐỘNG VÀ TỰ ĐỘNG LƯU)
    // ======================================================================
    
    public void addPublisher(Publisher publisher) {
        // Tăng kích thước mảng lên 1
        publishers = Arrays.copyOf(publishers, publisherCount + 1);
        publishers[publisherCount++] = publisher;
        System.out.println("✅ Đã thêm nhà xuất bản thành công! Đang lưu...");
        savePublishersToFile(); // TỰ ĐỘNG LƯU
    }

    public void addPublisher() {
        Publisher newPublisher = new Publisher();
        newPublisher.nhap(); // Yêu cầu nhập từ bàn phím
        addPublisher(newPublisher);
    }

    public Publisher getPublisherById(String publisherId) {
        for (int i = 0; i < publisherCount; i++) {
            if (publishers[i].getPublisherId().equalsIgnoreCase(publisherId)) {
                return publishers[i];
            }
        }
        return null; 
    }

    public void findAndShowPublisherById() {
        System.out.print("Nhập ID nhà xuất bản cần tìm: ");
        String publisherId = scanner.nextLine();
        Publisher publisher = getPublisherById(publisherId);
        if (publisher != null) {
            System.out.println("🔎 Thông tin nhà xuất bản tìm thấy:");
            System.out.println(publisher);
        } else {
            System.out.println("❌ Không tìm thấy nhà xuất bản với ID: " + publisherId);
        }
    }

    public boolean updatePublisherById(String publisherId) {
        Publisher publisherToUpdate = getPublisherById(publisherId);
        if (publisherToUpdate != null) {
            System.out.println("📝 Tìm thấy NXB. Vui lòng nhập thông tin mới:");
            publisherToUpdate.nhap(); 
            publisherToUpdate.setPublisherId(publisherId);
            System.out.println("✅ Cập nhật thông tin NXB thành công! Đang lưu...");
            savePublishersToFile(); // TỰ ĐỘNG LƯU
            return true;
        }
        return false;
    }

    public void updatePublisher() {
        System.out.print("Nhập ID nhà xuất bản cần sửa: ");
        String publisherId = scanner.nextLine();
        if (!updatePublisherById(publisherId)) {
            System.out.println("❌ Không tìm thấy NXB với ID: " + publisherId + " để cập nhật.");
        }
    }

    public boolean removePublisherById(String publisherId) {
        int indexToRemove = -1;
        for (int i = 0; i < publisherCount; i++) {
            if (publishers[i].getPublisherId().equalsIgnoreCase(publisherId)) {
                indexToRemove = i;
                break;
            }
        }
        
        if (indexToRemove != -1) {
            for (int j = indexToRemove; j < publisherCount - 1; j++) {
                publishers[j] = publishers[j + 1];
            }
            publisherCount--;
            publishers = Arrays.copyOf(publishers, publisherCount);
            
            // TỰ ĐỘNG LƯU
            savePublishersToFile();
            return true;
        }
        return false;
    }

    public void removePublisher() {
        System.out.print("Nhập ID nhà xuất bản cần xóa: ");
        String publisherId = scanner.nextLine();
        if (removePublisherById(publisherId)) {
            System.out.println("🗑️ Xóa nhà xuất bản thành công!");
        } else {
            System.out.println("❌ Không tìm thấy NXB với ID: " + publisherId + " để xóa.");
        }
    }

    public void showAllPublishers() {
        if (publisherCount == 0) {
            System.out.println("❌ Chưa có nhà xuất bản nào trong danh sách!");
            return;
        }
        System.out.println("🏢 ===== DANH SÁCH NHÀ XUẤT BẢN (" + publisherCount + " NXB) =====");
        for (int i = 0; i < publisherCount; i++) {
            System.out.println(publishers[i]);
            System.out.println("--------------------");
        }
    }

    public int getPublisherCount() {
        return publisherCount;
    }
    
    // ======================================================================
    // PHƯƠNG THỨC GHI VÀ ĐỌC FILE CSV
    // ======================================================================

    public void savePublishersToFile() {
        try (FileWriter fw = new FileWriter(DATA_FILE, false); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            for (int i = 0; i < publisherCount; i++) {
                bw.write(publishers[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + publisherCount + " nhà xuất bản vào file " + DATA_FILE + " thành công.");
            
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu file: " + e.getMessage());
        }
    }

    public void loadPublishersFromFile() {
        BufferedReader br = null;
        Publisher[] tempPublishers = new Publisher[0];
        int tempCount = 0;
        
        try {
            br = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            
            while ((line = br.readLine()) != null) {
                
                // Giả định Publisher.readFromCSV(line) tồn tại
                Publisher publisher = Publisher.readFromCSV(line);
                
                if (publisher != null) {
                    tempPublishers = Arrays.copyOf(tempPublishers, tempCount + 1);
                    tempPublishers[tempCount++] = publisher;
                }
            }
            
            this.publishers = tempPublishers;
            this.publisherCount = tempCount;
            
            if (tempCount > 0) {
                System.out.println("✅ Tải thành công " + tempCount + " nhà xuất bản từ file " + DATA_FILE + ".");
            }
            
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File dữ liệu " + DATA_FILE + " không tồn tại. Bắt đầu với danh sách trống.");
            this.publishers = new Publisher[0];
            this.publisherCount = 0;
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Lỗi đóng luồng đọc file: " + e.getMessage());
                }
            }
        }
    }
}