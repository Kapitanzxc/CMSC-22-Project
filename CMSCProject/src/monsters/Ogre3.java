package monsters;

import elements.Formatting;

public class Ogre3 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Ogre3(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.OGRE3, 80, 30,  0.225, 0.13, 0.59, 0.75);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl3ROgre1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl3LOgre1, 65, 65);
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
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}
	}		
}
