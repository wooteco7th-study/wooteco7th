package vendingmachine.price.coin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.coin.Coin;

class CoinTest {

    @Test
    void createCoin() {
    }

    @Test
    @DisplayName("해당 금액에 대해 가능한 동전 종류를 반환한다.")
    void calculateAvailableCoinTypes() {
        // Given

        // When
        List<Coin> coins = Coin.calculateAvailableCoinTypes(150);

        // Then
        assertThat(coins).contains(Coin.COIN_100, Coin.COIN_50, Coin.COIN_10);
    }

    @Test
    @DisplayName("가장 싼 동전 금액을 반환한다.")
    void getLowest() {
        // Given

        // When & Then
        assertThat(Coin.getLowestAmount()).isEqualTo(10);
    }
}
