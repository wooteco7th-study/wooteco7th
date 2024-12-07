package menu.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import menu.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoachNameGroupTest {

    @Test
    @DisplayName("코치가 2명 미만 이므로 예외가 발생한다.")
    void validateMinSizeTest() throws Exception {
        //given
        final List<CoachName> coachNames = List.of(new CoachName("수달"));
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CoachNameGroup(coachNames))
                .withMessageContaining(ErrorMessage.INVALID_COACHES_MIN_SIZE.getMessage());
    }

    @Test
    @DisplayName("코치가 5명을 초과하여 예외가 발생한다.")
    void validateMaxSizeTest() throws Exception {
        //given
        final List<CoachName> coachNames = List.of(new CoachName("수달"), new CoachName("밍트"), new CoachName("러키"),
                new CoachName("람쥐"), new CoachName("로케"), new CoachName("희용"));
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CoachNameGroup(coachNames))
                .withMessageContaining(ErrorMessage.INVALID_COACHES_MAX_SIZE.getMessage());
    }

}
