package christmas.util;

import java.time.LocalDate;

public class LocalDateConvertor {

    private LocalDateConvertor() {
        
    }

    public static LocalDate convert(final int year, final int month, final int day) {
        return LocalDate.of(year, month, day);
    }
}
