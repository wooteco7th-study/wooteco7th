package subway.domain.path;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import java.util.Optional;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.domain.station.StationType;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

// 최단거리 찾기
// 연결 여부는 section에서 파악 -> 항상 section과 함께 사용하므로 합성 사용
public class PathFinder {

    private final Sections sections;
    private final DijkstraShortestPath shortestTimeGraph;
    private final DijkstraShortestPath shortestDistanceGraph;

    public PathFinder() {
        this.sections = new Sections(SectionType.findAll());
        this.shortestTimeGraph = initializeTimeGraph();
        this.shortestDistanceGraph = initializeDistanceGraph();
    }

    public void validatePathConnected(final String startVertex, final String endVertex) {
        List<String> path = calculateShortestDistancePath(startVertex, endVertex);
        validateConsecutiveConnections(path);
    }

    public List<String> calculateShortestTimePath(final String start, final String end) {
        return getPath(shortestTimeGraph, start, end);
    }

    public List<String> calculateShortestDistancePath(final String start, final String end) {
        return getPath(shortestDistanceGraph, start, end);
    }

    private void validateConsecutiveConnections(final List<String> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            String start = path.get(i);
            String end = path.get(i + 1);
            if (isNotConnected(start, end)) {
                throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
            }
        }
    }

    private DijkstraShortestPath initializeDistanceGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        initVertex(distanceGraph);
        initializeDistanceWeight(distanceGraph);
        return new DijkstraShortestPath(distanceGraph);
    }

    private DijkstraShortestPath initializeTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        initVertex(timesGraph);

        for (SectionType section : SectionType.findAll()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(section.getDepartureStationName(), section.getArrivalStationName()),
                    section.getTakenTime());
        }
        return new DijkstraShortestPath(timesGraph);
    }

    private boolean isNotConnected(final String start, final String end) {
        return !sections.isConnected(start, end);
    }

    private void initializeDistanceWeight(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (SectionType section : SectionType.findAll()) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getDepartureStationName(),
                            section.getArrivalStationName()),
                    section.getDistance());
        }
    }

    private void initVertex(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (StationType stationType : StationType.values()) {
            distanceGraph.addVertex(stationType.name());
        }
    }

    private List<String> getPath(final DijkstraShortestPath graph, final String start, final String end) {
        return Optional.ofNullable(graph.getPath(start, end))
                .map(GraphPath::getVertexList)
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_PATH));
    }

    public int getTotalDistance(final List<String> path) {
        return sections.getTotalDistance(path);
    }

    public int getTotalTime(final List<String> path) {
        return sections.getTotalTime(path);
    }
}
