package game.character;

import java.awt.Color;
import java.util.Random;

import game.gui.GameWorldPanel;

public class Goblin extends Character {

    private static final Random random = new Random();

    public Goblin(String name) {
        super(name, random.nextInt(5) + 5);
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

    @Override
    public String getSpriteKey() {
        return isAlive() ? "goblin" : "goblin-fainted";
    }

    @Override
    public int getRenderSize() {
        return GameWorldPanel.SQUARE_SIZE / 3;
    }

    @Override
    public String getToolTipText() {
        return name + " (" + hitPoints + ")";
    }

    @Override
    public Color getColor() {
        return Color.GREEN.darker().darker();
    }

}
