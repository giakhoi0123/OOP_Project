package model;

import java.util.Date;

public class LoanTicket {
    private String ticketId;
    private Reader reader;
    private Book book;
    private Date loanDate;

    public LoanTicket(String ticketId, Reader reader, Book book, Date loanDate) {
        this.ticketId = ticketId;
        this.reader = reader;
        this.book = book;
        this.loanDate = loanDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public String toString() {
        return "LoanTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", reader=" + reader +
                ", book=" + book +
                ", loanDate=" + loanDate +
                '}';
    }
}