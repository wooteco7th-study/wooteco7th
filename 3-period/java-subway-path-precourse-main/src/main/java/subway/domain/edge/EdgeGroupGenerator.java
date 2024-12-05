package subway.domain.edge;

import java.util.List;
import java.util.stream.Collectors;
import subway.domain.line.Line;
import subway.domain.station.Station;

public class EdgeGroupGenerator {

    private EdgeGroupGenerator() {

    }

    public static EdgeGroup generate() {
        final List<EdgeInfo> edgeInfos = EdgeInfo.findAll();
        final List<Edge> edges = edgeInfos.stream()
                .map(EdgeGroupGenerator::createEdges)
                .collect(Collectors.toList());
        return new EdgeGroup(edges);
    }

    private static Edge createEdges(final EdgeInfo edgeInfo) {
        final Line lineType = new Line(edgeInfo.getLineType().getName());
        final Station start = new Station(edgeInfo.getStart().getName());
        final Station end = new Station(edgeInfo.getEnd().getName());
        final int distance = edgeInfo.getDistance();
        final int time = edgeInfo.getTime();
        return new Edge(lineType, start, end, distance, time);
    }
}
