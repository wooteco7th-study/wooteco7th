package lotto.util;

import static lotto.domain.support.CustomAssert.assertIllegalArgument;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    @DisplayName("null이거나 빈 값이면 예외가 발생한다")
    void validateNotNullOrBlank(String input) {
        assertIllegalArgument(
                () -> InputValidator.validateNotNullOrBlank(input, ErrorMessage.INVALID_INPUT),
                ErrorMessage.INVALID_INPUT);
    }
}
