package subway.domain;

import static subway.exception.ErrorMessage.INVALID_STATION_PATH;

import java.util.Collections;
import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

// 출발 지하철역(정점)에서 도착 지하철역까지의 거리, 시간
// - 출발 지하철역
//- 종료 지하철역
//- 걸린 시간
//- 이동 거리
public class RouteRepository {

    private final List<Route> routes;

    private final DijkstraShortestPath shortestTimePath;
    private final DijkstraShortestPath shortestDistancePath;

    public RouteRepository(final List<Route> routes, final List<Station> stations) {
        this.routes = routes;
        this.shortestTimePath = initializeTimeGraph(stations);
        this.shortestDistancePath = initializeDistanceGraph(stations);
    }

    public int getTime(final String start, final String end) {
        int time = getAdjacentDistance(start, end);
        if (time != 0) {
            return time;
        }
        return getAdjacentDistance(end, start);
    }

    private Integer getAdjacentDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(end))
                .filter(route -> route.getArrivalStation().getName().equals(start))
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
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_ARGUMENT));
    }

    public DijkstraShortestPath initializeDistanceGraph(final List<Station> stations) {
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

    public DijkstraShortestPath initializeTimeGraph(final List<Station> stations) {
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

    public Integer getShortestTime(final Station start, final Station end) {
        GraphPath path = shortestTimePath.getPath(start.getName(), end.getName());
        if (path == null) {
            return null;
        }
        return (int) path.getWeight();
    }

    public Integer getShortestDistance(final Station start, final Station end) {
        GraphPath path = shortestDistancePath.getPath(start.getName(), end.getName());
        if (path == null) {
            return null;
        }
        return (int) path.getWeight();
    }

    public List<String> getShortestTimePath(final Station start, final Station end) {
        GraphPath path = shortestTimePath.getPath(start.getName(), end.getName());
        return path.getVertexList();
    }

    public List<String> getShortestDistancePath(final Station start, final Station end) {
        GraphPath path = shortestDistancePath.getPath(start.getName(), end.getName());
        return path.getVertexList();
    }


    public List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public void add(final Route route) {
        routes.add(route);
    }

    public void validatePathConnected(final Station departureStation, final Station arrivalStation) {
        if (getShortestTime(departureStation, arrivalStation) == null) {
            throw new CustomIllegalArgumentException(INVALID_STATION_PATH);
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
