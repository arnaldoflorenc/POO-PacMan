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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Main extends Application {

	Mapa map = new Mapa();
	Game game = new Game();
	
	private MediaPlayer mediaPlayer;
	private Stage primaryStage;

	@Override
    public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		showMainMenu();        
    }
	
	public void showMainMenu() {
		try {
            // Carregar o arquivo FXML do menu
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));
            
            
            // Carregar a cena
            Scene scene = new Scene(parent);
            
            // Tocar o áudio
           playStartMusic(); 
           
            // Definir a cena inicial
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pacman Game");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void playStartMusic() {
		try {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Parar qualquer áudio anterior
            }

            // Configurar o caminho do arquivo de áudio
            Media sound = new Media(new File("src/sounds/start.wav").toURI().toString());
            mediaPlayer = new MediaPlayer(sound);

            // Ajustar o volume e tocar a música
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Erro ao carregar ou tocar o som: " + e.getMessage());
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
