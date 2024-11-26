package vendingmachine.util;

public class Convertor {

    private Convertor() {

    }

    public static Integer changeType(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
