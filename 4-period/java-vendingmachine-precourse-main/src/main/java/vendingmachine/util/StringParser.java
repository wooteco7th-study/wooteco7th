package vendingmachine.util;

import vendingmachine.domain.Inventories;
import vendingmachine.domain.Inventory;
import vendingmachine.domain.Product;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private static final String PRODUCTS_DELIMITER = ";";
    private static final String PRODUCT_PRICE_QUANTITY_DELIMITER = ",";
    private static final String SQUARE_BRACKET = "[\\[\\]]";
    private static final String BLANK = "";

    private StringParser() {
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

