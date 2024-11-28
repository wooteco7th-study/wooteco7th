package vendingmachine.domain.price.coin;

import java.util.HashMap;
import java.util.Map;
import vendingmachine.domain.price.Price;

public class LeastCoinGenerator implements CoinGenerator {

    private final Map<Coin, Long> holdingCoins;

    public LeastCoinGenerator(final Map<Coin, Long> holdingCoins) {
        this.holdingCoins = new HashMap<>(holdingCoins);
    }

    @Override
    public Map<Coin, Long> generateCoins(final Price price) {
        Map<Coin, Long> coins = new HashMap<>();
        Price target = price;
        for (Coin maxPriceCoin : Coin.sortedCoins()) {
            target = processCoin(coins, target, maxPriceCoin);
        }
        return coins;
    }

    private Price processCoin(final Map<Coin, Long> coins, Price target, final Coin maxPriceCoin) {
        if (holdingCoins.containsKey(maxPriceCoin)) {
            long count = Math.min(target.divide(maxPriceCoin.getPrice()), holdingCoins.get(maxPriceCoin));
            target = target.subtractByCount(maxPriceCoin.getPrice(), count);
            updateCoins(coins, maxPriceCoin, count);
        }
        return target;
    }

    private void updateCoins(final Map<Coin, Long> coins, final Coin maxPriceCoin, final long count) {
        coins.merge(maxPriceCoin, count, Long::sum);
        holdingCoins.merge(maxPriceCoin, -count, Long::sum);
    }
}
