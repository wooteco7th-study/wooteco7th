package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputPriceTest {

    @Test
    @DisplayName("투입 금액이 0원일 경우 예외가 발생한다.")
    void createInputPrice() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new InputPrice(0))
                .hasMessageContaining(INVALID_AMOUNT.getMessage());
    }
}
