package assets;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
	private static int yTiles = 21;
	private static int xTiles = 21;
	private final static int tileSize = 48;
	protected static List<String> Maze = new ArrayList<>();
	
	public Mapa() {
		Maze.add("111111111121111111111");
		Maze.add("122222222222222222221");
		Maze.add("121121111212111121121");
		Maze.add("121121111212111121121");
		Maze.add("122222222222222222221");
		Maze.add("121121211111112121121");
		Maze.add("122221222212222122221");
		Maze.add("111121111212111121111");
		Maze.add("000121222222222121000");
		Maze.add("111121210111012121111");
		Maze.add("00002221RP0OB122C0000");
		Maze.add("111121211111112121111");
		Maze.add("000121222222222121000");
		Maze.add("111121211111112121111");
		Maze.add("122222222212222222221");
		Maze.add("121121111212111121121");
		Maze.add("122122222222222221221");
		Maze.add("112121211111112121211");
		Maze.add("122221222212222122221");
		Maze.add("122222222222222222221");
		Maze.add("111111111121111111111");
	}
	
	
	public List<String> iniMaze(){
		 return Maze;
	}
	
	public static int getTileSize() {
		return tileSize;
	}
	
	public static int getYTiles() {
		return yTiles;
	}
	
	public static int getXTiles() {
		return xTiles;
	}
	

}
