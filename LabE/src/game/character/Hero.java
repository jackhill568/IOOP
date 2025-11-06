package game.character;
import game.book.Book;
import game.core.Interactable;
import game.core.Skill;
import java.util.ArrayList;


public class Hero extends Character implements Interactable {
  private ArrayList<Skill> currentSkills;
  private String[] journal;
  private int numJournalEntries;
  private int maxHP; 

  public Hero(String name) {
    super(name, 12);
    this.journal = new String[5];
    this.currentSkills = new ArrayList<Skill>();
    this.numJournalEntries = 0;
    this.maxHP = this.hitPoints;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void AddSkill(Skill currentSkill) {
    for (int i = 0; i < currentSkills.size(); i++){
      if (currentSkills.get(i).equals(currentSkill)){
        return;
      } 
    }
    currentSkills.add(currentSkill);
  }

  public String getSkills() {
    return currentSkills.toString();
  }

  public boolean addJournalEntry(String entry) {
    if (numJournalEntries >= journal.length) {
      return false;
    } else {
      journal[numJournalEntries++] = entry;
      return true;
    }
  }

  public void readBook(Book book) {
    book.doRead(this);
  }

  @Override
  public String toString() {
    return "A hero called " + name + ", skill " + getSkills() + ", hp " + hitPoints;
  }
  @Override
  public String getSummaryString() {
	  return this.name;
  }
  @Override
  public void interact(Hero hero){
    System.out.println(hero.name + " meets " + this.name);

  }
  public Boolean checkSkill(Skill skill) {
    for (int i = 0; i < currentSkills.size(); i++) {
      if (currentSkills.get(i).equals(skill)) {
        System.out.println(this.name + " uses " + skill);
        return true;
      }
    }
    return false;
  }

  public void heal() {
    if (checkSkill(Skill.HEALING)){
      hitPoints =  maxHP;
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

    Skill selected = (currentSkills.size()==0) ? Skill.NONE : currentSkills.get((int)(Math.random() * currentSkills.size()));

    if (selected == Skill.SWORD_FIGHTING) {
      damage = 8;
      System.out.println(name + " slashes " + target.name + " for " + damage + " damage!");
      target.takeDamage(damage);
    } else if (selected == Skill.SPELLCASTING) {
      damage = 10;
      System.out.println(name + " casts a fireball at " + target.name + " for " + damage + " damage!");
      target.takeDamage(damage);
    } else {
      System.out.println(name + " punches " + target.name + " for " + damage + " damage!");
      target.takeDamage(damage);
    }
  }

}
