package elements;

public class SwordWoman extends Sprite{
//	Attributes for animation
	private long previousTimeIdle;
	private long previousTimeAttack;
	private long previousTimeDie;
	private long previousTimeRWalk;
	private long previousTimeLWalk;
	public int animationCountIdle;
	private int animationCountAttack;
	private int animationCountDie;
	private int animationCountWalk;
	
// 	Constructor
	public SwordWoman(int x, int y, int playerNumber, long previousTime){
		super(Formatting.SWORDWOMAN, playerNumber, x,y, 0.17, 0.20, 0.53, 0.1, 0,1,1);
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 0;
		this.animationCountWalk = 1;
		this.loadImage(Formatting.SWRIdle1);
//		this.loadImage(Formatting.PIXEL);
	}
	
//	Display images per frames per second
	public void animation (long currentTime, Sprite player2) {
//		Idle Animation
		if(currentTime - this.previousTimeIdle >= (250 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == 0 && this.getDY()==0) {
				this.animationCountIdle ++;
				if (getDirection() == 1) {
					this.idleRight();
				} else {
					this.idleLeft();
				}
				
				previousTimeIdle = currentTime;
			}
			
		}
		// Animation Right Walk
		if(currentTime - this.previousTimeRWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == 2) {
				this.animationCountWalk ++;
				this.walkRight();
				this.setDirection(1);
				previousTimeRWalk = currentTime;
			}
		}
		
		// Animation Left Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == -2) {
				this.animationCountWalk ++;
				this.walkLeft();
				this.setDirection(2);
				previousTimeLWalk = currentTime;
			}
		}
		
		// Animation Right Down Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDY() == 2) {
				this.animationCountWalk ++;
				if (getDirection() == 1) {
					this.walkRight();
				} else {
					this.walkLeft();
				}
				previousTimeLWalk = currentTime;
				}
			}
		
		// Animation Left Down Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDY() == -2) {
				this.animationCountWalk ++;
				if (getDirection() == 1) {
					this.walkRight();
				} else {
					this.walkLeft();
				}
				previousTimeLWalk = currentTime;
				}
			}
//		Attack animation
		if(this.getAttack() && checkAlive()) {
			if (this.getDirection() == 1) {
				this.attackRightAnimation(currentTime, player2);
				this.previousTimeDie = currentTime;
			} else {
				this.attackLeftAnimation(currentTime, player2);
				this.previousTimeDie = currentTime;
			}
		}
	}

//	Image frames
	public void idleRight () {
		this.animationCountIdle %= 5;
		if (animationCountIdle == 1) {
			this.img = Formatting.SWRIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.SWRIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.SWRIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.SWRIdle4;
		}
	}
	
	public void idleLeft () {
		this.animationCountIdle %= 5;
		if (animationCountIdle == 1) {
			this.img = Formatting.SWLIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.SWLIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.SWLIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.SWLIdle4;
		}
	}
	
	public void attackRightAnimation(long currentTime, Sprite player2) {
		this.setDX(0);
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 7;
			if (animationCountAttack == 1) {
				this.img = Formatting.SWRAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.SWRAttack2;
				
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.SWRAttack3;
//				Checks if the weapon and character collides
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.SWRAttack4;
		
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.SWRAttack5;
				
			}
			else if (animationCountAttack == 6) {
				this.img = Formatting.SWRAttack6;
				System.out.println("Attack Animation Finished");
				this.attack = false;
				this.setCollisionChecker(false);
			}
			this.previousTimeAttack = currentTime;
		}
	}
	
	public void attackLeftAnimation(long currentTime, Sprite player2) {
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 7;
			if (animationCountAttack == 1) {
				this.img = Formatting.SWLAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.SWLAttack2;
				
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.SWLAttack3;
//				Checks if the weapon and character collides
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.SWLAttack4;
		
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.SWLAttack5;
			}
			else if (animationCountAttack == 6) {
				this.img = Formatting.SWLAttack6;
				System.out.println("Attack Animation Finished");
				this.attack = false;
				this.setCollisionChecker(false);
			}
			this.previousTimeAttack = currentTime;
		}
	}
//	Dying animation
	public boolean dieAnimation(long currentTime) {
		if(currentTime - this.previousTimeDie>= (1000 * 1000000)) {
			this.animationCountDie ++;
			this.animationCountDie %= 6;
			if (this.getDirection() == 1) {
				if (animationCountDie == 1) {
					this.img = Formatting.SWRDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.SWRDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.SWRDie3;
				} 
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
					this.img = Formatting.SWRDie4;
				} else if (animationCountDie == 5){
					System.out.println("Dying Animation Finished");
					this.setVisible(false);
					return true;
				}
			} else {
				if (animationCountDie == 1) {
					this.img = Formatting.SWLDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.SWLDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.SWLDie3;
				}
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
					this.img = Formatting.SWLDie4;
				} else if (animationCountDie == 5){
					System.out.println("Dying Animation Finished");
					this.setVisible(false);
					return true;
				}
			}
			this.previousTimeDie = currentTime;
			
		}
		
		return false;
	}
	
//	Frames for walking to right
	public void walkRight () {
		this.animationCountWalk %= 9;
		if (animationCountWalk == 1) {
			this.img = Formatting.SWRWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.SWRWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.SWRWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.SWRWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.SWRWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.SWRWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.SWRWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.SWRWalk8;
		}
	}
	
//	Frames for walking to left
	public void walkLeft () {
		this.animationCountWalk %= 9;
		if (animationCountWalk == 1) {
			this.img = Formatting.SWLWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.SWLWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.SWLWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.SWLWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.SWLWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.SWLWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.SWLWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.SWLWalk8;
		}
	}

//	Animation for hitting opponent
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
		if (getHit() == true && this.checkAlive()) {
			if (direction == 1) {
				this.img = Formatting.SWRHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
//				Decreases health
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			} else {
				this.img = Formatting.SWLHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
//				Decreases health
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			}
		} else if (this.getDirection() == 1){
			this.img = Formatting.SWRIdle1;
		} else {
			this.img = Formatting.SWLIdle1;
		}
	}
}
