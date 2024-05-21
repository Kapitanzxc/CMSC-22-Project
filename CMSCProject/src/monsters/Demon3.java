package monsters;

import elements.Formatting;

public class Demon3 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Demon3(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.DEMON3, 100, 0.24, 0.18, 0.515, 0.77);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl3RDemon1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl3LDemon1, 65, 65);
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
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}
	
	}			

}
