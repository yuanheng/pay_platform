package com.bootdo.common.utils;


import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * @author zhuyq
 */
public class MathUtil {

    public static final int SCALE_2 = 2;

    private MathUtil() {
    }

    public static final BigDecimal DEFAULT_UNIFY_DIVISOR = BigDecimal.valueOf(10000);

    public static final int DEFAULT_SCALE = 4;
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal addUpDigit(List<BigDecimal> list) {
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }
        return list.stream().filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal divide(Number a, Number b) {
        BigDecimal x = parseBigDecimal(a);
        BigDecimal y = parseBigDecimal(b);
        if (x.compareTo(BigDecimal.ZERO) == 0 || y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return x.divide(y, DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(Number a, Number b, int size) {
        BigDecimal x = parseBigDecimal(a);
        BigDecimal y = parseBigDecimal(b);
        if (x.compareTo(BigDecimal.ZERO) == 0 || y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return x.divide(y, size, RoundingMode.HALF_UP);
    }

    public static BigDecimal parseBigDecimal(Number n) {
        if (n instanceof Integer) {
            return BigDecimal.valueOf((Integer) n);
        } else if (n instanceof Long) {
            return BigDecimal.valueOf((Long) n);
        } else {
            return new BigDecimal(String.valueOf(n));
        }
    }

    public static BigDecimal divideByDivisor(BigDecimal denominator) {
        return divide(denominator, DEFAULT_UNIFY_DIVISOR);
    }

    /**
     * ????????????
     *
     * @param dividend ?????????
     * @param divisor  ??????
     * @return ????????? / ??????????????????????????????????????????
     * ?????????????????????null?????????null
     * ?????????0?????????0
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor, DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(int scale, BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal dividend, Double divisor) {
        return divide(dividend, BigDecimal.valueOf(divisor), DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal dividend, int divisor) {
        return divide(dividend, BigDecimal.valueOf(divisor), DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal dividend, long divisor) {
        return divide(dividend, BigDecimal.valueOf(divisor), DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * ????????????
     *
     * @param dividend ?????????
     * @param divisor  ??????
     * @return ????????? / ??????
     * ?????????????????????null?????????null
     * ?????????0?????????0
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {

        // ?????????????????????null?????????null
        if (dividend == null || divisor == null) {
            return BigDecimal.ZERO;
        }

        // ?????????0?????????0
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // ????????? / ??????
        return dividend.divide(divisor, scale, roundingMode);
    }

    /**
     * ????????????
     *
     * @return ln(arg)
     */
    public static BigDecimal log(BigDecimal arg) {

        if (arg == null) {
            return null;
        }
        if (BigDecimal.ZERO.compareTo(arg) == 0) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(Math.log(arg.doubleValue()));
    }

}
