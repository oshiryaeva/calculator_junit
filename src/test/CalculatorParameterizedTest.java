package test;

import lab.shiryaeva.Calculator;
import lab.shiryaeva.OperationType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorParameterizedTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1, delimiter = '\u003B')
    void testAdding(int a, int b, int add, int subtract, int multiply, int divide, String xor) {
        assertEquals(add, calculator.calculateResult(a, b, OperationType.ADD));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1, delimiter = '\u003B')
    void testSubtraction(int a, int b, int add, int subtract, int multiply, int divide, String xor) {
        assertEquals(subtract, calculator.calculateResult(a, b, OperationType.SUBTRACT));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1, delimiter = '\u003B')
    void testMultiplication(int a, int b, int add, int subtract, int multiply, int divide, String xor) {
        assertEquals(multiply, calculator.calculateResult(a, b, OperationType.MULTIPLY));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1, delimiter = '\u003B')
    void testDivision(int a, int b, int add, int subtract, int multiply, int divide, String xor) {
        assertEquals(divide, calculator.calculateResult(a, b, OperationType.DIVIDE));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () ->
                calculator.calculateResult(64, 0, OperationType.DIVIDE));
    }

    // Here we skip the lines of incorrect XOR values in CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 75, delimiter = '\u003B')
    void testXor(int a, int b, int add, int subtract, int multiply, int divide, int xor) {
        assertEquals(xor, calculator.calculateResult(a, b, OperationType.XOR));
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("So long and thanks for all the fish");
    }
}