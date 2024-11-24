package gui;

import application.Main;
import assets.Game;
import assets.Sprites;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController {
	
	private Stage stage;
	private Main main;
	
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Button start;
	
	@FXML
	private Button exit;
	
	@FXML
	private ImageView pacmanFleeGif;
	
	@FXML
	private ImageView pacmanHuntGif;
	
	private Image gifTop;
	private Image gifBottom;
	
	private boolean showingFlee = true;
	
	@FXML
	private void initialize() {
		
		 
		 gifTop = new Image(Sprites.class.getResource("/sprites/MenuAnim_flee.gif").toString());
		 gifBottom = new Image(Sprites.class.getResource("/sprites/MenuAnim_hunt.gif").toString());
		 
		 
		// Configurar os GIFs
	        pacmanFleeGif.setImage(gifTop);
	        pacmanFleeGif.setFitWidth(192); // Dobrar o tamanho original
	        pacmanFleeGif.setFitHeight(13);
	        pacmanFleeGif.setPreserveRatio(true);

	        pacmanHuntGif.setImage(gifBottom);
	        pacmanHuntGif.setFitWidth(192);
	        pacmanHuntGif.setFitHeight(13);
	        pacmanHuntGif.setPreserveRatio(true);
	        pacmanHuntGif.setVisible(false);
	        pacmanHuntGif.setScaleX(-1);

	        // Iniciar as animações de movimento
	        startAlternatingMovement(); // Move de 0 a 600 pixels
	  
	}
	
	@FXML
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void onButtonStartAction() {
		Game game = new Game(); // Instância do jogo
        Scene gameScene = new Scene(game, Game.getGameXsize(), Game.getGameYsize());
        gameScene.setOnKeyPressed(eventKey -> game.handleKeyPress(eventKey));
        gameScene.setOnKeyReleased(eventKey -> game.handleKeyRelease(eventKey));

        // Obter o estágio atual e definir a nova cena
        Stage stage = (Stage) start.getScene().getWindow();
        stage.setScene(gameScene);
	}
	
	@FXML
	public void onButtonExitAction() {
		System.exit(0);
	}
	
	private void startAlternatingMovement() {
        // Configurar movimento para o GIF de cima
        TranslateTransition transitionTop = createMovement(pacmanFleeGif, -200, 800);

        // Configurar movimento para o GIF de baixo
        TranslateTransition transitionBottom = createMovement(pacmanHuntGif, 800, -200);

        // Adicionar listener ao movimento de cima para iniciar o movimento de baixo
        transitionTop.setOnFinished(event -> {
            pacmanFleeGif.setVisible(false); // Esconde o GIF de cima
            pacmanHuntGif.setVisible(true);  // Mostra o GIF de baixo
            transitionBottom.play();         // Inicia o movimento de baixo
        });

        // Adicionar listener ao movimento de baixo para reiniciar o movimento de cima
        transitionBottom.setOnFinished(event -> {
            pacmanHuntGif.setVisible(false); // Esconde o GIF de baixo
            pacmanFleeGif.setVisible(true);  // Mostra o GIF de cima
            transitionTop.play();            // Reinicia o movimento de cima
        });
        
        transitionTop.play();
    }
	
	private TranslateTransition createMovement(ImageView gif, double fromX, double toX) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(gif); // O nó que será movido
        transition.setFromX(fromX); // Posição inicial
        transition.setToX(toX); // Posição final
        transition.setDuration(Duration.seconds(5)); // Tempo de duração do movimento
        transition.setInterpolator(Interpolator.LINEAR); // Movimento suave
        return transition;
    }
	
	private void setBackgroundImage(String imagePath) {
		Image backgroundImage = new Image(Sprites.class.getResource(imagePath).toString());
		
		ImageView backgroundView = new ImageView(backgroundImage);
		backgroundView.setFitWidth(rootPane.getPrefWidth()); // Ajustar à largura do AnchorPane
	    backgroundView.setFitHeight(rootPane.getPrefHeight()); // Ajustar à altura do AnchorPane
	    backgroundView.setPreserveRatio(false);
	    
	    rootPane.getChildren().add(0, backgroundView);
	}
}
