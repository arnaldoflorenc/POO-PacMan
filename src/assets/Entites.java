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
		
		int projectedX = getX() + velocidadeX;
	    int projectedY = getY() + velocidadeY;
		
	    if (isCollidingWithWalls(projectedX, projectedY)) {
			System.out.println("Check");
			return false;
		}
		
		return true;
	}

	public void updateDir() {
		updateVel(this.Acao_esperada);
		this.Acao_atual = Acao_esperada;
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
		return this.Acao_atual != this.Acao_esperada;
	}
	
	public void move() {
		
		updateDir();
		
	    int newX = getX() + velocidadeX;
	    int newY = getY() + velocidadeY;
	    
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
	
	private boolean isCollidingWithWalls(int newX, int newY) {
	    for (MapElement wall : game.walls) {
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
