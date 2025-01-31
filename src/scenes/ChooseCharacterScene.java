package scenes;

import javax.sound.sampled.Clip;

import elements.Formatting;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

// Scene for selecting character
public class ChooseCharacterScene {
	// attributes
	private Group root;
	private Stage stage;
	private Scene chooseCharacterScene,menuScene;
	private Canvas canvas;
	private ChooseCharacter1 chooseCharacter1;
	// Constructor
	public ChooseCharacterScene(Scene menuScene, Stage stage, Clip clip) {
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
	
	// returns the choose character scene.
	public Scene getScene() {
		//invoke the first character selection
		System.out.println("Choose player 1!");
		//transitions to chooseCharacter1 scene.
		this.chooseCharacter1.start();
		//display the stage
		this.stage.show();
		return chooseCharacterScene;
	}
}
