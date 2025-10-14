public class Hero {
  private String name;
  private String currentSkill;
  private String[] journal = new String[5];
  private int journalPointer = -1;

  public Hero(String name) {
    this.name = name;
    this.currentSkill = "none";
  }

  public boolean addJournalFact(String fact) {
    if (this.journalPointer < 5) {
      this.journal[++this.journalPointer] = fact;
      return true;
    }
    return false;
  }

  public void printJournal() {
    if (this.journalPointer == -1) {
      System.out.println("(none)");
      return;
    }
    for (int i = 0; i < this.journalPointer + 1; i++) {
      System.out.println("Log " + i + ": " + this.journal[i]);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrentSkill() {
    return currentSkill;
  }

  public void setCurrentSkill(String currentSkill) {
    this.currentSkill = currentSkill;
  }

  public void readBook(Book book) {
    book.doRead(this);
  }

  public void useSkill(String skillName) {
    if (!currentSkill.equals("none") && skillName.equals(currentSkill)) {
      System.out.println(this.name + " uses " + skillName);
    } else {
      System.out.println(this.name + " does not know how to do that");
    }
  }

  public void printDetails() {
    System.out.println("Hero name: '" + name + "', equipped skill: " + currentSkill);
  }
}
