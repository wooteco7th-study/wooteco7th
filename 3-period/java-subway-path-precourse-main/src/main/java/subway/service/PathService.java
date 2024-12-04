package subway.service;

import static subway.domain.PathCriteriaOption.MINIMUM_TIME;
import static subway.domain.PathCriteriaOption.SHORTEST_DISTANCE;

import java.util.List;
import subway.domain.PathCriteriaOption;
import subway.dto.PathSearchResult;

public class PathService {
    private final DistanceShortestPath distanceShortestPath;
    private final TimeShortestPath timeShortestPath;

    public PathService(final DistanceShortestPath distanceShortestPath, final TimeShortestPath timeShortestPath) {
        this.distanceShortestPath = distanceShortestPath;
        this.timeShortestPath = timeShortestPath;
    }

    public PathSearchResult findPath(String departure, String arrival, PathCriteriaOption criteriaOption) {
        if (SHORTEST_DISTANCE == criteriaOption) {
            List<String> path = distanceShortestPath.getPath(departure, arrival);
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
            List<String> path = timeShortestPath.getPath(departure, arrival);
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
