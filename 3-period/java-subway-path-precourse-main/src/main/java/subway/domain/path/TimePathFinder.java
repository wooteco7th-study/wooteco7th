package subway.domain.path;

import java.util.List;
import java.util.Optional;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public class TimePathFinder extends PathFinder {

    private final DijkstraShortestPath graph;

    public TimePathFinder(final Sections sections) {
        super(sections);
        this.graph = initializeGraph(SectionType::getTime);
    }

    @Override
    public List<String> calculateShortestPath(final String start, final String end) {
        return Optional.ofNullable(graph.getPath(start, end))
                .map(GraphPath::getVertexList)
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_STATION_PATH));
    }
}
