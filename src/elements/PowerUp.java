package elements;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

import characters.Sprite;
import monsters.Monster;
import scenes.GameTimer;

public class PowerUp extends Sprite {
	static Random random = new Random();
	private int type;
	
	private static int spawnX, spawnY;
	private final static int POWERUPTIME = 8;			// Duration  of 8 seconds if a power-up is picked up
	private final static int SPAWNDELAY_FPOWERUP = 5; 	// 5 seconds
	private final static int SPAWNDELAY_SPOWERUP = 20; 	// 20 seconds
	private final static int UPTIME_SPOWERUP = 10;		// Special Power-ups disappear after 10 seconds if not picked up
	private final static int NUM_FRAGMENT_POWERUP = 20;	// 20 fragments
	private final static int NUM_SPECIAL_POWERUP = 3;	// 3 special power-ups (1 of each type)
	
	public PowerUp(int xPos, int yPos, int type) {
		super(0, 0, xPos, yPos, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0);
		
		this.type = type;
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

// 	Methods for generating and managing power-ups
    public static void generateFragmentPowerUps(ArrayList<PowerUp> fragmentPowerUps) {
// 		Create fragment type power-ups
    	for (int i = fragmentPowerUps.size(); i < NUM_FRAGMENT_POWERUP; i++) {
            spawnY = PowerUp.spawnY();
            spawnX = PowerUp.spawnX(spawnY);
            PowerUp fragment = new PowerUp(spawnX, spawnY, 1);
            fragmentPowerUps.add(fragment);
        }
    }
    
//	Generate special powerups
	public static void generateSpecialPowerUps(ArrayList<PowerUp> specialPowerUps) {        
//		Create special type power-ups
        for (int i = 0; i < NUM_SPECIAL_POWERUP; i++) {
        	spawnY = PowerUp.spawnY();
            spawnX = PowerUp.spawnX(spawnY);
            PowerUp special = new PowerUp(spawnX, spawnY, i+2);
            specialPowerUps.add(special);
        }
	}
	
//	Spawn fragments
	public static void spawnFragmentPowerUps(ArrayList<PowerUp> fragmentPowerUps, long currentNanoTime) {
        // Elapsed time from spawn to current (in seconds)
        double spawnElapsedTime = (currentNanoTime - GameTimer.getPreviousTimeFPowerUp()) / 1000000000.0;
        // Respawn every 5 seconds (if fragments < 20)
        if (spawnElapsedTime > SPAWNDELAY_FPOWERUP) {
            generateFragmentPowerUps(fragmentPowerUps); // generates power ups
            GameTimer.setPreviousTimeFPowerUp(currentNanoTime); // resets time to compare again until the spawn delay
        }
	}
	
//	Spawn powerups
	public static void spawnSpecialPowerUps(ArrayList<PowerUp> specialPowerUps, long currentNanoTime) {
//		Elapsed time from spawn to current (in seconds)
		double spawnElapsedTime = (currentNanoTime - GameTimer.getPreviousTimeSPowerUp()) / 1000000000.0;
        
// 		Spawn special power-ups
   		if (spawnElapsedTime > SPAWNDELAY_SPOWERUP) {
     	  	generateSpecialPowerUps(specialPowerUps); // generates power ups
     	  	GameTimer.setPreviousTimeSPowerUp(currentNanoTime); // resets time to compare again until 10 seconds elapsed
   		}
	}
	
//  Render power-ups
	public static void renderPowerUps(ArrayList<PowerUp> fragmentPowerUps, ArrayList<PowerUp> specialPowerUps, GraphicsContext gc) {
		for (PowerUp fragment : fragmentPowerUps) {
			fragment.render(gc);
		}
		
		for (PowerUp special : specialPowerUps) {	        
	        special.render(gc);
		}
	}
	
//	Collects powerups
	public static void collectPowerUps(ArrayList<PowerUp> fragmentPowerUps, ArrayList<PowerUp> specialPowerUps, Sprite player1, Sprite player2, long currentNanoTime) {
		for(int i = 0; i < specialPowerUps.size(); i++){
//			Checks collision of characters and powerups
	        PowerUp special = specialPowerUps.get(i);
	        if (special.getAlive()){
	        	special.checkPowerUpCollision(player1, currentNanoTime);
	        	special.checkPowerUpCollision(player2, currentNanoTime);
	        } else {
//	        	Removes special power ups if idle for 10 seconds or picked up
	        	specialPowerUps.remove(i);
	        }
	    }
		
		for(int i = 0; i < fragmentPowerUps.size(); i++){
//			Checks collision of characters and fragments
	        PowerUp fragment = fragmentPowerUps.get(i);
	        if (fragment.getAlive()){
	        	fragment.checkPowerUpCollision(player1, currentNanoTime);
	        	fragment.checkPowerUpCollision(player2, currentNanoTime);
	        } else {
//	        	Removes fragments that are picked up 
	        	fragmentPowerUps.remove(i);
	        }
	    }
	}

//	Delete special power-ups that is idle for 10 seconds
	public static void deletePowerUps(ArrayList<PowerUp> specialPowerUps, long currentNanoTime) {		
		for(int i = 0; i < specialPowerUps.size(); i++){
			if (((currentNanoTime-GameTimer.getPreviousTimeSPowerUp()) / 1000000000.0) >= UPTIME_SPOWERUP){ // checks if 5 seconds have elapsed
				specialPowerUps.get(i).setAlive(false);
				specialPowerUps.remove(i); // removed from the array list
				
				i--; // adjusting index
			}
		}		
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
    				player.addHealth(25);
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
	
//	Map boundaries
	public static int spawnY() {
		int randY = random.nextInt(105,500);
		return randY;
	}
	
	public static int spawnX(int y) {
		int randX = 0;
		
//		north
		if (y >= 105 && y <= 179) {
			randX = random.nextInt(350, 800);
//		north 2
		} else if (y >= 180 && y <= 249) {
			randX = random.nextInt(250, 900);
//		middle
		} else if (y >= 250 && y <= 379) {
			randX = random.nextInt(210, 950);
//		south 1
		} else if (y >= 380 && y <= 469) {
			randX = random.nextInt(300, 870);
//		south 2
		} else if (y >= 470 && y <= 500) {
			randX = random.nextInt(300, 800);
		}
		return randX;
	}
	

	
//	Unimplemented methods
	public boolean dieAnimation(long nanoTime) {return false;}
	public void animation(long nanoTime, Sprite player, ArrayList<Monster> monsterArrayList) {}
	public void hitAnimation(long currentTime, Sprite player, int direction2) {}
	public void hitAnimationMonster(long currentTime, Monster monster, int direction) {}

	
}