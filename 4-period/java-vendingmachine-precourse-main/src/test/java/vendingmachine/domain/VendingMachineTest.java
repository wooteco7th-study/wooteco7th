package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @DisplayName("상품 재고가 전부 소진되어 구매할 수 있는 상품이 존재하지 않는다.")
    @Test
    void hasPurchaseAbleProductTest() {
        //given
        final Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_500, 1);
        coins.put(Coin.COIN_100, 1);
        final CoinGroup coinGroup = new CoinGroup(coins);
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 1), new Product("사이다", 100, 1));
        final ProductGroup productGroup = new ProductGroup(products);
        final Money money = new Money(1000);

        //when
        final VendingMachine vendingMachine = new VendingMachine(coinGroup, productGroup, money);
        vendingMachine.purchaseProduct("콜라");
        vendingMachine.purchaseProduct("사이다");
        final boolean result = vendingMachine.hasPurchaseAbleProduct();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("가장 비싼 동전 부터 사용하여 잔돈으로 반환한다.")
    void getRemainingCoinsTest() throws Exception {
        //given
        final Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_500, 1);
        coins.put(Coin.COIN_100, 10);
        final CoinGroup coinGroup = new CoinGroup(coins);
        final List<Product> products = Arrays.asList(new Product("콜라", 100, 1), new Product("사이다", 100, 1));
        final ProductGroup productGroup = new ProductGroup(products);
        final Money money = new Money(1000);

        //when
        final VendingMachine vendingMachine = new VendingMachine(coinGroup, productGroup, money);
        final Map<Coin, Integer> remainingCoins = vendingMachine.getRemainingCoins();

        //then
        assertAll(
                () -> assertThat(remainingCoins.get(Coin.COIN_500)).isEqualTo(1),
                () -> assertThat(remainingCoins.get(Coin.COIN_100)).isEqualTo(5)
        );
    }
}
