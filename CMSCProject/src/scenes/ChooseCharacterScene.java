package scenes;

import elements.Formatting;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

// Scene for choosing character
public class ChooseCharacterScene {
	private Group root;
	private Stage stage;
	private Scene chooseCharacterScene,menuScene;
	private Canvas canvas;
	private ChooseCharacter1 chooseCharacter1;
	

	// Constructor
	public ChooseCharacterScene(Scene menuScene, Stage stage) {
		// Initialize variables
		this.stage = stage;
		this.root = new Group();
		this.chooseCharacterScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.menuScene = menuScene;
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.root.getChildren().add(canvas);
		// Initialize chooseCharacter 1 Scene
		this.chooseCharacter1 = new ChooseCharacter1(this.chooseCharacterScene, this.menuScene, this.stage);
		
	}
	
	public Scene getScene() {
		//invoke the choose character 1 scene
		System.out.println("Choose player 1!");
		this.chooseCharacter1.start();
		this.stage.show();
		return chooseCharacterScene;
	}
}
