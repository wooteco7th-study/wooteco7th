package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekTest {

    @DisplayName("다음 요일을 찾는다.")
    @Test
    void getNextTest() {
        //given
        final Week saturday = Week.SATURDAY;

        //should
        assertThat(Week.getNext(saturday)).isEqualTo(Week.SUNDAY);
    }
}
