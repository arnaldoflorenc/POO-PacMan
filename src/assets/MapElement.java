package assets;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapElement {
	protected int x = 0;
	protected int y = 0;
	
	protected int tamX = Mapa.getTileSize();
	protected int tamY = Mapa.getTileSize();
	protected Image sprite, oldSprite;
	private boolean alive = true;
	
	public MapElement(Image sprite, int x, int y, int tamX, int tamY) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.tamX = tamX;
		this.tamY = tamY;
	}
	
	public void setX(int x) { this.x = x; }
	
	public int getX() { return x; }
	
	public void setY(int y) { this.y = y; }
	
	public int getY() {	return y; }
	
	public int getTamX() { return tamX; }

	public int getTamY() { return tamY;	}
	
	public Image getSprite() { return sprite; }
	
	public void setSprite(Image sprite) { this.sprite = sprite;	}
	
	public void setPowerSprite() {
		
	}
	
	public void draw(GraphicsContext gc) {
        if (sprite != null) { 
            gc.drawImage(sprite, x, y, tamX, tamY);
           
        }
    }
	
	
	
	public boolean isColliding(MapElement a, MapElement b) {
		return a.x < b.x + b.tamX &&
			   b.x < a.x + a.tamX &&
			   a.y < b.y + b.tamY &&
			   b.y < a.y + a.tamY;
	}
	
	public boolean isCollidingPoint(MapElement a, MapElement b) {
		return a.x + 32 < b.x + b.tamX &&
			   b.x + 32 < a.x + a.tamX &&
			   a.y + 32 < b.y + b.tamY &&
			   b.y + 32 < a.y + a.tamY;
	}
	
	public boolean isColliding(int x, int y, MapElement wall) {
	    return x < wall.getX() + wall.getTamX() &&
	           x + getTamX() > wall.getX() &&
	           y < wall.getY() + wall.getTamY() &&
	           y + getTamY() > wall.getY();
	}
	
	public void kill() {
		oldSprite = getSprite();
		setSprite(null);
		this.alive = false;
	}
	
	public void revive(int x, int y) {
		setSprite(oldSprite);
		this.alive = true;
		this.setX(x);
		this.setY(y);
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setDeath() {
    	this.alive = false;
	}
}
