package service;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import model.Borrow;
import model.BorrowTicket; 

public class BorrowService {
    private Borrow[] danhSach = new Borrow[0]; 
    private int soLuong = 0;
    private final String filePath = "data/borrow.csv";
    private final Scanner scanner = new Scanner(System.in);
    
    // THÊM THAM CHIẾU ĐẾN SERVICE CHI TIẾT
    private final BorrowTicketService borrowTicketService; 
    
    // THÊM THAM CHIẾU ĐẾN BookService ĐỂ CẬP NHẬT SỐ LƯỢNG
    private BookService bookService;

    public BorrowService() {
        // Khởi tạo Service chi tiết ngay
        this.borrowTicketService = new BorrowTicketService(); 
        docTuFile(); // Tải dữ liệu khi khởi tạo
    }
    
    /**
     * Thiết lập BookService để cập nhật số lượng sách khi mượn.
     * @param bookService Instance của BookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
        // Truyền BookService xuống cho BorrowTicketService
        this.borrowTicketService.setBookService(bookService);
    }
    
    /**
     * Lấy BorrowTicketService để sử dụng cho các Service khác.
     * @return BorrowTicketService instance
     */
    public BorrowTicketService getBorrowTicketService() {
        return this.borrowTicketService;
    }

    /**
     * Phương thức nhập thông tin Borrow từ người dùng và thêm vào danh sách.
     */
    public void addBorrow() {
        Borrow b = new Borrow();
        System.out.println("--- Nhập phiếu mượn mới ---");
    
        // 1. Nhập thông tin chung của phiếu mượn
        b.nhap(); // Gọi hàm nhap() đã được thêm vào Borrow.java

        // 2. Thêm vào danh sách và lưu
        them(b);
        
        // 3. THÊM LOGIC NHẬP CHI TIẾT SÁCH MƯỢN (Gọi Service khác)
        borrowTicketService.nhapChiTiet(b.getBorrowId());
    }
    
    /**
     * Thêm một phiếu mượn mới vào danh sách và tự động lưu.
     */
    public void them(Borrow b) {
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = b;
        
        System.out.println("✅ Thêm phiếu mượn thành công. Đang lưu...");
        ghiVaoFile(); // Tự động ghi lại toàn bộ danh sách
    }

    public boolean capNhatNgayTra(String borrowId, LocalDate actuallyReturnDate) {
        Borrow b = findBorrowById(borrowId);
        if (b != null && !b.daTra()) {
            b.setActuallyReturnDate(actuallyReturnDate);
            
            ghiVaoFile(); 
            System.out.println("✅ Cập nhật ngày trả cho phiếu " + borrowId + " thành công.");
            return true;
        }
        System.out.println("❌ Không tìm thấy phiếu hoặc phiếu đã được trả.");
        return false;
    }

    /**
     * Xóa phiếu mượn theo ID và tự động lưu.
     */
    public boolean xoaBorrow(String borrowId) {
        int indexToRemove = -1;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getBorrowId().equalsIgnoreCase(borrowId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            // XÓA CHI TIẾT TRƯỚC
            borrowTicketService.xoaChiTietTheoBorrowId(borrowId); 
            
            // Dịch chuyển và cắt bớt mảng
            int numMoved = soLuong - indexToRemove - 1;
            if (numMoved > 0) {
                System.arraycopy(danhSach, indexToRemove + 1, danhSach, indexToRemove, numMoved);
            }
            soLuong--;
            danhSach = Arrays.copyOf(danhSach, soLuong);
            
            ghiVaoFile();
            System.out.println("✅ Xóa phiếu mượn " + borrowId + " thành công.");
            return true;
        }
        System.out.println("❌ Không tìm thấy phiếu mượn với ID: " + borrowId);
        return false;
    }
    
    public void deleteBorrow() {
        System.out.print("Nhập ID phiếu mượn cần xóa: ");
        String borrowId = scanner.nextLine();
        xoaBorrow(borrowId);
    }   

    // ----------------------------------------------------------------
    // PHƯƠNG THỨC GHI VÀ ĐỌC FILE CSV
    // ----------------------------------------------------------------

