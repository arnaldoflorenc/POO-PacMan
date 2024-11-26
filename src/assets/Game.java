package assets;

import java.util.HashSet;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Game extends Pane {
	
	private MediaPlayer mediaPlayer;
	
	private static final int TILE_SIZE = MapElement.getTileSize();
	private static int gameXSize = Mapa.getXTiles() * TILE_SIZE;
	private static int gameYSize = Mapa.getYTiles() * TILE_SIZE;
	
	public static HashSet<MapElement> walls;
	HashSet<MapElement> points; //arrumar dps
	HashSet<Ghosts> ghosts;
	Pacman pacman;
	
	protected Image wallImage;
	
	
	private Canvas canvas;
	private GraphicsContext gc;
	
	public Game() {
		setPrefSize(gameXSize, gameYSize);
		
		canvas = new Canvas(gameXSize,gameYSize);
		gc = canvas.getGraphicsContext2D();
		getChildren().add(canvas);
		
		// Tocar a música ao iniciar o jogo
		playStartMusic();
		
		loadMap();
		
		setOnKeyPressed(this::handleKeyPress);
        setOnKeyReleased(this::handleKeyRelease);
        
        AnimationTimer gameLoop = new AnimationTimer() {
        	@Override
        	public void handle(long now) {
        		update();
        		draw();
        	}
        };
        
        gameLoop.start();
	}
	
	private void playStartMusic() {
		try {
			String startFile = "src/sounds/start.wav";
			Media startMusic = new Media(new File(startFile).toURI().toString());
			mediaPlayer = new MediaPlayer(startMusic);
			
			mediaPlayer.play();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro: não foi possível carregar a música de início.");
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
				
				int x = c * MapElement.getTileSize();
				int y = l * MapElement.getTileSize();
				
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
		if(pacman.canMove()) pacman.updateDir();
        
		pacman.move();
        
        for (Ghosts ghost : ghosts) {
            ghost.move();
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
}
