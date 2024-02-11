package main;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**CalculatorAdvances class provides basic and advanced arithmetic operations 
 * and extends Calculator class.
 * @author Igor Grubisa
 * @version 1.0
 * @since 11.02.2024.
 */ 
public class CalculatorAdvanced extends Calculator {
	
	/**
	 * Default constructor of the CalculatorAdvanced class.
	 */
	public CalculatorAdvanced() {
		
	}
	
	/**This method finds power of the number and calculate factorial for 
	 * the number within the range 0-10.
	 * @param action This parameter marks the operation it will be performed 
	 * on the current value of calculator.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws NumberNotInAreaException This type of exception will be thrown if user passes the number that
	 * is not within range 0-10.
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		
		if (!Character.isDigit(action) && action != '!')
			throw new NotSupportedOperationException("Action "+ action + " is not supported!");
		
		int result;
		// radi stepenovanje 
		if (Character.isDigit(action)) {
			result = powerOfNumber(getCurrentValue().intValue(), Character.getNumericValue(action));
		}
		// radi faktorisanje
		else {
			if (getCurrentValue() < 0.0 || getCurrentValue() > 10.0)
				throw new NumberNotInAreaException("The currentValue of calculator is not whitin range [0,10]!");
			
			result = 1;
			for (int i=1; i<=getCurrentValue().intValue();i++)
				result*=i;
		}

		setCurrentValue((double) result);
	}
	
	/**Auxiliary method used for calculating power of the number. 
	 * @param base This parameter is the current value of calculator. 
	 * @param exponent This parameter indicates how many times base number
	 * will be multiply by themselves. 
	 * @return Return the result of exponentiation.
	 */
	private int powerOfNumber(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) 
            result *= base;  
		
		return result; 
	}

	/**Second arithmetic method of CalculatorAdvances class that check if current value
	 * of calculator is Armstrong or perfect number. 
	 * @param value This parameter indicates which characteristic of the number will be searched. 
	 * @return Return true if the current value of calculator is Armstrong or perfect number. 
	 * @throws NotSupportedOperationException This type of exception will be thrown if user passes 
	 * anything else than 'A' or 'P'. 
	 * @throws NumberNotInAreaException This type of exception will be thrown if the integer part
	 * of current value of calculator is smaller than 1.
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		
		if (value != 'A' && value != 'P')
			throw new NotSupportedOperationException("Characteristic "+ value + " is not supported!");
		if (getCurrentValue().intValue() < 1)
			throw new NumberNotInAreaException("Integer part of the currentValue of calculator is < 1!"); 
		
		if (value == 'A' && isArmstrongNumber(getCurrentValue().intValue()))
			return true;
		if (value == 'P' && isPerfectNumber(getCurrentValue().intValue()))
			return true;
		
		return false;
	}

	/**Auxiliary method used for testing if the passed number is Armstrong.
	 * @param number This parameter is the number which will be tested. 
	 * @return Return true if passed number is Armstrong, false otherwise. 
	 */
	private boolean isArmstrongNumber(int number) {
		
		int temp, lastDigit, digits=0, result = 0;
        temp = number;
        while(temp>0)    
        {   
        	temp = temp/10;   
        	digits++;   
        }

        temp = number;
        while (temp != 0)
        {
            lastDigit = temp % 10;
            int stepen=lastDigit;
            for (int i=1; i<digits; i++)
            	stepen*=lastDigit;
            result += stepen;
            temp /= 10;
        }

        if (result == number)
            return true;
        else
            return false;
	}
	
	/**Auxiliary method used for testing if the passed number is perfect number.
	 * @param number This parameter is the number which will be tested. 
	 * @return Return true if passed number is perfect number, false otherwise. 
	 */
	private boolean isPerfectNumber(int number) {
		 
		if (number == 1)
            return false;
 
        int sum = 1;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
 
        if (sum == number)
            return true;
		return false;
	}
	
}
