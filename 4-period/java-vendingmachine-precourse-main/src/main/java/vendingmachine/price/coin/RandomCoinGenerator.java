package vendingmachine.price.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.price.Price;

public class RandomCoinGenerator implements CoinGenerator {

    @Override
    public Map<Coin, Integer> generateCoins(final Price price) {
        Map<Coin, Integer> coins = new HashMap<>();
        Price target = price;
        while (target.isMoreThanEqual(Coin.getLowest().getPrice())) {
            List<Integer> coinTypes = getCoinTypes(target);
            Price coinAmount = new Price(pickNumber(coinTypes));
            target = target.subtract(coinAmount);
            Coin coin = Coin.createCoin(coinAmount);
            coins.put(coin, coins.getOrDefault(coin, 0) + 1);
        }
        return coins;
    }

    private static List<Integer> getCoinTypes(final Price price) {
        return Coin.calculateAvailableCoinTypes(price).stream()
                .map(Coin::getPrice)
                .map(Price::getAmount)
                .map(Long::intValue)
                .toList();
    }

    private int pickNumber(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }
}
