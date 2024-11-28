package vendingmachine.domain.price.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.domain.price.Price;

public class RandomCoinGenerator implements CoinGenerator {

    @Override
    public Map<Coin, Integer> generateCoins(final Price price) {
        Map<Coin, Integer> coins = initializeMaps();
        int target = price.getAmount();
        while (target >= Coin.getLowestAmount()) {
            int pickAmount = pickCoinAmount(target);
            target -= pickAmount;
            coins.merge(Coin.createCoin(pickAmount), 1, Integer::sum);
        }
        return coins;
    }

    private Map<Coin, Integer> initializeMaps() {
        return Coin.sortedCoins().stream()
                .collect(Collectors.toMap(
                        coin -> coin,
                        quantity -> 0,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private int pickCoinAmount(final int target) {
        List<Integer> coinTypes = getCoinTypes(target);
        return pickNumber(coinTypes);
    }

    private List<Integer> getCoinTypes(final int price) {
        return Coin.calculateAvailableCoinTypes(price).stream()
                .map(Coin::getPrice)
                .map(Price::getAmount)
                .toList();
    }

    private int pickNumber(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }
}
