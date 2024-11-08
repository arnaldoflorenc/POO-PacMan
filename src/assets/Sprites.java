package assets;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Sprites {
	
	protected static Map<String, Image> sprites;
		
	public Sprites() {
		
		sprites = new HashMap<>();
		
		sprites.put("wall", new ImageIcon(getClass().getResource("/sprites/wall.png")).getImage());
		sprites.put("blueGhost", new ImageIcon(getClass().getResource("/sprites/ghost_blue_side.gif")).getImage());
		sprites.put("orangeGhost", new ImageIcon(getClass().getResource("/sprites/ghost_orange_side.gif")).getImage());
		sprites.put("pinkGhost", new ImageIcon(getClass().getResource("/sprites/ghost_pink_side.gif")).getImage());
		sprites.put("redGhost", new ImageIcon(getClass().getResource("/sprites/ghost_red_side.gif")).getImage());
		
		sprites.put("pacmanEating", new ImageIcon(getClass().getResource("/sprites/pacman_eat.gif")).getImage());

	}
	
	public static Image getSprite(String spriteName){
		return sprites.get(spriteName);
	}
	
	public static Image getPacmanSprite(String dir) {
		
		Image spriteBase = sprites.get("pacmanEating");
        int theta = 0;
		
		switch (dir) {
			case "Up":
				theta = -90;
			    break;
			case "Down":
				theta = 90;
			    break;
			case "Left":
				theta = 180;
			    break;
			case "Right":
				theta = 0;
			    break;
			    
		}
		return rotateImage(spriteBase, theta);
	}
	
	private static Image rotateImage(Image img, int angle) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);
        g2d.setTransform(transform);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}
