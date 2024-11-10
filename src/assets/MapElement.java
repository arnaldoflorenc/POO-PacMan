package assets;

import javafx.scene.image.Image;

public class MapElement {
	private int x = 0;
	private int y = 0;
	
	private final static int tileSize = 32;
	private  int tamX = tileSize;
	private  int tamY = tileSize;
	private Image sprite;
	
	//private int inicialX;
	//private int inicialY;
	
	public MapElement(String sprite, int x, int y, int tamX, int tamY) {
		this.sprite = Sprites.getSprite(sprite);
		this.x = x;
		this.y = y;
		this.tamX = tamX;
		this.tamY = tamY;
		//this.inicialX = x;
		//this.inicialY = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public int getY() {
		return y;
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
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setPacmanSprite(String sprite) {
		
	}
	
	public void setGhostSprite() {
		
	}
	
	public void setPowerSprite() {
		
	}
	
	public void eraseSprite() {
		this.sprite = null;
	}
	
	public static int getTileSize() {
		return tileSize;
	}
	
	public boolean isColliding(MapElement a, MapElement b) {
		return a.x < b.x + b.tamX && b.x < a.x + a.tamX && a.y < b.y + b.tamY && b.y < a.y + a.tamY;
	}
	
	public boolean isColliding(int x, int y, MapElement wall) {
	    return x < wall.getX() + wall.getTamX() &&
	           x + getTamX() > wall.getX() &&
	           y < wall.getY() + wall.getTamY() &&
	           y + getTamY() > wall.getY();
	}
	
	
}
