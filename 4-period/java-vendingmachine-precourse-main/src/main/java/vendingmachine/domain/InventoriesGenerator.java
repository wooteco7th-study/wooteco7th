package vendingmachine.domain;

import vendingmachine.util.StringParser;

import java.util.List;

public class InventoriesGenerator {

    private static final String PRODUCTS_DELIMITER = ";";
    private static final String PRODUCT_PRICE_QUANTITY_DELIMITER = ",";
    private static final String SQUARE_BRACKET = "[\\[\\]]";
    private static final String BLANK = "";

    private InventoriesGenerator() {
    }

    public static Inventories parseInventories(String input) {
        List<Inventory> inventories = StringParser.parseWithDelimiter(input, PRODUCTS_DELIMITER)
                .stream()
                .map(InventoriesGenerator::createInventory)
                .toList();
        return new Inventories(inventories);
    }

    private static Inventory createInventory(String product) {
        String removedBracketInput = product.replaceAll(SQUARE_BRACKET, BLANK);
        List<String> data = StringParser.parseWithDelimiter(removedBracketInput, PRODUCT_PRICE_QUANTITY_DELIMITER);

        String name = data.getFirst();
        int price = StringParser.parseToValidNumber(data.get(1));
        int stock = StringParser.parseToValidNumber(data.get(2));
        return new Inventory(
                new Product(name, price),
                stock);
    }
}
