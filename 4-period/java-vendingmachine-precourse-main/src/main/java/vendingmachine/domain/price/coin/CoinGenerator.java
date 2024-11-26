package vendingmachine.domain.price.coin;

import java.util.Map;
import vendingmachine.domain.price.Price;

@FunctionalInterface
public interface CoinGenerator {

    Map<Coin, Long> generateCoins(Price price);
}