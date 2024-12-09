package oncall.domain.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LegalHolidayTest {

    @ParameterizedTest
    @CsvSource({"1,1,true", "1,2,false, 5,5,true"})
    @DisplayName("법정 공휴일인지 확인한다.")
    void isLegalHoliday(int month, int day, boolean expected) {
        // Given

        // When & Then
        assertThat(LegalHoliday.isLegalHoliday(month, day)).isEqualTo(expected);
    }
}
