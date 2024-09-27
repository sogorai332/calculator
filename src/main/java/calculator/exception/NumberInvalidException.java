package calculator.exception;

public class NumberInvalidException extends Exception {

    public static String INVALID_NUMBER = "The number is invalid";

    public NumberInvalidException(String message) {
        super(message);
    }
}
