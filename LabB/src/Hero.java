public class Hero {

  String name;
  String currentSkill;
  int level;

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
  public int getLevel(){
    return this.level;
  }
  public void setLevel(int value){
    this.level = value;
  }

  public void printDetails() {
    System.out.println("Hero name: " + this.getName() + "\ncurrent skill: " + this.getSkill() + "\ncurrent level: " + this.getLevel());
  }

  public void useSkill(String skillname) {
    if (this.getSkill().equals ("None")) {
      System.out.println(this.getName() + " has no skill");
    } else if (this.getSkill().equals(skillname)) {
      System.out.println(this.getName() + " uses " + skillname);
    } else {
      System.out.println(this.getName() + " does not know how to do that");
    }
  }

  public void readBook(Book book) {
    if (book.getLevel() <= this.level){
      this.currentSkill = book.getSkill();
      System.out.println(this.getName() + " has read " + book.getTitle() + " and now knows: " + book.getSkill());

      this.setLevel(this.getLevel()+1);

    } else {
      System.out.println("The Hero doesnt have high enough level!");
    }
  }

  public void forgetSkill(){
    this.currentSkill = "None";
  }

  Hero(String name) {
    this.name = name;
    this.currentSkill = "None";
    this.level = 1;
  }
}
