package subway.domain;

import java.util.List;
import java.util.Objects;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class Subway {

    private final EdgeGroup edgeGroup;
    private final Path path;

    public Subway(final EdgeGroup edgeGroup, final Path path) {
        this.edgeGroup = edgeGroup;
        this.path = path;
    }

    public int calculateShortestWeight(final PathCommand pathCommand) {
        if (Objects.equals(pathCommand, PathCommand.SHORTEST_TIME)) {
            final DijkstraShortestPath dijkstraShortestTimePath = edgeGroup.getDijkstraShortestTimePath();
            return (int) dijkstraShortestTimePath.getPathWeight(path.getStart(), path.getEnd());
        }
        final DijkstraShortestPath dijkstraShortestDistancePath = edgeGroup.getDijkstraShortestDistancePath();
        return (int) dijkstraShortestDistancePath.getPathWeight(path.getStart(), path.getEnd());

    }

    public List<String> getShortestPath(final PathCommand pathCommand) {
        if (Objects.equals(pathCommand, PathCommand.SHORTEST_TIME)) {
            final DijkstraShortestPath dijkstraShortestTimePath = edgeGroup.getDijkstraShortestTimePath();
            return (List<String>) dijkstraShortestTimePath.getPath(path.getStart(), path.getEnd());
        }
        final DijkstraShortestPath dijkstraShortestDistancePath = edgeGroup.getDijkstraShortestDistancePath();
        return (List<String>) dijkstraShortestDistancePath.getPath(path.getStart(), path.getEnd());
    }


}
