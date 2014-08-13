/*
 * File: HangmanCanvas.java
 * ------------------------
 * Name: Chinmaya Ramachandra Kuduvalli
 * Section Leader: Amy Xu
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.*;
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Constants for the simple version of the picture (in pixels) */
	private static final int GALLOW_OFFSET = 50;

	private static final int SCAFFOLD_HEIGHT = 360;

	private static final int BEAM_LENGTH = 144;

	private static final int ROPE_LENGTH = 18;

	private static final int HEAD_RADIUS = 36;

	private static final int BODY_LENGTH = 144;

	private static final int ARM_OFFSET_FROM_HEAD = 28;

	private static final int UPPER_ARM_LENGTH = 72;

	private static final int LOWER_ARM_LENGTH = 44;

	private static final int HIP_WIDTH = 36;

	private static final int LEG_LENGTH = 108;

	private static final int FOOT_LENGTH = 28;

	/** 
	 * These variables need to be instance variables as 
	 * they cannot be passed into the public methods. 
	 */
	private GLabel outputWord;

	private GLabel incorrectList;

	private String incorrectLetters;


	/** HangmanCanvas constructor. You can do initialization (if needed) here. */
	public HangmanCanvas() {

		outputWord = new GLabel("");
		incorrectList = new GLabel("");
		incorrectLetters = "";
	}

	/** Resets the display so that only the scaffold appears. */
	public void reset() {

		removeAll();
		incorrectLetters = "";
		drawGallows();
	}
	
	/** This method draws the gallows when called. */
	private void drawGallows() {

		int xOfPole = GALLOW_OFFSET;
		int y2Rope = xOfPole + ROPE_LENGTH;
		int x2Beam = BEAM_LENGTH + xOfPole;

		GLine scaffoldPole = new GLine(xOfPole, xOfPole, xOfPole, SCAFFOLD_HEIGHT);
		GLine topBeam = new GLine(xOfPole, xOfPole, x2Beam, xOfPole);
		GLine rope = new GLine(x2Beam, xOfPole,x2Beam,y2Rope);

		add(scaffoldPole);
		add(topBeam);
		add(rope);
	}

	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String guessedWord) {

		remove(outputWord);
		outputWord = new GLabel(guessedWord, GALLOW_OFFSET, getHeight() - GALLOW_OFFSET);
		outputWord.setFont("Courier-20");
		add(outputWord);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(char letter, int i) {

		drawNextBodyPart(i);
		displayIncorrectLetter(letter);
	}

	/** This method displays the string of incorrect letters typed. */
	private void displayIncorrectLetter(char letter) {

		remove(incorrectList);
		incorrectLetters += letter;
		incorrectList = new GLabel(incorrectLetters, GALLOW_OFFSET, getHeight() - 20);
		incorrectList.setFont("Courier-20");
		incorrectList.setColor(Color.RED);
		add(incorrectList);
	}

	/**
	 * This method calls the methods to draw the body parts 
	 * according to the number of incorrect guesses made. 
	 */
	private void drawNextBodyPart(int i) {

		switch(i) {

		case 7: drawHead();
				break;

		case 6: drawBody();
				break;

		case 5: drawLeftArm();		
				break;

		case 4: drawRightArm();
				break;

		case 3:	drawLeftLeg();
				break;

		case 2: drawRightLeg();
				break;

		case 1:	drawLeftFoot();
				break;

		case 0:	drawRightFoot();
				break;
			
		default:break;		
		}
	}

	/** This method draws the head when called.*/
	private void drawHead() {

		int xOfHead = BEAM_LENGTH + GALLOW_OFFSET - HEAD_RADIUS;
		int yofHead = GALLOW_OFFSET + ROPE_LENGTH;

		GOval head = new GOval(xOfHead, yofHead, HEAD_RADIUS * 2, HEAD_RADIUS * 2);

		add(head);
	}

	/** This method draws the body when called. */
	private void drawBody() {

		int xOfBody = BEAM_LENGTH + GALLOW_OFFSET;
		int y1Body = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2);
		int y2Body = y1Body + BODY_LENGTH;

		GLine body = new GLine(xOfBody, y1Body, xOfBody, y2Body);

		add(body);
	}

	/** This method draws the left arm when called. */
	private void drawLeftArm() {

		int xOfArm = BEAM_LENGTH + GALLOW_OFFSET;
		int xOfLeftArm = xOfArm - UPPER_ARM_LENGTH;
		int yOfUpperArm = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		int yOfLowerarm = yOfUpperArm + LOWER_ARM_LENGTH;

		GLine leftUpperArm = new GLine(xOfArm, yOfUpperArm, xOfLeftArm, yOfUpperArm);
		GLine leftLowerArm = new GLine(xOfLeftArm, yOfUpperArm, xOfLeftArm, yOfLowerarm);

		add(leftUpperArm);
		add(leftLowerArm);

	}

	/** This method draws the right arm when called. */
	private void drawRightArm() {

		int xOfArm = BEAM_LENGTH + GALLOW_OFFSET;
		int xOfRightArm = xOfArm + UPPER_ARM_LENGTH;
		int yOfUpperArm = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
		int yOfLowerArm = yOfUpperArm + LOWER_ARM_LENGTH;

		GLine rightUpperArm = new GLine(xOfArm, yOfUpperArm, xOfRightArm, yOfUpperArm);
		GLine rightLowerArm = new GLine(xOfRightArm, yOfUpperArm, xOfRightArm, yOfLowerArm);

		add(rightUpperArm);
		add(rightLowerArm);
	}

	/** This method draws the left leg when called. */
	private void drawLeftLeg() {

		int yOfHip = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
		int x1Hip = BEAM_LENGTH + GALLOW_OFFSET;
		int x2LeftHip = x1Hip - HIP_WIDTH;

		GLine leftHip = new GLine(x1Hip, yOfHip, x2LeftHip, yOfHip);
		GLine leftLeg = new GLine(x2LeftHip, yOfHip, x2LeftHip, yOfHip + LEG_LENGTH);

		add(leftHip);
		add(leftLeg);
	}

	/** This method draws the right leg when called. */
	private void drawRightLeg() {

		int yOfHip = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
		int x1Hip = BEAM_LENGTH + GALLOW_OFFSET;
		int x2RightHip = x1Hip + HIP_WIDTH;

		GLine rightHip = new GLine(x1Hip, yOfHip, x2RightHip, yOfHip);
		GLine rightLeg = new GLine(x2RightHip, yOfHip, x2RightHip, yOfHip + LEG_LENGTH);

		add(rightHip);
		add(rightLeg);

	}

	/** This method draws the right foot when called. */
	private void drawLeftFoot() {

		int x1LeftFoot = BEAM_LENGTH + GALLOW_OFFSET - HIP_WIDTH;
		int x2LeftFoot = x1LeftFoot - FOOT_LENGTH;
		int yOfFoot = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;

		GLine leftFoot = new GLine(x1LeftFoot, yOfFoot, x2LeftFoot, yOfFoot);

		add(leftFoot);
	}

	/** This method draws the right foot when called. */
	private void drawRightFoot() {

		int x1RightFoot = BEAM_LENGTH + GALLOW_OFFSET + HIP_WIDTH;
		int x2RightFoot = x1RightFoot + FOOT_LENGTH;
		int yOfFoot = GALLOW_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;

		GLine rightFoot = new GLine(x1RightFoot, yOfFoot, x2RightFoot, yOfFoot);

		add(rightFoot);
	}
}
