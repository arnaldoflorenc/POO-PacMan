package assets;

import java.awt.Image;

public class MapElement {
	private int x;
	private int y;
	private int z; //nao uso
	
	private static int tileSize = 32;
	private  int tamX = tileSize;
	private  int tamY = tileSize;
	private Image sprite;
	
	private int inicialX;
	private int inicialY;
	
	public MapElement(Image sprite, int x, int y, int tamX, int tamY) {
		this.sprite = sprite;
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
	
	public static int getTileSize() {
		return tileSize;
	}
}
