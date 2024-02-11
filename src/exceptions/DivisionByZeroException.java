package exceptions;

/**
 * Custom made class which extends Exception class. 
 * This type of exception will be thrown if user tried to divide number by zero. 
 * @author Igor Grubisa
 * @version 1.0
 * @since 10.02.2024.
 */
public class DivisionByZeroException extends Exception {
	
	/**Default message when DivisionByZeroException has been thrown. 
	 */
	private static final String DIVISION_BY_ZERO_MSG = "Division by zero is not allowed!";
	
	/**Default constructor which propagates default message to super class.
	 */
	public DivisionByZeroException() {
		super(DIVISION_BY_ZERO_MSG);
	}
	
}
