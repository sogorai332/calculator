package calculator;

import calculator.exception.NumberInvalidException;
import calculator.exception.OperationInvalidException;
import org.junit.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class CalculatorImplTest {

    private Calculator calculator = null;

    public CalculatorImplTest() throws NumberInvalidException {
    }

//    private static Stream<Arguments> getTestCalculateParameters() {
//        return Stream.of(
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, (byte) 3, (byte) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, (short) 3, (short) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, 3, 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, (long) 3, (long) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, new AtomicInteger(3), (long) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, new AtomicLong(3), (long) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, new LongAccumulator(Long::sum, 3), (long) 9),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, new LongAdder(), (long) 12),
//                Arguments.of(BigDecimal.valueOf(12), Operation.ADD, BigInteger.valueOf(3), (long) 9),
//                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, (float) 1.1, (float) 0.2),
//                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, 1.1, 0.2),
//                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, new DoubleAccumulator(Double::sum, 1.1), 0.2),
//                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, new DoubleAdder(), 1.3),
//                Arguments.of(BigDecimal.valueOf(1.3), Operation.ADD, BigDecimal.valueOf(1.1), 0.2),
//                Arguments.of(BigDecimal.valueOf(Integer.MAX_VALUE).multiply(BigDecimal.valueOf(2)), 10,
//                        Operation.ADD, Integer.MAX_VALUE, Integer.MAX_VALUE),
//                Arguments.of(BigDecimal.valueOf(Long.MAX_VALUE).multiply(BigDecimal.valueOf(2)), 10,
//                        Operation.ADD, Long.MAX_VALUE, Long.MAX_VALUE),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, (byte) 3, (byte) 9),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, (short) 3, (short) 9),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, 3, 9),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, (long) 3, (long) 9),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, (float) 3, (float) 9),
//                Arguments.of(BigDecimal.valueOf(-6), Operation.SUBTRACT, (double) 3, (double) 9),
//                Arguments.of(BigDecimal.valueOf(Long.MIN_VALUE).subtract(BigDecimal.valueOf(Long.MAX_VALUE)), 10,
//                        Operation.SUBTRACT, Long.MIN_VALUE, Long.MAX_VALUE),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, (byte) 3, (byte) 9),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, (short) 3, (short) 9),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, 3, 9),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, (long) 3, (long) 9),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, (float) 3, (float) 9),
//                Arguments.of(BigDecimal.valueOf(27), Operation.MULTIPLY, (double) 3, (double) 9),
//                Arguments.of(BigDecimal.valueOf(Long.MAX_VALUE).multiply(BigDecimal.valueOf(Long.MAX_VALUE)), 10,
//                        Operation.MULTIPLY, Long.MAX_VALUE, Long.MAX_VALUE),
//                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, (byte) 10, (byte) 2),
//                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, (short) 10, (short) 2),
//                Arguments.of(BigDecimal.valueOf(5), Operation.DIVIDE, 10, 2),
//                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, (long) 10, (long) 2),
//                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, (float) 10, (float) 2),
//                Arguments.of(BigDecimal.valueOf(5),  Operation.DIVIDE, (double) 10, (double) 2),
//                Arguments.of(BigDecimal.valueOf(0.3333333333),  Operation.DIVIDE, 1, 3),
//                Arguments.of(BigDecimal.valueOf(0.3333333333),  Operation.DIVIDE, 1.0, 3.0),
//                Arguments.of(BigDecimal.valueOf(1), Operation.DIVIDE, 1, Long.MAX_VALUE));
//    }
//
//
//    @ParameterizedTest
//    @MethodSource("getTestCalculateParameters")
//    void testCalculate(BigDecimal expected, Operation op, Number num1, Number num2) throws NumberInvalidException, OperationInvalidException {
//        Calculator calculator = new CalculatorImpl();
//        assertEquals(0, expected.compareTo((BigDecimal) calculator.calculate(op, num1, num2)));
//    }

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

}
