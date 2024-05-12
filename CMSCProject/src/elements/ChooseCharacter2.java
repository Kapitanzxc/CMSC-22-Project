package elements;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChooseCharacter2 extends AnimationTimer{
//	Attributes
	private Group root;
	private Stage stage;
	private Scene aboutScene;
	private Scene menuScene;
	private Canvas canvas;
	private Scene characterP2Scene;
	private GraphicsContext gc;
	private int characterP2;
	private int animationCount;
	private long previousTime;

	// Constructor
	public ChooseCharacter2(GraphicsContext gc, Scene theScene,  Stage stage, Scene menuScene, int player1) {
		this.stage = stage;
		this.characterP2 = Formatting.KNIGHT;
		this.animationCount = 0;
		this.previousTime = System.nanoTime();
		this.gc = gc;
		this.characterP2Scene = theScene;
		this.menuScene = menuScene;
		// Draw Background (1st frame)
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc.drawImage(Formatting.P1KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentTime) {
		// Show the selection of characters per frame
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
		if (code == KeyCode.RIGHT || code == KeyCode.D) {
    		this.characterP2 ++;
    	} 
		
		if (code == KeyCode.LEFT || code == KeyCode.A) {
			this.characterP2 --;
		}
		
		if (code == KeyCode.SPACE || code == KeyCode.ENTER) {
			gameplayScene(this.characterP2);
		}
		
		System.out.println(code+" key pressed.");
	}
//	Function for rendering the images
	public void showCharacters(long currentTime) {
//		Algorithm for looping through the characters
		if (this.characterP2 > 4) {
			this.characterP2 = 1;
		} else if (this.characterP2 <= 0){
			this.characterP2 = 4;
		}

		if (currentTime - previousTime > 250 * 1000000) {
//			Clears the canvas
			this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
			this.animationCount ++;
			this.animationCount %= 3;
//			Check the current selection then display it
			if (this.characterP2 == Formatting.KNIGHT) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P2KNIGHT1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P2KNIGHT2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			else if (this.characterP2 == Formatting.ORC) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P2ORC1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P2ORC2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			else if (this.characterP2 == Formatting.SWORDSWOMAN) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P2SWORDSWOMAN1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P2SWORDSWOMAN2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			
			else if (this.characterP2 == Formatting.WIZARD) {
				if (this.animationCount == 1) {
					this.gc.drawImage(Formatting.P2WIZARD1, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				} else {
					this.gc.drawImage(Formatting.P2WIZARD2, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
				}
			}
			this.previousTime = currentTime;
		}
	}
//	Proceed to gameplayScene after selecting a character
	public void gameplayScene (int player1) {
		this.stop();
		System.out.println("Load game scene");
		GameScene gameScene = new GameScene(this.menuScene, this.stage, player1, this.characterP2);
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
