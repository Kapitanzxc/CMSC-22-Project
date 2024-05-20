package elements;

import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Sprite {
//	Attributes
	protected Image img;
	private int characterID, playerNumber;	
	protected int x, y, dx, dy, width, height, health, attackPoints, direction,maxHealth;
	private double xOffset, yOffset, attackWOffset, attackHOffset, characterLWidth, characterRWidth, widthLOffset, widthROffset, hitboxW, hitboxH, attackW, attackH, xLOffset, attackLWOffset;
	private boolean visible, alive, collisionChecker, showBoxes;
	protected boolean attack,hit;
	public Rectangle hitbox, attackbox;

	private static final int HEALTHWIDTH = 147;
    private static final int HEALTHHEIGHT = 6;
    
//	Constructor
	public Sprite(int characterID, int playerNumber, int xPos, int yPos, int health, 
			double xOffset, double yOffset,
			double xLOffset,
			double hitBoxW, double hitBoxH,
			double attackWOffset, double attackHOffset,
			double attackLWOffset,
			double attackW, double attackH,
			double widthLOffset, double widthROffset){
		
		this.characterID = characterID;
		this.playerNumber = playerNumber;
		this.x = (int) (xPos + this.width * xOffset);
		this.y = (int) (yPos + this.height + yOffset);
		this.hitboxW = hitBoxW;
		this.hitboxH = hitBoxH;
		this.xLOffset = xLOffset;
		this.widthLOffset = widthLOffset;
		this.widthROffset = widthROffset;
		this.maxHealth = 100;
		this.health = health;
		this.attackPoints = 1;
		this.visible = true;
		this.alive = true;
		this.attack = false;
		this.hit = false;
		this.setShowBoxes(false);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.attackWOffset = attackWOffset;
		this.attackHOffset = attackHOffset;
		this.attackLWOffset = attackLWOffset;
		this.attackW = attackW;
		this.attackH = attackH;
		this.hitbox = new Rectangle (this.x, this.y, 0 , 0);
		this.attackbox = new Rectangle (this.x, this.y, 0,0);
		System.nanoTime();
		this.collisionChecker = false;
		this.direction = 1;
	}
	
	protected abstract boolean dieAnimation(long nanoTime);
	protected abstract void animation(long nanoTime, Sprite player2);
	protected abstract void hitAnimation(long currentTime, Sprite player1, int direction2);
	
	
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}
	
	//method to render the character and update hitbox
	public void render(GraphicsContext gc){
		if (this.visible) {
			flipHitbox(gc);
			this.characterLWidth =  this.width * this.widthLOffset;
			this.characterRWidth =  this.width * this.widthROffset;
		} 
		
		gc.drawImage(this.img, this.x, this.y);   
		renderHealthBar(gc);
		 // Draw hitbox and attackbox
		if (this.isShowBoxes()) {
			gc.setStroke(Color.GREEN); 
		    gc.strokeRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		    // Draw attackbox
		    gc.setStroke(Color.RED); 
		    gc.strokeRect(this.attackbox.x, this.attackbox.y, this.attackbox.width, this.attackbox.height);
		}
    }
	
	
	public void renderHealthBar(GraphicsContext gc) {
	    double healthPercentage = (double) this.health / this.maxHealth;

	    if (this.playerNumber == 1) {
	        // Health Bar
	        gc.setFill(Formatting.DARKBLUE);
	        gc.fillRect(88, 30, HEALTHWIDTH, HEALTHHEIGHT);
	        gc.setFill(Color.GREEN);
	        gc.fillRect(88, 30, HEALTHWIDTH * healthPercentage, HEALTHHEIGHT);
	        switch (this.characterID) {
	            case Formatting.KNIGHT:
	                gc.drawImage(Formatting.KnightLHealthBar, 10, 10);  
	                break;
	            case Formatting.ORC:
	                gc.drawImage(Formatting.OrcLHealthBar, 10, 10);  
	                break;
	            case Formatting.SWORDWOMAN:
	                gc.drawImage(Formatting.SWLHealthBar, 10, 10);  
	                break;
	            case Formatting.WIZARD:
	                gc.drawImage(Formatting.WizLHealthBar, 10, 10);  
	                break;
	        }
	        // Set font and color
	        gc.setFill(Color.WHITE);
	        gc.setFont(Formatting.FONT_STYLE_22);
	        // Render text
	        gc.fillText(""+this.attackPoints, 120, 65);
	    } else {
	        // Health Bar
	        gc.setFill(Color.GREEN);
	        gc.fillRect(966, 30, HEALTHWIDTH, HEALTHHEIGHT);
	        gc.setFill(Formatting.DARKBLUE);
	        gc.fillRect(966, 30, HEALTHWIDTH * (healthPercentage - 1)*-1, HEALTHHEIGHT);
	        switch (this.characterID) {
	            case Formatting.KNIGHT:
	                gc.drawImage(Formatting.KnightRHealthBar, 959, 10);   
	                break;
	            case Formatting.ORC:
	                gc.drawImage(Formatting.OrcRHealthBar, 959, 10);
	                break;
	            case Formatting.SWORDWOMAN:
	                gc.drawImage(Formatting.SWRHealthBar, 959, 10);  
	                break;
	            case Formatting.WIZARD:
	                gc.drawImage(Formatting.WizRHealthBar, 959, 10);  
	                break;
	        }
	        // Set font and color
	        gc.setFill(Color.WHITE);
	        gc.setFont(Formatting.FONT_STYLE_22);
	        // Render text depending on the number of digits
	        if (this.attackPoints >= 1000) {
	            gc.fillText(""+this.attackPoints, 1041, 65);
	        } else if (this.attackPoints >= 100){
	            gc.fillText(""+this.attackPoints, 1050, 65);
	        } else if (this.attackPoints >= 10){
	            gc.fillText(""+this.attackPoints, 1063, 65);
	        } else {
	            gc.fillText(""+this.attackPoints, 1073, 65);
	        }
	    }
	}

	
