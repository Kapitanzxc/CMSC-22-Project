package characters;

import java.awt.Rectangle;
import java.util.ArrayList;

import elements.Formatting;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import monsters.Monster;

public abstract class Sprite {
//	Attributes
	protected Image img;
	private int characterID, playerNumber;	
	protected int x, y, dx, dy, width, height, health, attackPoints, direction,maxHealth, fragmentsCollected, monstersKilled, specialCollected, currentWidth, currentHeight;
	private double xOffset, yOffset, attackWOffset, attackHOffset, hitboxW, hitboxH, attackW, attackH, attackLWOffset;
	private boolean visible, alive, collisionChecker, showBoxes, showCBoxes;
	protected boolean attack,hit;
	public Rectangle hitbox, attackbox;
	
//	For powerups
	private int speed;
	private int addDamage;
	private long speedBoostTime;
	private long attackBoostTime;
	private boolean speedBoostActive;
	private boolean attackBoostActive;
	
	private static final int HEALTHWIDTH = 147;
    private static final int HEALTHHEIGHT = 6;
    
//	Constructor
	public Sprite(int characterID, int playerNumber, int xPos, int yPos, int direction, 
			int health, int attackPoints, int speed,
//			hitbox
			double xOffset, double yOffset,
			double hitBoxW, double hitBoxH,
//			Attackbox
			double attackWOffset, double attackHOffset,
			double attackLWOffset,
			double attackW, double attackH){
		
		this.characterID = characterID; // Player code
		this.playerNumber = playerNumber; // If player 1 or 2
		this.maxHealth = health;   // initial max health
		this.health = health;   // health of a character
		this.attackPoints = attackPoints;  // initial attack points
		this.xOffset = xOffset;  // How far is the body from the left
		this.yOffset = yOffset;  // How far is the body from the above
		this.x =  xPos;	// X coordinate
		this.y =  yPos;	// Y coordinate	
		this.hitboxW = hitBoxW; // hitbox width
		this.hitboxH = hitBoxH; // hitbox height
		this.attackWOffset = attackWOffset; // How far is the attack range from the left (character in right direction)
		this.attackHOffset = attackHOffset; // How far is the attack range from the above
		this.attackLWOffset = attackLWOffset; // How far is the attack range from the left (character in left direction)
		this.attackW = attackW; // attackbox width
		this.attackH = attackH; // attackbox right
		this.direction = direction; // direction of the player
//		Character stats
		this.fragmentsCollected = 0;
		this.specialCollected = 0;
		this.monstersKilled = 0;
//		Boolean variables which checks if the character is still visible, alive, in attacking motion, getting hit, or collides with other entities
		this.visible = true;
		this.alive = true;
		this.attack = false;
		this.hit = false;
		this.collisionChecker = false;
//		Variable for showing the hitboxes and attackboxes of characters
		this.setShowBoxes(false);
		this.hitbox = new Rectangle (this.x, this.y, 0 , 0);
		this.attackbox = new Rectangle (this.x, this.y, 0,0);
//		Powerups attributes
		this.speed = speed;
		this.speedBoostTime = 0;
		this.attackBoostTime = 0;
		this.speedBoostActive = false;
		this.attackBoostActive = false;
		this.addDamage = 0;
	}
	
//	Abstract methods
	public abstract boolean dieAnimation(long nanoTime);
	public abstract void animation(long nanoTime, Sprite player2, ArrayList<Monster> monsterArrayList);
	public abstract void hitAnimation(long currentTime, Sprite player1, int direction);
	public abstract void hitAnimationMonster(long currentTime, Monster monster, int direction);
	
	
	//method to set the object's image
	protected void loadImage(Image img, int width, int height){
		try{
			this.img = img;
		  	this.width = width;
	        this.height = height;
		} catch(Exception e){}
	}
	
