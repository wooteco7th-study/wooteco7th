package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayOfWeekTest {

    @DisplayName("다음 요일을 찾는다.")
    @Test
    void getNextTest() {
        //given
        final DayOfWeek saturday = DayOfWeek.SATURDAY;

        //should
        assertThat(DayOfWeek.getNext(saturday)).isEqualTo(DayOfWeek.SUNDAY);
    }
}
