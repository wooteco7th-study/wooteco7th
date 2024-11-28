package vendingmachine;

import org.junit.jupiter.api.Test;
import vendingmachine.domain.Coin;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {
    @Test
    void 동전_중_가장_낮은_금액을_가진_동전을_반환한다() {
        // given & when
        int lowestCoinAmount = Coin.getLowestCoin();
        // then
        assertThat(lowestCoinAmount).isEqualTo(10);
    }

}
