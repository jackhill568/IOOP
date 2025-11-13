package game.core;

import game.character.Hero;

public interface Interactable {

    public void interact (Hero hero);

    public String getSummaryString();

}
