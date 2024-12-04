package assets;

import java.util.ArrayList;
import java.util.HashSet;

import gui.GameVisualController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import java.io.File;


public class Game extends Pane {
	
	private static final int TILE_SIZE = Mapa.getTileSize();
	private static int gameXSize = Mapa.getXTiles() * TILE_SIZE;
	private static int gameYSize = Mapa.getYTiles() * TILE_SIZE;
	
	private AudioClip eatDot0 = new AudioClip(new File("src/sounds/eat_dot_0.wav").toURI().toString());
	private AudioClip eatDot1 = new AudioClip(new File("src/sounds/eat_dot_1.wav").toURI().toString());
	private boolean togglePointSound = true;		// Alterna entre os sons dos pontos
	
	public static HashSet<MapElement> walls;
	HashSet<MapElement> points;
	HashSet<Ghosts> ghosts;
	Pacman pacman;
	private final IntegerProperty score = new SimpleIntegerProperty(0);
	private final IntegerProperty lives = new SimpleIntegerProperty();
	
	protected Image wallImage;
	
	private static Entites.Action action = Entites.getRandomAction();
	private Canvas canvas;

	private GraphicsContext gc;
	private AnimationTimer gameLoop;
	private GameVisualController controller;
	
	public Game() {
		setPrefSize(gameXSize, gameYSize);
		

		canvas = new Canvas(gameXSize,gameYSize);
		gc = canvas.getGraphicsContext2D();
		getChildren().add(canvas);

		loadMap();
	}
	
	public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (allPoints() || Pacman.getLives() == 0) { ///////////////////////////
                    stopGameLoop();
                    Ghosts.stopGhostSound();
                    endGame(); // Método para lidar com o término do jogo
                    return;
                }
            	
            	// Som dos fantasmas
            	if (pacman.isAlive()) {
            	    Ghosts.startGhostSound();
            	} else {
            	    Ghosts.stopGhostSound();
            	}
            	
