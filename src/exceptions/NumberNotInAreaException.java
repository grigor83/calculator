package exceptions;

/**Custom made class which extends Exception class. 
 * This type of exception will be thrown when integer part of attribute currentValue is smaller than 1. 
 * @author Igor Grubisa
 * @version 1.0
 * @since 10.02.2024.
 */
public class NumberNotInAreaException extends Exception {
	
	/**Constructor with parameter which propagates custom messages to super class. 
	 * @param message Custom message which will be propagates to super class. 
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}
	
}
