package elements;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameTimer extends AnimationTimer {
//	Attributes
	private Scene gameScene;
	private GraphicsContext gc;
	private Stage stage;
	private Scene menuScene;
	private boolean gameOver;
	private Sprite player1;
	private Sprite player2;
	private int playerWinner;
	private int player1Code;
	private int player2Code;
	private long previousTimerTime;
	private long time;
	private int animationCountTimer;
	private long previousTimerBG;
	private Image timerBGimg;
	
	GameTimer(GraphicsContext gc, Scene theScene, Scene menuScene, Stage stage, int player1, int player2) {
		this.player1Code = player1;
		this.player2Code = player2;
		this.time = 121;
		this.previousTimerTime = System.nanoTime();
		this.animationCountTimer = 1;
		this.previousTimerBG = System.nanoTime();
		this.timerBGimg = Formatting.TIMER1;
		this.stage = stage;
		this.menuScene = menuScene;
		this.gc = gc;
		this.gameScene = theScene;
//		Creating two players
		this.player1 = createPlayer(player1, 500, 100, 1);
        this.player2 = createPlayer(player2, 600, 100, 2);

		//call method to handle key click event
		this.handleKeyPressEvent();
	}
	private Sprite createPlayer(int playerType, int x, int y, int playerNum) {
        switch (playerType) {
            case Formatting.KNIGHT:
                return new Knight(x, y, playerNum, System.nanoTime());
            case Formatting.ORC:
               return new Orc(x, y, playerNum, System.nanoTime());
            case Formatting.SWORDWOMAN:
                return new SwordWoman(x, y, playerNum, System.nanoTime());
            case Formatting.WIZARD:
                return new Wizard(x, y, playerNum, System.nanoTime());
            default:
//            	Reference: https://rollbar.com/blog/how-to-throw-illegalargumentexception-in-java/
            	throw new IllegalArgumentException("Invalid player type: " + playerType);
        }
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
					this.playerWinner = this.player2Code;
				} else {
					this.gameOver = player2.dieAnimation(System.nanoTime());
					this.player2.render(this.gc);
					this.playerWinner = this.player1Code;
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
	
	private void gameplay(long currentNanoTime) {
		// TODO Auto-generated method stub
//		Resets the screen and draw the map
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		gc.setFill(Color.BLACK); 
	    gc.fillRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT); 
		timerBackground(currentNanoTime);
		this.gc.drawImage(Formatting.MAP, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		timer(currentNanoTime);
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
		
		if(ke==KeyCode.Y) {
			this.player1.setShowBoxes(!this.player1.isShowBoxes());
			this.player2.setShowBoxes(!this.player2.isShowBoxes());
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
	
	private void timer(long currentTime) {
	    if (currentTime - this.previousTimerTime >= 1000 * 1000000) {
		    this.time --;
	        this.previousTimerTime = currentTime;
	        
	    }
//	    Fixing position per digits
	    gc.setFill(Color.WHITE);
        gc.setFont(Formatting.FONT_STYLE_26);
        if (time >= 100) {
        	gc.fillText("" + time, 573, 45);
        } 
        else if (time >= 10){
        	gc.fillText("" + time, 587, 45);
        } 
        else {
        	gc.fillText("" + time, 603, 45);
        }
      
	}
	
//	Frames for timer background
	public void timerBackground (long currentTime) {
		if (currentTime - this.previousTimerBG >= 100 * 1000000) {
			this.animationCountTimer ++;
			this.animationCountTimer %= 11;
			if (animationCountTimer == 1 || animationCountTimer == 0) {
				this.timerBGimg = Formatting.TIMER1;
			} 
			else if (animationCountTimer == 2) {
				this.timerBGimg = Formatting.TIMER2;
			} 
			else if (animationCountTimer == 3) {
				this.timerBGimg = Formatting.TIMER3;
			} 
			else if (animationCountTimer == 4) {
				this.timerBGimg = Formatting.TIMER4;
			} 
			else if (animationCountTimer == 5) {
				this.timerBGimg = Formatting.TIMER5;
			} 
			else if (animationCountTimer == 6) {
				this.timerBGimg = Formatting.TIMER6;
			} 
			else if (animationCountTimer == 7) {
				this.timerBGimg = Formatting.TIMER7;
			} 
			else if (animationCountTimer == 8) {
				this.timerBGimg = Formatting.TIMER8;
			} 
			else if (animationCountTimer == 9) {
				this.timerBGimg = Formatting.TIMER9;
			} 
			else if (animationCountTimer == 10) {
				this.timerBGimg = Formatting.TIMER10;
			} 
			
			this.previousTimerBG = currentTime;
		}
		this.gc.drawImage(this.timerBGimg, 524, -20);;	
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
	        WinningScene winningScene = new WinningScene(this.menuScene, this.stage, this.playerWinner);
	        stage.setScene(winningScene.getScene());
	    });
	    
	    fadeOut.play();
	}

	
	public Scene getScene() {
		return gameScene;
	}

}
