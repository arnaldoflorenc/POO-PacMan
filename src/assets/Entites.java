package assets;

import javafx.scene.image.Image;

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
	private Game game;
	
	public int velocidadeX = 0;
	public int velocidadeY = 0;
	
	public Entites (Game game, Image sprite, int x, int y, int tamanhoX, int tamanhoY){
		super(sprite, x, y, tamanhoX, tamanhoY);
		this.game = game;
	}
	
	public boolean canMove() {
		
		Entites projection = new Entites(game, this.getSprite(), this.getX(), this.getY(),this.getTamX(),this.getTamY());
		boolean colisao = false;
		projection.Acao_esperada = this.Acao_esperada;
		projection.velocidadeX = this.velocidadeX;
		projection.velocidadeY = this.velocidadeY;
		projection.updateVel(Acao_esperada);
		projection.setX(projection.getX() + projection.velocidadeX);
		projection.setY(projection.getY() + projection.velocidadeY);
		updateVel(this.Acao_atual);  //evita q o pacman trave ao receber ordem de direcao
		
	    for(MapElement wall : Game.walls) {
	    	if (isColliding(projection, wall)) {
				colisao = true;
				break;
			}
	    }
			
		if(!colisao)
			return true;
		
		return false;
	}

	public void updateDir() {
		if (this.getStatus() == true)
		{
			this.Acao_atual = Acao_esperada;
		}
		else
		{
			this.Acao_atual = Action.DEFAULT;
		}
		updateVel(this.Acao_atual);
	}
	
	public void setDir(Action dir){
		this.Acao_esperada = dir;
		this.Acao_passada = Acao_atual;
	}

	protected void updateVel(Action acao_esp2) {
		this.velocidadeX = acao_esp2.dx ;
		this.velocidadeY = acao_esp2.dy ;
	}
	
	public boolean directionChange() {
		if(this.Acao_atual != this.Acao_esperada)	return true;
		return false;
	}
	
	public void move() {
		
	    int newX = getX() + velocidadeX;
	    int newY = getY() + velocidadeY;
	    
	    if(newX >= Game.getGameXsize()) {
	    	newX = 0;
	    } else if(newX < 0){
	    	newX = Game.getGameXsize() - getTamX();
	    }
	    else
	    	setX(newX);
	    if(newY >= Game.getGameYsize()) {
	    	newY = 0;
	    } else if(newY < 0) {
	    	newY = Game.getGameYsize() - getTamY();
	    }
	    else
	    	setY(newY);
	    
	    for (MapElement wall : Game.walls) {
			if (isColliding(this, wall)) {
				setX(newX - velocidadeX);
				setY(newY - velocidadeY);
				return;
			}
		}
	}
	
	public void move(int segueX, int segueY) {
		
		updateDir();
		
	    int newX = getX() + segueX;
	    int newY = getY() + segueY;
	    
	    if(newX >= Game.getGameXsize()) {
	    	newX = 0;
	    } else if(newX < 0){
	    	newX = Game.getGameXsize() - getTamX();
	    }
	    
	    if(newY >= Game.getGameYsize()) {
	    	newY = 0;
	    } else if(newY < 0) {
	    	newY = Game.getGameYsize() - getTamY();
	    }
	    
	    if (canMove()) {
	        setX(newX);
	        setY(newY);
	    }
	}
	
	/**private boolean isCollidingWithWalls(int newX, int newY) {
	    for (MapElement wall : game.walls) {
	        if (isColliding(newX, newY, wall)) {
	            return true;
	        }
	    }
	    return false; 
	}**/
	
	public Action getdir(){
		return Acao_atual;
	}
	
	public Action getPdir(){
		return Acao_passada;
	}
	
}
