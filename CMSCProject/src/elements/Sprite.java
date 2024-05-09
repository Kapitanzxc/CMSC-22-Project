package elements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class Sprite {
	protected Image img;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected double width;
	protected double height;
	
	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}
	
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}
	
	//method to set the image to the image view node
	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
        
    }
	
	//method to set the object's width and height properties
	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}
	
	//method to return the image
	Image getImage(){
		return this.img;
	}
	
	public void move() {
		if (this.y + this.dy + this.height >= 91 && this.y + this.dy + this.height <= 167) {
			if (this.x + this.dx >= 343 && this.x + this.dx + this.width <= 870) {
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
	
	private void stopCharacter(KeyCode ke){
		this.setDX(0);
		this.setDY(0);
	}
	
	//getters
	public int getX() {
    	return this.x;
	}

	public int getY() {
    	return this.y;
	}
	
	public int getDX(){
		return this.dx;
	}
	public int getDY(){
		return this.dy;
	}
	
	public boolean getVisible(){
		return visible;	
	}
	public boolean isVisible(){
		if(visible) return true;
		return false;
	}
	
	//setters
	public void setDX(int dx){
		this.dx = dx;
	}
	
	public void setDY(int dy){
		this.dy = dy;
	}
	
	public void setWidth(double val){
		this.width = val;
	}

	public void setHeight(double val){
		this.height = val;
	}
		
	public void setVisible(boolean value){
		this.visible = value;
	}
}
