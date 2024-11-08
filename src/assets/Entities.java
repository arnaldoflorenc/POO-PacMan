package assets;

public class Entities extends MapElement{
	protected char direcao = 'R'; //R L U D
	private int velocidadeX = 0;
	private int velocidadeY = 0;
	
	public Entities(String sprite, int x, int y, int tamX, int tamY) {
		super(sprite, x, y, tamX, tamY);
	}
	
	public void updateDir(char newDir) {
		char direcaoPassada = this.direcao;
		this.direcao = newDir;
		updateVel();
		setX(getX() + velocidadeX);
		setY(getY() + velocidadeY);
		
		for (MapElement wall : Game.walls) {
			if (isColliding(this, wall)) {
				setX(getX() - velocidadeX);
				setY(getY() - velocidadeY);
				updateVel();
			}
		}
	}
	
	public void updateVel() {
		switch(this.direcao) {
		case 'R':
			this.velocidadeY = 0;
			this.velocidadeX = getTileSize()/4;
			break;
		case 'L':
			this.velocidadeY = 0;
			this.velocidadeX = -getTileSize()/4;
			break;
		case 'U':
			this.velocidadeX = 0;
			this.velocidadeY = -getTileSize()/4;
			break;
		case 'D':
			this.velocidadeX = 0;
			this.velocidadeY = getTileSize()/4;
			break;
		default:
			break;
		}
	}
	
	public void move() {
		setX(getX() + velocidadeX);
		setY(getY() + velocidadeY);
		
		for (MapElement wall : Game.walls) {
			if (isColliding(this, wall)) {
				setX(getX() - velocidadeX);
				setY(getY() - velocidadeY);
				return;
			}
		}
	}
	
	public char getDir() {
		return direcao;
	}
	
}
