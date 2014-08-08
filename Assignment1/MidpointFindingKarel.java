/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
	public void run() {
		midPointForSmallWorld();
		if(frontIsClear()) {
			standInTheMiddle();
		}
	}
	
	/* Makes Karel put a beeper at the mid point
	 * for worlds of width less than 3. 
	 */
	
	private void midPointForSmallWorld() {
		if(frontIsBlocked()) {
			putBeeper();
		} else {
			move();
			if(frontIsBlocked()) {
				putBeeper();
			}	
		}
	}
	
	private void standInTheMiddle() {
		fillRow();
		clearEndBeepers();
		while(noBeepersPresent()) {
			findMidPoint();
		}
	}
	
	/* Makes Karel fill the 1st street with beepers
	 * until it hits a wall.
	 */
	
	private void fillRow() {
		putBeeper();
		while(frontIsClear()) {
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}			
		}

		if(frontIsBlocked()) {
			turnAround();
		}		
	}
	
	/* Makes Karel check if there is a beeper in front 
	 * of the beeper it is about to pick, and picks it up only if
	 * this condition is  satisfied.
	 */
	
	private void findMidPoint() {
		goToNextBeeper();
		checkAndPickBeeper();
	}
	
	/* Makes Karel pick up the beepers at the 
	 * extreme ends of the street.
	 */
	
	private void clearEndBeepers() {
		pickBeeper();
		while(frontIsClear()) {
			move();
		}
		
		if(beepersPresent()) {
			pickBeeper();	
			turnAround();
		} else {
			turnAround();
		}
	}
	
	/* Moves to the beeper to be picked up next
	 * which is at the other end of the street.
	 */
	
	private void goToNextBeeper() {
		move();
		while(beepersPresent()) {
			move();
		}		
		backToPosition();		
	}
	
	/* Makes Karel check if there is a beeper in front of the
	 * beeper it is about to pick, and picks it up only if 
	 * that condition is true.
	 * I discussed this trick with my brother.
	 */
	
	private void checkAndPickBeeper() {
		if(beepersPresent()) {
			move();
			if(beepersPresent()) {
				backToPosition();
				pickBeeper();
			} else {
				backToPosition();
			}
			turnAround();
		}
	}
	
	/* Makes Karel move to its  previous position.
	 */
	
	private void backToPosition() {
		turnAround();
		move();		
	}
}


