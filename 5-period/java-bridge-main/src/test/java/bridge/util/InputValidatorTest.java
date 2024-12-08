package bridge.util;

import static bridge.support.CustomAssertions.assertIllegalArgument;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import bridge.exception.ErrorMessage;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
                () -> InputValidator.validateNotNullOrBlank(input, ErrorMessage.INVALID_NUMBER),
                ErrorMessage.INVALID_NUMBER);
    }

    @Test
    @DisplayName("주어진 패턴이 입력 문자열과 일치하지 않는지 확인할 수 있다")
    void isNotSuitablePattern() {
        // Given
        String input = "잘못된 형식의 문자열";
        Pattern pattern = Pattern.compile("이름: (.*), 나이: (\\d+)");

        // When & Then
        assertThat(InputValidator.isInvalidPattern(input, pattern)).isTrue();
    }

    @Test
    @DisplayName("주어진 패턴이 입력 문자열과 일치하는지 확인할 수 있다")
    void isSuitablePattern() {
        // Given
        String input = "이름: 홍길동, 나이: 20";
        Pattern pattern = Pattern.compile("이름: (.*), 나이: (\\d+)");

        // When & Then
        assertThat(InputValidator.isInvalidPattern(input, pattern)).isFalse();
    }
}
