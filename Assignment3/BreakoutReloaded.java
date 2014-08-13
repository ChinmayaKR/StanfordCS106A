/*
 * File: Breakout.java
 * -------------------
 * Name: Chinmaya Ramachandra Kuduvalli	
 * Section Leader: Amy Xu
 * 
 * This program implements the game of Breakout.
 * It involves laying the bricks, and checking for collisions when 
 * the ball is in motion. It is done by creating the ball and paddle 
 * as instance variables, and changing their variables and moving
 * them according to the collision conditions. The game is over when 
 * all lives are spent or when all bricks are cleared. 
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutReloaded extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	@SuppressWarnings("unused")
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 1;
	
	/** The ball required for the game */
	private GOval ball;
	
	/** The paddle required for the game */
	private GRect paddle;

	/** The number of turns to display */
	private GLabel turns;
	
	/** Audio clips */
	private AudioClip brickBreak = MediaTools.loadAudioClip("supermariobrothers-shellkick.au");
	
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce-2.au");

	private AudioClip endGame = MediaTools.loadAudioClip("supermariobrothers-mariodies.au");
	
	private AudioClip loseLife = MediaTools.loadAudioClip("pacman-die.au");
	
	private AudioClip winGame = MediaTools.loadAudioClip("supermariobrothers-stagecleared.au");
	
	
	public void run() {

		int numTurns = NTURNS;		
		int numBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		
		setUpGame();
		startAndPlayGame(numTurns,numBricks);
	}
	
	// Draws the whole Breakout game level.
	private void setUpGame() {
		setUpBlocks();
		setUpBall();
		setUpPaddle();
		displayTurns();
		
		addMouseListeners();
	}
		
	// Draws the blocks according to the dimensions of the window.
	private void setUpBlocks() {
		double xStart=0;
		double midPoint = getWidth()/2;
		int yStart = BRICK_Y_OFFSET;	
		
		for(int i = 0; i < NBRICK_ROWS; i++) {
			
			xStart = midPoint - (NBRICKS_PER_ROW/2 * BRICK_WIDTH) - BRICK_WIDTH/2;
			layRowOfBricks(xStart, yStart, i);
			yStart += BRICK_HEIGHT + BRICK_SEP;
		}	
	}			
	
	// Draws a single row of bricks.
	private void layRowOfBricks(double x_start,int y,int i) {
		
		for(int j = 0; j < NBRICKS_PER_ROW; j++) {
			GRect brick = new GRect(x_start, y, BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			colorBricks(brick,i);
			add(brick);
			x_start += BRICK_WIDTH + BRICK_SEP;
		}
	}
	
	// Colors every 2 consecutive rows of bricks with different color.
	private void colorBricks(GRect block,int k) {

		if (k < 2) {
			block.setColor(Color.RED);
		} else if (k >= 2 && k < 4) {
			block.setColor(Color.ORANGE);
		} else if (k >= 4 && k < 6) {
			block.setColor(Color.YELLOW);
		} else if (k >= 6 && k < 8) {
			block.setColor(Color.GREEN);
		} else if (k >= 8 && k < 10) {
			block.setColor(Color.CYAN);
		}
	}
	
	// Draws the ball.
	private void setUpBall() {

		int xOfBall = getWidth()/2 - BALL_RADIUS;
		int yOfBall = getHeight()/2 - BALL_RADIUS;
		ball = new GOval(xOfBall, yOfBall, BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
	}	

	// Draws the paddle.
	private void setUpPaddle() {
		
		double yOfPaddle = getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
		double xOfPaddle = (getWidth() - PADDLE_WIDTH)/2;
		paddle = new GRect(xOfPaddle, yOfPaddle, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}

	// Displays a label to show the number of turns left.
	private void displayTurns() {
		
		turns = new GLabel("" + NTURNS,4,20);
		turns.setFont("IMPACT-20");
		add(turns);
	}	
	
	// Moves the paddle according to the position of the mouse when it is moved.
	public void mouseMoved(MouseEvent evt) {
		
		int x = evt.getX() - PADDLE_WIDTH/2;
		if(evt.getX() < PADDLE_WIDTH/2) {
			x = 2;
		}
		
		if(evt.getX() > getWidth() - PADDLE_WIDTH/2) {
			x = getWidth() - PADDLE_WIDTH - 2;
		}
		paddle.setLocation(x, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
	}
	
	/* Launches the game and sets the ball in motion given that the 
	 * player still has lives left. If the player doesn't have any 
	 * turns left, it ends the game and waits for a click to start it again. 
	 */
	private void startAndPlayGame(int numTurns,int numBricks) {
	
		if(numTurns == 0) {
			endTheGame();
			add(ball);
			numTurns = NTURNS;
			remove(turns);
			displayTurns();
			startAndPlayGame(numTurns,numBricks);
		}
		
		while(numTurns > 0) {		
			ball.setLocation(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
			startGame();
			launchAndPlayBall(numTurns,numBricks);
		}
	}

	// Adds a label to count to 3 before launching the ball
	private void startGame() {
		
		waitForClick();
		for(int i = 3; i >= 0; i--) {
			GLabel start = new GLabel("" + i, 0, 0);
			
			double xStart = start.getWidth();
			double yStart = start.getHeight();
			
			start = new GLabel("" + i,getWidth()/2 - xStart * (3/2),getHeight()/2 - yStart * 2);
			start.setFont("IMPACT-42");
			start.setColor(Color.BLUE);
			
			if(i == 0) {
				start = new GLabel("GO!" , 0, 0);

				xStart = start.getWidth();
				yStart = start.getHeight();
				
				start = new GLabel("GO!" ,getWidth()/2 - xStart * (3/2),getHeight()/2 - yStart * 2);
				
				start.setFont("IMPACT-42");
				start.setColor(Color.BLUE);
			}

			add(start);
			pause(1000);
			remove(start);
		}
	}

	/* Displays a label for the Game Over condition, i.e, when the
	 * player has lost all turns. It waits for a click and resets the
	 * game.
	 */
	private void endTheGame() {

		remove(ball);
		
		GLabel gameOver = new GLabel("GAME OVER", 0, 0);
		gameOver.setFont("IMPACT-40");
		
		double xOfGameOver = gameOver.getWidth();
		double yOfGameOver = gameOver.getHeight();
		
		gameOver.setLocation(getWidth()/2 - xOfGameOver/2, getHeight()/2 - yOfGameOver/2);
		gameOver.setFont("IMPACT-40");
		gameOver.setColor(Color.RED);
		
		add(gameOver);
		endGame.play();
		GLabel playAgain = playOnceMore();
		add(playAgain);

		waitForClick();
		remove(gameOver);
		remove(playAgain);
	}

	/* Sets the ball in motion in a random x direction and a defined y direction,
	 * and the game can be played. It also changes the direction of the ball according 
	 * to its collisions with the walls, the Top, the bricks and the paddle. It also 
	 * decrements the number of bricks each time they are hit and removed, and displays
	 * a winning label when all of them have been cleared.
	 */
	private void launchAndPlayBall(int numTurns, int numBricks) {
		
		RandomGenerator	rgen = new RandomGenerator();
		
		double dx = rgen.nextDouble(1.0,3.0);
		double dy = 3;

		if (rgen.nextBoolean(0.5)) {
			dx = -dx;
		}

		while(true) {
			ball.move(dx,dy);
			GObject collider = getCollidingObject();

			if(ballHasHitBottom()) {
				resetGame(numTurns,numBricks);
			} 
			
			if(ballHasHitTop()) {
				dy = -dy;
				bounceClip.play();
			}
			
			if(ballHasHitWall()) {
				dx = -dx;
				bounceClip.play();
			} 
			
			if(collider != null && 
					!collider.equals(paddle) &&
					!collider.equals(turns)) {
				remove(collider);
				brickBreak.play();
				numBricks--;
				if(numBricks == 0) {
					displayWin();
					numTurns = NTURNS;
					setUpGame();
					numBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
					startAndPlayGame(numTurns,numBricks);
				}
				if(dy < 0) {
					dy = -dy + 0.2;
				} else {
					dy = -dy - 0.2;
				}
			}
									
			if(collider == paddle && dy > 0.0) {
				dy = -dy;
				bounceClip.play();
			}
			pause(1000/60);
		}
	}	
	
	/* Checks for and returns any object which may be present at the four
	 * corners of the square in which the ball is drawn.
	 */
	private GObject getCollidingObject() {

		GObject element = null;
		
		element = getElementAt(ball.getX(), ball.getY());
		if(element != null) {
			return element;
		}
		element = getElementAt(ball.getX() + ball.getWidth(), ball.getY());
		if(element != null) {
			return element;
		}
		
		element = getElementAt(ball.getX() + ball.getWidth(), 
				ball.getY() + ball.getHeight());
		if(element != null) {
			return element;
		}
		
		element = getElementAt(ball.getX(), ball.getY() + ball.getHeight());
		if(element != null) {
			return element;
		}

		return null;
	}
	
	// Checks if the ball has hit the top of the console/window.
	private boolean ballHasHitTop() {
	
		double topY = ball.getY();
		return topY <= 0;
	}

	// Checks if the ball has hit the sides of the console/window.
	private boolean ballHasHitWall() {
		
		double wallX;
		if(ball.getX() < getWidth()/2) {
			wallX = ball.getX();
			return wallX <= 1;
		} else {
			wallX = ball.getX() + ball.getHeight();
			return wallX >= getWidth() - 1;
		}
	}

	// Checks if the ball has hit the bottom of the console/window.
	private boolean ballHasHitBottom() {
		
		double bottomY = ball.getY() + ball.getHeight();
		return bottomY >= getHeight();
	} 

	/* Decrements the number of turns, and sets the turns label 
	 * according to that. It also resets the ball to the center of
	 * the window and it can be launched again by a click.
	 */
	private void resetGame(int numTurns,int numBricks) {

		remove(turns);
		
		numTurns--;
		if(numTurns != 0) loseLife.play();
		
		turns = new GLabel("" + numTurns,4,25);
		turns.setFont("IMPACT-25");
		add(turns);
		
		startAndPlayGame(numTurns,numBricks);
	}

	/* Displays a label for the win condition, i.e.,
	 * when all the bricks have been cleared.
	 */
	private void displayWin() {

		remove(ball);
		remove(paddle);
		
		GLabel youWin = new GLabel("YOU WIN!!", 0, 0);
		
		double xWin = youWin.getWidth();
		double yWin = youWin.getHeight();
		
		youWin.setLocation(getWidth()/2 - xWin * (3/2), 
				getHeight()/2 - yWin * 2);
		youWin.setFont("IMPACT-40");
		youWin.setColor(Color.BLUE);
		add(youWin);
		
		winGame.play();
		
		GLabel playAgain = playOnceMore();
		add(playAgain);
		
		waitForClick();
		
		remove(youWin);
		remove(playAgain);
	}
	
	
	// Displays the label to prompt to play again
	private GLabel playOnceMore() {
		GLabel playAgain = new GLabel("Click to play again", 0, 0);
		playAgain.setFont("IMPACT-20");
		
		double xPlayAgain = playAgain.getWidth();
		double yPlayAgain = getHeight()/2 + playAgain.getAscent();

		playAgain = new GLabel("Click to play again", getWidth()/2 - xPlayAgain/2, yPlayAgain);
		playAgain.setFont("IMPACT-20");
		playAgain.setColor(Color.BLACK);
		
		return playAgain;
	}

}
