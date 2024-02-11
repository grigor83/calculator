package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**This class is the test class that is used to test calculator and his basic operations, 
 * like addition, subtraction, multiplication and division.
 * @author Igor Grubisa
 * @version 1.0
 * @since 10.02.2024.
 */
class CalculatorTest {
	
	/**This is object which will be tested. 
	 */
	private Calculator calculator = new Calculator();
  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
 
	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**This test is used to check if calculator is initialized correctly. 
	 */
	@Test
	void testCalculator() {
		//assertNotNull(calculator);
		assertThat(calculator, is(notNullValue()));
		assertThat(0.0, is(calculator.getCurrentValue()));
	}
	
	
	/**This test is used to check if method of getting current value of calculator working correctly.
	 */
	@Test
	void testGetCurrentValue() {
		//assertEquals(0.0, calculator.getCurrentValue());
		assertThat(0.0, is(calculator.getCurrentValue()));
	}

	/**Test setting the current value of calculator.
	 */
	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(5.0);
		//assertEquals(5.0, calculator.getCurrentValue());
		assertThat(5.0, is(calculator.getCurrentValue()));
	}

	/**Test with parameters to check if addition working correctly. 
	 * @param a This is the first addend. 
	 * @param b This is the second addend.
	 * @param expectedResult This parameter is the expected result after addition a and b. 
	 * @param operator This parameter is used for mark operation, in this case it is +.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws DivisionByZeroException This type of exception will be thrown if user attempt to divide by zero. 
	 */
	@ParameterizedTest
	@MethodSource("parametersForAddition")
	void testAdditionParametrized(Double a, Double b, Double expectedResult, char operator) 
			throws NotSupportedOperationException, DivisionByZeroException {
		
		calculator.setCurrentValue(a);
		calculator.calculate(b, operator);
		//assertEquals(result, calculator.getCurrentValue());
		assertThat(expectedResult, is(calculator.getCurrentValue()));
	}

	/**Method which creates parameters for testAdditionParametrized.
	 * @return This returns parameters for addition. 
	 */
	private static Stream<Arguments> parametersForAddition(){
		return Stream.of(Arguments.of(2d,8d,10d,'+'), Arguments.of(-5d,6d,1d,'+'), Arguments.of(4d,-2d,2d,'+'), Arguments.of(2d,-8d,-6d,'+'), 
						 Arguments.of(0d,8d,8d,'+'),  Arguments.of(0d,-1d,-1d,'+'), Arguments.of(0d,0d,0d,'+'), Arguments.of(-2d,2d,0d,'+'));
	}
	
	/**Test with parameters to check if subtraction working correctly.
	 * @param a This is the first number from which second parameter b will be sub.
	 * @param b This is the second number which will be sub from first parameter a.
	 * @param expectedResult This parameter is the expected result after subtraction a and b. 
	 * @param operator This parameter is used for mark operation, in this case it is -.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws DivisionByZeroException This type of exception will be thrown if user attempt to divide by zero. 
	 */
	@ParameterizedTest
	@MethodSource("parametersForSubtraction")
	void testSubtractionParametrized(Double a, Double b, Double expectedResult, char operator) 
			throws NotSupportedOperationException, DivisionByZeroException {
		
		calculator.setCurrentValue(a);
		calculator.calculate(b, operator);
		assertThat(expectedResult, is(calculator.getCurrentValue()));
	}
	
	/**Method which creates parameters for testSubtractionParametrized.
	 * @return This returns parameters for subtraction. 
	 */
	private static Stream<Arguments> parametersForSubtraction(){
		return Stream.of(Arguments.of(20d,8d,12d,'-'), Arguments.of(5d,-6d,11d,'-'), Arguments.of(-4d,2d,-6d,'-'), Arguments.of(-2d,-8d,6d,'-'), 
						 Arguments.of(0d,8d,-8d,'-'),  Arguments.of(0d,-1d,1d,'-'), Arguments.of(0d,0d,0d,'-'), Arguments.of(-2d,-2d,0d,'-'));
	}
	
	/**Test with parameters to check if multiplication working correctly.
	 * @param a This is the first number to be multiplied. 
	 * @param b This is the second number to be multiplied.
	 * @param expectedResult This parameter is the expected result after multiplication a and b. 
	 * @param operator This parameter is used for mark operation, in this case it is *.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws DivisionByZeroException  This type of exception will be thrown if user attempt to divide by zero. 
	 */
	@ParameterizedTest
	@MethodSource("parametersForMultiplication")
	void testMultiplicationParametrized(Double a, Double b, Double expectedResult, char operator) 
			throws NotSupportedOperationException, DivisionByZeroException {
		
		calculator.setCurrentValue(a);
		calculator.calculate(b, operator);
		assertThat(expectedResult, is(calculator.getCurrentValue()));
	}
	
	/**Method which creates parameters for testMultiplicationParametrized.
	 * @return This returns parameters for multiplication. 
	 */
	private static Stream<Arguments> parametersForMultiplication(){
		return Stream.of(Arguments.of(2d,8d,16d,'*'), Arguments.of(5d,-6d,-30d,'*'), Arguments.of(-4d,2d,-8d,'*'), Arguments.of(-2d,-8d,16d,'*'), 
						Arguments.of(0d,8d,0d,'*'),  Arguments.of(7d,0d,0d,'*'), Arguments.of(0d,-7d,-0d,'*'), Arguments.of(-2d,0d,-0d,'*'),
						Arguments.of(0d,0d,0d,'*'));
	}
	
	/**Test with parameters to check if division working correctly.
	 * @param a This is the first number for division. 
	 * @param b This is the second number for division.
	 * @param expectedResult This parameter is the expected result after a divided by b. 
	 * @param operator This parameter is used for mark operation, in this case it is /.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation.
	 * @throws DivisionByZeroException This type of exception will be thrown if user attempt to divide by zero. 
	 */
	@ParameterizedTest
	@MethodSource("parametersForDivision")
	void testDivisionParametrized(Double a, Double b, Double expectedResult, char operator) 
			throws NotSupportedOperationException, DivisionByZeroException {
		
		calculator.setCurrentValue(a);
		calculator.calculate(b, operator);
		assertThat(expectedResult, is(calculator.getCurrentValue()));
	}
	
	/**Method which creates parameters for testDivisionParametrized.
	 * @return This returns parameters for division. 
	 */
	private static Stream<Arguments> parametersForDivision(){
		return Stream.of(Arguments.of(8d,2d,4d,'/'), Arguments.of(5d,2d,2.50d,'/'), Arguments.of(-4d,2d,-2d,'/'), 
						Arguments.of(-2d,-8d,0.25d,'/'), Arguments.of(0d,8d,0d,'/'),  Arguments.of(0d,-4d,-0d,'/'));
	}
	
	/**Test with parameters to check exceptions which can be thrown by division or unsupported operation.
	 * @param a This is the first number in this arithmetic operation. 
	 * @param b This is the second number in this arithmetic operation. 
	 * @param operator This parameter is used for mark operation, in this case it is / and u.
	 * @param exception This parameter is any class that extends Exception.
	 */
	@ParameterizedTest
	@MethodSource("parametersForThrowingExceptions")
	void testCalculateExceptionsParametrized(Double a, Double b, char operator, Class<? extends Exception> exception) {
		calculator.setCurrentValue(a);
		Exception ex = assertThrows(exception, () -> calculator.calculate(b, operator));
		if (ex instanceof DivisionByZeroException)
			assertThat(ex.getMessage(), containsString("zero"));
		if (ex instanceof NotSupportedOperationException)
			assertThat(ex.getMessage(), containsString("Operator"));
	}
	
	/**Method which creates parameters for testCalculateExceptionsParametrized.
	 * @return This returns parameters that will generate exceptions. 
	 */
	private static Stream<Arguments> parametersForThrowingExceptions(){
		return Stream.of(Arguments.of(8d,0d,'/', DivisionByZeroException.class), 
						Arguments.of(5d,2d,'u', NotSupportedOperationException.class));
	}
}
