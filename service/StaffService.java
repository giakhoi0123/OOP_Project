package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import model.Staff;

public class StaffService implements IStaffService {
    // Mảng động ban đầu là mảng rỗng
    private Staff[] staffs = new Staff[0];
    private int count = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final String DATA_FILE = "data/staff.csv";

    public StaffService() {
        loadStaffsFromFile(); // TẢI DỮ LIỆU KHI KHỞI TẠO
    }
    
    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC QUẢN LÝ (SỬ DỤNG MẢNG ĐỘNG VÀ TỰ ĐỘNG LƯU)
    // ----------------------------------------------------------------------

    public void addStaff(Staff staff) {
        // Tăng kích thước mảng lên 1 bằng Arrays.copyOf
        staffs = Arrays.copyOf(staffs, count + 1);
        staffs[count++] = staff;
        System.out.println("✅ Đã thêm nhân viên thành công! Đang lưu...");
        saveStaffsToFile(); // TỰ ĐỘNG LƯU SAU KHI THÊM
    }
    
    @Override
    public void addStaff() {
        System.out.println("\n--- Thêm Nhân Viên Mới ---");
        Staff newStaff = new Staff();
        newStaff.nhap(); // Giả định Staff.nhap() không đóng System.in
        addStaff(newStaff);
    }

    @Override
    public Staff getStaffById(String staffId) {
        for (int i = 0; i < count; i++) {
            if (staffs[i].getStaffId().equalsIgnoreCase(staffId)) {
                return staffs[i];
            }
        }
        return null;
    }

    @Override
    public void findAndShowStaffById() {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        String staffId = scanner.nextLine().trim();
        Staff staff = getStaffById(staffId);
        if (staff != null) {
            System.out.println("🔎 Thông tin nhân viên tìm thấy:");
            System.out.println(staff);
        } else {
            System.out.println("❌ Không tìm thấy nhân viên với ID: " + staffId);
        }
    }

    public boolean updateStaffById(String staffId) {
        Staff staffToUpdate = getStaffById(staffId);
        if (staffToUpdate != null) {
            System.out.println("📝 Tìm thấy nhân viên. Vui lòng nhập thông tin mới:");
            staffToUpdate.nhap(); // Gọi hàm nhập để cập nhật thông tin
            staffToUpdate.setStaffId(staffId); // Đảm bảo ID không bị thay đổi
            System.out.println("✅ Cập nhật thông tin thành công! Đang lưu...");
            saveStaffsToFile(); // TỰ ĐỘNG LƯU SAU KHI SỬA
            return true;
        }
        return false;
    }

    @Override
    public void updateStaff() {
        System.out.print("Nhập ID nhân viên cần sửa: ");
        String staffId = scanner.nextLine().trim();
        if (!updateStaffById(staffId)) {
            System.out.println("❌ Không tìm thấy nhân viên với ID: " + staffId + " để cập nhật.");
        }
    }

    public boolean deleteStaffById(String staffId) {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (staffs[i].getStaffId().equalsIgnoreCase(staffId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
             // Dịch chuyển các phần tử sau vị trí xóa
            for (int j = indexToRemove; j < count - 1; j++) {
                staffs[j] = staffs[j + 1];
            }
            count--;
            // Cắt bớt mảng
            staffs = Arrays.copyOf(staffs, count);
            return true;
        }
        return false;
    }

    @Override
    public void deleteStaff() {
        if (count == 0) {
            System.out.println("📭 Danh sách trống, không có ai để xóa!");
            return;
        }

        System.out.print("\nNhập ID nhân viên cần xóa: ");
        String staffId = scanner.nextLine().trim();

        if (deleteStaffById(staffId)) {
            System.out.println("🗑️ Đã xóa nhân viên có ID: " + staffId + ". Đang lưu...");
            saveStaffsToFile(); // TỰ ĐỘNG LƯU SAU KHI XÓA
        } else {
            System.out.println("❌ Không tìm thấy nhân viên với ID: " + staffId);
        }
    }

    @Override
    public void showAllStaff() {
        if (count == 0) {
            System.out.println("📭 Chưa có nhân viên nào trong danh sách!");
            return;
        }

        System.out.println("\n👥 ===== DANH SÁCH NHÂN VIÊN (" + count + " người) =====");
        for (int i = 0; i < count; i++) {
            // Giả định staffs[i].toString() tồn tại
            System.out.println(staffs[i]);
            System.out.println("--------------------");
        }
    }

    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC GHI VÀ ĐỌC FILE CSV
    // ----------------------------------------------------------------------

    @Override
    public void saveStaffsToFile() {
        // Ghi ĐÈ file (overwrite = false)
        try (FileWriter fw = new FileWriter(DATA_FILE, false); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            for (int i = 0; i < count; i++) {
                // Giả định staffs[i].toCSV() tồn tại
                bw.write(staffs[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + count + " nhân viên vào file " + DATA_FILE + " thành công.");
            
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void loadStaffsFromFile() {
        BufferedReader br = null;
        Staff[] tempStaffs = new Staff[0];
        int tempCount = 0;
        
        try {
            br = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            
            while ((line = br.readLine()) != null) {
                
                // Giả định Staff.readFromCSV(line) tồn tại
                Staff staff = Staff.readFromCSV(line);
                
                if (staff != null) {
                    tempStaffs = Arrays.copyOf(tempStaffs, tempCount + 1);
                    tempStaffs[tempCount++] = staff;
                }
            }
            
            // Cập nhật mảng chính
            this.staffs = tempStaffs;
            this.count = tempCount;
            
            if (tempCount > 0) {
                System.out.println("✅ Tải thành công " + tempCount + " nhân viên từ file " + DATA_FILE + ".");
            }
            
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File dữ liệu " + DATA_FILE + " không tồn tại. Bắt đầu với danh sách trống.");
            this.staffs = new Staff[0];
            this.count = 0;
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file: " + e.getMessage());
        } finally {
            // Đảm bảo đóng BufferedReader
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