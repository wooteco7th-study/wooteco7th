package menu.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import menu.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CoachNameGroupTest {

    @ParameterizedTest
    @MethodSource("invalidSize")
    @DisplayName("코치가 2~5명을 벗어나 예외가 발생한다.")
    void validateSize(final List<CoachName> coachNames) throws Exception {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CoachNameGroup(coachNames))
                .withMessageContaining(ErrorMessage.INVALID_COACHES_SIZE.getMessage());
    }


    private static Stream<List<CoachName>> invalidSize() {
        return Stream.of(
                List.of(new CoachName("수달")),
                List.of(new CoachName("수달"), new CoachName("밍트"), new CoachName("러키"), new CoachName("람쥐"), new CoachName("로케"), new CoachName("희용")
        ));
    }

}