    /**
     * Đọc toàn bộ danh sách phiếu mượn từ file CSV.
     */
    public void docTuFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            this.danhSach = new Borrow[0];
            this.soLuong = 0;
            System.out.println("ℹ️ File dữ liệu mượn sách không tồn tại. Khởi tạo danh sách trống.");
            return;
        }
        
        Borrow[] temp = new Borrow[0];
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                // Gọi hàm tĩnh readFromCSV() của Borrow
                Borrow b = Borrow.readFromCSV(line); 

                if (b != null) {
                    temp = Arrays.copyOf(temp, count + 1);
                    temp[count++] = b;
                }
            }
            
            this.danhSach = temp;
            this.soLuong = count;
            if (soLuong > 0) {
                System.out.println("✅ Tải thành công " + soLuong + " phiếu mượn từ file " + filePath + ".");
            }

        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file: " + e.getMessage());
            this.danhSach = new Borrow[0];
            this.soLuong = 0;
        }
    }

    /**
     * Ghi toàn bộ danh sách hiện tại vào file CSV (GHI ĐÈ dữ liệu cũ).
     */
    public void ghiVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { 
            for (int i = 0; i < soLuong; i++) {
                bw.write(danhSach[i].toCSV()); 
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // PHƯƠNG THỨC TÌM KIẾM VÀ BÁO CÁO (Sửa logic hiển thị chi tiết)
    // ----------------------------------------------------------------
    
    public Borrow[] sachDangMuon() {
        Borrow[] temp = new Borrow[soLuong];
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (!danhSach[i].daTra()) temp[count++] = danhSach[i];
        }
        return Arrays.copyOf(temp, count);
    }

    public Borrow[] sachQuaHan(LocalDate homNay) {
        Borrow[] temp = new Borrow[soLuong];
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].quaHan(homNay)) temp[count++] = danhSach[i];
        }
        return Arrays.copyOf(temp, count);
    }

    public Borrow[] getAll() {
        return Arrays.copyOf(danhSach, soLuong);
    }

    public Borrow findBorrowById(String borrowId) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getBorrowId().equalsIgnoreCase(borrowId)) {
                return danhSach[i];
            }
        }
        return null;
    }

    public void findBorrowById() {
        System.out.print("Nhập ID phiếu mượn cần tìm: ");
        String borrowId = scanner.nextLine();
        Borrow b = findBorrowById(borrowId);
        if (b != null) {
            System.out.println("✅ Tìm thấy phiếu mượn:");
            System.out.println(b);
            
            // HIỂN THỊ CHI TIẾT SÁCH MƯỢN
            BorrowTicket[] tickets = borrowTicketService.getChiTietByBorrowId(borrowId);
            if (tickets.length > 0) {
                System.out.println("--- Chi tiết sách mượn ---");
                for (BorrowTicket ticket : tickets) {
                    System.out.println(ticket);
                }
            } else {
                 System.out.println("--- Chi tiết sách mượn (Không có) ---");
            }
        } else {
            System.out.println("❌ Không tìm thấy phiếu mượn với ID: " + borrowId);
        }
    }

    // ... (Các hàm khác giữ nguyên) ...

    public void updateBorrow() {
        System.out.print("Nhập ID phiếu mượn cần cập nhật ngày trả: ");
        String borrowId = scanner.nextLine();
        System.out.print("Nhập ngày trả thực tế (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            LocalDate actuallyReturnDate = LocalDate.parse(dateStr);
            capNhatNgayTra(borrowId, actuallyReturnDate);
        } catch (Exception e) {
            System.out.println("❌ Định dạng ngày không hợp lệ.");
        }
    }

    public void updateAndSaveAll() {
        ghiVaoFile(); 
        borrowTicketService.ghiVaoFile(); // Lưu cả chi tiết
        System.out.println("✅ Cập nhật và lưu toàn bộ danh sách mượn (cả chi tiết) thành công.");
    }
    
    public void updateQuantity() {
        System.out.println("Tổng số phiếu mượn hiện có: " + soLuong);
        System.out.println("Tổng số chi tiết sách mượn hiện có: " + borrowTicketService.soLuong);
    }
}