package model;

public class Book {
    private String id;
    private String title;
    private Author author;
    private Publisher publisher;
    private Category category;
    private boolean isAvailable;

    public Book(String id, String title, Author author, Publisher publisher, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
    return "Book {" +
            "Mã sách='" + id + '\'' +
            ", Tên sách='" + title + '\'' +
            ", Tác giả='" + author + '\'' +
            ", Nhà XB='" + publisher + '\'' +
            ", Thể loại='" + category + '\'' +
            ", Còn sẵn='" + (isAvailable ? "Có" : "Không") + '\'' +
            '}';
    }
}