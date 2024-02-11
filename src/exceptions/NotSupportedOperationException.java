package exceptions;

/**Custom made class which extends Exception class. 
 * This type of exception will be thrown if user tried to perform unsupported arithmetic operation. 
 * @author Igor Grubisa
 * @version 1.0
 * @since 10.02.2024.
 */
public class NotSupportedOperationException extends Exception {
	
	/**Constructor with parameter which propagates custom messages to super class. 
	 * @param message Custom message which will be propagates to super class. 
	 */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}