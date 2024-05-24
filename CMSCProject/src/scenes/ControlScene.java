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

public class ControlScene {
//	Attributes
	private Group root;
	private Scene controlScene;
	private Canvas canvas;
	private GraphicsContext gc;
	
	// Constructor
	public ControlScene(Scene menuScene, Scene parentScene, Stage stage) {
		this.root = new Group();
		this.controlScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		// Draw Background
		this.gc.drawImage(Formatting.CONTROLS, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
//		Draw Scroll Wheel
		this.gc.drawImage(Formatting.MIDDLE_SCROLL, (Formatting.SCREEN_WIDTH - Formatting.LEFT_SCROLL.getWidth())/2, Formatting.YPOS_TEXT);
		
		// Draw right button
		ImageView imgView = new ImageView(Formatting.RIGHT_BUTTON);
		Button rightButton = new Button();
		rightButton.setPadding(Insets.EMPTY);
		rightButton.setTranslateX(Formatting.XPOS_TEXT+68);
		rightButton.setTranslateY(Formatting.YPOS_TEXT);
		rightButton.setPrefSize(Formatting.RIGHT_BUTTON.getWidth(),Formatting.RIGHT_BUTTON.getHeight() );
		rightButton.setBackground(new Background(new BackgroundFill(Formatting.BEIGE, null, null)));
		rightButton.setGraphic(imgView);
		rightButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
//			Go to Reference Scene
			public void handle(MouseEvent event) {
				System.out.println("Load Reference Scene");
				ReferenceScene referenceScene = new ReferenceScene(menuScene, controlScene, stage);
				stage.setScene(referenceScene.getScene());
			}	
			
		});
	    
	    // Draw left button
		ImageView imgView1 = new ImageView(Formatting.LEFT_BUTTON);
		Button leftButton = new Button();
		leftButton.setPadding(Insets.EMPTY);
		leftButton.setTranslateX(Formatting.XPOS_TEXT-100);
		leftButton.setTranslateY(Formatting.YPOS_TEXT);
		leftButton.setPrefSize(Formatting.LEFT_BUTTON.getWidth(),Formatting.LEFT_BUTTON.getHeight() );
		leftButton.setBackground(new Background(new BackgroundFill(Formatting.BEIGE, null, null)));
		leftButton.setGraphic(imgView1);
		leftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
//			Go to About Scene
			public void handle(MouseEvent event) {
				// Go back to the parent Scene
				System.out.println("Load About Scene");
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
		homeButton.setBackground(new Background(new BackgroundFill(Formatting.BGCOLOR, null, null)));
		homeButton.setGraphic(imgViewHome);
		homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Go to Menu Scene
				System.out.println("Load Menu Scene");
				stage.setScene(menuScene);
			}	
			
		});
	   
		// Load these things
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(rightButton);
		this.root.getChildren().add(leftButton);
		this.root.getChildren().add(homeButton);

	}
	
	public Scene getScene() {
		return controlScene;
	}
	
	
}
