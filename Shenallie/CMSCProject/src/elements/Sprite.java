package elements;

import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class Sprite {
	protected Image img;
	protected int x, y, dx, dy;
	protected int width;
	protected int height;
	protected int health;
	protected int attackPoints;
	private boolean visible;
	private boolean alive;
	protected boolean attack;
	protected boolean hit;
	private double xOffset;
	private double yOffset;
	private double attackWOffset;
	private double attackHOffset;
	public Rectangle hitbox;
	public Rectangle attackbox;
	private boolean collisionChecker;
	private int animationCountDie;
	private long previousTimeDie;
	private int direction;

	
	public Sprite(int xPos, int yPos, double xOffset, double yOffset, double attackWOffset, double attackHOffset){
		this.x = xPos;
		this.y = yPos;
		this.health = 15;
		this.attackPoints = 5;
		this.visible = true;
		this.alive = true;
		this.attack = false;
		this.hit = false;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.attackWOffset = attackWOffset;
		this.attackHOffset = attackHOffset;
		this.hitbox = new Rectangle (this.x, this.y, 0 , 0);
		this.attackbox = new Rectangle (this.x, this.y, 0,0);
		this.previousTimeDie = System.nanoTime();
		this.animationCountDie = 1;
		this.collisionChecker = false;
		this.setDirection(1);
	}
	
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}
	
	//method to set the image to the image view node
	void render(GraphicsContext gc){
		if (this.visible) {
			flipHitbox(gc);
		} 
    }

	private void hitboxRUpdate() {
		// TODO Auto-generated method stub
		this.hitbox.x = (int) (this.x + this.width * this.xOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = this.width/2;	
		this.hitbox.height = (int) (this.height - this.height * this.yOffset);
	}
	
	private void attackboxRUpdate() {
		// TODO Auto-generated method stub
		this.attackbox.x = (int) (this.x + this.width * this.attackWOffset);
		this.attackbox.y = this.y;	
		this.attackbox.width = this.width/2;	
		this.attackbox.height = this.height;
	}
	
	private void hitboxLUpdate() {
		// TODO Auto-generated method stub
		this.hitbox.x = (int) (this.x + this.width * this.attackWOffset);
		this.hitbox.y = this.y;
		this.hitbox.width = this.width/2;	
		this.hitbox.height = (int) (this.height - this.height * this.yOffset);
	}
	
	private void attackboxLUpdate() {
		// TODO Auto-generated method stub
		this.attackbox.x = (int) (this.x + this.width * this.xOffset);
		this.attackbox.y = (int) (this.y + this.height * this.yOffset);
		this.attackbox.width = this.width/2;	
		this.attackbox.height = this.height;
	}
	
	private void flipHitbox(GraphicsContext gc) {
		if (direction == 1) {
			hitboxRUpdate();
			attackboxRUpdate();
		} else {
			hitboxLUpdate();
			attackboxLUpdate();
		}
		gc.drawImage(this.img, this.x, this.y);   
	}
	
	
	public void checkCollision(Sprite player1, Sprite player2, long currentTime, int direction) {
		if (player1.attackbox.intersects(player2.hitbox)) {
			player2.setHit(true);
			System.out.println("Hit");
			player2.hitAnimation(currentTime, player1, direction);
	        // Attack hits the defending player
	    	player1.setCollisionChecker(true);
	    } else {
	    	System.out.println("Miss");
	    }
	}
	
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
		if (getHit() == true) {
			if (direction == 1) {
				this.img = Formatting.KnightRHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			} else {
				this.img = Formatting.KnightLHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			}
		} else {
			this.img = Formatting.KnightRIdle1;
		}
	}

	protected boolean checkAlive() {
		if (this.health <= 0) {
			this.alive = false;
			return false;
		}
		return true;
	}

	//method to set the object's width and height properties
	private void setSize(){
		this.width = (int) this.img.getWidth();
	    this.height = (int) this.img.getHeight();
	}
	
	public void move() {
		if (mapCollision()) {
			this.x += this.dx;
			this.y += this.dy;
		}
	}
	
	public boolean mapCollision() {
		if (this.y + this.dy + this.height >= 91 && this.y + this.dy + this.height <= 167) {
			if (this.x + this.dx >= 343 && this.x + this.dx + this.width <= 870) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else if (this.y + this.dy + this.height >= 168 && this.y + this.dy + this.height <= 240) {
			if (this.x + this.dx >= 230 && this.x + this.dx + this.width <= 967) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else if (this.y + this.dy + this.height >= 241 && this.y + this.dy + this.height <= 397) {
			if (this.x + this.dx >= 134 && this.x + this.dx + this.width <= 1069) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else if (this.y + this.dy + this.height >= 398 && this.y + this.dy + this.height <= 486) {
			if (this.x + this.dx >= 180 && this.x + this.dx + this.width  <= 1023) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else if (this.y + this.dy + this.height >= 487 && this.y + this.dy + this.height <= 560) {
			if (this.x + this.dx >= 286 && this.x + this.dx + this.width  <= 922) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else if (this.y + this.dy + this.height >= 561 && this.y + this.dy + this.height <= 619) {
			if (this.x + this.dx >= 384 && this.x + this.dx + this.width  <= 816) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
		} else {
			System.out.println("Collision");
			return false;
		}
		
	}
	//getters
	public int getX() {
    	return this.x;
	}

	public int getY() {
    	return this.y;
	}
	
	public int getDX(){
		return this.dx;
	}
	public int getDY(){
		return this.dy;
	}
	
	public int getWidth() {
		return (int) this.img.getWidth();
	}
	
	public int getHeight() {
		return (int) this.img.getHeight();
	}
	
	public boolean getAttack(){
		return this.attack;
	}
	
	public boolean getHit(){
		return this.hit;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getAttackPoints() {
		return this.attackPoints;
	}
	
	public boolean getVisible() {
		return this.visible;
	}
	
	public boolean getCollisionChecker() {
		return collisionChecker;
	}
	
	public void setHealth(int damage) {
		this.health -= damage;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setCollisionChecker(boolean collisionChecker) {
		this.collisionChecker = collisionChecker;
	}

	public void setAttack(boolean bool) {
		this.attack = bool;
	}

	private void setHit(boolean b) {
		this.hit = true;
		
	}
	public void setDX(int dx){
		this.dx = dx;
	}
	
	public void setDY(int dy){
		this.dy = dy;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
