package calculator.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class CalculatorUtils {

    public static BigDecimal convert(Number num) {
        if (num instanceof AtomicInteger || num instanceof AtomicLong
            || num instanceof AtomicInteger || num instanceof BigInteger
                || num instanceof Byte || num instanceof Integer
                || num instanceof Long || num instanceof LongAccumulator
                || num instanceof LongAdder || num instanceof Short) {
            return BigDecimal.valueOf(num.longValue());
        }
        if (num instanceof BigDecimal) {
            return (BigDecimal)num;
        }
        return BigDecimal.valueOf(num.doubleValue());
    }
}
