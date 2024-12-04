package subway.dto;

import java.util.List;

public class PathSearchResult {
    private final int distanceSum;
    private final int timeSum;
    private final List<String> path;

    public PathSearchResult(final int distanceSum, final int timeSum, final List<String> path) {
        this.distanceSum = distanceSum;
        this.timeSum = timeSum;
        this.path = path;
    }

    public int getDistanceSum() {
        return distanceSum;
    }

    public int getTimeSum() {
        return timeSum;
    }

    public List<String> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "PathSearchResult{" +
                "distanceSum=" + distanceSum +
                ", timeSum=" + timeSum +
                ", path=" + path +
                '}';
    }
}
