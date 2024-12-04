package lotto.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatUtils {
    private NumberFormatUtils() {
    }

    /**
     * 소수점 n째 자리까지 표시합니다. (기본적으로 버림 처리)
     *
     * @param number        변환할 숫자
     * @param decimalPlaces 표시할 소수점 자리수
     * @return 지정된 자리수까지의 문자열
     */
    public static String formatToDecimalPlaces(double number, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(decimalPlaces, RoundingMode.DOWN);
        return bd.toPlainString();
    }

    /**
     * 숫자를 올림합니다.
     *
     * @param number 올림할 숫자
     * @return 올림된 숫자
     */
    public static double roundUp(double number) {
        return Math.ceil(number);
    }

    /**
     * 숫자를 내림합니다.
     *
     * @param number 내림할 숫자
     * @return 내림된 숫자
     */
    public static double roundDown(double number) {
        return Math.floor(number);
    }

    /**
     * 숫자를 반올림합니다.
     *
     * @param number 반올림할 숫자
     * @return 반올림된 숫자
     */
    public static double roundHalfUp(double number) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * 지정된 소수점 자리수까지 올림합니다.
     *
     * @param number        올림할 숫자
     * @param decimalPlaces 소수점 자리수
     * @return 올림된 숫자
     */
    public static double roundUp(double number, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(decimalPlaces, RoundingMode.CEILING);
        return bd.doubleValue();
    }

    /**
     * 지정된 소수점 자리수까지 내림합니다.
     *
     * @param number        내림할 숫자
     * @param decimalPlaces 소수점 자리수
     * @return 내림된 숫자
     */
    public static double roundDown(double number, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(decimalPlaces, RoundingMode.FLOOR);
        return bd.doubleValue();
    }

    /**
     * 지정된 소수점 자리수까지 반올림합니다.
     *
     * @param number        반올림할 숫자
     * @param decimalPlaces 소수점 자리수
     * @return 반올림된 숫자
     */
    public static double roundHalfUp(double number, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * 지정된 라운딩 모드로 숫자를 변환하고, 소수점 자리수를 설정합니다.
     *
     * @param number        변환할 숫자
     * @param decimalPlaces 소수점 자리수
     * @param roundingMode  라운딩 모드 (예: RoundingMode.UP, RoundingMode.DOWN, RoundingMode.HALF_UP 등)
     * @return 변환된 숫자
     */
    public static double round(double number, int decimalPlaces, RoundingMode roundingMode) {
        BigDecimal bd = new BigDecimal(Double.toString(number));
        bd = bd.setScale(decimalPlaces, roundingMode);
        return bd.doubleValue();
    }

    /**
     * 라운딩 후 지정된 소수점 자리수까지 표시합니다.
     *
     * @param number        변환할 숫자
     * @param decimalPlaces 소수점 자리수
     * @param roundingMode  라운딩 모드 (예: RoundingMode.UP, RoundingMode.DOWN, RoundingMode.HALF_UP 등)
     * @return 변환된 숫자의 문자열 표현
     */
    public static String roundAndFormat(double number, int decimalPlaces, RoundingMode roundingMode) {
        double roundedNumber = round(number, decimalPlaces, roundingMode);
        return formatToDecimalPlaces(roundedNumber, decimalPlaces);
    }

}
