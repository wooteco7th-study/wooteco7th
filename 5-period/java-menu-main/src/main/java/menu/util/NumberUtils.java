package menu.util;

public class NumberUtils {
    private NumberUtils() {
    }

    // 최소값보다 크거나 같은지 확인
    public static boolean isGreaterThanOrEqualTo(int target, int min) {
        return target >= min;
    }

    // 범위 내에 있는지 확인 (포함)
    public static boolean isBetweenInclusive(int target, int min, int max) {
        return target >= min && target <= max;
    }

    // 특정 단위의 배수인지 확인
    public static boolean isMultipleOf(int target, int unit) {
        return target % unit == 0;
    }

    // 양수
    public static boolean isPositive(int number) {
        return number > 0;
    }

    // 음수
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 짝수
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    // 절댓값
    public static int absolute(int number) {
        return Math.abs(number);
    }

    // 최댓값
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    // 숫자의 자릿수 합 계산
    public static int sumOfDigits(int number) {
        int sum = 0;
        number = Math.abs(number);
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

}
