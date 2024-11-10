package assets;

public class Entites extends MapElement{
	
	protected enum Action {
		UP(0,-1),
		DOWN(0,1),
		LEFT(-1,0),
		RIGHT(1,0),
		DEFAULT(0,0);
		
		final int dx,dy;
		
		Action(int dx, int dy){
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	protected Action Acao_esperada = Action.DEFAULT;
	protected Action Acao_passada = Action.DEFAULT;
	protected Action Acao_atual = Action.DEFAULT;
	
	public int velocidadeX = 0;
	public int velocidadeY = 0;
	
	public Entites (String sprite, int x, int y, int tamanhoX, int tamanhoY){
		super(sprite, x, y, tamanhoX, tamanhoY);
	}
	
	public boolean canMove() {
		
		Entites projection = new Entites(null, this.getX(), this.getY(), this.getTamX(), this.getTamY());
		boolean colisao = false;
		projection.Acao_esperada = this.Acao_esperada;
		projection.velocidadeX = this.velocidadeX;
		projection.velocidadeY = this.velocidadeY;
		projection.updateVel(Acao_esperada);
		projection.setX(projection.getX() + projection.velocidadeX);
		projection.setX(projection.getY() + projection.velocidadeY);
		updateVel(this.Acao_atual);
		
		for (MapElement wall : Game.walls) {
			if (isColliding(projection, wall)) {
				colisao = true;
				break;
			}
		}
		
		return !colisao;
	}

	public void updateDir() {
		updateVel(this.Acao_esperada);
		this.Acao_atual = Acao_esperada;
	}
	
	public void setDir(Action dir){
		this.Acao_esperada = dir;
		this.Acao_passada = Acao_atual;
	}

	private void updateVel(Action acao_esp2) {
		switch (acao_esp2) {
			case UP:
				this.velocidadeX = 0;
				this.velocidadeY = -getTileSize()/8;
				this.setPacmanSprite("Up");
				break;
			case DOWN:
				this.velocidadeX = 0;
				this.velocidadeY = getTileSize()/8;
				this.setPacmanSprite("Down");
				break;
			case LEFT:
				this.velocidadeY = 0;
				this.velocidadeX = -getTileSize()/8;
				this.setPacmanSprite("Left");
				break;
			case RIGHT:
				this.velocidadeY = 0;
				this.velocidadeX = getTileSize()/8;
				this.setPacmanSprite("Right");
				break;
		}
		
	}
	
	public boolean directionChange() {
		return this.Acao_atual != this.Acao_esperada;
	}
	
	public void move() {
	    int newX = getX() + velocidadeX;
	    int newY = getY() + velocidadeY;

	    if (!isCollidingWithWalls(newX, newY)) {
	        setX(newX);
	        setY(newY);
	    }
	}
	
	private boolean isCollidingWithWalls(int newX, int newY) {
	    for (MapElement wall : Game.walls) {
	        if (isColliding(newX, newY, wall)) {
	            return true;
	        }
	    }
	    return false; 
	}
	
	public Action getdir(){
		return Acao_atual;
	}
	
	public Action getPdir(){
		return Acao_passada;
	}
	
}
