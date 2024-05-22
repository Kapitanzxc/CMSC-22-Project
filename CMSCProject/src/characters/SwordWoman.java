package characters;

import elements.Formatting;

public class SwordWoman extends Sprite{
//	Attributes for animation
	private long previousTimeIdle,previousTimeAttack,previousTimeDie,previousTimeRWalk,previousTimeLWalk;
	public int animationCountIdle,animationCountAttack,animationCountDie,animationCountWalk;
	
// 	Constructor
	public SwordWoman(int x, int y, int playerNumber, long previousTime){
		super(Formatting.SWORDWOMAN, playerNumber, x,y, 100,  
				0.17, 0.20, 
				0.52,
				0.35, 0.7,
				0.53, 0.25, 
				0, 
				0.44, 0.61,
				0,1);
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 0;
		this.animationCountWalk = 1;
		this.loadImage(Formatting.SWRIdle1, 55, 44);
//		this.loadImage(Formatting.PIXEL);
	}
	
//	**********
//  inadjust ko din yung sa animation ng walking imbis na 2 ginawa kong based sa speed
//	**********
	
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
			if (this.getDX() == this.getSpeed()) {
				this.animationCountWalk ++;
				this.walkRight();
				this.setDirection(1);
				previousTimeRWalk = currentTime;
			}
		}
		
		// Animation Left Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == -this.getSpeed()) {
				this.animationCountWalk ++;
				this.walkLeft();
				this.setDirection(2);
				previousTimeLWalk = currentTime;
			}
		}
		
		// Animation Right Down Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDY() == this.getSpeed()) {
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
			if (this.getDY() == -this.getSpeed()) {
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
	            this.img = Formatting.SWRIdle1;
	            break;
	        case 2:
	            this.img = Formatting.SWRIdle2;
	            break;
	        case 3:
	            this.img = Formatting.SWRIdle3;
	            break;
	        case 4:
	            this.img = Formatting.SWRIdle4;
	            break;
	        default:
	            break;
	    }
	}

	public void idleLeft() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.SWLIdle1;
	            break;
	        case 2:
	            this.img = Formatting.SWLIdle2;
	            break;
	        case 3:
	            this.img = Formatting.SWLIdle3;
	            break;
	        case 4:
	            this.img = Formatting.SWLIdle4;
	            break;
	        default:
	            break;
	    }
	}

	
	public void attackRightAnimation(long currentTime, Sprite player2) {
	    this.setDX(0);
	    this.setDY(0);
	    // TODO Auto-generated method stub
	    if (currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 7;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.SWRAttack1;
	                break;
	            case 2:
	                this.img = Formatting.SWRAttack2;
	                break;
	            case 3:
	                this.img = Formatting.SWRAttack3;
	                // Checks if the weapon and character collide
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection());
	                }
	                break;
	            case 4:
	                this.img = Formatting.SWRAttack4;
	                break;
	            case 5:
	                this.img = Formatting.SWRAttack5;
	                break;
	            case 6:
	                this.img = Formatting.SWRAttack6;
	                System.out.println("Attack Animation Finished");
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	            default:
	                break;
	        }
	        this.previousTimeAttack = currentTime;
	    }
	}

	
	public void attackLeftAnimation(long currentTime, Sprite player2) {
	    this.setDY(0);
	    // TODO Auto-generated method stub
	    if (currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 7;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.SWLAttack1;
	                break;
	            case 2:
	                this.img = Formatting.SWLAttack2;
	                break;
	            case 3:
	                this.img = Formatting.SWLAttack3;
	                // Checks if the weapon and character collide
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection());
	                }
	                break;
	            case 4:
	                this.img = Formatting.SWLAttack4;
	                break;
	            case 5:
	                this.img = Formatting.SWLAttack5;
	                break;
	            case 6:
	                this.img = Formatting.SWLAttack6;
	                System.out.println("Attack Animation Finished");
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	            default:
	                break;
	        }
	        this.previousTimeAttack = currentTime;
	    }
	}

//	Dying animation
	public boolean dieAnimation(long currentTime) {
	    if (currentTime - this.previousTimeDie >= (1000 * 1000000)) {
	        this.animationCountDie++;
	        this.animationCountDie %= 6;
	        if (this.getDirection() == 1) {
	            switch (animationCountDie) {
	                case 1:
	                    this.img = Formatting.SWRDie1;
	                    break;
	                case 2:
	                    this.img = Formatting.SWRDie2;
	                    break;
	                case 3:
	                    this.img = Formatting.SWRDie3;
	                    break;
	                case 4:
	                    // If character dies, return true (game is over)
	                    this.img = Formatting.SWRDie4;
	                    break;
	                case 5:
	                    System.out.println("Dying Animation Finished");
	                    this.setVisible(false);
	                    return true;
	                default:
	                    break;
	            }
	        } else {
	            switch (animationCountDie) {
	                case 1:
	                    this.img = Formatting.SWLDie1;
	                    break;
	                case 2:
	                    this.img = Formatting.SWLDie2;
	                    break;
	                case 3:
	                    this.img = Formatting.SWLDie3;
	                    break;
	                case 4:
	                    // If character dies, return true (game is over)
	                    this.img = Formatting.SWLDie4;
	                    break;
	                case 5:
	                    System.out.println("Dying Animation Finished");
	                    this.setVisible(false);
	                    return true;
	                default:
	                    break;
	            }
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
	            this.img = Formatting.SWRWalk1;
	            break;
	        case 2:
	            this.img = Formatting.SWRWalk2;
	            break;
	        case 3:
	            this.img = Formatting.SWRWalk3;
	            break;
	        case 4:
	            this.img = Formatting.SWRWalk4;
	            break;
	        case 5:
	            this.img = Formatting.SWRWalk5;
	            break;
	        case 6:
	            this.img = Formatting.SWRWalk6;
	            break;
	        case 7:
	            this.img = Formatting.SWRWalk7;
	            break;
	        case 8:
	            this.img = Formatting.SWRWalk8;
	            break;
	        default:
	            break;
	    }
	}

//	Frames for walking to left
	public void walkLeft() {
	    this.animationCountWalk %= 9;
	    switch (animationCountWalk) {
	        case 1:
	            this.img = Formatting.SWLWalk1;
	            break;
	        case 2:
	            this.img = Formatting.SWLWalk2;
	            break;
	        case 3:
	            this.img = Formatting.SWLWalk3;
	            break;
	        case 4:
	            this.img = Formatting.SWLWalk4;
	            break;
	        case 5:
	            this.img = Formatting.SWLWalk5;
	            break;
	        case 6:
	            this.img = Formatting.SWLWalk6;
	            break;
	        case 7:
	            this.img = Formatting.SWLWalk7;
	            break;
	        case 8:
	            this.img = Formatting.SWLWalk8;
	            break;
	        default:
	            break;
	    }
	}


//	Animation for hitting opponent
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
	    if (getHit() && checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.SWRHit1;
	                break;
	            default:
	                this.img = Formatting.SWLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
	        this.hit = false;
	        // Decreases health
	        this.setHealth(attacker.getAttackPoints());
	        System.out.println("Player Health Remaining: " + this.health);
	    } else {
	        if (this.getDirection() == 1) {
	            this.img = Formatting.SWRIdle1;
	        } else {
	            this.img = Formatting.SWLIdle1;
	        }
	    }
	}

}
