package vendingmachine.service;

import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_PRODUCT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import vendingmachine.domain.price.HoldingPrice;
import vendingmachine.domain.price.InputPrice;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.price.coin.CoinGenerator;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.domain.product.Quantity;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.util.InputValidator;
import vendingmachine.util.StringParser;

public class VendingService {

    private static final String HOLDING_PRODUCT_REGEX = "^\\[([가-힣a-zA-Z]+),([1-9]\\d*),([1-9]\\d*)\\]$";
    private static final Pattern HOLDING_PRODUCT_PATTERN = Pattern.compile(HOLDING_PRODUCT_REGEX);

    private final CoinGenerator randomCoinGenerator;

    public VendingService(final CoinGenerator coinGenerator) {
        this.randomCoinGenerator = coinGenerator;
    }

    public HoldingPrice createHoldingPrice(int amount) {
        return new HoldingPrice(amount);
    }

    public Map<Coin, Integer> createRandomCoins(final Price holdingPrice) {
        return randomCoinGenerator.generateCoins(holdingPrice);
    }

    public Products createHoldingProducts(final List<String> inputs) {
        List<Product> products = new ArrayList<>();
        for (String input : inputs) {
            if (InputValidator.isInvalidPattern(input, HOLDING_PRODUCT_PATTERN)) {
                throw new CustomIllegalArgumentException(INVALID_HOLDING_PRODUCT);
            }
            products.add(createHoldingProduct(input));
        }
        return new Products(products);
    }

    private Product createHoldingProduct(final String input) {
        List<String> group = InputValidator.findMatchingGroups(input, HOLDING_PRODUCT_PATTERN);
        ProductPrice price = new ProductPrice(StringParser.parseToInteger(group.get(1), INVALID_HOLDING_PRODUCT));
        Quantity quantity = new Quantity(StringParser.parseToInteger(group.get(2), INVALID_HOLDING_PRODUCT));
        return new Product(group.get(0), price, quantity);
    }

    public InputPrice createInputPrice(final int inputPrice) {
        return new InputPrice(inputPrice);
    }
}
