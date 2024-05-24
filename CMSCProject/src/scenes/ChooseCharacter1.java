package scenes;

import elements.Formatting;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//handles the character selection for player 2
public class ChooseCharacter1 extends AnimationTimer{
	private Stage stage;
	private Scene menuScene, characterP1Scene;
	private GraphicsContext gc;
	private int characterP1,animationCount;
	private long previousTime;
	private boolean nextCharacter;
	private Group root;
	private Canvas canvas;

	// Constructor
	public ChooseCharacter1(Scene theScene, Scene menuScene, Stage stage) {
		this.root = new Group();
        this.menuScene = menuScene;
        this.stage = stage;
        this.characterP1 = Formatting.KNIGHT;
        this.animationCount = 0;
        this.nextCharacter = false;
        this.previousTime = System.nanoTime();
    	this.canvas = new Canvas(Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
        this.characterP1Scene = theScene;

        // Set the scene's root to the group
        this.characterP1Scene.setRoot(this.root);
        this.root.getChildren().add(canvas);
        
		// Draw Background (1st frame)
		this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.handleKeyPressEvent();
	}
	
//	@Override
	public void handle(long currentTime) {
		// Show the selection of characters per frame
		if (nextCharacter == false) {
			showCharacters(currentTime);//shows character if not yet selected
		} else {
			player2Selection(this.characterP1);//transitions to player2selection if the character 1 has been selected
		}
		
	}
	
//	Sets up key press input by the user for handling character selection
	private void handleKeyPressEvent() {
		characterP1Scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();//gets the key code
            	nextCharacter(code);
			}
			
		});
		
	}
	
//	Handles key presses to change the selected character of the player
	public void nextCharacter(KeyCode code) {
	    switch (code) {// code is the key that was pressed
	        case RIGHT, D:
	        	//moves the character selection to the right
	            this.characterP1++;
	            break;
	        case LEFT, A:
	        	//moves the character selection to the left
	            this.characterP1--;
	            break;
	        case SPACE:
	        	//the player 1 have selected a character
	            this.nextCharacter = true;
	            switch (this.characterP1) {
	                case Formatting.KNIGHT:
	                    System.out.println("Player 1 chosen Knight");
	                    break;
	                case Formatting.ORC:
	                    System.out.println("Player 1 chosen Orc");
	                    break;
	                case Formatting.SWORDWOMAN:
	                    System.out.println("Player 1 chosen SwordWoman");
	                    break;
	                case Formatting.WIZARD:
	                    System.out.println("Player 1 chosen Wizard");
	                    break;
	            }
	            break;
	        default:
	        	//invalid cases
	            System.out.println("Unknown key pressed.");
	            break;
	    }
//		key press confirmation
	    System.out.println(code + " key pressed.");
	}

	
//	Function for rendering the images
	public void showCharacters(long currentTime) {
	    // Algorithm for looping through the characters
	    if (this.characterP1 > 4) {
	        this.characterP1 = 1;
	    } else if (this.characterP1 <= 0) {
	        this.characterP1 = 4;
	    }

	    if (currentTime - previousTime > 250 * 1000000) {
	        // Clears the canvas
	        this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	        this.animationCount++;
	        this.animationCount %= 3;

	        // Check the current selection then display it
	        switch (this.characterP1) {
	            case Formatting.KNIGHT:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P1KNIGHT2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.ORC:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P1ORC1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P1ORC2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.SWORDWOMAN:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P1SWORDWOMAN1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P1SWORDWOMAN2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.WIZARD:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P1WIZARD1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P1WIZARD2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	        }
	        this.gc.drawImage(Formatting.CCCONTROLS,  0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	        this.previousTime = currentTime;
	    }
	    
//		Hover FX
	    // ImageView creation
	    ImageView knightView = createImageView(Formatting.KNIGHTV, -80, 275, 430, 220);
	    ImageView swordWomanView = createImageView(Formatting.SWV, 480, 300, 450, 230);
	    ImageView orcView = createImageView(Formatting.ORCV, 280, 250, 379, 325);
	    ImageView wizardView = createImageView(Formatting.WIZV, 510, 240, 1000, 287);

	    // Text creation and formatting
	    Text hoverKnight = createHoverText("Starts with high attack power, making him a formidable melee fighter", 550, 680);
	    Text hoverOrc = createHoverText("Boasts superior starting health, allowing him to endure more damage in battle", 550, 680);
	    Text hoverSW = createHoverText("The fastest character, excelling in speed and agility on the battlefield", 550, 680);
	    Text hoverWiz = createHoverText("Specializes in long-range attacks, casting powerful fireballs to strike enemies from a distance", 550, 680);

	    // Add hover event handlers
	    knightView.setOnMouseEntered(event -> hoverKnight.setVisible(true));
	    knightView.setOnMouseExited(event -> hoverKnight.setVisible(false));
	    orcView.setOnMouseEntered(event -> hoverOrc.setVisible(true));
	    orcView.setOnMouseExited(event -> hoverOrc.setVisible(false));
	    swordWomanView.setOnMouseEntered(event -> hoverSW.setVisible(true));
	    swordWomanView.setOnMouseExited(event -> hoverSW.setVisible(false));
	    wizardView.setOnMouseEntered(event -> hoverWiz.setVisible(true));
	    wizardView.setOnMouseExited(event -> hoverWiz.setVisible(false));

	    // Add all ImageView and Text nodes to the root group
	    this.root.getChildren().addAll(knightView, swordWomanView, orcView, wizardView, hoverKnight, hoverOrc, hoverSW, hoverWiz);

	}
	
	  // Method to create an ImageView for hover fx
    private ImageView createImageView(Image image, double x, double y, double width, double height) {
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setOpacity(0);
        return imageView;
    }

    // Method to create a Text with formatting
    private Text createHoverText(String text, double x, double y) {
        Text hoverText = new Text(text);
        hoverText.setVisible(false); // Initially hidden
        hoverText.setX(x - hoverText.getLayoutBounds().getWidth() / 2);
        hoverText.setY(y);
        hoverText.setFill(Color.WHITE);
        hoverText.setFont(Formatting.FONT_STYLE_22);
        return hoverText;
    }
    
    
//	Proceeds to player2secelction after player 1 have chosen a character
	public void player2Selection (int player1) {
		this.stop();
		System.out.println("Choose player 2!" );
		// chooseCharacter2 object
		ChooseCharacter2 chooseCharacter2 = new ChooseCharacter2(this.characterP1Scene,  this.stage, this.menuScene, player1);
		chooseCharacter2.start();
	}
	//returns the current scene
	public Scene getScene() {
		return characterP1Scene;
	}
	
}
