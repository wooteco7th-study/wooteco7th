package subway.domain.path;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class Path {

    private final DijkstraShortestPath graph;

    public Path(final WeightedMultigraph<String, DefaultWeightedEdge> weightGraph) {
        this.graph = new DijkstraShortestPath(weightGraph);
    }

    public List<String> getPath(final String start, final String end) {
        return graph.getPath(start, end).getVertexList();
    }

    public double getPathWeight(final String start, final String end) {
        return graph.getPathWeight(start, end);
    }
}
