package elements;

import java.util.Random;

import javafx.scene.image.Image;

public class Knight extends Sprite{
	private String name;
	private int strength;
	private boolean alive;

	private final static int CHARACTER_WIDTH = 200;

	public Knight(int x, int y){
		super(x,y);
		this.loadImage(Formatting.CHARACTER_R);
		
	}
	
	public void moveLeft () {
		this.img = Formatting.CHARACTER_L;
	}
	
	public void moveRight () {
		this.img = Formatting.CHARACTER_R;
	}
	public void move() {
    	this.x += this.dx;
    	this.y += this.dy;
	}

}
