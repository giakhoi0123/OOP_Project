package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReturnDetail {
    private String bookId;
    private String ReturnId;
    private String ReasonId;

    public ReturnDetail(){
        bookId = "";
        ReturnId = "";
        ReasonId = "";
    }

    public ReturnDetail(String bookId, String ReturnId, String ReasonId){
        this.bookId = bookId;
        this.ReturnId = ReturnId;
        this.ReasonId = ReasonId;
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book ID: ");
        bookId = sc.nextLine();
        // Return ID được gán tự động từ Return.input()
        System.out.print("Enter reason ID (PenaltyRule ID): ");
        ReasonId = sc.nextLine();
    }

    public void output(){
        System.out.println("Book ID: " + bookId + " || Return ID: " + ReturnId + " || Reason ID: " + ReasonId);
    }
    public ReturnDetail(ReturnDetail rd){
        this.bookId = rd.bookId;
        this.ReturnId = rd.ReturnId;
        this.ReasonId = rd.ReasonId;
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getReturnId() { return ReturnId; }
    public void setReturnId(String returnId) { ReturnId = returnId; }
    public String getReasonId() { return ReasonId; }
    public void setReasonId(String reasonId) { ReasonId = reasonId; }

    public String toCSV() {
        return bookId + "," + ReturnId + "," + ReasonId;
    }
    
    public static ReturnDetail readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 3) return null;
        
        return new ReturnDetail(
            parts[0].trim(), // bookId
            parts[1].trim(), // ReturnId
            parts[2].trim()  // ReasonId
        );
    }

    public void ghiFile() {
        String filename = "return_details.csv";

        try (FileWriter fw = new FileWriter(filename, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());

        }
    }
    
    @Override
    public String toString() {
        return toCSV();
    }
}