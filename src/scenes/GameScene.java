package scenes;

import elements.Formatting;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

//	 handles the game scene setup
public class GameScene {
	private Group root;
	private Stage stage;
	private Scene gameScene;
	private Scene menuScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	

	// Constructor
	//initializes the game scene with players' chosen characters
	public GameScene(Scene menuScene, Stage stage, int player1, int player2) {
		// Initialize variables
		this.stage = stage;
		this.root = new Group();
		this.gameScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.menuScene = menuScene;
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		this.root.getChildren().add(canvas);
		// Draw Background
		this.gametimer = new GameTimer(this.gc,this.gameScene, this.menuScene, this.stage, player1, player2);
		
	}
	// returns the gameScene
	public Scene getScene() {
		//invoke the start method of the animation timer
		this.gametimer.start();
		//displays the stage
		this.stage.show();
		return gameScene;
	}
}
