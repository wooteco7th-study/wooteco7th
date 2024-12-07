package subway.domain.path;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.Section;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.CustomIllegalArgumentException;

public class PathFinder {

    private final Graph shortestTimeGraph;
    private final Graph shortestDistanceGraph;

    public PathFinder() {
        this.shortestTimeGraph = initializeTimeGraph();
        this.shortestDistanceGraph = initializeDistanceGraph();
    }

    public void validatePathConnected(final String startVertex, final String endVertex) {
        List<String> path = getShortestDistancePath(startVertex, endVertex);
        for (int i = 0; i < path.size() - 1; i++) {
            String start = path.get(i);
            String end = path.get(i + 1);
            if (isNotConnected(start, end)) {
                throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
            }
        }
    }

    public int getShortestTime(final Station start, final Station end) {
        return (int) shortestTimeGraph.getPathWeight(start.getName(), end.getName());
    }

    public int getShortestDistance(final Station start, final Station end) {
        return (int) shortestDistanceGraph.getPathWeight(start.getName(), end.getName());
    }

    public List<String> getShortestTimePath(final String start, final String end) {
        return shortestTimeGraph.getPath(start, end);
    }

    public List<String> getShortestDistancePath(final String start, final String end) {
        return shortestDistanceGraph.getPath(start, end);
    }

    private Graph initializeDistanceGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        initVertex(distanceGraph);
        initEdgeWeight(distanceGraph);
        return new Graph(distanceGraph);
    }

    private boolean isNotConnected(final String start, final String end) {
        return !SectionRepository.isConnected(start, end);
    }

    private void initEdgeWeight(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Section section : SectionRepository.routes()) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(section.getDepartureStation().getName(), section.getArrivalStation().getName()),
                    section.getDistance());
        }
    }

    private void initVertex(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }
    }

    private Graph initializeTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        initVertex(timesGraph);

        for (Section section : SectionRepository.routes()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(section.getDepartureStation().getName(), section.getArrivalStation().getName()),
                    section.getTakenTime());
        }
        return new Graph(timesGraph);
    }
}
