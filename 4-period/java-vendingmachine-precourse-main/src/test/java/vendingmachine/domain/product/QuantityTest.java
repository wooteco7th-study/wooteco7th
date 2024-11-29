package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.INVALID_ZERO_QUANTITY;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuantityTest {

    @Test
    @DisplayName("수량이 음수이면 예외가 발생한다.")
    void test() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new Quantity(-1))
                .hasMessageContaining(INVALID_ZERO_QUANTITY.getMessage());
    }

    @Test
    void isZero() {
    }
}