	//method to render the character and update hitboxes depending on characters direction
	public void render(GraphicsContext gc){
		if (this.visible) {
//			If character is still visible, render its image and update its hitbox
			renderCharacter(gc);
			updateHitbox(gc);
		} 
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
	
//	Draws the character in the scene
	private void renderCharacter(GraphicsContext gc) {
//		Grow mechanism (enlarge the image by 0.2% of its attackpoitns)
        this.currentWidth = (int) (this.width * (1 + 0.002* this.attackPoints));
        this.currentHeight = (int) (this.height * (1 + 0.002 * this.attackPoints));
//      Draw the character
        gc.drawImage(this.img, this.x, this.y,   this.currentWidth,  this.currentHeight );
	 }
	
//	Draws the health bars of each character
	public void renderHealthBar(GraphicsContext gc) {
//		Computing for health percentage
	    double healthPercentage = (double) this.health / this.maxHealth;
//	    For player 1:
	    if (this.playerNumber == 1) {
	    	
	        // Health Bar
//	    	Dark blue background
	        gc.setFill(Formatting.DARKBLUE);
	        gc.fillRect(88, 30, HEALTHWIDTH, HEALTHHEIGHT);
//	        Green colored health bar
	        gc.setFill(Color.GREEN);
//	        Width depending on the health percentage
	        gc.fillRect(88, 30, HEALTHWIDTH * healthPercentage, HEALTHHEIGHT);
	        
//	        Show health bar depending on the character
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
//	        Set font to red if attackboost is active, else, white
	        if (this.attackBoostActive) {
	        	gc.setFill(Color.RED);
	        } else {
	        	gc.setFill(Color.WHITE);
	        }
	        gc.setFont(Formatting.FONT_STYLE_22);
	        // Render text (attack points)
	        gc.fillText(""+this.attackPoints, 120, 65);
	        

//	        If speedboost is active, draw this icon
	        if (this.speedBoostActive) {
	        	gc.drawImage(Formatting.SBOOST, 165, 43, 30, 30);
	        }
	       
	        
	    } else {
	    	
	        // Health Bar
//	    	Green health bar
	        gc.setFill(Color.GREEN);
	        gc.fillRect(966, 30, HEALTHWIDTH, HEALTHHEIGHT);
//	    	Dark blue background
//	        Dark blue background covering the green health bar
//	        Dark blue will gradually cover the green health bar depending on the health percentage
	        gc.setFill(Formatting.DARKBLUE);
	        gc.fillRect(966, 30, HEALTHWIDTH * (healthPercentage - 1)*-1, HEALTHHEIGHT);
	        
//	        Show health bar depending on the character
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
//	        Set font to red if attackboost is active, else, white
	        if (this.attackBoostActive) {
	        	gc.setFill(Color.RED);
	        } else {
	        	gc.setFill(Color.WHITE);
	        }
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
	        
//	        If speedboost is active, draw this icon
	        if (this.speedBoostActive) {
	        	gc.drawImage(Formatting.SBOOST, 1005, 43, 30, 30);
	        }
	       
	    }
	}

	
//	Update hitbox depending on the direction
	private void updateHitbox(GraphicsContext gc) {
		hitboxRUpdate();
		if (this.direction == 1) {
			attackboxRUpdate();
		} else {
			attackboxLUpdate();
		}
		
	}
	
//	Swapping attack boxes when the character faced opposite direction
	
	private void hitboxRUpdate() {
		this.hitbox.x = (int) (this.x + this.currentWidth * this.xOffset);
		this.hitbox.y = (int) (this.y + this.currentHeight * this.yOffset);
		this.hitbox.width = (int) (this.currentWidth * this.hitboxW);
		this.hitbox.height = (int) (this.currentHeight * this.hitboxH);
	}
//	When facing right direction
	private void attackboxRUpdate() {
		this.attackbox.x = (int) (this.x + this.currentWidth * this.attackWOffset);
		this.attackbox.y = (int) (this.y + this.currentHeight * this.attackHOffset);	
		this.attackbox.width = (int) (this.currentWidth * this.attackW);	
		this.attackbox.height = (int) (this.currentHeight * this.attackH);
		
	}
//	When facing left direction
	private void attackboxLUpdate() {
		this.attackbox.x = (int) (this.x + this.currentWidth * this.attackLWOffset);
		this.attackbox.y = (int) (this.y + this.currentHeight * this.attackHOffset);	
		this.attackbox.width = (int) (this.currentWidth * this.attackW);	
		this.attackbox.height = (int) (this.currentHeight * this.attackH);
		
	}

//	Method for checking collision
	public void checkCollision(Sprite player1, Sprite player2, long currentTime, int direction, ArrayList<Monster> monsterArrayList) {
	    // Check collision between player1 and player2
	    if (player1.attackbox.intersects(player2.hitbox)) {
//	    	Player 2 is hit
	        player2.setHit(true);
	        System.out.println("Hit");
//	        Plays hit animation
	        player2.hitAnimation(currentTime, player1, direction);
	        // Attack hits the defending player
	        player1.setCollisionChecker(true);
	    } else {
	        System.out.println("Miss");
	    }
	    
	    // Check collision between player1 and each monster in the monsterArrayList
	    for (Monster monster : monsterArrayList) {
	        if (player1.attackbox.intersects(monster.getHitbox())) {
	        	monster.setHit(true);
	        	monster.setHitTime(currentTime);
//	        	Monster hit animation
	        	monster.hitAnimation();
	        	player1.setCollisionChecker(true);
//	        	Decreases heath
	            monster.setHealth(monster.getHealth() - player1.getAttackPoints()); 
	            System.out.println("Monster Health Remaining: " + monster.getHealth());
//	            If player slayed the monster, update its stats
	            if (monster.getHealth() <= 0) {
	            	player1.monstersKilled += 1;
	            	player1.attackPoints += monster.getReward();
	            	player1.addHealth(monster.getReward());
	            	player1.maxHealth += monster.getReward();
	            }
	            System.out.println("Monster Hit");
	        }
	    }
	}	
	
//	Method for checking collision from the monster
	public void checkMonsterCollision(ArrayList<Monster> monsterArrayList, long currentTime) {
	    for (Monster monster: monsterArrayList) {
	    	if (monster.getHitbox().intersects(this.hitbox)) {
	    		 // Elapsed time of last attack
		        double attackElapsedTime = (currentTime - monster.getLastAttackTime()) / 1000000000.0;
		        // Attack player after 2 seconds
		        if (attackElapsedTime > monster.getAttackTime()) {
		        	this.setHit(true);
		 	        System.out.println("Hit by monster");
//		 	        Play hit animation of the player when hit by the monster
		 	        this.hitAnimationMonster(currentTime, monster, direction);
		           	monster.setLastAttackTime(currentTime);
		        }
	    	}	
	    }
	}	


	// Checks if character still alive
	public boolean checkAlive() {
		if (this.health <= 0) {
			this.alive = false;
			return false;
		}
		return true;
	}

//	Method which constantly checks player movement
	public void move(ArrayList<Rectangle> mapBoundaries) {
	    if (this.alive) {
	    	
	        // Calculate potential new position
	        int newX = this.hitbox.x + this.dx;
	        int newY = this.hitbox.y + this.dy;

	        // Create a new hitbox for the next possible position
	        Rectangle newHitbox = new Rectangle(newX, newY, this.hitbox.width, this.hitbox.height);

	        // Check if the new position will cause a collision
	        if (!mapCollision(newHitbox, mapBoundaries)) {
	            System.out.println("Collision");
	        } else {
	            // No collision, update coordinates
	            this.x += this.dx;
	            this.y += this.dy;
	        }
	    }
	}

//	Returns true when the hitbox doesnt intersects with map boundaries
	public boolean mapCollision(Rectangle newHitbox, ArrayList<Rectangle> mapBoundaries) {
	    for (Rectangle boundary : mapBoundaries) {
	        if (newHitbox.intersects(boundary)) {
	            return false;
	        }
	    }
	    return true;
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
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public int getFragmentsCollected() {
		return this.fragmentsCollected;
	}
	
	public int getSpecialCollected() {
		return this.specialCollected;
	}
	
	public int getMonstersKilled() {
		return this.monstersKilled;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public boolean isAttackBoostActive () {
		return this.attackBoostActive;
	}
	
	public boolean isSpeedBoostActive () {
		return this.speedBoostActive;
	}
	
	public long getAttackBoostTime() {
		return this.attackBoostTime;
	}
	
	public long getSpeedBoostTime() {
		return this.speedBoostTime;
	}
	
	public int getAddDamage() {
		return this.addDamage;
	}
	
	public void addMaxHealth(int add) {
		this.maxHealth += add;
		
	}
	public void addHealth(int add) {
		this.health += add;
	}
	
	public void addFragments(int add) {
		this.fragmentsCollected += add;
	}
	
	public void addSpecial(int add) {
		this.specialCollected += add;
	}
	
//	Setters
	public void setAlive(boolean state) {
		this.alive = state;
	}
	
	public void setAttackPoints (int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setHealthAdd(int additional) {
//		Max health = 100
		if(this.health + additional > 100) {
			this.health = 100;
		} else {
			this.health += additional;
		}
	}
	
	public void addFragments() {
		this.fragmentsCollected += 1;
	}
	
	public void addSpecial() {
		this.specialCollected += 1;
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
	
	public boolean isShowCBoxes() {
		return showCBoxes;
	}

	public void setShowCBoxes(boolean showCBoxes) {
		this.showCBoxes = showCBoxes;
	}
	
	public void addAttackPoints () {
		this.attackPoints += 1;
	}
	
	public void setAttackBoostActive(boolean active) {
		this.attackBoostActive = active;
	}
	
	public void setSpeedBoostActive(boolean active) {
		this.speedBoostActive = active;
	}
	
	public void setAttackBoostTime(long collectTime) {
		this.attackBoostTime = collectTime;
	}
	
	public void setSpeedBoostTime(long collectTime) {
		this.speedBoostTime = collectTime;
	}
	
	public void setAddDamage(int addDamage) {
		this.addDamage = addDamage;
	}
	
	public void addMaxHealth() {
		this.maxHealth += 1;
		
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	


	
}
