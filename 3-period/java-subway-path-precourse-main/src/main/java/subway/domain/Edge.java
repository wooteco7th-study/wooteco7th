package subway.domain;

import java.util.List;
import java.util.Objects;
import subway.error.ErrorMessage;
import subway.util.ListValidator;

public class Edge {

    private final Line line;
    private final Station start;
    private final Station end;
    private final int distance;
    private final int time;

    public Edge(final Line line, final Station start, final Station end, final int distance, final int time) {
        validate(start, end);
        this.line = line;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    private void validate(final Station start, final Station end) {
        ListValidator.validateDuplicate(List.of(start, end), ErrorMessage.INVALID_DUPLICATED_STATION);
    }

    public boolean hasStartAndEnd(final Station start, final Station end) {
        return Objects.equals(this.start, start) && Objects.equals(this.end, end);
    }

    public String getStartStationName() {
        return start.getName();
    }

    public String getEndStationName() {
        return end.getName();
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
