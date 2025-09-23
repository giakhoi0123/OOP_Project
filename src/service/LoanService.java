// src/service/LoanService.java
package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Book;
import model.LoanTicket;
import model.Reader;

public class LoanService {
    private List<LoanTicket> loanTickets = new ArrayList<>();

    // Cấp phiếu mượn sách
    public void issueLoan() {
        // Tạo phiếu mượn mẫu (có thể thay bằng nhập từ người dùng)
        Reader reader = new Reader("user1", "password1", "R001");
        Book book = new Book("1", "Java Programming", null, null, null);
        LoanTicket loanTicket = new LoanTicket("LT001", reader, book, new Date());
        loanTickets.add(loanTicket);
        System.out.println("Đã cấp phiếu mượn sách: " + loanTicket.getTicketId());
    }

    // Xử lý trả sách
    public void processReturn() {
        if (loanTickets.isEmpty()) {
            System.out.println("Không có phiếu mượn nào để xử lý trả sách.");
            return;
        }
        LoanTicket ticket = loanTickets.remove(0); // Xử lý phiếu mượn đầu tiên (chỉ là ví dụ)
        System.out.println("Đã xử lý trả sách cho phiếu: " + ticket.getTicketId());
    }
    // Lấy số lượng phiếu mượn
    public int getTotalLoans() {
        return loanTickets.size();
    }
}