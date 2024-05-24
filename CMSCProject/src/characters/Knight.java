package characters;

import java.util.ArrayList;

import elements.Formatting;
import monsters.Monster;

// Knight Character
public class Knight extends Sprite{
	
//	Attributes for animation
	private long previousTimeIdle,previousTimeAttack,previousTimeDie,previousTimeRWalk,previousTimeLWalk;
	public int animationCountIdle,animationCountAttack,animationCountDie,animationCountWalk;
	
// 	Constructor
	public Knight(int x, int y, int playerNumber, long previousTime){
		super(Formatting.KNIGHT, playerNumber, x,y, 
				100, 15, 2,
				0.42, 0.18, 
				0.20, 0.77,
				0.7, 0.05,
				0.11,
				0.27, 0.95);
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
//		Load knight Image
		this.loadImage(Formatting.KnightRIdle1, 84, 44);

	}
	
//	**********
//  inadjust ko din yung sa animation ng walking imbis na 2 ginawa kong based sa speed
//	**********
	
//	Display images per frames per second
	public void animation (long currentTime, Sprite player2, ArrayList<Monster> monsterArrayList) {
//		Idle Animation
		if(currentTime - this.previousTimeIdle >= (250 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
			if (this.getDX() == 0 && this.getDY()== 0) {
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
<<<<<<< HEAD
			if (this.getDX() == this.getSpeed()) {
=======
			if (this.getDX() >= 2) {
>>>>>>> main
				this.animationCountWalk ++;
				this.walkRight();
				this.setDirection(1);
				previousTimeRWalk = currentTime;
			}
		}
		
		// Animation Left Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
<<<<<<< HEAD
			if (this.getDX() == -this.getSpeed()) {
=======
			if (this.getDX() <= -2) {
>>>>>>> main
				this.animationCountWalk ++;
				this.walkLeft();
				this.setDirection(2);
				previousTimeLWalk = currentTime;
			}
		}
		
		// Animation Right Down Walk
		if(currentTime - this.previousTimeLWalk >= (125 * 1000000) && this.attack == false && this.getHit() == false && this.checkAlive() == true) {
<<<<<<< HEAD
			if (this.getDY() == this.getSpeed()) {
=======
			if (this.getDY() >= 2) {
>>>>>>> main
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
<<<<<<< HEAD
			if (this.getDY() == -this.getSpeed()) {
=======
			if (this.getDY() <= -2) {
>>>>>>> main
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
	            case 2:
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
	            this.img = Formatting.KnightRIdle1;
	            break;
	        case 2:
	            this.img = Formatting.KnightRIdle2;
	            break;
	        case 3:
	            this.img = Formatting.KnightRIdle3;
	            break;
	        case 4:
	            this.img = Formatting.KnightRIdle4;
	            break;
	    }
	}

	public void idleLeft() {
	    this.animationCountIdle %= 5;
	    switch (animationCountIdle) {
	        case 1:
	            this.img = Formatting.KnightLIdle1;
	            break;
	        case 2:
	            this.img = Formatting.KnightLIdle2;
	            break;
	        case 3:
	            this.img = Formatting.KnightLIdle3;
	            break;
	        case 4:
	            this.img = Formatting.KnightLIdle4;
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
	        this.animationCountAttack %= 6;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.KnightRAttack1;
	                break;
	            case 2:
	                this.img = Formatting.KnightRAttack2;
	                break;
	            case 3:
	                this.img = Formatting.KnightRAttack3;
	                // Checks if the weapon (attackbox) and other character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
	                }
	                break;
	            case 4:
	                this.img = Formatting.KnightRAttack4;
	                break;
	            case 5:
//	            	Last animation, set everything to false for reset
	                this.img = Formatting.KnightRAttack5;
	                System.out.println("Attack Animation Finished");
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
	    this.setDY(0);
	    this.setDX(0);
//	    Run animation
	    if (currentTime - this.previousTimeAttack >= (142 * 1000000)) {
	        this.animationCountAttack++;
	        this.animationCountAttack %= 6;
	        switch (animationCountAttack) {
	            case 1:
	                this.img = Formatting.KnightLAttack1;
	                break;
	            case 2:
	                this.img = Formatting.KnightLAttack2;
	                break;
	            case 3:
	                this.img = Formatting.KnightLAttack3;
	                // Checks if the weapon (attackbox) and character collides
	                if (this.getCollisionChecker() == false && player2.checkAlive()) {
	                    this.checkCollision(this, player2, currentTime, player2.getDirection(), monsterArrayList);
	                }
	                break;
	            case 4:
	                this.img = Formatting.KnightLAttack4;
	                break;
	            case 5:
	                this.img = Formatting.KnightLAttack5;
//	            	Last animation, set everything to false for reset
	                System.out.println("Attack Animation Finished");
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
	    if (currentTime - this.previousTimeDie >= (1000 * 1000000)) {
	        this.animationCountDie++;
	        this.animationCountDie %= 5;
//	        Run animation depending on its direction
	        switch (this.getDirection()) {
	            case 1:
	                switch (animationCountDie) {
	                    case 1:
	                        this.img = Formatting.KnightRDie1;
	                        break;
	                    case 2:
	                        this.img = Formatting.KnightRDie2;
	                        break;
	                    case 3:
	                        this.img = Formatting.KnightRDie3;
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
	                        this.img = Formatting.KnightLDie1;
	                        break;
	                    case 2:
	                        this.img = Formatting.KnightLDie2;
	                        break;
	                    case 3:
	                        this.img = Formatting.KnightLDie3;
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
	            this.img = Formatting.KnightRWalk1;
	            break;
	        case 2:
	            this.img = Formatting.KnightRWalk2;
	            break;
	        case 3:
	            this.img = Formatting.KnightRWalk3;
	            break;
	        case 4:
	            this.img = Formatting.KnightRWalk4;
	            break;
	        case 5:
	            this.img = Formatting.KnightRWalk5;
	            break;
	        case 6:
	            this.img = Formatting.KnightRWalk6;
	            break;
	        case 7:
	            this.img = Formatting.KnightRWalk7;
	            break;
	        case 8:
	            this.img = Formatting.KnightRWalk8;
	            break;
	    }
	}

	
//	Frames for walking to left
	public void walkLeft() {
	    this.animationCountWalk %= 9;
	    switch (animationCountWalk) {
	        case 1:
	            this.img = Formatting.KnightLWalk1;
	            break;
	        case 2:
	            this.img = Formatting.KnightLWalk2;
	            break;
	        case 3:
	            this.img = Formatting.KnightLWalk3;
	            break;
	        case 4:
	            this.img = Formatting.KnightLWalk4;
	            break;
	        case 5:
	            this.img = Formatting.KnightLWalk5;
	            break;
	        case 6:
	            this.img = Formatting.KnightLWalk6;
	            break;
	        case 7:
	            this.img = Formatting.KnightLWalk7;
	            break;
	        case 8:
	            this.img = Formatting.KnightLWalk8;
	            break;
	    }
	}

	
//	Animation when hit
	public void hitAnimation(long currentTime, Sprite attacker, int direction) {
//		Animation when hit (left and right)
	    if (getHit() == true && this.checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.KnightRHit1;
	                break;
	            default:
	                this.img = Formatting.KnightLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
            this.hit = false;
            // Decreases health
            this.setHealth(this.health - attacker.getAttackPoints());
            System.out.println("Player Health Remaining: " + this.health);
//      If not hit, show idle animation
	    } else if (this.getDirection() == 1) {
	        this.img = Formatting.KnightRIdle1;
	    } else {
	        this.img = Formatting.KnightLIdle1;
	    }
	}
	
	
//	Animation when hit by monster
	public void hitAnimationMonster(long currentTime, Monster monster, int direction) {
//		Animation when hit (left and right)
	    if (getHit() == true && this.checkAlive()) {
	        switch (direction) {
	            case 1:
	                this.img = Formatting.KnightRHit1;
	                break;
	            default:
	                this.img = Formatting.KnightLHit1;
	                break;
	        }
	        System.out.println("Hit Animation Finished");
            this.hit = false;
            // Decreases health
            this.setHealth(this.health - monster.getReward());
            System.out.println("Player Health Remaining: " + this.health);
//      If not hit, show idle animation
	    } else if (this.getDirection() == 1) {
	        this.img = Formatting.KnightRIdle1;
	    } else {
	        this.img = Formatting.KnightLIdle1;
	    }
	}


	

	
}

