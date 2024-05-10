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
	public static final Image CHARACTER_R = new Image("file:resources/characterRight.png",  35, 35,true,true);
	public static final Image MAP = new Image("file:resources/map.jpg",  7000, 2917,true,true);
	public static final Image PIXEL = new Image("file:resources/pixel.jpg", 1, 1,false,false);
//	Font Styles
	public static final Font FONT_STYLE_26 = Font.loadFont("file:resources/PrStart.ttf", 26);
	public static final double XPOS_TEXT = SCREEN_WIDTH/2;
	public static final double YPOS_TEXT = 600;
//	Characters
	
	public static final double CHARACTER_WIDTH = 55;
	public static final double CHARACTER_HEIGHT = 55;
	
	public static final Image Knight = new Image("file:resources/Knight/Idle/RKnightIdle1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image Knight2 = new Image("file:resources/Knight/Idle/RKnightIdle2.png",  CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image Knight3 = new Image("file:resources/Knight/Idle/RKnightIdle3.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image Knight4 = new Image("file:resources/Knight/Idle/RKnightIdle4.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightAttack1 = new Image("file:resources/Knight/Attack/RKnightAttack1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightAttack2 = new Image("file:resources/Knight/Attack/RKnightAttack2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightAttack3 = new Image("file:resources/Knight/Attack/RKnightAttack3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightAttack4 = new Image("file:resources/Knight/Attack/RKnightAttack4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightAttack5 = new Image("file:resources/Knight/Attack/RKnightAttack5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	public static final Image KnightHit1 = new Image("file:resources/Knight/Hit/RKnightHit.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightHit2 = new Image("file:resources/Knight/Hit/RKnightHit2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightHit3 = new Image("file:resources/Knight/Hit/RKnightHit3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightHit4 = new Image("file:resources/Knight/Hit/RKnightHit4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightHit5 = new Image("file:resources/Knight/Hit/RKnightHit5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightDie1 = new Image("file:resources/Knight/Die/RKnightDie1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightDie2 = new Image("file:resources/Knight/Die/RKnightDie2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightDie3 = new Image("file:resources/Knight/Die/RKnightDie3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	
	
}