package service;
import model.Author;

public class AuthorService {
    private Author[] authors = new Author[50];
    private static int authorCount = 0;

    public void addAuthor(Author author) {
        Author newAuthor = new Author();
        newAuthor.nhap();
        authors[authorCount++] = newAuthor;
    }

    public Author getAuthorById(String authorId) {
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].getAuthorId().equals(authorId)) {
                return authors[i];
            }
        }
        return null;
    }
}
