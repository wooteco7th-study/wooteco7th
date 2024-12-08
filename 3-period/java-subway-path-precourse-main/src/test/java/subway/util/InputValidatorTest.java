package subway.util;

import static subway.support.CustomAssert.assertIllegalArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import subway.exception.ErrorMessage;

class InputValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    @DisplayName("null이거나 빈 값이면 예외가 발생한다")
    void validateNotNullOrBlank(String input) {
        assertIllegalArgument(
                () -> InputValidator.validateNotNullOrBlank(input, ErrorMessage.INVALID_NUMBER),
                ErrorMessage.INVALID_NUMBER);
    }
}
