package game.character;

import game.core.Interactable;
import game.gui.Renderable;

public abstract class Character implements Interactable, Renderable {
  private static final int _0 = 0;
  protected String name;
  protected int hitPoints;

  public Character(String name, int hitPoints) {

    this.hitPoints = hitPoints;
    if (hitPoints < 0)
      hitPoints = _0;
  }

  public String getName() {
    return name;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public void takeDamage(int points) {
    hitPoints -= points;
    if (hitPoints < 0)
      hitPoints = 0;
  }

  public boolean isAlive() {
    return this.hitPoints > 0;
  }

  @Override
  public String toString() {
    return name + " (" + hitPoints + ")";
  }

  public abstract void attack(Character target);

  public String getSummaryString() {
    if (isAlive()) {
      return name;
    } else {
      return "{" + name + "}";
    }
  }

  public boolean isInteractable() {
    return isAlive();
  }

}
