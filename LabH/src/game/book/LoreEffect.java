package game.book;

import game.character.Hero;

public class LoreEffect implements BookEffect {

    private String loreNote;

    public LoreEffect (String loreNote) {
        this.loreNote = loreNote;
    }

    @Override
    public void apply(Hero hero) {
        hero.addLore(loreNote);
    }

}
