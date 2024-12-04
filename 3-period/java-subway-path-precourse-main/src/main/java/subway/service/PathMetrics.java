package subway.service;

public class PathMetrics {
    final int distanceSum;
    final int timeSum;

    PathMetrics(int distanceSum, int timeSum) {
        this.distanceSum = distanceSum;
        this.timeSum = timeSum;
    }
}
