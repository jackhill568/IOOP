package game.core;

import game.book.CursedBookException;
import game.character.Hero;

public interface Interactable {

    public void interact (Hero hero) throws CursedBookException;

    public String getSummaryString();

}
