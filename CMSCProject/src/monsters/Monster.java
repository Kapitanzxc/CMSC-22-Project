package monsters;

import java.awt.Rectangle;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Monster{
//	Attributes
	protected Image img;
	private static Random random = new Random();
	protected int type;
	private long attackLastTime;
	private int x, y, width, height, health, reward, attackTime, maxHealth;
	private double hitBoxW, hitBoxH, xOffset, yOffset;
	private boolean visible, showBoxes;
	private Rectangle hitbox;
	
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
	public void render(GraphicsContext gc){
		if (this.visible) {
//			If visible, update hitbox
			hitbox();
//			Draw the monster
			gc.drawImage(this.img, this.x, this.y, this.width, this.height);
//			Show health bar
			renderHealthBar(gc);
		}
		
//		Show hitboxes when toggled
		if (this.showBoxes) {
			gc.setStroke(Color.GREEN); 
		    gc.strokeRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}
    }
	
//	Method for rendering health bar
	private void renderHealthBar(GraphicsContext gc) {
//		Width, height, and health percentage
        double healthBarWidth = this.hitbox.width;
        double healthBarHeight = 5;
        double healthPercentage = (double) this.health / this.maxHealth;
        double healthBar = healthBarWidth * healthPercentage;
        
//      Black for background
        gc.setFill(Color.BLACK);
//      Draw the background (height depending on the level of the monster)
        if (this.reward == 30) {
        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 13, healthBar, healthBarHeight);
        } else {
        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 8, healthBar, healthBarHeight);
        }
//      Draw the health bar (height depending on the level of the monster)
        gc.setFill(Color.RED);
        if (this.reward == 30) {
        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 13, healthBar, healthBarHeight);
        } else {
        	gc.fillRect(this.hitbox.x, this.hitbox.y - healthBarHeight - 8, healthBar, healthBarHeight);
        }
       
    }
	 
//	Monster hitbox
	private void hitbox() {
		this.hitbox.x = (int) (this.x + this.width * this.xOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = (int) (this.width * this.hitBoxW);
		this.hitbox.height = (int) (this.height * this.hitBoxH);
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

//	getters
	public int getType() {
		return this.type;
	}
	
	public int getHealth() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return this.reward;
	}
	public boolean isShowBoxes() {
		// TODO Auto-generated method stub
		return this.showBoxes;
	}
	
//	Setters
	public void setShowBoxes(boolean b) {
		// TODO Auto-generated method stub
		this.showBoxes = b;
	}

	public void setHealth(int health) {
		// TODO Auto-generated method stub
		this.health = health;
	}

	public void setLastAttackTime(long time) {
		this.attackLastTime = time;
	}
	
// 	Unimplemented methods
	public abstract void hitAnimation();
	public abstract void animation(long currentTime);


	

	


	

}