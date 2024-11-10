package assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprites {

    // Vetores para armazenar os sprites das direções
    private static Image[] pacmanSprites = new Image[4];  // Up, Down, Left, Right
    private static Image[] ghostSprites = new Image[4];   // Blue, Orange, Pink, Red
    public static Image wallSprite;

    // Inicializando os sprites no carregamento da classe
    static {
    	wallSprite = new Image(Sprites.class.getResource("/sprites/wall.png").toString());
    	
        // Carregando os sprites do Pacman
        pacmanSprites[0] = new Image(Sprites.class.getResource("/sprites/pacmanUp.png").toString());
        pacmanSprites[1] = new Image(Sprites.class.getResource("/sprites/pacmanDown.png").toString());
        pacmanSprites[2] = new Image(Sprites.class.getResource("/sprites/pacmanLeft.png").toString());
        pacmanSprites[3] = new Image(Sprites.class.getResource("/sprites/pacmanRight.png").toString());

        // Carregando os sprites dos fantasmas
        ghostSprites[0] = new Image(Sprites.class.getResource("/sprites/blueGhost.png").toString());
        ghostSprites[1] = new Image(Sprites.class.getResource("/sprites/orangeGhost.png").toString());
        ghostSprites[2] = new Image(Sprites.class.getResource("/sprites/pinkGhost.png").toString());
        ghostSprites[3] = new Image(Sprites.class.getResource("/sprites/redGhost.png").toString());
    }

    // Métodos para obter os sprites específicos
    public static Image getPacmanSprite(String direction) {
        switch (direction) {
            case "Up":
                return pacmanSprites[0];
            case "Down":
                return pacmanSprites[1];
            case "Left":
                return pacmanSprites[2];
            case "Right":
                return pacmanSprites[3];
            default:
                return null;
        }
    }

    public static Image getGhostSprite(String ghostType) {
        switch (ghostType) {
            case "Blue":
                return ghostSprites[0];
            case "Orange":
                return ghostSprites[1];
            case "Pink":
                return ghostSprites[2];
            case "Red":
                return ghostSprites[3];
            default:
                return null;
        }
    }
}
