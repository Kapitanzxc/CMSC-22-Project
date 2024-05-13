package elements;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
import javafx.scene.text.Font;

public class Formatting {
//	Dimensions
	public static final int MULTIPLIER = 2;
	public static final int SCREEN_WIDTH = 600 * MULTIPLIER;
	public static final int SCREEN_HEIGHT = 360 * MULTIPLIER;
//	Menu and map assets
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
	public static final Image MAP = new Image("file:resources/map2.png",  7000, 2917,true,true);
	public static final Image TOWER = new Image("file:resources/tower.png",  70, 172,true,true);
	public static final Image PIXEL = new Image("file:resources/pixel.jpg", 1, 1,false,false);
//	Health Bars and Timer
	public static final Image KnightRHealthBar = new Image("file:resources/HealthBar/RKnightHealthBar.png", 231, 72,true,true);
	public static final Image KnightLHealthBar = new Image("file:resources/HealthBar/LKnightHealthBar.png", 231, 72,true,true);
	public static final Image SWRHealthBar = new Image("file:resources/HealthBar/RSWHealthBar.png", 231, 72,true,true);
	public static final Image SWLHealthBar = new Image("file:resources/HealthBar/LSWHealthBar.png", 231, 72,true,true);
	public static final Image WizRHealthBar = new Image("file:resources/HealthBar/RWizHealthBar.png", 231, 72,true,true);
	public static final Image WizLHealthBar = new Image("file:resources/HealthBar/LWizHealthBar.png", 231, 72,true,true);
	
	public static final Image TIMER1 = new Image("file:resources/TimerEffect/Timer1.png", 180, 173,true,true);
	public static final Image TIMER2 = new Image("file:resources/TimerEffect/Timer2.png", 180, 173,true,true);
	public static final Image TIMER3 = new Image("file:resources/TimerEffect/Timer3.png", 180, 173,true,true);
	public static final Image TIMER4 = new Image("file:resources/TimerEffect/Timer4.png", 180, 173,true,true);
	public static final Image TIMER5 = new Image("file:resources/TimerEffect/Timer5.png", 180, 173,true,true);
	public static final Image TIMER6 = new Image("file:resources/TimerEffect/Timer6.png", 180, 173,true,true);
	public static final Image TIMER7 = new Image("file:resources/TimerEffect/Timer7.png", 180, 173,true,true);
	public static final Image TIMER8 = new Image("file:resources/TimerEffect/Timer8.png", 180, 173,true,true);
	public static final Image TIMER9 = new Image("file:resources/TimerEffect/Timer9.png", 180, 173,true,true);
	public static final Image TIMER10 = new Image("file:resources/TimerEffect/Timer10.png", 180, 173,true,true);

	
//	Font Styles
	public static final Font FONT_STYLE_26 = Font.loadFont("file:resources/PrStart.ttf", 26);
	public static final Font FONT_STYLE_25 = Font.loadFont("file:resources/Munro.ttf",25);
	public static final Font FONT_STYLE_22 = Font.loadFont("file:resources/Munro.ttf",22);
	
	
	public static final double XPOS_TEXT = SCREEN_WIDTH/2;
	public static final double YPOS_TEXT = 600;
	public static final Color BEIGE = Color.rgb(236,221, 192);
	public static final Color DARKBLUE = Color.rgb(24,20, 37);
	public static final Color BGCOLOR = Color.rgb(38,33, 74);
	
//	Character codes
	public static final int KNIGHT = 1;
	public static final int ORC = 2;
	public static final int SWORDWOMAN = 3;
	public static final int WIZARD = 4;
	
//	Choose character images
	public static final Image P1KNIGHT1 = new Image("file:resources/ChooseCharacter/Player1/P1Knight1.jpg", 7000, 2917,true,true);
	public static final Image P1KNIGHT2 = new Image("file:resources/ChooseCharacter/Player1/P1Knight2.jpg", 7000, 2917,true,true);
	public static final Image P1ORC1 = new Image("file:resources/ChooseCharacter/Player1/P1Orc1.jpg", 7000, 2917,true,true);
	public static final Image P1ORC2 = new Image("file:resources/ChooseCharacter/Player1/P1Orc2.jpg", 7000, 2917,true,true);
	public static final Image P1SWORDWOMAN1 = new Image("file:resources/ChooseCharacter/Player1/P1SW1.jpg", 7000, 2917,true,true);
	public static final Image P1SWORDWOMAN2 = new Image("file:resources/ChooseCharacter/Player1/P1SW2.jpg", 7000, 2917,true,true);
	public static final Image P1WIZARD1 = new Image("file:resources/ChooseCharacter/Player1/P1Wiz1.jpg", 7000, 2917,true,true);
	public static final Image P1WIZARD2 = new Image("file:resources/ChooseCharacter/Player1/P1Wiz2.jpg", 7000, 2917,true,true);
	
