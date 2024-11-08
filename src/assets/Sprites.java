package assets;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Sprites {
	
	protected static Map<String, Image> sprites;
		
	public Sprites() {
		
		sprites = new HashMap<>();
		
		sprites.put("wall", new ImageIcon(getClass().getResource("/sprites/wall.png")).getImage());
		sprites.put("blueGhost", new ImageIcon(getClass().getResource("/sprites/blueGhost.png")).getImage());
		sprites.put("orangeGhost", new ImageIcon(getClass().getResource("/sprites/orangeGhost.png")).getImage());
		sprites.put("pinkGhost", new ImageIcon(getClass().getResource("/sprites/pinkGhost.png")).getImage());
		sprites.put("redGhost", new ImageIcon(getClass().getResource("/sprites/redGhost.png")).getImage());
		
		sprites.put("pacmanUp", new ImageIcon(getClass().getResource("/sprites/pacmanUp.png")).getImage());
		sprites.put("pacmanDown", new ImageIcon(getClass().getResource("/sprites/pacmanDown.png")).getImage());
		sprites.put("pacmanLeft", new ImageIcon(getClass().getResource("/sprites/pacmanLeft.png")).getImage());
		sprites.put("pacmanRight", new ImageIcon(getClass().getResource("/sprites/pacmanRight.png")).getImage());

	}
	
	public static Image getSprite(String spriteName){
		return sprites.get(spriteName);
	}
}
