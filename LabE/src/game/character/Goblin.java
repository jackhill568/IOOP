package game.character;

import game.core.Interactable;
public class Goblin extends Character implements Interactable {

  public Goblin(String name) {
    super(name, 10);
  }

  @Override
  public String toString() {
    return "A goblin called " + this.name + " with HP " + hitPoints;
  }
  @Override
  public String getSummaryString() {
	  return this.name;
  }

  @Override
  public void interact(Hero hero) {
    hero.doBattle(this);

  }
  @Override
  public void attack(Character target) {
    System.out.println(name + " swings a club at " + target.name + " for 3 damage!");
    target.takeDamage(3);
  }

}
