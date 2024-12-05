package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinGroupTest {

    @DisplayName("금액에 따라 가장 비싼 동전을 가져온다.")
    @Test
    void getExpensiveCoinTest() {
        //given
        final int money = 100;
        Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_500, 1);
        coins.put(Coin.COIN_100, 1);

        //when
        final CoinGroup coinGroup = new CoinGroup(coins);
        final Coin expensiveCoin = coinGroup.getExpensiveCoin(money);

        //then
        assertThat(expensiveCoin).isEqualTo(Coin.COIN_100);
    }
}
