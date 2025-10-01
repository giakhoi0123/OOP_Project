package model;

public class Text_Book extends Book {
    String textType;
    public Text_Book() {
        super();
    }
    public Text_Book(String id, String title, String authorID, String publisherID, String categoryID,String supllirID, Boolean isAvailable, String textType) {
        super(id, title, authorID, publisherID, categoryID,supllirID,isAvailable);
        this.textType = textType;
    }
    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập loại sách giáo khoa: ");
        textType = scanner.nextLine();
        
    }
    public String getTextType() {
        return textType;
    }
    public void setTextType(String textType) {
        this.textType = textType;
    } 
    @Override
    public String toString() {
        return "Text_Book{" + super.toString() + ", textType='" + textType + "'}";
    }
}
