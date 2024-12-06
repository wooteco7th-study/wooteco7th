package christmas.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LocalDateConvertor {

    private LocalDateConvertor() {

    }

    public static LocalDate convert(final int year, final int month, final int day) {
        return LocalDate.of(year, month, day);
    }

    public static List<LocalDate> convert(final int year, final int month, final int ... days) {
        return Arrays.stream(days)
                .mapToObj(day -> convert(year, month, day))
                .toList();

    }
}
