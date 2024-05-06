package elements;

import javafx.scene.image.Image;
//import javafx.scene.text.Font;
import javafx.scene.text.Font;

public class Formatting {
//	Dimensions
	public static final int MULTIPLIER = 2;
	public static final int SCREEN_WIDTH = 600 * MULTIPLIER;
	public static final int SCREEN_HEIGHT = 360 * MULTIPLIER;
//	Menu Assets
	public static final Image BACKGROUND = new Image("file:resources/background.jpg", 7000, 2917,true,true);
	public static final Image ABOUT = new Image("file:resources/about.jpg", 7000, 2917,true,true);
	public static final Image CONTROLS = new Image("file:resources/controls.jpg", 7000, 2917,true,true);
	public static final Image REFERENCES = new Image("file:resources/references.jpg", 7000, 2917,true,true);
	public static final Image DEVELOPERS = new Image("file:resources/developers.jpg", 7000, 2917,true,true);
	public static final Image HOME_BUTTON = new Image("file:resources/homeButton.png", 39, 40.5,true,true);
	public static final Image RIGHT_BUTTON = new Image("file:resources/rightButton.png", 31, 32,true,true);
	public static final Image LEFT_BUTTON = new Image("file:resources/leftButton.png", 31, 32,true,true);
	public static final Image LEFT_SCROLL = new Image("file:resources/scrollLeft.png", 136.5, 31.5,true,true);
	public static final Image MIDDLE_SCROLL = new Image("file:resources/scrollMiddle.png",  136.5, 31.5,true,true);
	public static final Image RIGHT_SCROLL = new Image("file:resources/scrollRight.png",  136.5, 31.5,true,true);
//	Font Styles
	public static final Font FONT_STYLE_26 = Font.loadFont("file:resources/PrStart.ttf", 26);
	public static final double XPOS_TEXT = SCREEN_WIDTH/2;
	public static final double YPOS_TEXT = 600;
	
}