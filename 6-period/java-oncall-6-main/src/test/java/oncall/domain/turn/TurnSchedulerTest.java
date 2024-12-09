package oncall.domain.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.IntStream;
import oncall.domain.HolidayChecker;
import oncall.domain.date.DayType;
import oncall.domain.date.Month;
import oncall.dto.TurnDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnSchedulerTest {

    @Test
    @DisplayName("순번대로 비상 근무표를 잘 작성했는지 확인한다.")
    void makeWorkSchedule() {
        // Given
        TurnScheduler turnScheduler = makeTurnScheduler();

        // When
        List<TurnDto> turnDtos = turnScheduler.makeWorkSchedule();

        // Then
        assertAll(
                () -> assertThat(turnDtos).extracting("name").contains("a", "b", "c", "d", "e"),
                () -> assertThat(turnDtos.size()).isEqualTo(31),
                () -> assertThat(turnDtos.get(4).isSpecialDay()).isTrue(),
                () -> assertThat(getACount(turnDtos)).isEqualTo(7),
                () -> assertThat(isAdjcent(turnDtos)).isFalse()
        );
    }

    private int getACount(final List<TurnDto> turnDtos) {
        return (int) turnDtos.stream()
                .map(TurnDto::name)
                .filter(name -> name.equals("a"))
                .count();
    }

    private boolean isAdjcent(final List<TurnDto> turnDtos) {
        return IntStream.range(1, 30)
                .anyMatch(i -> turnDtos.get(i).name().equals(turnDtos.get(i + 1).name()));
    }

    private TurnScheduler makeTurnScheduler() {
        Turn weekdayTurn = makeTurn(List.of("a", "b", "c", "d", "e"));
        Turn holidayTurn = makeTurn(List.of("e", "d", "c", "a", "b"));
        HolidayChecker holidayChecker = new HolidayChecker(new Month(5, DayType.월.name()));
        return new TurnScheduler(weekdayTurn, holidayTurn, holidayChecker);
    }

    private Turn makeTurn(List<String> input) {
        return new Turn(input);
    }
}
