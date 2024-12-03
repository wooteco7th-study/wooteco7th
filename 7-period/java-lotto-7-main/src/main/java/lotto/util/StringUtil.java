package lotto.util;

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isBetweenLength(String target, int min, int max) {
        if (min <= target.length() && target.length() <= max) {
            return true;
        }
        return false;
    }

    public static boolean hasLength(String target, int criteria) {
        if (target.length() == criteria) {
            return true;
        }
        return false;
    }


    // 문자열이 숫자만으로 구성되어 있는지 확인
    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    // 문자열이 알파벳 문자만으로 구성되어 있는지 확인
    public static boolean isAlpha(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    // 문자열을 역순으로 반환
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    // 문자열의 첫 글자를 대문자로 변환
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // 문자열 내에서 특정 문자열의 등장 횟수를 반환
    public static int countOccurrences(String str, String sub) {
        int count = 0, idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

}
