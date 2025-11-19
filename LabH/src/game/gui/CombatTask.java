package game.gui;

import game.character.Goblin;
import game.character.Hero;
import game.core.GameWorld;

public class CombatTask implements Runnable {

    private Hero hero;
    private Goblin goblin;
    private HeroPanel heroPanel;
    private GameController gameController;
    private GameWorld gameWorld;

    public CombatTask (Goblin goblin, HeroPanel heroPanel, GameController gameController, GameWorld gameWorld) {
        this.hero = gameWorld.getHero();
        this.goblin = goblin;
        this.heroPanel = heroPanel;
        this.gameController = gameController;
        this.gameWorld = gameWorld;
    }

    @Override
    public void run() {
        if (!hero.isAlive() && !goblin.isAlive()) return;

        // TODO write your implementation to the first part of Task 1 here
        while (hero.isAlive() && goblin.isAlive()){
            hero.attack(goblin);
            System.out.println("Hero attacks");
            if (!hero.isAlive() && !goblin.isAlive()) { 
                System.out.println("Enemy has been defeated");
                return;
            }

            heroPanel.enableDodge(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // System.out.println("Exception: " + e);
            }

            heroPanel.enableDodge(false);

            if (gameController.hasDodged()) {
                goblin.takeDamage(2);
                System.out.println("Hero dodged and the goblin lost 2 hp");
            } else {
                goblin.attack(hero);
                System.out.println("the hero failed to dodge and got hit by the goblin");
            }
        }

        if (!hero.isAlive()) {
            System.out.println("Hero has fainted");
        }
        if (!goblin.isAlive()) {
            System.out.println("Enemy has been defeated!");
            this.gameWorld.enemyDefeated();
            GameWindow.getInstance().checkEnemyState();
        }
    }

}
