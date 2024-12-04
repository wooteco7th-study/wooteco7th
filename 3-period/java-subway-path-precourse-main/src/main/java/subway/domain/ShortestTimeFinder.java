package subway.domain;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class ShortestTimeFinder {
    private final DijkstraShortestPath<Station, DefaultWeightedEdge> dijkstraShortestPath;

    public ShortestTimeFinder() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        ShortestTimeRepository.connections()
                .forEach(connection -> addPathToGraph(graph, connection));

        this.dijkstraShortestPath = new DijkstraShortestPath<>(graph);
    }

    public GraphPath<Station, DefaultWeightedEdge> findPath(Station start, Station end) {
        return dijkstraShortestPath.getPath(start, end);
    }

    private void addPathToGraph(WeightedMultigraph<Station, DefaultWeightedEdge> graph, StationTimeConnection connection) {
        graph.addVertex(connection.getStartStation());
        graph.addVertex(connection.getEndStation());
        int time = connection.getTime();

        graph.setEdgeWeight(graph.addEdge(connection.getStartStation(), connection.getEndStation()), time);
    }
}
