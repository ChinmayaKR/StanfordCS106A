/*
 * File: FindRange.java
 * Name: Chinmaya Ramachandra Kuduvalli 	
 * Section Leader: Amy Xu 
 * --------------------
 * This program reads in a list of integers, one per line, until
 * the user has entered a sentinel value, which is set to 0 by 
 * default[can be changed by changing value of "SENTINEL"]. When 
 * the sentinel is read, the smallest and largest values in the 
 * list are displayed.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	/* Reads values one per line, stops input when it reads the
	 * sentinel value, and displays smallest and largest of the
	 * values in the list.
	 */
	public void run() {
		
		println("This program finds the largest and smallest numbers");
		println("Enter " + SENTINEL + " at the end.");
		
		// Receives the first input value for initialization.
		double numInput = readDouble("=> ");
		
		/* If the given input is equal to the sentinel the program 
		 * displays proper message. If not, it goes ahead to find 
		 * the smallest and largest integers given before the sentinel.
		 */
		if(numInput != SENTINEL) {
			double small = numInput;
			double large = numInput;

			while(true) {
				numInput = readDouble("=> ");
				if(numInput == SENTINEL) break;

				if(numInput < small) small = numInput;

				if(numInput > large) large = numInput;
			}

			println("smallest: " + small);
			println("largest: " + large);
			
			// Prints appropriate message for no values given before sentinel
		} else {
			
			println("You have not entered any values for comparision.");
		}
	}
}

