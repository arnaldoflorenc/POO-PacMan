package gui;

import assets.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameVisualController {
	 @FXML
	 private Canvas gameCanvas;
	 
	 @FXML
	 private Label livesLabel;
	 
	 @FXML
	 private Label pointsLabel;
	 
	 
	 private Game game;
	 
	 @FXML
	 public void initialize() {
		 
	     gameCanvas.setWidth(Game.getGameXsize());
	     gameCanvas.setHeight(Game.getGameYsize());
	        
	     game = new Game();
	     
	     game.setController(this);
	     game.setCanvas(gameCanvas);
	     
	     livesLabel.textProperty().bind(game.livesProperty().asString("Vidas: %d"));
	     pointsLabel.textProperty().bind(game.pointsProperty().asString("Pontos: %d"));

	     gameCanvas.sceneProperty().addListener((observable, oldScene, newScene) -> {
	         if (newScene != null) {
	             newScene.setOnKeyPressed(event -> game.handleKeyPress(event));
	             newScene.setOnKeyReleased(event -> game.handleKeyPress(event));
	             gameCanvas.requestFocus(); // Garantir que o Canvas tenha foco
	         }
	     });
	     
	     game.startGameLoop();
	 }
	 
	 public void endGame() {
		    try {
		        Stage stage = (Stage) gameCanvas.getScene().getWindow();

		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GameOver.fxml"));
		        Parent gameOverRoot = loader.load();
		        
		        GameOverController gameOverController = loader.getController();

		        gameOverController.setFinalPoints(game.pointsProperty().get());

		        Scene gameOverScene = new Scene(gameOverRoot);
		        stage.setScene(gameOverScene);
		        stage.setTitle("Fim de Jogo");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	    
}
