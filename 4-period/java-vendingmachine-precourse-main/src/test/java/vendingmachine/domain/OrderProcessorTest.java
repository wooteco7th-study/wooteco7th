package vendingmachine.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.InputPrice;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.domain.product.Quantity;

class OrderProcessorTest {

    @Test
    @DisplayName("상품을 정상적으로 구매한다.")
    void process() {
        // Given
        Products holdingProducts = new Products(List.of(makeCoke(), makeCider()));
        OrderProcessor processor = new OrderProcessor(holdingProducts, new InputPrice(3000));

        // When
        boolean isTerminated = processor.process(holdingProducts.findByName("콜라"));

        // Then
        Assertions.assertThat(isTerminated).isFalse();
    }

    private Product makeCoke() {
        return new Product("콜라", new ProductPrice(1000), new Quantity(10));
    }

    private Product makeCider() {
        return new Product("사이다", new ProductPrice(1500), new Quantity(20));
    }
}
