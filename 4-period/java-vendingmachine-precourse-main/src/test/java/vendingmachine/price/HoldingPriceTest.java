package vendingmachine.price;

import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_AMOUNT;
import static vendingmachine.support.CustomExceptionAssertions.assertIllegalArgument;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.HoldingPrice;

@DisplayName("보유 금액 테스트")
class HoldingPriceTest {

    @Test
    @DisplayName("10원으로 떨어지지 않은 숫자이면 생성된다.")
    void test1() {
        // Given

        // When & Then
        Assertions.assertThatCode(() -> {
            new HoldingPrice(230);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("10원으로 떨어지지 않은 숫자이면 예외가 발생한다.")
    void test2() {
        // Given

        // When & Then
        assertIllegalArgument(() -> new HoldingPrice(233), INVALID_HOLDING_AMOUNT);
    }
}
