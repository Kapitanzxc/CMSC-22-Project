package elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WinningScene {
    private Group root;
    private Stage stage;
    private Scene menuScene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Scene winningScene;
    private int playerWinNum, playerWinner;
	private Sprite player1,player2;

    // Constructor
    public WinningScene(Scene menuScene, Stage stage, int playerWinner, int playerWinNum, Sprite player1, Sprite player2) {
        // Initialize variables
        this.stage = stage;
        this.root = new Group();
        this.player1 = player1;
        this.player2 = player2;
        this.winningScene = new Scene(this.root, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        this.menuScene = menuScene;
        this.canvas = new Canvas(Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.playerWinNum = playerWinNum;
        this.playerWinner = playerWinner;

        victoryBackground();
        // Back to menu text
        Text backToMenu = new Text(Formatting.SCREEN_WIDTH / 2 + 250, Formatting.SCREEN_HEIGHT - 50, "Back To Menu");
        backToMenu.setFont(Formatting.FONT_STYLE_26);
        backToMenu.setFill(Color.WHITE);
        backToMenu.setStroke(Color.BLACK);
        this.addMouseEvent(backToMenu);

        // Adding these to the scene
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(backToMenu);

        // Set the scene on the stage
        this.stage.setScene(this.winningScene);

        // Start the summary
        summary(this.playerWinNum, this.player1, this.player2);
    }

    public void victoryBackground() {
        // Draw Background
        if (this.playerWinner == Formatting.KNIGHT) {
            this.gc.drawImage(Formatting.KNIGHTVICTORY, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        } else if (this.playerWinner == Formatting.SWORDWOMAN) {
            this.gc.drawImage(Formatting.SWVICTORY, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        } else if (this.playerWinner == Formatting.WIZARD) {
            this.gc.drawImage(Formatting.WIZVICTORY, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        } else if (this.playerWinner == Formatting.ORC) {
            this.gc.drawImage(Formatting.ORCVICTORY, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        } else {
            this.gc.drawImage(Formatting.DRAWBG, 0, 0, Formatting.SCREEN_WIDTH, Formatting.SCREEN_HEIGHT);
        }
    }
    
    // https://stackoverflow.com/questions/58775873/how-to-use-timeline-to-perform-a-void-method-every-x-seconds-on-javafx 
    public void summary(int playerWinNum, Sprite player1, Sprite player2) {
    	
        Timeline timeline = new Timeline();
        
        // Display text after 2 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Text playerWins;
                if (playerWinNum == 1) {
                    playerWins = new Text(514, 270, "Player 1 Wins!");  
                } 
                else if (playerWinNum == 2) {
                	playerWins = new Text(514, 270, "Player 2 Wins!");
                } 
                else {
                	playerWins = new Text(514, 270, "");
                }
                
		       	playerWins.setFont(Formatting.FONT_STYLE_28);
		        playerWins.setFill(Color.WHITE);
		        root.getChildren().add(playerWins);
            }
        }));

        
        // Show rectangles after 4 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	// Rectangle coordinates and size
                double x = 280;
                double x2 = 645;
                double y = 310;
                double width = 250;
                double height = 280;

                switch (playerWinner) {
                    case 1:
                        gc.setStroke(Color.RED);
                        break;
                    case 2:
                        gc.setStroke(Color.GREEN);
                        break;
                    case 3:
                        gc.setStroke(Color.PINK);
                        break;
                    case 4:
                        gc.setStroke(Color.PURPLE);
                        break;
                    case 0:
                    	 gc.setStroke(Color.GRAY);
                         break;
                }
                // Opacity: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
//              Draw rectangles
                Color fillColor = Color.rgb(0, 0, 0, 0.5); 
                gc.setFill(fillColor);
                gc.setLineWidth(1);
                gc.fillRect(x, y, width, height);
                gc.fillRect(x2, y, width, height);
                if (playerWinNum == 1) {
            	   gc.strokeRect(x, y, width, height);
                   gc.setStroke(Color.GRAY);
                   gc.strokeRect(x2, y, width, height);
                } else {
            	 gc.strokeRect(x2, y, width, height);
                 gc.setStroke(Color.GRAY);
                 gc.strokeRect(x, y, width, height);
                }
                
//              Display text
                Text player1text = new Text(370, 340, "Player 1"); 
                Text player2text = new Text(740, 340, "Player 2"); 
                player1text.setFont(Formatting.FONT_STYLE_22);
                player2text.setFont(Formatting.FONT_STYLE_22);
                player1text.setFill(Color.WHITE);
                player2text.setFill(Color.WHITE);
		        root.getChildren().addAll(player1text, player2text);	
             
            }
        }));
        
     // Show heart icon after 5 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
				
                double x = 290;
                double x2 = 655;
                double y = 360;
                
                gc.drawImage(Formatting.HEART, x, y);
                gc.drawImage(Formatting.HEART, x2, y);
                
