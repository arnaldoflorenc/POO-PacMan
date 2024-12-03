package application;

import java.util.ArrayList;
import java.util.List;

import assets.Game;
import assets.Mapa;
import gui.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private Mapa map = new Mapa();
	private List<String> levelData = new ArrayList<>();

	private static final int BLOCK_SIZE = 40;
	private static final int CIRCLE_RAD = 6;
	Game game = new Game();

	@Override
    public void start(Stage primaryStage) {
        try {
            // Carregar o arquivo FXML do menu
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));
            
            
            // Carregar a cena
            Scene scene = new Scene(parent);

           

            // Definir a cena inicial
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pacman Game");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
