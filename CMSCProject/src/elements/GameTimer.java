package elements;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameTimer extends AnimationTimer {
//	Attributes
	private Scene gameScene;
	private GraphicsContext gc;
	private Stage stage;
	private Scene menuScene;
	private boolean gameOver;
	private Knight player1;
	private Knight player2;
	
	GameTimer(GraphicsContext gc, Scene theScene, Scene menuScene, Stage stage, int player1, int player2) {
		this.stage = stage;
		this.menuScene = menuScene;
		this.gc = gc;
		this.gameScene = theScene;
//		Creating two players
		if (player1 == Formatting.KNIGHT) {
			this.player1 = new Knight(344,100,System.nanoTime());
		} 
		
		if (player2 == Formatting.KNIGHT) {
			this.player2 = new Knight(600,100,System.nanoTime());
		}

		//call method to handle key click event
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentNanoTime) {
//		Runs the gameplay and checks game over situation
		if (!this.gameOver) {
//			Run gameplay 
			gameplay(currentNanoTime);
//			Check if one player is already dead
			if (!player1.checkAlive() || !player2.checkAlive()) {
//				If dead: Finish the die animation
				if (!player1.checkAlive()) {
					this.gameOver = player1.dieAnimation(System.nanoTime());
					this.player1.render(this.gc);
				} else {
					this.gameOver = player2.dieAnimation(System.nanoTime());
					this.player2.render(this.gc);
				}
			}
//		End the game 
		} else {
			endGame();
		}
	}
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveCharacter(code);
			}
			
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                stopCharacter(code);
        		
            }
        });
    }
	
	public void gameplay(long currentNanoTime) {
		// TODO Auto-generated method stub
//		Resets the screen and draw the map
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc.drawImage(Formatting.MAP, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		// Check players movement and update it accordingly
		this.player1.move();
		this.player2.move();
//		Shows players respective animations
		this.player1.animation(System.nanoTime(), this.player2);
		this.player2.animation(System.nanoTime(), this.player1);
//		Spawning two players
		this.player1.render(this.gc);
		this.player2.render(this.gc);
//		Draw structure
		this.gc.drawImage(Formatting.TOWER, 307, 202);
		this.gc.drawImage(Formatting.TOWER, 844, 202);
	}
	
	//method that will move the chracter depending on the key pressed
	private void moveCharacter(KeyCode ke) {
		if(ke==KeyCode.W) {
			this.player1.setDY(-2);                 
		}
		if(ke==KeyCode.A) {
			this.player1.setDX(-2);
		}
		if(ke==KeyCode.S) {
			this.player1.setDY(2);
		}
		if(ke==KeyCode.D) {
			this.player1.setDX(2);
		}
		if(ke==KeyCode.UP) {
			this.player2.setDY(-2);                 
		}
		if(ke==KeyCode.LEFT) {
			this.player2.setDX(-2);
		}
		if(ke==KeyCode.DOWN) {
			this.player2.setDY(2);
		}
		if(ke==KeyCode.RIGHT) {
			this.player2.setDX(2);
		}
		if(ke==KeyCode.F) {
			this.player1.setAttack(true);
		}	
		if(ke==KeyCode.K) {
			this.player2.setAttack(true);
		}
		if(ke==KeyCode.SPACE) {
			int x =  this.player2.x + this.player2.width;
			int y = this.player2.y + this.player2.height;
			System.out.println("X: " + x + " Y: " + y);
		}
		System.out.println(ke+" key pressed.");
   	}
	
	// Set dx and dy to 0 when character is not moving
	private void stopCharacter(KeyCode ke){
		if (ke == KeyCode.W || ke == KeyCode.A|| ke == KeyCode.S || ke == KeyCode.D) {
			this.player1.setDX(0);
			this.player1.setDY(0);
		}
		if (ke == KeyCode.UP || ke == KeyCode.LEFT|| ke == KeyCode.DOWN || ke == KeyCode.RIGHT) {
			this.player2.setDX(0);
			this.player2.setDY(0);
		}
	}
	
//	End game method
	private void endGame() {
//		Stop the timer
	 this.stop();
//	    Reference: https://stackoverflow.com/questions/24978278/fade-in-fade-out-a-screen-in-javafx
	    FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), stage.getScene().getRoot());
	    fadeOut.setFromValue(1.0);
	    fadeOut.setToValue(0.0);
	    fadeOut.setOnFinished(event -> {
	        // Proceed to winning scene
	        WinningScene winningScene = new WinningScene(this.menuScene, this.stage);
	        stage.setScene(winningScene.getScene());
	    });
	    
	    fadeOut.play();
	}

	
	public Scene getScene() {
		return gameScene;
	}

}
