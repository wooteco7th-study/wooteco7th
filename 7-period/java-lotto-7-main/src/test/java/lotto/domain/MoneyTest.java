package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {


    @Test
    @DisplayName("구입 금액이 1,000 단위가 아니므로 예외가 발생한다.")
    void validateUnitTest() throws Exception {
        //given
        final int number = 1100;

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(number))
                .withMessageContaining(ErrorMessage.INVALID_WRONG_MONEY_UNIT.getMessage());

    }

    @Test
    @DisplayName("구입 금액이 1,000 미만 이므로 예외가 발생한다.")
    void validateRangeTest() throws Exception {
        //given
        final int number1 = -1;
        final int number2 = 100;

        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new Money(number1))
                        .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_MONEY_RANGE.getMessage()),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new Money(number2))
                        .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_MONEY_RANGE.getMessage())
        );


    }
}
