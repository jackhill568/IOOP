package game.gui;

import game.book.CursedBookException;
import game.character.Goblin;
import game.core.GameWorld;
import game.core.Interactable;
import javax.swing.JOptionPane;

public class GameController {

    private GameWorld gameWorld;
    private HeroPanel heroPanel;
    private GameWorldPanel gameWorldPanel;

    public GameController(GameWorld gameWorld, HeroPanel heroPanel, GameWorldPanel gameWorldPanel) {
        this.gameWorld = gameWorld;
        this.heroPanel = heroPanel;
        this.gameWorldPanel = gameWorldPanel;
    }

    public void updateUI() {
        heroPanel.update();
        gameWorldPanel.repaint();
    }

    public void handleInteraction(Interactable inter) {
        if (inter instanceof Goblin) {
            startCombat((Goblin)inter);
        } else {
            int choice = JOptionPane.showConfirmDialog(GameWindow.getInstance(), inter.getSummaryString(),
                    "Do you want to interact?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    GameWindow.getInstance().getIcon());
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    inter.interact(gameWorld.getHero());
                } catch (CursedBookException e) {
                    switch (e.getCurseType()) {
                        case FORGET_SKILL -> gameWorld.getHero().forgetRandomSkill();
                        case REDUCE_HP -> gameWorld.getHero().reduceHP(3);
                        case REDUCE_MAXHP -> gameWorld.getHero().reduceMaxHP(5);
                    }
                    JOptionPane.showMessageDialog(GameWindow.getInstance(), e.getMessage(), "You have been cursed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private Thread combatThread;
    private boolean dodged;

    public void startCombat (Goblin goblin) {
        this.dodged = false;
        combatThread =new Thread(new CombatTask(goblin, this.heroPanel, this, this.gameWorld));
        combatThread.start();
    }
       
    public void registerDodge() {
        this.dodged = true;
        // TODO extend this method for Task 1
        if (combatThread.isAlive()) {
            this.dodged = true;
            combatThread.interrupt();
        }
    }

    public boolean hasDodged() {
        return dodged;
    }
}
