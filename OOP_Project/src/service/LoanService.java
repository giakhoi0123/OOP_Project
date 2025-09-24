// src/service/LoanService.java
package service;

import java.util.Date;
import model.Book;
import model.LoanTicket;
import model.Reader;

interface ILoanService {
    void issueLoan();
    void processReturn();
    int getTotalLoans();
}

public class LoanService implements ILoanService {
    private LoanTicket[] loanTickets = new LoanTicket[100]; // Giả sử tối đa 100 phiếu
    private int count = 0; // Số lượng phiếu hiện tại

    // Cấp phiếu mượn sách
    public void issueLoan() {
        if (count >= loanTickets.length) {
            System.out.println("Danh sách phiếu mượn đã đầy, không thể cấp thêm.");
            return;
        }

        // Tạo phiếu mượn mẫu (có thể thay bằng nhập từ người dùng)
        Reader reader = new Reader("user1", "password1", "R001");
        Book book = new Book("1", "Java Programming", null, null, null);
        LoanTicket loanTicket = new LoanTicket("LT00" + (count + 1), reader, book, new Date());

        loanTickets[count] = loanTicket;
        count++;

        System.out.println("Đã cấp phiếu mượn sách: " + loanTicket.getTicketId());
    }

    // Xử lý trả sách (xóa phiếu mượn đầu tiên)
    public void processReturn() {
        if (count == 0) {
            System.out.println("Không có phiếu mượn nào để xử lý trả sách.");
            return;
        }

        LoanTicket ticket = loanTickets[0];
        // Dời tất cả phần tử còn lại về trước 1 vị trí
        for (int i = 1; i < count; i++) {
            loanTickets[i - 1] = loanTickets[i];
        }
        loanTickets[count - 1] = null;
        count--;

        System.out.println("Đã xử lý trả sách cho phiếu: " + ticket.getTicketId());
    }

    // Lấy số lượng phiếu mượn
    public int getTotalLoans() {
        return count;
    }
}
