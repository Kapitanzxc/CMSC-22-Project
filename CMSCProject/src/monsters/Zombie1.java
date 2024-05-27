package monsters;

import elements.Formatting;

public class Zombie1 extends Monster{
//	Attributes
	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
//	Constructor
	public Zombie1(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.ZOMBIE1, 50, 10,  0.4, 0.745, 0.25, 0.22);
//		Variables for animation
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
//		Load image
		if (direction == 1) {
			this.loadImage(Formatting.Lvl1RZombie1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl1LZombie1, 65, 65);
		}
	}
	
//	Animation for idle
	public void animation(long currentTime) {
//		Animation depending on its direction
		if (direction == 1) {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				previousTimeIdle = currentTime;
			}
		} 
		
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}	
	}
	
//	Animation when hit
	public void hitAnimation() {
		// Hit sound effect
        playSound(Formatting.HITSOUNDFX);
		if (direction == 1) {
			this.img = Formatting.Lv11RZombieHit;
		}	
		else {
			this.img = Formatting.Lv11LZombieHit;
		}
	}

}
