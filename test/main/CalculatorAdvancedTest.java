package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**This class is the test class that is used to test calculator and his advances operations, 
 * like exponentiation and factorial.
 * @author Igor Grubisa
 * @version 1.0
 * @since 11.02.2024.
 */
class CalculatorAdvancedTest {
 
	/**
	 * This is the object that will be tested.
	 */
	private CalculatorAdvanced calcAdvanced = new CalculatorAdvanced();
	
	@BeforeAll 
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
 
	@BeforeEach
	void setUp() throws Exception {
		calcAdvanced.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * This test is used to check if advanced calculator has initialized correctly. 
	 */
	@Test
	void testCalculatorAdvanced() {
		assertThat(calcAdvanced, is(notNullValue()));
		assertThat(0.0, is(calcAdvanced.getCurrentValue()));
	}
	
	/**Test with parameters to check if advanced arithmetic operation (exponentiation) of calculator
	 * is working correctly. 
	 * @param currentValue First parameter is the number on which operation will be performed. 
	 * @param expectedResult This parameter is the expected result after performing exponentiation.
	 * @param action This parameter indicates which operation will be performed.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation (if he passes non-digit character or anything different than '!').
	 * @throws NumberNotInAreaException This type of exception will be thrown if user passes the number that
	 * is not within range 0-10.
	 */
	@ParameterizedTest
	@MethodSource("parametersForExponentiation")
	void testExponentiation(Double currentValue, Double expectedResult, char action) 
			throws NotSupportedOperationException, NumberNotInAreaException {
		
		calcAdvanced.setCurrentValue(currentValue);
		calcAdvanced.calculateAdvanced(action);
		assertThat(expectedResult, is(calcAdvanced.getCurrentValue()));
	}
	
	/**Method which creates parameters for testExponentiation.
	 * @return This returns parameters for exponentiation. 
	 */
	private static Stream<Arguments> parametersForExponentiation(){
		return Stream.of(Arguments.of(5d,25d,'2'), Arguments.of(-5d,25d,'2'), Arguments.of(-5d,-125d,'3'), Arguments.of(2.5d,4d,'2'),
						Arguments.of(-2.5d,4d,'2'), Arguments.of(-2.5d,-8d,'3'), Arguments.of(0d,0d,'2'), 
						Arguments.of(5d,1d,'0'), Arguments.of(0d,1d,'0'), 
						Arguments.of(1d,1d,'7'));
	}
	
	/**Test with parameters to check if advanced arithmetic operation (factorial) of calculator
	 * is working correctly. 
	 * @param currentValue First parameter is the number on which operation will be performed. 
	 * @param expectedResult This parameter is the expected result after performing factorial 
	 * on the current value.  
	 * @param action This parameter indicates which operation will be performed.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user attempt to perform 
	 * 			unsupported arithmetic operation (if he passes non-digit character or anything different than '!').
	 * @throws NumberNotInAreaException This type of exception will be thrown if user passes the number that
	 * is not within range 0-10.
	 */
	@ParameterizedTest
	@MethodSource("parametersForFactorial")
	void testFactoriel(Double currentValue, Double expectedResult, char action) 
			throws NotSupportedOperationException, NumberNotInAreaException {
		
		calcAdvanced.setCurrentValue(currentValue);
		calcAdvanced.calculateAdvanced(action);
		assertThat(expectedResult, is(calcAdvanced.getCurrentValue()));
	}
	
	/**Method which creates parameters for method testFactorial.
	 * @return This returns parameters for factorial. 
	 */
	private static Stream<Arguments> parametersForFactorial(){
		return Stream.of(Arguments.of(0d,1d,'!'), Arguments.of(1d,1d,'!'), Arguments.of(5d,120d,'!'),
						Arguments.of(9.99d,362880d,'!'), Arguments.of(0.1,1d,'!'), Arguments.of(10d,3628800d,'!'));
	}
	
	/**Test with parameters to check exceptions which can be thrown while performing exponentiation or factorial.
	 * @param currentValue First parameter is the number on which operation will be performed. 
	 * @param action This parameter indicates which operation will be performed.
	 * @param exception This parameter is the exception that expected to be thrown.
	 * NotSupportedOperationException will be thrown if user passes non-digit character or anything different than '!'.
	 * NumberNotInAreaException will be thrown if user the number that is not within range 0-10.
	 */
	@ParameterizedTest
	@MethodSource("parametersForThrowingExceptions")
	void testCalculateAdvancedExceptions(Double currentValue, char action, Class<? extends Exception> exception) {
		calcAdvanced.setCurrentValue(currentValue);
		Exception ex = assertThrows(exception, () -> calcAdvanced.calculateAdvanced(action));
		if (ex instanceof NumberNotInAreaException)
			assertThat(ex.getMessage(), containsString("range [0,10]"));
		if (ex instanceof NotSupportedOperationException)
			assertThat(ex.getMessage(), containsString("Action"));
	}
	
	/**Method which creates parameters for method testCalculateAdvancedExceptions.
	 * @return This returns parameters which causes exceptions to be thrown. 
	 */
	private static Stream<Arguments> parametersForThrowingExceptions(){
		return Stream.of(Arguments.of(-1d,'!', NumberNotInAreaException.class), Arguments.of(11d,'!', NumberNotInAreaException.class),
						 Arguments.of(-0.1d,'!', NumberNotInAreaException.class), Arguments.of(10.01d,'!', NumberNotInAreaException.class),
					Arguments.of(11d,'S', NotSupportedOperationException.class), Arguments.of(9d,'?', NotSupportedOperationException.class));
	}	

	/**Test with parameters to check if method for finding characteristic (Armstrong or perfect number)
	 *  of current value of calculator is working correctly.  
	 * @param currentValue First parameter is current value of calculator and this number will be tested if Armstrong or perfect.
	 * @param characteristic This parameter indicates which characteristic of the number will be searched. 
	 * @param expectedResult True if the number is Armstrong or perfect, false otherwise.
	 * @throws NotSupportedOperationException This type of exception will be thrown if user passes 
	 * anything else than 'A' or 'P'. 
	 * @throws NumberNotInAreaException This type of exception will be thrown if the integer part
	 * of current value of calculator is smaller than 1.
	 */
	@ParameterizedTest
	@MethodSource("parametersForCharacteristic")
	void testHasCharacteristic(Double currentValue, char characteristic, boolean expectedResult) 
			throws NotSupportedOperationException, NumberNotInAreaException {
		
		calcAdvanced.setCurrentValue(currentValue);
		assertThat(expectedResult, is(calcAdvanced.hasCharacteristic(characteristic)));
	}
	
	/**Method which creates parameters for testHasCharacteristic.
	 * @return This returns parameters for testHasCharacteristic. 
	 */
	private static Stream<Arguments> parametersForCharacteristic(){
		return Stream.of(Arguments.of(153d,'A', true), Arguments.of(153.475d,'A', true), Arguments.of(1634d,'A', true), 
						Arguments.of(154d,'A', false), Arguments.of(1635.04d,'A', false), Arguments.of(3d,'A', true), 
						Arguments.of(28d,'P', true), Arguments.of(8128.154d,'P', true), Arguments.of(8130.1d,'P', false), 
						Arguments.of(1.02,'P', false), Arguments.of(30d,'P', false), Arguments.of(1d,'A', true) );
	}	
	
	/**Test with parameters to check exceptions which can be thrown while checking if the number is Armstrong or perfect.
	 * @param currentValue First parameter is current value of calculator and this number will be tested if Armstrong or perfect.
	 * @param characteristic This parameter indicates which characteristic of the number will be searched. 
	 * @param exception This parameter is the exception that expected to be thrown.
	 * NotSupportedOperationException will be thrown if user passes anything else than 'A' or 'P'. 
	 * NumberNotInAreaException will be thrown if the integer part of current value of calculator is smaller than 1.
	 */
	@ParameterizedTest
	@MethodSource("parametersForCharacteristicExceptions")
	void testHasCharacteristicExceptions(Double currentValue, char characteristic, Class<? extends Exception> exception) {
		
		calcAdvanced.setCurrentValue(currentValue);
		Exception ex = assertThrows(exception, () -> calcAdvanced.hasCharacteristic(characteristic));
		if (ex instanceof NumberNotInAreaException)
			assertThat(ex.getMessage(), containsString("Integer part"));
		if (ex instanceof NotSupportedOperationException)
			assertThat(ex.getMessage(), containsString("Characteristic"));
	}
	
	/**Method which creates parameters for method testHasCharacteristicExceptions.
	 * @return This returns parameters which causes exceptions to be thrown. 
	 */
	private static Stream<Arguments> parametersForCharacteristicExceptions(){
		return Stream.of(Arguments.of(0.99,'A',NumberNotInAreaException.class), Arguments.of(0.99,'P',NumberNotInAreaException.class),
						Arguments.of(-2d,'A',NumberNotInAreaException.class), Arguments.of(1.1,'h', NotSupportedOperationException.class),
						Arguments.of(0.99,'m', NotSupportedOperationException.class));
	}

}
