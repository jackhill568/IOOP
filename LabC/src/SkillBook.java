class SkillBook extends Book {

  private String skillToLearn;

  public String getSkillToLearn() {
    return this.skillToLearn;
  }

  @Override
  public String toString() {
    return super.toString() + "\nSkill to Learn: " + this.skillToLearn;
  }

  @Override
  public void doRead(Hero hero) {
    System.out.println(hero.getName() + " has read " + this.title + " and learnt " + this.getSkillToLearn());
    hero.setCurrentSkill(this.skillToLearn);
  }

  SkillBook(String title, String author, int numPages, String skillToLearn) {

    super(title, author, numPages);
    this.skillToLearn = skillToLearn;

  }
}