//	Update hitbox
	private void flipHitbox(GraphicsContext gc) {
		if (this.direction == 1) {
			hitboxRUpdate();
			attackboxRUpdate();
		} else {
			hitboxLUpdate();
			attackboxLUpdate();
		}
		
	}
	
//	Swapping hitboxes when the character faced opposite direction
	private void hitboxRUpdate() {
		// TODO Auto-generated method stub
		this.hitbox.x = (int) (this.x + this.width * this.xOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = (int) (this.width * this.hitboxW);
		this.hitbox.height = (int) (this.height * this.hitboxH);
	}
	
	private void attackboxRUpdate() {
		// TODO Auto-generated method stub
		this.attackbox.x = (int) (this.x + this.width * this.attackWOffset);
		this.attackbox.y = (int) (this.y + this.height * this.attackHOffset);	
		this.attackbox.width = (int) (this.width * this.attackW);	
		this.attackbox.height = (int) (this.height * this.attackH);
		
	}
	
	private void hitboxLUpdate() {
		// TODO Auto-generated method stub
		this.hitbox.x = (int) (this.x + this.width * this.xLOffset);
		this.hitbox.y = (int) (this.y + this.height * this.yOffset);
		this.hitbox.width = (int) (this.width * this.hitboxW);
		this.hitbox.height = (int) (this.height * this.hitboxH);
	}
	
	private void attackboxLUpdate() {
		// TODO Auto-generated method stub
		this.attackbox.x = (int) (this.x + this.width * this.attackLWOffset);
		this.attackbox.y = (int) (this.y + this.height * this.attackHOffset);	
		this.attackbox.width = (int) (this.width * this.attackW);	
		this.attackbox.height = (int) (this.height * this.attackH);
		
	}

//	Method for checking collision
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
	


	// Checks if character still alive
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
	
//	Method for updating characters coordinates
	public void move() {
		if (mapCollision() && this.alive) {
			this.x += this.dx;
			this.y += this.dy;
		}
	}
	
//	Map boundaries
	public boolean mapCollision() {
//		north
		if (this.y + this.dy + this.height >= 91 && this.y + this.dy + this.height <= 167) {
			if (this.x + this.dx + this.characterLWidth >= 343 && this.x + this.dx + this.characterRWidth <= 870) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
//		north 2
		} else if (this.y + this.dy + this.height >= 168 && this.y + this.dy + this.height <= 240) {
			if (this.x + this.dx + this.characterLWidth >= 230 && this.x + this.dx + this.characterRWidth <= 967) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
//		middle
		} else if (this.y + this.dy + this.height >= 241 && this.y + this.dy + this.height <= 397) {
			if (this.x + this.dx + this.characterLWidth >= 134 && this.x + this.dx + this.characterRWidth <= 1069) {
//				Structures
				if (this.y + this.dy + this.height >= 314 && this.y + this.dy  <= 373) {
					if (this.x + this.dx + this.characterRWidth >= 303 && this.x + this.dx + this.characterLWidth <= 381) {
						System.out.println("Collision1");
						return false;
					} 
					else if (this.x + this.dx + this.characterRWidth >= 839 && this.x + this.dx + this.characterLWidth <= 910){
						System.out.println("Collision2");
						return false;
					}
				}
				return true;

			} else {
				System.out.println("Collision3");
				return false;
			}
//		south 1
		} else if (this.y + this.dy + this.height >= 398 && this.y + this.dy + this.height <= 486) {
			if (this.x + this.dx + this.characterLWidth >= 180 && this.x + this.dx + this.characterRWidth  <= 1023) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
//		south 2
		} else if (this.y + this.dy + this.height >= 487 && this.y + this.dy + this.height <= 560) {
			if (this.x + this.dx + this.characterLWidth >= 286 && this.x + this.dx + this.characterRWidth  <= 922) {
				return true;
			} else {
				System.out.println("Collision");
				return false;
			}
//		south 3
		} else if (this.y + this.dy + this.height >= 561 && this.y + this.dy + this.height <= 619) {
			if (this.x + this.dx + this.characterLWidth >= 384 && this.x + this.dx + this.characterRWidth  <= 816) {
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
	
	public boolean getAlive() {
		return this.alive;
	}
	
	public int getPlayerNumber() {
		return this.playerNumber;
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
	
	public int getDirection() {
		return direction;
	}
	
	public void addMaxHealth(int add) {
		this.maxHealth += add;
		
	}

	public void addHealth(int add) {
		this.health += add;
	}

	
//	Setters
	public void setAlive(boolean state) {
		this.alive = state;
	}
	
	public void setAttackPoints (int additional) {
		this.attackPoints += 1;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setHealth(int damage) {
		this.health -= damage;
	}
	
	public void setHealthAdd(int additional) {
//		Max health = 100
		if(this.health + additional > 100) {
			this.health = 100;
		} else {
			this.health += additional;
		}
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

	public boolean isShowBoxes() {
		return showBoxes;
	}

	public void setShowBoxes(boolean showBoxes) {
		this.showBoxes = showBoxes;
	}


	
}
