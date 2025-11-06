package game.core;

import java.util.Random;
import java.util.Scanner;


import game.book.*;
import game.character.*;
import game.character.Character;
import game.core.*;


public class GameHarness {

  // Read input from the user
  private static Scanner scanner;

  // Game content
  private static Hero hero;
  private static Cell[][] cells;
  private static int heroX, heroY;
  private static final int WORLD_SIZE = 5;

  // create rooms
  private static void createWorld() {
    // Books to use
    Book[] books = new Book[4];
    books[0] = new SkillBook("Sword Mastery", "K. Steel", 250, Skill.SWORD_FIGHTING);
    books[1] = new SkillBook("Herbal Remedies", "L. Green", 180, Skill.HEALING);
    books[2] = new SkillBook("Secrets of the Arcane", "M. Rune", 320, Skill.SPELLCASTING);
    books[3] = new LoreBook("History of the world", "An author", 2000, "Stuff happened");

    // Randomly distribute enemies
    Random random = new Random();

    cells = new Cell[WORLD_SIZE][];
    int goblinCount = 1;

    for (int i = 0; i < WORLD_SIZE; i++) {
      cells[i] = new Cell[WORLD_SIZE];
      for (int j = 0; j < WORLD_SIZE; j++) {
        cells[i][j] = new Cell(i, j);
        if (i == j && i < books.length) {
          // Comment out the next line if you want to test the new GameHarness before
          // starting Lab E
          cells[i][j].addInteractable(books[i]);
        }
        if (random.nextDouble() > 0.8 && !(i == WORLD_SIZE - 1 && j == WORLD_SIZE - 1)) {
          // Comment out the next line if you want to test the new GameHarness before
          // starting Lab E
          cells[i][j].addInteractable(new Goblin("Gobby" + goblinCount));
          goblinCount++;
        }
      }
    }
  }

  private static void printCells() {
    for (int i = WORLD_SIZE - 1; i >= 0; i--) {
      for (int j = 0; j < WORLD_SIZE; j++) {
        if (heroX == j && heroY == i) {
          System.out.print("*");
        }
        // This is not an error! Need to print like this so (x,y) and North/South make
        // sense
        System.out.print(cells[j][i] + ", ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    scanner = new Scanner(System.in);

    System.out.println("Name your hero! (default: Rowan)");
    String name = scanner.nextLine().trim();
    if (name.length() == 0)
      name = "Rowan";
    hero = new Hero(name);

    createWorld();

    heroX = 4;
    heroY = 4;
    updateHeroLocation();

    boolean loop = true;
    while (loop) {
      System.out.println();
      System.out.println(heroX + "," + heroY + ": " + hero);
      System.out.println("Make a choice:\n0. Exit game\n1. Heal\n2. Print journal\n3. Print world\n4. Move");
      try {
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
          case 0:
            loop = false;
            break;

          case 1:
            // Comment out the next line if you want to test the new GameHarness before
            // starting Lab E
            hero.heal();
            break;

          case 2:
            hero.printJournal();
            break;

          case 3:
            printCells();
            break;

          case 4:
            moveHero();
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

  private static void moveHero() {
    if (!hero.isAlive()) {
      System.out.println("Hero has fainted and cannot move");
      return;
    }

    System.out.println("Enter move direction (N, E, S, W)");
    String direction = scanner.nextLine().trim();
    int newX = heroX, newY = heroY;
    switch (direction.toUpperCase()) {
      case "N":
        newY = Math.min(heroY + 1, WORLD_SIZE - 1);
        break;

      case "E":
        newX = Math.min(heroX + 1, WORLD_SIZE - 1);
        break;

      case "S":
        newY = Math.max(heroY - 1, 0);
        break;

      case "W":
        newX = Math.max(heroX - 1, 0);
        break;
    }
    if (newX != heroX || newY != heroY) {
      heroX = newX;
      heroY = newY;
      updateHeroLocation();
    }
  }

  private static void updateHeroLocation() {
    System.err.println("Hero is now at location (" + heroX + "," + heroY + ")");
    for (Interactable inter : cells[heroX][heroY].getInteractables()) {
      inter.interact(hero);
    }
  }

  // This method is not used any more in the current version of GameHarness but is
  // left
  // here because it will be useful for Task 3 of Lab E.

  private static void doBattle() {
    Character enemy = new Goblin("Buddy");
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
