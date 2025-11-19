package game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.core.GameWorld;

public class Renderer {

    private static final Map<String, Image> IMAGES;
    static {
        IMAGES = new HashMap<>();
        String[] fileNames = { "book-open", "book-closed", "hero", "hero-fainted", "hero-flipped", "goblin", "goblin-fainted" };
        for (String fileName : fileNames) {
            try {
                IMAGES.put (fileName, ImageIO.read(Renderer.class.getClassLoader().getResourceAsStream("game/images/" + fileName + ".png")));
            } catch (IOException | IllegalArgumentException e) {
                // Oh well that image doesn't exist
            }
        }
    }

    public static void render (Renderable renderable, int gridX, int gridY, Graphics g) {
        int realX = gridX * GameWorldPanel.SQUARE_SIZE;
        int realY = (GameWorld.GRID_SIZE - gridY - 1) * GameWorldPanel.SQUARE_SIZE;
        Image image = IMAGES.getOrDefault(renderable.getSpriteKey(), null);
        int size = renderable.getRenderSize();
        int renderX = realX + (GameWorldPanel.SQUARE_SIZE - size)/2;
        int renderY = realY + (GameWorldPanel.SQUARE_SIZE - size)/2;
        if (image != null) {
            boolean landscape = (image.getWidth(null) > image.getHeight(null));
            if (landscape) {
                image = image.getScaledInstance(size, -1, 0);
            } else {
                image = image.getScaledInstance(-1, size, 0);
            }
            g.drawImage(image, renderX, renderY, null);
        } else {
            g.setColor(renderable.getColor());
            g.fillRect(renderX, renderY, size, size);
            g.setColor(Color.white);
            g.drawRect(renderX, renderY, size, size);
        }
    }

}
