package scenes;

import java.util.Random;

import characters.Knight;
import characters.Orc;
import characters.Sprite;
import characters.SwordWoman;
import characters.Wizard;
import elements.Formatting;
import elements.PowerUp;

import java.util.ArrayList;

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
import monsters.Demon1;
import monsters.Demon2;
import monsters.Demon3;
import monsters.Monster;
import monsters.Ogre1;
import monsters.Ogre2;
import monsters.Ogre3;
import monsters.Zombie1;
import monsters.Zombie2;
import monsters.Zombie3;

public class GameTimer extends AnimationTimer {
//	Attributes
	private Scene gameScene,menuScene;
	private GraphicsContext gc;
	private Stage stage;
	private boolean gameOver;
	private Sprite player1,player2;
	private int playerWinner,player1Code,player2Code,animationCountTimer, playerWinNum;
	private long previousTimerTime,time,previousTimerBG, previousTimeMonster;
	private Image timerBGimg;
//	private int numMonster;

//	****************
//	Power-ups
	private ArrayList<PowerUp> fragmentPowerUps, specialPowerUps;
	private int spawnX, spawnY;
	private long startFPowerUpSpawn, startSPowerUpSpawn;
	private boolean initialSpawn;
	
	private final static int SPAWNDELAY_FPOWERUP = 5; 	// 5 seconds
	private final static int SPAWNDELAY_SPOWERUP = 20; 	// 20 seconds
	private final static int UPTIME_SPOWERUP = 10;
	private final static int UPTIME_MONSTER = 10;
	private final static int NUM_FRAGMENT_POWERUP = 40;	// 40 fragments
	private final static int NUM_SPECIAL_POWERUP = 3;	// 3 special power-ups (1 of each type)
//	****************
	
	// Monsters
	private ArrayList<Monster> monsterArrayList;
	private int monsterX, monsterY;
	private long startMonsterSpawn;
	
	private final static int SPAWNDELAY_MONSTERS = 5; // spawn monster every after 5 seconds
	private final static int NUM_MONSTER = 6;	// spawn 6 monsters
	
	GameTimer(GraphicsContext gc, Scene theScene, Scene menuScene, Stage stage, int player1, int player2) {
		this.player1Code = player1;
		this.player2Code = player2;
		this.time = 121;
		this.playerWinNum = 0;
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
        this.previousTimeMonster = System.nanoTime();
        
//      Power Ups
        this.startFPowerUpSpawn = System.nanoTime();
        this.startSPowerUpSpawn = System.nanoTime();
        this.fragmentPowerUps = new ArrayList<PowerUp>();
        this.specialPowerUps = new ArrayList<PowerUp>();
        this.initialSpawn = true;

        this.monsterArrayList = new ArrayList<Monster>();
//		call method to handle key click event
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
		if (!this.gameOver && this.time > 0) {
//			Run gameplay 
			gameplay(currentNanoTime);
			
//			Check if one player is already dead
			if (!player1.checkAlive() || !player2.checkAlive()) {
				
//				If dead: Finish the die animation
				if (!player1.checkAlive()) {
					this.gameOver = player1.dieAnimation(System.nanoTime());
					this.player1.render(this.gc);
					this.playerWinner = this.player2Code;
					this.playerWinNum = 2;
				} else {
					this.gameOver = player2.dieAnimation(System.nanoTime());
					this.player2.render(this.gc);
					this.playerWinner = this.player1Code;
					this.playerWinNum = 1;
				}
			}
//		End the game 
		} else if (this.gameOver){
//			One of the players died
			endGame(0);
		} else {
//			Game finishes because of time
			endGame(1);
		}
	}
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                keysCharacter(code);
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
//		Resets the screen and draw the map
		this.gc.clearRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		gc.setFill(Color.BLACK); 
	    gc.fillRect(0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT); 
		timerBackground(currentNanoTime);
		this.gc.drawImage(Formatting.MAP, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		timer(currentNanoTime);

//		Spawning Fragment Power-ups
		spawnFragmentPowerUps(currentNanoTime);
//		Spawning Special Power-ups
		spawnSpecialPowerUps(currentNanoTime);
		// Spawn Monsters
		spawnMonsters(currentNanoTime);
//		Rendering Power-ups
		renderPowerUps();
		// Rendering Monsters
		renderMonsters();
	

//		Check players movement and update it accordingly
		this.player1.move();
		this.player2.move();
//		Shows players respective animations
		this.player1.animation(System.nanoTime(), this.player2);
		this.player2.animation(System.nanoTime(), this.player1);
		// Monster animation
		animationMonster(currentNanoTime);
//		Spawning two players
		this.player1.render(this.gc);
		this.player2.render(this.gc);
//		Draw structure
		this.gc.drawImage(Formatting.TOWER, 307, 202);
		this.gc.drawImage(Formatting.TOWER, 844, 202);

//		Deletes Power-ups (by collecting and expiration)
		deletePowerUps(currentNanoTime);
		// Deleting Monster every 10 seconds
//		deleteMonsters(currentNanoTime);
	}

