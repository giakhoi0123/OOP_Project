// src/model/Notification.java

package model;


public abstract class Notification{
    private String loaiThongBao;
    private Book book ;
    private Reader reader;
    public Notification() {
        super();
    }
    public Notification(String loaiThongBao, Book book, Reader reader) {
        this.loaiThongBao = loaiThongBao;
        this.book = book;
        this.reader = reader;
    }
    public abstract void sendNotification();
    public abstract void readNotifications();
}