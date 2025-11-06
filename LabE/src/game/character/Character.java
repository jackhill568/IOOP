package game.character;

public abstract class Character {
  protected String name;
  protected int hitPoints;

  public Character(String name, int hitPoints) {
    this.name = name;
    this.hitPoints = hitPoints;
    if (hitPoints < 0)
      hitPoints = 0;
  }

  public String getName() {
    return name;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public void doBattle(Character enemy) {
    this.printDetails();

    while (this.isAlive() && enemy.isAlive()) {
      if (this.isAlive()) {
        this.attack(enemy);
      }
      if (enemy.isAlive()) {
        enemy.attack(this);
      }
    }
    if (this.isAlive()) {
      System.out.println("Enemy has been defeated!");
    }
  }

  public void takeDamage(int points) {
    hitPoints -= points;
    if (hitPoints < 0)
      hitPoints = 0;
  }
  public void printDetails() {
 	System.out.println("asdas"); 
  }

  public boolean isAlive() {
    return this.hitPoints > 0;
  }

  @Override
  public String toString() {
    return name + " (" + hitPoints + ")";
  }

  public abstract void attack(Character target);
}
