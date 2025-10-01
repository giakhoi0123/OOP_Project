// src/model/Category.java
package model;

public class Category {
    private String id;
    private String name;
    public Category() {
    }
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public void nhap() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập mã thể loại: ");
        id = scanner.nextLine();
        System.out.print("Nhập tên thể loại: ");
        name = scanner.nextLine();
        scanner.close();
    }
    // Getters, setters, toString()
    public String getCategoryId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category{id='" + id + "', name='" + name + "'}";
    }
}