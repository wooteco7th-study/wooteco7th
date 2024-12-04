package subway.service;

import static subway.domain.option.PathCriteriaOption.MINIMUM_TIME;
import static subway.domain.option.PathCriteriaOption.SHORTEST_DISTANCE;

import java.util.List;
import subway.domain.option.PathCriteriaOption;
import subway.domain.repository.DistanceShortestPathGraph;
import subway.domain.repository.TimeShortestPathGraph;
import subway.domain.validator.RouteSearchValidator;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;
import subway.dto.PathSearchResult;

public class PathService {
    private final DistanceShortestPath distanceShortestPath;
    private final TimeShortestPath timeShortestPath;
    private final DistanceShortestPathGraph distanceShortestPathGraph;
    private final TimeShortestPathGraph timeShortestPathGraph;

    public PathService(final DistanceShortestPath distanceShortestPath, final TimeShortestPath timeShortestPath,
                       final DistanceShortestPathGraph distanceShortestPathGraph,
                       final TimeShortestPathGraph timeShortestPathGraph) {
        this.distanceShortestPath = distanceShortestPath;
        this.timeShortestPath = timeShortestPath;
        this.distanceShortestPathGraph = distanceShortestPathGraph;
        this.timeShortestPathGraph = timeShortestPathGraph;
    }

    public PathSearchResult findPath(DepartureStation departure, ArrivalStation arrival,
                                     PathCriteriaOption criteriaOption) {
        if (SHORTEST_DISTANCE == criteriaOption) {
            RouteSearchValidator.validate(departure, arrival, distanceShortestPathGraph.getGraph());

            List<String> path = distanceShortestPath.getPath(departure.getValue(), arrival.getValue());
            int distanceSum = 0;
            int timeSum = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                String from = path.get(i);
                String to = path.get(i + 1);
                double pathDistance = distanceShortestPath.getPathWeight(from, to);
                double pathTime = timeShortestPath.getPathWeight(from, to);
                distanceSum += pathDistance;
                timeSum += pathTime;
            }
            return new PathSearchResult(distanceSum, timeSum, path);
        }

        if (MINIMUM_TIME == criteriaOption) {
            RouteSearchValidator.validate(departure, arrival, timeShortestPathGraph.getGraph());

            List<String> path = timeShortestPath.getPath(departure.getValue(), arrival.getValue());
            int distanceSum = 0;
            int timeSum = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                String from = path.get(0);
                String to = path.get(1);
                double pathDistance = distanceShortestPath.getPathWeight(from, to);
                double pathTime = timeShortestPath.getPathWeight(from, to);
                distanceSum += pathDistance;
                timeSum += pathTime;
            }
            return new PathSearchResult(distanceSum, timeSum, path);
        }
        throw new IllegalArgumentException("[ERROR] 경로를 찾을 수 없습니다.");
    }
}
