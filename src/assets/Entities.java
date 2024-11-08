package assets;

public class Entities extends MapElement{
	protected char direcao = 'R'; //R L U D
	protected char direcaoDesejada = 'R';
	protected char direcaoPassada = 'R';
	public int velocidadeX = 0;
	public int velocidadeY = 0;
	
	public Entities(String sprite, int x, int y, int tamX, int tamY) {
		super(sprite, x, y, tamX, tamY);
	}
	
	public boolean canMove() {
		Entities projection = new Entities(null, this.getX(), this.getY(), this.getTamX(), this.getTamY());
		boolean colisao = false;
		projection.direcaoDesejada = this.direcaoDesejada;
		projection.velocidadeX = this.velocidadeX;
		projection.velocidadeY = this.velocidadeY;
		projection.updateVel(direcaoDesejada);
		projection.setX(projection.getX() + projection.velocidadeX);
		projection.setY(projection.getY() + projection.velocidadeY);
		updateVel(this.direcao);  //evita q o pacman trave ao receber ordem de direcao
		for (MapElement wall : Game.walls) {
			if (isColliding(projection, wall)) {
				colisao = true;
				break;
			}
		}
		if(!colisao) {
			return true;
		}
		return false;
	}

	public void updateDir() {
		updateVel(this.direcaoDesejada);
		this.direcao = this.direcaoDesejada;

	}
	
	public void setDir(char dir) {
		this.direcaoDesejada = dir;
		this.direcaoPassada = this.direcao;
	}
	
	public void updateVel(char dir) {
		switch(dir) {
		case 'R':
			this.velocidadeY = 0;
			this.velocidadeX = getTileSize()/8;
			this.setPacmanSprite("Right");
			break;
		case 'L':
			this.velocidadeY = 0;
			this.velocidadeX = -getTileSize()/8;
			this.setPacmanSprite("Left");
			break;
		case 'U':
			this.velocidadeX = 0;
			this.velocidadeY = -getTileSize()/8;
			this.setPacmanSprite("Up");
			break;
		case 'D':
			this.velocidadeX = 0;
			this.velocidadeY = getTileSize()/8;
			this.setPacmanSprite("Down");
			break;
		default:
			break;
		}
	}
	
	
	
	public boolean directionChanged() {
		if (this.direcao != this.direcaoDesejada) {
			
			return true;
		}
		return false;
	}
	
	public void move() {
		if (getX() >= (Game.getGameXsize() - 32)) {
			setX(getX() - (Game.getGameXsize() - 32) + velocidadeX);
		}
		else if (getX() <= 0) {
			setX(getX() + (Game.getGameXsize() - 32) + velocidadeX);
		}
		else {
			setX(getX() + velocidadeX);
		}
		if (getY() >= Game.getGameYsize() - 32) {
			setY(getY() - (Game.getGameYsize() - 32) + velocidadeY);
		}
		else if (getY() <= 0) {
			setY(getY() + (Game.getGameYsize() - 32) + velocidadeY);
		}
		else {
			setY(getY() + velocidadeY);
		}
		
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
	
	public char getPDir() {
		return direcaoPassada;
	}
	
}
