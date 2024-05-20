package elements;

import java.util.Random;

public class Monster extends Sprite {
	static Random random = new Random();
	private int type;
	private long duration;
//	private long spawnTime;
	private long upTime;
	private int add;
	public int animationCountIdle;
	private long previousTimeIdle;
	
	public Monster(int xPos, int yPos, int type, int health) {
		super(1, 0, xPos, yPos, health, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0);
		
		this.type = type;
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
	
	public void attackMonster(Sprite player) {
 	
    	if (player.attackbox.intersects(this.hitbox)) {
    		this.health -= player.attackPoints;
    	}
	}
	
// Image Frames

	public void idleRight() {
	    this.animationCountIdle %= 5;
	    switch (type) {
	    	
	    	// Right Zombie Level 1 animation
	    	case Formatting.ZOMBIE1:
	    		switch (animationCountIdle) {
		        	case 1:
		        		this.img = Formatting.Lvl1RZombie1;
		        		break;
		        	case 2:
		        		this.img = Formatting.Lvl1RZombie2;
		        		break;
		        	case 3:
		        		this.img = Formatting.Lvl1RZombie3;
		        		break;
		        	case 4:
		        		this.img = Formatting.Lvl1RZombie4;
		        		break;
		   			}
	    		break;
	    	
	    	// Right Zombie Level 3 animation
	    	case Formatting.ZOMBIE3:
	    		switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3RZombie1;
			            break;
			        case 2:
			            this.img = Formatting.Lvl3RZombie2;
			            break;
			        case 3:
			            this.img = Formatting.Lvl3RZombie3;
			            break;
			        case 4:
			            this.img = Formatting.Lvl3RZombie4;
			            break;
	    			}
	    		break;
	    		
	    	// Right Zombie Level 3 animation
	    	case Formatting.ZOMBIE2:
		   		switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2RZombie1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2RZombie2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2RZombie3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2RZombie4;
				        break;
		   		}
		   		break;
		   	
		   	// Left Zombie Level 1 animation
	    	case Formatting.LZOMBIE1:
		   		switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl1LZombie1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl1LZombie2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl1LZombie3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl1LZombie4;
				        break;
		   		}
		   		break;
		   		
		   	// Left Zombie Level 2 animation
	    	case Formatting.LZOMBIE2:
		   		switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2LZombie1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2LZombie2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2LZombie3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2LZombie4;
				        break;
		   		}
		   		break;
		   		
		   	// Left Zombie Level 3 animation
	    	case Formatting.LZOMBIE3:
		   		switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3LZombie1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl3LZombie2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl3LZombie3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl3LZombie4;
				        break;
		   		}
		   		break;
		   	
		   	// Right Ogre Level 1 animation
	    	case Formatting.OGRE1:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl1ROgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl1ROgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl1ROgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl1ROgre4;
				        break;
		    	}
	   		break;
	   		
//          Right Ogre Level 2 animation
	    	case Formatting.OGRE2:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2ROgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2ROgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2ROgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2ROgre4;
				        break;
		    	}
	   		break;
		    
//          Right Ogre Level 3 animation
	    	case Formatting.OGRE3:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3ROgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl3ROgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl3ROgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl3ROgre4;
				        break;
		    	}
	   		break;
	   		
//          Left Ogre Level 1 animation
	    	case Formatting.LOGRE1:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl1LOgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl1LOgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl1LOgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl1LOgre4;
				        break;
		    	}
	   		break;
	   		
	   		// Left Ogre Level 2 animation
	    	case Formatting.LOGRE2:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2LOgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2LOgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2LOgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2LOgre4;
				        break;
		    	}
	   		break;
	   		
	   		// Left Ogre Level 3 animation
	    	case Formatting.LOGRE3:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3LOgre1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl3LOgre2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl3LOgre3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl3LOgre4;
				        break;
		    	}
	   		break;
	   		
	   		// Right Demon Level 1 animation
	    	case Formatting.DEMON1:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl1RDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl1RDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl1RDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl1RDemon4;
				        break;
		    	}
	   		break;
	   		
	   		// Right Demon Level 2 animation
	    	case Formatting.DEMON2:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2RDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2RDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2RDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2RDemon4;
				        break;
		    	}
	   		break;
	   		
	   		// Right Demon Level 3 animation
	    	case Formatting.DEMON3:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3RDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl3RDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl3RDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl3RDemon4;
				        break;
		    	}
	   		break;
	   	
	   		// Left Demon Level 1 animation
	    	case Formatting.LDEMON1:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl1LDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl1LDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl1LDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl1LDemon4;
				        break;
		    	}
	   		break;
	   		
	   		// Left Demon Level 2 animation
	    	case Formatting.LDEMON2:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl2LDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl2LDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl2LDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl2LDemon4;
				        break;
		    	}
	   		break;    
	   		
	   		// Left Demon Level 3 animation
	    	case Formatting.LDEMON3:
		    	switch (animationCountIdle) {
			        case 1:
			            this.img = Formatting.Lvl3LDemon1;
			            break;
			        case 2:
				        this.img = Formatting.Lvl3LDemon2;
			            break;
			        case 3:
				        this.img = Formatting.Lvl3LDemon3;
				        break;
				    case 4:
				        this.img = Formatting.Lvl3LDemon4;
				        break;
		    	}
	   		break; 
	    }
	    
	}
	
	@Override
	protected boolean dieAnimation(long nanoTime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void animation(long currentTime, Sprite player2) {
//		Idle Animation
		if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
			if (this.getDX() == 0 && this.getDY()==0) {
				this.animationCountIdle ++;
				if (getDirection() == 1) {
					this.idleRight();
				}
//				} else {
//					this.idleRight();
//				}
				
				previousTimeIdle = currentTime;
			}
				
		}
		
	}

	@Override
	protected void hitAnimation(long currentTime, Sprite player1, int direction2) {
		// TODO Auto-generated method stub
		
	}
}