	public static final Image P2KNIGHT1 = new Image("file:resources/ChooseCharacter/Player2/P2Knight1.jpg", 7000, 2917,true,true);
	public static final Image P2KNIGHT2 = new Image("file:resources/ChooseCharacter/Player2/P2Knight2.jpg", 7000, 2917,true,true);
	public static final Image P2ORC1 = new Image("file:resources/ChooseCharacter/Player2/P2Orc1.jpg", 7000, 2917,true,true);
	public static final Image P2ORC2 = new Image("file:resources/ChooseCharacter/Player2/P2Orc2.jpg", 7000, 2917,true,true);
	public static final Image P2SWORDWOMAN1 = new Image("file:resources/ChooseCharacter/Player2/P2SW1.jpg", 7000, 2917,true,true);
	public static final Image P2SWORDWOMAN2 = new Image("file:resources/ChooseCharacter/Player2/P2SW2.jpg", 7000, 2917,true,true);
	public static final Image P2WIZARD1 = new Image("file:resources/ChooseCharacter/Player2/P2Wiz1.jpg", 7000, 2917,true,true);
	public static final Image P2WIZARD2 = new Image("file:resources/ChooseCharacter/Player2/P2Wiz2.jpg", 7000, 2917,true,true);
	
//	Characters
	
