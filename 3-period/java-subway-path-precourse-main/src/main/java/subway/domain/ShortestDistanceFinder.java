package subway.domain;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class ShortestDistanceFinder {
    private final DijkstraShortestPath<Station, DefaultWeightedEdge> dijkstraShortestPath;
    private int totalDistance;
    private int totalTime;

    public ShortestDistanceFinder() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        ShortestDistanceRepository.connections()
                .forEach(connection -> addPathToGraph(graph, connection));

        this.dijkstraShortestPath = new DijkstraShortestPath<>(graph);
    }

    public GraphPath<Station, DefaultWeightedEdge> findPath(Station start, Station end) {
        return dijkstraShortestPath.getPath(start, end);
    }

    private void addPathToGraph(WeightedMultigraph<Station, DefaultWeightedEdge> graph, StationDistanceConnection connection) {
        graph.addVertex(connection.getStartStation());
        graph.addVertex(connection.getEndStation());
        int distance = connection.getDistance();

        graph.setEdgeWeight(graph.addEdge(connection.getStartStation(), connection.getEndStation()), distance);
    }
}
