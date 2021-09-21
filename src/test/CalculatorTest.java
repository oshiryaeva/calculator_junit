package test;

import lab.shiryaeva.Calculator;
import lab.shiryaeva.OperationType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class CalculatorTest {

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
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 653, delimiter = '\u003B')
    void testXor(int a, int b, int add, int subtract, int multiply, int divide, int xor) {
        assertEquals(xor, calculator.calculateResult(a, b, OperationType.XOR));
    }

    private DynamicTest createTest(final int a, final int b, final OperationType operator, final int expected) {
        String displayName = String.format(Locale.getDefault(), "When we %s them then the result is %s", operator.name(), expected);
        return dynamicTest(displayName, () -> {
            int result = calculator.calculateResult(a, b, operator);
            assertEquals(expected, result);
        });
    }

    @TestFactory
    @DisplayName("Set of dynamic tests given inputs of Ten and Two")
    Collection<DynamicTest> dynamicFactory() {
        final int a = 10;
        final int b = 2;
        Set<DynamicTest> tests = new LinkedHashSet<>(6);
        tests.add(createTest(a, b, OperationType.ADD, 12));
        tests.add(createTest(a, b, OperationType.SUBTRACT, 8));
        tests.add(createTest(a, b, OperationType.MULTIPLY, 20));
        tests.add(createTest(a, b, OperationType.DIVIDE, 5));
        tests.add(createTest(a, b, OperationType.XOR, 8));
        return tests;
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