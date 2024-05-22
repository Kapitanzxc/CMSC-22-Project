package elements;

public class Wizard extends Sprite {
//	Attributes for animation
	private long previousTimeDie,previousTimeAttack,previousTimeRWalk,previousTimeLWalk,previousTimeIdle;
	private int animationCountDie,animationCountIdle, animationCountAttack,animationCountWalk;
// 	Constructor
	
	public Wizard(int x, int y, int playerNumber, long previousTime){
		super(Formatting.WIZARD, playerNumber, x,y, 
				0.45, 0.30, 
				0.45,
				0.1, 0.64,
				0.68, 0.46,
				0.025,
				0.24, 0.62,
				0.375,0.55);
		
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 1;
		this.animationCountWalk = 1;
		this.loadImage(Formatting.WizRIdle1);
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
	public void idleRight() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.WizRIdle1;
	            break;
	        case 2:
	            this.img = Formatting.WizRIdle2;
	            break;
	        case 3:
	            this.img = Formatting.WizRIdle3;
	            break;
	        case 4:
	            this.img = Formatting.WizRIdle4;
	            break;
	        default:
	            // Handle unexpected case here
	            break;
	    }
	}

	public void idleLeft() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.WizLIdle1;
	            break;
	        case 2:
	            this.img = Formatting.WizLIdle2;
	            break;
	        case 3:
	            this.img = Formatting.WizLIdle3;
	            break;
	        case 4:
	            this.img = Formatting.WizLIdle4;
	            break;
	        default:
	            // Handle unexpected case here
	            break;
	    }
	}

	
	public void attackRightAnimation(long currentTime, Sprite player2) {
	    this.setDX(0);
	    this.setDY(0);

	    if (currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 10;

	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.WizRAttack1;
	                break;
	            case 2:
	                this.img = Formatting.WizRAttack2;
	                break;
	            case 3:
	                this.img = Formatting.WizRAttack3;
	                break;
	            case 4:
	                this.img = Formatting.WizRAttack4;
	                break;
	            case 5:
	                this.img = Formatting.WizRAttack5;
	                break;
	            case 6:
	                this.img = Formatting.WizRAttack6;
	                break;
	            case 7:
	                this.img = Formatting.WizRAttack7;
	                break;
	            case 8:
	                this.img = Formatting.WizRAttack8;
	                break;
	            case 9:
	                this.img = Formatting.WizRAttack9;
	                break;
	            case 0:
	                System.out.println("Attack Animation Finished");
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	            default:
	                // Handle unexpected case here
	                break;
	        }

	        if (animationCountAttack >= 5 && animationCountAttack <= 9 && this.getCollisionChecker() == false && player2.checkAlive()) {
	            this.checkCollision(this, player2, currentTime, player2.getDirection());
	        }

	        this.previousTimeAttack = currentTime;
	    }
	}

	public void attackLeftAnimation(long currentTime, Sprite player2) {
	    this.setDX(0);
	    this.setDY(0);

	    if (currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 10;

	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.WizLAttack1;
	                break;
	            case 2:
	                this.img = Formatting.WizLAttack2;
	                break;
	            case 3:
	                this.img = Formatting.WizLAttack3;
	                break;
	            case 4:
	                this.img = Formatting.WizLAttack4;
	                break;
	            case 5:
	                this.img = Formatting.WizLAttack5;
	                break;
	            case 6:
	                this.img = Formatting.WizLAttack6;
	                break;
	            case 7:
	                this.img = Formatting.WizLAttack7;
	                break;
	            case 8:
	                this.img = Formatting.WizLAttack8;
	                break;
	            case 9:
	                this.img = Formatting.WizLAttack9;
	                break;
	            case 0:
	                System.out.println("Attack Animation Finished");
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	            default:
	                // Handle unexpected case here
	                break;
	        }

	        if (animationCountAttack >= 5 && animationCountAttack <= 9 && this.getCollisionChecker() == false && player2.checkAlive()) {
	            this.checkCollision(this, player2, currentTime, player2.getDirection());
	        }

	        this.previousTimeAttack = currentTime;
	    }
	}

//	Dying animation
	public boolean dieAnimation(long currentTime) {
	    if (currentTime - this.previousTimeDie >= (1000 * 1000000)) {
	        this.animationCountDie++;
	        this.animationCountDie %= 6;
	        int direction = this.getDirection();
	        switch (animationCountDie) {
	            case 1:
	                this.img = (direction == 1) ? Formatting.WizRDie1 : Formatting.WizLDie1;
	                break;
	            case 2:
	                this.img = (direction == 1) ? Formatting.WizRDie2 : Formatting.WizLDie2;
	                break;
	            case 3:
	                this.img = (direction == 1) ? Formatting.WizRDie3 : Formatting.WizLDie3;
	                break;
	            case 4:
	                this.img = (direction == 1) ? Formatting.WizRDie4 : Formatting.WizLDie4;
	                break;
	            case 5:
	                System.out.println("Dying Animation Finished");
	                this.setVisible(false);
	                return true;
	            default:
	                // Handle unexpected case here
	                break;
	        }
	        this.previousTimeDie = currentTime;
	    }
	    return false;
	}

//	Frames for walking to right
	public void walkRight() {
	    this.animationCountWalk %= 9;
	    switch (animationCountWalk) {
	        case 1:
	            this.img = Formatting.WizRWalk1;
	            break;
	        case 2:
	            this.img = Formatting.WizRWalk2;
	            break;
	        case 3:
	            this.img = Formatting.WizRWalk3;
	            break;
	        case 4:
	            this.img = Formatting.WizRWalk4;
	            break;
	        case 5:
	            this.img = Formatting.WizRWalk5;
	            break;
	        case 6:
	            this.img = Formatting.WizRWalk6;
	            break;
	        case 7:
	            this.img = Formatting.WizRWalk7;
	            break;
	        case 8:
	            this.img = Formatting.WizRWalk8;
	            break;
	        default:
	            // Handle unexpected case here
	            break;
	    }
	}

	
//	Frames for walking to left
	public void walkLeft() {
	    this.animationCountWalk %= 9;
	    switch (animationCountWalk) {
	        case 1:
	            this.img = Formatting.WizLWalk1;
	            break;
	        case 2:
	            this.img = Formatting.WizLWalk2;
	            break;
	        case 3:
	            this.img = Formatting.WizLWalk3;
	            break;
	        case 4:
	            this.img = Formatting.WizLWalk4;
	            break;
	        case 5:
	            this.img = Formatting.WizLWalk5;
	            break;
	        case 6:
	            this.img = Formatting.WizLWalk6;
	            break;
	        case 7:
	            this.img = Formatting.WizLWalk7;
	            break;
	        case 8:
	            this.img = Formatting.WizLWalk8;
	            break;
	        default:
	            // Handle unexpected case here
	            break;
	    }
	}

	
//	Animation for hitting opponent
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
	    if (getHit() && this.checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.WizRHit1;
	                break;
	            default:
	                this.img = Formatting.WizLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
	        this.hit = false;
	        // Decreases health
	        this.setHealth(attacker.getAttackPoints()); 
	        System.out.println("Player Health Remaining: " + this.health);
	    } else {
	        if (this.getDirection() == 1) {
	            this.img = Formatting.WizRIdle1;
	        } else {
	            this.img = Formatting.WizLIdle1;
	        }
	    }
	}

	

}
