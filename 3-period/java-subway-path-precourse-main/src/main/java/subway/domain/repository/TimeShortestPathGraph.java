package subway.domain.repository;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class TimeShortestPathGraph {
    WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);

    public void addVertex(String vertex) {
        graph.addVertex(vertex);
    }

    public void setEdgeWeight(String fromVertex, String toVertex, int weight) {
        graph.setEdgeWeight(graph.addEdge(fromVertex, toVertex), weight);
    }

    public WeightedMultigraph<String, DefaultWeightedEdge> getGraph() {
        return graph;
    }
}
