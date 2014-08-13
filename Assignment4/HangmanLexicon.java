/*
 * File: HangmanLexicon.java
 * -------------------------
 * Name: Chinmaya Ramachandra Kuduvalli		
 * Section Leader: Amy Xu
 * -------------------------
 * This program reads the lexicon file and stores the words given in the lexicon.
 * It returns the word at a specified index.
 */

import java.io.*;
import java.util.*;

public class HangmanLexicon {

	/** Declare Instance Variables you need here */
	private ArrayList<String> lexicon;

	/** HangmanLexicon constructor. Do any initialization of your lexicon here. */
	public HangmanLexicon() {

		lexicon = new ArrayList<String>();
		readFromLexicon();
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {

		return lexicon.get(index);
	}

	/** Reads the words from the given lexicon file and transfers into an array list*/
	private void readFromLexicon() {
		try {
			FileReader hangmanLexicon = new FileReader("HangmanLexicon.txt");
			BufferedReader fileReader = new BufferedReader(hangmanLexicon);

			while(fileReader.readLine() != null) {
				lexicon.add(fileReader.readLine());
			}

			fileReader.close();
		} catch(IOException e) {
			System.out.println("Couldn't open file: " + e);
		}
	}

}
