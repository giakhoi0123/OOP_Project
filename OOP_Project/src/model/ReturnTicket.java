// src/model/ReturnTicket.java
package model;

import java.util.Date;

public class ReturnTicket {
    private String ticketId;
    private LoanTicket loanTicket;
    private Date returnDate;

    public ReturnTicket(String ticketId, LoanTicket loanTicket, Date returnDate) {
        this.ticketId = ticketId;
        this.loanTicket = loanTicket;
        this.returnDate = returnDate;
    }

    // Getters, setters, toString()
}