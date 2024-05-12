package elements;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameScene {
	private Group root;
	private Stage stage;
	private Scene gameScene;
	private Scene menuScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	

	// Constructor
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
	
	public Scene getScene() {
		//invoke the start method of the animation timer
		this.gametimer.start();
		this.stage.show();
		return gameScene;
	}
}
