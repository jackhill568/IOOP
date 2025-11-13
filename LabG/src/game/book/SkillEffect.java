package game.book;

import game.character.Hero;
import game.core.Skill;

public class SkillEffect implements BookEffect {

    private Skill skill;

    public SkillEffect(Skill skill) {
        this.skill = skill;
    }

    @Override
    public void apply(Hero hero) {
        hero.addSkill(skill);
    }

}
