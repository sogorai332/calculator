package calculator;

import calculator.exception.NumberInvalidException;
import calculator.exception.OperationInvalidException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.Assert.assertEquals;

public class CalculatorImplTest {

    private Calculator calculator = null;

    public CalculatorImplTest() throws NumberInvalidException {
    }

    private static Stream<Arguments> getTestCalculateParameters() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, (byte) 3, (byte) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, (short) 3, (short) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, 3, 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, (long) 3, (long) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, new AtomicInteger(3), (long) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, new AtomicLong(3), (long) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null,  new LongAccumulator(Long::sum, 3), (long) 9),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, new LongAdder(), (long) 12),
                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, null, BigInteger.valueOf(3), (long) 9),
                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, null, (float) 1.1, (float) 0.2),
                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, null, 1.1, 0.2),
                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, null, new DoubleAccumulator(Double::sum, 1.1), 0.2),
                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, null, new DoubleAdder(), 1.3),
                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, null, BigDecimal.valueOf(1.1), 0.2),
                Arguments.of(BigDecimal.valueOf(Integer.MAX_VALUE).multiply(BigDecimal.valueOf(2)),
                        Operation.ADD, null, Integer.MAX_VALUE, Integer.MAX_VALUE),
                Arguments.of(BigDecimal.valueOf(Long.MAX_VALUE).multiply(BigDecimal.valueOf(2)),
                        Operation.ADD, null, Long.MAX_VALUE, Long.MAX_VALUE),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, (byte) 3, (byte) 9),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, (short) 3, (short) 9),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, 3, 9),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, (long) 3, (long) 9),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, (float) 3, (float) 9),
                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, null, (double) 3, (double) 9),
                Arguments.of(BigDecimal.valueOf(Long.MIN_VALUE).subtract(BigDecimal.valueOf(Long.MAX_VALUE)),
                        Operation.SUBTRACT, null, Long.MIN_VALUE, Long.MAX_VALUE),
                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, null, (byte) 3, (byte) 9),
                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, null, (short) 3, (short) 9),
                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, null, 3, 9),
                Arguments.of(BigDecimal.valueOf(27),  Operation.MULTIPLY, null, (long) 3, (long) 9),
                Arguments.of(BigDecimal.valueOf(27),  Operation.MULTIPLY, null, (float) 3, (float) 9),
                Arguments.of(BigDecimal.valueOf(27),  Operation.MULTIPLY, null, (double) 3, (double) 9),
                Arguments.of(BigDecimal.valueOf(Long.MAX_VALUE).multiply(BigDecimal.valueOf(Long.MAX_VALUE)),
                        Operation.MULTIPLY, null, Long.MAX_VALUE, Long.MAX_VALUE),
                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, null, (byte) 10, (byte) 2),
                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, null, (short) 10, (short) 2),
                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, null, 10, 2),
                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, null, (long) 10, (long) 2),
                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, null, (float) 10, (float) 2),
                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, null, (double) 10, (double) 2),
                Arguments.of(BigDecimal.valueOf(0.3333333333),  Operation.DIVIDE, 10,  1, 3),
                Arguments.of(BigDecimal.valueOf(0.3333333333),   Operation.DIVIDE, 10, 1.0, 3.0),
                Arguments.of(BigDecimal.valueOf(0), Operation.DIVIDE, 10, 1, Long.MAX_VALUE));
    }


    @ParameterizedTest
    @MethodSource("getTestCalculateParameters")
    void testCalculate(BigDecimal expected, Operation op, Integer scale, Number num1, Number num2) throws NumberInvalidException, OperationInvalidException {
        Calculator calculator = new CalculatorImpl();
        assertEquals(expected, calculator.calculate(op, scale, num1, num2));
    }

    @Test
    public void testAdd() throws OperationInvalidException, NumberInvalidException {
        Number expected = BigDecimal.valueOf(5);
        calculator = new CalculatorImpl(2);
        Number actual = calculator.add(3).value();
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtract() throws OperationInvalidException, NumberInvalidException {
        Number expected = BigDecimal.valueOf(2);
        calculator = new CalculatorImpl(5);
        Number actual = calculator.subtract(3).value();
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiply() throws OperationInvalidException, NumberInvalidException {
        Number expected = BigDecimal.valueOf(10);
        calculator = new CalculatorImpl(5);
        Number actual = calculator.multiply(2).value();
        assertEquals(expected, actual);
    }

    @Test
    public void testDivide() throws OperationInvalidException, NumberInvalidException {
        Number expected = BigDecimal.valueOf(3);
        calculator = new CalculatorImpl(6);
        Number actual = calculator.divide(2).value();
        assertEquals(expected, actual);
    }

    @Test(expected = NumberInvalidException.class)
    public void testDivideByZero() throws OperationInvalidException, NumberInvalidException {
        Number expected = BigDecimal.valueOf(3);
        calculator = new CalculatorImpl(6);
        Number actual = calculator.divide(0).value();
        assertEquals(expected, actual);
    }

    @Test
    public void startCalculator() throws NumberInvalidException, OperationInvalidException {
        assertEquals( BigDecimal.valueOf(1), new CalculatorImpl(1).value());
        assertEquals( BigDecimal.valueOf(1), new CalculatorImpl(1)
                .subtract(1).add(1)
                .multiply(1).divide( 1).value());
        assertEquals(BigDecimal.valueOf(1), new CalculatorImpl(1.0)
                .subtract(1.0).add( 1.0)
                .multiply( 1.0).divide(1.0).value());
        Calculator calculator = new CalculatorImpl(11);
        assertEquals(BigDecimal.valueOf(11), calculator.value());
        calculator.add( 1.5);
        assertEquals( BigDecimal.valueOf(12.5), calculator.value());
        calculator.subtract( (float) 2.5).multiply((long) 5);
        assertEquals(BigDecimal.valueOf(50), calculator.value());
    }

}
