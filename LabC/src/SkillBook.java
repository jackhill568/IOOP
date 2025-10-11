class SkillBook extends Book {

  private String skillToLearn;

  public String getSkillToLearn() {
    return this.skillToLearn;
  }

  @Override
  public String toString() {
    return super.toString() + "\nSkill to Learn: " + this.skillToLearn;
  }

  SkillBook(String title, String author, int numPages, String skillToLearn) {

    super(title, author, numPages);
    this.skillToLearn = skillToLearn;

  }
}
