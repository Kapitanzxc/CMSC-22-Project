package characters;

import java.util.ArrayList;

import elements.Formatting;
import monsters.Monster;

public class SwordWoman extends Sprite{
	
//	Attributes for animation
	private long previousTimeIdle,previousTimeAttack,previousTimeDie,previousTimeRWalk,previousTimeLWalk;
	public int animationCountIdle,animationCountAttack,animationCountDie,animationCountWalk;
	
// 	Constructor
	public SwordWoman(int x, int y, int playerNumber, long previousTime){
		super(Formatting.SWORDWOMAN, playerNumber, x,y, 100,  
				0.42, 0.20, 
				0.22, 0.7,
				0.7, 0.25, 
				0.08, 
				0.3, 0.61);
//		Variables for animation
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 0;
		this.animationCountWalk = 1;
//		Load Sword Woman Image
		this.loadImage(Formatting.SWRIdle1, 84, 44);
	}
	
//	Display images per frames per second
	public void animation (long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
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
				this.attackRightAnimation(currentTime, player2, monsterArrayList);
				this.previousTimeDie = currentTime;
			} else {
				this.attackLeftAnimation(currentTime, player2, monsterArrayList);
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

//  Image frames for attacking (right)
	public void attackRightAnimation(long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
//		Setting dx and dy to zero to stop the character from moving
		this.setDX(0);
	    this.setDY(0);
//	    Run animation
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
	                // Checks if the weapon (attackbox) and other character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
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
//	            	Last animation, set everything to false for reset
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

//  Image frames for attacking (left) 
	public void attackLeftAnimation(long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
//		Setting dx and dy to zero to stop the character from moving
	    this.setDY(0);
	    this.setDX(0);
//	    Run animation
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
	             // Checks if the weapon (attackbox) and character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
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
//	            	Last animation, set everything to false for reset
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
//		Run animation
	    if (currentTime - this.previousTimeDie >= (1000 * 1000000)) {
	        this.animationCountDie++;
	        this.animationCountDie %= 6;
	        if (this.getDirection() == 1) {
//		        Run animation depending on its direction
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
	                    this.img = Formatting.SWRDie4;
	                    break;
	                case 5:
	                    System.out.println("Dying Animation Finished");
//                       Set the character to invisible
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
	                    this.img = Formatting.SWLDie4;
	                    break;
	                case 5:
	                    System.out.println("Dying Animation Finished");
//                      Set the character to invisible
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
	
	

//	Animation when hit
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
//		Animation when hit (left and right)
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
	        this.setHealth(this.health - attacker.getAttackPoints());
	        System.out.println("Player Health Remaining: " + this.health);
//	    If not hit, show idle animation
	    } else {
	        if (this.getDirection() == 1) {
	            this.img = Formatting.SWRIdle1;
	        } else {
	            this.img = Formatting.SWLIdle1;
	        }
	    }
	}
	
//	Animation for when hit by monster
	public void hitAnimationMonster(long currentTime, Monster monster,  int direction) {
//		Animation when hit (left and right)
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
	        this.setHealth(this.health -  monster.getReward());
	        System.out.println("Player Health Remaining: " + this.health);
//	    If not hit, show idle animation
	    } else {
	        if (this.getDirection() == 1) {
	            this.img = Formatting.SWRIdle1;
	        } else {
	            this.img = Formatting.SWLIdle1;
	        }
	    }
	}

}
