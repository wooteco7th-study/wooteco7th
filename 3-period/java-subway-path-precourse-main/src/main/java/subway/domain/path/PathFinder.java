package subway.domain.path;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.Route;
import subway.domain.route.RoutesRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.CustomIllegalArgumentException;

public class PathFinder {

    private final Path shortestTimePath;
    private final Path shortestDistancePath;

    public PathFinder() {
        this.shortestTimePath = initializeTimeGraph();
        this.shortestDistancePath = initializeDistanceGraph();
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
        return (int) shortestTimePath.getPathWeight(start.getName(), end.getName());
    }

    public int getShortestDistance(final Station start, final Station end) {
        return (int) shortestDistancePath.getPathWeight(start.getName(), end.getName());
    }

    public List<String> getShortestTimePath(final Station start, final Station end) {
        return shortestTimePath.getPath(start.getName(), end.getName());
    }

    public List<String> getShortestDistancePath(final String start, final String end) {
        return shortestDistancePath.getPath(start, end);
    }

    private Path initializeDistanceGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        initVertex(distanceGraph);
        initEdgeWeight(distanceGraph);
        return new Path(distanceGraph);
    }

    private boolean isNotConnected(final String start, final String end) {
        return !RoutesRepository.isConnected(start, end);
    }

    private void initEdgeWeight(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Route route : RoutesRepository.routes()) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getDistance());
        }
    }

    private void initVertex(final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        for (Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }
    }

    private Path initializeTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        initVertex(timesGraph);

        for (Route route : RoutesRepository.routes()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getTakenTime());
        }
        return new Path(timesGraph);
    }
}
