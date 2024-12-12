package oncall.domain.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.date.DayType;
import oncall.domain.date.Month;
import oncall.domain.date.MonthType;
import oncall.domain.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkSchedulerTest {

    @Test
    @DisplayName("근무표를 작성한다.")
    void process() {
        // Given
        Month month = new Month(MonthType.APR, DayType.목);
        Turn weekdayTurn = new Turn(List.of("밍트", "수달", "러키", "진수", "로케"));
        Turn holidayTurn = new Turn(List.of("수달", "로케", "진수", "러키", "밍트"));
        WorkScheduler workScheduler = new WorkScheduler(month, new Turns(weekdayTurn, holidayTurn), new ArrayList<>());

        // When
        List<Name> result = workScheduler.process();

        // Then
        assertAll(
                () -> assertThat(result).hasSize(MonthType.APR.getEndDay()),
                () -> assertThat(result).extracting("value").containsExactlyInAnyOrder("밍트",
                        "수달",
                        "로케",
                        "수달",
                        "러키",
                        "진수",
                        "로케",
                        "밍트",
                        "수달",
                        "진수",
                        "러키",
                        "진수",
                        "러키",
                        "로케",
                        "밍트",
                        "수달",
                        "밍트",
                        "수달",
                        "러키",
                        "진수",
                        "로케",
                        "밍트",
                        "수달",
                        "로케",
                        "진수",
                        "러키",
                        "진수",
                        "로케",
                        "밍트",
                        "수달")
                );
    }
}
