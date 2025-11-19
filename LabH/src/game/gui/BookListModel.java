package game.gui;

import javax.swing.AbstractListModel;

import game.book.Book;
import game.character.Hero;

public class BookListModel extends AbstractListModel<Book> {
    private Hero hero;
    
    public BookListModel (Hero hero) {
        this.hero = hero;
    }

    @Override
    public int getSize() {
        return hero.getReadBooks().size();
    }

    @Override
    public Book getElementAt(int index) {
        return hero.getReadBooks().get(index);
    }

    public void update() {
        fireContentsChanged(this, 0, hero.getReadBooks().size());
    }

}