	private void spawnMonsters(long currentTime){
// 		Spawn monsters
		long monsterElapsedTime = (currentTime - this.previousTimeMonster) / 1000000000;
//		if (currentTime - this.previousTimeMonster >= 5000*1000000) {
		this.startMonsterSpawn = System.nanoTime();
		if (monsterElapsedTime >= SPAWNDELAY_MONSTERS) {
			int[] monsterType = {11, 12, 13, 21, 22, 23, 31, 32, 33};
			Random random = new Random();
			
			for (int i = monsterArrayList.size(); i < NUM_MONSTER; i++) {
				spawnY = Monster.spawnY();
				spawnX = Monster.spawnX(spawnY);
				
				// Random spawning of monsters
				int randomType = monsterType[random.nextInt(monsterType.length)];
				int randomDirection = random.nextInt(1, 3);
				Monster monster = createMonster(randomType, spawnX, spawnY, randomDirection);
				this.monsterArrayList.add(monster);
			}
			
			this.previousTimeMonster = currentTime;
		}
	}
	
	private Monster createMonster(int monsterType, int x, int y, int direction){
        switch (monsterType) {
	        case Formatting.ZOMBIE1:
	            return new Zombie1(x, y, direction);
	        case Formatting.ZOMBIE2:
	            return new Zombie2(x, y, direction);
            case Formatting.ZOMBIE3:
                return new Zombie3(x, y, direction);
            case Formatting.OGRE1:
	            return new Ogre1(x, y, direction);
	        case Formatting.OGRE2:
	            return new Ogre2(x, y, direction);
            case Formatting.OGRE3:
                return new Ogre3(x, y, direction);
            case Formatting.DEMON1:
	            return new Demon1(x, y, direction);
	        case Formatting.DEMON2:
	            return new Demon2(x, y, direction);
            case Formatting.DEMON3:
                return new Demon3(x, y, direction);
        }
		return null;
	}
	
//  Render monster
	private void renderMonsters() {
		for (Monster monster : this.monsterArrayList) {	        
	        monster.render(this.gc);
	    }
	}
	
//	// Delete Monster
//		public void dieMonster(long currentNanoTime) {		
//			for(Monster monster : monsterArrayList){
//				if (monster.health < 0){ // check monster's health
//					monster.setVisible(false);
//				}
//			}		
//		}
	
	// Delete Monster
	public void animationMonster(long currentTime) {		
		for(Monster monster : monsterArrayList){
			monster.animation(currentTime);
		}
	}
	

////	Delete monsters that is idle for 10 seconds
//	private void deleteMonsters(long currentNanoTime) {		
//		for(Monster monster : this.monsterArrayList){
//			if (((currentNanoTime-this.startMonsterSpawn) / 1000000000.0) >= UPTIME_MONSTER){ // checks if 5 seconds have elapsed
//				monster.setVisible(false);
//				this.monsterArrayList.remove(monster); // removed from the array list
//			}
//		}		
//	}
	private void generateFragmentPowerUps(int numFragments){
// 		Create fragment type power-ups
        for (int i = fragmentPowerUps.size(); i < numFragments; i++) {
            spawnY = PowerUp.spawnY();
            spawnX = PowerUp.spawnX(spawnY);
            PowerUp fragment = new PowerUp(spawnX, spawnY, 1, System.nanoTime(), 0, 1);
            this.fragmentPowerUps.add(fragment);
        }
	}
	
