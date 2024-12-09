package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import oncall.domain.date.DayType;
import oncall.domain.date.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HolidayCheckerTest {

    private HolidayChecker holidayChecker;

    @BeforeEach
    void setUp() {
        holidayChecker = new HolidayChecker(new Month(5, DayType.목.name()));
    }

    @ParameterizedTest
    @CsvSource({"2,false, 3,true"})
    @DisplayName("휴일인지 확인한다.")
    void isHoliday(int inputDay, boolean expected) {
        // Given

        // When & Then
        assertThat(holidayChecker.isHoliday(inputDay)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("해당 날짜의 요일을 구한다.")
    void calculateDayType(int inputDay, DayType expected) {
        // Given

        // When
        assertThat(holidayChecker.calculateDayType(inputDay)).isEqualTo(expected);

        // Then
    }

    private static Stream<Arguments> calculateDayType() {
        return Stream.of(
                Arguments.of(3, DayType.토),
                Arguments.of(6, DayType.화)
        );
    }

    @ParameterizedTest
    @CsvSource({"3,false, 5,true, 6,false, 8,true"})
    @DisplayName("평일이면서 법정공휴일의 경우인지 확인한다.")
    void isWeekdayHoliday(int inputDay, boolean expected) {
        // Given

        // When & Then
        assertThat(holidayChecker.isWeekdayHoliday(inputDay)).isEqualTo(expected);
    }
}
