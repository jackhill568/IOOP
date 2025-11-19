package game.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.book.Book;

public class Library {
    private Map<String, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(String bookId, Book book) {
        books.put(bookId, book);
    }

    public Book getBook (String bookId) {
        return books.get(bookId);
    }

    public List<String> getSortedBookIds() {
        List<String> bookIds = new ArrayList<>(books.keySet());
        Collections.sort(bookIds);
        return bookIds;
    }

}
