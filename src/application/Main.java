package application;

import java.util.ArrayList;
import java.util.List;

import assets.Game;
import assets.Mapa;
import assets.Pacman;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	private Mapa map = new Mapa();
	private List<String> levelData = new ArrayList<>();

	private static final int BLOCK_SIZE = 40;
	private static final int CIRCLE_RAD = 6;

	@Override
    public void start(Stage primaryStage) {
        // Cria uma instância do jogo
        Game game = new Game();

        // Configura a cena com o tamanho do jogo
        Scene scene = new Scene(game, Game.getGameXsize(), Game.getGameYsize());

        
        // Define a cena como focada em eventos de teclado
        scene.setOnKeyPressed(event -> game.handleKeyPress(event)); // passando o evento de tecla
        scene.setOnKeyReleased(event -> game.handleKeyRelease(event));

        // Configura o palco principal (janela)
        primaryStage.setTitle("Pacman Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Desativa redimensionamento
        primaryStage.show();

        // Dá foco ao jogo para receber eventos de teclado automaticamente
        game.requestFocus();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
