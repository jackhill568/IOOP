public class SkillBook extends Book {

    private Skill skillToLearn;

    public SkillBook(String title, String author, int numPages, Skill skillToLearn) {
        super(title, author, numPages);
        this.skillToLearn = skillToLearn;
    }

    @Override
    public void doRead(Hero hero) {
        hero.setCurrentSkill(skillToLearn);
        System.out.println(hero.getName() + " has read " + title + " and learns the skill: " + skillToLearn);
    }

    @Override
    public String toString() {
        return super.toString() + ", Skill: " + skillToLearn;
    }

}
