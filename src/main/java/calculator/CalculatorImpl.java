package calculator;

import calculator.exception.NumberInvalidException;
import calculator.exception.OperationInvalidException;

import java.math.BigDecimal;

import static calculator.util.CalculatorUtils.convert;

public class CalculatorImpl<N extends Number> implements Calculator<N> {

    private N value;

    public CalculatorImpl() throws NumberInvalidException {
        this((N)BigDecimal.valueOf(0l));
    }

    public CalculatorImpl(N num) throws NumberInvalidException {
        if(num == null) {
            throw new NumberInvalidException(NumberInvalidException.INVALID_NUMBER);
        }
        this.value = num;
    }

    public N calculate(Operation op, N num1, N num2) throws OperationInvalidException {
        switch(op) {
            case ADD:
                return (N) convert(num1).add(convert(num2));
            case SUBTRACT:
                return (N) convert(num1).subtract(convert(num2));
            case MULTIPLY:
                return (N) convert(num1).multiply(convert(num2));
            case DIVIDE:
                return (N) convert(num1).divide(convert(num2));
        }
        throw new OperationInvalidException("Operation = " + op.toString() + " is not supported");
    }

    @Override
    public Calculator add(N n) throws OperationInvalidException {
        this.value = calculate(Operation.ADD, this.value, n);
        return this;
    }

    @Override
    public Calculator subtract(N n) throws OperationInvalidException {
        this.value = calculate(Operation.SUBTRACT, this.value, n);
        return this;
    }

    @Override
    public Calculator multiply(N n) throws OperationInvalidException {
        this.value = calculate(Operation.MULTIPLY, this.value, n);
        return this;
    }

    @Override
    public Calculator divide(N n) throws OperationInvalidException {
        this.value = calculate(Operation.DIVIDE, this.value, n);
        return this;
    }

    @Override
    public N value() {
        return this.value;
    }


}
