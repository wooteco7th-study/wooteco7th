package oncall.domain.date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MonthTest {

    @Test
    @DisplayName("휴일인지 확인한다.")
    void isHoliday(int value, boolean expected) {
        // Given
        Month month = new Month(MonthType.MAY, DayType.금);

        // When & Then
        month.isHoliday(value);

    }

    @Test
    void isSpecialDay() {
    }
}
