package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;
import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_QUANTITY;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.ProductPrice;

class ProductTest {

    @Test
    @DisplayName("가격이 100원 미만일 경우 에러가 발생한다.")
    void 가격이_100원_미만일_경우() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Product("밀키스", new ProductPrice(90), new Quantity(10)))
                .hasMessageContaining(INVALID_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("가격이 10원으로 나누어 떨어지지 않을 경우 에러가 발생한다.")
    void 가격이_10원으로_나누어_떨어지지_않을_경우() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Product("밀키스", new ProductPrice(199), new Quantity(10)))
                .hasMessageContaining(INVALID_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("상품을 새롭게 생성할 경우 수량이 0이면 에러가 발생한다.")
    void test() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Product("밀키스", new ProductPrice(100), new Quantity(0)))
                .hasMessageContaining(INVALID_PRODUCT_QUANTITY.getMessage());
    }
}
