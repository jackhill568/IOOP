
public class Book {

  String title;
  String author;
  int numPages;
  String skill;

  public String getTitle() {
    return this.title;
  }

  public String getAuthor() {
    return this.author;
  }

  public int getNumPages() {
    return this.numPages;
  }

  public String getSkill() {
    return this.skill;
  }

  public void printDetails() {
    System.out.println("title: " + this.getTitle() + "\nauthor: " + this.getAuthor() +
        "\nnumber of pages: " + this.getNumPages() + "\nskill: " + this.getSkill());
  }

  Book(String title, String author, int numPages, String skill) {

    this.title = title;
    this.author = author;
    this.numPages = numPages;
    this.skill = skill;

  }

}
