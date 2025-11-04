package service;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import model.BorrowTicket;

public class BorrowTicketService {
    private BorrowTicket[] danhSach = new BorrowTicket[0]; 
    public int soLuong = 0;
    private final String filePath = "data/borrow_details.csv"; 
    private final Scanner sc = new Scanner(System.in);
    // THÊM THAM CHIẾU ĐẾN BookService ĐỂ CẬP NHẬT SỐ LƯỢNG
    private BookService bookService;

    public BorrowTicketService() {
        docTuFile();
    }
    
    /**
     * Thiết lập BookService để cập nhật số lượng sách khi mượn.
     * @param bookService Instance của BookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    // --- FILE I/O ---

    public void docTuFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            this.danhSach = new BorrowTicket[0];
            this.soLuong = 0;
            System.out.println("ℹ️ File dữ liệu chi tiết phiếu mượn không tồn tại. Khởi tạo danh sách trống.");
            return;
        }
        
        BorrowTicket[] temp = new BorrowTicket[0];
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                BorrowTicket bt = BorrowTicket.readFromCSV(line); 
                if (bt != null) {
                    temp = Arrays.copyOf(temp, count + 1);
                    temp[count++] = bt;
                }
            }
            
            this.danhSach = temp;
            this.soLuong = count;
            if (soLuong > 0) {
                System.out.println("✅ Tải thành công " + soLuong + " chi tiết phiếu mượn từ file " + filePath + ".");
            }

        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file chi tiết phiếu mượn: " + e.getMessage());
            this.danhSach = new BorrowTicket[0];
            this.soLuong = 0;
        }
    }

    public void ghiVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { 
            for (int i = 0; i < soLuong; i++) {
                bw.write(danhSach[i].toCSV()); 
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file chi tiết phiếu mượn: " + e.getMessage());
        }
    }

    // --- LOGIC METHODS ---
    
    public void themChiTiet(BorrowTicket bt) {
        danhSach = Arrays.copyOf(danhSach, soLuong + 1);
        danhSach[soLuong++] = bt;
        ghiVaoFile(); // Tự động lưu chi tiết
    }
    
    public void nhapChiTiet(String borrowId) {
        System.out.println("  [Chi Tiết Phiếu Mượn " + borrowId + "]");
        int num = 0;
        try {
             System.out.print("  Nhập số lượng sách khác nhau muốn mượn: ");
             num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
             System.out.println("  ❌ Số lượng không hợp lệ, mặc định 1.");
             num = 1;
        }

        for (int i = 0; i < num; i++) {
             System.out.println("  --- Sách thứ " + (i + 1) + " ---");
             String ticketId = "BT" + (soLuong + 1);
             System.out.print("  Nhập Book ID: ");
             String bookId = sc.nextLine();
             System.out.print("  Nhập số lượng sách (Quantity): ");
             int quantity = sc.nextInt(); 
             sc.nextLine(); // Consume newline
             
             // KIỂM TRA SỐ LƯỢNG SÁCH CÒN TRONG KHO
             if (bookService != null) {
                 if (!bookService.checkAvailableQuantity(bookId, quantity)) {
                     System.out.println("  ❌ Không đủ số lượng sách [" + bookId + "] trong kho!");
                     System.out.println("  Số lượng có sẵn: " + bookService.getBookQuantity(bookId));
                     i--; // Lùi lại để nhập lại
                     continue;
                 }
             }
             
             BorrowTicket newTicket = new BorrowTicket(ticketId, borrowId, bookId, quantity);
             themChiTiet(newTicket);
             
             // CẬP NHẬT SỐ LƯỢNG SÁCH TRONG KHO (Giảm số lượng khi mượn)
             if (bookService != null) {
                 bookService.decreaseBookQuantity(bookId, quantity);
             }
             
             System.out.println("  ✅ Đã thêm chi tiết: " + newTicket);
        }
    }
    
    public void xoaChiTietTheoBorrowId(String borrowId) {
        BorrowTicket[] newDanhSach = new BorrowTicket[soLuong];
        int newCount = 0;
        boolean changed = false;
        
        for (int i = 0; i < soLuong; i++) {
            if (!danhSach[i].getBorrowId().equalsIgnoreCase(borrowId)) {
                newDanhSach[newCount++] = danhSach[i];
            } else {
                // HOÀN TRẢ SỐ LƯỢNG SÁCH VÀO KHO KHI XÓA CHI TIẾT MƯỢN
                if (bookService != null) {
                    bookService.increaseBookQuantity(danhSach[i].getBookId(), danhSach[i].getQuantity());
                }
                changed = true;
            }
        }
        
        if (changed) {
            this.danhSach = Arrays.copyOf(newDanhSach, newCount);
            this.soLuong = newCount;
            ghiVaoFile();
            System.out.println("  [Chi Tiết] Đã xóa thành công các bản ghi liên quan đến phiếu " + borrowId);
        }
    }
    
    public BorrowTicket[] getChiTietByBorrowId(String borrowId) {
        BorrowTicket[] result = new BorrowTicket[soLuong];
        int count = 0;
        for (int i = 0; i < soLuong; i++) {
            if (danhSach[i].getBorrowId().equalsIgnoreCase(borrowId)) {
                result[count++] = danhSach[i];
            }
        }
        return Arrays.copyOf(result, count);
    }
}