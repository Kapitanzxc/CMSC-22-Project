package elements;

import java.awt.Font;
import java.util.ArrayList;

import javafx.event.EventHandler;
//import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SplashScreen {
//	Attributes
	private Stage stage;
	private Group root;
	private Scene splashScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image titlePicture;
	private ArrayList<Text> texts;
	
	public static final double XPOS_TEXT = Formatting.SCREEN_WIDTH/2-100;
	public static final double YPOS_TEXT = Formatting.SCREEN_HEIGHT/2+130;
	
	//constructor
	public SplashScreen(Stage stage) {
		this.stage = stage;
		this.root = new Group();
		this.splashScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	 	this.titlePicture = new Image("file:resources/gameLogo.png", 600, 600 , true, true); 
	 	this.texts = new ArrayList<Text>();
	}
//	//sets stage
	public void setStage() {
        // Draw the images on the canvas
        this.gc.drawImage(Formatting.BACKGROUND, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        this.gc.drawImage(this.titlePicture, (Formatting.SCREEN_WIDTH - titlePicture.getWidth())/2, 70); 
        //texts
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT,"NEW GAME"));
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT+35,"ABOUT"));
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT+70,"DEVELOPERS"));
  		//customizes texts and adds events
  		for(Text t: texts) {
  			t.setFont(Formatting.FONT_STYLE_26);
  			t.setFill(Color.WHITE);
  			t.setStroke(Color.BLACK);
  			this.addMouseEvent(t);
  		}

        // Add the canvas and texts to the root node
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(texts.get(0));
		this.root.getChildren().add(texts.get(1));
		this.root.getChildren().add(texts.get(2));
		this.stage.setTitle("CMSC 22 Project: Grow and Conquer");
		this.stage.setScene(this.splashScene);
		this.stage.show();
	}
	
	//creates mouse events for texts
	private void addMouseEvent(Text t) {
		//changes color when text is hovered in
		t.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.DARKBLUE);
			}
		});
		//changes color when text is hovered out
		t.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.WHITE);
			}
		});
		//changes scene when text is clicked
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// when new game is clicked
				if(t.getText().equals("NEW GAME")) {
					System.out.println("Load Select Characters");
//					change scene here
					ChooseCharacterScene chooseCharacterScene = new ChooseCharacterScene(splashScene, stage);
					stage.setScene(chooseCharacterScene.getScene());
				}
				//when about is clicked
				else if(t.getText().equals("ABOUT")) {
					System.out.println("Load About Scene");
//					change scene here
					AboutScene aboutScene = new AboutScene(splashScene, stage);
					stage.setScene(aboutScene.getScene());
				}
				//when developers is clicked
				else {
					System.out.println("Load Developer Scene");
//					change scene here
					DeveloperScene developerScene = new DeveloperScene(splashScene, stage);
					stage.setScene(developerScene.getScene());
				}
			}
			
		});
			
//		
	}
	

}
