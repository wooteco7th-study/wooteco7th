package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import oncall.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayWorkerGroupTest {

    @DisplayName("다음 근무자를 가져온다.")
    @Test
    void getNextWorkerTest() {
        //when
        final WeekdayWorkerGroup weekdayWorkerGroup = new WeekdayWorkerGroup(List.of("밍트", "람쥐", "수달", "러키", "고로케"));
        final String nextWorker1 = weekdayWorkerGroup.getNextWorker();
        final String nextWorker2 = weekdayWorkerGroup.getNextWorker();
        final String nextWorker3 = weekdayWorkerGroup.getNextWorker();
        final String nextWorker4 = weekdayWorkerGroup.getNextWorker();
        final String nextWorker5 = weekdayWorkerGroup.getNextWorker();
        final String nextWorker6 = weekdayWorkerGroup.getNextWorker();

        //then
        assertAll(
                () -> assertThat(nextWorker1).isEqualTo("밍트"),
                () -> assertThat(nextWorker2).isEqualTo("람쥐"),
                () -> assertThat(nextWorker3).isEqualTo("수달"),
                () -> assertThat(nextWorker4).isEqualTo("러키"),
                () -> assertThat(nextWorker5).isEqualTo("고로케"),
                () -> assertThat(nextWorker6).isEqualTo("밍트")
        );
    }

    @DisplayName("바뀐 다음 근무자를 가져온다.")
    @Test
    void getChangedWorkerTest() {
        //when
        final WeekdayWorkerGroup weekdayWorkerGroup = new WeekdayWorkerGroup(List.of("밍트", "람쥐", "수달", "러키", "고로케"));
        final String nextWorker1 = weekdayWorkerGroup.getChangedWorker("희용");
        final String nextWorker2 = weekdayWorkerGroup.getNextWorker();

        //then
        assertAll(
                () -> assertThat(nextWorker1).isEqualTo("밍트"),
                () -> assertThat(nextWorker2).isEqualTo("희용")
        );
    }

    @Test
    @DisplayName("이름이 5글자가 넘는 근무자가 존재하여 예외가 발생한다.")
    void validateLengthTest() throws Exception {
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new WeekdayWorkerGroup(List.of("밍트밍트밍트", "람쥐", "수달", "러키", "고로케")))
                .withMessageContaining(ErrorMessage.INVALID_INPUT.getMessage());

    }
}
