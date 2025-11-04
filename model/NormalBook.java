package model;

public class NormalBook extends Book {
    private String coverType;
    private int pageCount;

    // ... (Constructors) ...
    public NormalBook() {
        super();
    }

    // Constructor khi nhập thông thường (Giả định giá bán lẻ = giá nhập ban đầu)
    public NormalBook(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, boolean isAvailable,
                      String coverType, int pageCount, int bookPrice) { 
        super(id, title, authorID, publisherID, categoryID, supplierID, isAvailable, bookPrice, 0);
        this.coverType = coverType;
        this.pageCount = pageCount;
    }
    
    // Constructor dùng khi đọc CSV
    public NormalBook(String id, String title, String authorID, String publisherID, String categoryID, String supplierID, boolean isAvailable, int bookPrice, int quantity,
                      String coverType, int pageCount) { 
        super(id, title, authorID, publisherID, categoryID, supplierID, isAvailable, bookPrice, quantity);
        this.coverType = coverType;
        this.pageCount = pageCount;
    }


    @Override
    public void nhap() {
        super.nhap();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập loại bìa (mềm/cứng): ");
        coverType = scanner.nextLine();
        System.out.print("Nhập số trang: ");
        pageCount = scanner.nextInt();
        scanner.nextLine();
    }

    // --- Getters cho các thuộc tính riêng ---
    public String getCoverType() { return coverType; }
    public int getPageCount() { return pageCount; }

    @Override
    public void xuatLoaiSach() {
        System.out.println("→ Đây là Sách thường (Normal Book)");
    }
    
    @Override
    public String toCSV() {
        // Thứ tự CSV: [Book fields (8)], coverType, pageCount, price(double)
        return super.toCSV() + 
               "," + coverType + 
               "," + pageCount;
    }

    /**
     * Phương thức tĩnh để tạo đối tượng NormalBook từ chuỗi CSV.
     * Thứ tự fields: [Book fields (9)], coverType (1), pageCount (1) -> Tổng 11 trường
     */
    public static NormalBook readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        // Cần 11 trường (9 của Book + 2 của NormalBook)
        if (parts.length != 11) {
            System.err.println("Lỗi CSV NormalBook: Không đủ 11 trường. Dòng: " + csvLine);
            return null;
        }

        try {
            return new NormalBook(
                parts[0].trim(),                                     // id
                parts[1].trim(),                                     // title
                parts[2].trim(),                                     // authorID
                parts[3].trim(),                                     // publisherID
                parts[4].trim(),                                     // categoryID
                parts[5].trim(),                                     // supplierID
                Boolean.parseBoolean(parts[6].trim()),               // isAvailable
                Integer.parseInt(parts[7].trim()),                   // price (int)
                Integer.parseInt(parts[8].trim()),                   // quantity (int)
                parts[9].trim(),                                     // coverType
                Integer.parseInt(parts[10].trim())                   // pageCount
            );
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi số trong CSV NormalBook: " + csvLine);
            return null;
        }
    }
    
    @Override
    public void ghiFile(String filename) {
        super.ghiFile("data/NormalBooks.csv"); // Sửa đường dẫn file về data/
    }

    @Override
    public String toString() {
        // Lấy thông tin chung từ lớp cha (giả định Book.toString() trả về chuỗi đẹp)
        String bookInfo = super.toString(); 

        // Định dạng đầu ra đẹp hơn cho lớp NormalBook
        return String.format(
            "| Loại sách: Sách Thường (Normal Book)\n" +
            "------------------------------------\n" + 
            "%s" + // Thông tin chung của Book (ID, Title, Price,...)
            "| Loại bìa: %s\n" +
            "| Số trang: %d\n" +
            "------------------------------------",
            bookInfo, coverType, pageCount
        );
    }
}