	private void generateSpecialPowerUps() {        
//		Create special type power-ups
        for (int i = 0; i < NUM_SPECIAL_POWERUP; i++) {
        	spawnY = PowerUp.spawnY();
            spawnX = PowerUp.spawnX(spawnY);
            PowerUp special = new PowerUp(spawnX, spawnY, i+2, System.nanoTime(), 8000*1000000, 10);
            this.specialPowerUps.add(special);
        }
	}
	
	private void spawnFragmentPowerUps(long currentNanoTime) {
	    // Initial Spawn of Fragment Power-ups
	    if (this.initialSpawn) {
	        this.generateFragmentPowerUps(NUM_FRAGMENT_POWERUP); // generates power ups
	        this.startFPowerUpSpawn = System.nanoTime(); // resets time to compare again until the spawn delay
	        this.initialSpawn = false;
	    } else {
	        // Elapsed time from spawn to current (in seconds)
	        double spawnElapsedTime = (currentNanoTime - this.startFPowerUpSpawn) / 1000000000.0;

	        // Respawn every 5 seconds (if fragments < 20)
	        if (spawnElapsedTime > SPAWNDELAY_FPOWERUP) {
	            this.generateFragmentPowerUps(NUM_FRAGMENT_POWERUP); // generates power ups
	            this.startFPowerUpSpawn = currentNanoTime; // resets time to compare again until the spawn delay
	        }
	    }

	    collectPowerUps(currentNanoTime); // check if collides with player or not
	}
	
	private void spawnSpecialPowerUps(long currentNanoTime) {
//		Elapsed time from spawn to current (in seconds)
		double spawnElapsedTime = (currentNanoTime - this.startSPowerUpSpawn) / 1000000000.0;
        
// 		Spawn special power-ups
   		if (spawnElapsedTime > SPAWNDELAY_SPOWERUP) {
     	  	this.generateSpecialPowerUps(); // generates power ups
     	  	this.startSPowerUpSpawn = System.nanoTime(); // resets time to compare again until 10 seconds elapsed
   		}
   		
//   	Check if a special power up collides with a player or not
   		collectPowerUps(currentNanoTime);
	}
	

		
//  Render power-ups
	private void renderPowerUps() {
		for (PowerUp special : this.specialPowerUps) {	        
	        special.render(this.gc);
	    }
		
		for (PowerUp fragment : this.fragmentPowerUps) {
			fragment.render(this.gc);
		}
	}
	
	private void collectPowerUps(long currentNanoTime) {
		for(int i = 0; i < this.specialPowerUps.size(); i++){
	        PowerUp special = this.specialPowerUps.get(i);
	        if (special.getAlive()){
	        	special.checkPowerUpCollision(this.player1, currentNanoTime);
	        	special.checkPowerUpCollision(this.player2, currentNanoTime);
	        } else {
//	        	Removes special power ups if idle for 10 seconds or picked up
	        	this.specialPowerUps.remove(i);
	        }
	    }
		
		for(int i = 0; i < this.fragmentPowerUps.size(); i++){
	        PowerUp fragment = this.fragmentPowerUps.get(i);
	        if (fragment.getAlive()){
	        	fragment.checkPowerUpCollision(this.player1, currentNanoTime);
	        	fragment.checkPowerUpCollision(this.player2, currentNanoTime);
	        } else {
//	        	Removes fragments that are picked up 
	        	this.fragmentPowerUps.remove(i);
	        }
	    }
	}

//	Delete special power-ups that is idle for 10 seconds
	private void deletePowerUps(long currentNanoTime) {		
		for(int i = 0; i < this.specialPowerUps.size(); i++){
			if (((currentNanoTime-this.startSPowerUpSpawn) / 1000000000.0) >= UPTIME_SPOWERUP){ // checks if 5 seconds have elapsed
				this.specialPowerUps.get(i).setAlive(false);
				this.specialPowerUps.remove(i); // removed from the array list
			}
		}		
	}
	

//	**********
//	yung set na 2 ginawa kong speed para mapalitan kapag nag speed boost
//	**********

