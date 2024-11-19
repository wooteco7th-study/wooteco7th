package baseball.util;

public class Converter {

    public static int convertToInteger(char number) {
        if (!Character.isDigit(number)) {
            throw new IllegalArgumentException("[ERROR] 숫자여야합니다.");
        }
        return number - '0';
    }
}
