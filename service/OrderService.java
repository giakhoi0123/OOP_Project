package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.Order;
import model.OrderDetail;

public class OrderService implements IOrderService {
    private Order[] orders;
    private int orderCount;
    private static final int MAX_ORDERS = 1000;
    
    private OrderDetail[] orderDetails;
    private int detailCount;
    private static final int MAX_DETAILS = 5000;
    
    private final Scanner scanner = new Scanner(System.in);
    private final String ORDER_FILE = "data/orders.csv";
    private final String DETAIL_FILE = "data/orders_details.csv";
    
    private BookService bookService;

    public OrderService() {
        this.orders = new Order[MAX_ORDERS];
        this.orderCount = 0;
        this.orderDetails = new OrderDetail[MAX_DETAILS];
        this.detailCount = 0;
        loadData(); 
    }
    
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    @Override
    public void saveData() {
        try (FileWriter fw = new FileWriter(ORDER_FILE, false); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i = 0; i < orderCount; i++) {
                bw.write(orders[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + orderCount + " đơn hàng vào file " + ORDER_FILE + ".");
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu đơn hàng: " + e.getMessage());
        }

        try (FileWriter fw = new FileWriter(DETAIL_FILE, false); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i = 0; i < detailCount; i++) {
                bw.write(orderDetails[i].toCSV());
                bw.newLine();
            }
            System.out.println("✅ Đã lưu " + detailCount + " chi tiết đơn hàng vào file " + DETAIL_FILE + ".");
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi lưu chi tiết đơn hàng: " + e.getMessage());
        }
    }

