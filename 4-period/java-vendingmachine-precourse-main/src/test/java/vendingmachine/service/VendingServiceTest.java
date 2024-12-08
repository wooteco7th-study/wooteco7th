package vendingmachine.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.domain.price.coin.RandomCoinGenerator;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.domain.product.Quantity;

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
        Products holdingProducts = service.createHoldingProducts(inputs);

        // Then
        assertThat(holdingProducts).isEqualTo(
                new Products(List.of(new Product("콜라", new ProductPrice(1500), new Quantity(20)),
                        new Product("사이다", new ProductPrice(1000), new Quantity(10)))));

    }
}
