package vendingmachine.domain.price.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.domain.price.Price;

public class RandomCoinGenerator implements CoinGenerator {

    @Override
    public Map<Coin, Long> generateCoins(final Price price) {
        Map<Coin, Long> coins = new HashMap<>();
        Price target = price;
        while (target.isMoreThanEqual(Coin.getLowest().getPrice())) {
            List<Integer> coinTypes = getCoinTypes(target);
            Price coinAmount = new Price(pickNumber(coinTypes));
            target = target.subtract(coinAmount);
            Coin coin = Coin.createCoin(coinAmount);
            coins.put(coin, coins.getOrDefault(coin, 0L) + 1);
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

    private int pickNumber(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }
}
