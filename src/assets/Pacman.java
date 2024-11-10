package assets;

public class Pacman extends Entites {

	public Pacman(Game game, int x, int y) {
    	super(game, Sprites.getPacmanSprite("Right"), x, y, MapElement.getTileSize(), MapElement.getTileSize());
    }

    // Sobrescrevendo o método move para ter uma movimentação específica para o Pacman
    @Override
    public void move() {
        super.move();
    }
}