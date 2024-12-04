package subway.domain.repository;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class TimeShortestPath {
    private final DijkstraShortestPath dijkstraShortestPath;


    public TimeShortestPath(final WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        dijkstraShortestPath = new DijkstraShortestPath(graph);
    }

    public List<String> getPath(String fromVertex, String toVertex) {
        return dijkstraShortestPath.getPath(fromVertex, toVertex).getVertexList();
    }

}
