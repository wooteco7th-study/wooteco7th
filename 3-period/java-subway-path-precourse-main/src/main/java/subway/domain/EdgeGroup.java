package subway.domain;

import java.util.Collections;
import java.util.List;
import subway.error.CustomIllegalStateException;
import subway.error.ErrorMessage;

public class EdgeGroup {

    private final List<Edge> edges;

    public EdgeGroup(final List<Edge> edges) {
        this.edges = edges;
    }

    public int getDistanceByStartAndEnd(final Station start, final Station end) {
        return edges.stream()
                .filter(edge -> edge.hasStartAndEnd(start, end))
                .findAny()
                .map(Edge::getDistance)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_FOUND_STATION));
    }

    public int getTimeByStartAndEnd(final Station start, final Station end) {
        return edges.stream()
                .filter(edge -> edge.hasStartAndEnd(start, end))
                .findAny()
                .map(Edge::getTime)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_FOUND_STATION));
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }
}
