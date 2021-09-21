package test;

import lab.shiryaeva.Calculator;
import lab.shiryaeva.OperationType;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class CalculatorDynamicTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
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