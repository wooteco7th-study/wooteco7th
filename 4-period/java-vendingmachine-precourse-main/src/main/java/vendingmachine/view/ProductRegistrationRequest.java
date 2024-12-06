package vendingmachine.view;

import static vendingmachine.exception.ErrorMessage.INVALID_PARSE_TO_INT;
import static vendingmachine.exception.ErrorMessage.INVALID_REQUEST_PRODUCT_FORM;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.money.Money;
import vendingmachine.domain.product.Product;

public class ProductRegistrationRequest {
    private final static int DEFAULT_SIZE = 3;
    private final static String SPLIT_COMMA = ",";
    private final static String SPLIT_SEMICOLON = ",";
    private final String name;
    private final int price;
    private final int quantity;

    private ProductRegistrationRequest(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static List<ProductRegistrationRequest> from(String input) {

        String sanitizedInput = input.replaceAll("\\[|\\]", "").trim();
        String[] productEntries = sanitizedInput.split(SPLIT_SEMICOLON);

        return Arrays.stream(productEntries)
                .map(String::trim)
                .filter(entry -> !entry.isEmpty())
                .map(entry -> {
                    String[] productInfo = entry.split(SPLIT_COMMA);
                    validateProductInfo(productInfo);
                    return new ProductRegistrationRequest(
                            productInfo[0].trim(),
                            parseToInt(productInfo[1]),
                            parseToInt(productInfo[2])
                    );
                })
                .collect(Collectors.toList());
    }

    private static void validateProductInfo(String[] productInfo) {
        if (productInfo.length != DEFAULT_SIZE) {
            throw new IllegalArgumentException(INVALID_REQUEST_PRODUCT_FORM.getMessage());
        }

    }

    private static int parseToInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PARSE_TO_INT.getMessage());
        }
    }


    public Product toProduct() {
        return Product.create(name, Money.of(price), quantity);
    }
}
