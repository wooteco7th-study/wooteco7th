package subway.domain.path;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import java.util.Optional;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.Section;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class PathFinder {

    private final DijkstraShortestPath shortestTimeGraph;
    private final DijkstraShortestPath shortestDistanceGraph;

    public PathFinder() {
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
        initEdgeWeight(distanceGraph);
        return new DijkstraShortestPath(distanceGraph);
    }

    private DijkstraShortestPath initializeTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        initVertex(timesGraph);

        for (Section section : SectionRepository.routes()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(section.getDepartureStation().getName(), section.getArrivalStation().getName()),
                    section.getTakenTime());
        }
        return new DijkstraShortestPath(timesGraph);
    }

    private boolean isNotConnected(final String start, final String end) {
        return !SectionRepository.isConnected(start, end);
    }

    private void initEdgeWeight(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Section section : SectionRepository.routes()) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getDepartureStation().getName(),
                            section.getArrivalStation().getName()),
                    section.getDistance());
        }
    }

    private void initVertex(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }
    }

    private List<String> getPath(final DijkstraShortestPath graph, final String start, final String end) {
        return Optional.ofNullable(graph.getPath(start, end))
                .map(GraphPath::getVertexList)
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_PATH));
    }

    public int getTotalDistance(final List<String> path) {
        return SectionRepository.getTotalDistance(path);
    }

    public int getTotalTime(final List<String> path) {
        return SectionRepository.getTotalTime(path);
    }
}
