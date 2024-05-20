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
		super(1, 0, xPos, yPos, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0);
		
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
    			player.addMaxHealth(this.add);
    			player.addHealth(this.add);
    			player.addFragments(this.add);
    		} else if (this.type == Formatting.DOUBLEDAMAGE) {
    			player.addSpecial(1);
    			// double damage
    		} else if (this.type == Formatting.HEAL) {
    			player.addSpecial(1);
    			if(player.getHealth() < player.maxHealth) {
    				player.setHealthAdd(this.add);
    			}
    		} else {
    			player.addSpecial(1);
    			// speed boost
    		}
    		
    		System.out.println("Player " + player.getPlayerNumber() + " has collected " + this.type + "."); 
    		this.setAlive(false); // Regen power-up disappears
    	}
	}
}