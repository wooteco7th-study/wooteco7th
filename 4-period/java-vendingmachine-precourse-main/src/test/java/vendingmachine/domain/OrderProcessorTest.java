package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static vendingmachine.exception.ErrorMessage.INVALID_ORDER_PRICE;
import static vendingmachine.exception.ErrorMessage.OUT_OF_STOCK;
import static vendingmachine.support.CustomExceptionAssertions.assertCustomIllegalArgumentException;

import java.util.List;
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

        // When & Then
        assertThatCode(() -> {
            processor.process(holdingProducts.findByName("콜라"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투입 금액보다 비싼 상품을 구매할 수 없다.")
    void process_expensive() {
        // Given
        Products holdingProducts = new Products(List.of(makeCoke(), makeCider()));
        OrderProcessor processor = new OrderProcessor(holdingProducts, new InputPrice(900));

        // When & Then
        assertCustomIllegalArgumentException(() -> processor.process(holdingProducts.findByName("콜라"))
                , INVALID_ORDER_PRICE);
    }

    @Test
    @DisplayName("재고가 없으면 구매할 수 없다.")
    void process_outOfStock() {
        // Given
        Products holdingProducts = new Products(List.of(new Product("콜라", new ProductPrice(1000), new Quantity(1))));
        OrderProcessor processor = new OrderProcessor(holdingProducts, new InputPrice(2000));

        // When & Then
        processor.process(holdingProducts.findByName("콜라"));
        assertCustomIllegalArgumentException(() -> processor.process(holdingProducts.findByName("콜라"))
                , OUT_OF_STOCK);
    }

    private Product makeCoke() {
        return new Product("콜라", new ProductPrice(1000), new Quantity(10));
    }

    private Product makeCider() {
        return new Product("사이다", new ProductPrice(1500), new Quantity(20));
    }
}
