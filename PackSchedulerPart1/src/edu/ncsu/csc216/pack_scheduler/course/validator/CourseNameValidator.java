package edu.ncsu.csc216.pack_scheduler.course.validator;


/**
 * This class validates the name of the Course used in NCSU Registration system.
 * @author Premsubedi, Edwerdo, Isla
 */
public class CourseNameValidator {
    
	/** current state of the FSM **/
	private State state;
	/** true if end state is valid, false otherwise.**/
	private boolean validEndState;
	/** Counts the number of letters in theString **/
	private int letterCount;
	/** Counts the number of digits in theString **/
	private int digitCount;
	

	/**
	 * Determines whether a string is a valid course or not.
	 * @param theString string parameter
	 * @return boolean
	 * @throws InvalidTransitionException if input doesn't match with the current state.
	 */
	public boolean isValid(String theString) throws InvalidTransitionException {
		letterCount = 0;
		digitCount = 0;
		state = new InitialState();
		for (int i = 0; i < theString.length(); i++) {
			char c = theString.charAt(i); /** Character being iterated over **/
			if (Character.isLetter(c)) {
				letterCount++;
				state.onLetter();
			}
			if (Character.isDigit(c)) {
				digitCount++;
				state.onDigit();
			}
			else if(!(Character.isLetter(c) || Character.isDigit(c))) {
				try{
				state.onOther();
				}
				catch(InvalidTransitionException e)
				{
				  throw e;
				}
			}
		}
		return validEndState || state.getClass() == SuffixState.class;
	}
	
	
	/**
	 * Abstract class which represents a state in the FSM. All other state classes
	 * extend this class.
	 *
	 */
	public abstract class State {
		/**
		 * Method which will modify a state when the character is on a letter.
		 * @throws InvalidTransitionException
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		/**
		 * Method which will modify a state when the character is on a digit.
		 * @throws InvalidTransitionException
		 */
		public abstract void onDigit() throws InvalidTransitionException;;
		
		/**
		 * Modifies all types of state classes if the character is not a letter or a digit.
		 */
		public void onOther() throws InvalidTransitionException {
			//if (Character.)
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Class which represents the 'Initial State' of the FSM
	 *
	 */
	public class InitialState extends State {
		
		/**
		 * Modifies the suffix state if the character is a letter.
		 */
		public void onLetter() {
			state = new LetterState();
				//letterCount++;
			}
		
		/**
		 * Modifies the suffix state if the character is a digit.
		 */
		public void onDigit() throws InvalidTransitionException {
			
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * Class which represents the 'Letter State' of the FSN
	 *
	 */
    public class LetterState extends State {

		/**
		 * Modifies the suffix state if the character is a letter.
		 */
        public void onLetter() throws InvalidTransitionException {
			if (letterCount > 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
		    }
			
			
        }
		/**
		 * Modifies the suffix state if the character is a digit.
		 */
		public void onDigit() {
			state = new NumberState();			
		}
	}
		
    /**
     * Class which represents 'Number State' of the FSM.
     *
     */
    public class NumberState extends State {
		
    	/**
    	 * Modifies the NumberState if the character is a letter.
    	 */
        public void onLetter() throws InvalidTransitionException {
        	
        	if(digitCount != 3)
        	{
        		throw new InvalidTransitionException("Course name must have 3 digits.");
        	}
        	
        	else {
            	state = new SuffixState();
        	}
        }
		
        /**
         * Modifies the NumberState if the character is a digit.
         */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount > 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
		    }
			else if (digitCount == 3) {
				validEndState = true;
			}
		}
    
	}
	
	
	/**
	 * Class which represents 'Suffix State' of the FSN.
	 *
	 */
	public class SuffixState extends State {
		
		/**
		 * Modifies the suffix state if the character is a letter.
		 */
        public void onLetter() throws InvalidTransitionException {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}

		
		/**
		 * Modifies the suffix state if the character is a digit.
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
		
	}
}
	
	

