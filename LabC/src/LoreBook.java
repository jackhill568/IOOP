public class LoreBook extends Book {

  private String lore;

  public String getLore() {
    return this.lore;
  }

  @Override
  public String toString() {
    return super.toString() + "\nLore: " + this.lore;
  }

  LoreBook(String title, String author, int numPages, String lore) {

    super(title, author, numPages);
    this.lore = lore;
  }

}
