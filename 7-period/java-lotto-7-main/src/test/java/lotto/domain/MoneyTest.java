package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1100, 2100, 1001})
    @DisplayName("천원 단위가 아니므로 예외가 발생한다.")
    void validateUnit(final int number) {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(number))
                .withMessageContaining(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    @DisplayName("천원 미만이므로 예외가 발생한다.")
    void validateRange(final int number) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(number))
                .withMessageContaining(ErrorMessage.EXCEEDS_MONEY.getMessage());

    }

}
