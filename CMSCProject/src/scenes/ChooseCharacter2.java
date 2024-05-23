package scenes;

import elements.Formatting;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

public class ChooseCharacter2 extends AnimationTimer{
	private Stage stage;
	private Scene menuScene, characterP2Scene;
	private GraphicsContext gc;
	private int characterP2,animationCount,player1;
	private long previousTime;
	private boolean nextCharacter;
	private Group root;
	private Canvas canvas;

	// Constructor
	public ChooseCharacter2(Scene theScene,  Stage stage, Scene menuScene, int player1) {
		this.root = new Group();
		this.nextCharacter = false;
		this.player1 = player1;
		this.stage = stage;
		this.characterP2 = Formatting.KNIGHT;
		this.animationCount = 0;
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.characterP2Scene = theScene;
		this.menuScene = menuScene;
		
		  // Set the scene's root to the group
        this.characterP2Scene.setRoot(this.root);
        this.root.getChildren().add(canvas);
        
		// Draw Background (1st frame)
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentTime) {
		// Show the selection of characters per frame
		if (this.nextCharacter == false) {
			showCharacters(currentTime);
		} else {
			gameplayScene();;
		}
		showCharacters(currentTime);
	}
	
//	Reads user input
	private void handleKeyPressEvent() {
		characterP2Scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
            	nextCharacter(code);
			}
			
		});
		
	}
//	Change image whenever the user input a code
	public void nextCharacter(KeyCode code) {
	    switch (code) {
	        case RIGHT, D:
	            this.characterP2++;
	            break;
	        case LEFT, A:
	            this.characterP2--;
	            break;
	        case SPACE, ENTER:
	            this.nextCharacter = true;
	            switch (this.characterP2) {
	                case Formatting.KNIGHT:
	                    System.out.println("Player 2 chosen Knight");
	                    break;
	                case Formatting.ORC:
	                    System.out.println("Player 2 chosen Orc");
	                    break;
	                case Formatting.SWORDWOMAN:
	                    System.out.println("Player 2 chosen SwordWoman");
	                    break;
	                case Formatting.WIZARD:
	                    System.out.println("Player 2 chosen Wizard");
	                    break;
	            }
		default:
			System.out.println("Invalid key pressed.");
			break;
	            
	    }

	    System.out.println(code + " key pressed.");
	}

//	Function for rendering the images
	public void showCharacters(long currentTime) {
	    // Algorithm for looping through the characters
	    if (this.characterP2 > 4) {
	        this.characterP2 = 1;
	    } else if (this.characterP2 <= 0) {
	        this.characterP2 = 4;
	    }

	    if (currentTime - previousTime > 250 * 1000000) {
	        // Clears the canvas
	        this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	        this.animationCount++;
	        this.animationCount %= 3;

	        // Check the current selection then display it
	        switch (this.characterP2) {
	            case Formatting.KNIGHT:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P2KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P2KNIGHT2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.ORC:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P2ORC1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P2ORC2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.SWORDWOMAN:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P2SWORDWOMAN1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P2SWORDWOMAN2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            case Formatting.WIZARD:
	                if (this.animationCount == 1) {
	                    this.gc.drawImage(Formatting.P2WIZARD1, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                } else {
	                    this.gc.drawImage(Formatting.P2WIZARD2, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	                }
	                break;
	            default:
	                System.out.println("Invalid character selected");
	                break;
	        }
	        this.gc.drawImage(Formatting.CCCONTROLS,  0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
	        this.previousTime = currentTime;
	    }
	    
//	 Hover FX
	    
	 // ImageView creation
	    ImageView knightView = createImageView(Formatting.KNIGHTV, -80, 275, 430, 220);
	    ImageView swordWomanView = createImageView(Formatting.SWV, 480, 300, 450, 230);
	    ImageView orcView = createImageView(Formatting.ORCV, 280, 250, 379, 325);
	    ImageView wizardView = createImageView(Formatting.WIZV, 510, 240, 1000, 287);

	    // Text creation and formatting
	    Text hoverKnight = createHoverText("This is the knight text", 550, 680);
	    Text hoverOrc = createHoverText("This is the orc text", 550, 680);
	    Text hoverSW = createHoverText("This is the swordwoman text", 550, 680);
	    Text hoverWiz = createHoverText("This is the wizard text", 550, 680);

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
	
	// Method to create an ImageView for hover effects
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
    

//	Proceed to gameplayScene after selecting a character
	public void gameplayScene () {
		this.stop();
		System.out.println("Load game scene");
		GameScene gameScene = new GameScene(this.menuScene, this.stage, this.player1, this.characterP2);
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this.characterP2Scene.getRoot());
		    fadeOut.setFromValue(1.0);
		    fadeOut.setToValue(0.0);
		    fadeOut.setOnFinished(e -> {
		        stage.setScene(gameScene.getScene());
		        ;
		    });
		    fadeOut.play();
	}
	
	public Scene getScene() {
		return characterP2Scene;
	}
	
}
