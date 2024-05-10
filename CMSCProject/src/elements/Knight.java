package elements;

// Knight Character
public class Knight extends Sprite{
//	Attributes for animation
	private long previousTimeIdle;
	private long previousTimeAttack;
	private long previousTimeDie;
	public int animationCountIdle;
	private int animationCountAttack;
	private int animationCountDie;
// 	Constructor
	public Knight(int x, int y, long previousTime){
		super(x,y, 0.20, 0.18, 0.53, 0.05);
		this.previousTimeIdle = previousTime;
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 1;
		this.loadImage(Formatting.Knight);
	}
	
//	Display images per frames per second
	public void animation (long currentTime, Sprite player2) {
		if(currentTime - this.previousTimeIdle >= (250 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == 0 && this.getDY()==0) {
				this.animationCountIdle ++;
				this.idle();
				previousTimeIdle = currentTime;
			}
		}
		if(this.getAttack() && checkAlive()) {
			this.attackAnimation(currentTime, player2);
			this.previousTimeDie = currentTime;
		}
		if ( !(this.checkAlive()) && this.getVisible() == true) {
			this.dieAnimation(currentTime);
		}
	}

	//	Image frames
	public void idle () {
		this.animationCountIdle %= 5;
		if (animationCountIdle == 1) {
			this.img = Formatting.Knight;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.Knight2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.Knight3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.Knight4;
		}
	}
	
	public void attackAnimation(long currentTime, Sprite player2) {
		this.setDX(0);
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 6;
			if (animationCountAttack == 1) {
				this.img = Formatting.KnightAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.KnightAttack2;
				if (this.getCollisionChecker() == false) {
					this.checkCollision(this, player2, currentTime);
				}
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.KnightAttack3;
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.KnightAttack4;
		
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.KnightAttack5;
				System.out.println("Attack Animation Finished");
				this.attack = false;
				this.setCollisionChecker(false);
			}
			
			this.previousTimeAttack = currentTime;
		}
	}
	
	public void dieAnimation(long currentTime) {
		if(currentTime - this.previousTimeDie>= (1000 * 1000000)) {
			this.animationCountDie ++;
			this.animationCountDie %= 4;
			if (animationCountDie == 1) {
				this.img = Formatting.KnightDie1;
			} 
			else if (animationCountDie == 2) {
				this.img = Formatting.KnightDie2;
			}
			else if (animationCountDie == 3) {
				this.img = Formatting.KnightDie3;
				System.out.println("Dying Animation Finished");
				this.setVisible(false);
			}
			this.previousTimeDie = currentTime;
		}
	}
	

	
}

