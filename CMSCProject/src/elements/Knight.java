package elements;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Knight extends Sprite{
	public int animationCountIdle;
	public int animationCountWalking;

	public Knight(int x, int y, int c){
		super(x,y);
		this.animationCountIdle = 1;
		this.loadImage(Formatting.Knight);
	}
	
//	Image frames
	public void idle () {
		this.animationCountIdle %= 4;
		if (animationCountIdle == 1) {
			this.img = Formatting.Knight;
		} 
		else if (animationCountIdle == 2) {
			this.img = Formatting.Knight2;
		}
		else if (animationCountIdle == 3) {
			this.img = Formatting.Knight3;
			}
		else if (animationCountIdle == 4) {
			this.img = Formatting.Knight4;
			}
	}
//	Display images per 250 milliseconds (4 Frames per second)
	public long idleStart(long currentTime, long previousTime) {
		if(currentTime - previousTime >= (250 * 1000000)) {
			if (this.getDX() == 0 && this.getDY()==0) {
				this.animationCountIdle ++;
				this.idle();
				previousTime = currentTime;
				return previousTime;
			}
		}
		return previousTime;
	}
	
}	
