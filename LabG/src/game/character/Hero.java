package game.character;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.book.Book;
import game.book.BookEffect;
import game.book.CursedBookException;
import game.core.Direction;
import game.core.Skill;
import game.gui.GameWorldPanel;

public class Hero extends Character {
    private List<Skill> skills;
    private int maxHitPoints;
    private Set<Book> readBooks;
    private List<String> journal;
    
    public Hero(String name) {
        super(name, 12);
        this.maxHitPoints = hitPoints;
        skills = new ArrayList<>();
        this.readBooks = new HashSet<>();
        this.journal = new ArrayList<>();
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A hero called " + name + ", skills " + skills + ", hp " + hitPoints;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill newSkill) {
        if (!skills.contains(newSkill)) {
            skills.add(newSkill);
            System.out.println(getName() + " has learned the skill: " + newSkill);
        }
    }

    public void addLore(String lore) {
        journal.add(lore);
        System.out.println(getName() + " discovers:" + lore);
    }

    public void printJournal() {
        System.out.println("Journal entries");
        System.out.println(journal);
    }

    public List<String> getJournal() {
        return journal;
    }

    @Override
    public void attack(Character target) {
        int damage = 2; // base unskilled damage
        String action = "feebly punches";

        // Attack power and message depend on skill combinations
        if (skills.contains(Skill.SPELLCASTING) && skills.contains(Skill.SWORD_FIGHTING)) {
            damage = 12;
            action = "swings an enchanted flaming sword at";
        } else if (skills.contains(Skill.SPELLCASTING) && skills.contains(Skill.MELEE_COMBAT)) {
            damage = 10;
            action = "swings a giant enchanted fist at";
        } else if (skills.contains(Skill.SPELLCASTING)) {
            damage = 8;
            action = "casts a fireball at";
        } else if (skills.contains(Skill.SWORD_FIGHTING)) {
            damage = 6;
            action = "swings a normal sword at";
        } else if (skills.contains(Skill.MELEE_COMBAT)) {
            damage = 4;
            action = "strongly punches";
        }

        System.out.println(name + " " + action + " " + target.name + " for " + damage + " damage!");
        target.takeDamage(damage);
    }

    public boolean canHeal() {
        return skills.contains(Skill.SUPER_HEALING) || skills.contains(Skill.HEALING);
    }

    public void heal() {
        if (!canHeal()) {
            System.out.println(name + " does not know how to heal");
            return;
        }
        if (skills.contains(Skill.SUPER_HEALING)) {
            System.out.println(name + " increases HP and heals to full");
            this.maxHitPoints += 5;
            this.hitPoints = maxHitPoints;
        } else if (skills.contains(Skill.HEALING)) {
            System.out.println(name + " heals to full");
            this.hitPoints = maxHitPoints;
        }
    }

    public void readBook(Book book) throws CursedBookException {
        BookEffect effectToUse = book.getFirstEffect();
        if (readBooks.contains(book) && book.getSecondEffect() != null) {
            effectToUse = book.getSecondEffect();
        }
        readBooks.add(book);
        effectToUse.apply(this);
    }

    public boolean hasRead (Book book) {
        return readBooks.contains(book);
    }

    public List<Book> getReadBooks() {
        return new ArrayList<>(readBooks);
    }

    @Override
    public void interact(Hero hero) {
        System.out.println("Hero meets their peer");
    }

    private boolean flipSprite = false;
    public void setDirection(Direction direction) {
        if (direction == Direction.EAST) {
            flipSprite = false;
        } else if (direction == Direction.WEST) {
            flipSprite = true;
        }
        // Don't change it for other directions
    }

    @Override
    public String getSpriteKey() {
        if (!isAlive()) {
            return "hero-fainted";
        } else if (flipSprite) {
            return "hero-flipped";
        } else {
            return "hero";
        }
    }

    @Override
    public int getRenderSize() {
        return GameWorldPanel.SQUARE_SIZE / 2;
    }

    @Override
    public String getToolTipText() {
        return name;
    }

    @Override
    public Color getColor() {
        return Color.RED.darker().darker();
    }

    public void forgetRandomSkill() {
        if (!skills.isEmpty()) {
            skills.remove (0);
        }
    }

    public void reduceHP(int value) {
        hitPoints = Math.max(hitPoints - value, 0);
    }

    public void reduceMaxHP(int value) {
        maxHitPoints = Math.max(maxHitPoints - value, 0);
        if (hitPoints > maxHitPoints) hitPoints = maxHitPoints;
    }

}
