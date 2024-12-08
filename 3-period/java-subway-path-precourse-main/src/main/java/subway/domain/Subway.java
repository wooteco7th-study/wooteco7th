package subway.domain;

import java.util.List;
import java.util.Objects;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import subway.domain.edge.EdgeGroup;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

public class Subway {

    private final EdgeGroup edgeGroup;
    private final Path path;

    public Subway(final EdgeGroup edgeGroup, final Path path) {
        this.edgeGroup = edgeGroup;
        this.path = path;
    }

    public int calculateTime(final List<String> shortestPath) {
        int time = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            final Station start = StationRepository.findByName(shortestPath.get(i));
            final Station end = StationRepository.findByName(shortestPath.get(i + 1));
            time += edgeGroup.getTimeByStartAndEnd(start, end);
        }
        return time;
    }

    public int calculateDistance(final List<String> shortestPath) {
        int distance = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            final Station start = StationRepository.findByName(shortestPath.get(i));
            final Station end = StationRepository.findByName(shortestPath.get(i + 1));
            distance += edgeGroup.getDistanceByStartAndEnd(start, end);
        }
        return distance;
    }

    public List<String> getShortestPath(final PathCommand pathCommand) {
        if (Objects.equals(pathCommand, PathCommand.SHORTEST_TIME)) {
            final DijkstraShortestPath dijkstraShortestTimePath = edgeGroup.getDijkstraShortestTimePath();
            return (List<String>) dijkstraShortestTimePath.getPath(path.getStart(), path.getEnd()).getVertexList();
        }
        final DijkstraShortestPath dijkstraShortestDistancePath = edgeGroup.getDijkstraShortestDistancePath();
        return dijkstraShortestDistancePath.getPath(path.getStart(), path.getEnd()).getVertexList();
    }

}
