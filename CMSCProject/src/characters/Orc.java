package characters;

import java.util.ArrayList;

import elements.Formatting;
import monsters.Monster;

public class Orc extends Sprite{
	
//	Attributes for animation
	private long previousTimeIdle,previousTimeLWalk, previousTimeRWalk, previousTimeDie, previousTimeAttack;
	public int animationCountIdle,animationCountAttack,animationCountDie,animationCountWalk;
	
// 	Constructor
	public Orc(int x, int y, int playerNumber, long previousTime){
		super(Formatting.ORC, playerNumber, x,y,100, 
				0.38, 0.23, 
				0.26, 0.47,
				0.66, 0.17,
				0.036,
				0.3, 0.70);
//		Variables for animation
		this.previousTimeIdle = previousTime;	
		this.previousTimeAttack = previousTime;
		this.previousTimeDie = previousTime;
		this.previousTimeRWalk = previousTime;
		this.previousTimeLWalk = previousTime;
		this.animationCountAttack = 1;
		this.animationCountIdle = 1;
		this.animationCountDie = 1;
		this.animationCountWalk = 1;
//		Load Orc Image
		this.loadImage(Formatting.OrcRIdle1, 73, 63);
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
		if (this.getAttack() && checkAlive()) {
		    switch (this.getDirection()) {
		        case 1:
		            this.attackRightAnimation(currentTime, player2, monsterArrayList);
		            this.previousTimeDie = currentTime;
		            break;
		        default:
		            this.attackLeftAnimation(currentTime, player2, monsterArrayList);
		            this.previousTimeDie = currentTime;
		            break;
		    }
		}
		}


//	Image frames for Idle
	public void idleRight() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.OrcRIdle1;
	            break;
	        case 2:
	            this.img = Formatting.OrcRIdle2;
	            break;
	        case 3:
	            this.img = Formatting.OrcRIdle3;
	            break;
	        case 4:
	            this.img = Formatting.OrcRIdle4;
	            break;
	    }
	}

	public void idleLeft() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.OrcLIdle1;
	            break;
	        case 2:
	            this.img = Formatting.OrcLIdle2;
	            break;
	        case 3:
	            this.img = Formatting.OrcLIdle3;
	            break;
	        case 4:
	            this.img = Formatting.OrcLIdle4;
	            break;
	    }
	}

	
	public void attackRightAnimation(long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
//		Setting dx and dy to zero to stop the character from moving
		this.setDX(0);
	    this.setDY(0);
//	    Run animation
	    if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 8;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.OrcRAttack1;
	                break;
	            case 2:
	                this.img = Formatting.OrcRAttack2;
	                break;
	            case 3:
	                this.img = Formatting.OrcRAttack3;
	                break;
	            case 4:
	                this.img = Formatting.OrcRAttack4;
	                break;
	            case 5:
	                this.img = Formatting.OrcRAttack5;
	                // Checks if the weapon (attackbox) and other character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
	                }
	                break;
	            case 6:
	                this.img = Formatting.OrcRAttack6;
	                break;
	            case 7:
	                this.img = Formatting.OrcRAttack7;
	                break;
	            case 0:
	                System.out.println("Attack Animation Finished");
//	            	Last animation, set everything to false for reset
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	        }
	        this.previousTimeAttack = currentTime;
	    }
	}

//  Image frames for attacking (left) 
	public void attackLeftAnimation(long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
//		Setting dx and dy to zero to stop the character from moving
		this.setDX(0);
	    this.setDY(0);
//	    Run animation
	    if(currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 8;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.OrcLAttack1;
	                break;
	            case 2:
	                this.img = Formatting.OrcLAttack2;
	                break;
	            case 3:
	                this.img = Formatting.OrcLAttack3;
	                break;
	            case 4:
	                this.img = Formatting.OrcLAttack4;
	                break;
	            case 5:
	                this.img = Formatting.OrcLAttack5;
	                // Checks if the weapon (attackbox) and character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
	                }
	                break;
	            case 6:
	                this.img = Formatting.OrcLAttack6;
	                break;
	            case 7:
	                this.img = Formatting.OrcLAttack7;
	                break;
	            case 0:
	                System.out.println("Attack Animation Finished");
//	            	Last animation, set everything to false for reset
	                this.attack = false;
	                this.setCollisionChecker(false);
	                break;
	        }
	        this.previousTimeAttack = currentTime;
	    }
	}

	
