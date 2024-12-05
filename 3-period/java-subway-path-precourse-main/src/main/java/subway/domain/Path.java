package subway.domain;

import java.util.List;
import subway.domain.station.Station;
import subway.error.ErrorMessage;
import subway.util.ListValidator;

public class Path {

    private final Station start;
    private final Station end;

    public Path(final Station start, final Station end) {
        validate(start, end);
        this.start = start;
        this.end = end;
    }

    private void validate(final Station start, final Station end) {
        ListValidator.validateDuplicate(List.of(start, end), ErrorMessage.INVALID_DUPLICATED_STATION);
    }

    public String getStart() {
        return start.getName();
    }

    public String getEnd() {
        return end.getName();
    }
}
