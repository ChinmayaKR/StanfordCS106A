/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		/* Assumption: Karel is at 1st street and
		 * 1st avenue facing east.
		 */
		
		fillSingleColumn();
		fillTheBoard();
	}
	
	/* Makes Karel lay down beepers to make it look 
	 * like a Checkerboard.
	 */
	
	private void fillTheBoard() {
		while(frontIsClear()) {
			fillRow();
			goToNextRow();
		}
	}
	
	/* If there is a single column/avenue present, 
	 * Makes Karel fill only that avenue.
	 */
	
	private void fillSingleColumn() {
		if(frontIsBlocked()) {
			turnLeft();
			fillRow();
		}
	}
	
	/* Makes Karel fill a whole street with beepers irrespective 
	 * of number of avenues and the direction Karel is facing.
	 */
	
	private void fillRow() {
		putBeeper();	
		while(frontIsClear()) {
			move();
			if(frontIsClear()) {				
				move();
				putBeeper();
			}
		}
	}
	
	/* Makes Karel go to the start of the next street (the street above) 
	 * both when Karel ends up facing East as well as West after filling 
	 * the row. This strategy allows Karel to fill the rows in a ZigZag 
	 * fashion.
	 */
	
	private void goToNextRow() {
		
		faceNorth();
		if(frontIsClear()) {
			if(noBeepersPresent()) {
				// For odd number of avenues.
				moveUp();
			} else {
				// For even number of avenues.
				moveUp();
				move();
			}
		}
	}	
	
	/* Makes Karel face North irrespective of the
	 * direction she is facing.
 	 */
	
	private void faceNorth() {
		
		//I discussed this with Prof.Tom
		while(notFacingNorth()) {
			turnLeft();
		}
	}
	
	/* Makes Karel move up one street and turn away
	 * from the adjacent wall.
	 */
	
	private void moveUp() {
		move();
		if(rightIsBlocked()) {
			turnLeft();
		}

		if(leftIsBlocked()) {
			turnRight();
		}
	}
}
