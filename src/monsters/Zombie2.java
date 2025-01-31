package monsters;

import elements.Formatting;

public class Zombie2 extends Monster{
//	Attributes
	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
//	Constructor
	public Zombie2(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.ZOMBIE2, 100, 20,  0.355, 0.475, 0.275, 0.5);
//		Variables for animation
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
//		Load image
		if (direction == 1) {
			this.loadImage(Formatting.Lvl2RZombie1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl2LZombie1, 65, 65);
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
				
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}	
		}		
	}
	
//	Animation when hit
	public void hitAnimation() {
		// Hit sound effect
        playSound(Formatting.HITSOUNDFX);
		if (direction == 1) {
			this.img = Formatting.Lv12RZombieHit;
		}	
		else {
			this.img = Formatting.Lv12LZombieHit;
		}
	}
}