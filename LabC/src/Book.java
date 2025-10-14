public class Book {
  protected String title;
  protected String author;
  protected int numPages;

  public Book(String title, String author, int numPages) {
    this.title = title;
    this.author = author;
    this.numPages = numPages;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getNumPages() {
    return numPages;
  }

  @Override
  public String toString() {
    return "'" + this.title + "' by " + this.author + " (" + this.numPages + ")";
  }

  public void printDetails() {
    System.out.println(this.toString());
  }

  public void doRead(Hero hero) {
    System.out.println(hero.getName() + " has read" + this.title);
  }
}
