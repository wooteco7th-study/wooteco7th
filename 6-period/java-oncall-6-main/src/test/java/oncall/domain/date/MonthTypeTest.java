package oncall.domain.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MonthTypeTest {

    @Test
    @DisplayName("달에 해당되는 MonthType을 반환한다.")
    void from() {
        assertThat(MonthType.from(1)).isEqualTo(MonthType.JAN);
    }
}
