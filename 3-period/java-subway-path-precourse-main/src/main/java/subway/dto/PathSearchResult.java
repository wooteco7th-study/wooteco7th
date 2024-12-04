package subway.dto;

import java.util.List;

public class PathSearchResult {
    private final int totalDistance;
    private final int totalTime;
    private final List<String> stations;

    public PathSearchResult(final int totalDistance, final int totalTime, final List<String> stations) {
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.stations = stations;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public List<String> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "PathSearchResult{" +
                "distanceSum=" + totalDistance +
                ", timeSum=" + totalTime +
                ", path=" + stations +
                '}';
    }
}
