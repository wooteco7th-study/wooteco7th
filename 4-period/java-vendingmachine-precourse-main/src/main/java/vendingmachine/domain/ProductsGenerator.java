package vendingmachine.domain;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.error.ErrorMessage;
import vendingmachine.util.StringParser;
import vendingmachine.util.StringValidator;

public class ProductsGenerator {

    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int PRODUCT_QUANTITY_INDEX = 2;
    private static final String WRAPPER_REGEX = "[\\[\\]]";
    private static final String DELIMITER_COMMA = ",";

    private ProductsGenerator() {

    }

    public static List<Product> generate(final List<String> inputs) {
        return inputs.stream()
                .map(ProductsGenerator::createProduct)
                .collect(Collectors.toList());
    }

    private static Product createProduct(final String input) {
        StringValidator.validateFormat(input, ErrorMessage.INVALID_PRODUCT_FORMAT);
        final List<String> tokens = StringParser.parseToTokens(StringParser.removePattern(input, WRAPPER_REGEX),
                DELIMITER_COMMA);
        final String name = tokens.get(PRODUCT_NAME_INDEX);
        final int price = StringParser.parseToInt(tokens.get(PRODUCT_PRICE_INDEX), ErrorMessage.INVALID_PRODUCT_FORMAT);
        final int quantity = StringParser.parseToInt(tokens.get(PRODUCT_QUANTITY_INDEX),
                ErrorMessage.INVALID_PRODUCT_FORMAT);
        return new Product(name, price, quantity);
    }

}
