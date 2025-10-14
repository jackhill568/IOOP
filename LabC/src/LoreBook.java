public class LoreBook extends Book {

  private String lore;

  public String getLore() {
    return this.lore;
  }

  @Override
  public String toString() {
    return super.toString() + "\nLore: " + this.lore;
  }

  @Override
  public void doRead(Hero hero) {

    boolean result = hero.addJournalFact(this.lore);
    if (result)
      System.out.println(hero.getName() + " has read " + this.getTitle() + " and learned " + this.lore);
    else
      System.out.println(hero.getName() + " has read " + this.title + "but their journal was full");
  }

  LoreBook(String title, String author, int numPages, String lore) {

    super(title, author, numPages);
    this.lore = lore;
  }

}
