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
	
	public static HashSet<MapElement> walls;
	HashSet<MapElement> points; //arrumar dps
	HashSet<Entities> ghosts;
	Sprites sprites;
	Entities pacman; //mudar para entity
	Timer gameLoop;
	
	
	public Game() {
		setPreferredSize(new Dimension(gameXSize, gameYSize));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		
		this.sprites = new Sprites();
		
		loadMap();
		
		gameLoop = new Timer(50, this);
		gameLoop.start();
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
		ghosts = new HashSet<Entities>();
		
				
		for (int l = 0; l < Mapa.getYTiles(); l++) {
			for (int c = 0; c < Mapa.getXTiles(); c++) {
				String linha = Mapa.tileMap[l];
				char tileMapChar = linha.charAt(c);
				
				int x = c * MapElement.getTileSize();
				int y = l * MapElement.getTileSize();
				
				switch(tileMapChar) {
				case 'X': {
					MapElement wall = new MapElement("wall", x, y, MapElement.getTileSize(), MapElement.getTileSize());
					walls.add(wall);
					break;
				}
				case 'B': {
					Entities ghost = new Entities("blueGhost", x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'p': {
					Entities ghost = new Entities("pinkGhost", x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'O': {
					Entities ghost = new Entities("orangeGhost", x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'R': {
					Entities ghost = new Entities("redGhost", x, y, MapElement.getTileSize(), MapElement.getTileSize());
					ghosts.add(ghost);
					break;
				}
				case 'P': {
					this.pacman = new Entities("pacmanRight", x, y, MapElement.getTileSize(), MapElement.getTileSize());
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
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() ==  KeyEvent.VK_UP) {
			pacman.updateDir('U');
			if (pacman.getDir() == 'U') {
			pacman.setSprite("pacmanUp");
			}
		}
		else if (e.getKeyCode() ==  KeyEvent.VK_DOWN) {
			pacman.updateDir('D');
			if (pacman.getDir() == 'D') {
			pacman.setSprite("pacmanDown");
			}
		}
		else if (e.getKeyCode() ==  KeyEvent.VK_LEFT) {
			pacman.updateDir('L');
			if (pacman.getDir() == 'L') {
			pacman.setSprite("pacmanLeft");
			}
		}
		else if (e.getKeyCode() ==  KeyEvent.VK_RIGHT) {
			pacman.updateDir('R');
			if (pacman.getDir() == 'R') {
			pacman.setSprite("pacmanRight");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pacman.move();
		repaint();
	}
	
}
