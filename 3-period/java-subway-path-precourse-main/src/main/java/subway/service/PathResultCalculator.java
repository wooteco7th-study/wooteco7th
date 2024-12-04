package subway.service;

import java.util.List;
import subway.dto.PathSearchResult;

public class PathResultCalculator {
    private final DistanceShortestPath distanceShortestPath;
    private final TimeShortestPath timeShortestPath;

    public PathResultCalculator(DistanceShortestPath distanceShortestPath, TimeShortestPath timeShortestPath) {
        this.distanceShortestPath = distanceShortestPath;
        this.timeShortestPath = timeShortestPath;
    }

    public PathSearchResult calculateResult(List<String> path) {
        PathMetrics metrics = calculatePathMetrics(path);
        return new PathSearchResult(metrics.distanceSum, metrics.timeSum, path);
    }

    private PathMetrics calculatePathMetrics(List<String> path) {
        int distanceSum = 0;
        int timeSum = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);

            distanceSum += distanceShortestPath.getPathWeight(from, to);
            timeSum += timeShortestPath.getPathWeight(from, to);
        }

        return new PathMetrics(distanceSum, timeSum);
    }
}
