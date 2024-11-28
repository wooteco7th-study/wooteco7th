package vendingmachine.util;

import vendingmachine.domain.Inventories;
import vendingmachine.domain.Inventory;
import vendingmachine.domain.Product;

import java.util.Arrays;
import java.util.List;

import static vendingmachine.exception.ExceptionMessage.AMOUNT_MUST_BE_POSITIVE;
import static vendingmachine.exception.ExceptionMessage.INVALID_AMOUNT;

public class StringParser {

    private static final String PRODUCTS_DELIMITER = ";";
    private static final String PRODUCT_PRICE_QUANTITY_DELIMITER = ",";
    private static final String SQUARE_BRACKET = "[\\[\\]]";
    private static final String BLANK = "";

    private StringParser() {
    }

    public static int parseToValidNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            validatePositiveNumber(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT.getMessage());
        }
    }

    private static void validatePositiveNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE.getMessage());
        }
    }

    public static Inventories parseInventories(String input) {
        List<Inventory> inventories = Arrays.stream(input.split(PRODUCTS_DELIMITER))
                .map(StringParser::createInventoryRequest)
                .toList();
        return new Inventories(inventories);
    }

    private static Inventory createInventoryRequest(String product) {
        String[] data = product.replaceAll(SQUARE_BRACKET, BLANK).split(PRODUCT_PRICE_QUANTITY_DELIMITER);
        String name = data[0];
        int price = Integer.parseInt(data[1]);
        int stock = Integer.parseInt(data[2]);
        return new Inventory(
                new Product(name, price),
                stock);
    }
}

