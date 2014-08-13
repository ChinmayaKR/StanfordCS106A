/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/** Instance variable declaration */
	private String entryName;	
	private int[] rankMap;

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {

		StringTokenizer tokens = new StringTokenizer(line);
		rankMap = new int[NDECADES];
		int tokenCount = 0;
		
		while(tokens.hasMoreTokens()) {
			if(tokenCount == 0) {
				entryName = tokens.nextToken();
			} else {
				rankMap[tokenCount -1] = Integer.parseInt(tokens.nextToken());
			}
			tokenCount++;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {

		return entryName;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {

		return rankMap[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {

		String finalString = entryName + " [";
		
		for(int i = 0; i < rankMap.length; i++) {
			finalString += (" " + rankMap[i]);
		}
		return finalString + " ]";
	}
}

