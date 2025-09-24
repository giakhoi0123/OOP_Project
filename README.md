# 📚 Hệ Thống Quản Lý Thư Viện (Library Management System)

Dự án **Hệ thống Quản lý Thư viện** được xây dựng bằng **Java** theo mô hình **OOP** với cấu trúc thư mục rõ ràng, gồm các lớp **Model**, **Service** và **Main** để quản lý sách, người dùng, mượn trả và thanh toán.

---

## 🏗 Cấu trúc thư mục
<pre> 
LibraryManagementSystem/
├── src/ 
│ ├── LibraryManagementSystem/
│ │ ├── menu.java
│ ├── main/
│ │ ├── main.java
│ ├── model/
│ │ ├── Book.java
│ │ ├── Author.java
│ │ ├── Publisher.java
│ │ ├── Category.java
│ │ ├── Reader.java
│ │ ├── Staff.java
│ │ ├── Librarian.java
│ │ ├── LoanTicket.java
│ │ ├── ReturnTicket.java
│ │ ├── Inventory.java
│ │ ├── Fine.java
│ │ ├── Statistics.java
│ │ ├── Notification.java
│ │ ├── Payment.java
│ │ ├── UserAccount.java
│ ├── service/ 
│ │ ├── BookService.java
│ │ ├── ReaderService.java 
│ │ ├── LoanService.java
│ │ ├── ReturnService.java
│ │ ├── PaymentService.java
│ │ ├── NotificationService.java 
</pre>

---

## ⚙️ Tính năng chính
- **Quản lý Sách**: Thêm, xóa, tìm kiếm sách.
- **Quản lý Người Dùng**: Thêm, xóa độc giả hoặc nhân viên.
- **Quản lý Mượn/Trả**: Tạo phiếu mượn, phiếu trả.
- **Thanh toán**: Xử lý tiền phạt, xem lịch sử thanh toán.
- **Thống kê**: Số lượng sách, người dùng, sách đang mượn.
- **Thông báo**: Gửi thông báo mượn/trả sách.

---

## 🚀 Cách chạy dự án

### 1. Biên dịch tất cả các file:
```bash
cd OOP_Project
javac -d out $(find src -name "*.java")
```
### 2. Chạy file:
```bash
java main.LibraryManagementSystem
```
## Yêu cầu môi trường
Java JDK 17 trở lên
IDE: IntelliJ IDEA hoặc VSCode hoặc Eclipse
