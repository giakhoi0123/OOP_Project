package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import model.Supplier;

public class SupplierService implements ISupplierService {
    // Mảng động ban đầu là mảng rỗng
    private Supplier[] suppliers = new Supplier[0]; 
    private int supplierCount = 0; 
    private final Scanner scanner = new Scanner(System.in);
    private final String DATA_FILE = "data/suppliers.csv";

    public SupplierService() {
        loadSuppliersFromFile(); // TẢI DỮ LIỆU KHI KHỞI TẠO
    }
    
    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC QUẢN LÝ (SỬ DỤNG MẢNG ĐỘNG VÀ TỰ ĐỘNG LƯU)
    // ----------------------------------------------------------------------

    public void addSupplier(Supplier supplier) {
        // Tăng kích thước mảng lên 1
        suppliers = Arrays.copyOf(suppliers, supplierCount + 1);
        
        // Thêm phần tử mới
        suppliers[supplierCount++] = supplier;
        System.out.println("✅ Đã thêm nhà cung cấp thành công! Đang lưu...");
        saveSuppliersToFile(); // TỰ ĐỘNG LƯU SAU KHI THÊM
    }
    
    @Override
    public void addSupplier() {
        System.out.println("\n--- Thêm Nhà Cung Cấp Mới ---");
        Supplier newSupplier = new Supplier();
        newSupplier.nhap(); // Giả định Supplier.nhap() không đóng System.in
        addSupplier(newSupplier);
    }

    @Override
    public Supplier getSupplierById(String supplierId) {
        for (int i = 0; i < supplierCount; i++) {
            if (suppliers[i].getSupplierId().equalsIgnoreCase(supplierId)) {
                return suppliers[i];
            }
        }
        return null;
    }

    @Override
    public void findAndShowSupplierById() {
        System.out.print("Nhập ID nhà cung cấp cần tìm: ");
        String supplierId = scanner.nextLine().trim();
        Supplier supplier = getSupplierById(supplierId);
        if (supplier != null) {
            System.out.println("🔎 Thông tin nhà cung cấp tìm thấy:");
            System.out.println(supplier);
        } else {
            System.out.println("❌ Không tìm thấy nhà cung cấp với ID: " + supplierId);
        }
    }

    public boolean updateSupplierById(String supplierId) {
        Supplier supplierToUpdate = getSupplierById(supplierId);
        if (supplierToUpdate != null) {
            System.out.println("📝 Tìm thấy NCC. Vui lòng nhập thông tin mới:");
            supplierToUpdate.nhap(); // Gọi hàm nhập để cập nhật thông tin
            supplierToUpdate.setSupplierId(supplierId); // Đảm bảo ID không bị thay đổi
            System.out.println("✅ Cập nhật thông tin NCC thành công! Đang lưu...");
            saveSuppliersToFile(); // TỰ ĐỘNG LƯU SAU KHI SỬA
            return true;
        }
        return false;
    }

    @Override
    public void updateSupplier() {
        System.out.print("Nhập ID nhà cung cấp cần sửa: ");
        String supplierId = scanner.nextLine().trim();
        if (!updateSupplierById(supplierId)) {
            System.out.println("❌ Không tìm thấy NCC với ID: " + supplierId + " để cập nhật.");
        }
    }

    public boolean deleteSupplierById(String supplierId) {
        int indexToRemove = -1;
        for (int i = 0; i < supplierCount; i++) {
            if (suppliers[i].getSupplierId().equalsIgnoreCase(supplierId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
             // Dịch chuyển các phần tử sau vị trí xóa
            for (int j = indexToRemove; j < supplierCount - 1; j++) {
                suppliers[j] = suppliers[j + 1];
            }
            supplierCount--;
            // Cắt bớt mảng
            suppliers = Arrays.copyOf(suppliers, supplierCount);
            return true;
        }
        return false;
    }

    @Override
    public void deleteSupplier() {
        if (supplierCount == 0) {
            System.out.println("📭 Danh sách trống, không có NCC để xóa!");
            return;
        }

        System.out.print("\nNhập ID nhà cung cấp cần xóa: ");
        String supplierId = scanner.nextLine().trim();

        if (deleteSupplierById(supplierId)) {
            System.out.println("🗑️ Đã xóa NCC có ID: " + supplierId + ". Đang lưu...");
            saveSuppliersToFile(); // TỰ ĐỘNG LƯU SAU KHI XÓA
        } else {
            System.out.println("❌ Không tìm thấy NCC với ID: " + supplierId);
        }
    }

    @Override
    public void showAllSuppliers() {
        if (supplierCount == 0) {
            System.out.println("📭 Chưa có nhà cung cấp nào trong danh sách!");
            return;
        }

        System.out.println("🏢 ===== DANH SÁCH NHÀ CUNG CẤP (" + supplierCount + " NCC) =====");
        for (int i = 0; i < supplierCount; i++) {
            // Giả định suppliers[i].toString() tồn tại
            System.out.println(suppliers[i]);
            System.out.println("--------------------");
        }
    }

    @Override
    public int getSupplierCount() {
        return supplierCount;
    }

    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC GHI VÀ ĐỌC FILE CSV
    // ----------------------------------------------------------------------

    @Override
    public void saveSuppliersToFile() {
        // Ghi ĐÈ file (overwrite = false)
        try (FileWriter fw = new FileWriter(DATA_FILE, false); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            for (int i = 0; i < supplierCount; i++) {
                // Giả định suppliers[i].toCSV() tồn tại
                bw.write(suppliers[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + supplierCount + " nhà cung cấp vào file " + DATA_FILE + " thành công.");
            
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @Override
    public void loadSuppliersFromFile() {
        BufferedReader br = null;
        Supplier[] tempSuppliers = new Supplier[0];
        int tempCount = 0;
        
        try {
            br = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            
            while ((line = br.readLine()) != null) {
                
                // Giả định Supplier.readFromCSV(line) tồn tại
                Supplier supplier = Supplier.readFromCSV(line);
                
                if (supplier != null) {
                    // Tăng kích thước mảng tạm thời
                    tempSuppliers = Arrays.copyOf(tempSuppliers, tempCount + 1);
                    tempSuppliers[tempCount++] = supplier;
                }
            }
            
            // Cập nhật mảng chính
            this.suppliers = tempSuppliers;
            this.supplierCount = tempCount;
            
            if (tempCount > 0) {
                System.out.println("✅ Tải thành công " + tempCount + " nhà cung cấp từ file " + DATA_FILE + ".");
            }
            
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File dữ liệu " + DATA_FILE + " không tồn tại. Bắt đầu với danh sách trống.");
            this.suppliers = new Supplier[0];
            this.supplierCount = 0;
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