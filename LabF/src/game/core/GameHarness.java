package game.core;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.book.Book;
import game.book.Library;
import game.book.LoreEffect;
import game.book.SkillEffect;
import game.character.Goblin;
import game.character.Hero;

public class GameHarness {

    // Read input from the user
    private static Scanner scanner;
    
    // Game content
    private static Hero hero;
    private static Cell[][] cells;
    private static int heroX, heroY;
    private static final int WORLD_SIZE = 5;

    // create rooms
    private static void createWorld () {
        // Books to use
        Library library = new Library();
        library.addBook("sword-fighting", new Book("Sword Mastery", "K. Steel", 250, 
            new SkillEffect(Skill.SWORD_FIGHTING)));
        library.addBook("herbal-healing", new Book("Herbal Remedies", "L. Green", 180, 
            new SkillEffect(Skill.HEALING)));
        library.addBook("fire-magic", new Book("Secrets of the Arcane", "M. Rune", 320, 
            new SkillEffect(Skill.SPELLCASTING)));
        library.addBook("ancient-lore", new Book("History of the world", "An author", 2000, 
            new LoreEffect("Stuff happened")));

        // Randomly distribute enemies
        Random random = new Random();

        cells = new Cell[WORLD_SIZE][];
        int goblinCount = 1;

        List<String> bookIds = library.getSortedBookIds();
        System.out.println("Book IDs: " + bookIds);
        for (int i = 0; i < WORLD_SIZE; i++) {
            cells[i] = new Cell[WORLD_SIZE];
            for (int j = 0; j < WORLD_SIZE; j++) {
                cells[i][j] = new Cell(i, j);
                if (i == j && i < bookIds.size()) {
                    cells[i][j].addInteractable(library.getBook(bookIds.get(i)));
                }
                if (random.nextDouble() > 0.8 && !(i == WORLD_SIZE-1 && j == WORLD_SIZE-1)) {
                    cells[i][j].addInteractable(new Goblin("Gobby"+goblinCount));
                    goblinCount++;
                }
            }
        }
    }

    private static void printCells() {
        for (int i = WORLD_SIZE-1; i >= 0; i--) {
            for (int j = 0; j < WORLD_SIZE; j++) {
                if (heroX == j && heroY == i) {
                    System.out.print ("*");
                }
                // This is not an error! Need to print like this so (x,y) and North/South make sense
                System.out.print(cells[j][i] + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        System.out.println("Name your hero! (default: Rowan)");
        String name = scanner.nextLine().trim();
        if (name.length() == 0) name = "Rowan";
        hero = new Hero(name);

        createWorld ();

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
                System.out.println ("Invalid choice!");
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
        switch (direction) {
            case "N":
                newY = Math.min(heroY+1, WORLD_SIZE-1);
                break;

            case "E":
                newX = Math.min(heroX+1, WORLD_SIZE-1);
                break;

            case "S":
                newY = Math.max(heroY-1, 0);
                break;

            case "W":
                newX = Math.max(heroX-1, 0);
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

}
