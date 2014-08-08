/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		repairEveryColumn();
		repairLastColumn();
	}
	
	/* Makes Karel repair all the columns except 
	 * the last column.
	 */
	
	private void repairEveryColumn() {
		while(frontIsClear()) { 			
			repairColumn();			
			moveToNextColumn();
		}
	}
	
	/* Makes Karel repair the last column.
	 */
	
	private void repairLastColumn() {
		if(frontIsBlocked()) {
			repairColumn();			
		}
	}
	
	/* Makes Karel fill the column with beepers and come 
	 * back to 1st street.
	 */
	
	private void repairColumn() {
		placeStones();			
		backToFoundation();		
	}
	
	/* Makes Karel fill a whole column with beepers
	 * irrespective of the height of the column.
	 */
	
	private void placeStones() {
		//Pre-condition: Karel is facing East 
		turnLeft();
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
		
		if(frontIsBlocked()) {				
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		//Post-condition: Karel is facing North
	}
	
	/* Moves Karel to the next column 
	 * which is 4 units away.
	 */
	
	private void moveToNextColumn() {
		
		move();
		move();
		move();
		move();
	}
	
	/* Moves Karel back to the first street.
	 */
	 
	private void backToFoundation() {
		//Pre-condition: Karel is facing North
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
		//Post-condition: Karel is facing east
	}
}
