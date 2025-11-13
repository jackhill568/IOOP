package game.book;
import game.character.Hero;

public class LoreEffect implements BookEffect{

    private final String loreNote;

    public LoreEffect(String loreNote) {
        this.loreNote = loreNote;
    }

    @Override
    public void apply(Hero hero){
        if (hero.addJournalEntry(loreNote)) {
            System.out.println(hero.getName() +  " discovers:" + loreNote);
        } else {
            System.out.println(hero.getName() + " discovers:" + loreNote + "\n but their brain is full");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Lore: " + loreNote;
    }
    
}
