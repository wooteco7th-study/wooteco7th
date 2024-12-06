package subway.domain.path;

import java.util.List;
import java.util.Optional;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class Graph {

    private final DijkstraShortestPath graph;

    public Graph(final WeightedMultigraph<String, DefaultWeightedEdge> weightGraph) {
        this.graph = new DijkstraShortestPath(weightGraph);
    }

    public List<String> getPath(final String start, final String end) {
        Optional<GraphPath> path = Optional.ofNullable(graph.getPath(start, end));
        if (path.isEmpty()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_PATH);
        }
        return path.get().getVertexList();
    }

    public double getPathWeight(final String start, final String end) {
        return graph.getPathWeight(start, end);
    }
}
