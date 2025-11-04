package model;

public class Novel extends Book {
    private String genre;
    private String language;

    public Novel() {
        super();
    }
    public Novel(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, Boolean isAvailable, int price, int quantity, String genre, String language) {
        super(id, title, authorID, publisherID, categoryID, supplierID, isAvailable, price, quantity);
        this.genre = genre;
        this.language = language;
    }
    
    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập thể loại tiểu thuyết: ");
        genre = scanner.nextLine();
        System.out.print("Nhập ngôn ngữ: ");
        language = scanner.nextLine();
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    @Override
    public String toCSV() {
        // Tổng 10 trường
        return super.toCSV() + 
               "," + genre +
               "," + language; 
    }
    
    /**
     * Phương thức tĩnh để tạo đối tượng Novel từ chuỗi CSV.
     * Thứ tự fields: [Book fields (9)], genre (1), language (1) -> TỔNG 11 TRƯỜNG
     */
    public static Novel readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        // Cần 11 trường (9 của Book + 2 của Novel)
        if (parts.length != 11) {
            System.err.println("Lỗi CSV Novel: Không đủ 11 trường. Dòng: " + csvLine);
            return null;
        }
        
        try {
            return new Novel(
                parts[0].trim(),                                     // id
                parts[1].trim(),                                     // title
                parts[2].trim(),                                     // authorID
                parts[3].trim(),                                     // publisherID
                parts[4].trim(),                                     // categoryID
                parts[5].trim(),                                     // supplierID
                Boolean.parseBoolean(parts[6].trim()),               // isAvailable
                Integer.parseInt(parts[7].trim()),                   // price (int)
                Integer.parseInt(parts[8].trim()),                   // quantity (int)
                parts[9].trim(),                                     // genre (String)
                parts[10].trim()                                     // language (String)
            );
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi số trong CSV Novel: " + csvLine);
            return null;
        }
    }

    @Override
    public void ghiFile(String filename) {
        super.ghiFile("data/Novels.csv"); // Sửa đường dẫn file
    }

    @Override
    public void xuatLoaiSach() {
        System.out.println("📖 Đây là Tiểu thuyết (Novel)");
    }
    
    @Override
    public String toString() {
        // Tái định dạng đầu ra để đẹp hơn
        String bookInfo = super.toString(); // Giả định Book.toString() trả về chuỗi đẹp
        
        return String.format(
            "| Loại sách: Tiểu thuyết (Novel)\n" +
            "%s" + // Thông tin chung của Book
            "| Thể loại: %s\n" +
            "| Ngôn ngữ: %s\n" +
            "------------------------------------",
            bookInfo, genre, language
        );
    }
}