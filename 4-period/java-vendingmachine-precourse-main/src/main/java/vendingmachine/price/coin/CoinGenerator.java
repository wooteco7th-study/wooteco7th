package vendingmachine.price.coin;

import java.util.Map;
import vendingmachine.price.Price;

public interface CoinGenerator {

    Map<Coin, Integer> generateCoins(Price price);
}
