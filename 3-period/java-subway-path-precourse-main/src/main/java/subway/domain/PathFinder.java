package subway.domain;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.exception.CustomIllegalArgumentException;

public class PathFinder {

    private final DijkstraShortestPath shortestTimePath;
    private final DijkstraShortestPath shortestDistancePath;

    public PathFinder(final List<Route> routes) {
        this.shortestTimePath = initializeTimeGraph(routes);
        this.shortestDistancePath = initializeDistanceGraph(routes);
    }

    public void validatePathConnected(final RoutesRepository routesRepository,
                                      final Station departureStation, final Station arrivalStation) {
        List<Route> routes = routesRepository.getRoutes();
        List<String> path = getShortestDistancePath(departureStation, arrivalStation);
        for (int i = 0; i < path.size() - 1; i++) {
            String start = path.get(i);
            String end = path.get(i + 1);
            boolean isConnected = routesRepository.isConnected(routes, start, end);
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

    private DijkstraShortestPath initializeDistanceGraph(final List<Route> routes) {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        for (Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }

        for (Route route : routes) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getDistance());
        }
        return new DijkstraShortestPath(distanceGraph);
    }

    private DijkstraShortestPath initializeTimeGraph(final List<Route> routes) {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Station station : StationRepository.stations()) {
            timesGraph.addVertex(station.getName());
        }

        for (Route route : routes) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getTakenTime());
        }
        return new DijkstraShortestPath(timesGraph);
    }
}
