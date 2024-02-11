package main;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**Calculator class provides basic arithmetic operations, such as addition, subtraction, 
 * multiplication and division.
 * @author Igor Grubisa
 * @version 1.0
 * @since 10.02.2024.
 */
public class Calculator {
	
	/**Attribute which represents current value of calculator. 
	 */
	private Double currentValue;
	
	/**Constructor of the Calculator class.
	 */
	public Calculator() { 
	} 
	
	/**The main method of the Calculator class and performs all arithmetic logic. 
	 * If user passes wrong parameter for operation, it will throw NotSupportedOperationException.
	 * If user try to divide by zero, it will throw DivisionByZeroException.
	 * @param value This parameter is the number on which will be performed given arithmetic operation.
	 * @param operator This parameter marks arithmetic operation that will be performed. 
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws DivisionByZeroException This type of exception will be thrown if user attempt to divide by zero. 
	 */
	public void calculate(Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {			
		
		Double result = null;
		switch (operator) {
		case '+': result = currentValue + value;
					break;
		case '-': result = currentValue - value;
					break;
		case '*': result = currentValue * value;
					break;
		case '/': if (value == 0)
					throw new DivisionByZeroException();
				  result = currentValue / value;
					break;
		default: throw new NotSupportedOperationException("Operator "+ operator + " is not supported!");
		}
		
		currentValue = result;
	}

	/**This method is getter for the attribute currentValue of Calculator class.
	 * @return Returns current value of the calculator. 
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**This method is setter for the attribute currentValue of Calculator class. 
	 * @param currentValue This parameter is double value to be set as currentValue of calculator. 
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	
}

