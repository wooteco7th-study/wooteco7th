package vendingmachine.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class CoinsGenerator {

    private static final int MIN_COIN_AMOUNT = Coin.COIN_10.getAmount();

    private CoinsGenerator() {

    }

    public static Map<Coin, Integer> generate(final Money money) {
        final Map<Coin, Integer> coins = initializeCoins();
        while (money.isExceedsValue(MIN_COIN_AMOUNT)) {
            final Coin coin = RandomCoinGenerator.generate();
            final int amount = coin.getAmount();
            if (money.isExceedsValue(amount)) {
                coins.merge(coin, 1, Integer::sum);
                money.subtractValue(amount);
            }
        }
        return coins;
    }


    private static Map<Coin, Integer> initializeCoins() {
        return Coin.findAll().stream()
                .collect(Collectors.toMap(
                        coin -> coin,
                        coin -> 0
                ));
    }
}
