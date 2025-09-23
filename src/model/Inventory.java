// src/model/Inventory.java
package model;

public class Inventory {
    private int totalBooks;
    private int availableBooks;

    public void updateInventory() {
        System.out.println("Updating inventory.");
    }

    // Getters, setters, toString()
    public int getTotalBooks() {
        return totalBooks;
    }
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }
    public int getAvailableBooks() {
        return availableBooks;
    }
}