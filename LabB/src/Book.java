
public class Book {

  String title;
  String author;
  int numPages;
  String skill;
  int levelRequirement;

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

  public int getLevel(){
    return this.levelRequirement;
  }
  public void printDetails() {
    System.out.println("title: " + this.getTitle() + "\nauthor: " + this.getAuthor() +
        "\nnumber of pages: " + this.getNumPages() + "\nskill: " + this.getSkill() +
        "\nrequired level to read: " + this.getLevel());
  }

  Book(String title, String author, int numPages, String skill) {

    this.title = title;
    this.author = author;
    this.numPages = numPages;
    this.skill = skill;
    this.levelRequirement = 1;

  }

}
