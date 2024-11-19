package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("결과 테스트")
class BaseballResultTest {

    @Test
    @DisplayName("생성 테스트")
    void 성공_생성() {
        // Given
        int strike = 2;
        int ball = 1;

        // When & Then
        assertThatCode(() -> {
            new BaseballResult(strike, ball);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"3,0,true", "1,2,false"})
    @DisplayName("승리 여부 조회 테스트")
    void 성공_승리여부조회(int strike, int ball, boolean expected) {
        // Given
        BaseballResult baseballResult = new BaseballResult(strike, ball);

        // When & Then
        assertThat(baseballResult.isWin()).isEqualTo(expected);
    }
}
