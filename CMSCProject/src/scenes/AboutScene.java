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

public class AboutScene {
//	Attributes
	private Group root;
	private Scene aboutScene;
	private Canvas canvas;
	private GraphicsContext gc;
	// Constructor
	public AboutScene(Scene menuScene, Stage stage) {
		this.root = new Group();
		this.aboutScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		// Draw Background
		this.gc.drawImage(Formatting.ABOUT, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		// Draw Scroll Wheel
		this.gc.drawImage(Formatting.LEFT_SCROLL, 530, Formatting.YPOS_TEXT);
		
//		Draw right button
		ImageView imgView = new ImageView(Formatting.RIGHT_BUTTON);
		Button rightButton = new Button();
		rightButton.setPadding(Insets.EMPTY);
		rightButton.setTranslateX(530 + Formatting.MIDDLE_SCROLL.getWidth() + 10);
		rightButton.setTranslateY(Formatting.YPOS_TEXT);
		rightButton.setPrefSize(Formatting.RIGHT_BUTTON.getWidth(),Formatting.RIGHT_BUTTON.getHeight());
		rightButton.setBackground(new Background(new BackgroundFill(Formatting.BEIGE, null, null)));
		rightButton.setGraphic(imgView);

//		Adding Event
		rightButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
//			Go to Control Scene
			public void handle(MouseEvent event) {
				System.out.println("Load Control Scene");
				ControlScene controlScene = new ControlScene(menuScene, aboutScene, stage);
				stage.setScene(controlScene.getScene());
			}	
		});
		
//		Draw home button
		ImageView imgViewHome = new ImageView(Formatting.HOME_BUTTON);
		Button homeButton = new Button();
		homeButton.setPadding(new Insets(0));
		homeButton.setTranslateX(7);
		homeButton.setTranslateY(7);
		homeButton.setPrefSize(Formatting.HOME_BUTTON.getWidth(),Formatting.HOME_BUTTON.getHeight());
		homeButton.setBackground(new Background(new BackgroundFill(Formatting.BROWN, null, null)));
		homeButton.setGraphic(imgViewHome);
//		Adding Event
		homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
//			Go to Menu Scene
			public void handle(MouseEvent event) {
				// Go back to the parent Scene
				System.out.println("Load Menu Scene");
				stage.setScene(menuScene);
			}	
			
		});
	   
		// Load these things
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(rightButton);
		this.root.getChildren().add(homeButton);

	}
	
	//return aboutScene
	public Scene getScene() {
		return aboutScene;
	}
	
	
	
}
