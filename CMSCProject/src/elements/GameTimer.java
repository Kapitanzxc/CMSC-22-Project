package elements;

import java.util.ArrayList;

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
	private Stage stage;
	private Group root;
	private Scene gameScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private ArrayList<Text> texts;
	private double XPOS;
	private double YPOS;
	private Knight knight;
	
	GameTimer(GraphicsContext gc, Scene theScene) {
		this.gc = gc;
		this.gameScene = theScene;
		this.knight = new Knight(100,100);
		//call method to handle mouse click event
		this.handleKeyPressEvent();
		this.XPOS = Formatting.SCREEN_WIDTH/2;
		this.YPOS = Formatting.SCREEN_HEIGHT/2;
	}
	
	@Override
	public void handle(long currentNanoTime) {
		// TODO Auto-generated method stub
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.gc.drawImage(Formatting.MAP, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		//call the methods to move the ship
		this.knight.move();
		
		//render the ship
		this.knight.render(this.gc);
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
	
	//method that will move the ship depending on the key pressed
	private void moveCharacter(KeyCode ke) {
		if(ke==KeyCode.UP) {
			this.knight.setDY(-3);                 
		}
		if(ke==KeyCode.LEFT) {
			this.knight.setDX(-3);
			this.knight.moveLeft();
		}
		if(ke==KeyCode.DOWN) {
			this.knight.setDY(3);
		}
		if(ke==KeyCode.RIGHT) {
			this.knight.setDX(3);
			this.knight.moveRight();
		}
		System.out.println(ke+" key pressed.");
   	}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopCharacter(KeyCode ke){
		this.knight.setDX(0);
		this.knight.setDY(0);
	}
	
	public Scene getScene() {
		return gameScene;
	}

}
