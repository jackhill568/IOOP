package game.character;

import game.book.Book;
import game.book.CurseEffect;
import game.book.CursedBookException;
import game.core.Skill;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hero extends Character {
    private List<Skill> skills;
    private String[] journal;
    private int numJournalEntries;
    private int maxHitPoints;
    private Set<Book> booksRead;

    public Hero(String name) {
        super(name, 12);
        this.maxHitPoints = hitPoints;
        skills = new ArrayList<>();
        this.journal = new String[5];
        this.numJournalEntries = 0;
        this.booksRead = new HashSet<>();
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
        }
    }

    public boolean addJournalEntry(String entry) {
        if (numJournalEntries >= journal.length) {
            return false;
        } else {
            journal[numJournalEntries++] = entry;
            return true;
        }
    }

    public void addToBooksRead(Book book) {
        this.booksRead.add(book);
    }

    public void readBook(Book book) {
    	try {
	        if (booksRead.contains(book) && book.getSecondEffect() != null){
			    book.getSecondEffect().apply(this);   
			}
			book.getFirstEffect().apply(this);
			this.booksRead.add(book);
    	} catch (CursedBookException e) {
            String[] possibleNames = {"Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah", "Ian", "Julia", "Kevin",
            						"Laura", "Michael", "Nina", "Oscar", "Paula", "Quinn", "Rachel", "Steve", "Tina"};
            System.out.println("The hero has been cursed and forgetten their name");
            this.name = possibleNames[(int)Math.random()*possibleNames.length];
    	}
    }

    public void printJournal() {
        System.out.println("Journal entries");
        if (numJournalEntries == 0) {
            System.out.println("(none)");
        } else {
            for (int i = 0; i < numJournalEntries; i++) {
                System.out.println("- " + journal[i]);
            }
        }
    }

    @Override
    public void attack(Character target) {
        int damage = 2; // base unskilled damage

        if (skills.contains(Skill.SPELLCASTING)) {
            damage = 10;
            System.out.println(name + " casts a fireball at " + target.name + " for " + damage + " damage!");
        } else if (skills.contains(Skill.SWORD_FIGHTING)) {
            damage = 8;
            System.out.println(name + " slashes " + target.name + " for " + damage + " damage!");
        } else {
            System.out.println(name + " punches " + target.name + " for " + damage + " damage!");
        }
        target.takeDamage(damage);
    }

    public void heal() {
        if (skills.contains(Skill.HEALING)) {
            System.out.println(name + " heals to full");
            this.hitPoints = maxHitPoints;
        } else {
            System.out.println(name + " does not know how to heal");
        }
    }


}
