package monsters;

import elements.Formatting;

public class Zombie1 extends Monster{

	private int animationCountIdle;
	private long previousTimeIdle;
	private int direction;
	
	public Zombie1(int xPos, int yPos, int direction) {
		super(xPos, yPos, Formatting.ZOMBIE1, 30, 10,  0.4, 0.745, 0.25, 0.22);
		this.animationCountIdle = 0;
		this.previousTimeIdle = System.nanoTime();
		this.direction = direction; 
		if (direction == 1) {
			this.loadImage(Formatting.Lvl1RZombie1, 65, 65);
		} else {
			this.loadImage(Formatting.Lvl1LZombie1, 65, 65);
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
			

}
