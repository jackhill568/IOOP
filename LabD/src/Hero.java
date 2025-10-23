public class Hero extends Character {
  private Skill currentSkill;
  private String[] journal;
  private int numJournalEntries;

  public Hero(String name) {
    super(name, 12);
    this.currentSkill = Skill.NONE;
    this.journal = new String[5];
    this.numJournalEntries = 0;
  }

  public Skill getCurrentSkill() {
    return currentSkill;
  }

  public void setCurrentSkill(Skill currentSkill) {
    this.currentSkill = currentSkill;
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

  public void useSkill(Skill skill) {
    if (skill != Skill.NONE && currentSkill == skill) {
      System.out.println(this.name + " uses " + skill);
    } else {
      System.out.println(this.name + " does not know how to do that");
    }
  }

  public void printDetails() {
    System.out.println("Hero name: '" + name + "', equipped skill: " + currentSkill + ", hit points: " + hitPoints);
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

    if (currentSkill == Skill.SWORD_FIGHTING) {
      damage = 8;
      System.out.println(name + " slashes " + target.name + " for " + damage + " damage!");
    } else if (currentSkill == Skill.SPELLCASTING) {
      damage = 10;
      System.out.println(name + " casts a fireball at " + target.name + " for " + damage + " damage!");
    } else {
      System.out.println(name + " punches " + target.name + " for " + damage + " damage!");
    }
    target.takeDamage(damage);
  }

}
