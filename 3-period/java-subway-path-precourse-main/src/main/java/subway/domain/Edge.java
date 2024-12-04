package subway.domain;

import java.util.Objects;

public class Edge {

    private final Line line;
    private final Station start;
    private final Station end;
    private final int distance;
    private final int time;

    public Edge(final Line line, final Station start, final Station end, final int distance, final int time) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
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
