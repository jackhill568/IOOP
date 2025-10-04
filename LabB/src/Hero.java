public class Hero {

  String name;
  String currentSkill;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSkill() {
    return this.currentSkill;
  }

  public void setSkill(String skill) {
    this.currentSkill = skill;
  }

  public void printDetails() {
    System.out.println("Hero name: " + this.getName() + "\ncurrent skill: " + this.getSkill());
  }

  public void useSkill(String skillname) {
    if (this.getSkill().equals(skillname)) {
      System.out.println(this.getName() + " uses " + skillname);
    } else {
      System.out.println(this.getName() + " does not know how to do that");
    }
  }

  public void readBook(Book book) {
    this.currentSkill = book.getSkill();
    System.out.println(this.getName() + " has read " + book.getTitle() + "and now knows: " + book.getSkill());
  }

  Hero(String name) {
    this.name = name;
    this.currentSkill = "None";
  }
}
