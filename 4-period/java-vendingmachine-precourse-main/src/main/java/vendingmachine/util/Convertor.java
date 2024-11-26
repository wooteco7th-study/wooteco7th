package vendingmachine.util;

import vendingmachine.excpetion.InputException;

public class Convertor {

    private Convertor() {

    }

    public static Integer changeType(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }
}
