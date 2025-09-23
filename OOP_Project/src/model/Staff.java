// src/model/Staff.java
package model;

public class Staff extends UserAccount {
    private String staffId;

    public Staff(String username, String password, String staffId) {
        super(username, password);
        this.staffId = staffId;
    }

    // Getters, setters, toString()
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}