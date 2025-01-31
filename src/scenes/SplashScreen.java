package scenes;

import elements.Formatting;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	private Clip clip;
	
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
  		texts.add(new Text(XPOS_TEXT+33, YPOS_TEXT+35,"ABOUT"));
  		texts.add(new Text(XPOS_TEXT-26, YPOS_TEXT+70,"DEVELOPERS"));
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
		
		
		// Background Music
		playSound(Formatting.BGSOUNDFX, 1);
	}
	
//  Method for playing sound
	// Reference: https://www.youtube.com/watch?v=wJO_cq5XeSA
	public void playSound(String soundFile, int loopMusic) {
	    try {
	        // get a sound clip
	        Clip clip = AudioSystem.getClip();
	        
	        // Load file as a resource from the classpath
	        URL soundURL = getClass().getClassLoader().getResource(soundFile);
            
            // If there is no sound file, do this
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }
	        
	        // open audio input stream (an input stream with a specified audio format and length) from the sound file 
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
	       
	        // open clip and start playing the sound
	        clip.open(audioInputStream);
	        clip.start();
	        
	        // loops the background music only
	        if (loopMusic == 1) {
	        	// Get the volume control
	            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	            gainControl.setValue(-12.0f); // reduces volume by 12 decibels
	        	clip.loop(Clip.LOOP_CONTINUOUSLY);
	        	
	        	
	        }
	        
	    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
	        e.printStackTrace();
	    }
	}
	//creates mouse event handler for text elements
	private void addMouseEvent(Text t) {
		//changes color when text is hovered in
		t.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				playSound(Formatting.HOVERSOUNDFX, 0);
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
		            	 // Select sound effect
		            	playSound(Formatting.SELECTSOUNDFX, 0);
		            	//transition to the choose character scene.
		                System.out.println("Load Select Characters");
		                ChooseCharacterScene chooseCharacterScene = new ChooseCharacterScene(splashScene, stage, clip);
		                stage.setScene(chooseCharacterScene.getScene());
		                break;
		            case "ABOUT": 
		            	 // Select sound effect
		            	playSound(Formatting.SELECTSOUNDFX, 0);
		            	//transition to the about scene.
		                System.out.println("Load About Scene");
		                AboutScene aboutScene = new AboutScene(splashScene, stage);
		                stage.setScene(aboutScene.getScene()); 
		                break;
		            case "DEVELOPERS":
		            	 // Select sound effect
		            	playSound(Formatting.SELECTSOUNDFX, 0);
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
