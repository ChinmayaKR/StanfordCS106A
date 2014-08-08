/*
/ * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */



import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {
	
	public void run() {
		moveToNewspaper();
		pickUpNewspaper();
		returnBack();
	}
	
	private void moveToNewspaper() { 
		//Pre-condition: Karel is facing east at 4th street and 3rd avenue
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();				
		//Post-condition: Karel is standing on the beeper
	}
	
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	private void turnAround() {
		turnLeft();
		turnLeft();
	}
	
	private void pickUpNewspaper() {
		/* Pre-condition: Karel is standing right on top of the beeper
		 * and facing east
		 */
		pickBeeper();
		turnAround();
		//Post-condition: Karel is facing west
	}
	
	private void returnBack() {
		//Pre-condition: Karel is facing west
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnAround();
		/* Post-condition: Karel has returned back to original position
		 * and orientation.
		 */
	}
}


