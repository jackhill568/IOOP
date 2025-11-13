package game.book;

import game.character.Hero;
import java.util.random.RandomGenerator;

public class CurseEffect implements BookEffect {

    @Override
    public void apply(Hero hero) throws CursedBookException {
    	throw new CursedBookException("cursed");

    }
    
    
}
