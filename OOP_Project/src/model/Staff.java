// src/model/Staff.java
package model;

public class Staff extends UserAccount {
    private String staffId;

    public Staff(String username, String password, String staffId) {
        super(username, password);
        this.staffId = staffId;
    }

    // Getters, setters, toString()
}