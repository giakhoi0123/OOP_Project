// src/model/Publisher.java
package model;

public class Publisher {
    private String id;
    private String name;

    public Publisher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters, setters, toString()
    public String getId() {
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
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}