//	Dying animation
	public boolean dieAnimation(long currentTime) {
//		Run animation
	    if(currentTime - this.previousTimeDie >= (1000 * 1000000)) {
	        this.animationCountDie++;
	        this.animationCountDie %= 6;
//	        Run animation depending on its direction
	        switch (this.getDirection()) {
	            case 1:
	                switch (animationCountDie) {
	                    case 1:
	                        this.img = Formatting.OrcRDie1;
	                        break;
	                    case 2:
	                        this.img = Formatting.OrcRDie2;
	                        break;
	                    case 3:
	                        this.img = Formatting.OrcRDie3;
	                        break;
	                    case 4:
	                        System.out.println("Dying Animation Finished");
//	                        Set the character to invisible
	                        this.setVisible(false);
	                        return true;
	                }
	                break;
	            default:
	                switch (animationCountDie) {
	                    case 1:
	                        this.img = Formatting.OrcLDie1;
	                        break;
	                    case 2:
	                        this.img = Formatting.OrcLDie2;
	                        break;
	                    case 3:
	                        this.img = Formatting.OrcLDie3;
	                        break;
	                    case 4:
	                        System.out.println("Dying Animation Finished");
//	                        Set the character to invisible
	                        this.setVisible(false);
	                        return true;
	                }
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
	            this.img = Formatting.OrcRWalk1;
	            break;
	        case 2:
	            this.img = Formatting.OrcRWalk2;
	            break;
	        case 3:
	            this.img = Formatting.OrcRWalk3;
	            break;
	        case 4:
	            this.img = Formatting.OrcRWalk4;
	            break;
	        case 5:
	            this.img = Formatting.OrcRWalk5;
	            break;
	        case 6:
	            this.img = Formatting.OrcRWalk6;
	            break;
	        case 7:
	            this.img = Formatting.OrcRWalk7;
	            break;
	        case 8:
	            this.img = Formatting.OrcRWalk8;
	            break;
	    }
	}

	
//	Frames for walking to left
	public void walkLeft() {
	    this.animationCountWalk %= 9;
	    switch (animationCountWalk) {
	        case 1:
	            this.img = Formatting.OrcLWalk1;
	            break;
	        case 2:
	            this.img = Formatting.OrcLWalk2;
	            break;
	        case 3:
	            this.img = Formatting.OrcLWalk3;
	            break;
	        case 4:
	            this.img = Formatting.OrcLWalk4;
	            break;
	        case 5:
	            this.img = Formatting.OrcLWalk5;
	            break;
	        case 6:
	            this.img = Formatting.OrcLWalk6;
	            break;
	        case 7:
	            this.img = Formatting.OrcLWalk7;
	            break;
	        case 8:
	            this.img = Formatting.OrcLWalk8;
	            break;
	    }
	}

	
//	Animation when hit
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
//		Animation when hit (left and right)
	    if (getHit() == true && this.checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.OrcRHit1;
	                break;
	            default:
	                this.img = Formatting.OrcLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
            this.hit = false;
            // Decreases health
            this.setHealth(this.health - attacker.getAttackPoints());
            System.out.println("Player Health Remaining: " + this.health);
//      If not hit, show idle animation
	    } else if (this.getDirection() == 1) {
	        this.img = Formatting.OrcRIdle1;
	    } else {
	        this.img = Formatting.OrcLIdle1;
	    }
	}

//	Animation when hit by monster
	public void hitAnimationMonster(long currentTime, Monster monster, int direction) {
//		Animation when hit (left and right)
	    if (getHit() == true && this.checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.OrcRHit1;
	                break;
	            default:
	                this.img = Formatting.OrcLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
            this.hit = false;
            // Decreases health
            this.setHealth(this.health - monster.getReward());
            System.out.println("Player Health Remaining: " + this.health);
//      If not hit, show idle animation
	    } else if (this.getDirection() == 1) {
	        this.img = Formatting.OrcRIdle1;
	    } else {
	        this.img = Formatting.OrcLIdle1;
	    }
	}

}
