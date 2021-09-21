package lab.shiryaeva;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator implements KeyListener {

    private static final String FIRST_NUMBER = "Please enter the first number: ";
    private static final String SECOND_NUMBER = "Please enter the second number: ";
    private static final String BINARY_VALUE = "Binary value of %s is ";
    private static final String OPERATION = "Please enter an operation. Possible options:";
    private static final String OPERATION_OPTIONS = "[+] plus     \t  [-] minus       \t [*] times     \t [/] division      \t [^] xor";
    private static final String INVALID_INPUT = "Invalid input. Please reenter";
    private static final String ZERO_DIVISION_ERROR = "The second number is zero. You cannot divide by 0, please restart.";
    private static final String RESULT = "Result: ";
    private static final String DECIMAL_RESULT = "Binary result: ";
    private static final String EXIT_MESSAGE = "Press Enter to try once again. Press ESC to exit";
    private static final String AGAIN_MESSAGE = "Oh, okay, here we go again";
    private static final String GOODBYE_MESSAGE = "Goodbye";

    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {

            System.out.println(FIRST_NUMBER);
            int first_number = getInputNumber();
            System.out.println(String.format( BINARY_VALUE, first_number) + decimalToBinary(first_number));

            System.out.println(SECOND_NUMBER);
            int second_number = getInputNumber();
            System.out.println(String.format( BINARY_VALUE, second_number) + decimalToBinary(second_number));

            OperationType operation = getInputOperation();

            int result = calculateResult(first_number, second_number, operation);
            String decimalResult = formatBinaryResult(decimalToBinary(result));

            System.out.println(RESULT + result);
            System.out.println(DECIMAL_RESULT + decimalResult);
            System.out.println();
            System.out.println(EXIT_MESSAGE);
            try {
                System.in.read();
            } catch (IOException e) {
                System.out.println(GOODBYE_MESSAGE);
            }
        }
    }

    private static int getInputNumber() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println(INVALID_INPUT);
        }
        return scanner.nextInt();
    }

    private static OperationType getInputOperation() {
        System.out.println(OPERATION);
        System.out.println(OPERATION_OPTIONS);
        try {
            String input = scanner.next();
            for (OperationType type : OperationType.values()) {
                if (type.getOperation().equalsIgnoreCase(input)) {
                    return type;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(INVALID_INPUT);
            scanner.nextLine();
        }
        return OperationType.NONE;
    }

    public int calculateResult(int first, int second, OperationType operation) {
        int result = 0;
        switch (operation) {
            case ADD:
                result = first + second;
                break;
            case SUBTRACT:
                result = first - second;
                break;
            case MULTIPLY:
                result = first * second;
                break;
            case DIVIDE:
                if (second == 0) {
                    System.out.println(ZERO_DIVISION_ERROR);
                    throw new ArithmeticException(ZERO_DIVISION_ERROR);
                } else {
                    result = (int) Math.round((double) first / (double) second);
                }
                break;
            case XOR:
                result = first ^ second;
                break;
        }
        return result;
    }

    public static String decimalToBinary(int number) {
        return Integer.toBinaryString(number);
    }

    public static String formatBinaryResult(String result) {
        return String.format("%8s", result).replaceAll(" ", "0");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println(GOODBYE_MESSAGE);
            System.exit(0);
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println(GOODBYE_MESSAGE);
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println(AGAIN_MESSAGE);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}