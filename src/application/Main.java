package application;

import java.util.ArrayList;
import java.util.List;

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
	public void start(Stage stage) {
		
		levelData = map.iniMaze();
		
		if(levelData.isEmpty()) {
			throw new IllegalStateException("labirinto não foi inicializado");
		}
		
		GridPane gridpane = new GridPane();
		
		for(int i = 0; i < levelData.size(); i++) {
			String Linha = levelData.get(i);
			
			for(int j = 0; j < Linha.length(); j++) {
				Circle circ = new Circle(CIRCLE_RAD);
				Rectangle rect = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
				
				
				if(Linha.charAt(j) == '1'){
					rect.setFill(Color.BLUE);
				} else {
					rect.setFill(Color.BLACK);
				} 
					
				StackPane stackpane = new StackPane();
				stackpane.getChildren().add(rect);
				
				
				
				if(Linha.charAt(j) == '2'){
					circ.setFill(Color.WHITE);
					stackpane.getChildren().add(circ);
				}
				
				gridpane.add(stackpane, j, i);
			}
		}
		

			Scene scene = new Scene(gridpane);
			stage.setTitle("Maze test");
			stage.setScene(scene);
			stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
