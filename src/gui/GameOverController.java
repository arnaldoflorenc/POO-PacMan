package gui;

import assets.Pacman;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameOverController {
	
	@FXML
	private Label finalPoints;
	
	
	
    @FXML
    private void handleBackToMenu() {
    	try {
            Stage stage = (Stage) finalPoints.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainMenu.fxml"));
            Parent menuRoot = loader.load();

            Scene menuScene = new Scene(menuRoot);
            Pacman.resetLives();
            stage.setScene(menuScene);
            stage.setTitle("Menu Principal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRestartGame() {
    	try {
            
            Stage stage = (Stage) finalPoints.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GameVisual.fxml"));
            Parent gameRoot = loader.load();

            Scene gameScene = new Scene(gameRoot);
            Pacman.resetLives();
            stage.setScene(gameScene);
            stage.setTitle("Jogo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setFinalPoints(int points) {
        finalPoints.setText("Pontos Finais: " + points);
    }
}