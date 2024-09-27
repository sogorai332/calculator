package calculator;

import calculator.exception.OperationInvalidException;

public interface Calculator<N extends Number> {

    N calculate(Operation op, N num1, N num2) throws OperationInvalidException;

    Calculator add(N n) throws OperationInvalidException;

    Calculator subtract(N n) throws OperationInvalidException;

    Calculator multiply(N n) throws OperationInvalidException;

    Calculator divide(N n) throws OperationInvalidException;

    N value();
}
