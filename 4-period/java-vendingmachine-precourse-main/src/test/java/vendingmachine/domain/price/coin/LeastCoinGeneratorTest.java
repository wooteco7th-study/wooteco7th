package vendingmachine.domain.price.coin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.Price;

class LeastCoinGeneratorTest {

    @Test
    @DisplayName("보유 동전을 가지고 최소한의 동전 개수로 잔돈을 반환한다.")
    void generateCoins() {
        // Given
        LeastCoinGenerator generator = new LeastCoinGenerator(Map.of(Coin.COIN_500, 8L, Coin.COIN_50, 3L));

        // When
        Map<Coin, Long> coins = generator.generateCoins(new Price(2900));

        // Then
        assertThat(coins).containsExactlyInAnyOrderEntriesOf(Map.of(Coin.COIN_500, 5L, Coin.COIN_50, 3L));
    }
}
