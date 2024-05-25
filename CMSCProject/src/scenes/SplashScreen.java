package scenes;

import elements.Formatting;

import java.util.ArrayList;

import javafx.event.EventHandler;
//import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//SplashScreen class as the initial scene of the game

public class SplashScreen {
//	Attributes
	private Stage stage;
	private Group root;
	private Scene splashScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image titlePicture;
	private ArrayList<Text> texts;
	
	//constants for text positioning
	public static final double XPOS_TEXT = Formatting.SCREEN_WIDTH/2-100;
	public static final double YPOS_TEXT = Formatting.SCREEN_HEIGHT/2+130;
	
	//constructor
	public SplashScreen(Stage stage) {
		this.stage = stage; //initialize the stage
		this.root = new Group();
		this.splashScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	 	this.titlePicture = new Image("file:resources/Menu/gameLogo.png", 600, 600 , true, true); 
	 	this.texts = new ArrayList<Text>();// initialize the list of text elements
	}
	//sets up the stage method 
	public void setStage() {
        // Draw the images on the canvas
        this.gc.drawImage(Formatting.BACKGROUND, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        this.gc.drawImage(this.titlePicture, (Formatting.SCREEN_WIDTH - titlePicture.getWidth())/2, 70); 
        //Add menu options as text elements
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT,"NEW GAME"));
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT+35,"ABOUT"));
  		texts.add(new Text(XPOS_TEXT, YPOS_TEXT+70,"DEVELOPERS"));
  		//customizes texts and adds events handler
  		for(Text t: texts) {
  			t.setFont(Formatting.FONT_STYLE_26); //sets font style
  			t.setFill(Color.WHITE);//sets the text color
  			t.setStroke(Color.BLACK);//sets the stroke color
  			this.addMouseEvent(t);//adds mouse event handlers
  		}

        // Add the canvas and texts to the root node
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(texts.get(0));
		this.root.getChildren().add(texts.get(1));
		this.root.getChildren().add(texts.get(2));
		//sets the stage title, scene, and shows the stage
		this.stage.setTitle("CMSC 22 Project: Grow and Conquer");
		this.stage.setScene(this.splashScene);
		this.stage.show();
	}
	
	//creates mouse event handler for text elements
	private void addMouseEvent(Text t) {
		//changes color when text is hovered in
		t.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.DARKBLUE);// sets the color into dark blue
			}
		});
		//changes color when text is hovered out
		t.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.WHITE);// sets the color back to white
			}
		});
		//changes scene when text is clicked
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        switch (t.getText()) {
		            case "NEW GAME":
		            	//transition to the choose character scene.
		                System.out.println("Load Select Characters");
		                ChooseCharacterScene chooseCharacterScene = new ChooseCharacterScene(splashScene, stage);
		                stage.setScene(chooseCharacterScene.getScene());
		                break;
		            case "ABOUT": 
		            	//transition to the about scene.
		                System.out.println("Load About Scene");
		                AboutScene aboutScene = new AboutScene(splashScene, stage);
		                stage.setScene(aboutScene.getScene()); 
		                break;
		            case "DEVELOPERS":
		            	//Transition to the developer scene.
		                System.out.println("Load Developer Scene");
		                DeveloperScene developerScene = new DeveloperScene(splashScene, stage);
		                stage.setScene(developerScene.getScene());
		                break;
		        }
		    }
		});

			
//		
	}
	

}
