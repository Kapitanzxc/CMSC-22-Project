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
	private Knight knight;
	private Knight pixel;
	private long previousTime;
	private Knight knight2;
	private long previousTime2;
	
	GameTimer(GraphicsContext gc, Scene theScene) {
		this.gc = gc;
		this.previousTime = System.nanoTime();
		this.gameScene = theScene;
//		Two knights
		this.knight = new Knight(344,100,2);
		this.knight2 = new Knight(370,100,2);
		//call method to handle mouse click event
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentNanoTime) {
		handle2(currentNanoTime);

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
	
	public void handle2(long currentNanoTime) {
		// TODO Auto-generated method stub
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc.drawImage(Formatting.MAP, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		// Method to move the two players
		this.knight.move();
		this.knight2.move();
//		Method to show idle animation
		this.previousTime = this.knight.idleStart(System.nanoTime(), this.previousTime);
		this.previousTime2 = this.knight2.idleStart(System.nanoTime(), this.previousTime2);
//		Spawning two knights
		this.knight.render(this.gc);
		this.knight2.render(this.gc);
	}
	
	//method that will move the knight depending on the key pressed
	private void moveCharacter(KeyCode ke) {
		if(ke==KeyCode.UP) {
			this.knight.setDY(-3);                 
		}
		if(ke==KeyCode.LEFT) {
			this.knight.setDX(-3);
		}
		if(ke==KeyCode.DOWN) {
			this.knight.setDY(3);
		}
		if(ke==KeyCode.RIGHT) {
			this.knight.setDX(3);
		}
		if(ke==KeyCode.W) {
			this.knight2.setDY(-3);                 
		}
		if(ke==KeyCode.A) {
			this.knight2.setDX(-3);
		}
		if(ke==KeyCode.S) {
			this.knight2.setDY(3);
		}
		if(ke==KeyCode.D) {
			this.knight2.setDX(3);
		}
			
		System.out.println(ke+" key pressed.");
   	}
	
	// Set dx and dy to 0 when character is not moving
	private void stopCharacter(KeyCode ke){
		if (ke == KeyCode.W || ke == KeyCode.A|| ke == KeyCode.S || ke == KeyCode.D) {
			this.knight2.setDX(0);
			this.knight2.setDY(0);
		}
		if (ke == KeyCode.UP || ke == KeyCode.LEFT|| ke == KeyCode.DOWN || ke == KeyCode.RIGHT) {
			this.knight.setDX(0);
			this.knight.setDY(0);
		}
	}
	
	
	public Scene getScene() {
		return gameScene;
	}

}
