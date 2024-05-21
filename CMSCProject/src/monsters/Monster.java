package monsters;

import java.awt.Rectangle;
import java.util.Random;

import characters.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Monster{
	
	protected Image img;
	private static Random random = new Random();
	protected int type;
	private long duration;
	private int x, y, width, height, health;
	private double hitBoxW, hitBoxH;
	private boolean visible, alive, showBoxes;
	private Rectangle hitbox;
	private double xOffset, yOffset;

	
	public Monster(int xPos, int yPos, int type, int health,
			double xOffset, double yOffset,
			double hitBoxW, double hitBoxH) {
		this.type = type;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.x = (int) (xPos + this.width * xOffset);				
		this.y = (int) (yPos + this.height + yOffset);
		this.hitBoxW = hitBoxW;
		this.hitBoxH = hitBoxH;
		this.health = health;
		this.showBoxes = false;
		this.visible = true;
		this.alive = true;
		this.hitbox = new Rectangle (this.x, this.y, 0 , 0);
	}
	
	//method to set the object's image
	protected void loadImage(Image img, int width, int height){
		try{
			this.img = img;
		  	this.width = width;
	        this.height = height;
		} catch(Exception e){}
	}
	
	public void render(GraphicsContext gc){
		if (this.visible) {
			hitbox();
			gc.drawImage(this.img, this.x, this.y, this.width, this.height);
		}
		
		if (this.showBoxes) {
			gc.setStroke(Color.GREEN); 
		    gc.strokeRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}
    }
	
//	
	private void hitbox() {
		// TODO Auto-generated method stub
		this.hitbox.x = (int) (this.x + this.width * this.xOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = (int) (this.width * this.hitBoxW);
		this.hitbox.height = (int) (this.height * this.hitBoxH);
	}

//	Map boundaries (+20, -20)
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
			int rand = random.nextInt(1, 4);
			
			if(rand == 1) {
				randX = random.nextInt(200, 228);
			} else if (rand == 2) {
				randX = random.nextInt(390, 768);
			} else {
				randX = random.nextInt(930, 950);
			}
//		south 1
		} else if (y >= 380 && y <= 469) {
			randX = random.nextInt(270, 870);
//		south 2
		} else if (y >= 470 && y <= 555) {
			randX = random.nextInt(380, 750);
		}
		return randX;
	}
	
//	public void attackMonster(Sprite player) {
// 	
//    	if (player.attackbox.intersects(this.hitbox)) {
//    		this.health -= player.attackPoints;
//    	}
//	}
	
	public abstract void animation(long currentTime);
	
//	getters
	public int getType() {
		return this.type;
	}
	
	public long getDuration() {
		return this.duration;
	}

	public boolean isShowBoxes() {
		// TODO Auto-generated method stub
		return this.showBoxes;
	}

	public void setShowBoxes(boolean b) {
		// TODO Auto-generated method stub
		this.showBoxes = b;
	}
	

}