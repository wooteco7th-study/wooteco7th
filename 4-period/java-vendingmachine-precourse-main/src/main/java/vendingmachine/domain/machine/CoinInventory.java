package vendingmachine.domain.machine;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import vendingmachine.domain.money.Coin;

public class CoinInventory {
    private final Map<Coin, Integer> coins;

    private CoinInventory(Map<Coin, Integer> coins) {
        this.coins = new EnumMap<>(coins);
    }

    public static CoinInventory from(Map<Coin, Integer> coins) {
        return new CoinInventory(new EnumMap<>(coins));
    }

    public Map<Coin, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
