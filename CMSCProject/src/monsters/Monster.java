package monsters;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import elements.Formatting;
import scenes.GameTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Monster{
//	Attributes
	protected Image img;
	private static Random random = new Random();
	protected int type;
	private long attackLastTime, hitTime;
	private int x, y, width, height, health, reward, attackTime, maxHealth;
	private double hitBoxW, hitBoxH, xOffset, yOffset;
	private boolean visible, showBoxes, hit;
	private Rectangle hitbox;
	
	private static int monsterX, monsterY;
	private final static int SPAWNDELAY_MONSTERS = 8; // spawn monster every after 8 seconds
	private final static int NUM_MONSTER = 1000;	// spawn 6 monsters
	private static final double MIN_DISTANCE_BETWEEN_MONSTERS = 100; // minimum distance between monsters
	
	public Monster(int xPos, int yPos, int type, int health, int reward,
			double xOffset, double yOffset,
			double hitBoxW, double hitBoxH) {
		this.type = type; // type of monster
		this.reward = reward; // reward of monster when killed
		this.attackLastTime= System.nanoTime(); // last time the monster attacked a character
		this.attackTime = 2; // how long does the monster will attack again (every 2 seconds)
		this.xOffset = xOffset; // how far is the body from the left
		this.yOffset = yOffset; // how far is the body from the above
		this.x = xPos;	// x coordinate			
		this.y = yPos;  // y coordinate
		this.hitBoxW = hitBoxW; // hitbox width
		this.hitBoxH = hitBoxH; // hitbox height
		this.health = health;   // monsters health
		this.maxHealth = health; // monsters max health
		this.showBoxes = false; // for showing monster hitbox
		this.visible = true; // if monster is still visible or not
		this.hit = false; // if monster is under attack
		this.hitTime = System.nanoTime();
		this.hitbox = new Rectangle (this.x, this.y, 0 , 0); // hitbox rectangle
	}
	
//	Load image
	protected void loadImage(Image img, int width, int height){
		try{
			this.img = img;
		  	this.width = width;
	        this.height = height;
		} catch(Exception e){}
	}
	
//	Render monster image
	public void render(GraphicsContext gc, long currentTime){
		if (this.visible) {
//			If visible, update hitbox
			hitbox();
//			Draw the monster
			gc.drawImage(this.img, this.x, this.y, this.width, this.height);
//			Show health bar
			renderHealthBar(gc, currentTime);
		}
		
//		Show hitboxes when toggled
		if (this.showBoxes) {
			gc.setStroke(Color.GREEN); 
		    gc.strokeRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}
    }
	
//	Method for rendering health bar
	private void renderHealthBar(GraphicsContext gc, long currentTime) {
		
//		Render health bar once hit
		if (this.hit) {
//			Width, height, and health percentage
	        double healthBarWidth = this.hitbox.width;
	        double healthBarHeight = 5;
	        double healthPercentage = (double) this.health / this.maxHealth;
	        double healthBar = healthBarWidth * healthPercentage;
	        
//	      Black for background
	      gc.setFill(Color.BLACK);
//	      Draw the background (height depending on the level of the monster)
	        if (this.reward == 30) {
	        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 13, healthBarWidth, healthBarHeight);
	        } else {
	        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 8, healthBarWidth, healthBarHeight);
	        }
//	      Draw the health bar (width depending on the level of the monster)
	        gc.setFill(Color.RED);
	        if (this.reward == 30) {
	        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 13, healthBar, healthBarHeight);
	        } else {
	        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 8, healthBar, healthBarHeight);
	        }
		}
		
//		If monster is not under attack within 3 seconds, hide the health bar
		long monsterLastHit = (currentTime - this.hitTime) / 1000000000;
		if (monsterLastHit >= 3) {
		    this.hit = false;
		}
		
       
    }
	 
//	Monster hitbox
	private void hitbox() {
		this.hitbox.x = (int) (this.x + this.width * this.xOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = (int) (this.width * this.hitBoxW);
		this.hitbox.height = (int) (this.height * this.hitBoxH);
	}

//  Method for playing sound
	// Reference: https://www.youtube.com/watch?v=wJO_cq5XeSA
	public void playSound(String soundFile) {
	    try {
	    	  // get a sound clip
	        Clip clip = AudioSystem.getClip();
	        
	        // Load file as a resource from the classpath
	        URL soundURL = getClass().getClassLoader().getResource(soundFile);
            
            // If there is no sound file, do this
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundFile);
                return;
            }
	        
	        // open audio input stream (an input stream with a specified audio format and length) from the sound file 
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
	       
	        // open clip and start playing the sound
	        clip.open(audioInputStream);
	        clip.start();
	      
	    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
	        e.printStackTrace();
	    }
	}
	
