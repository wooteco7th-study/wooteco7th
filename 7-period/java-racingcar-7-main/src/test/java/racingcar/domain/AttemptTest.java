package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.ErrorMessage;

class AttemptTest {

    @Test
    @DisplayName("시도 횟수가 10_000을 초과하여 예외가 발생 한다.")
    void validateRangeMaxTest() throws Exception {
        //given
        final int attempt = 10_001;
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Attempt(attempt))
                .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_ATTEMPT_RANGE.getMessage());

    }

    @Test
    @DisplayName("시도 횟수가 1미만 이므로 예외가 발생한다.")
    void validateRangeMinTest() throws Exception {
        //given
        final int attempt = -1;
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Attempt(attempt))
                .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_ATTEMPT_RANGE.getMessage());

    }

}
