package game.book;

import java.awt.Color;

import game.character.Hero;
import game.core.Interactable;
import game.gui.GameWindow;
import game.gui.GameWorldPanel;
import game.gui.Renderable;

public class Book implements Interactable, Renderable {
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

    public void setSecondEffect (BookEffect secondEffect) {
        this.secondEffect = secondEffect;
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

    public void interact (Hero hero) throws CursedBookException {
        System.out.println(hero.getName() + " reads " + this);
        hero.readBook (this);
    }

    public String getSummaryString() {
        return title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        return true;
    }

    @Override
    public String getSpriteKey() {
        if (GameWindow.getInstance().getGameWorld() == null) {
            return "book-closed";
        }
        return GameWindow.getInstance().getGameWorld().getHero().hasRead(this) ? "book-open" : "book-closed";
    }

    @Override
    public int getRenderSize() {
        return GameWorldPanel.SQUARE_SIZE / 3;
    }

    @Override
    public String getToolTipText() {
        return title;
    }

    @Override
    public Color getColor() {
        return Color.BLUE.darker().darker();
    }

}
