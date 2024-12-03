package gui;

import application.Main;
import assets.Sprites;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
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
		 
		 start.setOnMouseEntered(event -> start.setCursor(Cursor.HAND));
		 exit.setOnMouseEntered(event -> exit.setCursor(Cursor.HAND));
		 
		 gifTop = new Image(Sprites.class.getResource("/sprites/MenuAnim_flee.gif").toString());
		 gifBottom = new Image(Sprites.class.getResource("/sprites/MenuAnim_hunt.gif").toString());
		 
		 
	        pacmanFleeGif.setImage(gifTop);
	        pacmanFleeGif.setFitWidth(192); 
	        pacmanFleeGif.setFitHeight(13);
	        pacmanFleeGif.setPreserveRatio(true);

	        pacmanHuntGif.setImage(gifBottom);
	        pacmanHuntGif.setFitWidth(192);
	        pacmanHuntGif.setFitHeight(13);
	        pacmanHuntGif.setPreserveRatio(true);
	        pacmanHuntGif.setVisible(false);
	        pacmanHuntGif.setScaleX(-1);

	        startAlternatingMovement(); 
	  
	}
	
	@FXML
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void onButtonStartAction(ActionEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GameVisual.fxml"));
            Parent gameRoot = loader.load();

            Scene gameScene = new Scene(gameRoot);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(gameScene);
            stage.setTitle("Game");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void onButtonExitAction() {
		System.exit(0);
	}
	
	private void startAlternatingMovement() {
        TranslateTransition transitionTop = createMovement(pacmanFleeGif, -200, 800);

        TranslateTransition transitionBottom = createMovement(pacmanHuntGif, 800, -200);

        transitionTop.setOnFinished(event -> {
            pacmanFleeGif.setVisible(false); 
            pacmanHuntGif.setVisible(true);  
            transitionBottom.play();         
        });

        transitionBottom.setOnFinished(event -> {
            pacmanHuntGif.setVisible(false); 
            pacmanFleeGif.setVisible(true);  
            transitionTop.play();            
        });
        
        transitionTop.play();
    }
	
	private TranslateTransition createMovement(ImageView gif, double fromX, double toX) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(gif); 
        transition.setFromX(fromX); 
        transition.setToX(toX); 
        transition.setDuration(Duration.seconds(5)); 
        transition.setInterpolator(Interpolator.LINEAR); 
        return transition;
    }
	
	private void setBackgroundImage(String imagePath) {
		Image backgroundImage = new Image(Sprites.class.getResource(imagePath).toString());
		
		ImageView backgroundView = new ImageView(backgroundImage);
		backgroundView.setFitWidth(rootPane.getPrefWidth()); 
	    backgroundView.setFitHeight(rootPane.getPrefHeight()); 
	    backgroundView.setPreserveRatio(false);
	    
	    rootPane.getChildren().add(0, backgroundView);
	}
}
