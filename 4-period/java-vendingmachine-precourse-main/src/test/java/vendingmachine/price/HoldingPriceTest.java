package vendingmachine.price;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.price.HoldingPrice;
import vendingmachine.exception.ErrorMessage;

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
        assertThatThrownBy(() -> new HoldingPrice(233))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_HOLDING_PRICE_DIVIDE_BY_TEN.getMessage());
    }

}
