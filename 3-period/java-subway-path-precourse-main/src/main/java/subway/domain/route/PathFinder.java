package subway.domain.route;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.route.Route;
import subway.domain.route.RoutesRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.CustomIllegalArgumentException;

public class PathFinder {

    private final DijkstraShortestPath shortestTimePath;
    private final DijkstraShortestPath shortestDistancePath;

    public PathFinder() {
        this.shortestTimePath = initializeTimeGraph();
        this.shortestDistancePath = initializeDistanceGraph();
    }

    public void validatePathConnected(final Station departureStation, final Station arrivalStation) {
        List<String> path = getShortestDistancePath(departureStation, arrivalStation);
        for (int i = 0; i < path.size() - 1; i++) {
            String start = path.get(i);
            String end = path.get(i + 1);
            boolean isConnected = RoutesRepository.isConnected(start, end);
            if (!isConnected) {
                throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
            }
        }
    }

    public int getShortestTime(final Station start, final Station end) {
        GraphPath path = shortestTimePath.getPath(start.getName(), end.getName());
        return (int) path.getWeight();
    }

    public int getShortestDistance(final Station start, final Station end) {
        GraphPath path = shortestDistancePath.getPath(start.getName(), end.getName());
        return (int) path.getWeight();
    }

    public List<String> getShortestTimePath(final Station start, final Station end) {
        return shortestTimePath.getPath(start.getName(), end.getName()).getVertexList();
    }

    public List<String> getShortestDistancePath(final Station start, final Station end) {
        return shortestDistancePath.getPath(start.getName(), end.getName()).getVertexList();
    }

    private DijkstraShortestPath initializeDistanceGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        for (Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }

        for (Route route : RoutesRepository.routes()) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getDistance());
        }
        return new DijkstraShortestPath(distanceGraph);
    }

    private DijkstraShortestPath initializeTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Station station : StationRepository.stations()) {
            timesGraph.addVertex(station.getName());
        }

        for (Route route : RoutesRepository.routes()) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getTakenTime());
        }
        return new DijkstraShortestPath(timesGraph);
    }
}
