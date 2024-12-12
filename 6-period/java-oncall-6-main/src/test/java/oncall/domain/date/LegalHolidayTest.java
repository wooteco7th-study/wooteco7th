package oncall.domain.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LegalHolidayTest {

    @ParameterizedTest
    @CsvSource({"1,true", "2,false"})
    @DisplayName("법정공휴일인지 확인한다.")
    void isHoliday(int day, boolean expected) {
        // Given

        // When & Then
        assertThat(LegalHoliday.isLegalHoliday(MonthType.JAN, day)).isEqualTo(expected);
    }
}
