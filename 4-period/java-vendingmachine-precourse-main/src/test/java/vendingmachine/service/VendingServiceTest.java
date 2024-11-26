package vendingmachine.service;

import static org.assertj.core.api.Assertions.assertThat;
import static vendingmachine.support.CustomExceptionAssertions.assertCustomIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.InputPrice;
import vendingmachine.domain.price.coin.RandomCoinGenerator;
import vendingmachine.domain.product.Product;

class VendingServiceTest {

    @Test
    void createHoldingPrice() {
    }

    @Test
    void createRandomCoins() {
    }

    @Test
    @DisplayName("보유 상품들을 생성한다.")
    void createHoldingProducts() {
        // Given
        VendingService service = new VendingService(new RandomCoinGenerator());
        List<String> inputs = Arrays.asList("[콜라,1500,20]", "[사이다,1000,10]");

        // When
        List<Product> holdingProducts = service.createHoldingProducts(inputs);

        // Then
        assertThat(holdingProducts).hasSize(2);
    }
}
