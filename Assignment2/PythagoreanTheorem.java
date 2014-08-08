/*
 * File: PythagoreanTheorem.java
 * Name: Chinmaya Ramachandra Kuduvalli 	
 * Section Leader: Amy Xu 
 * -----------------------------
 * This program accepts the values for 'a' and 'b' as doubles and then
 * calculates the solution of 'c'[sum of squares of 'a' and 'b'] as a double
 * and displays the value of 'c'.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	public void run() {
		
		double c = computePythogoreanTheorem();
		println("c = " + c);
	}

	/* Takes input from the user, and finds the sum of squares of 
	 * the given 2 numbers, and returns the square root of this sum.
	 */
	private double computePythogoreanTheorem() {

		double a, b; 
		println("Enter values to compute the Pythagorean therorem.");
		
		a = readDouble("a: ");
		b = readDouble("b: ");
		
		// Finds the squares of a and b
		double a_squared = a*a;
		double b_squared = b*b;
		
		/* Finds the square root of the sum
		 * of squares of a and b
		 */
		double c = Math.sqrt(a_squared + b_squared);
		return c;
	}
}
