package application;

import javax.swing.JFrame;

import assets.Game;
import assets.MapElement;
import assets.Mapa;

public class App {

	public static void main(String[] args) {
		int gameX = Mapa.getXTiles() * MapElement.getTileSize();
		int gameY = Mapa.getYTiles() * MapElement.getTileSize();
		
		JFrame frame = new JFrame("PacMan Game");
		frame.setSize(gameX, gameY);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Game pacmanGame = new Game();
		frame.add(pacmanGame);
		frame.pack();
		pacmanGame.requestFocus();
		frame.setVisible(true);
	}

}