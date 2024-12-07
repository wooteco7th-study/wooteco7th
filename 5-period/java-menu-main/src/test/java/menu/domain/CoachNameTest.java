package menu.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import menu.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoachNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"수달수달수", "수"})
    @DisplayName("코치 이름이 2~4글자를 벗어나 예외가 발생한다.")
    void validateRangeTest(final String name) throws Exception {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CoachName(name))
                .withMessageContaining(ErrorMessage.INVALID_COACH_NAME.getMessage());

    }

}
