package lab01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A (buggy) class that analyzes words. Designed for use in a debugging
 * laboratory in a computer science course.
 * 
 * Adapted from: http://www.horstmann.com/bigj/help/debugger/tutorial.html
 */
public class WordAnalyzer {

	private String word;

	/**
	 * Constructs an analyzer for a given word.
	 * 
	 * @param aWord
	 *            the word to be analyzed
	 */
	public WordAnalyzer(String aWord) {
		word = aWord;
		if (aWord == null) {
			throw new IllegalArgumentException("nullPointerException occurred");
		}
	}

	/**
	 * Gets the first repeated character. A character is <i>repeated</i> if it
	 * occurs at least twice in adjacent positions. For example, 'l' is repeated
	 * in "hollow", but 'o' is not.
	 * 
	 * @return the first repeated character, or nothing (\0 the null character)
	 *         if none found
	 */
	public char firstRepeatedCharacter() {
		for (int i = 0; i+1 < word.length(); i++) {
//			System.out.println("i = " + i);

			char ch = word.charAt(i);
//			System.out.println(" ch = " + ch);
			char nextCh = word.charAt(i + 1);
//			System.out.println("nextCh = " + nextCh );
			if (ch == nextCh) {
				return ch;
			}
		}
		return '\0';
	}

	/**
	 * Gets the first multiply occurring character. A character is
	 * <i>multiple</i> if it occurs at least twice in the word, not necessarily
	 * in adjacent positions. For example, both 'o' and 'l' are multiple in
	 * "hollow", but 'h' is not.
	 * 
	 * @return the first repeated character, or 0 if none found
	 */
	public char firstMultipleCharacter() {
		Logger.getLogger("fMP").setLevel(Level.OFF);
		Logger.getLogger("fMP").info("Entering firstMultipleCharacter method");
		for (int i = 0; i < word.length(); i++) {
			Logger.getLogger("fMP").info("i = " + i);
			char ch = word.charAt(i);
			Logger.getLogger("fMP").info("Looking for ch = " + ch);
			int nextLoc = findSameCharacterPosition(ch, i+1);
			Logger.getLogger("fMP").info("Found next " + ch + " at nextLoc = " + nextLoc);
			if (nextLoc >= 0) {
				Logger.getLogger("fMP").info("Multiple found - Exiting firstMultipleCharacter");
				return ch;
			}
		}
		Logger.getLogger("fMP").info("No multiple - Exiting firstMultipleCharacter");
		return 0;
	}

	/**
	 * find the position of repeating character
	 * @param c
	 * @param pos
	 * @return i or -1 if none is found
	 */
	
	/*
	 * find the position of the repeating character
	 */
	private int findSameCharacterPosition(char c, int pos) {
		assert pos >= 0 : "pos must be >= 0 but was " + pos; //it asserts that the pos has to be bigger or equal to 0
		assert pos < word.length() : "pos must be < word length but was" + pos; //it asserts that the pos has to be smaller than the word length
		for (int i = pos; i < word.length(); i++) { //for loop through the characters of the given word
			if (word.charAt(i) == c) { //checks if the found character is the same as the given character
				return i; //returns the position of the character
			}
		}
		return -1; //if none is found
	}

	/**
	 * Counts the number of groups of repeated characters. For example,
	 * "mississippi!!!" has four such groups: ss, ss, pp and !!!.
	 * 
	 * @return the number of groups of repeated characters.
	 */
	public int countRepeatedCharacters() {
		/*
		 * Go through the word comparing the character at each position to the
		 * next. If a repetition is found, determine if it is at the start of a
		 * group (e.g. the first bb in "abbbcd"). If it is then add 1 to the
		 * number of groups.
		 */
		int numGroups = 0; // the number of groups seen so far.
		for (int chPos = 0; chPos < word.length() - 1; chPos++) {
			if (word.charAt(chPos) == word.charAt(chPos + 1)) {
				// found a repetition
				if(chPos == 0) {
					numGroups++;
				}else if (word.charAt(chPos - 1) != word.charAt(chPos)) {
					// it's at the start of a group
					numGroups++;
				}
			}
		}
		return numGroups;
	}

	/**
	 * Create an instance of WordAnalyzer and invoke some methods on it.
	 * 
	 * @param args
	 *            none.
	 */
	public static void main(String[] args) {
		WordAnalyzer wa1 = new WordAnalyzer("aabbcccccbbbdd");
		int r = wa1.countRepeatedCharacters();
		System.out.println(r);
		
	}
}
