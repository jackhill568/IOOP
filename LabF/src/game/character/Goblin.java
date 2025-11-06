package game.character;

import game.core.Interactable;

public class Goblin extends Character implements Interactable {

    public Goblin(String name) {
        super(name, 10);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " swings a club at " + target.name + " for 3 damage!");
        target.takeDamage(3);
    }

    @Override
    public void interact(Hero hero) {
        System.out.println("Hero should battle");
        System.out.println(hero + " will battle " + this);

        while (hero.isAlive() && this.isAlive()) {
            if (hero.isAlive()) {
                hero.attack (this);
            }
            if (this.isAlive()) {
                this.attack (hero);
            }
        }

        if (hero.isAlive()) {
            System.out.println("Enemy has been defeated!");
        } else {
            System.out.println("Hero has fainted");
        }
    }

    @Override
    public String toString() {
        return "A goblin named " + name + " (" + hitPoints + ")";
    }

    public String getSummaryString() {
        if (isAlive()) {
            return name;
        } else {
            return "{" + name + "}";
        }
    }

}
