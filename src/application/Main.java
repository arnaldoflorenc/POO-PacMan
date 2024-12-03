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
import javafx.scene.media.AudioClip;
import java.io.File;

public class Main extends Application {

	private Mapa map = new Mapa();
	private List<String> levelData = new ArrayList<>();
	
	private MediaPlayer mediaPlayer;
	private Stage primaryStage;

	private static final int BLOCK_SIZE = 40;
	private static final int CIRCLE_RAD = 6;
	
	Game game = new Game();

	@Override
    public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		showMainMenu();
    }
	
	private void showMainMenu() {
		 try {
	          // Carregar o arquivo FXML do menu
	          Parent parent = FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));         
	            
	          // Carregar a cena
	          Scene scene = new Scene(parent);            
	           
	          // Definir a cena inicial
	          primaryStage.setScene(scene);
	          primaryStage.setTitle("Pacman Game - Menu");
	          primaryStage.setResizable(false);
	          primaryStage.show();
	          
	          playGameMusic();

	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}
	
	 private void playGameMusic() {
	        try {
	            if (mediaPlayer != null) {
	                mediaPlayer.stop(); // Parar qualquer áudio anterior
	            }

	            // Configurar o caminho do arquivo de áudio
	            String soundPath = "src/sounds/start.wav";
	            Media sound = new Media(new File(soundPath).toURI().toString());
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
