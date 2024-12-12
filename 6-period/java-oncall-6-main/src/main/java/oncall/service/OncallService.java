package oncall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import oncall.domain.date.DayType;
import oncall.domain.date.Month;
import oncall.domain.name.Name;
import oncall.domain.name.Turns;
import oncall.domain.name.WorkScheduler;
import oncall.dto.TurnDto;

public class OncallService {


    public List<TurnDto> processSchedule(final Month month, final Turns turns) {
        WorkScheduler workScheduler = new WorkScheduler(month, turns, new ArrayList<>());
        List<Name> names = workScheduler.process();
        return convertToDtos(month, names);
    }

    private List<TurnDto> convertToDtos(final Month month, final List<Name> names) {
        return IntStream.range(1, names.size() + 1)
                .mapToObj(day -> convertToDto(month, day, names.get(day - 1)))
                .toList();
    }

    private TurnDto convertToDto(final Month month, final int day, final Name name) {
        DayType dayType = calculateDayType(month, day);
        return new TurnDto(month.getMonthTypeValue(), day, dayType.name(), name.getValue(), month.isSpecialDay(day));
    }

    private DayType calculateDayType(final Month month, final int day) {
        DayType startDayType = month.getStartDayType();
        return startDayType.calculateDayType(day);
    }
}
