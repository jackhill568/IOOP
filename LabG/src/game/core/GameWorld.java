package game.core;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import game.book.Book;
import game.book.CurseEffect;
import game.book.CurseType;
import game.book.LoreEffect;
import game.book.SkillEffect;
import game.character.Goblin;
import game.character.Hero;

public class GameWorld {

    private Cell[][] cells;
    private Hero hero;
    private int heroX, heroY;

    // Dimensions
    public static final int GRID_SIZE = 6;

    public GameWorld (String heroName) {
        // Books to use
        // (Created by Copilot)
        Library library = new Library();

        library.addBook("swords", new Book("Sword Mastery", "K. Steel", 250,
                new SkillEffect(Skill.SWORD_FIGHTING)));

        library.addBook("combat", new Book("Close Combat Tactics", "J. Iron", 300,
                new SkillEffect(Skill.MELEE_COMBAT),
                new CurseEffect("You feel bruised (HP reduced)", CurseType.REDUCE_HP)));

        library.addBook("arcane", new Book("Secrets of the Arcane", "M. Rune", 320,
                new SkillEffect(Skill.SPELLCASTING)));

        library.addBook("healing", new Book("Herbal Remedies", "L. Green", 180,
                new SkillEffect(Skill.HEALING),
                new CurseEffect("Your vitality weakens (Max HP reduced)", CurseType.REDUCE_MAXHP)));

        library.addBook("superheal", new Book("Advanced Healing Arts", "S. Mercy", 400,
                new SkillEffect(Skill.SUPER_HEALING)));

        library.addBook("duelist", new Book("The Art of the Duel", "A. Blade", 350,
                new SkillEffect(Skill.SWORD_FIGHTING),
                new CurseEffect("You forget a random skill!", CurseType.FORGET_SKILL)));

        library.addBook("battle", new Book("Battlefield Survival", "C. Valor", 280,
                new SkillEffect(Skill.MELEE_COMBAT)));

        library.addBook("grimoires", new Book("Forbidden Grimoires", "D. Shade", 500,
                new SkillEffect(Skill.SPELLCASTING),
                new CurseEffect("Dark magic drains your life (HP reduced)", CurseType.REDUCE_HP)));

        library.addBook("restoration", new Book("Restoration Rituals", "E. Light", 450,
                new SkillEffect(Skill.SUPER_HEALING),
                new CurseEffect("Your body feels frail (Max HP reduced)", CurseType.REDUCE_MAXHP)));

        library.addBook("warrior", new Book("Warrior’s Path", "T. Steelheart", 320,
                new SkillEffect(Skill.SWORD_FIGHTING),
                new LoreEffect("Legends speak of warriors who never falter.")));

        library.addBook("myths", new Book("Myths of the Bound Realms", "F. Teller", 220,
                new LoreEffect("Some say the Library connects to realms where time flows backward."),
                new LoreEffect("You feel slightly disoriented, as if time skipped a beat.")));

        library.addBook("origins", new Book("Origins of the Library", "A. Archivist", 200,
                new LoreEffect("Long ago, the Library was built to safeguard knowledge from collapsing worlds."),
                new LoreEffect("A faint echo of ancient voices lingers in your mind.")));

        library.addBook("echoes", new Book("Echoes of the Forgotten", "N. Whisper", 240,
                new LoreEffect("The book hums with memories not your own."),
                new CurseEffect("Your memory is clouded (Forget a skill)", CurseType.FORGET_SKILL)));

        // Randomly distribute enemies and books
        Random random = new Random();

        cells = new Cell[GRID_SIZE][];
        int goblinCount = 1;

        List<String> bookIds = library.getSortedBookIds();
        Collections.shuffle(bookIds);
        int bookNum = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            cells[i] = new Cell[GRID_SIZE];
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j] = new Cell(i, j);
                // Can't have a goblin and a book on the same square (too confusing)
                if (random.nextDouble() > 0.8) {
                    cells[i][j].addInteractable(library.getBook(bookIds.get(bookNum)));
                    bookNum++;
                } else if (random.nextDouble() > 0.8 && bookNum < bookIds.size()) {
                    cells[i][j].addInteractable(new Goblin("Gobby"+goblinCount));
                    goblinCount++;
                }
            }
        }

        hero = new Hero(heroName);
        updateHeroLocation(GRID_SIZE - 1, GRID_SIZE - 1);
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Hero getHero() {
        return this.hero;
    }

    public void moveHero(String direction) {
        if (!hero.isAlive()) {
            System.out.println("Hero has fainted and cannot move");
            return;
        }

        int newX = heroX, newY = heroY;
        switch (direction) {
            case "N":
                newY = Math.min(heroY+1, cells.length-1);
                break;

            case "E":
                newX = Math.min(heroX+1, cells.length-1);
                break;

            case "S":
                newY = Math.max(heroY-1, 0);
                break;

            case "W":
                newX = Math.max(heroX-1, 0);
                break;
        }
        if (newX != heroX || newY != heroY) {
            updateHeroLocation(newX, newY);
        }
    }

    public void updateHeroLocation(int newX, int newY) {
        this.heroX = newX;
        this.heroY = newY;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }
}
