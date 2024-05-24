package books;

public class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, "Story", stock);
    }
}
