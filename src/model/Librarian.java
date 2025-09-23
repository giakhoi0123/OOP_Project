// src/model/Librarian.java
package model;

public class Librarian extends Staff {
    public Librarian(String username, String password, String staffId) {
        super(username, password, staffId);
    }

    public void manageBooks() {
        System.out.println("Managing books.");
    }

    // to string
    @Override
    public String toString() {
        return "Librarian{" +
                "staffId='" + getStaffId() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}