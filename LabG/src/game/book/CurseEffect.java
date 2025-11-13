package game.book;

import game.character.Hero;

public class CurseEffect implements BookEffect {

    private String message;
    private CurseType curseType;

    public CurseEffect (String message, CurseType curseType) {
        this.message = message;
        this.curseType = curseType;
    }

    @Override
    public void apply(Hero hero) throws CursedBookException {
        throw new CursedBookException(message, curseType);
    }

}
