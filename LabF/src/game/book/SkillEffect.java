package game.book;

import game.character.Hero;
import game.core.Skill;

public class SkillEffect implements BookEffect{

    private final Skill skillToLearn;

    public SkillEffect(Skill skillToLearn) {
        this.skillToLearn = skillToLearn;
    }

    @Override
    public void apply(Hero hero){
        hero.addSkill(skillToLearn);
        System.out.println(hero.getName() + " has read "  + " and learns the skill: " + skillToLearn);
    }

    @Override
    public String toString() {
        return super.toString() + ", Skill: " + skillToLearn;
    }
}
