package vendingmachine.domain;

import java.util.Map;
import java.util.Map.Entry;

public class CoinGroup {

    private final Map<Coin, Integer> coins;

    public CoinGroup(final Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public Coin getExpensiveCoin(final int value) {
        return coins.entrySet().stream()
                .sorted((e1, e2) -> e2.getKey().getAmount() - e1.getKey().getAmount())
                .filter(entry -> entry.getKey().getAmount() <= value)
                .filter(entry -> entry.getValue() > 0)
                .findAny()
                .map(Entry::getKey)
                .orElse(Coin.NONE);
    }
}
