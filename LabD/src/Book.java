public abstract class Book {
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
    return "'" + title + "' by " + author + " (" + numPages + "pp)";
  }

  public void printDetails() {
    System.out.println(this);
  }

  public abstract void doRead(Hero hero);

}
