package model; 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 

public class Author {
    private String authorId;
    private String name;
    private String address;
    private String phone;

    public Author() {
    }

    public Author(String authorId, String name, String address, String phone) {
        this.authorId = authorId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Author(Author a){
        this.authorId = a.authorId;
        this.name = a.name;
        this.address = a.address;
        this.phone = a.phone;
    }
    
    /**
     * Nhập thông tin tác giả từ bàn phím.
     */
    public void nhap() {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Nhập mã tác giả (Author ID): ");
        authorId = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        name = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        phone = scanner.nextLine();
    }
    
    // ----------------------------------------------------------------------
    // GETTERS VÀ SETTERS
    // ----------------------------------------------------------------------
    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC XỬ LÝ CSV
    // ----------------------------------------------------------------------

    /**
     * Chuyển đổi đối tượng Author thành chuỗi CSV.
     */
    public String toCSV() {
        return authorId + "," + name + "," + address + "," + phone;
    }

    /**
     * Phương thức tĩnh để tạo một Author object từ một dòng CSV.
     */
    public static Author readFromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        
        if (parts.length != 4) {
            System.err.println("❌ Lỗi định dạng CSV cho Tác giả: Dòng không đủ 4 trường. Dòng: " + csvLine);
            return null;
        }

        // Tạo đối tượng Author mới, sử dụng trim()
        return new Author(
            parts[0].trim(), // authorId
            parts[1].trim(), // name
            parts[2].trim(), // address
            parts[3].trim()  // phone
        );
    }

    /**
     * Ghi đối tượng Author vào file CSV ở chế độ ghi tiếp (append). 
     */
    public void ghiFile() {
        String fileName = "data/authors.csv"; // <-- Sửa đường dẫn file về data/
        try (FileWriter fw = new FileWriter(fileName, true); 
            BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(this.toCSV());
            bw.newLine();
            
        } 
        catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file " + fileName + ": " + e.getMessage()); 
        }
    }

    @Override
    public String toString() {
        return String.format(
            "| Mã Tác giả: %s | Tên: %s\n" +
            "| Địa chỉ: %s | SĐT: %s",
            authorId, name, address, phone
        );
    }
}