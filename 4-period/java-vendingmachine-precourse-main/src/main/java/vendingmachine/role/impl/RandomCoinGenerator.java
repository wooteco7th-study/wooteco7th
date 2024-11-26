package vendingmachine.role.impl;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInList;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;
import vendingmachine.role.CoinGenerator;


/*
- [ ] 자판기가 보유하고 있는 금액으로 동전을 무작위로 생성한다.
 */
public class RandomCoinGenerator implements CoinGenerator {
    @Override
    public Map<Coin, Integer> generate(Money amount) {
        Map<Coin, Integer> generated = new EnumMap<>(Coin.class);
        int remaining = amount.getAmount();

        while (remaining > 0) {
            Coin coin = selectRandomCoin(remaining);
            generated.merge(coin, 1, Integer::sum);
            remaining -= coin.getAmount();
        }

        return generated;
    }

    private Coin selectRandomCoin(int amount) {

        List<Coin> availableCoins = Arrays.stream(Coin.values())
                .filter(coin -> isSufficientAmountToPick(amount, coin))
                .collect(toList());
        if (availableCoins.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 생성 가능한 동전이 없D습니다.");
        }

        int randomIndex = pickNumberInList(
                IntStream.range(0, availableCoins.size())
                        .boxed()
                        .collect(toList())
        );
        return availableCoins.get(randomIndex);
    }

    private static boolean isSufficientAmountToPick(final int amount, final Coin coin) {
        return coin.getAmount() <= amount;
    }


}
