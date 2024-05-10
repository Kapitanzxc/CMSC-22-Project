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
	
	public static final Image KnightRIdle1 = new Image("file:resources/Knight/Idle/RKnightIdle1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRIdle2 = new Image("file:resources/Knight/Idle/RKnightIdle2.png",  CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRIdle3 = new Image("file:resources/Knight/Idle/RKnightIdle3.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRIdle4 = new Image("file:resources/Knight/Idle/RKnightIdle4.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightLIdle1 = new Image("file:resources/Knight/Idle/LKnightIdle1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLIdle2 = new Image("file:resources/Knight/Idle/LKnightIdle2.png",  CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLIdle3 = new Image("file:resources/Knight/Idle/LKnightIdle3.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLIdle4 = new Image("file:resources/Knight/Idle/LKnightIdle4.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	
	public static final Image KnightRAttack1 = new Image("file:resources/Knight/Attack/RKnightAttack1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRAttack2 = new Image("file:resources/Knight/Attack/RKnightAttack2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRAttack3 = new Image("file:resources/Knight/Attack/RKnightAttack3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRAttack4 = new Image("file:resources/Knight/Attack/RKnightAttack4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRAttack5 = new Image("file:resources/Knight/Attack/RKnightAttack5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightLAttack1 = new Image("file:resources/Knight/Attack/LKnightAttack1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLAttack2 = new Image("file:resources/Knight/Attack/LKnightAttack2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLAttack3 = new Image("file:resources/Knight/Attack/LKnightAttack3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLAttack4 = new Image("file:resources/Knight/Attack/LKnightAttack4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLAttack5 = new Image("file:resources/Knight/Attack/LKnightAttack5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	public static final Image KnightRHit1 = new Image("file:resources/Knight/Hit/RKnightHit.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLHit1 = new Image("file:resources/Knight/Hit/LKnightHit.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	
	public static final Image KnightRDie1 = new Image("file:resources/Knight/Die/RKnightDie1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRDie2 = new Image("file:resources/Knight/Die/RKnightDie2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRDie3 = new Image("file:resources/Knight/Die/RKnightDie3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightLDie1 = new Image("file:resources/Knight/Die/LKnightDie1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLDie2 = new Image("file:resources/Knight/Die/LKnightDie2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLDie3 = new Image("file:resources/Knight/Die/LKnightDie3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	
	public static final Image KnightRWalk1 = new Image("file:resources/Knight/Walk/RKnightWalk1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk2 = new Image("file:resources/Knight/Walk/RKnightWalk2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk3 = new Image("file:resources/Knight/Walk/RKnightWalk3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk4 = new Image("file:resources/Knight/Walk/RKnightWalk4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk5 = new Image("file:resources/Knight/Walk/RKnightWalk5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk6 = new Image("file:resources/Knight/Walk/RKnightWalk6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk7 = new Image("file:resources/Knight/Walk/RKnightWalk7.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightRWalk8 = new Image("file:resources/Knight/Walk/RKnightWalk8.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image KnightLWalk1 = new Image("file:resources/Knight/Walk/LKnightWalk1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk2 = new Image("file:resources/Knight/Walk/LKnightWalk2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk3 = new Image("file:resources/Knight/Walk/LKnightWalk3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk4 = new Image("file:resources/Knight/Walk/LKnightWalk4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk5 = new Image("file:resources/Knight/Walk/LKnightWalk5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk6 = new Image("file:resources/Knight/Walk/LKnightWalk6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk7 = new Image("file:resources/Knight/Walk/LKnightWalk7.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image KnightLWalk8 = new Image("file:resources/Knight/Walk/LKnightWalk8.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	
	
	
}