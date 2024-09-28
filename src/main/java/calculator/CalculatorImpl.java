package calculator;

import calculator.exception.NumberInvalidException;
import calculator.exception.OperationInvalidException;

import java.math.BigDecimal;

/**
 * @author Soumo Gorai
 * @see calculator.Calculator
 */
public class CalculatorImpl implements Calculator {

    private BigDecimal value;

    public CalculatorImpl() throws NumberInvalidException {
        this(null);
    }

    public CalculatorImpl(Number num) throws NumberInvalidException {
        if(null != num) {
            this.value = new BigDecimal(num.toString());
        }
    }

    @Override
    public BigDecimal calculate(Operation op, Number num1, Number num2) throws OperationInvalidException, NumberInvalidException {
        return calculate(op, null, num1, num2);
    }

    @Override
    public BigDecimal calculate(Operation op, Integer scale, Number num1, Number num2) throws OperationInvalidException, NumberInvalidException {
        this.checkValue(num1);
        this.checkValue(num2);

        BigDecimal numA = new BigDecimal(num1.toString());
        BigDecimal numB = new BigDecimal(num2.toString());
        BigDecimal result = null;

        switch(op) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
                result = op.execute(scale, numA, numB);
                break;
            case DIVIDE:
                if (BigDecimal.ZERO.equals(numB)) {
                    throw new NumberInvalidException("Error. Please insert valid denominator.");
                }
                result = op.execute(scale, numA, numB);
                break;
            default:
                throw new OperationInvalidException("Operation = " + op.toString() + " is not supported");
        }
       return new BigDecimal(result.stripTrailingZeros().toPlainString());

    }

    @Override
    public Calculator add(Number n) throws OperationInvalidException, NumberInvalidException {
        this.value = calculate(Operation.ADD, this.value, n);
        return this;
    }

    private void checkValue(Number value) throws NumberInvalidException {
        if(null == value) {
            throw new NumberInvalidException("value cannot be null. Please check constructor usage.");
        }
    }

    @Override
    public Calculator subtract(Number n) throws OperationInvalidException, NumberInvalidException {
        this.value = calculate(Operation.SUBTRACT, this.value, n);
        return this;
    }

    @Override
    public Calculator multiply(Number n) throws OperationInvalidException, NumberInvalidException {
        this.value = calculate(Operation.MULTIPLY, this.value, n);
        return this;
    }

    @Override
    public Calculator divide(Number n) throws OperationInvalidException, NumberInvalidException {
        this.value = calculate(Operation.DIVIDE, this.value, n);
        return this;
    }

    @Override
    public Calculator divide(Number n, Integer scale) throws OperationInvalidException, NumberInvalidException {
        this.value = calculate(Operation.DIVIDE, scale, this.value, n);
        return this;
    }

    @Override
    public BigDecimal value() {
        return this.value;
    }


}
