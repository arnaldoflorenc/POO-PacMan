package assets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	private static int gameYSize = Mapa.getYTiles() * MapElement.getTileSize();
	private static int gameXSize = Mapa.getXTiles() * MapElement.getTileSize();
	
	HashSet<MapElement> walls;
	HashSet<MapElement> points; //arrumar dps
	HashSet<MapElement> ghosts;
	MapElement pacman;
	Timer gameLoop;
	
	protected Image wallImage;
	
	protected Image blueGhostImage;
	protected Image orangeGhostImage;
	protected Image pinkGhostImage;
	protected Image redGhostImage;
	
	protected Image pacmanUpImage;
	protected Image pacmanDownImage;
	protected Image pacmanLeftImage;
	protected Image pacmanRightImage;
	
	public Game() {
		setPreferredSize(new Dimension(gameXSize, gameYSize));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		
		wallImage = new ImageIcon(getClass().getResource("/sprites/wall.png")).getImage();
		
		blueGhostImage = new ImageIcon(getClass().getResource("/sprites/blueGhost.png")).getImage();
		orangeGhostImage = new ImageIcon(getClass().getResource("/sprites/orangeGhost.png")).getImage();
		pinkGhostImage = new ImageIcon(getClass().getResource("/sprites/pinkGhost.png")).getImage();
		redGhostImage = new ImageIcon(getClass().getResource("/sprites/redGhost.png")).getImage();
		
		pacmanUpImage = new ImageIcon(getClass().getResource("/sprites/pacmanUp.png")).getImage();
		pacmanDownImage = new ImageIcon(getClass().getResource("/sprites/pacmanDown.png")).getImage();
		pacmanLeftImage = new ImageIcon(getClass().getResource("/sprites/pacmanLeft.png")).getImage();
		pacmanRightImage = new ImageIcon(getClass().getResource("/sprites/pacmanRight.png")).getImage();
		
		loadMap();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.drawImage(pacman.getSprite(), pacman.getX(), pacman.getY(), pacman.getTamX(), pacman.getTamY(), null);
		
		for (MapElement ghost : ghosts) {
			g.drawImage(ghost.getSprite(), ghost.getX(), ghost.getY(), ghost.getTamX(), ghost.getTamY(), null);
		}
		for (MapElement wall: walls) {
			g.drawImage(wall.getSprite(), wall.getX(), wall.getY(), wall.getTamX(), wall.getTamY(), null);
		}
		g.setColor(Color.WHITE);
		for (MapElement point: points) {
			g.fillRect(point.getX(), point.getY(), point.getTamX(), point.getTamY());
		}
	}
	
	public void loadMap() {
		
		walls = new HashSet<MapElement>();
		points = new HashSet<MapElement>();
		ghosts = new HashSet<MapElement>();
		
				
		for (int l = 0; l < Mapa.getYTiles(); l++) {
			for (int c = 0; c < Mapa.getXTiles(); c++) {
				String linha = Mapa.tileMap[l];
				char tileMapChar = linha.charAt(c);
				
				int x = c * MapElement.getTileSize();
				int y = l * MapElement.getTileSize();
				
				switch(tileMapChar) {
				case '0': {
					MapElement wall = new MapElement(wallImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					walls.add(wall);
					break;
				}
				case 'B': {
					MapElement ghost = new MapElement(blueGhostImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'p': {
					MapElement ghost = new MapElement(pinkGhostImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'O': {
					MapElement ghost = new MapElement(orangeGhostImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'R': {
					MapElement ghost = new MapElement(redGhostImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'P': {
					this.pacman = new MapElement(pacmanRightImage, x, y, MapElement.getTileSize(), MapElement.getTileSize());
					break;
				}
				case ' ': {
					MapElement point = new MapElement(null, x + 14, y + 14, 4, 4);
					points.add(point);
					break;
				}
				default:
					break;
			}
			}
		}
	}
	
	
	
	
	
	
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}

	public static int getGameXsize() {
		return 0;
	}

	public static int getGameYsize() {
		return 0;
	}
	
}
