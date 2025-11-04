# 🚀 HƯỚNG DẪN CHẠY DỰ ÁN LIBRARY MANAGEMENT SYSTEM

## 📋 YÊU CẦU HỆ THỐNG

- ☕ Java JDK 8 trở lên
- 💻 Terminal/Command Prompt
- 📝 Text Editor hoặc IDE (VS Code, IntelliJ IDEA...)

## 🔧 CÁC BƯỚC CHẠY

### Bước 1: Mở Terminal

```bash
cd /Users/phamgiakhoi/OOP_Project/LibraryManagementSystem
```

### Bước 2: Compile dự án

```bash
javac -encoding UTF-8 model/*.java service/*.java manager/*.java main/*.java
```

**Kết quả mong đợi:** Không có lỗi compile

### Bước 3: Chạy chương trình

```bash
java -cp . main.Main
```

## 🎮 HƯỚNG DẪN SỬ DỤNG

### Menu Chính

Khi chạy chương trình, bạn sẽ thấy menu:

```
===== LIBRARY MANAGEMENT SYSTEM =====
1. Quản lý Tác giả (Authors)
2. Quản lý Sách (Books)
3. Quản lý Độc giả (Readers)
4. Quản lý Mượn Sách (Borrowing)
5. Quản lý Trả Sách (Returning)
6. Quản lý Nhà Xuất Bản (Publishers)
7. Quản lý Quy tắc Phạt (Penalty Rules)
8. Quản lý Đơn Hàng (Orders)
9. Quản lý Thể loại (Categories)
10. Quản lý Nhân Viên (Staff)
11. Quản lý Nhà Cung Cấp (Suppliers)
12. Quản lý Phiếu Phạt (Ticket Fines)
0. Thoát
```

### Cách sử dụng:

1. **Nhập số** tương ứng với chức năng muốn sử dụng
2. **Enter** để xác nhận
3. Làm theo hướng dẫn trên màn hình
4. Chọn **0** để quay lại menu chính

## 📚 DEMO CÁC CHỨC NĂNG CHÍNH

### 1. Xem thống kê sách

```
Chọn: 2 (Quản lý Sách)
→ Chọn: 5 (Hiển thị thống kê nhanh)
```

Kết quả: Hiển thị thống kê theo loại sách, tồn kho

### 2. Xem danh sách tác giả

```
Chọn: 1 (Quản lý Tác giả)
→ Chọn: 4 (Hiển thị tất cả tác giả)
```

### 3. Thống kê phiếu phạt

```
Chọn: 12 (Quản lý Phiếu Phạt)
→ Chọn: 4 (Thống kê phiếu phạt)
```

### 4. Thống kê đơn hàng

```
Chọn: 8 (Quản lý Đơn Hàng)
→ Chọn: 8 (Thống kê)
```

## 🗂️ CẤU TRÚC THỨ MỤC

```
LibraryManagementSystem/
├── data/                  # Dữ liệu CSV
│   ├── authors.csv
│   ├── books_data.csv
│   ├── categories.csv
│   ├── publishers.csv
│   ├── staff.csv
│   ├── readers.csv
│   ├── PenaltyRule.csv
│   ├── orders.csv
│   ├── orders_details.csv
│   ├── suppliers.csv
│   ├── borrow.csv
│   ├── borrow_details.csv
│   ├── return.csv
│   ├── return_details.csv
│   └── TicketFine.csv
│
├── model/                 # 18 Model classes
│   ├── Author.java
│   ├── Book.java
│   ├── Novel.java
│   ├── Text_Book.java
│   └── ...
│
├── service/               # 13 Services + 6 Interfaces
│   ├── AuthorService.java
│   ├── BookService.java
│   ├── IBookService.java
│   └── ...
│
├── manager/               # 13 Manager classes
│   ├── AuthorManager.java
│   ├── BookManager.java
│   ├── LibraryManager.java
│   └── ...
│
└── main/
    └── Main.java         # Entry point
```

## ⚠️ LƯU Ý QUAN TRỌNG

### 1. Encoding UTF-8

