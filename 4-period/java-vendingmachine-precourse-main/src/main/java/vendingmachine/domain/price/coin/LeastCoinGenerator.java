package vendingmachine.domain.price.coin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.domain.price.Price;

public class LeastCoinGenerator implements CoinGenerator {
    @Override
    public Map<Coin, Long> generateCoins(final Price price) {
        Map<Coin, Long> coins = new HashMap<>();
        Price target = price;
        while (target.isMoreThanEqual(Coin.getLowest().getPrice())) {
            Coin maxPriceCoin = Coin.calculateMaxCoin(target);
            long count = target.divide(maxPriceCoin.getPrice());
            target = target.getRemaining(maxPriceCoin.getPrice(), count);
            coins.put(maxPriceCoin, coins.getOrDefault(maxPriceCoin, 0L) + count);
        }
        return coins;
    }

    public Map<Coin, Long> generateCoins2(final Price price, final Map<Coin, Long> randomCoins) {
        Map<Coin, Long> coins = new HashMap<>();
        Price target = price;

        for (Coin maxPriceCoin : Coin.sortedCoins()) {
            if (randomCoins.containsKey(maxPriceCoin)) {
                Long quantity = randomCoins.get(maxPriceCoin);
                target = target.getRemaining(maxPriceCoin.getPrice(), quantity);
                coins.put(maxPriceCoin, coins.getOrDefault(maxPriceCoin, 0L) + quantity);
                if (maxPriceCoin.equals(Coin.COIN_10)) {
                    break;
                }
            }
        }
        return coins;
    }

    private List<Integer> getCoinTypes(final Price price) {
        return Coin.calculateAvailableCoinTypes(price).stream()
                .map(Coin::getPrice)
                .map(Price::getAmount)
                .map(Long::intValue)
                .toList();
    }
}
