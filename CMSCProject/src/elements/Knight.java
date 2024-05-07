package elements;

import java.util.Random;

import javafx.scene.image.Image;

public class Knight extends Sprite{


	public Knight(int x, int y){
		super(x,y);
//		this.loadImage(Formatting.PIXEL);
		this.loadImage(Formatting.CHARACTER_R);
		
	}
	public void moveLeft () {
		this.img = Formatting.CHARACTER_L;
	}
	
	public void moveRight () {
		this.img = Formatting.CHARACTER_R;
	}
	public void move() {
		if (this.y + this.dy + this.height >= 91 && this.y + this.dy + this.height <= 167) {
			if (this.x + this.dx >= 343 && this.x + this.dx + this.width <= 840) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else if (this.y + this.dy + this.height >= 168 && this.y + this.dy + this.height <= 240) {
			if (this.x + this.dx >= 230 && this.x + this.dx + this.width <= 967) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else if (this.y + this.dy + this.height >= 241 && this.y + this.dy + this.height <= 397) {
			if (this.x + this.dx >= 134 && this.x + this.dx + this.width <= 1069) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else if (this.y + this.dy + this.height >= 398 && this.y + this.dy + this.height <= 486) {
			if (this.x + this.dx >= 180 && this.x + this.dx + this.width  <= 1023) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else if (this.y + this.dy + this.height >= 487 && this.y + this.dy + this.height <= 560) {
			if (this.x + this.dx >= 286 && this.x + this.dx + this.width  <= 922) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else if (this.y + this.dy + this.height >= 561 && this.y + this.dy + this.height <= 619) {
			if (this.x + this.dx >= 384 && this.x + this.dx + this.width  <= 816) {
				this.x += this.dx;
			    this.y += this.dy;
			} else {
				System.out.println("Collision");
			}
		} else {
			System.out.println("Collision");
		}
		
	}

}