	//method that will read users input
	private void keysCharacter(KeyCode ke) {
	    switch (ke) {
	        case W:
	            this.player1.setDY(-this.player1.getSpeed());
	            break;
	        case A:
	            this.player1.setDX(-this.player1.getSpeed());
	            break;
	        case S:
	            this.player1.setDY(this.player1.getSpeed());
	            break;
	        case D:
	            this.player1.setDX(this.player1.getSpeed());
	            break;
	        case UP:
	            this.player2.setDY(-this.player2.getSpeed());
	            break;
	        case LEFT:
	            this.player2.setDX(-this.player2.getSpeed());
	            break;
	        case DOWN:
	            this.player2.setDY(this.player2.getSpeed());
	            break;
	        case RIGHT:
	            this.player2.setDX(this.player2.getSpeed());
	            break;
	        case F:
	            this.player1.setAttack(true);
	            break;
	        case K:
	            this.player2.setAttack(true);
	            break;
	        case SPACE:
//	            int x = this.player2.x + this.player2.width;
//	            int y = this.player2.y + this.player2.height;
//	            System.out.println("X: " + x + " Y: " + y);
	            break;
	        case Y:
	            this.player1.setShowBoxes(!this.player1.isShowBoxes());
	            this.player2.setShowBoxes(!this.player2.isShowBoxes());
	            break;
	        case U:
	            this.player1.setShowCBoxes(!this.player1.isShowCBoxes());
	            this.player2.setShowCBoxes(!this.player2.isShowCBoxes());
	            break;
	        default:
	            System.out.println(ke + " key pressed.");
	            break;
	    }
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
	public void timerBackground(long currentTime) {
	    if (currentTime - this.previousTimerBG >= 100 * 1000000) {
	        this.animationCountTimer++;
	        this.animationCountTimer %= 11;
	        switch (animationCountTimer) {
	            case 1:
	                this.timerBGimg = Formatting.TIMER1;
	                break;
	            case 2:
	                this.timerBGimg = Formatting.TIMER2;
	                break;
	            case 3:
	                this.timerBGimg = Formatting.TIMER3;
	                break;
	            case 4:
	                this.timerBGimg = Formatting.TIMER4;
	                break;
	            case 5:
	                this.timerBGimg = Formatting.TIMER5;
	                break;
	            case 6:
	                this.timerBGimg = Formatting.TIMER6;
	                break;
	            case 7:
	                this.timerBGimg = Formatting.TIMER7;
	                break;
	            case 8:
	                this.timerBGimg = Formatting.TIMER8;
	                break;
	            case 9:
	                this.timerBGimg = Formatting.TIMER9;
	                break;
	            case 10:
	                this.timerBGimg = Formatting.TIMER10;
	                break;
	        }
	        this.previousTimerBG = currentTime;
	    }
	    this.gc.drawImage(this.timerBGimg, 524, -20);
	}

	
	
//	End game method
	private void endGame(int winningType) {
//		Stop the timer
	 this.stop();
//	    Reference: https://stackoverflow.com/questions/24978278/fade-in-fade-out-a-screen-in-javafx
	    FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), stage.getScene().getRoot());
	    fadeOut.setFromValue(1.0);
	    fadeOut.setToValue(0.0);
	    fadeOut.setOnFinished(event -> {
	    	WinningScene winningScene;
	        // Proceed to winning scene
	    	if (winningType == 0) {
//	    		When one of the players die:
	    		winningScene = new WinningScene(this.menuScene, this.stage, this.playerWinner, this.playerWinNum, this.player1, this.player2);
//	  	    Victory by time
	    	} else {
//	    		Checks whose player have the greater amount of attack points
	    		if (player1.getAttackPoints() > player2.getAttackPoints()) {
	    			this.playerWinNum = 1;
	    			winningScene = new WinningScene(this.menuScene, this.stage, this.player1Code, this.playerWinNum, this.player1, this.player2);
	    		} else if (player2.getAttackPoints() > player1.getAttackPoints()) {
	    			this.playerWinNum = 2;
	    			 winningScene = new WinningScene(this.menuScene, this.stage, this.player2Code, this.playerWinNum, this.player1, this.player2);
//	    		If draw:
	    		} else {
	    			winningScene = new WinningScene(this.menuScene, this.stage, 0, this.playerWinNum, this.player1, this.player2);
	    		}
	    		
	    	}
	    	stage.setScene(winningScene.getScene());
	    });
	    
	    fadeOut.play();
	}

	
	public Scene getScene() {
		return gameScene;
	}

}
