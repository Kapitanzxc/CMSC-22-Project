package elements;

import java.util.ArrayList;
import java.util.Random;

import characters.Sprite;
import monsters.Monster;

public class PowerUp extends Sprite {
	static Random random = new Random();
	private int type;
	private long duration;
	private static final int POWERUPTIME = 8;
	
	
	public PowerUp(int xPos, int yPos, int type, long spawnTime, long duration) {
		super(0, 0, xPos, yPos, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0);
		
		this.type = type;
		this.duration = duration;
//		loads the image for the correct type
		if(type == Formatting.FRAGMENT) {
			int rand = random.nextInt(1,9);
			if(rand == 1) {
				loadImage(Formatting.BLUEFRAGMENT, 20, 15);
			} else if(rand == 2) {
				loadImage(Formatting.DBLUEFRAGMENT, 20, 15);
			} else if(rand == 3) {
				loadImage(Formatting.GREENFRAGMENT, 20, 15);
			} else if(rand == 4) {
				loadImage(Formatting.ORANGEFRAGMENT, 20, 15);
			} else if(rand == 5) {
				loadImage(Formatting.PINKFRAGMENT, 20, 20);
			} else if(rand == 6) {
				loadImage(Formatting.PURPLEFRAGMENT, 20, 20);
			} else if(rand == 7) {
				loadImage(Formatting.REDFRAGMENT, 20, 15);
			} else {
				loadImage(Formatting.YORANGEFRAGMENT, 20, 20);
			} 
		} else if (type == Formatting.ATTACKBOOST) {
			loadImage(Formatting.ABOOST, 40, 40);
		} else if (type == Formatting.HEAL) {
			loadImage(Formatting.PHEAL, 20, 20);
		} else {
			loadImage(Formatting.SBOOST, 40, 40);
		}
 		
	}

//	Map boundaries
	public static int spawnY() {
		int randY = random.nextInt(105,544);
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
			randX = random.nextInt(150, 1051);
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
	
	public void checkPowerUpCollision(Sprite player, long currentNanoTime) {
		checkActive(player, currentNanoTime);
		String typeFragment;
//    	Check collision between player and powerups
    	if (player.hitbox.intersects(this.hitbox)) {
    		
//    		if else get type of power up
    		if (this.type == Formatting.FRAGMENT) {
    			player.addAttackPoints();
    			player.addMaxHealth();
    			player.addHealth(1);
    			player.addFragments();
    			typeFragment = "Fragment";
    		} else if (this.type == Formatting.ATTACKBOOST) {
    			player.addSpecial();
    			// attack boost
    			player.setAttackBoostActive(true);
    			player.setAddDamage((int) (player.getAttackPoints()*0.5));
    			player.setAttackPoints(player.getAttackPoints() + player.getAddDamage());
    			player.setAttackBoostTime(currentNanoTime);
    			typeFragment = "Attack Boost";
    		} else if (this.type == Formatting.HEAL) {
    			player.addSpecial();
    			if(player.getHealth() < player.getMaxHealth()) {
    				player.addHealth(10);
    			}
    			typeFragment = "Heal";
    		} else {
    			player.addSpecial();
    			// speed boost
    			player.setSpeedBoostActive(true);
    			player.setSpeed(player.getSpeed() + 1);
    			player.setSpeedBoostTime(currentNanoTime);
    			typeFragment = "Speed Boost";
    		}
    		
    		
    		
    		System.out.println("Player " + player.getPlayerNumber() + " has collected " + typeFragment + "."); 
    		this.setAlive(false); // Power-up disappears
    	}
	}
	
	public void checkActive(Sprite player, long currentNanoTime) {
//		attack boost
		if(player.isAttackBoostActive() == true) {
			double elapsedAttackTime = (currentNanoTime - player.getAttackBoostTime()) / 1000000000.0;
			
			if(elapsedAttackTime > POWERUPTIME) {
				player.setAttackBoostActive(false);
    			player.setAttackPoints(player.getAttackPoints()-player.getAddDamage());
    			player.setAddDamage(0);
    			player.setAttackBoostTime(0);
			}
		}
		
//		speed boost
		if(player.isSpeedBoostActive() == true) {
			double elapsedSpeedTime = (currentNanoTime - player.getSpeedBoostTime()) / 1000000000.0;
			
			if(elapsedSpeedTime > POWERUPTIME) {
				player.setSpeedBoostActive(false);
    			player.setSpeed(player.getSpeed() - 1);
    			player.setSpeedBoostTime(0);
			}
		}
	}
//	getters
	public int getType() {
		return this.type;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
//	Unimplemented methods
	public boolean dieAnimation(long nanoTime) {return false;}
	public void animation(long nanoTime, Sprite player, ArrayList<Monster> monsterArrayList) {}
	public void hitAnimation(long currentTime, Sprite player, int direction2) {}
	public void hitAnimationMonster(long currentTime, Monster monster, int direction) {}

	
}