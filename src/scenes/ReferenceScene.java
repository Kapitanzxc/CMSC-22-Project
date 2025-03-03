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

public class ReferenceScene {
	private Group root;
	private Scene referenceScene;
	private Canvas canvas;
	private GraphicsContext gc;
	// Constructor
	public ReferenceScene(Scene menuScene,Scene parentScene, Stage stage) {
		this.root = new Group();
		this.referenceScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		// Draw Background
		this.gc.drawImage(Formatting.REFERENCES, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
//		DrawScroll Wheel
		this.gc.drawImage(Formatting.RIGHT_SCROLL, 530, Formatting.YPOS_TEXT);
		
		// Draw left button
		ImageView imgView1 = new ImageView(Formatting.LEFT_BUTTON);
		Button leftButton = new Button();
		leftButton.setPadding(Insets.EMPTY);
		leftButton.setTranslateX(490);
		leftButton.setTranslateY(Formatting.YPOS_TEXT);
		leftButton.setPrefSize(Formatting.LEFT_BUTTON.getWidth(),Formatting.LEFT_BUTTON.getHeight() );
		leftButton.setBackground(new Background(new BackgroundFill(Formatting.BEIGE, null, null)));
		leftButton.setGraphic(imgView1);
		leftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Go to Control Scene
				System.out.println("Load Control Scene");
				stage.setScene(parentScene);
			}	
		});
	   
//		// Draw home button
		ImageView imgViewHome = new ImageView(Formatting.HOME_BUTTON);
		Button homeButton = new Button();
		homeButton.setPadding(new Insets(0));
		homeButton.setTranslateX(7);
		homeButton.setTranslateY(7);
		homeButton.setPrefSize(Formatting.HOME_BUTTON.getWidth(),Formatting.HOME_BUTTON.getHeight() );
		homeButton.setBackground(new Background(new BackgroundFill(Formatting.BROWN, null, null)));
		homeButton.setGraphic(imgViewHome);
		homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Load Menu Scene");
				// Go to Menu Scene
				stage.setScene(menuScene);
			}	
			
		});
		
		// Load these things
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(leftButton);
		this.root.getChildren().add(homeButton);

	}
	
	public Scene getScene() {
		return referenceScene;
	}
}
