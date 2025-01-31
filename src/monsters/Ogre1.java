package monsters;

import elements.Formatting;

public class Ogre1 extends Monster{
//	Attributes
	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
//	Constructor
	public Ogre1(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.OGRE1, 50, 10,  0.4, 0.725, 0.235, 0.25);
//		Variables for animation
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction;
//		Load image
		if (direction == 1) {
			this.loadImage(Formatting.Lvl1ROgre1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl1LOgre1, 65, 65);
		}
		
	}
	
//	Animation for idle
	public void animation(long currentTime) {
//		Animation depending on its direction
		if (this.direction == 1) {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				previousTimeIdle = currentTime;
			}
		} else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				previousTimeIdle = currentTime;
			}
		}
	}
	
//	Animation when hit
	public void hitAnimation() {
		// Hit sound effect
        playSound(Formatting.HITSOUNDFX);
		if (direction == 1) {
			this.img = Formatting.Lvl1ROgreHit;
		}	
		else {
			this.img = Formatting.Lvl1LOgreHit;
		}
	}
	
}
