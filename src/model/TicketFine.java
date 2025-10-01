package model;

public class TicketFine {
    private String ticketFineId;
    private double fineAmount;
    private String reason;
    private String dateIssued;
    private String ruleId;

    public TicketFine() {
    }
    public TicketFine(String ticketFineId, double fineAmount, String reason, String dateIssued, String ruleId) {
        this.ticketFineId = ticketFineId;
        this.fineAmount = fineAmount;
        this.reason = reason;
        this.dateIssued = dateIssued;
        this.ruleId = ruleId;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã vé phạt: ");
        ticketFineId = scanner.nextLine();
        System.out.print("Nhập số tiền phạt: ");
        fineAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập lý do phạt: ");
        reason = scanner.nextLine();
        System.out.print("Nhập ngày phát hành (YYYY-MM-DD): ");
        dateIssued = scanner.nextLine();
        System.out.print("Nhập mã quy định: ");
        ruleId = scanner.nextLine();
    }
    public String getTicketFineId() {
        return ticketFineId;
    }
    public void setTicketFineId(String ticketFineId) {
        this.ticketFineId = ticketFineId;
    }
    public double getFineAmount() {
        return fineAmount;
    }
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }
    public String getRuleId() {
        return ruleId;
    }
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
    @Override
    public String toString() {
        return "TicketFine{ticketFineId='" + ticketFineId + "', fineAmount='" + fineAmount + "', reason='" + reason + "', dateIssued='" + dateIssued + "', ruleId='" + ruleId + "'}";
    }

}
