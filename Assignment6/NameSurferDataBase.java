/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	private HashMap<String, NameSurferEntry> nameMap;


	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {

		nameMap = new HashMap<String, NameSurferEntry>();
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader reader = new BufferedReader(file);
			while(true) {
				String line = reader.readLine();
				if(line == null) {
					break;
				}
				NameSurferEntry entry = new NameSurferEntry(line);
				nameMap.put(entry.getName(), entry);
			}
			reader.close();
			file.close();
		} catch(Exception e) {
			System.out.println("Error opening file: " + e);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {

		String key = getNormalizedName(name);
		NameSurferEntry entry = nameMap.get(key);
		return entry;
	}

	/**
	 * Returns a string in which only the first character is capitalized.
	 * 
	 * @param name The name corresponding to the entry.
	 * @return Normalized version of "name".
	 */
	private String getNormalizedName(String name) {

		if(name.length() == 1) {
			return name.toUpperCase();
		}
		name = name.toLowerCase();
		String firstChar = name.substring(0, 1);
		firstChar = firstChar.toUpperCase();
		name = firstChar + name.substring(1);
		
		return name;
	}
}

