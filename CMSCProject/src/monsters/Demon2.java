package monsters;

import elements.Formatting;

public class Demon2 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Demon2(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.DEMON2, 50, 20,  0.325, 0.55, 0.28, 0.415);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl2RDemon1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl2LDemon1, 65, 65);
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
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}
		
		
	}
			

}
