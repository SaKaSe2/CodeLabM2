package books;

public class TextBook extends Book {
    public TextBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, "Text", stock);
    }
}
