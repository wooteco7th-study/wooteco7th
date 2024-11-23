package christmas.service;

import christmas.Exception.DateException;
import christmas.Exception.InputException;

public class Converter {

    private Converter() {

    }

    public static int getNumberDate(String date) {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new DateException();
        }
    }

    public static int getNumberCount(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }
}
