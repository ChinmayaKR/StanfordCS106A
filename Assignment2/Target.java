/*
 * File: Target.java
 * Name: Chinmaya Ramachandra Kuduvalli	
 * Section Leader: Amy Xu 
 * -----------------
 * This program prints a target symbol, which is two alternate concentric red circles
 * with a white circle in between the two red circles. This target is centered in the 
 * console, which is done by obtaining the height and width of the console and halving
 * it and subtracting the radii of the respective circles from it. 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	

	private static final double OUTER_RED_CIRCLE_RADIUS = 72;
	private static final double WHITE_CIRCLE_RADIUS = 72 * 0.65;
	private static final double INNER_RED_CIRCLE_RADIUS = 72 * 0.3;
	

	// Prints a target symbol which is centered in the console.
	public void run() {

		drawOuterRedCirle();
		drawWhiteCirle();
		drawInnerRedCirle();
	}

	
	// Prints the outer red circle
	private void drawOuterRedCirle() {

		GOval outerCircle = new GOval(getWidth()/2 - OUTER_RED_CIRCLE_RADIUS, getHeight()/2 - OUTER_RED_CIRCLE_RADIUS,
				OUTER_RED_CIRCLE_RADIUS * 2, OUTER_RED_CIRCLE_RADIUS * 2);
		outerCircle.setFilled(true);
		outerCircle.setFillColor(Color.RED);
		add(outerCircle);
	}
	
	// Prints the inner white circle
	private void drawWhiteCirle() {
		
		GOval whiteCircle = new GOval(getWidth()/2 - WHITE_CIRCLE_RADIUS, getHeight()/2 - WHITE_CIRCLE_RADIUS,
				WHITE_CIRCLE_RADIUS * 2, WHITE_CIRCLE_RADIUS * 2);
		whiteCircle.setFilled(true);
		whiteCircle.setFillColor(Color.WHITE);		
		add(whiteCircle);
	}
	
	// Prints the inner most red circle
	private void drawInnerRedCirle() {
		
		GOval innerCircle = new GOval(getWidth()/2 -INNER_RED_CIRCLE_RADIUS, getHeight()/2 - INNER_RED_CIRCLE_RADIUS,
				INNER_RED_CIRCLE_RADIUS * 2, INNER_RED_CIRCLE_RADIUS * 2);
		innerCircle.setFilled(true);
		innerCircle.setFillColor(Color.RED);
		add(innerCircle);
	}
}
