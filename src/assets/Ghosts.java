package assets;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class Ghosts extends Entites {

	// Definindo o comportamento de cada fantasma

	private String ghostType;
	private int ghostx, ghosty;
	private int seguir;

	public Ghosts(Game game, String ghost, int x, int y) {
		super(game, Sprites.getGhostSprite("Right", ghost), x, y, Mapa.getTileSize(), Mapa.getTileSize());
		this.ghostx = x;
		this.ghosty = y;
		this.ghostType = ghost;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
			
        gc.drawImage(sprite, x, y, tamX, tamY);
    }
	
	// Método estático para pegar o sprite baseado no tipo de fantasma

	
	public void move(int pacmanX, int pacmanY) {
		
		if (pacmanIsClose(pacmanX, pacmanY)) {
			folowPacMan(pacmanX, pacmanY);
		} else {
			movRandon();
		}
	}

	public boolean pacmanIsClose(int px, int py) {
		return Math.abs(this.ghostx - px) + Math.abs(this.ghosty - py) <= 6;
	}

	public void folowPacMan(int px, int py) {
		if (Math.abs(this.ghostx - px) > Math.abs(this.ghosty - py)) {
			seguir = this.ghostx > px ? 3 : 1;
		} else {
			seguir = this.ghosty > py ? 0 : 2;
		}

		Moverdir(seguir);
	}

	public void movRandon() {
		Random random = new Random();
		seguir = random.nextInt(4);
		Moverdir(seguir);
	}
	
	public void Moverdir(int seguir) {
		
		switch(seguir) {
			case 0:
				super.move(-1, 0);//cima
				this.sprite = Sprites.getGhostSprite(getTipo(), "Up");
				break;
			case 1:
				super.move(0,1);//direita
				this.sprite = Sprites.getGhostSprite(getTipo(), "Right");
				break;
			case 2:
				super.move(0,-1);//esquerda
				this.sprite = Sprites.getGhostSprite(getTipo(), "Left");
				break;
			case 3:
				super.move(1,0);//baixo
				this.sprite = Sprites.getGhostSprite(getTipo(), "Down");
				break;
		}
	}

	public String getTipo() {
		return ghostType;
	}
}