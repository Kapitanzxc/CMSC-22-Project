package elements;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameTimer extends AnimationTimer {

	private Scene gameScene;
	private GraphicsContext gc;
	private Knight player1;
	private Knight player2;
	
	GameTimer(GraphicsContext gc, Scene theScene) {
		this.gc = gc;
		this.gameScene = theScene;
//		Creating two players
		this.player1 = new Knight(344,100,System.nanoTime());
		this.player2 = new Knight(600,100,System.nanoTime());
		//call method to handle key click event
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentNanoTime) {
//		Runs the gameplay 
		gameplay(currentNanoTime);

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
	}
	
	//method that will move the chracter depending on the key pressed
	private void moveCharacter(KeyCode ke) {
		if(ke==KeyCode.W) {
			this.player1.setDY(-3);                 
		}
		if(ke==KeyCode.A) {
			this.player1.setDX(-3);
		}
		if(ke==KeyCode.S) {
			this.player1.setDY(3);
		}
		if(ke==KeyCode.D) {
			this.player1.setDX(3);
		}
		if(ke==KeyCode.UP) {
			this.player2.setDY(-3);                 
		}
		if(ke==KeyCode.LEFT) {
			this.player2.setDX(-3);
		}
		if(ke==KeyCode.DOWN) {
			this.player2.setDY(3);
		}
		if(ke==KeyCode.RIGHT) {
			this.player2.setDX(3);
		}
		if(ke==KeyCode.F) {
			this.player1.setAttack(true);
		}	
		if(ke==KeyCode.K) {
			this.player2.setAttack(true);
		}
		if(ke==KeyCode.SPACE) {
			System.out.println("X: " + this.player2.x + " Y: " + this.player2.y);
			this.player2.setAttack(true);
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
	
	public Scene getScene() {
		return gameScene;
	}

}