//	Map boundaries 
	public static int spawnY() {
		int randY = random.nextInt(105,555);
		
		return randY;
	}
	
	public static int spawnX(int y) {
		int randX = 0;
		
//		north
		if (y >= 105 && y <= 179) {
			randX = random.nextInt(350, 788);
//		north 2
		} else if (y >= 180 && y <= 249) {
			randX = random.nextInt(250, 888);
//		middle
		} else if (y >= 250 && y <= 260) {
			randX = random.nextInt(150, 988);
//		middle 2 (contains pillars)
		} else if (y >= 261 && y <= 379){
			randX = random.nextInt(200, 950);
//		south 1
		} else if (y >= 380 && y <= 469) {
			randX = random.nextInt(270, 870);
//		south 2
		} else if (y >= 470 && y <= 555) {
			randX = random.nextInt(380, 750);
		}
		return randX;
	}

	//	Method for spawning monsters
	public static void spawnMonsters(ArrayList<Monster> monsterArrayList, long currentTime){
// 		Spawn monsters after SPAWNDELAY time
		long monsterElapsedTime = (currentTime - GameTimer.getPreviousTimeMonster()) / 1000000000;
		if (monsterElapsedTime >= SPAWNDELAY_MONSTERS) {
//			Create monsters based on the number of NUM_MONSTER
			for (int i = monsterArrayList.size(); i < NUM_MONSTER; i++) {
				boolean locationChecker;
//				To prevent infinite loop
				int attempts = 0;
		        int maxAttempts = 50;
//				Ensure each monster have minimum space or gap between each other
				do {
	                // Random y coordinate
	                monsterY = spawnY();
	                // Random x coordinate
	                monsterX = spawnX(monsterY);
	                //Initialize locationCheckter to true
	                locationChecker = true;

	                // Check if the position is far enough from other monsters
	                for (Monster monster : monsterArrayList) {
//	                	Calculate the distance between the random xy coordinate and the current monsters from the arrayList
	                    double distance = calculateDistance(monsterX, monsterY, monster.getX(), monster.getY());
//	                  	If the distance is less than the minimum distance:
//	                    Compute for another x and y coordinate
	                    if (distance < MIN_DISTANCE_BETWEEN_MONSTERS) {
	                    	locationChecker = false;
	                        break;
	                    }
	                }
	                attempts ++;
	                
	            } while (!locationChecker && attempts < maxAttempts);
				
//				If location is valid, create a monster;
//				If after maxAttempts and still no location valid, dont create a monster
				if (locationChecker) {
//					Create monster based on randomization and given xy
					Monster monster = createMonster(monsterX, monsterY);
//					Add the created monster to the array
					monsterArrayList.add(monster);
				}
				
			}
			GameTimer.setPreviousTimeMonster(currentTime);
		}
	}

//	Method for calculating distance between two monsters
//	Reference: https://www.geeksforgeeks.org/program-calculate-distance-two-points/
	public static double calculateDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}

//	Creating monster based on monster type
	private static Monster createMonster(int x, int y){
//		Monster codes
		int[] monsterType = {11, 12, 13, 21, 22, 23, 31, 32, 33};
		Random random = new Random();
		// Random spawning of monsters
		int randomType = monsterType[random.nextInt(monsterType.length)];
//		Random direciotn
		int direction = random.nextInt(1, 3);
		
        switch (randomType) {
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
	public static void renderMonsters(ArrayList<Monster> monsterArrayList, GraphicsContext gc, long currentTime) {
		for (Monster monster : monsterArrayList) {	        
	        monster.render(gc, currentTime);
	    }
	}

	// Monster animation
	public static void animationMonster(ArrayList<Monster> monsterArrayList, long currentTime) {		
		for(Monster monster : monsterArrayList){
			monster.animation(currentTime);
		}
	}
	
	// Delete Monster
	public static void dieMonster(ArrayList<Monster> monsterArrayList) {		
		for(int i = 0; i < monsterArrayList.size(); i++) {
	        Monster monster = monsterArrayList.get(i);
	        if (monster.getHealth() <= 0) {
	            monsterArrayList.remove(i);
	            i--; // adjusting index
	        }
	    }
	}	
	
//	getters
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Rectangle getHitbox() {
		return this.hitbox;
	}
	
	public int getAttackTime() {
		return this.attackTime;
	}
	
	public long getLastAttackTime() {
		return this.attackLastTime;
	}
	public int getReward() {
		return this.reward;
	}
	public boolean isShowBoxes() {
		return this.showBoxes;
	}
	
//	Setters
	public void setShowBoxes(boolean b) {
		this.showBoxes = b;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setLastAttackTime(long time) {
		this.attackLastTime = time;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public void setHitTime(long time) {
		this.hitTime = time;
	}
	
// 	Unimplemented methods
	public abstract void hitAnimation();
	public abstract void animation(long currentTime);
}