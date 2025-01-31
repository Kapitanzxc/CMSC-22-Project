package monsters;

import elements.Formatting;

public class Ogre2 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Ogre2(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.OGRE2, 100, 20, 0.345, 0.525, 0.31, 0.455);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl2ROgre1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl2LOgre1, 65, 65);
		}
		
	}
	

	@Override
	public void animation(long currentTime) {
		if (direction == 1) {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}
	}
			
	public void hitAnimation() {
		// Hit sound effect
        playSound(Formatting.HITSOUNDFX);
		if (direction == 1) {
			this.img = Formatting.Lvl2ROgreHit;
		}	
		else {
			this.img = Formatting.Lvl2LOgreHit;
		}
	}

}
