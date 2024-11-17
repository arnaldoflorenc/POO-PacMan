package assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprites {

    // Vetores para armazenar os sprites das direções
    private static Image pacmanSprite;  
    private static Image[] redGhostSprites = new Image[3];   
    private static Image[] orangeGhostSprites = new Image[3];   
    private static Image[] pinkGhostSprites = new Image[3];  
    private static Image[] blueGhostSprites = new Image[3];  
    public static Image wallSprite;
    public static Image pointSprite;

    // Inicializando os sprites no carregamento da classe
    static {
    	wallSprite = new Image(Sprites.class.getResource("/sprites/wall.png").toString());
    	pointSprite = new Image(Sprites.class.getResource("/sprites/points.png").toString());
    	
        // Carregando os sprites do Pacman
        pacmanSprite = new Image(Sprites.class.getResource("/sprites/pacman_eat.gif").toString());

        // Carregando os sprites dos fantasmas
        redGhostSprites[0] = new Image(Sprites.class.getResource("/sprites/ghost_red_down.gif").toString());
        redGhostSprites[1] = new Image(Sprites.class.getResource("/sprites/ghost_red_side.gif").toString());
        redGhostSprites[2] = new Image(Sprites.class.getResource("/sprites/ghost_red_up.gif").toString());
        
        orangeGhostSprites[0] = new Image(Sprites.class.getResource("/sprites/ghost_orange_down.gif").toString());
        orangeGhostSprites[1] = new Image(Sprites.class.getResource("/sprites/ghost_orange_side.gif").toString());
        orangeGhostSprites[2] = new Image(Sprites.class.getResource("/sprites/ghost_orange_up.gif").toString());
        
        pinkGhostSprites[0] = new Image(Sprites.class.getResource("/sprites/ghost_pink_down.gif").toString());
        pinkGhostSprites[1] = new Image(Sprites.class.getResource("/sprites/ghost_pink_side.gif").toString());
        pinkGhostSprites[2] = new Image(Sprites.class.getResource("/sprites/ghost_pink_up.gif").toString());
        
        blueGhostSprites[0] = new Image(Sprites.class.getResource("/sprites/ghost_blue_down.gif").toString());
        blueGhostSprites[1] = new Image(Sprites.class.getResource("/sprites/ghost_blue_side.gif").toString());
        blueGhostSprites[2] = new Image(Sprites.class.getResource("/sprites/ghost_blue_up.gif").toString());
        
    }

    // Métodos para obter os sprites específicos
    public static Image getPacmanSprite() {
        return pacmanSprite;
    }

    public static Image getGhostSprite(String direction, String ghostType) {
        switch (ghostType) {
            case "Blue":
            	switch (direction) {
                case "Up":
                    return blueGhostSprites[3];
                case "Down":
                    return blueGhostSprites[0];
                case "Left":
                    return blueGhostSprites[2];
                case "Right":
                    return blueGhostSprites[2];
                default:
                    return null;
            	}
            case "Orange":
            	switch (direction) {
                case "Up":
                    return orangeGhostSprites[3];
                case "Down":
                    return orangeGhostSprites[0];
                case "Left":
                    return orangeGhostSprites[2];
                case "Right":
                    return orangeGhostSprites[2];
                default:
                    return null;
            	}
            case "Pink":
            	switch (direction) {
                case "Up":
                    return pinkGhostSprites[3];
                case "Down":
                    return pinkGhostSprites[0];
                case "Left":
                    return pinkGhostSprites[2];
                case "Right":
                    return pinkGhostSprites[2];
                default:
                    return null;
            	}
            case "Red":
            	switch (direction) {
                case "Up":
                    return redGhostSprites[3];
                case "Down":
                    return redGhostSprites[0];
                case "Left":
                    return redGhostSprites[2];
                case "Right":
                    return redGhostSprites[2];
                default:
                    return null;
            	}
            default:
                return null;
        }
    }
}
