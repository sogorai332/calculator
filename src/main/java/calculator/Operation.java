package calculator;

import calculator.exception.OperationInvalidException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * defines the operators for the calculator
 * operands are of BigDecimal type to preserve precision
 */
public enum Operation {

    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public BigDecimal execute(Integer scale, BigDecimal num1, BigDecimal num2) throws OperationInvalidException {
        switch (this) {
            case ADD:
                return num1.add(num2);
            case SUBTRACT:
                return num1.subtract(num2);
            case MULTIPLY:
                return num1.multiply(num2);
            case DIVIDE:
                if(null != scale) {
                    return num1.divide(num2, scale, RoundingMode.HALF_EVEN);
                }
                return num1.divide(num2);
        }
        throw new OperationInvalidException("Operation is not supported");

    }
}