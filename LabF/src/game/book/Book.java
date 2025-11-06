package game.book;

import game.character.Hero;
import game.core.Interactable;

public class Book implements Interactable {
    private String title;
    private String author;
    private int numPages;
    private BookEffect firstEffect, secondEffect;
    
    public Book(String title, String author, int numPages, BookEffect firstEffect, BookEffect secondEffect) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.firstEffect = firstEffect;
        this.secondEffect = secondEffect;
    }

    public Book(String title, String author, int numPages, BookEffect firstEffect) {
        this(title, author, numPages, firstEffect, null);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumPages() {
        return numPages;
    }

    public BookEffect getFirstEffect() {
        return this.firstEffect;
    }

    public BookEffect getSecondEffect() {
        return this.secondEffect;
    }

    @Override
    public String toString() {
        return "'" + title + "' by " + author + " (" + numPages + "pp)";
    }

    public void interact (Hero hero) {
        System.err.println(hero.getName() + " reads " + this);
        hero.readBook (this);
    }

    public String getSummaryString() {
        return title;
    }
}
