/*
 * File: Hangman.java
 * ------------------
 * Name: Chinmaya Ramachandra Kuduvalli
 * Section Leader: Amy Xu
 * ------------------
 * This file contains the program that plays the Hangman game.
 */

import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {

	/** This variable is required to update the canvas of the program.  */
	private HangmanCanvas canvas;

	public void run() {
	
		setFont("Courier-BOLD-15");
		boolean replayCheck = true;
		createCanvas();

		while(replayCheck) {
			playHangman();    					
			replayCheck = playAgain();
		}
	}

	/** This method creates the canvas part of the Hangman program. */
	private void createCanvas() {
		canvas = new HangmanCanvas();
		setLayout(new GridLayout(1, 2));
		add(canvas);
		validate();
	}

	/** This method starts and plays the hangman game when called. */
	private void playHangman() {

		RandomGenerator rgen = new RandomGenerator();
		HangmanLexicon lexicon = new HangmanLexicon();
		canvas.reset();

		char userGuess = '\0';
		int nextIndex = rgen.nextInt(0,lexicon.getWordCount()-1);
		boolean flag = false;
		int i = 8;

		println("\nWelcome to Hangman!");

		String inputWord = lexicon.getWord(nextIndex);
		char[] word = new char[inputWord.length()];

		for(int j = 0; j < inputWord.length(); j++) {
			word[j] = '-';
		}

		while(i != 0) {

			flag = false;
			String guessedWord = new String(word);

			canvas.displayWord(guessedWord);

			if(inputWord.equals(guessedWord)) {
				println("\nYou have guessed the word: " + inputWord + "\nYou win!");
				break;
			}			

			printMessages(i, guessedWord);

			String userInput = readLine("\nYour guess: ");

			while(!inputIsValid(userInput)) {
				userInput = promptForValidInput(userInput);
			}
			userGuess = userInput.charAt(0);

			if(Character.isLowerCase(userGuess)) {
				userGuess = Character.toUpperCase(userGuess);
			}

			for(int k = 0; k < inputWord.length(); k++) {
				if(userGuess == inputWord.charAt(k)) {	
					word[k] = userGuess;
					flag = true;
				}
			}

			if(flag == false) {
				println("\nThere are no " + userGuess + "'s in this word");
				i--;
				canvas.noteIncorrectGuess(userGuess, i);
			}
		}

		if(i == 0) {
			println("You're completely hung.");
			println("\nThe word was " + inputWord);
			canvas.displayWord(inputWord);
			println("You lose.");
		}
	}

	/** This methods updates and prints the messages required. */
	private void printMessages(int i, String guessedWord) {

		print("\nYour word looks like: ");
		println(guessedWord);
		println("You have " + i + " guesses left.");
	}

	/** 
	 * This method checks if the user has entered a 
	 * valid input and returns a boolean accordingly.   
	 */
	private boolean inputIsValid(String userInput) {

		if(userInput.length() > 1 || userInput.isEmpty() || !Character.isLetter(userInput.charAt(0))) {
			return false;
		}
		return true;
	}

	/** 
	 * This method provides the string prompt for when the user has entered
	 * an invalid input, and returns the correct input once it is entered.
	 */
	private String promptForValidInput(String userInput) {
		if(userInput.length() > 1) {
			return readLine("Please enter a single letter: ");
		}

		if(userInput.isEmpty()) {
			return readLine("Please enter an input: ");
		}

		if(!Character.isLetter(userInput.charAt(0))) {
			return readLine("Please enter a valid input: ");
		}
		return userInput;
	}

	/** 
	 * This methods provides a prompt asking the user if he/she wants 
	 * to play again, and returns a boolean value accordingly
	 */
	private boolean playAgain() {

		String yes = "Y";
		String no = "N";
		String playOnceMore = readLine("Play again? [Y or N]: ");

		if(playOnceMore.equalsIgnoreCase(yes))  {
			return true;
		} else if(playOnceMore.equalsIgnoreCase(no)) {
			println("\nThank you for playing!\nSee you later!");
			return false;
		}
		return false;
	}
}
