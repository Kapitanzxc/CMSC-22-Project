package monsters;

import elements.Formatting;

public class Zombie3 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Zombie3(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.ZOMBIE3, 80, 30,  0.2, 0.26, 0.6, 0.715);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl3RZombie1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl3LZombie1, 65, 65);
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
				
				previousTimeIdle = currentTime;
			}
		} 
		else {
			if(currentTime - this.previousTimeIdle >= (100 * 1000000)) {
				this.animationCountIdle ++;
				this.animationCountIdle %= 5;
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
				
				previousTimeIdle = currentTime;
			}
		}
	
		
	}
			

}
