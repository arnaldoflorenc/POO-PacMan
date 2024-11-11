package assets;

public class Ghosts extends Entites {

    // Definindo o comportamento de cada fantasma
    
    private String ghostType;

    public Ghosts(Game game, String ghost, int x, int y) {
        super(game, Sprites.getGhostSprite(ghost), x, y, MapElement.getTileSize(), MapElement.getTileSize());
        this.ghostType = ghost;
    }

    // Método estático para pegar o sprite baseado no tipo de fantasma
    

    // Método para movimentação dos fantasmas (pode ser customizado mais tarde)
    @Override
    public void move() {
        // Lógica de movimentação para o fantasma, como se ele se movesse aleatoriamente
        super.move();
    }

    public String getTipo() {
        return ghostType;
    }
}