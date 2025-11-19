package game.gui;

import javax.swing.AbstractListModel;

import game.character.Hero;

public class JournalListModel extends AbstractListModel<String>{

    private Hero hero;

    public JournalListModel(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int getSize() {
        return hero.getJournal().size();
    }

    @Override
    public String getElementAt(int index) {
        return hero.getJournal().get(index);
    }

    public void update() {
        fireContentsChanged(this, 0, getSize());
    }

}