    @Override
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            orderCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Order order = Order.readFromCSV(line); 
                if (order != null && orderCount < MAX_ORDERS) {
                    orders[orderCount++] = order;
                }
            }
            System.out.println("✅ Tải thành công " + orderCount + " đơn hàng.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File đơn hàng (" + ORDER_FILE + ") chưa tồn tại. Bắt đầu trống.");
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file đơn hàng: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(DETAIL_FILE))) {
            String line;
            detailCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                OrderDetail detail = OrderDetail.readFromCSV(line);
                if (detail != null && detailCount < MAX_DETAILS) {
                    orderDetails[detailCount++] = detail;
                }
            }
            System.out.println("✅ Tải thành công " + detailCount + " chi tiết đơn hàng.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("ℹ️ File chi tiết đơn hàng (" + DETAIL_FILE + ") chưa tồn tại. Bắt đầu trống.");
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file chi tiết đơn hàng: " + e.getMessage());
        }
    }

    @Override
    public void addOrderAndDetails() {
        if (orderCount >= MAX_ORDERS) {
            System.out.println("❌ Đã đạt giới hạn số lượng đơn hàng!");
            return;
        }
        
        System.out.println("--- Thêm Đơn Hàng Mới ---");
        System.out.print("Nhập mã đơn hàng: ");
        String orderId = scanner.nextLine();
        if (findOrderById(orderId) != null) {
            System.out.println("❌ Lỗi: Mã đơn hàng '" + orderId + "' đã tồn tại.");
            return;
        }

        System.out.print("Nhập mã nhân viên (Staff ID): ");
        String staffId = scanner.nextLine();
        System.out.print("Nhập mã nhà cung cấp (Supplier ID): ");
        String supplierId = scanner.nextLine();
        String orderDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE); 

        Order newOrder = new Order(orderId, staffId, orderDate, "0.0", supplierId);
        orders[orderCount++] = newOrder;
        System.out.println("✅ Thêm thông tin cơ bản của đơn hàng thành công!");

        while (true) {
            System.out.print("Bạn có muốn thêm sách vào đơn hàng này không? (y/n): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
            addDetailAndUpdateTotal(newOrder);
        }
        System.out.println("=> Đơn hàng " + newOrder.getOrderId() + " đã được tạo với Tổng tiền: " + newOrder.getTotalAmount());
        saveData();
    }
    
    @Override
    public void updateOrder() {
        System.out.print("Nhập mã đơn hàng cần cập nhật: ");
        String orderId = scanner.nextLine();
        Order orderToUpdate = findOrderById(orderId);

        if (orderToUpdate == null) {
            System.out.println("❌ Không tìm thấy đơn hàng có mã: " + orderId);
            return;
        }

        System.out.println("Nhập thông tin mới (để trống nếu không muốn thay đổi):");
        System.out.print("Mã nhân viên mới (Staff ID): ");
        String newStaffId = scanner.nextLine();
        if (!newStaffId.trim().isEmpty()) {
            orderToUpdate.setStaffId(newStaffId);
        }
        System.out.print("Mã nhà cung cấp mới (Supplier ID): ");
        String newSupplierId = scanner.nextLine();
        if (!newSupplierId.trim().isEmpty()) {
            orderToUpdate.setSupplierId(newSupplierId);
        }

        System.out.println("✅ Cập nhật đơn hàng thành công!");
        saveData();
    }

    @Override
    public void deleteOrder() {
        System.out.print("Nhập mã đơn hàng cần xóa: ");
        String orderId = scanner.nextLine();
        Order orderToDelete = findOrderById(orderId);

        if (orderToDelete == null) {
            System.out.println("❌ Không tìm thấy đơn hàng có mã: " + orderId);
            return;
        }

        int newDetailCount = 0;
        for (int i = 0; i < detailCount; i++) {
            if (!orderDetails[i].getOrderId().equals(orderId)) {
                orderDetails[newDetailCount++] = orderDetails[i];
            }
        }
        for (int i = newDetailCount; i < detailCount; i++) {
            orderDetails[i] = null;
        }
        detailCount = newDetailCount;
        
        int indexToDelete = -1;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getOrderId().equals(orderId)) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < orderCount - 1; i++) {
                orders[i] = orders[i + 1];
            }
            orders[--orderCount] = null;
        }
        
        System.out.println("✅ Xóa đơn hàng và tất cả chi tiết liên quan thành công!");
        saveData();
    }

    @Override
    public Order findOrderById(String orderId) {
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getOrderId().equalsIgnoreCase(orderId)) {
                return orders[i];
            }
        }
        return null;
    }

    public Order findOrderById() {
        System.out.print("Nhập mã đơn hàng cần tìm: ");
        String orderId = scanner.nextLine();
        return findOrderById(orderId);
    }

    @Override
    public void displayAllOrders() {
        System.out.println("\n--- Danh Sách Đơn Hàng (" + orderCount + ") ---");
        if (orderCount == 0) {
            System.out.println("Chưa có đơn hàng nào.");
            return;
        }
        for (int i = 0; i < orderCount; i++) {
            System.out.println(orders[i]);
        }
    }

    private void addDetailAndUpdateTotal(Order order) {
        if (detailCount >= MAX_DETAILS) {
            System.out.println("❌ Đã đạt giới hạn số lượng chi tiết đơn hàng!");
            return;
        }
        
        System.out.println("--- Thêm chi tiết cho đơn hàng " + order.getOrderId() + " ---");
        OrderDetail newDetail = new OrderDetail();
        
        System.out.print("Nhập mã chi tiết đơn hàng (Detail ID): ");
        String detailId = scanner.nextLine();
        if (findOrderDetailById(detailId) != null) {
            System.out.println("❌ Lỗi: Mã chi tiết '" + detailId + "' đã tồn tại.");
            return;
        }
        newDetail.setOrderDetailId(detailId);
        newDetail.setOrderId(order.getOrderId());

        System.out.print("Nhập mã sách (Book ID): ");
        newDetail.setBookId(scanner.nextLine());
        
        try {
            System.out.print("Nhập số lượng: ");
            newDetail.setQuantity(Integer.parseInt(scanner.nextLine()));
            System.out.print("Nhập đơn giá: ");
            newDetail.setPrice(scanner.nextLine());
            
            double currentTotal = Double.parseDouble(order.getTotalAmount());
            double detailAmount = newDetail.getQuantity() * Double.parseDouble(newDetail.getPrice());
            order.setTotalAmount(String.valueOf(currentTotal + detailAmount));
            
            orderDetails[detailCount++] = newDetail;
            System.out.println("✅ Thêm chi tiết sách thành công!");
            
            if (bookService != null) {
                bookService.increaseBookQuantity(newDetail.getBookId(), newDetail.getQuantity());
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: Số lượng hoặc giá không hợp lệ. Vui lòng nhập số. Chi tiết không được thêm.");
            return;
        }
        saveData();
    }

    @Override
    public void addDetailToExistingOrder() {
        System.out.print("Nhập mã đơn hàng bạn muốn thêm chi tiết vào: ");
        String orderId = scanner.nextLine();
        Order order = findOrderById(orderId);
        if (order == null) {
            System.out.println("❌ Không tồn tại đơn hàng có mã này.");
            return;
        }
        addDetailAndUpdateTotal(order);
        System.out.println("=> Tổng tiền mới của đơn hàng " + order.getOrderId() + " là: " + order.getTotalAmount());
    }

    @Override
    public void updateOrderDetail() {
        System.out.print("Nhập mã chi tiết đơn hàng cần cập nhật: ");
        String detailId = scanner.nextLine();
        OrderDetail detailToUpdate = findOrderDetailById(detailId);
        
        if(detailToUpdate == null) {
            System.out.println("❌ Không tìm thấy chi tiết có mã: " + detailId);
            return;
        }

        Order parentOrder = findOrderById(detailToUpdate.getOrderId());
        double oldAmount = detailToUpdate.getQuantity() * Double.parseDouble(detailToUpdate.getPrice());
        
        System.out.print("Nhập mã sách mới (để trống nếu không đổi): ");
        String newBookId = scanner.nextLine();
        if (!newBookId.trim().isEmpty()) {
            detailToUpdate.setBookId(newBookId);
        }

        try {
            System.out.print("Nhập số lượng mới (để trống nếu không đổi): ");
            String newQuantityStr = scanner.nextLine();
            if (!newQuantityStr.trim().isEmpty()) {
                detailToUpdate.setQuantity(Integer.parseInt(newQuantityStr));
            }
            
            System.out.print("Nhập đơn giá mới (để trống nếu không đổi): ");
            String newPrice = scanner.nextLine();
            if (!newPrice.trim().isEmpty()) {
                detailToUpdate.setPrice(newPrice);
            }

            if (parentOrder != null) {
                double currentTotal = Double.parseDouble(parentOrder.getTotalAmount());
                double newAmount = detailToUpdate.getQuantity() * Double.parseDouble(detailToUpdate.getPrice());
                parentOrder.setTotalAmount(String.valueOf(currentTotal - oldAmount + newAmount));
                System.out.println("=> Tổng tiền mới của đơn hàng " + parentOrder.getOrderId() + " là: " + parentOrder.getTotalAmount());
            }

            System.out.println("✅ Cập nhật chi tiết đơn hàng thành công!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: Số lượng hoặc giá không hợp lệ. Cập nhật thất bại.");
            return;
        }
        saveData();
    }

    @Override
    public void deleteOrderDetail() {
        System.out.print("Nhập mã chi tiết đơn hàng cần xóa: ");
        String detailId = scanner.nextLine();
        OrderDetail detailToDelete = findOrderDetailById(detailId);

        if (detailToDelete == null) {
            System.out.println("❌ Không tìm thấy chi tiết có mã: " + detailId);
            return;
        }

        Order parentOrder = findOrderById(detailToDelete.getOrderId());
        if (parentOrder != null) {
            double currentTotal = Double.parseDouble(parentOrder.getTotalAmount());
            double detailAmount = detailToDelete.getQuantity() * Double.parseDouble(detailToDelete.getPrice());
            parentOrder.setTotalAmount(String.valueOf(currentTotal - detailAmount));
        }

        int indexToDelete = -1;
        for (int i = 0; i < detailCount; i++) {
            if (orderDetails[i].getOrderDetailId().equalsIgnoreCase(detailId)) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < detailCount - 1; i++) {
                orderDetails[i] = orderDetails[i + 1];
            }
            orderDetails[--detailCount] = null;
        }
        
        System.out.println("✅ Xóa chi tiết đơn hàng thành công!");
        if (parentOrder != null) {
            System.out.println("=> Tổng tiền mới của đơn hàng " + parentOrder.getOrderId() + " là: " + parentOrder.getTotalAmount());
        }
        saveData();
    }

    @Override
    public OrderDetail findOrderDetailById(String orderDetailId) {
        for (int i = 0; i < detailCount; i++) {
            if (orderDetails[i].getOrderDetailId().equalsIgnoreCase(orderDetailId)) {
                return orderDetails[i];
            }
        }
        return null;
    }

    @Override
    public void displayAllOrderDetails() {
        System.out.println("\n--- Danh Sách Tất Cả Chi Tiết Đơn Hàng (" + detailCount + ") ---");
        if (detailCount == 0) {
            System.out.println("Chưa có chi tiết đơn hàng nào.");
            return;
        }
        for (int i = 0; i < detailCount; i++) {
            System.out.println(orderDetails[i]);
        }
    }
    
    @Override
    public void displayDetailsForSpecificOrder() {
        System.out.print("Nhập mã đơn hàng cần xem chi tiết: ");
        String orderId = scanner.nextLine();
        Order parentOrder = findOrderById(orderId);

        if (parentOrder == null) {
            System.out.println("❌ Không tìm thấy đơn hàng có mã: " + orderId);
            return;
        }

        System.out.println("\n--- Chi Tiết cho Đơn Hàng: " + orderId + " ---");
        System.out.println("Thông tin chung: " + parentOrder);

        int count = 0;
        System.out.println("Các sản phẩm trong đơn hàng:");
        for (int i = 0; i < detailCount; i++) {
            if (orderDetails[i].getOrderId().equals(orderId)) {
                System.out.println("  - " + orderDetails[i]);
                count++;
            }
        }
        
        if (count == 0) {
            System.out.println("-> Đơn hàng này chưa có sản phẩm nào.");
        }
    }

    private double parseTotalAmount(String amountStr) {
        try {
            return Double.parseDouble(amountStr.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double[] getTotalRevenueByDateRange(LocalDate startDate, LocalDate endDate) {
        double[] result = new double[2]; // [0] = totalRevenue, [1] = count
        
        for (int i = 0; i < orderCount; i++) {
            try {
                LocalDate orderDate = LocalDate.parse(orders[i].getOrderDate());
                
                if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                    result[0] += parseTotalAmount(orders[i].getTotalAmount());
                    result[1]++;
                }
            } catch (DateTimeParseException e) {
            }
        }
        
        return result;
    }
    
    public void statisticTotalRevenueByDateRange() {
        System.out.println("\n--- THỐNG KÊ DOANH THU THEO KHOẢNG NGÀY ---");
        System.out.print("Nhập ngày bắt đầu (yyyy-MM-dd): ");
        String startStr = scanner.nextLine().trim();
        System.out.print("Nhập ngày kết thúc (yyyy-MM-dd): ");
        String endStr = scanner.nextLine().trim();
        
        LocalDate startDate, endDate;
        try {
            startDate = LocalDate.parse(startStr);
            endDate = LocalDate.parse(endStr);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Định dạng ngày không hợp lệ. Vui lòng nhập theo yyyy-MM-dd.");
            return;
        }

        double[] result = getTotalRevenueByDateRange(startDate, endDate);
        
        System.out.println("\n===== BÁO CÁO DOANH THU (" + startDate + " - " + endDate + ") =====");
        System.out.printf("✅ Tổng số đơn hàng: %d\n", (int)result[1]);
        System.out.printf("💰 Tổng doanh thu: %,.0f VNĐ\n", result[0]);
        System.out.println("====================================================");
    }
    
    public double[] getQuarterlyFinancials(int year) {
        double[] quarterlyCost = new double[4];
        
        for (int i = 0; i < orderCount; i++) {
            try {
                LocalDate orderDate = LocalDate.parse(orders[i].getOrderDate());
                if (orderDate.getYear() == year) {
                    int month = orderDate.getMonthValue();
                    int quarter = (month - 1) / 3;
                    quarterlyCost[quarter] += parseTotalAmount(orders[i].getTotalAmount());
                }
            } catch (DateTimeParseException e) {
            }
        }
        
        return quarterlyCost;
    }
    
    public void statisticQuarterlyFinancials(int year) {
        double[] quarterlyCost = getQuarterlyFinancials(year);
        
        System.out.println("\n===== TỔNG CHI PHÍ THEO QUÝ NĂM " + year + " =====");
        System.out.println("+---------+-------------------------+");
        System.out.printf("| %-7s | %-21s |\n", "Quý", "Tổng Chi Phí (VNĐ)");
        System.out.println("+---------+-------------------------+");
        
        double totalYearlyCost = 0;
        for (int q = 0; q < 4; q++) {
            System.out.printf("| %-7s | %,.0f VNĐ\n", "Q" + (q + 1), quarterlyCost[q]);
            totalYearlyCost += quarterlyCost[q];
        }
        System.out.println("+---------+-------------------------+");
        System.out.printf("| %-7s | %,.0f VNĐ\n", "TỔNG NĂM", totalYearlyCost);
        System.out.println("=========================================");
    }

    public void getGroupedFinancials(String groupType, String[] keys, double[] values, int[] size) {
        size[0] = 0;
        
        if (!groupType.equalsIgnoreCase("SUPPLIER") && !groupType.equalsIgnoreCase("STAFF")) {
            return;
        }
        
        for (int i = 0; i < orderCount; i++) {
            String key;
            if (groupType.equalsIgnoreCase("SUPPLIER")) {
                key = orders[i].getSupplierId();
            } else {
                key = orders[i].getStaffId();
            }
            
            double amount = parseTotalAmount(orders[i].getTotalAmount());
            
            int index = -1;
            for (int j = 0; j < size[0]; j++) {
                if (keys[j].equals(key)) {
                    index = j;
                    break;
                }
            }
            
            if (index != -1) {
                values[index] += amount;
            } else {
                keys[size[0]] = key;
                values[size[0]] = amount;
                size[0]++;
            }
        }
    }
    
    public void statisticGroupedFinancials(String groupChoice) {
        System.out.println("\n--- THỐNG KÊ CHI PHÍ CHI TIẾT THEO NHÓM ---");
        
        String fieldName;
        String groupType;
        
        if (groupChoice.equals("1")) {
            fieldName = "Nhà Cung Cấp";
            groupType = "SUPPLIER";
        } else if (groupChoice.equals("2")) {
            fieldName = "Nhân Viên";
            groupType = "STAFF";
        } else {
            System.out.println("❌ Lựa chọn không hợp lệ.");
            return;
        }

        String[] keys = new String[MAX_ORDERS];
        double[] values = new double[MAX_ORDERS];
        int[] size = new int[1];
        
        getGroupedFinancials(groupType, keys, values, size);

        System.out.println("\n===== BÁO CÁO CHI PHÍ THEO " + fieldName.toUpperCase() + " =====");
        System.out.printf("| %-15s | %-20s |\n", "Mã " + fieldName, "Tổng Chi Phí (VNĐ)");
        System.out.println("+-----------------+----------------------+");
        
        double grandTotal = 0;
        for (int i = 0; i < size[0]; i++) {
            System.out.printf("| %-15s | %,.0f VNĐ\n", keys[i], values[i]);
            grandTotal += values[i];
        }
        System.out.println("+-----------------+----------------------+");
        System.out.printf("| %-15s | %,.0f VNĐ\n", "TỔNG CỘNG", grandTotal);
        System.out.println("=========================================");
    }
}
