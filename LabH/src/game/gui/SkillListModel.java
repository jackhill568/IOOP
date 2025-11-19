package game.gui;

import javax.swing.AbstractListModel;

import game.character.Hero;
import game.core.Skill;

public class SkillListModel extends AbstractListModel<Skill> {

    private Hero hero;

    public SkillListModel(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int getSize() {
        return hero.getSkills().size();
    }

    @Override
    public Skill getElementAt(int index) {
        return hero.getSkills().get(index);
    }

    public void update() {
        fireContentsChanged(this, 0, getSize());
    }

}
