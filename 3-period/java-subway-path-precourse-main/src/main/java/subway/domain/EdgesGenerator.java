package subway.domain;

import java.util.List;
import java.util.stream.Collectors;

public class EdgesGenerator {

    private EdgesGenerator() {

    }

    public static List<Edge> generate() {
        final List<EdgeInfo> edgeInfos = EdgeInfo.findAll();
        return edgeInfos.stream()
                .flatMap(edgeInfo -> createEdges(edgeInfo).stream())
                .collect(Collectors.toList());
    }

    private static List<Edge> createEdges(final EdgeInfo edgeInfo) {
        final Line lineType = new Line(edgeInfo.getLineType().getName());
        final Station start = new Station(edgeInfo.getStart().getName());
        final Station end = new Station(edgeInfo.getEnd().getName());
        final int distance = edgeInfo.getDistance();
        final int time = edgeInfo.getTime();
        return List.of(new Edge(lineType, start, end, distance, time),
                new Edge(lineType, end, start, distance, time));
    }
}
