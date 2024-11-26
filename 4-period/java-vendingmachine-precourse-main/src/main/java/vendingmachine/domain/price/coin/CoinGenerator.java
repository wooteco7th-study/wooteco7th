package vendingmachine.domain.price.coin;

import java.util.Map;
import vendingmachine.domain.price.Price;

public interface CoinGenerator {

    Map<Coin, Integer> generateCoins(Price price);
}
