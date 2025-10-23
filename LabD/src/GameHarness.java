import java.util.Scanner;

public class GameHarness {

  // Read input from the user
  private static Scanner scanner;

  // Game content
  private static Hero hero;
  private static Book[] books;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);

    System.out.println("Name your hero! (default: Rowan)");
    String name = scanner.nextLine().trim();
    if (name.length() == 0)
      name = "Rowan";
    hero = new Hero(name);

    books = new Book[4];
    books[0] = new SkillBook("Sword Mastery", "K. Steel", 250, Skill.SWORD_FIGHTING);
    books[1] = new SkillBook("Herbal Remedies", "L. Green", 180, Skill.HEALING);
    books[2] = new SkillBook("Secrets of the Arcane", "M. Rune", 320, Skill.SPELLCASTING);
    books[3] = new LoreBook("History of the world", "An author", 2000, "Stuff happened");

    boolean loop = true;
    while (loop) {
      System.out.println();
      hero.printDetails();
      System.out
          .println("Make a choice:\n0. Exit game\n1. Read a book\n2. Use a skill\n3. Print journal\n4. Battle enemy");
      try {
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
          case 0:
            loop = false;
            break;

          case 1:
            readBook();
            break;

          case 2:
            useSkill();
            break;

          case 3:
            hero.printJournal();
            break;

          case 4:
            doBattle();
            if (!hero.isAlive()) {
              System.out.println("YOU DIED.");
              loop = false;
            }
            break;

          default:
            throw new RuntimeException();
        }
      } catch (RuntimeException ex) {
        System.out.println("Invalid choice!");
      }
    }

    scanner.close();
  }

  private static void useSkill() {
    System.out.println("Enter the skill to use");
    Skill skillName = Skill.valueOf(scanner.nextLine().trim());
    hero.useSkill(skillName);
  }

  private static void readBook() {
    System.out.println("Choose a book");
    for (int i = 0; i < books.length; i++) {
      System.out.print(i + 1 + ". ");
      books[i].printDetails();
    }
    try {
      int choice = Integer.parseInt(scanner.nextLine());
      if (choice > 0 && choice <= books.length) {
        hero.readBook(books[choice - 1]);
      } else {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid choice");
    }
  }

  private static void doBattle() {
    Character enemy = new Rat("Rat");
    System.out.println("A wild enemy appears:");
    enemy.printDetails();

    while (hero.isAlive() && enemy.isAlive()) {
      if (hero.isAlive()) {
        hero.attack(enemy);
      }
      if (enemy.isAlive()) {
        enemy.attack(hero);
      }

    }

    if (hero.isAlive()) {
      System.out.println("Enemy has been defeated!");
    }
  }
}
