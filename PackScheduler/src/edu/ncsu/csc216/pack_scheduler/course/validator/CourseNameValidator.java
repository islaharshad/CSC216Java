/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;


/**A class containing the logic for determining the validity of a name as a Course name, contains the method isValid.
 * @author Addison Garrigus
 *
 */
public class CourseNameValidator {
	/** State object for initial actions */
	private final State initial = new InitialState();
	/** State object for actions after receiving a letter */
	private final State letter = new LetterState();
	/** State object for actions after receiving a digit */
	private final State digit = new NumberState();
	/** State object for actions after receiving a letter from the digit state */
	private final State suffix = new SuffixState();
	/** The state object used in the isValid method*/
	private State state = initial;
	/** A boolean to determine whether three digits have been entered*/
	private boolean canEnd = false;
	/** The number of letters in the name*/
	private int numLetters = 0;
	/** The number of digits in the name*/
	private int numDigits = 0;


	/** A method to test the validity of a given string as a name for a course
	 * @param name - the string to test
	 * @return - returns true or false depending on validity or not
	 * @throws InvalidTransitionException - Thrown when the input has an invalid character 
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		numLetters = 0;
		numDigits = 0;
		state = initial;
		canEnd = false;
		// Create a variable to track the current character index
		int charIndex = 0;
		
		// Variable to keep track of the current input character being examined
		char c;
		
		// Iterate through the ID, examining one character at a time
		while(charIndex < name.length()) {
			// Set the current character being examined
			c = name.charAt(charIndex);
			if(Character.isLetter(c)){
				state.onLetter();
			}
			else if(Character.isDigit(c)){
				state.onDigit();
			}
			else{
				state.onOther();
			}
			charIndex++;
		}
		if(state == suffix || canEnd)
			return true;
		return false;
	}
	
	/**Abstract class holding common code for the four sublclasses. Contains the onOther method code.
	 * @author Wil Elias
	 *
	 */
	private abstract class State {
		
		/** Handles actions when passed a letter
		 * @throws InvalidTransitionException
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/** Handles actions when passed a digit
		 * @throws InvalidTransitionException
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/** Throws an InvalidTransitionException when given a character that is not a letter or a digit
		 * @throws InvalidTransitionException
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**A subclass of State which keeps track of the number of letters and moves to the digit state when given a digit
	 * @author Wil Elias
	 *
	 */
	private class LetterState extends State {

		/** The maximum number of letters that can come before digits */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/** Increases numLetters, throws an InvalidTransitionException when it goes above 4
		 *@throws InvalidTransitionException - when more than four letters come before the digits
		 */
		public void onLetter() throws InvalidTransitionException {
			if (numLetters >= MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			numLetters++;
		}
		
		/** Changes state to NumberState and increases numDigits by 1
		 */
		public void onDigit() {
			state = digit;
			numDigits++;
		}
		
	}
	
	/**A subclass of State which allows the isValid method to return true and throws exceptions if it is followed by another character
	 * @author Wil Elias
	 *
	 */
	private class SuffixState extends State {
		
		/** Throws an InvalidTransitionException 
		 * @throws InvalidTransitionException - whenever it is called
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			
		}
		
		/** Throws an InvalidTransitionException 
		 * @throws InvalidTransitionException - whenever it is called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
			
		}
		
	}
	
	/**A subclass of State, when given a letter changes to the LetterState and when given a digit thows an exception
	 * @author Wil Elias
	 *
	 */
	private class InitialState extends State {

		/** Changes state to LetterState and increases numLetters by 1
		 */
		@Override
		public void onLetter() {
			state = letter;
			numLetters++;
		}

		/** Throws an InvalidTransitionException
		 * @throws InvalidTransitionException - whenever it is called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
			
		}
		
	}
	
	/**A subclass of State which keeps track of the number of digits and throws an exception if it gets above 3. Moves to the SuffixState if 
	 * given a letter after reaching 3 digits. 
	 * @author Wil Elias
	 *
	 */
	private class NumberState extends State {
		
		/** The number of digits required in a course name*/
		private static final int COURSE_NUMBER_LENGTH = 3;

		/** Throws an InvalidTransitionException when numDigits is less than 3, else it changes state to SuffixState
		 *@throws InvalidTransitionException - When numDigits is less than 3
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(numDigits < COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			else{
				state = suffix;
			}
		}

		/** Increases numDigits by 1 until it reaches three, then throws an InvalidTransitionException if called again, sets the canEnd boolean
		 * to true once numDigits = 3
		 * @throws InvalidTransitionException - When numDigits == 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if(numDigits < 3)
				numDigits++;
			else
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			if(numDigits == 3)
				canEnd = true;
		}
		
	}
	
}
