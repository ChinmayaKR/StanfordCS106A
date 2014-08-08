/* 
 * File: FixingBrokenJava.java
 * Name: Chinmaya Ramachandra Kuduvalli	
 * Section Leader: Amy Xu
 * 
 * Edit: ALL COMPILE-TIME AND RUN-TIME ERRORS FIXED AND LOGIC UPDATED.
 * 
 * This program attempts to read an integer greater than one from the
 * user, then check whether that integer is prime (whether its only
 * divisors are 1 and itself). If so, it prints a message saying
 * that the number is prime; otherwise it says that the number is
 * composite.
 */
import acm.program.*;

public class FixingBrokenJava extends ConsoleProgram {
	
	/* Reads a number from the user and reports whether or not it
	 * is prime.
	 */
	public void run() {
		
		/* Gets the value from the user. */
		int value = readInput();
		
		/* Checks and prints whether or not it is prime. */
		if(isPrime(value)) {
			println(value + " is prime.");
		} else {
			println(value + " is composite.");
		}
	}
	
	/**
	 * Given a positive integer, returns whether that integer is
	 * prime.
	 * 
	 * @param value The value to test.
	 * @return Whether or not it is prime.
	 */
	private boolean isPrime(int value) {
		
		/* Tries all possible divisors of the number. If any of them
		 * cleanly divide the number, returns that the number is
		 * composite.
		 */
		boolean primeCheck = true;
		
		for (int divisor = 2; divisor < value; divisor++) {
			if (value % divisor == 0) {
				primeCheck = false;
			}
		}
		return primeCheck;
	}
	
	/**
	 * Reads an integer greater than one from the user.
	 * 
	 * @return An integer greater than one entered by the user.
	 */
	private int readInput() {
		
		/* Gets an initial value. */
		int value = readInt("Enter an integer greater than 1: ");
		
		/* If the value wasn't greater than one, re-prompts. */
		while(value <= 1) {
			println("Please enter a positive integer greater than 1.");
			value = readInt("Enter a positive integer: ");
		}
		return value;
	}
}
