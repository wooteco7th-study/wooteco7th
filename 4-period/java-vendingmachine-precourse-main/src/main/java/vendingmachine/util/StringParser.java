package vendingmachine.util;

import vendingmachine.dto.request.InventoriesRequest;
import vendingmachine.dto.request.InventoryRequest;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private static final String PRODUCTS_DELIMITER = ";";
    private static final String PRODUCT_PRICE_QUANTITY_DELIMITER = ",";
    private static final String SQUARE_BRACKET = "[\\[\\]]";
    private static final String BLANK = "";

    private StringParser() {
    }

    public static InventoriesRequest parseInventories(String input) {
        List<InventoryRequest> inventoryRequests = Arrays.stream(input.split(PRODUCTS_DELIMITER))
                .map(StringParser::createInventoryRequest)
                .toList();
        return new InventoriesRequest(inventoryRequests);
    }

    private static InventoryRequest createInventoryRequest(String product) {
        String[] data = product.replaceAll(SQUARE_BRACKET, BLANK).split(PRODUCT_PRICE_QUANTITY_DELIMITER);
        String name = data[0];
        int price = Integer.parseInt(data[1]);
        int stock = Integer.parseInt(data[2]);
        return new InventoryRequest(name, price, stock);
    }
}

