package vendingmachine.domain.price.coin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import vendingmachine.domain.price.Price;

public class LeastCoinGenerator implements CoinGenerator {

    private final Map<Coin, Integer> holdingCoins;

    public LeastCoinGenerator(final Map<Coin, Integer> holdingCoins) {
        this.holdingCoins = new HashMap<>(holdingCoins);
    }

    @Override
    public Map<Coin, Integer> generateCoins(final Price price) {
        Map<Coin, Integer> coins = new LinkedHashMap<>();
        int target = price.getAmount();
        for (Coin coin : Coin.sortedCoins()) {
            target = processCoin(coins, target, coin);
        }
        return coins;
    }

    private int processCoin(final Map<Coin, Integer> coins, int target, final Coin coin) {
        if (holdingCoins.getOrDefault(coin, 0) > 0) {
            int coinAmount = coin.getPrice().getAmount();
            int possibleCount = target / coinAmount;
            int count = Math.min(possibleCount, holdingCoins.get(coin));
            target -= coinAmount * count;
            updateCoin(coins, coin, count);
        }
        return target;
    }

    private void updateCoin(final Map<Coin, Integer> coins, final Coin coin, final int count) {
        holdingCoins.merge(coin, -count, Integer::sum);
        coins.merge(coin, count, Integer::sum);
    }
}
