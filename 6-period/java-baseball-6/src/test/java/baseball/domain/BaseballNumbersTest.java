package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.domain.baseball.BaseballNumbers;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("숫자 테스트")
class BaseballNumbersTest {

    @Nested
    @DisplayName("생성 테스트")
    class 생성_테스트 {

        @Test
        @DisplayName("중복되는 숫자가 있으면 실패한다.")
        void 실패_중복숫자() {
            // Given
            List<Integer> numbers = List.of(1, 2, 2);

            // When & Then
            assertThatThrownBy(() -> BaseballNumbers.from(numbers))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("3자리 숫자만 입력할 수 있습니다.");
        }

        @Test
        @DisplayName("숫자가 1 이상 9 이하가 아니면 실패한다.")
        void 실패_범위() {
            // Given
            List<Integer> numbers = List.of(10, 2, 3);

            // When & Then
            assertThatThrownBy(() -> BaseballNumbers.from(numbers))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("1부터 9까지의 숫자만 입력할 수 있습니다.");
        }
    }

    @Test
    @DisplayName("스트라이크 수를 센다")
    void 성공_스트라이크() {
        // Given
        BaseballNumbers numbers = BaseballNumbers.from(List.of(1, 2, 3));
        BaseballNumbers compared = BaseballNumbers.from(List.of(1, 7, 8));

        // When
        int strike = numbers.countStrike(compared);

        // Then
        assertThat(strike).isEqualTo(1);
    }

    @Test
    @DisplayName("볼 수를 센다")
    void 성공_볼() {
        // Given
        BaseballNumbers numbers = BaseballNumbers.from(List.of(1, 2, 3));
        BaseballNumbers compared = BaseballNumbers.from(List.of(3, 7, 8));

        // When
        int ball = numbers.countBall(compared);

        // Then
        assertThat(ball).isEqualTo(1);
    }
}
