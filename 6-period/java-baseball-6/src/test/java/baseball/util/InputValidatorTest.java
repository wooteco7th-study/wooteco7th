package baseball.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("입력값 검증 테스트")
class InputValidatorTest {

    @Test
    @DisplayName("null이면 예외가 발생한다.")
    void 실패_검증_null() {
        // Given

        // When & Then
        assertThatThrownBy(() -> InputValidator.validateNotNullOrBlank(null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("null일 수 없습니다.");
    }

    @Test
    @ValueSource(strings = {"", " "})
    @DisplayName("비어있으면 예외가 발생한다.")
    void 실패_검증_empty(String input) {
        // Given

        // When & Then
        assertThatThrownBy(() -> InputValidator.validateNotNullOrBlank(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백일 수 없습니다.");
    }
}
