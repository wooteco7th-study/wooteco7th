package vendingmachine.price.coin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.price.Price;

class RandomCoinGeneratorTest {

    @Test
    @DisplayName("보유 금액으로 동전을 랜덤으로 생성한다.")
    void generateCoins() {
        // Given
        RandomCoinGenerator generator = new RandomCoinGenerator();
        Price price = new Price(5000);

        // When
        Map<Coin, Integer> coins = generator.generateCoins(price);

        // Then
        long sum = 0;
        for (Entry<Coin, Integer> entry : coins.entrySet()) {
            sum += entry.getKey().getPrice().getAmount() * entry.getValue();
        }
        assertThat(sum).isEqualTo(5000);
    }
}
