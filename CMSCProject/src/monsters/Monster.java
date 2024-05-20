package monsters;

import java.awt.Rectangle;
import java.util.Random;

import characters.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Monster{
	
	protected Image img;
	private static Random random = new Random();
	protected int type;
	private long duration;
	private int x, y, width, height, hitBoxW, hitBoxH, health;
	private boolean visible, alive;
	private Rectangle hitbox;

	
	public Monster(int xPos, int yPos, int type, int health,
			double xOffset, double yOffset,
			int hitBoxW, int hitBoxH) {
		this.type = type;
		this.x = (int) (xPos + this.width * xOffset);				
		this.y = (int) (yPos + this.height + yOffset);
		this.hitBoxW = hitBoxW;
		this.hitBoxH = hitBoxH;
		this.health = health;
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
			gc.drawImage(this.img, this.x, this.y, this.width, this.height);
		}
    }

//	Map boundaries (+20, -20)
	public static int spawnY() {
		int randY = random.nextInt(105,611);
		
		return randY;
	}
	
	public static int spawnX(int y) {
		int randX = 0;
		
//		north
		if (y >= 105 && y <= 179) {
			randX = random.nextInt(350, 851);
//		north 2
		} else if (y >= 180 && y <= 249) {
			randX = random.nextInt(250, 951);
//		middle
		} else if (y >= 250 && y <= 289) {
			randX = random.nextInt(150, 1051);
//		middle 2 (contains pillars)
		} else if (y >= 290 && y <= 379){
			int rand = random.nextInt(1, 4);
			
			if(rand == 1) {
				randX = random.nextInt(150, 291);
			} else if (rand == 2) {
				randX = random.nextInt(390, 831);
			} else {
				randX = random.nextInt(930, 1051);
			}
//		south 1
		} else if (y >= 380 && y <= 469) {
			randX = random.nextInt(270, 1006);
//		south 2
		} else if (y >= 470 && y <= 544) {
			randX = random.nextInt(300, 906);
//		south 3
		} else {
			randX = random.nextInt(400, 796);
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
	

}