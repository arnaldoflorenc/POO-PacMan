package assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.animation.*;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class Pacman extends Entites {
	
	private double rotationAngle = 0;
	private static int lives = 3;

	protected static AudioClip deathSound;
	
	public Pacman(Game game, int x, int y) {
    	super(game, Sprites.getPacmanSprite(), x, y, Mapa.getTileSize(), Mapa.getTileSize());
    	
    	deathSound = new AudioClip(Pacman.class.getResource("/sounds/death_0.wav").toString());
    }

    // Sobrescrevendo o método move para ter uma movimentação específica para o Pacman
    @Override
    public void move() {
        super.move();
        
        switch(getdir()) {
        case UP:
        	rotationAngle = 270;
        	break;
        case DOWN:
        	rotationAngle = 90;
        	break;
        case LEFT:
        	rotationAngle = 180;
        	break;
        case RIGHT:
        	rotationAngle = 0;
        	break;
        default:
        	rotationAngle = 0;
        	break;
        }
    }
    
    
    @Override
    public void draw(GraphicsContext gc) {
        // Salva o estado atual do GraphicsContext
        gc.save();

        // Translada para o centro do Pacman (para aplicar rotação no centro)
        gc.translate(getX() + getTamX() / 2, getY() + getTamY() / 2);
        
        
        if (isAlive()) {
            gc.rotate(rotationAngle);  // Aplica a rotação somente quando está vivo
        }

        // Desenha o sprite rotacionado
        gc.drawImage(getSprite(), -getTamX() / 2, -getTamY() / 2, getTamX(), getTamY());  // Ajusta a posição para o centro

        // Restaura o estado gráfico
        gc.restore();
    }
    
    @Override
    public void respawn(int x, int y) {
    	if (lives > 0) {
            lives--;
                // Revive o Pacman, redefinindo o sprite para o original
                this.setAlive();
                this.revive(x, y);
                //setSprite(Sprites.getPacmanSprite());
        }
    }
    
    
    
    public static int getLives() {
    	return lives;
    }
    
    public static void resetLives() {
    	lives = 3;
    }
    
}