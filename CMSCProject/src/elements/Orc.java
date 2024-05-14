package elements;

public class Orc extends Sprite{
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
	
	public Orc(int x, int y, int playerNumber, long previousTime){
		super(Formatting.ORC, playerNumber, x,y, 
				0.28, 0.23, 
				0.45,
				0.29, 0.47,
				0.68, 0.17,
				0.05,
				0.32, 0.70,
				0.375,0.55,
				0.28);
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 1;
		this.animationCountWalk = 1;
		this.loadImage(Formatting.OrcRIdle1);
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
			this.img = Formatting.OrcRIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.OrcRIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.OrcRIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.OrcRIdle4;
		}
	}
	
	public void idleLeft () {
		this.animationCountIdle %= 5;
		if (animationCountIdle == 1) {
			this.img = Formatting.OrcLIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.OrcLIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.OrcLIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.OrcLIdle4;
		}
	}
	
	public void attackRightAnimation(long currentTime, Sprite player2) {
		this.setDX(0);
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 8;
			if (animationCountAttack == 1) {
				this.img = Formatting.OrcRAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.OrcRAttack2;
				
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.OrcRAttack3;
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.OrcRAttack4;
//				Checks if the weapon and character collides
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.OrcRAttack5;
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 6) {
				this.img = Formatting.OrcRAttack6;
			}
			else if (animationCountAttack == 7) {
				this.img = Formatting.OrcRAttack7;
			}
			else if (animationCountAttack == 0) {
				System.out.println("Attack Animation Finished");
				this.attack = false;
				this.setCollisionChecker(false);
			}
			this.previousTimeAttack = currentTime;
		}
	}
	
	public void attackLeftAnimation(long currentTime, Sprite player2) {
		this.setDX(0);
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 8;
			if (animationCountAttack == 1) {
				this.img = Formatting.OrcLAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.OrcLAttack2;
				
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.OrcLAttack3;
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.OrcLAttack4;	
			}
			else if (animationCountAttack == 5) {
//				Checks if the weapon and character collides
				this.img = Formatting.OrcLAttack5;
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 6) {
				this.img = Formatting.OrcLAttack6;
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 7) {
				this.img = Formatting.OrcLAttack7;
				if (this.getCollisionChecker() == false && player2.checkAlive()) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 0) {
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
					this.img = Formatting.OrcRDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.OrcRDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.OrcRDie3;
				} 
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
					this.img = Formatting.OrcRDie4;
				} else if (animationCountDie == 5){
					System.out.println("Dying Animation Finished");
					this.setVisible(false);
					return true;
				}
			} else {
				if (animationCountDie == 1) {
					this.img = Formatting.OrcLDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.OrcLDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.OrcLDie3;
				}
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
					this.img = Formatting.OrcLDie4;
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
			this.img = Formatting.OrcRWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.OrcRWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.OrcRWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.OrcRWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.OrcRWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.OrcRWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.OrcRWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.OrcRWalk8;
		}
	}
	
//	Frames for walking to left
	public void walkLeft () {
		this.animationCountWalk %= 9;
		if (animationCountWalk == 1) {
			this.img = Formatting.OrcLWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.OrcLWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.OrcLWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.OrcLWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.OrcLWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.OrcLWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.OrcLWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.OrcLWalk8;
		}
	}
	
//	Animation for hitting opponent
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
		if (getHit() == true && this.checkAlive()) {
			if (direction == 1) {
				this.img = Formatting.OrcRHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
//				Decreases health
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			} else {
				this.img = Formatting.OrcLHit1;
				System.out.println("Hit Animation Finished");
				this.hit = false;
//				Decreases health
				this.setHealth(attacker.getAttackPoints()); 
				System.out.println("Player Health Remaining: " + this.health);
			}
		} else if (this.getDirection() == 1){
			this.img = Formatting.OrcRIdle1;
		} else {
			this.img = Formatting.OrcLIdle1;
		}
	}
	

}
