package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.error.ErrorMessage;

class MoneyTest {

    @Test
    @DisplayName("금액이 음수이므로 예외가 발생한다.")
    void validateRangeTest() throws Exception {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(-1))
                .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_MONEY_RANGE.getMessage());

    }

}
