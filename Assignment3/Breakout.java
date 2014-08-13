/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 60;

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
	private static final int NTURNS = 3;
	
	/** The ball required for the game */
	private GOval ball;
	
	/** The paddle required for the game */
	private GRect paddle;

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

		if (k < 2) block.setFillColor(Color.RED);
		else if (k >= 2 && k < 4) block.setFillColor(Color.ORANGE);
		else if (k >= 4 && k < 6) block.setFillColor(Color.YELLOW);
		else if (k >= 6 && k < 8) block.setFillColor(Color.GREEN);
		else if (k >= 8 && k < 10) block.setFillColor(Color.CYAN);
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

	
	private void startAndPlayGame(int numTurns,int numBricks) {
	
		if(numTurns == 0) {
			endTheGame();
			add(ball);
			numTurns = NTURNS;
			startAndPlayGame(numTurns,numBricks);
		}
		while(numTurns > 0) {		
			ball.setLocation(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
			startGame();
			launchAndPlayBall(numTurns, numBricks);
		}
	}

	
	private void startGame() {
		
		waitForClick();
		for(int i = 3; i >= 0; i--) {
			GLabel start = new GLabel("" + i, 0, 0);
			
			double xStart = start.getWidth();
			double yStart = start.getHeight();
			
			start = new GLabel("" + i,getWidth()/2 - xStart, getHeight()/2 - yStart * 2);
			start.setFont("IMPACT-42");
			
			if(i == 0) {
				start = new GLabel("GO!" , 0, 0);
				xStart = start.getWidth();
				yStart = start.getHeight();
				start = new GLabel("GO!" ,getWidth()/2 - xStart, getHeight()/2 - yStart * 2);
				start.setFont("IMPACT-42");
			}
			
			start.setColor(Color.BLUE);
			add(start);
			pause(1000);
			remove(start);
		}
	}

	private void endTheGame() {

		remove(ball);
		
		GLabel label = new GLabel("GAME OVER", 0, 0);
		
		double xLabel = label.getWidth();
		double yLabel = label.getHeight();
		
		label = new GLabel("GAME OVER",getWidth()/2 - xLabel, getHeight()/2 - yLabel * 2);
		label.setFont("IMPACT-42");
		label.setColor(Color.RED);
		add(label);
		waitForClick();
		remove(label);
	}

	private void launchAndPlayBall(int numTurns,int numBricks) {
		
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
				resetGame(numTurns, numBricks);
			} 
			
			if(ballHasHitTop()) {
				dy = -dy;
			}
			
			if(ballHasHitWall()) {
				dx = -dx;
			} 
			
			if(collider != null && collider != paddle && dy < 0) {
				numBricks--;
				remove(collider);
				if(numBricks == 0) {
					displayWin();
					numTurns = NTURNS;
					setUpGame();
					numBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
					startAndPlayGame(numTurns,numBricks);
				}
				dy = -dy;
			} 
			
			if(collider == paddle && dy > 0.0) {
				dy = -dy;
			}
			pause(1000/60);
		}
	}	
	
	private void displayWin() {

		remove(ball);
		remove(paddle);
		GLabel youWin = new GLabel("YOU WIN!!", 0, 0);
		double xWin = youWin.getWidth();
		double yWin = youWin.getHeight();
		youWin = new GLabel("YOU WIN!!", 
				getWidth()/2 - xWin, 
				getHeight()/2 - (yWin * 2));
		youWin.setFont("IMPACT-BOLD-42");
		youWin.setColor(Color.GREEN);
		add(youWin);
		waitForClick();
		remove(youWin);
	}

	private GObject getCollidingObject() {

		GObject element  = null;
		
		element = getElementAt(ball.getX(), ball.getY());
		if(element != null) return element;
		
		element = getElementAt(ball.getX() + ball.getWidth(), ball.getY());
		if(element != null) return element;
		
		element = getElementAt(ball.getX() + ball.getWidth(), 
				ball.getY() + ball.getHeight());
		if(element != null) return element;
		
		element = getElementAt(ball.getX(), ball.getY() + ball.getHeight());
		if(element != null) return element;

		return null;
	}


	private boolean ballHasHitTop() {
	
		double topY = ball.getY();
		return topY <= 0;
	}

	private boolean ballHasHitWall() {
		
		double wallX;
		if(ball.getX() < getWidth()/2) {
			wallX = ball.getX();
			return wallX <= 1;
		} else {
			wallX = ball.getX() + ball.getWidth() ;
			return wallX >= getWidth() - 1;
		}
	}

	private boolean ballHasHitBottom() {
		
		double bottomY = ball.getY() + ball.getHeight();
		return bottomY >= getHeight();
	} 

	private void resetGame(int numTurns,int numBricks) {

		numTurns--;
		startAndPlayGame(numTurns,numBricks);
	}

	public void mouseMoved(MouseEvent evt) {
		
		int x = evt.getX() - PADDLE_WIDTH/2;
		int yOfPaddle = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;

		if(evt.getX() < PADDLE_WIDTH/2) {
			x = 2;
		}
		
		if(evt.getX() > getWidth() - PADDLE_WIDTH/2) {
			x = getWidth() - PADDLE_WIDTH - 2;
		}
		paddle.setLocation(x, yOfPaddle);
	}
}
