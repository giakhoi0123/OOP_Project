package model;

public class Novel extends Book {
    String genre;
    public Novel() {
        super();
    }
    public Novel(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, Boolean isAvailable, String genre) {
        super(id, title, authorID, publisherID, categoryID,supplierID,isAvailable);
        this.genre = genre;
    }
    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập thể loại tiểu thuyết: ");
        genre = scanner.nextLine();
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "Novel{" + super.toString() + ", genre='" + genre + "'}";
    }
}
