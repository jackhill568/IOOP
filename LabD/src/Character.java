public abstract class Character {
  protected String name;
  protected int hitPoints;

  public Character(String name, int hitPoints) {
    this.name = name;
    this.hitPoints = hitPoints;
    if (hitPoints <= 0) {
      return;
    }
  }

  public String getName() {
    return name;
  }

  public int getHitPoints() {
    return this.hitPoints;
  }

  public void takeDamage(int points) {
    this.hitPoints -= (this.hitPoints > 0) ? points : 0;
  }

  public boolean isAlive() {
    return this.hitPoints > 0;
  }

  public void printDetails() {
    System.out.println(name + " (" + hitPoints + ")");
  }

  public abstract void attack(Character target);
}
