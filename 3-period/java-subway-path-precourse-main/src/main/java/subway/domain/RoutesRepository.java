package subway.domain;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.exception.CustomIllegalArgumentException;

public class RoutesRepository {

    private final List<Route> routes;
    private final DijkstraShortestPath shortestTimePath;
    private final DijkstraShortestPath shortestDistancePath;

    public RoutesRepository(final List<Route> routes, final List<Station> stations) {
        this.routes = routes;
        this.shortestTimePath = initializeTimeGraph(stations);
        this.shortestDistancePath = initializeDistanceGraph(stations);
    }

    public int getTime(final String start, final String end) {
        return getAdjacentDistance(start, end);
    }

    private int getAdjacentDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getTakenTime)
                .findFirst()
                .orElse(0);
    }

    public int getDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getDistance)
                .findFirst()
                .orElse(0);
    }

    private DijkstraShortestPath initializeDistanceGraph(final List<Station> stations) {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
                DefaultWeightedEdge.class);
        for (Station station : stations) {
            distanceGraph.addVertex(station.getName());
        }

        for (Route route : routes) {
            distanceGraph.setEdgeWeight(
                    distanceGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getDistance());
        }
        return new DijkstraShortestPath(distanceGraph);
    }

    private DijkstraShortestPath initializeTimeGraph(final List<Station> stations) {
        WeightedMultigraph<String, DefaultWeightedEdge> timesGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Station station : stations) {
            timesGraph.addVertex(station.getName());
        }

        for (Route route : routes) {
            timesGraph.setEdgeWeight(
                    timesGraph.addEdge(route.getDepartureStation().getName(), route.getArrivalStation().getName()),
                    route.getTakenTime());
        }
        return new DijkstraShortestPath(timesGraph);
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

    public void add(final Route route) {
        routes.add(route);
    }

    public void validatePathConnected(final Station departureStation, final Station arrivalStation) {
        List<String> paths = getShortestDistancePath(departureStation, arrivalStation);
        for (int i = 0; i < paths.size() - 1; i++) {
            String start = paths.get(i);
            String end = paths.get(i + 1);
            boolean isConnected = routes.stream()
                    .anyMatch(route -> route.findRoute(StationRepository.findByName(start),
                            StationRepository.findByName(end)));
            if (!isConnected) {
                throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
            }
        }
    }

    public int getTotalTime(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getTime(start, end);
        }
        return total;
    }

    public int getTotalDistance(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getDistance(start, end);
        }
        return total;
    }
}
