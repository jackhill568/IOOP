package game.book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private final Map<String, Book> books; 

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(String bookId, Book book){
        books.put(bookId, book);
    }

    public Book getBook(String bookId){
        return books.get(bookId);
    }

    public List<String> getSortedBookIds() {
        return new ArrayList<>(books.keySet());

    }

}