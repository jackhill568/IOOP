public class Goblin extends Character {

    public Goblin(String name) {
        super(name, 10);
    }

    @Override
    public void printDetails() {
        System.out.println("A goblin called " + this.name + " with HP " + hitPoints);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " swings a club at " + target.name + " for 3 damage!");
        target.takeDamage(3);
    }

}
