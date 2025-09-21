package model;

public class Reader extends UserAccount {
    private String readerId;

    public Reader(String username, String password, String readerId) {
        super(username, password);
        this.readerId = readerId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId='" + readerId + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}