package subway.domain.edge;

import java.util.Collections;
import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.station.Station;
import subway.error.CustomIllegalStateException;
import subway.error.ErrorMessage;

public class EdgeGroup {

    private final List<Edge> edges;

    public EdgeGroup(final List<Edge> edges) {
        this.edges = edges;
    }

    public int getDistanceByStartAndEnd(final Station start, final Station end) {
        return edges.stream()
                .filter(edge -> edge.hasStartAndEnd(start, end))
                .findAny()
                .map(Edge::getDistance)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_LINKED_STATION));
    }

    public int getTimeByStartAndEnd(final Station start, final Station end) {
        return edges.stream()
                .filter(edge -> edge.hasStartAndEnd(start, end))
                .findAny()
                .map(Edge::getTime)
                .orElseThrow(() -> new CustomIllegalStateException(ErrorMessage.INVALID_NOT_LINKED_STATION));
    }

    public DijkstraShortestPath getDijkstraShortestTimePath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph
                = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        for (Edge edge : edges) {
            graph.addVertex(edge.getStartStationName());
            graph.addVertex(edge.getEndStationName());
            graph.setEdgeWeight(graph.addEdge(edge.getStartStationName(), edge.getEndStationName()), edge.getTime());
        }
        return new DijkstraShortestPath(graph);
    }

    public DijkstraShortestPath getDijkstraShortestDistancePath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph
                = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        for (Edge edge : edges) {
            graph.addVertex(edge.getStartStationName());
            graph.addVertex(edge.getEndStationName());
            graph.setEdgeWeight(graph.addEdge(edge.getStartStationName(), edge.getEndStationName()), edge.getDistance());
        }
        return new DijkstraShortestPath(graph);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }
}
