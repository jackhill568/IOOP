package game.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import game.core.GameWorld;

public class GameWindow extends JFrame {
	private static final GameWindow _INSTANCE = new GameWindow();
	private GameWorld gameWorld;
	private GameWorldPanel gameWorldPanel;
	private ImageIcon icon;

	public static GameWindow getInstance() {
		return _INSTANCE;
	}

	private GameWindow() {
		super("Library of Legends");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// Don't care, it'll just not look like a native application
		}

		icon = null;
		try {
			BufferedImage iconImage = ImageIO.read(Renderer.class.getClassLoader().getResourceAsStream("game/images/hero_icon.png"));
			setIconImage(iconImage);
			icon = new ImageIcon(iconImage.getScaledInstance(25, -1, 0));
		} catch (IOException | IllegalArgumentException e) {
			// Don't care, it just won't have a cool icon
		}

		Object name = JOptionPane.showInputDialog(this, "Name the hero", "What is your name?", 
			JOptionPane.QUESTION_MESSAGE, icon, null, "Rowan");
		if (name == null) {
			System.exit(0);
		}
		
		gameWorld = new GameWorld((String)name);

		gameWorldPanel = new GameWorldPanel(gameWorld);
		
		JTextArea logArea = new JTextArea(10, 80);
		logArea.setEditable(false);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		JScrollPane logScroll = new JScrollPane(logArea);

		System.setOut(new PrintStream(new JTextAreaOutputStream(logArea)));

		HeroPanel heroPanel = new HeroPanel(gameWorld.getHero());

		GameController gameController = new GameController(gameWorld, heroPanel, gameWorldPanel);
		gameWorldPanel.setController(gameController);
		
		Box topBox = Box.createHorizontalBox();
		topBox.add(gameWorldPanel);
		topBox.add(heroPanel);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(topBox);
		getContentPane().add(logScroll);
		pack();
	}

	public GameWorldPanel getGameWorldPanel() {
		return this.gameWorldPanel;
	}

	public GameWorld getGameWorld() {
		return this.gameWorld;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}

    public void gameOver() {
		JOptionPane.showMessageDialog(this, "YOU DIED.", "Game over", JOptionPane.ERROR_MESSAGE, icon);
		System.exit(0);
    }

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_INSTANCE.setVisible(true);
			}
		});
	}


}
