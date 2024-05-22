package monsters;

import elements.Formatting;

public class Demon1 extends Monster{
//	Attributes
	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
//	Constructor
	public Demon1(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.DEMON1, 50, 10, 0.4, 0.71, 0.27, 0.26);
//		Variables for animation
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
//		Load image
		if (direction == 1) {
			this.loadImage(Formatting.Lvl1RDemon1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl1LDemon1, 65, 65);

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
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				previousTimeIdle = currentTime;
			}
		}
	}
	
//	Animation when hit
	public void hitAnimation() {
		if (direction == 1) {
			this.img = Formatting.Lvl1RDemonHit;
		}	
		else {
			this.img = Formatting.Lvl1LDemonHit;
		}
	}
	
			

}
