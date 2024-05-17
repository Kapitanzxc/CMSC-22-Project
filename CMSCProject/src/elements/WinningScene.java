package elements;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinningScene {
	private Group root;
	private Stage stage;
	private Scene menuScene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Scene winningScene;

	// Constructor
	public WinningScene(Scene menuScene, Stage stage, int playerWinner) {
		// Initialize variables
		this.stage = stage;
		this.root = new Group();
		this.winningScene = new Scene(this.root,Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
		this.menuScene = menuScene;
		this.canvas = new Canvas(Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		// Draw Background
		if (playerWinner == Formatting.KNIGHT) {
			this.gc.drawImage(Formatting.KNIGHTVICTORY, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		} 
		else if (playerWinner == Formatting.SWORDWOMAN) {
			this.gc.drawImage(Formatting.SWVICTORY, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		}
		else if (playerWinner == Formatting.WIZARD) {
			this.gc.drawImage(Formatting.WIZVICTORY, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		}
		else if (playerWinner == Formatting.ORC) {
			this.gc.drawImage(Formatting.ORCVICTORY, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		} 
//		Draw
		else {
			this.gc.drawImage(Formatting.DRAWBG, 0, 0, Formatting.SCREEN_WIDTH,Formatting.SCREEN_HEIGHT);
		}
		
//		Back to menu text
		Text backToMenu = new Text(Formatting.SCREEN_WIDTH/2 + 250, Formatting.SCREEN_HEIGHT - 50,"Back To Menu");
		backToMenu.setFont(Formatting.FONT_STYLE_26);
		backToMenu.setFill(Color.WHITE);
		backToMenu.setStroke(Color.BLACK);
		this.addMouseEvent(backToMenu);
//		Adding these to the scene
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(backToMenu);
	}
	
	//creates mouse events for texts
	private void addMouseEvent(Text t) {
		//changes color when text is hovered in
		t.setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.DARKBLUE);
			}
		});
		//changes color when text is hovered out
		t.setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setFill(Color.WHITE);
			}
		});
		//changes scene when text is clicked
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// when new game is clicked
				System.out.println("Load New Game Scene");
//				change scene here
				stage.setScene(menuScene);
			}
			
		});
			
	
	}
	
	public Scene getScene() {
		return winningScene;
	}
}
