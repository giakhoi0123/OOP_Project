package model;

public class Text_Book extends Book {
    private String textType; 
    private int GradeLevel;

    public Text_Book() {
        super();
    }
    public Text_Book(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, Boolean isAvailable, int price, int quantity, String textType, int GradeLevel) {
        super(id, title, authorID, publisherID, categoryID, supplierID, isAvailable, price, quantity);
        this.textType = textType;
        this.GradeLevel = GradeLevel;
    }
    
    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập loại sách giáo khoa: ");
        textType = scanner.nextLine();
        System.out.print("Nhập cấp độ/lớp: ");
        GradeLevel = scanner.nextInt();
        scanner.nextLine();
    }

    public String getTextType() { return textType; }
    public void setTextType(String textType) { this.textType = textType; }
    public int getGradeLevel() { return GradeLevel; }
    public void setGradeLevel(int GradeLevel) { this.GradeLevel = GradeLevel; }
    
    @Override
    public String toCSV() {
        // Tổng 10 trường
        return super.toCSV() + 
               "," + textType +
               "," + GradeLevel; 
    }
    
    /**
     * Phương thức tĩnh để tạo đối tượng Text_Book từ chuỗi CSV.
     * Thứ tự fields: [Book fields (9)], textType (1), GradeLevel (1) -> TỔNG 11 TRƯỜNG
     */
    public static Text_Book readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        // Cần 11 trường (9 của Book + 2 của Text_Book)
        if (parts.length != 11) {
            System.err.println("Lỗi CSV Text_Book: Không đủ 11 trường. Dòng: " + csvLine);
            return null;
        }
        
        try {
            return new Text_Book(
                parts[0].trim(),                                     // id
                parts[1].trim(),                                     // title
                parts[2].trim(),                                     // authorID
                parts[3].trim(),                                     // publisherID
                parts[4].trim(),                                     // categoryID
                parts[5].trim(),                                     // supplierID
                Boolean.parseBoolean(parts[6].trim()),               // isAvailable
                Integer.parseInt(parts[7].trim()),                   // price (int)
                Integer.parseInt(parts[8].trim()),                   // quantity (int)
                parts[9].trim(),                                     // textType (String)
                Integer.parseInt(parts[10].trim())                   // GradeLevel (int)
            );
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi số trong CSV Text_Book: " + csvLine);
            return null;
        }
    }
    
    @Override
    public void xuatLoaiSach() {
        System.out.println("Đây là sách giáo khoa (Text Book)");
    }
    
    @Override
    public void ghiFile(String filename) {
        super.ghiFile("data/Text_Books.csv"); // Sửa đường dẫn file
    }
    
    @Override
    public String toString() {
        // 1. Lấy thông tin chung từ lớp cha (giả định Book.toString() trả về chuỗi đẹp)
        String bookInfo = super.toString(); 

        // 2. Định dạng đầu ra đẹp hơn cho lớp Text_Book
        return String.format(
            "| Loại sách: Sách Giáo Khoa (Text Book)\n" +
            "------------------------------------\n" + 
            "%s" + // Thông tin chung của Book (ID, Title, Price,...)
            "| Loại SGK: %s\n" +
            "| Cấp độ/Lớp: %d\n" +
            "------------------------------------",
            bookInfo, textType, GradeLevel
        );
    }
}