            	lives.set(Pacman.getLives());
                update();
                draw();
            }
        };

        gameLoop.start();
	}
	
	public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
	
	private void draw() {
		gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameXSize, gameYSize);
        
        for (MapElement wall : walls) {
            wall.draw(gc);
        }
        for (MapElement point : points) {
            point.draw(gc);
        }
        for (Ghosts ghost : ghosts) {
            ghost.draw(gc);
        }
        pacman.draw(gc);
    }
	
	
	public void loadMap() {
		
		walls = new HashSet<MapElement>();
		points = new HashSet<MapElement>();
		ghosts = new HashSet<Ghosts>();
			
				
		for (int l = 0; l < Mapa.getYTiles(); l++) {
			for (int c = 0; c < Mapa.getXTiles(); c++) {
				String linha = Mapa.Maze.get(l);
				char tileMapChar = linha.charAt(c);
				
				int x = c * Mapa.getTileSize();
				int y = l * Mapa.getTileSize();
				
				switch (tileMapChar) {
                case '1': 
                    walls.add(new MapElement(Sprites.wallSprite, x, y, TILE_SIZE, TILE_SIZE));
                    //System.out.println("wall");
                    break;
                case '2':
                    points.add(new MapElement(Sprites.pointSprite , x , y , TILE_SIZE, TILE_SIZE));
                    //System.out.println("points");
                    break;
                case 'B':
                    ghosts.add(new Ghosts(this, "Blue", x, y));
                    //System.out.println("Blue");
                    break;
                case 'O':
                    ghosts.add(new Ghosts(this, "Orange", x, y));
                    //System.out.println("Orange");
                    break;
                case 'P':
                    ghosts.add(new Ghosts(this, "Pink", x, y));
                    //System.out.println("Pink");
                    break;
                case 'R':
                    ghosts.add(new Ghosts(this, "Red", x, y));
                    //System.out.println("red");
                    break;
                case 'C':
                    pacman = new Pacman(this, x, y);
                    //System.out.println("pacman");
                    break;
            }
			}
		}
	}
	
	
	private void update() {
		if(pacman.canMove())
			pacman.updateDir();
        
		pacman.move();
		registerPoint();
		
		if (pacman.isAlive()) {
			for (Entites ghost: this.ghosts)
				checkDeathCollision(pacman, ghost);
		}
			
		
        for (Ghosts ghost : ghosts) {
        	if (!ghost.canMove()) {
        		if (!ghost.canMove(ghost.getAcaoAtual())) {
        			action = Entites.getRandomAction();
        			ghost.setDir(action); //COLISAO
        		}
        		if (!ghost.canMove(action)) {
        			ghost.setDir(Entites.getRandomAction()); //MEIO
        		}
        		if (!ghost.canMove()) {
        			ghost.setDir(Entites.getRandomAction());
        		}
        	}
        	else {
        		ghost.updateDir();
        	}
        	
        	ghost.move();
        	
        }
        
        try {
        	Thread.sleep(1);
        }
			catch (InterruptedException e) {
	        System.out.println("A thread foi interrompida!");
		}
    }
	
    public void registerPoint() {
    	for (MapElement point : this.points) {
	        if (pacman.isCollidingPoint(this.pacman, point) && point.isAlive() == true) {
	            point.kill();
	            score.set(score.get() + 2);
	            
	            if(togglePointSound) {
	            	eatDot0.play();
	            	
	            } else {
	            	eatDot1.play();
	            }
	            togglePointSound = !togglePointSound;    
	        }
	    }
    }
    
    private boolean allPoints() {
    	for (MapElement point : this.points) {
	        if(point.isAlive()) return false;
	    }
    	return true;
    }
    
    public void checkDeathCollision(Pacman a, Entites b) {
		if (a.isColliding(a, b) && a.isAlive() == true) {
			a.kill();
			
			
			a.setSprite(Sprites.getPacmanDeathSprite());
            Pacman.deathSound.play();
            	
            // Define o sprite de morte e toca o som
        	double deathTime = 1;
        	Timeline respawnDelay = new Timeline(new KeyFrame(Duration.seconds(deathTime), event->{
        		a.setSprite(Sprites.getPacmanSprite());
        		if (Pacman.getLives() > 0) {     
        			
        			for (int l = 0; l < Mapa.getYTiles(); l++) {
        				for (int c = 0; c < Mapa.getXTiles(); c++) {
        					String linha = Mapa.Maze.get(l);
        					char tileMapChar = linha.charAt(c);
        					int x = c * Mapa.getTileSize();
        					int y = l * Mapa.getTileSize();
        					
        					
        					if (tileMapChar == 'C') {
        						
        						a.respawn(x,y);
        					}
        				}
        			}
        		}
        		else
        		{
        			a.setDeath();
        		}
        	}));
        	respawnDelay.setCycleCount(1);
        	respawnDelay.play();
            	
			


      
                
		}
}
	
	public static int getGameXsize() {
		return gameXSize;
	}

	public static int getGameYsize() {
		return gameYSize;
	}
	
	public void handleKeyPress(KeyEvent event) {
		switch (event.getCode()) {
        case UP:
            pacman.setDir(Entites.Action.UP); // Configura direção para cima
            break;
        case DOWN:
            pacman.setDir(Entites.Action.DOWN); // Configura direção para baixo
            break;
        case LEFT:
            pacman.setDir(Entites.Action.LEFT); // Configura direção para a esquerda
            break;
        case RIGHT:
            pacman.setDir(Entites.Action.RIGHT); // Configura direção para a direita
            break;
        default:
        	break;
    }
	}
	
	public void handleKeyRelease(KeyEvent event) {
		
	}
	
	public void setCanvas(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
    }
	
	public void setController(GameVisualController controller) {
		this.controller = controller;
	}
	
	public IntegerProperty pointsProperty() {
        return score;
    }

    public IntegerProperty livesProperty() {
        return lives;
    }
    
    
    
    private void endGame() {
    	controller.endGame(); // Notifica o controlador para redirecionar
    }
}
