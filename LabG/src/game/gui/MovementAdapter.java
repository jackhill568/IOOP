package game.gui;

import game.character.Character;
import game.core.Cell;
import game.core.Direction;
import game.core.GameWorld;
import game.core.Interactable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovementAdapter extends KeyAdapter {

    private GameWorld gameWorld;
    private GameController gameController;

    public MovementAdapter(GameWorld gameWorld, GameController gameController) {
        this.gameWorld = gameWorld;
        this.gameController = gameController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case 'W':
                moveHero(Direction.NORTH);
                break;

            case KeyEvent.VK_RIGHT:
            case 'D':
                moveHero(Direction.EAST);
                break;

            case KeyEvent.VK_DOWN:
            case 'S':
                moveHero(Direction.SOUTH);
                break;

            case KeyEvent.VK_LEFT:
            case 'A':
                moveHero(Direction.WEST);
                break;
        }
    }

    public void moveHero(Direction direction) {
        if (!gameWorld.getHero().isAlive()) {
            return;
        }

        gameWorld.getHero().setDirection(direction);

        int heroX = gameWorld.getHeroX();
        int heroY = gameWorld.getHeroY();

        int newX = heroX, newY = heroY;
        switch (direction) {
            case NORTH -> newY = Math.min(heroY + 1, GameWorld.GRID_SIZE - 1);
            case EAST  -> newX = Math.min(heroX + 1, GameWorld.GRID_SIZE - 1);
            case SOUTH -> newY = Math.max(heroY - 1, 0);
            case WEST  -> newX = Math.max(heroX - 1, 0);
        }
        if (newX != heroX || newY != heroY) {
            gameWorld.updateHeroLocation(newX, newY);
            Cell heroCell = gameWorld.getCell(gameWorld.getHeroX(),
                    gameWorld.getHeroY());
            for (Interactable inter : heroCell.getInteractables()) {
                // Ignore fainted goblins and fainted hero
                if ((inter instanceof Character) && !((Character)inter).isAlive()) continue;
                if (!gameWorld.getHero().isAlive()) break;

                // Replace this code with a call to GameController.handleInteraction (Task 2)

                gameController.handleInteraction(inter);
               
            }
            gameController.updateUI();
        }
    }

}
