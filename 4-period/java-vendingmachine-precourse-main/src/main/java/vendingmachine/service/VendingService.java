package vendingmachine.service;

import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_PRODUCT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import vendingmachine.domain.price.HoldingPrice;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.price.coin.CoinGenerator;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Quantity;
import vendingmachine.dto.CoinDto;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.util.Converter;
import vendingmachine.util.StringParser;

public class VendingService {

    private static final String HOLDING_PRODUCT_REGEX = "^\\[([가-힣]+),(\\d+),(\\d+)\\]$";
    private static final Pattern HOLDING_PRODUCT_PATTERN = Pattern.compile(HOLDING_PRODUCT_REGEX);

    private final CoinGenerator coinGenerator;

    public VendingService(final CoinGenerator coinGenerator) {
        this.coinGenerator = coinGenerator;
    }

    public HoldingPrice createHoldingPrice(long amount) {
        return new HoldingPrice(amount);
    }

    public List<CoinDto> createRandomCoins(final Price holdingPrice) {
        Map<Coin, Integer> coins = coinGenerator.generateCoins(holdingPrice);
        List<CoinDto> coinDtos = new ArrayList<>();
        for (Entry<Coin, Integer> entry : coins.entrySet()) {
            coinDtos.add(new CoinDto(entry.getKey().getPrice().getAmount(), entry.getValue()));
        }
        return coinDtos;
    }

    public List<Product> createHoldingProducts(final List<String> inputs) {
        List<Product> products = new ArrayList<>();
        for (String input : inputs) {
            if (StringParser.isNotSuitablePattern(input, HOLDING_PRODUCT_PATTERN)) {
                throw new CustomIllegalArgumentException(INVALID_HOLDING_PRODUCT);
            }
            products.add(createHoldingProduct(input));
        }
        return products;
    }

    private Product createHoldingProduct(final String input) {
        List<String> group = StringParser.extractByGroup(input, HOLDING_PRODUCT_PATTERN);
        ProductPrice price = new ProductPrice(Converter.convertToLong(group.get(1), INVALID_HOLDING_PRODUCT));
        Quantity quantity = new Quantity(Converter.convertToInteger(group.get(2), INVALID_HOLDING_PRODUCT));
        return new Product(group.get(0), price, quantity);
    }
}
