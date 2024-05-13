package elements;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseCharacter1 extends AnimationTimer{
//	Attributes
	private Group root;
	private Stage stage;
	private Scene aboutScene;
	private Scene menuScene;
	private Canvas canvas;
	private Scene characterP1Scene;
	private GraphicsContext gc;
	private int characterP1;
	private int animationCount;
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
		if (code == KeyCode.RIGHT || code == KeyCode.D) {
    		this.characterP1 ++;
    	} 
		
		if (code == KeyCode.LEFT || code == KeyCode.A) {
			this.characterP1 --;
		}
		
		if (code == KeyCode.SPACE) {
			this.nextCharacter = true;
			if (this.characterP1 == Formatting.KNIGHT) {
				System.out.println("Player 1 chosen Knight");
			} 
			else if (this.characterP1 == Formatting.ORC) {
				System.out.println("Player 1 chosen Orc");
			}
			else if (this.characterP1 == Formatting.SWORDWOMAN) {
				System.out.println("Player 1 chosen SwordWoman");
			}
			else if (this.characterP1 == Formatting.WIZARD) {
				System.out.println("Player 1 chosen Wizard");
			}
		}
		
		System.out.println(code+" key pressed.");
	}
	
//	Function for rendering the images
	public void showCharacters(long currentTime) {
//		Algorithm for looping through the characters
		if (this.characterP1 > 4) {
			this.characterP1 = 1;
		} else if (this.characterP1 <= 0){
			this.characterP1 = 4;
		}
		
		if (currentTime - previousTime > 250 * 1000000) {
//			Clears the canvas
			this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
			this.animationCount ++;
			this.animationCount %= 3;
			
//			Check the current selection then display it
			if (this.characterP1 == Formatting.KNIGHT) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P1KNIGHT2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			
			else if (this.characterP1 == Formatting.ORC) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P1ORC1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P1ORC2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}

			else if (this.characterP1 == Formatting.SWORDWOMAN) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P1SWORDWOMAN1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P1SWORDWOMAN2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			else if (this.characterP1 == Formatting.WIZARD) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P1WIZARD1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P1WIZARD2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			this.previousTime = currentTime;
		}
	}
//	Proceed to player2secelction after selecting a character
	public void player2Selection (int player1) {
		this.stop();
		System.out.println("Choose player 2!");
		ChooseCharacter2 chooseCharacter2 = new ChooseCharacter2(this.gc, this.characterP1Scene,  this.stage, this.menuScene, player1);
		chooseCharacter2.start();
	}
	
	public Scene getScene() {
		return characterP1Scene;
	}
	
}
