package service;

import model.Order;
import model.OrderDetail;

interface IorderService {
    void createOrder();
    void updateOrder();
    void cancelOrder();
    void getOrderDetails();
}
public class OrderService implements IorderService {
    private Order orderlist[] = new Order[100];
    private OrderDetail orderDetail_list[] = new OrderDetail[100];
    private int orderCount = 0;
    private int orderDetailCount = 0;
    
    @Override
    public void createOrder() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void updateOrder() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void cancelOrder() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void getOrderDetails() {
        // TODO Auto-generated method stub
        
    }
}
