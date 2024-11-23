package christmas.service;

import christmas.Exception.InputException;

public class Converter {

    private Converter() {

    }

    public static int getNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }
}
