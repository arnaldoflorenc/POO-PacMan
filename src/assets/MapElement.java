package assets;

import java.awt.Image;

public class MapElement {
	private int x;
	private int y;
	
	private static int tileSize = 32;
	private  int tamX = tileSize;
	private  int tamY = tileSize;
	private Image sprite;
	
	private int inicialX;
	private int inicialY;
	
	public MapElement(String sprite, int x, int y, int tamX, int tamY) {
		this.sprite = Sprites.getSprite(sprite);
		this.x = x;
		this.y = y;
		this.tamX = tamX;
		this.tamY = tamY;
		this.inicialX = x;
		this.inicialY = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getTamX() {
		return tamX;
	}

	public int getTamY() {
		return tamY;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = Sprites.getSprite(sprite);
	}
	
	public static int getTileSize() {
		return tileSize;
	}
	
	public boolean isColliding(MapElement a, MapElement b) {
		return a.x < b.x + b.tamX && b.x < a.x + a.tamX && a.y < b.y + b.tamY && b.y < a.y + a.tamY;
	}
}
