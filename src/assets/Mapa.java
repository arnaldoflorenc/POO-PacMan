package assets;

public class Mapa {
	private static int yTiles = 22;
	private static int xTiles = 21;
	
	protected static String[] tileMap = {
	        "XXXXXXXXXX XXXXXXXXXX",
	        "X                   X",
	        "X XX XXXX X XXXX XX X",
	        "X XX XXXX X XXXX XX X",
	        "X                   X",
	        "X XX X XXXXXXX X XX X",
	        "X    X    X    X    X",
	        "XXXX XXXX-X-XXXX XXXX",
	        "---X X----R----X X---",
	        "XXXX X-XXX-XXX-X XXXX",
	        "---- --XB-p-OX-- ----",
	        "XXXX X-XXXXXXX-X XXXX",
	        "---X X---------X X---",
	        "XXXX X-XXXXXXX-X XXXX",
	        "X         X         X",
	        "X XX XXXX X XXXX XX X",
	        "X  X      P      X  X",
	        "XX X X XXXXXXX X X XX",
	        "X    X    X    X    X",
	        "X XXXXXXX X XXXXXXX X",
	        "X                   X",
	        "XXXXXXXXXX XXXXXXXXXX" 
	    };
	
	public static int getYTiles() {
		return yTiles;
	}
	
	public static int getXTiles() {
		return xTiles;
	}
}
