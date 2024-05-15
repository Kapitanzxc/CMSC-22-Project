package elements;

import java.util.Random;

public class PowerUp extends Sprite {
	static Random random = new Random();
	private int type;
	private long duration;
	private long spawnTime;
	private long upTime;
	private int add;
	
	public PowerUp(int xPos, int yPos, int type, long spawnTime, long duration, int add) {
		super(1, 0, xPos, yPos, 0, 0, 0, 0, 0, 0, 0);
		
		this.type = type;
		this.duration = duration;
		this.add = add;
		this.spawnTime = spawnTime;
		this.upTime = upTime;
		
		if(type == Formatting.FRAGMENT) {
			loadImage(Formatting.PFRAGMENT);
		} else if (type == Formatting.DOUBLEDAMAGE) {
			loadImage(Formatting.PDOUBLEDAMAGE);
		} else if (type == Formatting.HEAL) {
			loadImage(Formatting.PHEAL);
		} else {
			loadImage(Formatting.PSPEEDBOOST);
		}
 		
	}
	
//	getters
	public int getType() {
		return this.type;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
//	Map boundaries (+20, -20)
	public static int spawnY() {
		int randY = random.nextInt(111,600);
		return randY;
	}
	
	public static int spawnX(int y) {
		int randX = 0;
		
//		north
		if (y >= 111 && y <= 187) {
			randX = random.nextInt(323, 851);
//		north 2
		} else if (y >= 188 && y <= 260) {
			randX = random.nextInt(250, 948);
//		middle
		} else if (y >= 261 && y <= 294) {
			randX = random.nextInt(154, 1050);
//		middle 2 (contains pillars)
		} else if (y >= 295 && y <= 430){
			int rand = random.nextInt(1, 4);
			
			if(rand == 1) {
				randX = random.nextInt(154, 279);
			} else if (rand == 2) {
				randX = random.nextInt(447, 843);
			} else {
				randX = random.nextInt(447, 843);
			}
			
//		middle 3
		} else if (y >= 431 && y <= 377){
			randX = random.nextInt(154, 1050);
//		south 1
		} else if (y >= 378 && y <= 466) {
			randX = random.nextInt(200, 1004);
//		south 2
		} else if (y >= 467 && y <= 540) {
			randX = random.nextInt(306, 903);
//		south 3
		} else {
			randX = random.nextInt(404, 797);
		}
		
		return randX;
	}

	@Override
	protected boolean dieAnimation(long nanoTime) {
		// collect animation?
		return false;
	}

	@Override
	protected void animation(long nanoTime, Sprite player) {
		// animation
	}

	@Override
	protected void hitAnimation(long currentTime, Sprite player, int direction2) {
		// 
	}
	
	public void checkPowerUpCollision(Sprite player) {
//    	Check collision between player and powerups    	
    	if (player.hitbox.intersects(this.hitbox)) {
    		
//    		if else get type of power up
    		if (this.type == Formatting.FRAGMENT) {
    			player.setAttackPoints(this.add);
    		} else if (this.type == Formatting.DOUBLEDAMAGE) {
    			
    		} else if (this.type == Formatting.HEAL) {
    			if(player.getHealth() < 100) {
    				player.setHealthAdd(this.add);
    			}
    		} else {
    			// speed boost
    		}
    		
    		System.out.println("Player " + player.getPlayerNumber() + " has collected " + this.type + "."); 
    		this.setAlive(false); // Regen power-up disappears
    	}
	}
}