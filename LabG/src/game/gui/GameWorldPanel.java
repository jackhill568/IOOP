package game.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import game.core.Cell;
import game.core.GameWorld;
import game.core.Interactable;

public class GameWorldPanel extends JPanel {
	public static final int PANEL_SIZE = 600;
	public static final int SQUARE_SIZE = PANEL_SIZE / GameWorld.GRID_SIZE;

	public final static Random RANDOM = new Random();

	private GameWorld gameWorld;

	public GameWorldPanel(GameWorld gameWorld) {
		this.gameWorld = gameWorld;

		// GUI properties
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		setMinimumSize(new Dimension(PANEL_SIZE, PANEL_SIZE));

		// Do this or tooltips never show up
		setToolTipText("");
	}

	public void setController(GameController gameController) {
		// Keyboard commands
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new MovementAdapter(gameWorld, gameController));
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				requestFocusInWindow();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Draw the background first
		super.paintComponent(g);

		// Draw grid lines (I think this may still be buggy but it works on square grids
		// so I'll live with it)
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < (GameWorld.GRID_SIZE - 1); i++) {
			int yPos = (i + 1) * SQUARE_SIZE;
			int xPos = (i + 1) * SQUARE_SIZE;
			g.drawLine(xPos, 0, xPos, PANEL_SIZE);
			g.drawLine(0, yPos, PANEL_SIZE, yPos);
		}

		// Draw all of the interactable objects
		for (int i = 0; i < GameWorld.GRID_SIZE; i++) {
			for (int j = 0; j < GameWorld.GRID_SIZE; j++) {
				Cell cell = gameWorld.getCell(i, j);
				for (Interactable interactable : cell.getInteractables()) {
					if (interactable instanceof Renderable) {
						Renderer.render((Renderable)interactable, i, j, g);
					}
				}
			}
		}

		// Draw the hero
		Renderer.render(gameWorld.getHero(), gameWorld.getHeroX(), 
			gameWorld.getHeroY(), g);
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		int cellX = event.getX() / SQUARE_SIZE;
		int cellY = event.getY() / SQUARE_SIZE;
		cellY = GameWorld.GRID_SIZE - cellY - 1;
		if (cellX >= GameWorld.GRID_SIZE || cellY >= GameWorld.GRID_SIZE) {
			return null;
		}
		List<String> texts = new ArrayList<>();
		if (gameWorld.getHeroX() == cellX && gameWorld.getHeroY() == cellY) {
			texts.add(gameWorld.getHero().getToolTipText());
		}
		Cell ttCell = gameWorld.getCell(cellX, cellY);
		for (Interactable inter : ttCell.getInteractables()) {
			texts.add(((Renderable)inter).getToolTipText());
		}
		if (texts.isEmpty()) {
			return null;
		} else {
			return texts.toString();
		}
	}

}
