/*
 * File: Pyramid.java
 * Name: Chinmaya Ramachandra Kuduvalli 	
 * Section Leader: Amy Xu 
 * ------------------
 * This program draws a pyramid by laying down bricks from the base 
 * of the pyramid to the apex of the pyramid. It decrements the number 
 * of bricks in the same order as it is laid. The dimensions of the bricks
 * can be changed by changing the constants BRICK_HEIGHT, BRICK_WIDTH, 
 * BRICKS_IN_BASE. It works for all dimensions of the bricks and any number
 * of bricks which can be viewed in the default console.
 */

import acm.graphics.*;
import acm.program.*;


public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	// Draws a pyramid using bricks of given dimensions.
	public void run() {
		
		constructPyramid();
	}
	
	/* Draws a pyramid using the bricks of given dimensions  
	 * by decrementing the number of bricks laid from base
	 * of the pyramid to apex.
	 */
	private void constructPyramid() {
		
		double x_start=0;
		int numBricks = BRICKS_IN_BASE;
		
		double midPoint = getWidth()/2;
		
		int y_start = getHeight() - BRICK_HEIGHT;				
		for(int i = BRICKS_IN_BASE; i > 0 ; i--) {
			
			if(numBricks % 2 == 0) {
				x_start = midPoint - (numBricks/2 * BRICK_WIDTH);
			} else {
				x_start = midPoint - (numBricks/2 * BRICK_WIDTH) - BRICK_WIDTH/2;
			}
			layRowOfBricks(x_start, y_start, numBricks);
			y_start -= BRICK_HEIGHT;
			numBricks--; 
		}
	}
	
	/* Draws one row of bricks with the given dimensions 
	 * of the brick.[Can be changed by changing the constants
	 * BRICK_WIDTH, BRICK_HEIGHT, BRICKS_IN_BASE]
	 */
	private void layRowOfBricks(double x_start, int y_start, int numBricks) {
		double x = x_start;
		double y = y_start;
		 
		for(int i = 0; i < numBricks; i++) {
			GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
			add(brick);
			x += BRICK_WIDTH;
		}
	}	
}



