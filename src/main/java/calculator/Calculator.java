package calculator;

import calculator.exception.NumberInvalidException;
import calculator.exception.OperationInvalidException;

import java.math.BigDecimal;

/**
 * underlying computation is conducted with BigDecimal datatype to preserve precision
 */
public interface Calculator {

    /**
     * calculates operand num1 by operand num2 using operator
     * @param op the operator
     * @param num1 the first operand
     * @param num2 the second operand
     * @return the result in BigDecimal type
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    BigDecimal calculate(Operation op, Number num1, Number num2) throws OperationInvalidException, NumberInvalidException;

    /**
     * calculates operand num1 by operand num2 using operator
     * @param op the operator
     * @param scale the precision after the decimal point; useful for non-terminating operations
     * @param num1 the first operand of type Number
     * @param num2 the second operand of type Number
     * @return the result in BigDecimal type
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    BigDecimal calculate(Operation op, Integer scale, Number num1, Number num2) throws OperationInvalidException, NumberInvalidException;

    /**
     * adds an operand to a pre-existing result
     * @param n the operand of type Number
     * @return this instance
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    Calculator add(Number n) throws OperationInvalidException, NumberInvalidException;

    /**
     * subracts an operand from a pre-existing result
     * @param n the operand of type Number
     * @return this instance
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    Calculator subtract(Number n) throws OperationInvalidException, NumberInvalidException;

    /**
     * multiplies an operand to a pre-existing result
     * @param n the operand of type Number
     * @return this instance
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    Calculator multiply(Number n) throws OperationInvalidException, NumberInvalidException;

    /**
     * divides an operand from a pre-existing result; includes default precision
     * @param n the operand of type Number
     * @return this instance
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    Calculator divide(Number n) throws OperationInvalidException, NumberInvalidException;

    /**
     * divides an operand from a pre-existing result; includes defined precision
     * @param n the operand of type Number
     * @scale the precision after the decimal point for floating point number types
     * @return this instance
     * @throws OperationInvalidException if the operator is invalid
     * @throws NumberInvalidException if an operand is invalid
     */
    Calculator divide(Number n, Integer scale) throws OperationInvalidException, NumberInvalidException;

    BigDecimal value();
}
