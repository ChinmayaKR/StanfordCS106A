/*
 * File: Hailstone.java
 * Name: Chinmaya Ramachandra Kuduvalli	
 * Section Leader: Amy Xu
 * --------------------
 * This program reads in a number from the user and displays the
 * Hailstone sequence for that number, in the same pattern as in
 * the book by Douglas Hofstadter's book.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	/* Receives input from the user and displays the
	 * Hailstone sequence for that number.
	 */
	public void run() {
		
		// Receives an integer input from the console
		int number = readInt("Enter a number: ");
		int numSteps = computeHailstone(number);
		
		println("The process took " + numSteps + " steps to reach 1");
	}
	
	/* Does the math for the given number and returns 
	 * the number of steps taken to reach 1.
	 */
	private int computeHailstone(int number) {
		
		int numSteps = 0;
		while(number != 1) {
			print(number);
			if(number % 2 == 0) {
				number = number/2;
				println(" is even, so I take half: " + number);
			} else {
				number = (number * 3) + 1;
				println(" is odd, so I make 3n+1: " + number);
			}
			numSteps++;
		}
		return numSteps;
	}
}

