package subway.domain.path;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import java.util.stream.IntStream;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.domain.station.StationType;
import subway.domain.weight.WeightFunction;
import subway.exception.CustomIllegalArgumentException;

public abstract class PathFinder {

    private final Sections sections;

    public PathFinder(final Sections sections) {
        this.sections = sections;
    }

    public abstract List<String> calculateShortestPath(String start, String end);

    public void validatePath(final String startVertex, final String endVertex) {
        List<String> path = calculateShortestPath(startVertex, endVertex);
        validateConsecutiveConnections(path);
    }

    public DijkstraShortestPath initializeGraph(WeightFunction weightFunction) {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        initializeVertex(graph);
        initializeWeight(weightFunction, graph);
        return new DijkstraShortestPath(graph);
    }

    private void initializeVertex(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (StationType stationType : StationType.values()) {
            distanceGraph.addVertex(stationType.name());
        }
    }

    private void initializeWeight(final WeightFunction weightFunction,
                                      final WeightedMultigraph<String, DefaultWeightedEdge> timesGraph) {
        for (SectionType section : SectionType.findAll()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(section.getDepartureStationName(), section.getArrivalStationName()),
                    weightFunction.getWeight(section));
        }
    }

    private void validateConsecutiveConnections(final List<String> path) {
        boolean isDisconnected = IntStream.range(0, path.size() - 1)
                .anyMatch(i -> isDisconnected(path.get(i), path.get(i + 1)));
        if (isDisconnected) {
            throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
        }
    }

    private boolean isDisconnected(final String start, final String end) {
        return !sections.isConnected(start, end);
    }

    public int getTotalDistance(final List<String> path) {
        return sections.calculateTotalDistance(path);
    }

    public int getTotalTime(final List<String> path) {
        return sections.calculateTotalTime(path);
    }
}
