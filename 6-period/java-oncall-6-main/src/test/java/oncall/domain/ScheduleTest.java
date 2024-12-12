package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
