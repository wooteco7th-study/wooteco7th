package subway.service;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Line;
import subway.domain.LineInfo;
import subway.domain.LineRepository;
import subway.domain.Result;
import subway.domain.Station;
import subway.domain.StationRepository;

public class WayFinder {

    private WeightedMultigraph<String, DefaultWeightedEdge> graph;

    public WayFinder(final boolean state) {
        init(state);
    }

    private void init(final boolean state) {
        graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }

        if (state) {
            initWithDistance();
            return;
        }
        initWithMinute();
    }

    private void initWithDistance() {
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            for (LineInfo lineInfo : line.getLineInfos()) {
                graph.setEdgeWeight(graph.addEdge(lineInfo.getPreStation(), lineInfo.getPostStation()), lineInfo.getBetweenDistance());
            }
        }
    }

    private void initWithMinute() {
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            for (LineInfo lineInfo : line.getLineInfos()) {
                graph.setEdgeWeight(graph.addEdge(lineInfo.getPreStation(), lineInfo.getPostStation()), lineInfo.getBetweenMinute());
            }
        }
    }

    public Result find(final String start, final String end) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath(graph);
        GraphPath<String, DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath(start, end);
        List<String> path = shortestPath.getVertexList();

        if (path == null) {
            throw new IllegalArgumentException();
        }

        return new Result(path);
    }
}
