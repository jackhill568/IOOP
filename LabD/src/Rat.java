public class Rat extends Character {
  int hitCounter;

  public Rat(String name) {
    super(name, 35);
    this.hitCounter = 0;
  }

  @Override
  public void printDetails() {
    System.out.println("A Rat called " + this.name + " with HP " + hitPoints);
  }

  @Override
  public void attack(Character target) {
    System.out.println(name + " throws cheese at " + target.name + " for " + 4 + 2 * this.hitCounter + " damage!");
    target.takeDamage(4 + 2 * this.hitCounter++);
  }

}