	public static final double CHARACTER_WIDTH = 55;
	public static final double CHARACTER_HEIGHT = 44;
	public static final double CHARACTER_WIDTHW = 180;
	public static final double CHARACTER_HEIGHTW = 52;
//	Knight
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
	
//	Swordwoman
	public static final Image SWRIdle1 = new Image("file:resources/SwordWoman/Idle/RSWIdle1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRIdle2 = new Image("file:resources/SwordWoman/Idle/RSWIdle2.png",  CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRIdle3 = new Image("file:resources/SwordWoman/Idle/RSWIdle3.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRIdle4 = new Image("file:resources/SwordWoman/Idle/RSWIdle4.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image SWLIdle1 = new Image("file:resources/SwordWoman/Idle/LSWIdle1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLIdle2 = new Image("file:resources/SwordWoman/Idle/LSWIdle2.png",  CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLIdle3 = new Image("file:resources/SwordWoman/Idle/LSWIdle3.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLIdle4 = new Image("file:resources/SwordWoman/Idle/LSWIdle4.png",   CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	
	public static final Image SWRAttack1 = new Image("file:resources/SwordWoman/Attack/RSWAttack1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRAttack2 = new Image("file:resources/SwordWoman/Attack/RSWAttack2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRAttack3 = new Image("file:resources/SwordWoman/Attack/RSWAttack3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRAttack4 = new Image("file:resources/SwordWoman/Attack/RSWAttack4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRAttack5 = new Image("file:resources/SwordWoman/Attack/RSWAttack5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRAttack6 = new Image("file:resources/SwordWoman/Attack/RSWAttack6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	
	public static final Image SWLAttack1 = new Image("file:resources/SwordWoman/Attack/LSWAttack1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLAttack2 = new Image("file:resources/SwordWoman/Attack/LSWAttack2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLAttack3 = new Image("file:resources/SwordWoman/Attack/LSWAttack3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLAttack4 = new Image("file:resources/SwordWoman/Attack/LSWAttack4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLAttack5 = new Image("file:resources/SwordWoman/Attack/LSWAttack5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLAttack6 = new Image("file:resources/SwordWoman/Attack/LSWAttack6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);

	public static final Image SWRHit1 = new Image("file:resources/SwordWoman/Hit/RSWHit.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLHit1 = new Image("file:resources/SwordWoman/Hit/LSWHit.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image SWRDie1 = new Image("file:resources/SwordWoman/Die/RSWDie1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRDie2 = new Image("file:resources/SwordWoman/Die/RSWDie2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRDie3 = new Image("file:resources/SwordWoman/Die/RSWDie3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRDie4 = new Image("file:resources/SwordWoman/Die/RSWDie4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image SWLDie1 = new Image("file:resources/SwordWoman/Die/LSWDie1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLDie2 = new Image("file:resources/SwordWoman/Die/LSWDie2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLDie3 = new Image("file:resources/SwordWoman/Die/LSWDie3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLDie4 = new Image("file:resources/SwordWoman/Die/LSWDie4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	
	public static final Image SWRWalk1 = new Image("file:resources/SwordWoman/Walk/RSWWalk1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk2 = new Image("file:resources/SwordWoman/Walk/RSWWalk2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk3 = new Image("file:resources/SwordWoman/Walk/RSWWalk3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk4 = new Image("file:resources/SwordWoman/Walk/RSWWalk4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk5 = new Image("file:resources/SwordWoman/Walk/RSWWalk5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk6 = new Image("file:resources/SwordWoman/Walk/RSWWalk6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk7 = new Image("file:resources/SwordWoman/Walk/RSWWalk7.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWRWalk8 = new Image("file:resources/SwordWoman/Walk/RSWWalk8.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
	public static final Image SWLWalk1 = new Image("file:resources/SwordWoman/Walk/LSWWalk1.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk2 = new Image("file:resources/SwordWoman/Walk/LSWWalk2.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk3 = new Image("file:resources/SwordWoman/Walk/LSWWalk3.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk4 = new Image("file:resources/SwordWoman/Walk/LSWWalk4.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk5 = new Image("file:resources/SwordWoman/Walk/LSWWalk5.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk6 = new Image("file:resources/SwordWoman/Walk/LSWWalk6.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk7 = new Image("file:resources/SwordWoman/Walk/LSWWalk7.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	public static final Image SWLWalk8 = new Image("file:resources/SwordWoman/Walk/LSWWalk8.png", CHARACTER_WIDTH, CHARACTER_HEIGHT,true,true);
	
//	Wizard
	public static final Image WizRIdle1 = new Image("file:resources/Wizard/Idle/RWizIdle1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRIdle2 = new Image("file:resources/Wizard/Idle/RWizIdle2.png",  CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRIdle3 = new Image("file:resources/Wizard/Idle/RWizIdle3.png",   CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRIdle4 = new Image("file:resources/Wizard/Idle/RWizIdle4.png",   CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	public static final Image WizLIdle1 = new Image("file:resources/Wizard/Idle/LWizIdle1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLIdle2 = new Image("file:resources/Wizard/Idle/LWizIdle2.png",  CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLIdle3 = new Image("file:resources/Wizard/Idle/LWizIdle3.png",   CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLIdle4 = new Image("file:resources/Wizard/Idle/LWizIdle4.png",   CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	
	public static final Image WizRAttack1 = new Image("file:resources/Wizard/Attack/RWizAttack1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack2 = new Image("file:resources/Wizard/Attack/RWizAttack2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack3 = new Image("file:resources/Wizard/Attack/RWizAttack3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack4 = new Image("file:resources/Wizard/Attack/RWizAttack4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack5 = new Image("file:resources/Wizard/Attack/RWizAttack5.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack6 = new Image("file:resources/Wizard/Attack/RWizAttack6.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack7 = new Image("file:resources/Wizard/Attack/RWizAttack7.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack8 = new Image("file:resources/Wizard/Attack/RWizAttack8.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRAttack9 = new Image("file:resources/Wizard/Attack/RWizAttack9.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack1 = new Image("file:resources/Wizard/Attack/LWizAttack1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack2 = new Image("file:resources/Wizard/Attack/LWizAttack2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack3 = new Image("file:resources/Wizard/Attack/LWizAttack3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack4 = new Image("file:resources/Wizard/Attack/LWizAttack4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack5 = new Image("file:resources/Wizard/Attack/LWizAttack5.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack6 = new Image("file:resources/Wizard/Attack/LWizAttack6.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack7 = new Image("file:resources/Wizard/Attack/LWizAttack7.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack8 = new Image("file:resources/Wizard/Attack/LWizAttack8.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLAttack9 = new Image("file:resources/Wizard/Attack/LWizAttack9.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);

	public static final Image WizRHit1 = new Image("file:resources/Wizard/Hit/RWizHit1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLHit1 = new Image("file:resources/Wizard/Hit/LWizHit1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	public static final Image WizRDie1 = new Image("file:resources/Wizard/Die/RWizDie1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRDie2 = new Image("file:resources/Wizard/Die/RWizDie2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRDie3 = new Image("file:resources/Wizard/Die/RWizDie3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRDie4 = new Image("file:resources/Wizard/Die/RWizDie4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	public static final Image WizLDie1 = new Image("file:resources/Wizard/Die/LWizDie1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLDie2 = new Image("file:resources/Wizard/Die/LWizDie2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLDie3 = new Image("file:resources/Wizard/Die/LWizDie3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLDie4 = new Image("file:resources/Wizard/Die/LWizDie4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	
	public static final Image WizRWalk1 = new Image("file:resources/Wizard/Walk/RWizWalk1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk2 = new Image("file:resources/Wizard/Walk/RWizWalk2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk3 = new Image("file:resources/Wizard/Walk/RWizWalk3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk4 = new Image("file:resources/Wizard/Walk/RWizWalk4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk5 = new Image("file:resources/Wizard/Walk/RWizWalk5.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk6 = new Image("file:resources/Wizard/Walk/RWizWalk6.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk7 = new Image("file:resources/Wizard/Walk/RWizWalk7.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizRWalk8 = new Image("file:resources/Wizard/Walk/RWizWalk8.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	
	public static final Image WizLWalk1 = new Image("file:resources/Wizard/Walk/LWizWalk1.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk2 = new Image("file:resources/Wizard/Walk/LWizWalk2.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk3 = new Image("file:resources/Wizard/Walk/LWizWalk3.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk4 = new Image("file:resources/Wizard/Walk/LWizWalk4.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk5 = new Image("file:resources/Wizard/Walk/LWizWalk5.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk6 = new Image("file:resources/Wizard/Walk/LWizWalk6.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk7 = new Image("file:resources/Wizard/Walk/LWizWalk7.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	public static final Image WizLWalk8 = new Image("file:resources/Wizard/Walk/LWizWalk8.png", CHARACTER_WIDTHW, CHARACTER_HEIGHTW,true,true);
	

//	Winner
	public static final Image KNIGHTVICTORY = new Image("file:resources/KnightVictory.jpg",7000, 2917,true,true);
	public static final Image SWVICTORY = new Image("file:resources/SWVictory.jpg",7000, 2917,true,true);

	
	
}