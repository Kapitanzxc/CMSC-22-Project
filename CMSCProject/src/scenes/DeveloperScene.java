package scenes;

import elements.Formatting;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

public class DeveloperScene {
	private Group root;
	private Scene developerScene;
	private Canvas canvas;
	private GraphicsContext gc;
	// Constructor
	public DeveloperScene(Scene menuScene, Stage stage) {
		this.root = new Group();
		this.developerScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		// Draw Background
		this.gc.drawImage(Formatting.DEVELOPERS, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		// Load these things
		
		
		// Draw home button
		ImageView imgViewHome = new ImageView(Formatting.HOME_BUTTON);
		Button homeButton = new Button();
		homeButton.setPadding(new Insets(0));
		homeButton.setTranslateX(7);
		homeButton.setTranslateY(7);
		homeButton.setPrefSize(26,27);
		homeButton.setGraphic(imgViewHome);
		homeButton.setBackground(new Background(new BackgroundFill(Formatting.BGCOLOR, null, null)));
		homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Go back to the parent Scene
				stage.setScene(menuScene);
			}	
			
		});
		
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(homeButton);

	}
	
	public Scene getScene() {
		return developerScene;
	}
}