Luôn compile với flag `-encoding UTF-8` để hỗ trợ tiếng Việt:

```bash
javac -encoding UTF-8 model/*.java service/*.java manager/*.java main/*.java
```

### 2. ClassPath

Khi chạy, phải ở trong thư mục `LibraryManagementSystem` và dùng `-cp .`:

```bash
java -cp . main.Main
```

### 3. Dữ liệu

- File CSV nằm trong thư mục `data/`
- Tự động load khi khởi động
- Tự động save sau mỗi thao tác CRUD

### 4. Thoát chương trình

- Luôn chọn **0** để thoát đúng cách
- Không dùng Ctrl+C (có thể mất dữ liệu chưa lưu)

## 🐛 XỬ LÝ LỖI THƯỜNG GẶP

### Lỗi: "Could not find or load main class main.Main"

**Nguyên nhân:** Chạy sai classpath

**Giải pháp:**
```bash
# Đảm bảo bạn đang ở thư mục LibraryManagementSystem
cd /Users/phamgiakhoi/OOP_Project/LibraryManagementSystem
# Chạy với -cp .
java -cp . main.Main
```

### Lỗi: "unmappable character for encoding UTF-8"

**Nguyên nhân:** Không compile với encoding UTF-8

**Giải pháp:**
```bash
javac -encoding UTF-8 model/*.java service/*.java manager/*.java main/*.java
```

### Lỗi: "NoSuchElementException"

**Nguyên nhân:** Input không đúng định dạng

**Giải pháp:** Nhập đúng số, không nhập chữ

### Lỗi: "File not found"

**Nguyên nhân:** Thư mục `data/` không tồn tại

**Giải pháp:** Tạo thư mục `data/` và các file CSV

## 📊 KIỂM TRA DỰ ÁN HOÀN CHỈNH

### Checklist trước khi demo:

- [ ] ✅ Compile không có lỗi
- [ ] ✅ Chạy được chương trình
- [ ] ✅ Load dữ liệu thành công (20+ items/file)
- [ ] ✅ Menu hiển thị đầy đủ 12 chức năng
- [ ] ✅ Test ít nhất 3-4 chức năng chính
- [ ] ✅ Thống kê hiển thị đúng
- [ ] ✅ Thoát chương trình không lỗi

## 🎯 DEMO CHO GIÁO VIÊN

### Kịch bản demo (5-10 phút):

1. **Khởi động** (30s)
   ```bash
   javac -encoding UTF-8 model/*.java service/*.java manager/*.java main/*.java
   java -cp . main.Main
   ```

2. **Giới thiệu kiến trúc** (1 phút)
   - Model-Service-Manager-Main
   - SOLID principles
   - Interface riêng file

3. **Demo chức năng Quản lý Sách** (2 phút)
   - Chọn 2 (Quản lý Sách)
   - Xem thống kê (option 5)
   - Giải thích: polymorphism (Novel, Text_Book, NormalBook)

4. **Demo chức năng Thống kê** (2 phút)
   - Chọn 12 (Phiếu Phạt)
   - Thống kê phạt theo quý
   - Giải thích: method overloading (có/không tham số)

5. **Demo tính năng nâng cao** (2 phút)
   - Chọn 8 (Đơn Hàng)
   - Thống kê doanh thu
   - Giải thích: tự động cập nhật số lượng sách

6. **Tổng kết** (1 phút)
   - 45 classes
   - 12 modules
   - SOLID, Design Pattern
   - Memory optimization

## 💡 TIPS

- 🎨 Menu có emoji đẹp
- 📊 Thống kê có format table
- 💾 Tự động save
- 🔄 Hỗ trợ tiếng Việt đầy đủ
- ⚡ Performance tốt (dùng mảng)

## 📞 HỖ TRỢ

Nếu gặp vấn đề, kiểm tra:
1. Java version: `java -version`
2. Đường dẫn file: `pwd`
3. File có tồn tại: `ls data/`
4. Encoding terminal: UTF-8

---

✅ **Chúc bạn demo thành công!** 🎉
