package elements;

// Knight Character
public class Knight extends Sprite{
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
	public Knight(int x, int y, long previousTime){
		super(x,y, 0.20, 0.18, 0.53, 0.05);
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 1;
		this.animationCountWalk = 1;
		this.loadImage(Formatting.KnightRIdle1);
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
		if(this.getAttack() && checkAlive() && player2.checkAlive()) {
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
			this.img = Formatting.KnightRIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.KnightRIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.KnightRIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.KnightRIdle4;
		}
	}
	
	public void idleLeft () {
		this.animationCountIdle %= 5;
		if (animationCountIdle == 1) {
			this.img = Formatting.KnightLIdle1;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.KnightLIdle2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.KnightLIdle3;
		}
		else if (animationCountIdle == 4) {
			this.img = Formatting.KnightLIdle4;
		}
	}
	
	public void attackRightAnimation(long currentTime, Sprite player2) {
		this.setDX(0);
		this.setDY(0);
		// TODO Auto-generated method stub
		if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
			this.animationCountAttack ++;
			this.animationCountAttack %= 6;
			if (animationCountAttack == 1) {
				this.img = Formatting.KnightRAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.KnightRAttack2;
				
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.KnightRAttack3;
//				Checks if the weapon and character collides
				if (this.getCollisionChecker() == false) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.KnightRAttack4;
		
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.KnightRAttack5;
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
			this.animationCountAttack %= 6;
			if (animationCountAttack == 1) {
				this.img = Formatting.KnightLAttack1;
			} 
			else if (animationCountAttack == 2) {
				this.img = Formatting.KnightLAttack2;
//				Checks if the weapon and character collides
				if (this.getCollisionChecker() == false) {
					this.checkCollision(this, player2, currentTime, player2.getDirection());
				}
			}
			else if (animationCountAttack == 3) {
				this.img = Formatting.KnightLAttack3;
			}
			else if (animationCountAttack == 4) {
				this.img = Formatting.KnightLAttack4;
		
			}
			else if (animationCountAttack == 5) {
				this.img = Formatting.KnightLAttack5;
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
			this.animationCountDie %= 5;
			if (this.getDirection() == 1) {
				if (animationCountDie == 1) {
					this.img = Formatting.KnightRDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.KnightRDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.KnightRDie3;
				} 
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
					System.out.println("Dying Animation Finished");
					this.setVisible(false);
					return true;
				}
			} else {
				if (animationCountDie == 1) {
					this.img = Formatting.KnightLDie1;
				} 
				else if (animationCountDie == 2) {
					this.img = Formatting.KnightLDie2;
				}
				else if (animationCountDie == 3) {
					this.img = Formatting.KnightLDie3;
				}
				else if (animationCountDie == 4) {
//					If character dies, return true (game is over)
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
			this.img = Formatting.KnightRWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.KnightRWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.KnightRWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.KnightRWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.KnightRWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.KnightRWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.KnightRWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.KnightRWalk8;
		}
	}
	
//	Frames for walking to left
	public void walkLeft () {
		this.animationCountWalk %= 9;
		if (animationCountWalk == 1) {
			this.img = Formatting.KnightLWalk1;
		} 
		else if (animationCountWalk == 2) {
			this.img = Formatting.KnightLWalk2;
		}
		else if (animationCountWalk == 3) {
			this.img = Formatting.KnightLWalk3;
		}
		else if (animationCountWalk == 4) {
			this.img = Formatting.KnightLWalk4;
		}
		else if (animationCountWalk == 5) {
			this.img = Formatting.KnightLWalk5;
		}
		else if (animationCountWalk == 6) {
			this.img = Formatting.KnightLWalk6;
		}
		else if (animationCountWalk == 7) {
			this.img = Formatting.KnightLWalk7;
		}
		else if (animationCountWalk == 8) {
			this.img = Formatting.KnightLWalk8;
		}
	}
	

	
}

