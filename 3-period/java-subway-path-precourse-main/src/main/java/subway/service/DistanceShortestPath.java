package subway.service;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class DistanceShortestPath {
    private final DijkstraShortestPath dijkstraShortestPath;


    public DistanceShortestPath(final WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        dijkstraShortestPath = new DijkstraShortestPath(graph);
    }

    public List<String> getPath(String fromVertex, String toVertex) {
        return dijkstraShortestPath.getPath(fromVertex, toVertex).getVertexList();
    }

    public double getPathWeight(String fromVertex, String toVertex) {
        return dijkstraShortestPath.getPathWeight(fromVertex, toVertex);
    }


}
