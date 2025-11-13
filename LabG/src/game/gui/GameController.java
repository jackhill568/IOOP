package game.gui;


import game.book.CursedBookException;
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
        int yes = JOptionPane.showConfirmDialog(heroPanel, "Would you like to interact with " + inter, "question", JOptionPane.YES_NO_OPTION);
        if (yes==0){
         try {
            inter.interact(gameWorld.getHero());
            } catch (CursedBookException e) {
                switch (e.getCurseType()) {
                    case FORGET_SKILL -> gameWorld.getHero().forgetRandomSkill();
                    case REDUCE_HP -> gameWorld.getHero().reduceHP(3);
                    case REDUCE_MAXHP -> gameWorld.getHero().reduceMaxHP(5);
                }
            }
        }

    }


}