//              Display text
                
//              Set to zero if health is less than 0
                if (player1.health < 0) {
                	player1.health = 0;
                }
                
                if (player2.health < 0) {
                	player2.health = 0;
                }
                
                Text player1health = new Text(x + 30, y + 18, "Remaining Health: " + player1.health); 
                Text player2health = new Text(x2 + 30, y + 18, "Remaining Health: " + player2.health); 
                player1health.setFont(Formatting.FONT_STYLE_20);
                player2health.setFont(Formatting.FONT_STYLE_20);
                player1health.setFill(Color.WHITE);
                player2health.setFill(Color.WHITE);
		        root.getChildren().addAll(player1health, player2health);

            }
        }));
//      Show attack points after 6 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {

                double x = 290;
                double x2 = 655;
                double y = 400;
                
                gc.drawImage(Formatting.SWORD, x, y);
                gc.drawImage(Formatting.SWORD, x2, y);
                
//              Display text

                Text player1points = new Text(x + 30, y + 18, "Attack points: " + player1.attackPoints); 
                Text player2points = new Text(x2 + 30, y + 18, "Attack points: " + player2.attackPoints); 
                player1points.setFont(Formatting.FONT_STYLE_20);
                player2points.setFont(Formatting.FONT_STYLE_20);
                player1points.setFill(Color.WHITE);
                player2points.setFill(Color.WHITE);
		        root.getChildren().addAll(player1points, player2points);

            }
        }));
        
//      Show fragments collected after 7 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {

                double x = 290;
                double x2 = 655;
                double y = 440;
                
                gc.drawImage(Formatting.HEART, x, y);
                gc.drawImage(Formatting.HEART, x2, y);
                
//                Display text
                Text player1frag = new Text(x + 30, y + 18, "Fragments collected: " + player1.fragmentsCollected); 
                Text player2frag = new Text(x2 + 30, y + 18, "Fragments collected: " + player2.fragmentsCollected); 
                player1frag.setFont(Formatting.FONT_STYLE_20);
                player2frag.setFont(Formatting.FONT_STYLE_20);
                player1frag.setFill(Color.WHITE);
                player2frag.setFill(Color.WHITE);
		        root.getChildren().addAll(player1frag, player2frag);

            }
        }));
        
//      Show powerups collected after 8 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(6), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	
                double x = 290;
                double x2 = 655;
                double y = 480;
                
                gc.drawImage(Formatting.HEART, x, y);
                gc.drawImage(Formatting.HEART, x2, y);
                
//                Display text
                Text player1frag = new Text(x + 30, y + 18, "Power Ups Collected: " + player1.specialCollected); 
                Text player2frag = new Text(x2 + 30, y + 18, "Power Ups Collected: " + player2.specialCollected); 
                player1frag.setFont(Formatting.FONT_STYLE_20);
                player2frag.setFont(Formatting.FONT_STYLE_20);
                player1frag.setFill(Color.WHITE);
                player2frag.setFill(Color.WHITE);
		        root.getChildren().addAll(player1frag, player2frag);

            }
        }));
        
//      Show monster killed after 9 seconds
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(7), new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	// Rectangle coordinates and size
                double x = 290;
                double x2 = 655;
                double y = 520;
                
                gc.drawImage(Formatting.MONSTER, x, y);
                gc.drawImage(Formatting.MONSTER, x2, y);
                
//                Display text
                Text player1frag = new Text(x + 30, y + 18, "Monsters killed: " + player1.monstersKilled); 
                Text player2frag = new Text(x2 + 30, y + 18, "Monsters killed: " + player2.monstersKilled); 
                player1frag.setFont(Formatting.FONT_STYLE_20);
                player2frag.setFont(Formatting.FONT_STYLE_20);
                player1frag.setFill(Color.WHITE);
                player2frag.setFill(Color.WHITE);
		        root.getChildren().addAll(player1frag, player2frag);

            }
        }));

        timeline.play();
    }

    // Creates mouse events for texts
    private void addMouseEvent(Text t) {
        // Changes color when text is hovered in
        t.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                t.setFill(Color.DARKBLUE);
            }
        });
        // Changes color when text is hovered out
        t.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                t.setFill(Color.WHITE);
            }
        });
        // Changes scene when text is clicked
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // When new game is clicked
                System.out.println("Load New Game Scene");
                // Change scene here
                stage.setScene(menuScene);
            }
        });
    }

    public Scene getScene() {
        return winningScene;
    }
}
