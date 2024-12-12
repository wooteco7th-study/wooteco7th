package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import oncall.common.CustomAssertions;
import oncall.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScheduleTest {

    @Test
    @DisplayName("다음 근무자를 가져온다")
    void getNextWorkerTest() {
        //given
        final List<String> workers = List.of("밍트", "수달", "람쥐", "러키", "로케");
        //when
        final Schedule schedule = new Schedule(workers);
        final String nextWorker1 = schedule.getNextWorker();
        final String nextWorker2 = schedule.getNextWorker();
        final String nextWorker3 = schedule.getNextWorker();
        final String nextWorker4 = schedule.getNextWorker();
        final String nextWorker5 = schedule.getNextWorker();
        final String nextWorker6 = schedule.getNextWorker();
        //then
        assertAll(
                () -> assertThat(nextWorker1).isEqualTo("밍트"),
                () -> assertThat(nextWorker2).isEqualTo("수달"),
                () -> assertThat(nextWorker3).isEqualTo("람쥐"),
                () -> assertThat(nextWorker4).isEqualTo("러키"),
                () -> assertThat(nextWorker5).isEqualTo("로케"),
                () -> assertThat(nextWorker6).isEqualTo("밍트")
        );
    }

    @Test
    @DisplayName("다음 근무자를 스왑하여 가져온다.")
    void getChangedNextWorkerTest() {
        //given
        final List<String> workers = List.of("밍트", "수달", "람쥐", "러키", "로케");

        //when
        final Schedule schedule = new Schedule(workers);
        final String worker = schedule.getChangedNextWorker("희용");
        final String nextWorker1 = schedule.getNextWorker();

        //then
        assertAll(
                () -> assertThat(worker).isEqualTo("밍트"),
                () -> assertThat(nextWorker1).isEqualTo("희용")
        );
    }

    @Test
    @DisplayName("근무자가 중복되어 예외가 발생한다")
    void validateDuplicateTest() {
        //given
        final List<String> workers = List.of("밍트", "밍트", "람쥐", "러키", "로케");

        //should
        CustomAssertions.customAssertThatIllegalArgumentException(() -> new Schedule(workers),
                ErrorMessage.INVALID_INPUT);

    }

    @Test
    @DisplayName("근무자의 이름이 5글자를 초과하여 예외가 발생한다")
    void validateNameLengthTest() {
        //given
        final List<String> workers = List.of("밍트밍트밍트", "수달", "람쥐", "러키", "로케");

        //should
        CustomAssertions.customAssertThatIllegalArgumentException(() -> new Schedule(workers),
                ErrorMessage.INVALID_INPUT);
    }

    @ParameterizedTest
    @MethodSource("invalidWorkers")
    @DisplayName("근무자가 5 ~ 35 범위를 벗어나 예외가 발생한다")
    void validateSizeTest(final List<String> workers) {
        //should
        CustomAssertions.customAssertThatIllegalArgumentException(() -> new Schedule(workers),
                ErrorMessage.INVALID_INPUT);
    }

    private static Stream<Arguments> invalidWorkers() {
        return Stream.of(
                Arguments.of(List.of("밍트", "수달", "람쥐", "러키")),
                Arguments.of(List.of("밍트", "수달", "람쥐"))
        );
    }
}
