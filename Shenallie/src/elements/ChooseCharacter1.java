package elements;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChooseCharacter1 extends AnimationTimer{
private Stage stage;
	private Scene menuScene, characterP1Scene;
	private GraphicsContext gc;
	private int characterP1,animationCount;
	private long previousTime;
	private boolean nextCharacter;

	// Constructor
	public ChooseCharacter1(GraphicsContext gc, Scene theScene, Scene menuScene, Stage stage) {
		this.menuScene = menuScene;
		this.stage = stage;
		this.characterP1 = Formatting.KNIGHT;
		this.animationCount = 0;
		this.nextCharacter = false;
		this.previousTime = System.nanoTime();
		this.gc = gc;
		this.characterP1Scene = theScene;
		// Draw Background (1st frame)
		this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentTime) {
		// Show the selection of characters per frame
		if (nextCharacter == false) {
			showCharacters(currentTime);
		} else {
			player2Selection(this.characterP1);
		}
		
	}
	
//	Reads user input
	private void handleKeyPressEvent() {
		characterP1Scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
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
	            this.characterP1++;
	            break;
	        case LEFT, A:
	            this.characterP1--;
	            break;
	        case SPACE:
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
	            System.out.println("Unknown key pressed.");
	            break;
	    }

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

	        this.previousTime = currentTime;
	    }
	}

//	Proceed to player2secelction after selecting a character
	public void player2Selection (int player1) {
		this.stop();
		System.out.println("Choose player 2!" );
		ChooseCharacter2 chooseCharacter2 = new ChooseCharacter2(this.gc, this.characterP1Scene,  this.stage, this.menuScene, player1);
		chooseCharacter2.start();
	}
	
	public Scene getScene() {
		return characterP1Scene;
	}
	
}
