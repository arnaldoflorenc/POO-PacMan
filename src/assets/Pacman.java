package assets;

import javafx.scene.canvas.GraphicsContext;

public class Pacman extends Entites {
	
	private double rotationAngle = 0;
	private static int lives = 1;
	
	public Pacman(Game game, int x, int y) {
    	super(game, Sprites.getPacmanSprite(), x, y, Mapa.getTileSize(), Mapa.getTileSize());
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
        gc.rotate(rotationAngle);  // Aplica a rotação com base no ângulo

        // Desenha o sprite rotacionado
        gc.drawImage(getSprite(), -getTamX() / 2, -getTamY() / 2, getTamX(), getTamY());  // Ajusta a posição para o centro

        // Restaura o estado gráfico
        gc.restore();
    }
    
    @Override
    public void respawn(int x, int y) {
    	if (lives > 0) {
    		lives--;
    		this.revive(x, y);
    	}
    }
    
    public static int getLives() {
    	return lives;
    }
    
}