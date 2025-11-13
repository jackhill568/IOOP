package game.book;

import game.character.Hero;
import game.core.Interactable;
import java.util.Objects;

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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Book){
            Book o = (Book) obj;
            return Objects.equals(this.title, o.getTitle()) && Objects.equals(this.author, o.getAuthor());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.author);
    }

    public void interact (Hero hero) {
        System.err.println(hero.getName() + " reads " + this);
        hero.readBook (this);
    }

    public String getSummaryString() {
        return title;
    }
}
