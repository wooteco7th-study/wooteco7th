package subway.service;

import java.util.List;
import subway.domain.option.PathCriteriaOption;
import subway.domain.repository.DistanceShortestPathGraph;
import subway.domain.repository.TimeShortestPathGraph;
import subway.domain.validator.RouteSearchValidator;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;

public class PathFinder {
    private final DistanceShortestPath distanceShortestPath;
    private final TimeShortestPath timeShortestPath;
    private final DistanceShortestPathGraph distanceShortestPathGraph;
    private final TimeShortestPathGraph timeShortestPathGraph;

    public PathFinder(DistanceShortestPath distanceShortestPath, TimeShortestPath timeShortestPath,
                      DistanceShortestPathGraph distanceShortestPathGraph,
                      TimeShortestPathGraph timeShortestPathGraph) {
        this.distanceShortestPath = distanceShortestPath;
        this.timeShortestPath = timeShortestPath;
        this.distanceShortestPathGraph = distanceShortestPathGraph;
        this.timeShortestPathGraph = timeShortestPathGraph;
    }

    public List<String> findPath(DepartureStation departure, ArrivalStation arrival,
                                 PathCriteriaOption criteriaOption) {
        if (criteriaOption == PathCriteriaOption.SHORTEST_DISTANCE) {
            return findDistancePath(departure, arrival);
        }
        if (criteriaOption == PathCriteriaOption.MINIMUM_TIME) {
            return findTimePath(departure, arrival);
        }
        throw new IllegalArgumentException("[ERROR] 경로를 찾을 수 없습니다.");
    }

    private List<String> findDistancePath(DepartureStation departure, ArrivalStation arrival) {
        RouteSearchValidator.validate(departure, arrival, distanceShortestPathGraph.getGraph());
        return distanceShortestPath.getPath(departure.getValue(), arrival.getValue());
    }

    private List<String> findTimePath(DepartureStation departure, ArrivalStation arrival) {
        RouteSearchValidator.validate(departure, arrival, timeShortestPathGraph.getGraph());
        return timeShortestPath.getPath(departure.getValue(), arrival.getValue());
    }
}
