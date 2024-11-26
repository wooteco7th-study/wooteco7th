package vendingmachine.domain.price.coin;

import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.Price;

class LeastCoinGeneratorTest {

    @Test
    void generateCoins() {
        // Given

        // When
        LeastCoinGenerator generator = new LeastCoinGenerator();
        Map<Coin, Long> coins = generator.generateCoins(new Price(2900));
        for (Entry<Coin, Long> entry : coins.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        // Then
    }
}
