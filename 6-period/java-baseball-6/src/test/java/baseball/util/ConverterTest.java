package baseball.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Converter 테스트")
class ConverterTest {

    @Test
    @DisplayName("문자를 숫자로 변환한다.")
    void 성공_숫자변환() {
        // Given
        char input = '1';

        // When
        int number = Converter.convertToInteger(input);

        // Then
        assertThat(number).isEqualTo(1);
    }

    @Test
    @DisplayName("숫자를 나타내는 문자가 아닐 경우 예외가 발생한다.")
    void 실패_숫자변환() {
        // Given
        char input = 'a';

        // When
        assertThatThrownBy(() -> Converter.convertToInteger(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력할 수 있습니다.");
    }
}
