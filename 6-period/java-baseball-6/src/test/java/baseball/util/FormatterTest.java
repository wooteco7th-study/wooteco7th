package baseball.util;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.domain.baseball.BaseballResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("포매터 테스트")
class FormatterTest {

    private Formatter formatter;

    @BeforeEach
    void setup() {
        formatter = new Formatter();
    }

    @Test
    @DisplayName("볼이 존재할 경우 볼 메세지를 포함한다.")
    void 성공_볼() {
        // Given
        BaseballResult result = new BaseballResult(0, 1);

        // When
        String message = formatter.makeResult(result);

        // Then
        assertThat(message).contains("1볼");
    }

    @Test
    @DisplayName("스트라이크가 존재할 경우 스트라이크 메세지를 포함한다.")
    void 성공_스트라이크() {
        // Given
        BaseballResult result = new BaseballResult(1, 0);

        // When
        String message = formatter.makeResult(result);

        // Then
        assertThat(message).contains("1스트라이크");
    }

    @Test
    @DisplayName("볼과 스트라이크 모두 존재하지 않을 경우 낫싱 메세지를 생성한다.")
    void 성공_낫싱() {
        // Given
        BaseballResult result = new BaseballResult(0, 0);

        // When
        String message = formatter.makeResult(result);

        // Then
        assertThat(message).isEqualTo("낫싱");
    }
}
